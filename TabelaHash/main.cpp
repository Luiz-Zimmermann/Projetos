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

    /*inserir(t , 41, 8);
    imprimir(t, 8);
    inserir(t,23, 8);
    inserir(t, 25,8);
    inserir(t, 39, 8);

     cout<<endl;
     imprimir(t, 8);
     */

    //remover(t, 25, 8);



    //cout<<endl;
    //imprimir(t,8);


   /* EThash *t[8];

    iniciaEThash(t, 8);

    imprimirEnc(t, 8);

    inserirEnc(t, 14,8);

    cout<<endl;

    imprimirEnc(t,8);
*/



    return 0;
}
