#include "stdio.h"
#include "stdlib.h"
#include "time.h"


typedef struct{
    int** tours;
    int indice[3],size;
}*Hanoi,hanoi;

Hanoi initTour(int size);
void printHanoi(Hanoi T);
void HanoiREC(Hanoi T,int gros,int petit,int t1,int t2);