#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <time.h>
#include <limits.h>




int main(int argc,char** argsv){
    
    int returnCode;
    //vérification des arguments
        if(argc < 3){
            printf("Too few arguments");
            return 0;
        }
        if(argc > 3){
            printf("Too many arguments");
            return 0;
        }
        long taille = atol(argsv[2]);
        if(taille == 0 ){
            printf("Erreur arguments longeur incorrect ou taille = 0 (tableau vide)");
            return 0;
        }
/* 
    //creation du fichier
        FILE *fich;

        fich = fopen(strcat(argsv[1],".bin"),"w");
        if(fich == NULL){
            printf("Erreur ouverture du fichier");
            return 0;
        }



    //creation du tableau
        
        long *tableau = malloc(sizeof(long) * taille);
        srand(time(0));
        for (long i = 0; i < taille; i++)
        {   
            long number = ((float) rand() / RAND_MAX ) * LONG_MAX ;
            tableau[i] = number;
        }
        printf("\n");
    
    //ecriture
        
        if(fwrite(tableau,sizeof(tableau[0]),taille,fich) != taille){
            printf("Erreur ecriture du fichier");
            exit(-1);
        };

    //fermeture du fichier
        returnCode = fclose( fich );
        if ( returnCode == EOF ) {
            fprintf( stderr, "Cannot close writing file\n" );
            exit( -1 );
        }

    */
    // partie 2
    
    //ouverture du fichier
        FILE *fich = fopen(strcat(argsv[1],".bin"),"rb");
        if(fich == NULL){
            printf("Erreur lors de la lecture");
            return 0;
        }
    
    
    //lecture du fichier
        long *tab2 = malloc(sizeof(long)*taille);
        if(tab2 == NULL){
            printf("Erreur allocation memoire");exit(-1);
        }
        if(fread(tab2,sizeof(tab2[0]),taille,fich) != taille){
            printf("Erreur lecture du fichier");
            exit(-1);
        };


    //affichage du tableau
        for (int i = 0; i < taille; i++)
        {
            printf("[%ld]",tab2[i]);
        }
        printf("\n");

    //fermeture du fichier
        returnCode = fclose( fich );
        if ( returnCode == EOF ) {
            fprintf( stderr, "Cannot close reading file\n" );
            exit( -1 );
        }
    return 1; 

} 