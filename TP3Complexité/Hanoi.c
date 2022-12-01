#include "Hanoi.h"
char* getName(char* str);

int main(int argc,char** argv){
    //vérification du nombre d'arguments

        if(argc <4 || argc >4){
            printf("Too many or few arguments\n");
            printf("%s <#disques> <Choix algo> <Destination>",getName(argv[0]));
            return 0;
        }
    //initialisation et déclarations des variables
        clock_t debut,fin;
        Hanoi tour = NULL;
        tour = initTour(atoi(argv[1]));
        int choix = atoi(argv[2]);
        int toTour = atoi(argv[3]);

    //vérification des valeurs des arguments
        if(choix >3 || choix <1){
            printf("choix doit etre 1(Recursive) 2(Iterative) ou 3(Iterative2)");
            return 0;
        }
        if(toTour != 2 && toTour != 3){
            printf("Tour doit etre 2 ou 3");
            return 0;
        }

    //execution des algorithme et calcule du temp d'execution
        debut = clock();
        switch (choix)
        {
            case 1:
                HanoiREC(tour,tour->size,1,0,toTour-1);
                break;
            case 2:
                HanoiIter(tour,0,toTour-1);
                break;
            case 3:
                HanoiIter2(tour,0,toTour-1);
                break;
        }
        fin = clock();
        
    //affichage du temp d'execution et du resultat
        printHanoi(tour);
        verification(tour);
        switch (choix)
        {
            case 1:
                printf("Solution recursive : %f s\n",(float)(fin - debut)/CLOCKS_PER_SEC);
                break;
            case 2:
                printf("Solution Iterative : %f s\n",(float)(fin - debut)/CLOCKS_PER_SEC);
                break;
            case 3:
                printf("Solution Iterative second algorithme : %f s\n",(float)(fin - debut)/CLOCKS_PER_SEC);
                break;
        }

    return 0;
}




char* getName(char* str){
    char delim[] = "\\";
    char *ptr = strtok(str, delim);
    char *past = NULL;
	while(ptr != NULL)
	{
        past = ptr;
		ptr = strtok(NULL, delim);
	}
    return past;
}