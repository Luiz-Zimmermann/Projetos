package luizz.aula.br.calculo_autonomia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//classe que ira mostrar os conteudos da lista
public class gaveta_itens extends RecyclerView.ViewHolder {

    private TextView  tvdata, tvfuel, tvkm;
    private ImageView posto;

    private  Info_List_Item itemLook;

    public gaveta_itens(@NonNull View itemView) {
        super(itemView);

        itemView.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v){
                Intent openActiviy = new Intent(v.getContext(), item_detalhado.class);
                openActiviy.putExtra("registro", gaveta_itens.this.itemLook);
                ((list_View) v.getContext()).startActivity(openActiviy);
        }
    });

        this.tvkm = itemView.findViewById(R.id.distance_view);
        this.tvdata = itemView.findViewById(R.id.date_view);
        this.posto = itemView.findViewById(R.id.image_posto);
        this.tvfuel = itemView.findViewById(R.id.liters_view);
    }

    public void atualiza_ItemLista(Info_List_Item item){

        itemLook= item;

        this.tvkm.setText("Km: "+item.getDistancia());
        this.tvdata.setText(item.getData());
        this.tvfuel.setText(item.getLitros()+"L");

        if(item.getPosto()==0){
            this.posto.setImageResource(R.drawable.petro);
        }else if(item.getPosto()==1){
            this.posto.setImageResource(R.drawable.ipi);
        }else if(item.getPosto()==2){
            this.posto.setImageResource(R.drawable.shell);
        }else if(item.getPosto()==3){
            this.posto.setImageResource(R.drawable.texaco);
        }else{
        }
    }
}
