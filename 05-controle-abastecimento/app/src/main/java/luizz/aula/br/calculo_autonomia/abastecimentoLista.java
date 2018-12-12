package luizz.aula.br.calculo_autonomia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public class abastecimentoLista extends AppCompatActivity {

   private abastecimentoAdapter adaptador;
   private final int codigo=2409;
   private boolean permissao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaabastecimento);

        this.permissao = this.getIntent().getBooleanExtra("permissao", false);

        RecyclerView rvLista = findViewById(R.id.recicleList);

       this.adaptador = new abastecimentoAdapter();
       this.adaptador.lista = abastecimentoDAO.carrega_Lista(this.getApplicationContext());

       rvLista.setAdapter(this.adaptador);
       rvLista.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
    }

    public void onclickadd(View v){
        Intent trocar_act = new Intent(this.getApplicationContext(), abastecimentoDados.class);
        if(this.adaptador.lista.size()>0){
            trocar_act.putExtra("kmAntigo", this.adaptador.lista.get(this.adaptador.lista.size()-1).getDistancia());
        }
        trocar_act.putExtra("permissao", permissao);
        startActivityForResult(trocar_act, codigo);
    }

    public void onClickMap(View v){

        if (permissao){
            Intent trocar_act = new Intent(this.getApplicationContext(), Lista_de_Registros_Mapa.class);
            this.startActivity(trocar_act);

        }else{
            Toast.makeText(this.getApplicationContext(), getString(R.string.GPSdisable), Toast.LENGTH_LONG).show();
            return;
        }
    }

    public  void onclickVoltar(View v){
        setResult(1);
        finish();
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==codigo) {
            if (resultCode == 1) {
                  this.adaptador.notifyDataSetChanged();
            }else{
                Toast.makeText(this.getApplicationContext(), getString(R.string.errorNot), Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this.getApplicationContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
        }
    }
}
