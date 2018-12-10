package luizz.aula.br.calculo_autonomia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class list_View extends AppCompatActivity {

   private adapter_item adaptador;
   private final int codigo=2409;
   private boolean permissao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__view);

        this.permissao = this.getIntent().getBooleanExtra("permissao", false);

        RecyclerView rvLista = findViewById(R.id.recicleList);

       this.adaptador = new adapter_item();
       this.adaptador.lista = Info_ListDAO.carrega_Lista(this.getApplicationContext());

       rvLista.setAdapter(this.adaptador);
       rvLista.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
    }

    public void onclickadd(View v){
        Intent trocar_act = new Intent(this.getApplicationContext(), Adicionar_dados.class);
        if(this.adaptador.lista.size()>0){
            trocar_act.putExtra("kmAntigo", this.adaptador.lista.get(this.adaptador.lista.size()-1).getDistancia());
        }
        trocar_act.putExtra("permissao", permissao);
        startActivityForResult(trocar_act, codigo);
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
