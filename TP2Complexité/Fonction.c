#include "Fonction.h"

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

void triFusionList(int tab[],int size){
    list diviser(int tab[],int debut,int size){
        if(size == 0)   return NULL
        if(size == 1)   return newList(tab[debut]);

        return fusion(diviser(tab,debut,(size/2 + size % 2)),diviser(tab,debut + size/2,size/2));
        

    }

    list fusion(list L1,list L2){
        if(L1 == NULL){
            return L2;
        }else if(L2 == NULL){
            return L1;
        }else if( L)
    }

    list Li = diviser(tab);
}*/


void fusion(int tab[],int debut,int fin,int temp[]){
    if(fin <= debut){return;}
    
    int millieu = ( debut + fin ) / 2;

    fusion(tab,debut,millieu,temp);
    fusion(tab,millieu+1,fin,temp);

    int tg = debut;
    int td = millieu+1;
    for (int i = debut; i <= fin; i++)
    {   
        if(tg > millieu){
            temp[i] = tab[td];
            td++;
        }else if(td > fin){
            temp[i] = tab[tg];
            tg++;
        }else if(tab[tg] > tab[td]){
            temp[i] = tab[td];
            td++;
        }else{
            temp[i] = tab[tg];
            tg++;
        }
    }
    for (int i = debut; i <= fin; i++)
    {
        tab[i] = temp[i];
    }

}


void tas(int tab[],int size){
    void tamisser(int tab[],int index,int limite){

        int filsgauche = index*2 + 1;
        int filsdroit = filsgauche+1;

        if(filsgauche < limite) tamisser(tab,filsgauche,limite);
        if(filsdroit < limite) tamisser(tab,filsdroit,limite);

        if(filsgauche <= limite){
            if(tab[filsgauche] > tab[index]){
                int temp = tab[index];
                tab[index] = tab[filsgauche];
                tab[filsgauche] = temp;
            }
        }
        if(filsdroit <= limite){
            if(tab[filsdroit] > tab[index]){
                int temp = tab[index];
                tab[index] = tab[filsdroit];
                tab[filsdroit] = temp;
            }
        }
    }

    int limite = size;
    for (int i = 0; i < size; i++)
    {
        tamisser(tab,0,limite);

        int temp = tab[0];
        tab[0]  = tab[limite];
        tab[limite] = temp;

        limite--;
    }

}



#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void tri_selection(int tab[], int nbr_element){
	int tmp;
	for(int i=0;i<nbr_element-1;i++){
		for(int j=i+1;j<nbr_element;j++){
			if(tab[j] < tab[i]){
				tmp = tab[i];
				tab[i] = tab[j];
				tab[j] = tmp;
			}
		}
	}
}

void tri_insertion(int tab[], int nbr_element){
	int element,j;
	for(int i=0;i<nbr_element;i++){
		element = tab[i];
		j = i;
		while(j>0 && tab[j-1]>element){
			tab[j] = tab[j-1];
			j -= 1;
		}
		tab[j] = element;
	}
}

void tri_bulle(int tab[], int nbr_element){
	int tmp;
	int i = nbr_element - 1;
	while(i>=1){
		for(int j=0;j<=i-1;j++){
			if(tab[j+1]<tab[j]){
				tmp = tab[j];
				tab[j] = tab[j+1];
				tab[j+1] = tmp;
			}
		}
		i-=1;
	}
}

int tri_rapide_permutation(int tab[],int first,int last){
	int i = first - 1;
	int j = last;
	int tmp;

	while(1){
		do{
			i += 1;
		}while(tab[i] < tab[last]);

		do{
			j -= 1;
		}while((j > first) && (tab[last] < tab[j]));

		if(i >= j)
			break;

		tmp = tab[i];
		tab[i] = tab[j];
		tab[j] = tmp;
	}

	tmp = tab[i];
	tab[i] = tab[last];
	tab[last] = tmp;

	return i;
}

int tri_rapide_pivot(int first,int last){
	srand(time(0));
	int num = (rand() % (last - first + 1) + first);
	return num;
}

void tri_rapide(int tab[],int first, int last){
	int i,p,tmp;

	if(last > first){
		p = tri_rapide_pivot(first,last);

		tmp = tab[p];
		tab[p] = tab[last];
		tab[last] = tmp;

		i = tri_rapide_permutation(tab,first,last);

		tri_rapide(tab,first,i-1);
		tri_rapide(tab,i+1,last);
	}
}
