#Tabela hash modificada para trabalho sobre memória cache
#Hash feita por Luiz Zimmermann


#include <iostream>
#include<conio.h>
#include<windows.h>
using namespace std;

#include"tadhash.h"
#include"tadencadeada.h"



int main()
{

    int  x;
    cout<<"Numero de blocos da Cache: ";
    cin>>x;


    Thash t[x];

    iniciaThash(t, x );

    //imprimir(t, 8);
    //chave=endereço neste caso

    int vet[x];
    for(int i=0;i<x;i++){
        cout<<"Informe o endereco: ";
        cin>>vet[i];

    }
    system("cls");

    for(int i=0;i<x;i++){

        system("cls");

        inserir(t,vet[i],i,x);
        imprimir(t,x,vet);
        system("pause");
    }

    return 0;
}
