#include "stdio.h"
#include "stdlib.h"
#include "time.h"
#include "string.h"

typedef struct{
    int** tours;
    int indice[3],size;
}*Hanoi,hanoi;
// on vas utiliser une structure constitué de trois tableau de meme taille que le nombre de disque du jeu 
//en plus d'un variable size qui nous aidra par la suite 
//on aura aussi un tableau avec 3 variable qui serviront d'indice pour les tableau qu'on vas manipuler en tant que pile

//******************************************************************************
//fonctions utile
//fonction d'initialisation du jeu
Hanoi initTour(int size);
//fonction d'affichage
void printHanoi(Hanoi T);

//******************************************************************************
//algorithme tiré du cous qui consiste a découper le probleme en sous probleme a chaque fois 
//jusqu'a arriver a un probleme assez simple pour etre résolu directement 
//méthode diviser pour mieux regner 
void HanoiREC(Hanoi T,int gros,int petit,int t1,int t2);
//******************************************************************************
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
void HanoiIter(Hanoi T,int t1,int t2);

//******************************************************************************
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
void HanoiIter2(Hanoi T,int t1,int t2);

//******************************************************************************
// algo vérification
//on garde les valeurs dans la variable old puis on compare avec la prochaine 
//en cas d'erreur on quitte le programme apres avoir affiché un message d'erreur
void verification(Hanoi T);