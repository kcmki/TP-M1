#include "graph.h"

int graph[9][9] = {
        /*A ,B ,C ,D ,E ,F ,G ,H ,M*/
    /*0-A*/{0 ,10,0 ,0 ,0 ,0 ,0 ,50,15},
    /*1-B*/{10,0 ,15,10,0 ,0 ,0 ,0 ,20},
    /*2-C*/{0 ,15,0 ,10,10,20,0 ,0 ,0 },
    /*3-D*/{0 ,10,10,0 ,20,0 ,10,0 ,30},
    /*4-E*/{0 ,0 ,10,15,0 ,0 ,10,0 ,30},
    /*5-F*/{0 ,0 ,20,0 ,10,0 ,10,20,0 },
    /*6-G*/{0 ,0 ,0 ,10,0 ,10,0 ,15,0 },
    /*7-H*/{50,0 ,0 ,0 ,0 ,20,15,0 ,20},
    /*8-M*/{15,20,0 ,30,0 ,0 ,0 ,20,0 }
};
int paschemin(list L,int i){
    if(L == NULL) return 1;
    while(L != NULL){
        if(L->nombre == i){return 0;}
        L = L->svt;
    }
    return 1;
}
void chemins(int taille,int source,int arrive,list *L,list *chemin){

    for (int i = 0; i < 9; i++)
    {   
        if(graph[source][i] != 0 && i != arrive && paschemin(*chemin,i)){
            ajouttete(chemin,i);
            chemins(taille + graph[source][i],i,arrive,L,chemin);
        }
        if(graph[source][i] != 0 && i == arrive){
            ajouttete(L,taille + graph[source][i]);
        }
    }
}

int main(){
    list L = NULL;
    list chemin = NULL;
    chemins(0,0,5,&L,&chemin);
    printlist(L);
    return 0;
}
