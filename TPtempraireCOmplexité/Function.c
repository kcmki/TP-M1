#include "stdio.h"

int PGCDiter(int a , int b){
    int pgcd = 0;
    while(b != a){
        pgcd = b;
        b = a%b;
        a = pgcd;
        if(b == 1) return b;
        if(b == 0) return a;
    }
    return a;
}
int PGCDrec(int a , int b){
    if(b==0) return a;
    PGCDrec(b,a%b);
}

void main(int args,int* argsv[]){
    printf("result iter : %d\n",PGCDiter(125,5));
    printf("result rec : %d\n",PGCDrec(23,15));
}