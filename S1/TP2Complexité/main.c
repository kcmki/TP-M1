#include "Fonction.h"



int main(int argc,char** argv){
        //initialisation et vérification des choix d'execution
            if(argc < 3){
                printf("Too few arguments");
                return 0;
            }
            if(argc > 3){
                printf("Too many arguments");
                return 0;
            }
            int algo = atoi(argv[1]);
            if(algo < 1 || algo > 6 ){
                printf("Erreur arguments algorithme non valide doit etre en 1 et 6");
                return 0;
            }
            int tailles[9] = {10000,50000,100000,500000,1000000,5000000,10000000,50000000,100000000};
            char fichiers[9][20] = {"tri-10^4.bin","tri-510^4.bin","tri-10^5.bin","tri-510^5.bin","tri-10^6.bin","tri-510^6.bin","tri-10^7.bin","tri-510^7.bin","tri-10^8.bin"};
            int choix = atoi(argv[2]) - 1;
            if(choix < 0 || choix > 8){
                printf("Erreur choix de la taille doit etre en 1 et 9");
                return 0;
            }
            //tableau temporaire
            long *temp = NULL;
        //ouverture du fichier
            FILE *fich = fopen(fichiers[choix],"rb");
            if(fich == NULL){
                printf("Erreur ouverture du fichier");
                exit(-1);
            }
        //lecture du tableau
            long *tab = malloc(sizeof(long)*tailles[choix]);
            if(tab == NULL){
                printf("Erreur allocation memoire");exit(-1);
            }
            if(fread(tab,sizeof(tab[0]),tailles[choix],fich) != tailles[choix]){
                printf("Erreur lecture du fichier");
                exit(-1);
            };
        fclose(fich);
        //debut algorithmes 
        //calcule temp d'execution
        clock_t Tdebut,Tfin;

        Tdebut = clock();
        
        long long calc = 0;
        //execution de l'algorithme
        switch(algo){
            case 1:
                    calc = longselection(tab,tailles[choix]);
                break;
            case 2:
                    calc = longinsertion(tab,tailles[choix]);
                break;
            case 3:
                    calc = longbulle(tab,tailles[choix]);
                break;
            case 4:
                    longrapide(tab,0,tailles[choix]-1,&calc);
                break;
            case 5:
                    temp = malloc(sizeof(long) * tailles[choix]);
                    if( temp == NULL){
                        printf("Allocation memoire tableau temp echoué");
                        exit(-1);
                    }
                    calc = longfusion(tab,0,tailles[choix]-1,temp);
                break;
            case 6:
                    calc = longtas(tab,tailles[choix]-1);
                break;
        }
        Tfin = clock();
        printf("temp de l'algo %d avec la taille %s est de : %f nombre de comparaison %lld",algo,fichiers[choix],(double)(Tfin-Tdebut)/CLOCKS_PER_SEC,calc);

}