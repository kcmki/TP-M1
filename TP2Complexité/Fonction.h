#include "stdlib.h"
#include "stdio.h"

#define NELEMS(x)  (sizeof(x) / sizeof((x)[0]))

typedef struct li{
    int nombre;
    struct li *suivant;
    struct li *precedent;
}*list;

//util functions

void swap(int tab[],int min, int i);
void printtab(int tab[],int size);
//list manip


//algorithms
void selection(int tab[],int size);
void insertion(int tab[],int size);
void fusion(int tab[],int debut,int fin,int temp[]);
void tas(int tab[],int size);

