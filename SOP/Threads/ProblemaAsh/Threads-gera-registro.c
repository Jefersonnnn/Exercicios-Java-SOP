#include <stdio.h>
#include <stdlib.h>
#include <time.h>

main(){
    srand( time(NULL) );
    int tipo;
    double valor;

    int registros = (rand()%50000 + 50000);
    for( ; registros>0; registros--){
        tipo = rand()%12;
        if(tipo>1){ // venda
            valor = ((rand()%2000)*5 + 5) / 100.0;
            printf("v:%.2lf\n", valor);
        }
        else{ // venda
            valor = ((rand()%9999)*5 + 100) / 100.0;
            printf("c:%.2lf\n", valor);
        }
    }

}
