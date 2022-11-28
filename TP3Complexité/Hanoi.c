#include "Hanoi.h"

int main(int argc,char** argv){
    printf("ff");
    if(argc <2 || argc >2){
        printf("Too many or few arguments\n");
        return 0;
    }
    Hanoi tour = NULL;
    tour = initTour(atoi(argv[2]));
    
    //printHanoi(tour);
    

    return 0;
}