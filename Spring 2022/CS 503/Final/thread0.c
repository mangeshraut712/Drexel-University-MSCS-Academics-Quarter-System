#include "csapp.h"
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define NTHR 16

void * do_work (void * arg);

struct AR
{
    double *array;
    int size;
    double *sum;
};

int main (int argc, char ** argv) 
{
    double array[1024];
    double sum;
    pthread_t id[4];
    struct AR * arg;
    void  *return_value;
    int i;
    int no_of_threads = NTHR;
    printf("Creating %d number of threads...\n", no_of_threads);
    
    for (i = 0; i < 1024; i++) array[i] = i;
    
    arg = (struct AR * ) calloc (1, sizeof (struct AR) ) ;
    arg->array = array;
    arg->size = 1024;
    arg->sum = &sum;
    
    for(i = 0; i <= 4; i++)
    {
        pthread_create(&id[i], NULL, do_work, arg);
    }
    for(i = 0; i <= 4; i++)
    {
        pthread_join(id[i], &return_value);
        
    }
    printf("sum = %10.1lf\n",sum);
}

void * do_work (void * arg)
{
    struct AR * argument;
    int i, size;
    double * array;
    double * sum;
    double localsum = 0;
    
    argument = (struct AR * ) arg;
    
    size = argument -> size;
    array = argument -> array;
    sum = argument -> sum;
    
    * sum = 0;
    for (i = 0; i < size; i ++ )
    {
        localsum += array[i];
    }
    *sum += localsum;
    pthread_exit(0);
    return NULL;
}

