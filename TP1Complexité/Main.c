#include "Functions.h"
void calcTempTable(long table[20]);
void qst3(long long table[6]);



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
    long Ntable7[20] = {1300727,1300751,1300769,1300771,1300781,1300813,1300829,1300837,1300841,1300843,1300907,1300921,1300927,1300931,1300963,1300967,1300979,1300997,1301011,1301017};
    long Ntable8[20] = {100000007,100000037,100000039,100000049,100000073,100000081,100000123,100000127,100000193,100000213,100000217,100000223,100000231,100000237,100000259,100000267,100000279,100000357,100000379,100000393};
    calcTempTable(Ntable7);
    calcTempTable(Ntable8);

    //test nb3
    long long table6a12[6] = {1000003,10000019,100000007,1000000007,10000000019,100000000003};
    qst3(table6a12);
    
    return 0;
}

void calcTempTable(long table[20]){
    double T[6] = {0,0,0,0,0,0};

    for (int i = 0; i < sizeof(*table) / sizeof(long); i++)
    {
        T[0]+=timeA1(table[i]);
        T[1]+=timeA2(table[i]);
        T[2]+=timeA3(table[i]);
        T[3]+=timeA4(table[i]);
        T[4]+=timeA5(table[i]);
        T[6]+=timeA6(table[i]);
    }
    printf("Qst 2 long %d :\n",get_int_len(table[0]));
    for (int i = 0; i < 6; i++)
    {
        printf("Algo :%d -> temp : %f\n",i+1,T[i]/(sizeof(*table) / sizeof(int)));
    }
}

void qst3(long long table[6]){

    double T[6] = {0,0,0,0,0,0};
    for (int j = 0; j < 6; j++)
        {
            for (int i = 0; i < 50; i++)
            {

                        T[0]+=timeA1(table[j]);
                        T[1]+=timeA2(table[j]);
                        T[2]+=timeA3(table[j]);
                        T[3]+=timeA4(table[j]);
                        T[4]+=timeA5(table[j]);
                        T[6]+=timeA6(table[j]);  
            }
            printf("Qst 3 long de %d :\n",j+6);

            for (int i = 0; i < 6; i++)
                {
                    printf("Algo :%d -> temp : %f\n",i+1,T[i]/50);
                }
    }
    

}

