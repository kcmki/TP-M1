#include "Functions.h"
#include "time.h"

int get_int_len (int value){
  int l=1;
  while(value>9){ l++; value/=10; }
  return l;
}

//verification algorithms
bool A1(long long N){
    for (long long i = 2; i < N; i++)
    {   
        if(N % i == 0) return false;
    }
    return true;
}
bool A2(long long N){
    for (long long i = 2; i < N/2; i++)
    {   
        if(N % i == 0) return false;
    }
    return true;
}
bool A3(long long N){
    long long x = 2;
    while(x <= N/x){
        if(!(N % x)) return false;
        x++;
    }
    return true;
}
bool A4(long long N){
    if(N % 2 == 1){
        for (long long i = 3; i < N; i=i+2)
        {   
            if(N % i == 0) return false;
        }
    }else{
        if(N == 2) return true;
        return false;
    }
    return true;
}
bool A5(long long N){
    if(N % 2 == 1){
        for (long long i = 3; i < N/2; i=i+2)
        {   
            if(N % i == 0) return false;
        }
    }else{
        if(N == 2) return true;
        return false;
    }
    return true;
}
bool A6(long long N){
    if(N % 2 == 1){
        long long x = 3;
        while(x <= N/x){
            if(!(N % x)) return false;
            x+=2;
        }
    }else{
        if(N == 2) return true;
        return false;
    }
    return true;
}

//time calculators
double timeA1(long long N){
    clock_t Tdebut,Tfin;

    Tdebut = clock();
    bool temp = A1(N);
    Tfin = clock();

    return (double) (Tfin-Tdebut)/CLOCKS_PER_SEC;

}
double timeA2(long long N){
    clock_t Tdebut,Tfin;

    Tdebut = clock();
    bool temp = A2(N);
    Tfin = clock();

    return (double) (Tfin-Tdebut)/CLOCKS_PER_SEC;

}
double timeA3(long long N){
    clock_t Tdebut,Tfin;

    Tdebut = clock();
    bool temp = A3(N);
    Tfin = clock();

    return (double) (Tfin-Tdebut)/CLOCKS_PER_SEC;

}
double timeA4(long long N){
    clock_t Tdebut,Tfin;

    Tdebut = clock();
    bool temp = A4(N);
    Tfin = clock();

    return (double) (Tfin-Tdebut)/CLOCKS_PER_SEC;

}
double timeA5(long long N){
    clock_t Tdebut,Tfin;

    Tdebut = clock();
    bool temp = A5(N);
    Tfin = clock();

    return (double) (Tfin-Tdebut)/CLOCKS_PER_SEC;

}
double timeA6(long long N){
    clock_t Tdebut,Tfin;

    Tdebut = clock();
    bool temp = A6(N);
    Tfin = clock();

    return (double) (Tfin-Tdebut)/CLOCKS_PER_SEC;

}