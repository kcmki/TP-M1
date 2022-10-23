#include "fonction.h"



int main(int argc,char** argv){
    int tab[9] = {5,2,1,1,5,45,12,32,3};
    int temp[NELEMS(tab)] = {0};
    printtab(tab,NELEMS(tab));
    tas(tab,NELEMS(tab)-1);
    printtab(tab,NELEMS(tab));
}