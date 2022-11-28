#include "Hanoi.h"
Hanoi initTour(int size){

    Hanoi tour = malloc(sizeof(hanoi));

    tour->i1 = -1;
    tour->i2 = -1;
    tour->i3 = -1;
    tour->size = size;
    
    tour->tours = malloc(sizeof(int*)*3);
    for (int i = 0; i < 3; i++)
    {   
        tour->tours[i] = malloc(sizeof(int)*size);
        if(tour->tours[i] == NULL){
            printf("Allocation impossible tour %d",i);
            exit(-1);
        } 
    }
    for (int i = size; i > 0; i--)
    {   
        tour->i1++;
        tour->tours[0][tour->i1] = i;
        tour->tours[1][tour->i1] = 0;
        tour->tours[2][tour->i1] = 0;
    }
    return tour;
}

void printHanoi(Hanoi T){
    printf("\nTours de Hanoi\n");
    for (int i = T->size - 1 ; i >= 0; i--)
    {
        printf("[%d] [%d] [%d]\n",T->tours[0][i],T->tours[1][i],T->tours[2][i]);
    }
    printf("Les indices des tours:\n");
    printf("[%d] [%d] [%d]\n",T->i1,T->i2,T->i3);
}