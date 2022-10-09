#include "Functions.h"

int main(){

    int N = 1300751;

    //test nb1
    printf("temp A1: %f\n",timeA1(N));
    printf("temp A2: %f\n",timeA2(N));
    printf("temp A3: %f\n",timeA3(N));
    printf("temp A4: %f\n",timeA4(N));
    printf("temp A5: %f\n",timeA5(N));
    printf("temp A6: %f\n",timeA6(N));

    //test nb2
    int Ntable[25] = {1300727,1300751,1300769,1300771,1300781,1300813,1300829,1300837,1300841,1300843,1300907,1300921,1300927,1300931,1300963,1300967,1300979,1300997,1301011,1301017,1301021,1301023,1301033,1301057,1301077};

    double T[6] = {0,0,0,0,0,0};

    for (int i = 0; i < sizeof(Ntable) / sizeof(int); i++)
    {
        T[0]+=timeA1(Ntable[i]);
        T[1]+=timeA2(Ntable[i]);
        T[2]+=timeA3(Ntable[i]);
        T[3]+=timeA4(Ntable[i]);
        T[4]+=timeA5(Ntable[i]);
        T[6]+=timeA6(Ntable[i]);
    }
    printf("Qst 2:\n");
    for (int i = 0; i < 6; i++)
    {
        printf("Algo :%d -> temp : %f\n",i+1,T[i]/(sizeof(Ntable) / sizeof(int)));
    }

    return 0;
}

