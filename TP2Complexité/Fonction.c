#include "Fonction.h"

void swap(long tab[],int min, int i){
    int temp = tab[min];
    tab[min] = tab[i];
    tab[i] = temp;
}
void inverser_tab(long tab[],int first,int last){
	int tmp;
	while (first < last){
		tmp = tab[first];
		tab[first] = tab[last];
		tab[last] = tmp;
		first++;
		last--;
	}
}
void printtab(int tab[],int size){
    printf("Tableau : ");
    for (int i = 0; i < size; i++)
    {
        printf("[%d]",tab[i]);
    }
    printf("\n");
}

//algorithme de tri
void selection(long tab[],int size){
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

void insertion(long tab[],int size){
    int findPlace(long tab[],int i){
            if(tab[0] >= tab[i]) return 0;
            for (int j = i-1; j > 0 ; j--)
            {   
                if(tab[j] <= tab[i] && tab[j+1] >= tab[i]){
                    return j+1;
                }
            }
        }

    void placedecale(long tab[],int i,int place){
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

void bulle(long tab[], int nbr_element){
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

void rapide(long tab[],int first, int last){
            int rapide_permutation(long tab[],int first,int last){
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

            int rapide_pivot(int first,int last){
                srand(time(0));
                int num = (rand() % (last - first + 1) + first);
                return num;
            }
	int i,p,tmp;

	if(last > first){
		p = rapide_pivot(first,last);

		tmp = tab[p];
		tab[p] = tab[last];
		tab[last] = tmp;

		i = rapide_permutation(tab,first,last);

		rapide(tab,first,i-1);
		rapide(tab,i+1,last);
	}
}

void fusion(long tab[],int debut,int fin,long temp[]){
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

void tas(long tab[],int size){
    void tamisser(long tab[],int index,int limite){

        int k = index;
        int j = 2*k;
        while(j <= limite){
            if(j<limite){
                if(tab[j] < tab[j+1]){
                    j++;
                }
            }
            if(tab[k] < tab[j]){
                swap(tab,k,j);
                k = j;
                j = 2*k;
            }else{
                j = limite+1;
            }
        }
    }

    int limite = size;
    for (int i = size/2; i >= 0; i--)
    {
        tamisser(tab, i, size);
    }
       
   for (int i = size; i >= 1 ; i--)
   {
        swap(tab,i,0);
        tamisser(tab,0,i-1);
   }

}


long long longselection(long tab[],int size){
    int min = 0;
    long long calc = 0;
    for (int i = 0; i < size - 1 ; i++)
    {
        min = i;
        for (int j = i; j < size; j++)
        {   calc++;
            if(tab[j] < tab[min]){
                min = j;
            }
        }
        if(min != i ){
            swap(tab,min,i);
        }
    }
    return calc;
}

long long longinsertion(long tab[],int size){
    long long calc = 0;
    for (int i = 1; i < size; i++)
    {  
        long x = tab[i];
        int j = i;
        while (j >= 0 && tab[j-1] > x )
        {
            calc= calc + 1;
            tab[j] = tab[j-1];
            j--;
        }
        tab[j] = x;
    }

    return calc;
}

long long longbulle(long tab[], int nbr_element){
	int tmp;
	int i = nbr_element - 1;
    long long calc = 0;
	while(i>=1){
		for(int j=0;j<=i-1;j++){
			if(tab[j+1]<tab[j]){
                calc++;
				tmp = tab[j];
				tab[j] = tab[j+1];
				tab[j+1] = tmp;
			}
		}
		i-=1;
	}
    return calc;
}

void longrapide(long tab[],int first, int last,long long *calc){
            int rapide_permutation(long tab[],int first,int last,long long *calc){
                int i = first - 1;
                int j = last;
                int tmp;
                while(1){
                    do{
                        i += 1;
                        (*calc)++;
                    }while(tab[i] < tab[last]);

                    do{
                        j -= 1;
                        (*calc)++;
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

            int rapide_pivot(int first,int last){
                srand(time(0));
                int num = (rand() % (last - first + 1) + first);
                return num;
            }
	int i,p,tmp;

	if(last > first){
		p = rapide_pivot(first,last);

		tmp = tab[p];
		tab[p] = tab[last];
		tab[last] = tmp;

		i = rapide_permutation(tab,first,last,calc);

		longrapide(tab,first,i-1,calc);
		longrapide(tab,i+1,last,calc);
	}
}

long long longfusion(long tab[],int debut,int fin,long temp[]){
    if(fin <= debut){return 0;}
    
    int millieu = ( debut + fin ) / 2;
    long long calc = 0;
    calc += longfusion(tab,debut,millieu,temp);
    calc += longfusion(tab,millieu+1,fin,temp);

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
            calc++;
            td++;
        }else{
            temp[i] = tab[tg];
            calc++;
            tg++;
        }
    }
    for (int i = debut; i <= fin; i++)
    {
        tab[i] = temp[i];
    }
    return calc;
}

long long longtas(long tab[],int size){
    long long tamisser(long tab[],int index,int limite){

        int k = index;
        int j = 2*k;
        long long calc = 0;
        while(j <= limite){
            if(j<limite){
                if(tab[j] < tab[j+1]){
                    j++;
                    calc++;
                }
            }
            if(tab[k] < tab[j]){
                swap(tab,k,j);
                k = j;
                j = 2*k;
            }else{
                j = limite+1;
            }
            calc++;
        }
        return calc;
    }

    int limite = size;
    long long calc = 0;
    for (int i = size/2; i >= 0; i--)
    {
        calc += tamisser(tab, i, size);
    }
       
   for (int i = size; i >= 1 ; i--)
   {
        swap(tab,i,0);
        calc += tamisser(tab,0,i-1);
   }
    return calc;
}

