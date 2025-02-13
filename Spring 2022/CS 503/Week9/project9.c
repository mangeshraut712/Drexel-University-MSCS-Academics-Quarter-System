#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <pthread.h>


typedef struct Client Client;

struct Client{
	int sock;
	char name[16];
};

void *recieve(void*);

int main(int argc, char *argv[]){

	int sock, connection, n;
	char buf[1024];
	struct sockaddr_in server;
	struct hostent *srv;
	pthread_t threcieve;
	Client *data;
	sock = socket(AF_INET, SOCK_STREAM, 0);
	server.sin_family = AF_INET;
	server.sin_port = htons(2020);
	server.sin_addr.s_addr = inet_addr("10.246.251.13");
	srv = gethostbyname("tux3");

	connection = connect(sock, (struct sockaddr *)&server, sizeof(struct sockaddr_in));

	if(connection <  0){
		perror("connection:\n");
		exit(1);
	}
	data->sock = sock;
	pthread_create(&threcieve, NULL, recieve, data);
	if(pthread_create(&threcieve, NULL, recieve, data)){
                perror("pthread_create");
                exit(1);
        }
	strcpy(data->name, argv[1]);
	send(sock, argv[1], sizeof(argv[1]), 0);
	while(fgets(buf, 1024, stdin) != NULL){
		if(send(sock, buf, strlen(buf), 0) <= 0){
			printf("Not sending\n");
		}

	}
	close(sock);
	pthread_exit(NULL);

}

void *recieve(void *p){
	Client *data = p;
	int n;
	char buf[1024];

	while(1){
		recv(data ->sock, buf, strlen(buf), 0);
		if(n <=0){
			printf("Error\n");
			pthread_exit(NULL);
		}
		printf("%s>", data -> name);
		printf("%s\n", buf);
	}
}
