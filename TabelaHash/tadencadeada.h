#ifndef TADENCADEADA_H
#define TADENCADEADA_H
#include <iostream>
using namespace std;

struct EThash
{
    int chave;
    EThash *prox;
};

void iniciaEThash(EThash *tabela[], int tam){

    for(int i=0; i<tam; i++){
        tabela[i]=NULL;
    }


}

/*int posicao( int pos, int tam){

    return pos%tam;
}*/

void inserirEnc(EThash *tabela[], int chave, int tam){

    int pos=chave%tam;

    //int pos=posicao(chave, 8);
    int i=0;

    EThash *novo;
    novo=new EThash();
    novo->chave=chave;
    novo->prox=tabela[pos];//  lista EX: 4321 -->  como c fosse insere inicio   2.prox=1
    tabela[pos]=novo;      // inicio=2


}



void removerEnc(EThash *tabela[], int chave, int tam){

     int pos=chave%tam;
    int i=0;
    EThash *aux;

    if(tabela[pos]!=NULL){
        if(tabela[pos]->chave == chave){
            aux=tabela[pos];
            tabela[pos]=tabela[pos]->prox;
            delete aux;
        }else{
            aux=tabela[pos]->prox;
            EThash *ant=tabela[pos];

            while(aux!=NULL && aux->chave !=chave){
                ant=aux;
                aux=aux->prox;
            }
            if(aux!=NULL){
                ant->prox=aux->prox;
                delete aux;
            }else{
                cout<<"Numero nao encontradod"<<endl;
            }
        }
    }else{
        cout<<"Numero nao encontradod"<<endl;
    }




}

void imprimirEnc(EThash *tabela[], int tam){
    EThash *aux;

    cout<<"Chave"<<endl;

    for(int i=0; i<tam;i++){
        aux=tabela[i];

        while (aux!=NULL) {
            cout<<"Entrada["<<i<<"]: "<<aux->chave<<endl;
            aux=aux->prox;
        }
    }
}
















#endif // TADENCADEADA_H
