#include <stdio.h>
#include <stdlib.h>

typedef struct elem{
    int nombre;
    struct elem* svt;
}elem , *list;

typedef struct noeud{
    int noeud;
    list voisin;
}*Arbre;


list newElem(int nb);
void printlist(list l);
void ajoutsuivant(list* l,int nb);
void ajouttete(list* l,int nb);