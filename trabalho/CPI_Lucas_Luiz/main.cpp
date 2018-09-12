#include <iostream>
#include <string>
#include <fstream>
#include <conio.h>
#include <windows.h>
#include <time.h>

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

void desenha(int xi, int yi, int x, int y){ // FUNÇÃO PARA DESENHAR PAREDES
    int h, l; // VARIAVEL AUXILIAR DE DESENHO
    for(l=0;l<x;l++)   // DESENHA PAREDE HORIZONTAL
    {
        gotoxy(xi+l,yi); printf("%c",205);
        gotoxy(xi+l,yi+y); printf("%c",205);
    }
    for(h=1;h<y;h++)   // DESENHA PAREDE VERTICAL
    {
        gotoxy(xi,yi+h); printf("%c",186);
        gotoxy(xi+x,yi+h); printf("%c",186);
    }
    // PONTAS DA ESQUERDA
    gotoxy(xi,yi); printf("%c", 201); ///superior
    gotoxy(xi,yi+y); printf("%c", 200); ///inferior
    // PONTAS DA DIREITA
    gotoxy(xi+x,yi); printf("%c",187); ///superior
    gotoxy(xi+x, yi+y); printf("%c",188); ///inferior
}

void parede(int xi,int yi, int h, int larg, int xy, int xx){

    for(int i=1;i<h;i++)   // DESENHA PAREDE VERTICAL
    {
        gotoxy(xi,yi+i); printf("%c",186);
        //gotoxy(xi+,yi+i); printf("%c",186);
    }

    for(int i=0;i<larg;i++){
        gotoxy(xx+i, xy); printf("%c",205);
    }


}

void interc(int x, int y){
    for(int i=0; i<3;i++)
        if(i==0){
            gotoxy(x, y); printf("%c", 203);
            gotoxy(x,y+16); printf("%c", 202);
        }else if(i==2){
        gotoxy(x+10, y); printf("%c", 203);
        gotoxy(x+10, y+16); printf("%c", 202);
        }else{
            gotoxy(x+31, y+2); printf("%c", 203);
            gotoxy(x+31, y+8); printf("%c", 202);

        }
}

int main()
{
    string nome;
    ifstream leitura;
    gotoxy(40, 5); cout << "Qual exercicio?";
    gotoxy(40,6); cin >> nome;
    system("cls");
    leitura.open(nome+".txt");

    if(!leitura.is_open()){
        gotoxy(20,5); cout<<"Nao foi possivel abrir o arquivo";
        desenha(19,4,33,2);
    }else{

    char vet[33];
    string op = "";
    double N_inst=0;

    int R=0, I=0, J=0, L=0, S=0, B=0, f=0;
    int Rc=4, Lc=5, Sc=4, Bc=3,Jc=3,Ic=4;
    int totalC;

    while (leitura.getline(vet,33)) {
       for(int i=0; i<6;i++){
            op+=vet[i];
        }
       if(op=="000000" || op=="011100"){
           R++;
       }else if(op[0]=='1' && op[2]=='0'){//Verifica se é uma instrução de load...
           L++;
       }else if(op[0]=='1' && op[2]=='1'){//Verifica se é uma instrução de save...
           S++;
       }else if(op[0]=='0' && op[2]=='1'){//Verifica se é uma instrução do tipo imediato...
           I++;
       }else if(op[0]=='0' && op[3]=='1'){//Verifica se é uma instrução do tipo branch...
           B++;
       }else if(op[0]=='0' && op[2]=='0' && op[4]=='1'){//Verifica se é uma instrução do tipo jump...
           J++;
       }else {//se não entrar em nenhuma condição, sera não indentificada.
           f++;
       }
        N_inst++;//Numero de instruções que há no programa."desnescessario"
        op="";//limpa a string op para não acumular dados de laços anteriores.
     }

    double CPI;
    CPI=(((R/N_inst)*Rc) + ((L/N_inst)*Lc) + ((S/N_inst)*Sc) + ((B/N_inst)*Bc) + ((J/N_inst)*Jc) + ((I/N_inst)*Ic) );
    totalC=(R*Rc)+(S*Sc)+(L*Lc)+(J*Jc)+(B*Bc)+(I*Ic);
    desenha(0,0,61,16);

    gotoxy(2,1); cout<< "Tipo de instrucao";
    parede(20,0, 16,19,2, 1);
    interc(20,0);



    gotoxy(24, 1); cout<< "CPI";
    parede(30, 0, 16, 9, 2, 21);

    gotoxy(6,3); cout<< "Tipo-R";
    gotoxy(25, 3); cout <<R;
    parede(20,0, 16,19,4, 1);
    parede(30, 0, 16, 9, 4, 21);

    gotoxy(5,5); cout<< "Imediato";
    gotoxy(25, 5); cout <<I;
    parede(20,0, 16,19,6, 1);
    parede(30, 0, 16, 9, 6, 21);

    gotoxy(6,7); cout<<"Branch";
    gotoxy(25, 7); cout <<B;
    parede(20,0, 16,19,8, 1);
    parede(30, 0, 16, 9, 8, 21);


    gotoxy(7,9); cout<< "Jump";
    gotoxy(25, 9); cout <<J;
    parede(20,0, 16,19,10, 1);
    parede(30, 0, 16, 9, 10, 21);



    gotoxy(7,11); cout<< "Load";
    gotoxy(25,11); cout <<L;
    parede(20,0, 12,19,12, 1);
    parede(30, 0, 12, 9, 12, 21);

    gotoxy(7,13); cout<< "Save";
    gotoxy(25, 13); cout <<S;
    parede(20,0, 12,19,12, 1);
    parede(30, 0, 12, 9, 12, 21);

    gotoxy(2,15); cout<< "Nao classificado";
    gotoxy(25, 15); cout <<f;

    parede(20,0, 14,19,14, 1);
    parede(30, 0, 14, 9, 14, 21);

    gotoxy(36,1); cout<<"Outras informacoes";
    parede(51,2, 6, 20, 2, 31);
    parede(51, 2, 6, 9, 2, 52);

    gotoxy(31,3); cout <<"Total de instrucoes";
    parede(51,2, 6, 20, 4, 31);
    parede(51, 2, 6, 9, 4, 52);
    gotoxy(56, 3); cout<<N_inst;

    gotoxy(33,5); cout <<"Total de Ciclos";
    parede(51,2, 6, 20, 6, 31);
    parede(51, 2, 6, 9, 6, 52);
    gotoxy(56, 5); cout<<totalC;

    gotoxy(39, 7);cout <<"CPI";
    parede(51,2, 6, 20, 8, 31);
    parede(51, 2, 6, 9, 8, 52);
     gotoxy(53, 7); cout<<CPI;
    }

    gotoxy(1, 27); system("Pause");
     return 0;
}
