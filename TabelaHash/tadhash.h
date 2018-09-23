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


struct Thash
{
    int chave;
    //char livre;
    string livre;
};

void iniciaThash(Thash tabela[], int tam){

    for(int i=0; i<tam; i++){
        tabela[i].livre="------";
        tabela[i].chave=0;

    }


}

int posicao( int pos, int tam){

    return pos%tam;
}

void inserir(Thash tabela[], int chave, int i, int tam){


    int pos=posicao(chave, tam);
    //int i=0;

    if(chave==tabela[pos].chave){
        tabela[i].livre="Acerto";
    }else{
        tabela[i].livre="Falta";
        tabela[pos].chave=chave;

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

void imprimir(Thash tabela[], int tam, int vet[]){
    gotoxy(35, 1); cout<<"Cache apos acesso";
    gotoxy(0,2); cout<<"Endereco"; gotoxy(15,2); cout<<"Validade"; gotoxy(35,2);cout<<"Bloco"; gotoxy(45,2); cout<<"Endereco";

    for(int i=0; i<tam;i++){

    gotoxy(0,3+i); cout<<vet[i];gotoxy(16,3+i); cout<<tabela[i].livre; gotoxy(37,3+i); cout<<i; gotoxy(48,3+i); cout<<tabela[i].chave<<endl;

    }
}


#endif // TADHASH_H
