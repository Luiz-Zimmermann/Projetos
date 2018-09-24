#ifndef TADHASH_H
#define TADHASH_H
#include <iostream>
#include<windows.h>
using namespace std;


void hidecursor(void){ //esconde o cursor
    CONSOLE_CURSOR_INFO cursor = {1, FALSE};
    SetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE), &cursor);
}

void gotoxy(short x , short y){ // Para escolher aonde vai cada informação (coordenadas)
    COORD coord;
    coord.X = x;
    coord.Y = y;

    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}


struct Thash{
    char chave;
    //char livre;
    string livre;
    Thash vetor[2];
    int time;
};

void iniciaThash(Thash tabela[], int tam){

    for(int i=0; i<tam; i++){
        for(int j=0;i<2;i++){
        tabela[i].vetor[j].livre="------";
        tabela[i].vetor[j].chave="------";
        tabela[i].vetor[j].time=999;
        }

    }


}

int posicao( int pos, int tam){

    return pos%tam;
}

int LRU(int x, int y ){
    if(x>y){
        return 0;
    }else{
        return 1;
    }
}

void inserir(Thash tabela[], char chave, int j, int tam){


    int pos=posicao(chave, tam);
    //int i=0;
    int ver=LRU(tabela[pos].vetor[0].time, tabela[pos].vetor[1].time);

    int cpos;
      for(int i=0;i<2;i++){
        if(chave==tabela[pos].vetor[0].chave){
            tabela[j].vetor[0].livre="Acerto";
            tabela[j].vetor[0].time=0;
            cpos=i;
        }
      }
        if(cpos==1 || cpos==2){
            return;
        }else if(ver==0){//se o dado1  estiver a mais tempo q dado2, entao o novo dado vai sobreescrever ele
            tabela[j].vetor[0].livre="Falta";
            tabela[pos].vetor[0].chave=chave;
            tabela[j].vetor[0].time=0;

        }else{//senao o dado novo vai sobreescrever o dado2
            tabela[j].vetor[1].livre="Falta";
            tabela[pos].vetor[1].chave=chave;
            tabela[j].vetor[1].time=0;
        }





    /*while(i < tam && tabela[(pos+i)%tam].livre != 'L' && tabela[(pos+i)%tam].livre !='R'){
        i=i+1;
    }

    if(i<tam){
        tabela[(pos+i)%tam].chave=chave;
        tabela[(pos+i)%tam].livre='O';
    }else{
        cout<<"Tabela cheia"<<endl;
    }*/

}

/*void remover(Thash tabela[], int chave, int tam){

    int pos=posicao(chave, tam);
    int i=0;

    while(i < tam && tabela[(pos+i)%tam].livre != 'L' && tabela[(pos+i)%tam].livre !='R' && chave!=tabela[(pos+i)%tam].chave){
        i=i+1;
    }

    if(i<tam){
        tabela[(pos+i)%tam].livre='R';
    }


}*/

void imprimir(Thash tabela[], int tam, char vet[]){
    gotoxy(35, 1); cout<<"Cache apos acesso";
    gotoxy(0,2); cout<<"Endereco"; gotoxy(15,2); cout<<"Validade"; gotoxy(35,2);cout<<"Bloco"; gotoxy(45,2); cout<<"Endereco";

    for(int i=0; i<tam;i++){

    gotoxy(0,3+i); cout<<vet[i];gotoxy(16,3+i); cout<<tabela[i].livre; gotoxy(37,3+i); cout<<i; gotoxy(48,3+i); cout<<tabela[i].chave<<endl;

    }
}


#endif // TADHASH_H
