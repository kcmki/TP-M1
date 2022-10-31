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

void ajoutsuivant(list* l,int nb)
{
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

int main(int argc,char** argz){
    
    list L = NULL;
    list tab[100];
    srand(time(0));
    for (int i = 0; i < 100; i++)
    {   
        for (int j = 0; j < 10; j++)
        {
            tab[i] = ajoutsuivant(rand())
        }
    }
    printlist(L);

    printf("Max liste : %d\n",maxList(&L));

    return 0;
}