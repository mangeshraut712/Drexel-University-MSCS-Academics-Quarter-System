/* $begin shellmain */
#include "csapp.h"
#include <unistd.h>

#define MAXARGS   128

/* function prototypes */
void eval(char*cmdline);
int parseline(char *buf, char **argv);
int builtin_command(char **argv);

int main()
{
    char cmdline[MAXLINE]; /* command line */
    
    while (1) {
        /* read */
        printf(" > ");
        fgets(cmdline, MAXLINE, stdin);
        if (feof(stdin))
            exit(0);
        
        /* evaluate */
        eval(cmdline);
    }
}
/* $end shellmain */

/* $begin eval */
/* eval - evaluate a command line */
void eval(char *cmdline)
{
    char *argv[MAXARGS]; /* argv for execve() */
    char *argv2[MAXARGS]; /* argv2 for execve() */
    char buf[MAXLINE];   /* holds modified command line */
    int bg;              /* should the job run in bg or fg? */
    pid_t pid, chpid;           /* process id */
    int fd0,fd1,i,j,temp, p;
    char input[100];
    char output[100];
    int fdpipe[2];
    int pipe_descriptor_1 = fdpipe[1];
    int pipe_descriptor_0 = fdpipe[0];
    
    strcpy(buf, cmdline);
    bg = parseline(buf, argv);
    if (argv[0] == NULL)
        return;   /* ignore empty lines */
    
    // finds where '<' or '>' occurs and make that argv[i] = NULL , to ensure that command wont't read that
    input[0] = output[0] = 0;
    for(i=0;argv[i];i++)
    {
        if(*argv[i] == '<')
        {
            strcpy(input,argv[i+1]);
        }
        
        if(*argv[i] == '>')
        {
            strcpy(output,argv[i+1]);
        }
        
    }
    
    for(i=0;argv[i];i++)
    {
        if(*argv[i] == '<')
        {
            *argv[i] = *argv[i+1] = 0;
        }
        
        if(*argv[i] == '>')
        {
            *argv[i] = *argv[i+1] = 0;
        }
    }
    temp = 0;
    for(i=0;argv[i];i++)
    {
        if(*argv[i]!=0)
            argv2[temp++] = argv[i];
    }
    argv2[temp] = NULL;
    
    for(i=0;argv2[i];i++)
        argv[i] = argv2[i];
    argv[i] = NULL;
    
    p = 0;
    for(temp=0;argv[temp];temp++)
        if(*argv[temp] == '|')
        {
            argv[temp] = NULL;
            p = 1;
            break;
        }
    if(p)
    {
        temp++;
        for(j=0;argv[temp];temp++)
        {
            argv2[j++] = argv[temp];
        }
        argv2[j] = NULL;
    }
    else
    {
        argv2[0] = NULL;
    }
    
    if (!builtin_command(argv))
    {
        if(!p)
        {
            pid= fork();
            //if '<' char was found in string inputted by user
            if(pid == 0)
            {
                if(*input)
                {
                    // fdo is file-descriptor
                    fd0 = open(input, O_RDONLY);
                    // dup2() copies content of fdo in input of preceeding file
                    dup2(fd0, 0); // STDIN_FILENO here can be replaced by 0
                    close(fd0); // necessary
                }
                
                //if '>' char was found in string inputted by user
                if (*output)
                {
                    fd1 = open(output , O_CREAT|O_TRUNC|O_RDWR);
                    dup2(fd1, 1); // 1 here can be replaced by STDOUT_FILENO
                    close(fd1);
                }
                if(execve(argv[0],argv,environ) < 0){
                    printf("%s: Command not found.\n",argv[0]);
                    exit(0);
                }
            }
        }
        else
        {
            pipe(fdpipe);
            pid = fork();
            if(pid == 0)
            {
                if(*input)
                {
                    // fdo is file-descriptor
                    fd0 = open(input, O_RDONLY);
                    // dup2() copies content of fdo in input of preceeding file
                    dup2(fd0, 0); // STDIN_FILENO here can be replaced by 0
                    close(fd0); // necessary
                }
                dup2(pipe_descriptor_1,1);
                close(fdpipe[0]);
                if (execve (argv[0], argv, environ) < 0)
                {
                    printf ("%s: Command not found.\n", argv[0]) ;
                    exit (0) ;
                }
            }
            else
            {
                printf ("started %6d: ", pid) ;
                for(temp = 0;argv[temp];temp++)
                    printf(" %s" , argv[temp]);
                printf("\n");
                pid = fork();
                if(chpid == 0)
                {
                    if (*output)
                    {
                        fd1 = open(output , O_CREAT|O_TRUNC|O_RDWR);
                        dup2(fd1, 1); // 1 here can be replaced by STDOUT_FILENO
                        close(fd1);
                    }
                    dup2(pipe_descriptor_0,0);
                    close(fdpipe[1]);
                    if(execve(argv[0],argv,environ) < 0)
                    {
                        printf("%s: Command not found.\n",argv[0]);
                        exit(0);
                    }
                }
                else
                {
                    printf ("started %6d: ", chpid) ;
                    for(temp = 0; argv2[i];temp++)
                        printf(" %s" , argv[temp]);
                    printf("\n");
                    close(pipe_descriptor_0);
                    close(pipe_descriptor_1);
                }
            }
        }
        
        /* parent waits for foreground job to terminate */
        if(!bg)
        {
            int status;
            if(waitpid(pid, &status, 0) < 0)
                unix_error("waitfg: waitpid error");
            if(p)
            {
                if(waitpid(chpid,&status,0) < 0)
                    unix_error("waitfg: waitpid error");
            }
        }
        else
        {
            printf("%d %s", pid, cmdline);
            if (p)
                printf ("%d %s", chpid, cmdline) ;
        }
    }
    return;
}


/* if first arg is a builtin command, run it and return true */
int builtin_command(char **argv)
{
    if (!strcmp(argv[0], "quit")) /* quit command */
        exit(0);
    if (!strcmp(argv[0], "&"))    /* ignore singleton & */
        return 1;
    return 0;                     /* not a builtin command */
}
/* $end eval */

/* $begin parseline */
/* parseline - parse the command line and build the argv array */
int parseline(char *buf, char **argv)
{
    char *delim;         /* points to first space delimiter */
    int argc;            /* number of args */
    int bg;              /* background job? */
    
    buf[strlen(buf)-1] = ' ';  /* replace trailing '\n' with space */
    while (*buf && (*buf == ' ')) /* ignore leading spaces */
        buf++;
    
    /* build the argv list */
    argc = 0;
    while ((delim = strchr(buf, ' '))) {
        argv[argc++] = buf;
        *delim = '\0';
        buf = delim + 1;
        while (*buf && (*buf == ' ')) /* ignore spaces */
            buf++;
    }
    argv[argc] = NULL;
    
    if (argc == 0)  /* ignore blank line */
        return 1;
    
    /* should the job run in the background? */
    if ((bg = (*argv[argc-1] == '&')) != 0)
        argv[--argc] = NULL;
    
    return bg;
}
/* $end parseline */
