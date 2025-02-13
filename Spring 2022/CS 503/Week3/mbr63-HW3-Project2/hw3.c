#include <stdio.h>
int threeDigitNumbers(int lastNumber)
{
int sum=0;
int number = lastNumber;
while(number !=0){
int lastDigit = number %10;
sum += (lastDigit *lastDigit* lastDigit);
number /=10;
}
if(sum == lastNumber){
return 1;
}
return 0;
}
int main()
{
for(int i=100;i<999;i++)
{
if(threeDigitNumbers(i)){
printf("%d\n",i);
}
}
return 0;
}
