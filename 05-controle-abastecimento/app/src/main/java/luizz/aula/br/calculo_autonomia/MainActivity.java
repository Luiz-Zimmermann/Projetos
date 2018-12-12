package luizz.aula.br.calculo_autonomia;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton add;
    private TextView autonomia;

    private boolean permissao;
    int codigo = 2409;

    //Não sei o porque, mas o programa não funciona em APIs acima de 27 pelo menos em alguns devices emulados no Android Studio
    //Foi testado em 4 dispositivos reais diferentes e pelo menos nesses casos funcionou


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //verifica se ja tem permissao de gps
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //pede permissao ao usuario
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else{
            permissao = true;
        }

        add = findViewById(R.id.add);
        autonomia = findViewById(R.id.tvKilo);

        ArrayList<abastecimentoInfo> dados = new ArrayList<abastecimentoInfo>();
        dados = abastecimentoDAO.carrega_Lista(this.getApplicationContext());
        if(dados.size()>1){
            double litrosTotal=0, distance, kmpL;
            distance = dados.get(dados.size()-1).getDistancia() - dados.get(0).getDistancia();

            for(int i=0; i<dados.size()-1; i++){
                litrosTotal += dados.get(i).getLitros();
            }
            kmpL=distance/litrosTotal;
            NumberFormat numF = DecimalFormat.getInstance();
            numF.setMaximumFractionDigits(2);
            autonomia.setText(numF.format(kmpL));
        }else{
            autonomia.setText("----");
        }
    }

    @Override//verifica se o usuario deu acesso ou nao ao utilizar o gps
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissao = true;
                } else {
                    Toast.makeText(this, "Não vai funcionar!!!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    public void onclick(View v){

       Intent trocar_act = new Intent(this.getApplicationContext(), abastecimentoLista.class);
       trocar_act.putExtra("permissao", permissao);
       startActivityForResult(trocar_act, codigo);
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==codigo) {
            if (resultCode == 1) {
                ArrayList<abastecimentoInfo> dados = new ArrayList<abastecimentoInfo>();
                dados = abastecimentoDAO.carrega_Lista(this.getApplicationContext());
                if(dados.size()>1){
                    double litrosTotal=0, distance, kmpL;
                    distance = dados.get(dados.size()-1).getDistancia() - dados.get(0).getDistancia();

                    for(int i=0; i<dados.size()-1; i++){
                        litrosTotal += dados.get(i).getLitros();
                    }
                    kmpL=distance/litrosTotal;
                    NumberFormat numF = DecimalFormat.getInstance();
                    numF.setMaximumFractionDigits(2);
                    autonomia.setText(numF.format(kmpL));
                }
            }else{
                Toast.makeText(this.getApplicationContext(), getString(R.string.error), Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this.getApplicationContext(),getString(R.string.error), Toast.LENGTH_LONG).show();
        }
    }
}