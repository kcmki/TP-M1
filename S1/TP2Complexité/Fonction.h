#include "stdlib.h"
#include "stdio.h"
#include "time.h"
#include "string.h"
#define NELEMS(x)  (sizeof(x) / sizeof((x)[0]))

typedef struct li{
    int nombre;
    struct li *suivant;
    struct li *precedent;
}*list;

//util functions

void swap(long tab[],int min, int i);
void printtab(int tab[],int size);
void inverser_tab(long tab[],int first,int last);
//list manip

//algorithms

void selection(long tab[],int size);
void insertion(long tab[],int size);
void bulle(long tab[], int nbr_element);
void rapide(long tab[],int first, int last);
void fusion(long tab[],int debut,int fin,long temp[]);
void tas(long tab[],int size);

// algo pour calcule nombre de test 

long long longselection(long tab[],int size);
long long longinsertion(long tab[],int size);
long long longbulle(long tab[], int nbr_element);
void longrapide(long tab[],int first, int last,long long *calc);
long long longfusion(long tab[],int debut,int fin,long temp[]);
long long longtas(long tab[],int size);
