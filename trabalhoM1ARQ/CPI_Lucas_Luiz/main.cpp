#include <iostream>
#include <string>
#include <fstream>


using namespace std;



int main()
{

    ifstream leitura;
    leitura.open("test.txt");

    if(!leitura.is_open()){
        cout<<"nao"<<endl;
    }

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

    cout<<"Instrucoes tipo R: "<<R<<endl;
    cout<<"Instrucoes tipo Load: "<<L<<endl;
    cout<<"Instrucoes tipo Save: "<<S<<endl;
    cout<<"Instrucoes tipo Imediato: "<<I<<endl;
    cout<<"Instrucoes tipo Branch: "<<B<<endl;
    cout<<"Instrucoes tipo Jump: "<<J<<endl;
    cout<<"Instrucoes nao classificadas: "<<f<<endl;
    cout <<"Total de instrucoes: "<<N_inst<<endl;
    cout <<"Total de Ciclos: "<<totalC<<endl;
    cout <<"CPI: "<<CPI<<endl;


     return 0;
}
















