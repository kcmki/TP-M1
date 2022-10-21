#include "fonction.h"



int main(int argc,char** argv){
    int tab[5] = {5,1,3,45,3};
    insertion(tab,NELEMS(tab));
    printtab(tab,NELEMS(tab));
}