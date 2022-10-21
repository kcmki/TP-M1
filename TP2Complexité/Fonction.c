#include "fonction.h"

void swap(int tab[],int min, int i){
    int temp = tab[min];
    tab[min] = tab[i];
    tab[i] = temp;
}

void printtab(int tab[],int size){
    printf("Tableau : ");
    for (int i = 0; i < size; i++)
    {
        printf("[%d]",tab[i]);
    }
    printf("\n");
}

void selection(int tab[],int size){
    int min = 0;
    for (int i = 0; i < size - 1 ; i++)
    {
        min = i;
        for (int j = i; j < size; j++)
        {
            if(tab[j] < tab[min]){
                min = j;
            }
        }
        if(min != i ){
            swap(tab,min,i);
        }
    }
}

void insertion(int tab[],int size){
    int findPlace(int tab[],int i){
            if(tab[0] > tab[i]) return 0;
            for (int j = 0; j < i ; j++)
            {   
                if(tab[j] <= tab[i] && tab[j+1] > tab[i]){
                    return j+1;
                }
            }
        }

    void placedecale(int tab[],int i,int place){
        int temp2;
        int temp = tab[i];

        for (int j = place; j <= i; j++)
        {   
            temp2 = tab[j];
            tab[j] = temp;
            temp = temp2;
        }
    }

    for (int i = 1; i < size; i++)
    {  
        placedecale(tab,i,findPlace(tab,i));
    }
    
}


/*
void bulle(int tab[]){

}

void rapide(int tab[]){

}

void fusion(int tab[]){

}

void tas(int tab[]){

} */