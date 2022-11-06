#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct elem{
    int nombre;
    struct elem* svt;
}elem , *list;

list newElem(int nb){
    list new = malloc(sizeof(elem));
    if(new == NULL){
        printf("Erreur allocation memoire");
        exit(-1);
        return NULL;
    }
    new->nombre = nb;
    new->svt = NULL;
    return new;
}

void printlist(list l){
    list temp = l;
    printf("List : ");
    while(temp != NULL){
        printf("%d->",temp->nombre);
        temp = temp->svt;
    }
    printf("\n");
}

void ajoutsuivant(list* l,int nb){
    list temp = *l;
    if(temp == NULL){
        *l = newElem(nb);
        return;
    }
    while(temp->svt != NULL){
        temp = temp->svt;
    }

    temp->svt = newElem(nb);
}

int maxList(list* l){
    list temp = *l;
    if(temp == NULL){
        printf("Errur liste vide");
        exit(-1);
    }
    int max = temp->nombre;
    temp = temp->svt;
    while(temp->svt != NULL){
        if(max < temp->nombre){
            max = temp->nombre;
        }
        temp = temp->svt;
    }
    return max;
}
void printtab(float tab[],int size){
    printf("Tableau : ");
    for (int i = 0; i < size; i++)
    {
        printf(" size,time : %d => [%f]\n",1000*(i+1),tab[i]);
    }
    printf("\n");
}
#define SIZE 100
#define NBTRY 10
int main(int argc,char** argz){
    
    list L = NULL;
    list tab[SIZE] = {NULL};
    srand(time(0));
    for (int i = 0; i < SIZE; i++)
    {   
        for (int j = 0; j < 1000*(i+1); j++)
        {
            ajoutsuivant(&(tab[i]),rand());
        }
    }
    float temp[SIZE] = {0};
    clock_t Tdebut,Tfin;
    int max = 0;
    for (int i = 0; i < SIZE; i++)
    {   
        for (int j = 0; j < NBTRY; j++)
            {
                Tdebut = clock();
                max =maxList(&(tab[i]));
                Tfin = clock();
                temp[i] += (float)(Tfin - Tdebut) / CLOCKS_PER_SEC;
                
            }
        temp[i] = temp[i] / NBTRY;
    }
    printtab(temp,SIZE);

    return 0;
}