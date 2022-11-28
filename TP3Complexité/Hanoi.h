#include "stdio.h"
#include "stdlib.h"
#include "time.h"


typedef struct{
    int** tours;
    int i1,i2,i3,size;
}*Hanoi,hanoi;

Hanoi initTour(int size);
void printHanoi(Hanoi T);