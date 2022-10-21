#include "stdlib.h"
#include "stdio.h"

#define NELEMS(x)  (sizeof(x) / sizeof((x)[0]))

//util functions

void swap(int tab[],int min, int i);
void printtab(int tab[],int size);

//algorithms
void selection(int tab[],int size);
void insertion(int tab[],int size);
