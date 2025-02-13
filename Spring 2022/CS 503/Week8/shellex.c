#include "csapp.h"
#include <unistd.h>

#define MAXARGS 128

/* function prototypes */
void eval (char * cmdline) ;
int parseline (char * buf, char * * argv) ;
int builtin_command (char * * argv) ;
void child_handler (int sig);

void child_handler (int sig)
{
    pid_t pid;
    int child_status,count;
    while ((pid = waitpid(-1, &child_status, WNOHANG)) > 0)
    {
        if (WIFEXITED(child_status))
        {
            printf("%d Exited child status [%d]\n",count, WEXITSTATUS(child_status));
        }
        else if (WIFSIGNALED(child_status))
        {
            printf("%d The process get terminated by signal %d\n",count,WTERMSIG(child_status));
        }
        else if (WIFSTOPPED(child_status))
        {
            printf("%d The process is stopped by signal %d\n",count,WSTOPSIG(child_status));
        }
        else if (WIFCONTINUED(child_status))
        {
            printf("The process is resumed\n");
        }
        else
            unix_error("waitpid error");
    }
}

int main () 
{
    char cmdline[MAXLINE];  /* command line */
    signal(SIGCHLD, child_handler);
    while (1)
    {
        /* read */
        printf (" > ") ;
        fgets (cmdline, MAXLINE, stdin) ;
        if (feof (stdin) )
            exit (0) ;
        /* evaluate */
        eval (cmdline) ;
    }
}

/* eval  -  evaluate a command line */
void eval (char * cmdline) 
{
    char *argv[MAXARGS];  /* argv for execve ()  */
    char *argvtemp[MAXARGS];  /* argv for execve ()  */
    char buf[MAXLINE];  /* holds modified command line */
    char  inputfilename[100];
    char outputfilename[100];
    char curdir[100];
    int HavePipe, i, j, bg;  /* should the job run in bg or fg? */
    int argc;  /* number of args */
    int fdin, fdout;
    int pfds[2];
    
    pid_t pid, pid2;  /* process id */
    
    strcpy (buf, cmdline) ;
    bg = parseline (buf, argv) ;
    if (argv[0] == NULL) return;  /* ignore empty lines */
    
    inputfilename[0] = outputfilename[0] = 0;
    for (argc = 0; argv[argc]; argc++)
    {
        if (*argv[argc] == '<') strcpy( inputfilename,argv[argc+1]);
        if (*argv[argc] == '>') strcpy(outputfilename,argv[argc+1]);
    }
    for (argc = 0; argv[argc]; argc++)
    {
        if (*argv[argc] == '<') *argv[argc] = *argv[argc+1] = 0;
        if (*argv[argc] == '>') *argv[argc] = *argv[argc+1] = 0;
    }
    i = 0;
    for (argc = 0; argv[argc]; argc++)
    {
        if (*argv[argc] != 0) argvtemp[i++] = argv[argc];
    }
    argvtemp[i] = NULL;
    for (argc = 0; argvtemp[argc]; argc++)
        argv[argc] = argvtemp[argc];
    argv[argc] = NULL;
    
    HavePipe = 0;
    for (i = 0; argv[i]; i++)
        if (*argv[i] == '|')
        {
            argv[i] = NULL;
            HavePipe = 1;
            break;
        }
    if (HavePipe)
    {
        i++;
        j = 0;
        for (; argv[i]; i++)
        {
            argvtemp[j++] = argv[i];
        }
        argvtemp[j] = NULL;
    }
    else
    {
        argvtemp[0] = NULL;
    }
    
    if (!builtin_command (argv) )
    {
        if (!HavePipe)
        {
            pid = fork ();
            if (pid == 0)
            {   /* child runs user job */
                if (*inputfilename)
                {
                    fdin = open(inputfilename,O_RDONLY); /* I removed the error checking */
                    dup2(fdin,0);
                    close(fdin);
                }
                if (*outputfilename)
                {
                    fdout = open(outputfilename,O_CREAT|O_TRUNC|O_RDWR, S_IRUSR|S_IWUSR);
                    dup2(fdout,1);
                    close(fdout);
                }
                if (execve (argv[0], argv, environ) < 0)
                {
                    printf ("%s: Command not found.\n", argv[0]) ;
                    exit (0) ;
                }
            }
            else
                printf ("started %d %s", pid, cmdline) ;
        }
        else /* I have pipe */
        { /* parent */
            pipe(pfds);
            pid = fork ();
            if (pid == 0)
            {
                /* child 1 runs command 1 */
                if (*inputfilename)
                {
                    fdin = open(inputfilename,O_RDONLY);
                    dup2(fdin,0);
                    close(fdin);
                }
                dup2(pfds[1],1);   /* make stdout same as pfds[1] */
                close(pfds[0]); /* we don't need this */
                if (execve (argv[0], argv, environ) < 0)
                {
                    printf ("%s: Command not found.\n", argv[0]) ;
                    exit (0) ;
                }
            }
            else
            { /* parent */
                printf ("started %6d: ", pid) ;
                for (i = 0; argv[i]; i++) printf(" %s",argv[i]); printf("\n");
                pid2 = fork ();
                if (pid2 == 0)
                {
                    /* child 2 runs command 2 */
                    if (*outputfilename)
                    {
                        fdout = open(outputfilename,O_CREAT|O_TRUNC|O_RDWR,S_IRUSR|S_IWUSR);
                        dup2(fdout,1);
                        close(fdout);
                    }
                    dup2(pfds[0],0);   /* make stdin same as pfds[0] */
                    close(pfds[1]); /* we don't need this */
                    if (execve (argvtemp[0], argvtemp, environ) < 0)
                    { printf ("%s: Command not found.\n", argvtemp[0]) ; exit (0) ; }
                }
                else
                { /* parent */
                    printf ("started %6d: ", pid2) ;
                    for (i = 0; argvtemp[i]; i++) printf(" %s",argvtemp[i]);printf("\n");
                    close(pfds[0]);
                    close(pfds[1]);
                }
            }
        }
        
        /* parent waits for foreground job to terminate */
        if (!bg)
        {
            int status;
            if (waitpid (pid, &status, 0) < 0)
                unix_error ("waitfg: waitpid error") ;
            printf ("%d exited\n", pid) ;
            if (HavePipe)
            {
                if (waitpid (pid2, &status, 0) < 0)
                    unix_error ("waitfg: waitpid error") ;
                printf ("%d exited\n", pid2) ;
            }
        }
        else
        {
            printf ("%d %s", pid, cmdline) ;
            if (HavePipe) printf ("%d %s", pid2, cmdline) ;
        }
    }
    return;
}

/* if first arg is a builtin command,  run it and return true */
int builtin_command (char * * argv) 
{
    if (!strcmp (argv[0], "quit") ) /* quit command */
        exit (0) ;
    if (!strcmp (argv[0], "&") ) /* ignore singleton & */
        return 1;
    return 0;
    /* not a builtin command */
}

/* parseline  -  parse the command line and build the argv array */
int parseline (char * buf, char * * argv) 
{
    char * delim;  /* points to first space delimiter */
    int argc;  /* number of args */
    int bg;  /* background job? */
    
    buf[strlen (buf) - 1] = ' ';  /* replace trailing '\n' with space */
    while ( * buf && ( * buf == ' ') ) /* ignore leading spaces */
        buf ++ ;
    
    /* build the argv list */
    argc = 0;
    while ( (delim = strchr (buf, ' ') ) )
    {
        argv[argc ++ ] = buf;
        * delim = '\0';
        buf = delim + 1;
        while ( * buf && ( * buf == ' ') ) /* ignore spaces */
            buf ++ ;
    }
    argv[argc] = NULL;
    
    if (argc == 0) /* ignore blank line */
        return 1;
    
    /* should the job run in the background? */
    if ( (bg = ( * argv[argc - 1] == '&') ) != 0)
        argv[ -- argc] = NULL;
    
    return bg;
}
