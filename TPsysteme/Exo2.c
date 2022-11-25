#include "math.h"
#include "stdlib.h"
#include "stdio.h"
#include "time.h"
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>

int main(){

    time_t t;
    srand(0);<
    pid_t x,y,z;
    int status;

    x = fork();
    if(x < 0){
        perror("FAILURE\n");
        return 1;
    }
    if(x == 0){
        int random = (int)rand() % 100;
        printf("x%d\n",random);
        return random;
    }else
    {
        y = fork();
        if(y < 0){
        perror("FAILURE\n");
        return 1;
        }else{
            if(y == 0){
                int random = (int)rand() % 100;
                printf("y%d\n",random);
                return random;}

            else{
                    z = fork();
                    if(z < 0){
                    perror("FAILURE\n");
                    return 1;
                    }else{
                        if(z == 0){
                            int random = (int)rand() % 100;
                            printf("z%d\n",random);
                            return random;}
                        else{
                            waitpid(x, &status, 0);
                            long long int val = WEXITSTATUS(status);
                            waitpid(y, &status, 0);
                            val *= WEXITSTATUS(status);
                            waitpid(z, &status, 0);
                            val += WEXITSTATUS(status);
                            printf("%lld", val);
                            printf("pi0d");
                            return 0;        
                        }

                    }
                }
            }
    }
    return 0;
}