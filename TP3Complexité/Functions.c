#include "Hanoi.h"
Hanoi initTour(int size){

    Hanoi tour = malloc(sizeof(hanoi));

    tour->size = size;
    
    tour->tours = malloc(sizeof(int*)*3);
    if(tour->tours  == NULL){
        printf("Erreur allocation dynamique");
        exit(-1);
    }
    for (int i = 0; i < 3; i++)
    {   
        //init indice
        tour->indice[i] = -1;
        //allocation tableau de pointeur
        tour->tours[i] = malloc(sizeof(int)*size);
        //allocation pile
        if(tour->tours[i] == NULL){
            printf("Allocation impossible tour %d",i);
            exit(-1);
        } 
    }

    for (int i = size; i > 0; i--)
    {   
        (tour->indice[0])++;
        tour->tours[0][tour->indice[0]] = i;
        tour->tours[1][tour->indice[0]] = 0;
        tour->tours[2][tour->indice[0]] = 0;
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
    printf("[%d] [%d] [%d]\n",T->indice[0],T->indice[1],T->indice[2]);
}

void deplacer(Hanoi  T,int t1,int t2){

    int adep = T->tours[t1][T->indice[t1]];

               T->tours[t1][T->indice[t1]] = 0;
               (T->indice[t1])--;
            
               (T->indice[t2])++;
               T->tours[t2][T->indice[t2]] = adep;
    
    printHanoi(T);
}

void HanoiREC(Hanoi T,int gros,int petit,int t1,int t2){   
    int mid = t1+t2;

    switch(mid){
        case 1:
            mid = 2;
            break;
        case 2:
            mid = 1;
            break;
        case 3:
            mid = 0;
            break;
    }
    if(gros==petit){  deplacer(T, t1, t2);}
    else if(gros > petit){
                HanoiREC(T,gros-1,petit, t1, mid);
                deplacer(T,t1, t2);
                HanoiREC(T,gros-1,petit, mid, t2);
    } 

    
}
