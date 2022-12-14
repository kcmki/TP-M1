#include "graph.h"
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
void ajouttete(list* l,int nb){
    list temp = newElem(nb);
    temp->svt = *l;
    *l = temp;
}