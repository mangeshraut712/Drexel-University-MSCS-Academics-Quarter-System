#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <dirent.h>
#include <string.h>

#ifndef MAX_BUF
#define MAX_BUF 200
#endif

int main(int argc, char *argv[])
{
  DIR *d;
  struct dirent *dir;
  FILE *fptr;
  char filename[100], c;
  char path[MAX_BUF];

  getcwd(path, MAX_BUF);
  printf("directory path: %s\n", path);

  if ( argc != 2 )
   {
       printf("usage: %s filename\n", argv[0] );
   }
   else
   {
       FILE *file = fopen( argv[1], "r" );

       if ( file == 0 )
       {
           printf( "Could not open file\n" );
       }
       else
       {
           int x;

           while  ( ( x = fgetc( file ) ) != EOF )
           {
               printf( "%c", x );
           }
           fclose( file );
       }
   }

   d = opendir(".");
   if (d)
   {

       while ((dir = readdir (d)) != NULL)
   {
       const size_t len = strlen(dir->d_name);
       if (len > 4                     &&
           dir->d_name[len - 4] == '.' &&
           dir->d_name[len - 3] == 't' &&
           dir->d_name[len - 2] == 'x' &&
           dir->d_name[len - 1] == 't')
       {
           printf ("%s\n", dir->d_name);
       }
   }
       closedir(d);
   }

   printf("Enter the filename to open \n");
   scanf("%s", filename);

   // Open file
   fptr = fopen(filename, "r");
   if (fptr == NULL)
   {
       printf("Cannot open file \n");
       exit(0);
   }

   // Read contents from file
   c = fgetc(fptr);
   while (c != EOF)
   {
       printf ("%c", c);
       c = fgetc(fptr);
   }

   fclose(fptr);
  return 0;
}
