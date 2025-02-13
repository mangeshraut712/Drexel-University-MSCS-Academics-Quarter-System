#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>

void runchild(int);
void runparent(int);

int
main(int argc, char *argv[])
{
	int chpid;

	if(argc != 2) {
		fprintf(stderr, "Usage: chat n\n");
		exit(1);
	}

	chpid = fork();
	if(chpid < 0) {
		perror("fork");
		exit(2);
	}
	if(chpid == 0) {
		runchild(atoi(argv[1]));
	}
	else {
		runparent(atoi(argv[1]));
	}
	exit(0);
}

void
runcommand(char *buf)
{
	int i, status;
	char *tokens[32];

	if(fork() > 0) {
		wait(&status);
	}
	else {
		tokens[0] = strtok(buf + 1, " \t\n");
		for(i = 1; i < 32; i++) {
			tokens[i] = strtok(NULL, " \t\n");
			if(tokens[i] == NULL)
				break;
		}
		execvp(tokens[0], tokens);
		perror("exec");
		exit(4);
	}
}

void
dooutredir(char *buf, int fdw)
{
	int i, status;
	char *tokens[32];

	if(fork() > 0) {
		wait(&status);
	}
	else {
		tokens[0] = strtok(buf + 1, " \t\n");
		for(i = 1; i < 32; i++) {
			tokens[i] = strtok(NULL, " \t\n");
			if(tokens[i] == NULL)
				break;
		}
		close(1);
		dup(fdw);
		execvp(tokens[0], tokens);
		perror("exec");
		exit(4);
	}
}

void
runparent(int which)
{
	int fdw, n;
	char buf[128];

	if(which == 1) {
		fdw = open("chatpipe1", O_WRONLY);
	}
	else {
		fdw = open("chatpipe2", O_WRONLY);
	}
	if(fdw < 0) {
		perror("parent open");
		exit(3);
	}

	while((n = read(0, buf, 127)) > 0) {
		buf[n] = '\0';
		switch(buf[0]) {
		case '!':
			runcommand(buf);
			break;
		case '>':
			dooutredir(buf, fdw);
			break;
		default:
			write(fdw, buf, n);
			break;
		}
	}
	close(fdw);
}

void
runchild(int which)
{
	int fdr, n;
	char buf[128];

	if(which == 1) {
		fdr = open("chatpipe2", O_RDONLY);
	}
	else {
		fdr = open("chatpipe1", O_RDONLY);
	}
	if(fdr < 0) {
		perror("child open");
		exit(3);
	}

	while((n = read(fdr, buf, 128)) > 0) {
		write(1, ">>> ", 4);
		write(1, buf, n);
	}

	close(fdr);
}
