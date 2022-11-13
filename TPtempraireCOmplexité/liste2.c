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

list range_append(int n){
    list tete = newElem(1);
    list last = tete;
    list new = NULL;
    for (int i = 2; i <= n; i++)
    {
        new = newElem(i);
        last->svt = new;
        last = last->svt;
    }
    return tete;
}

list range_insert(int n){
    list tete = NULL,temp = NULL;
    for (int i = n; i > 0; i--)
    {   
        temp = tete;
        tete = newElem(i);
        tete->svt = temp;
    }
    return tete;
}

int main(int argc,char** argsv){

        if(argc < 3){
            printf("Too few arguments");
            return 0;
        }
        if(argc > 3){
            printf("Too many arguments");
            return 0;
        }
        int choix = atoi(argsv[1]);
        int nombre = atoi(argsv[2]);
        if(nombre == 0 ){
            printf("Erreur arguments nombre trop petit");
            return 0;
        }
        if(choix != 1 && choix != 2){
            printf("Erreur arguments choix incorrect 1 pour range_append ou 2 pour range_insert");
            return 0;
        }

    list L = NULL;
    clock_t Tdebut,Tfin;
    Tdebut = clock();
    switch (choix)
    {
        case 1:
            L = range_append(nombre);
            break;
        case 2:
            L = range_insert(nombre);
            break;
    }
    Tfin = clock();

    printf("temp d'execution algo %d - taille %d : %f",choix,nombre,(float)(Tfin - Tdebut) / CLOCKS_PER_SEC);
}