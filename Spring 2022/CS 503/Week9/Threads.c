#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <fcntl.h>

struct data_t
{
    double val;
    pthread_t id;
    int index;
};

typedef struct data_t data_t;

void initque();
int enque(data_t * dt);
double proc(char * ar);

void initque(){
}

double proc(char * ar){
    int x=*ar;
    return x;
}

int count;
void *work(void *arg) {
    int *fd=(int *)arg;
    char *c = (char *) calloc(1000, sizeof(char));
    int size=read(*fd,c,1000);
    pthread_mutex_t lock;
    for(int i=0;i<size;i++){
        pthread_mutex_lock(&lock);
        count=count+1;
        pthread_mutex_unlock(&lock);
    }
    return NULL;
}

int main(int argc,char **argv)
{
    if(argc!=3)
    {
        printf("Invalid number of arguments!\n");
        return 0;
    }
    int fd1 = open(argv[1], O_RDONLY);
    int fd2 = open(argv[2], O_RDONLY);
    
    count=0;
    initque();
    
    pthread_t thread1;
    pthread_t thread2;
    //creating two threads
    pthread_create(&thread1, NULL, work, &fd1);
    pthread_create(&thread2, NULL, work, &fd2);
    
    //join two threads
    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);
    
    //print the count value
    printf("count=%d\n",count);
}
