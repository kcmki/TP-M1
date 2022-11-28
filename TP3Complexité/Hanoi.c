#include "Hanoi.h"

int main(int argc,char** argv){

    if(argc <2 || argc >2){
        printf("Too many or few arguments\n");
        return 0;
    }
    Hanoi tour = NULL;
    tour = initTour(atoi(argv[1]));

    printHanoi(tour);
    HanoiREC(tour,tour->size,1,0,2);

    return 0;
}