#include "Hanoi.h"




//initialise le jeu
Hanoi initTour(int size){

    Hanoi tour = malloc(sizeof(hanoi));

    tour->size = size;
    
    tour->tours = malloc(sizeof(int*)*3);
    if(tour->tours  == NULL){
        printf("Erreur allocation dynamique");
        exit(-1);
    }
    for (int i = 0; i < 3; i++)
    {   
        //init indice
        tour->indice[i] = -1;
        //allocation tableau de pointeur
        tour->tours[i] = malloc(sizeof(int)*size);
        //allocation pile
        if(tour->tours[i] == NULL){
            printf("Allocation impossible tour %d",i);
            exit(-1);
        } 
    }

    for (int i = size; i > 0; i--)
    {   
        (tour->indice[0])++;
        tour->tours[0][tour->indice[0]] = i;
        tour->tours[1][tour->indice[0]] = 0;
        tour->tours[2][tour->indice[0]] = 0;
    }
    return tour;
}

//affiche les tours

void printHanoi(Hanoi T){
    printf("\nTours de Hanoi\n");
    for (int i = T->size - 1 ; i >= 0; i--)
    {
        printf("[%d] [%d] [%d]\n",T->tours[0][i],T->tours[1][i],T->tours[2][i]);
    }
    printf("Les indices des tours:\n");
    printf("[%d] [%d] [%d]\n",T->indice[0],T->indice[1],T->indice[2]);
}

//déplace le disque de T1 vers T2

void deplacer(Hanoi  T,int t1,int t2){

    int adep = T->tours[t1][T->indice[t1]];

               T->tours[t1][T->indice[t1]] = 0;
               (T->indice[t1])--;
            
               (T->indice[t2])++;
               T->tours[t2][T->indice[t2]] = adep;
    //printHanoi(T);
    //verification(T);
}

//Solution récursive

void HanoiREC(Hanoi T,int gros,int petit,int t1,int t2){   
    int mid = t1+t2;

    //algorithme tiré du cour 

    //recherche de la tour intermédiaire
        switch(mid){
            case 1:
                mid = 2;
                break;
            case 2:
                mid = 1;
                break;
            case 3:
                mid = 0;
                break;
        }
    //execution
    if(gros==petit){  deplacer(T, t1, t2);}
    else if(gros > petit){
                HanoiREC(T,gros-1,petit, t1, mid);
                deplacer(T,t1, t2);
                HanoiREC(T,gros-1,petit, mid, t2);
    } 
}

//Solution itérative

void HanoiIter(Hanoi T,int t1,int t2){
    int mid = t1+t2;
    //recherche de la tour intermédiaire
        switch(mid){
            case 1:
                mid = 2;
                break;
            case 2:
                mid = 1;
                break;
            case 3:
                mid = 0;
                break;
        }
        //algo implémenté 
        //  For an even (pair) number of disks:
        //  
        //      make the legal move between pegs A and B (in either direction),
        //      make the legal move between pegs A and C (in either direction),
        //      make the legal move between pegs B and C (in either direction),
        //      repeat until complete.
        //  For an odd (impaair) number of disks:
        //  
        //      make the legal move between pegs A and C (in either direction),
        //      make the legal move between pegs A and B (in either direction),
        //      make the legal move between pegs B and C (in either direction),
        //     repeat until complete.
        //  In each case, a total of 2n − 1 moves are made. 
    //execution
    if(T->size%2){
        while (T->indice[t2] != ( T->size - 1 ))
        {   
            //check T1 T2
                if(T->indice[t1] == -1){
                    if(T->indice[t2] != -1) {deplacer(T,t2,t1);}
                    
                }else if(T->indice[t2] == -1){
                    if(T->indice[t1] != -1){ deplacer(T,t1,t2);}
                    
                }else{
                    if(T->tours[t1][T->indice[t1]] > T->tours[t2][T->indice[t2]]) {deplacer(T,t2,t1);}
                    else {deplacer(T,t1,t2);}
                }
                if(T->indice[t2] == ( T->size - 1 )) continue;
            //check T1 mid
                if(T->indice[t1] == -1){
                    if(T->indice[mid] != -1) {deplacer(T,mid,t1);}
                    
                }else if(T->indice[mid] == -1){
                    if(T->indice[t1] != -1){ deplacer(T,t1,mid);}
                    
                }else{
                    if(T->tours[t1][T->indice[t1]] > T->tours[mid][T->indice[mid]]) {deplacer(T,mid,t1);}
                    else {deplacer(T,t1,mid);}
                }

            //check mid T2
                if(T->indice[mid] == -1){
                    if(T->indice[t2] != -1) {deplacer(T,t2,mid);}
                    
                }else if(T->indice[t2] == -1){
                    if(T->indice[mid] != -1){deplacer(T,mid,t2);}
                    
                }else{
                    if(T->tours[mid][T->indice[mid]] > T->tours[t2][T->indice[t2]]) {deplacer(T,t2,mid);}
                    else {deplacer(T,mid,t2);}
                }
        }
    } 
    else{
        while (T->indice[t2] != ( T->size - 1 ))
        {
            //check T1 mid
                if(T->indice[t1] == -1){
                    if(T->indice[mid] != -1) {deplacer(T,mid,t1);}
                    
                }else if(T->indice[mid] == -1){
                    if(T->indice[t1] != -1){ deplacer(T,t1,mid);}
                    
                }else{
                    if(T->tours[t1][T->indice[t1]] > T->tours[mid][T->indice[mid]]) {deplacer(T,mid,t1);}
                    else {deplacer(T,t1,mid);}
                }

            //check T1 T2
                if(T->indice[t1] == -1){
                    if(T->indice[t2] != -1) {deplacer(T,t2,t1);}
                    
                }else if(T->indice[t2] == -1){
                    if(T->indice[t1] != -1){ deplacer(T,t1,t2);}
                    
                }else{
                    if(T->tours[t1][T->indice[t1]] > T->tours[t2][T->indice[t2]]) {deplacer(T,t2,t1);}
                    else {deplacer(T,t1,t2);}
                }

            //check mid T2
                if(T->indice[mid] == -1){
                    if(T->indice[t2] != -1) {deplacer(T,t2,mid);}
                    
                }else if(T->indice[t2] == -1){
                    if(T->indice[mid] != -1){ deplacer(T,mid,t2);}
                    
                }else{
                    if(T->tours[mid][T->indice[mid]] > T->tours[t2][T->indice[t2]]) {deplacer(T,t2,mid);}
                    else {deplacer(T,mid,t2);}
                }
        }
    }
}

void HanoiIter2(Hanoi T,int t1,int t2){
     int mid = t1+t2;
    
    
    // algorithme utilisé :
    //
    //  Number the disks 1 through n (largest to smallest).
    //
    //    If n is odd, the first move is from peg A to peg C.
    //    If n is even, the first move is from peg A to peg B.
    //
    //  Now, add these constraints:
    //    No odd disk may be placed directly on an odd disk.
    //    No even disk may be placed directly on an even disk.
    //    There will sometimes be two possible pegs: one will have disks, and the other will be empty. Place the disk on the non-empty peg.
    //    Never move a disk twice in succession.

    //recherche de la tour intermédiaire
        switch(mid){
            case 1:
                mid = 2;
                break;
            case 2:
                mid = 1;
                break;
            case 3:
                mid = 0;
                break;
        }

    //execution
    int last = -1,temp1,temp2;
    if(T->size % 2 == 1) {deplacer(T,t1,t2);last = t2;}
    else {deplacer(T,t1,mid);last = mid;}
    while(T->indice[t2] != ( T->size - 1 )){
        
        switch(last){
            case 0:
                temp1 = 1;
                temp2 = 2;
                break;
            case 1:
                temp1 = 0;
                temp2 = 2;
                break;
            case 2:
                temp1 = 0;
                temp2 = 1;
                break;
        }
        if(T->indice[temp1] != -1 && T->indice[temp2] != -1){
            if(T->tours[temp1][T->indice[temp1]] < T->tours[temp2][T->indice[temp2]]){
                 if((T->tours[last][T->indice[last]] % 2) != (T->tours[temp1][T->indice[temp1]] % 2) && T->tours[last][T->indice[last]] > T->tours[temp1][T->indice[temp1]]){
                    deplacer(T,temp1,last);
                }else{
                    deplacer(T,temp1,temp2);
                    last = temp2;
                } 
            }else{
                if((T->tours[last][T->indice[last]] % 2) != (T->tours[temp2][T->indice[temp2]] % 2) && T->tours[last][T->indice[last]] > T->tours[temp2][T->indice[temp2]]){
                    deplacer(T,temp2,last);
                }else{
                    deplacer(T,temp2,temp1);
                    last = temp1;
                } 
            }
        }
        else if(T->indice[temp1] == -1){
            if(T->tours[temp2][T->indice[temp2]] < T->tours[last][T->indice[last]] && T->tours[temp2][T->indice[temp2]]%2 != T->tours[last][T->indice[last]]%2){deplacer(T,temp2,last);}
            else {deplacer(T,temp2,temp1);last = temp1;}
        }
        else{
            if(T->tours[temp1][T->indice[temp1]] < T->tours[last][T->indice[last]] && T->tours[temp1][T->indice[temp1]] %2 != T->tours[last][T->indice[last]] %2){deplacer(T,temp1,last);}
            else {deplacer(T,temp1,temp2);last = temp2;}  
        }
    } 
}

//algorithme de vérification

void verification(Hanoi T){
    int old;
    //on garde les valeurs dans la variable old puis on compare avec la prochaine 
    //en cas d'erreur on quitte le programme apres avoir affiché un message d'erreur
    for (int i = 0; i < 3; i++)
    {   
        
        old = T->tours[i][0];
        for (int j = 1; j < T->indice[i]; j++)
        {
            if(old < T->tours[i][j]){printf("Erreur tour %d invalide",i);exit(-1);}
            old = T->tours[i][j];
        }
    }
    printf("Jeu valide\n");
}