package luizz.aula.br.calculo_autonomia;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class adapter_item extends RecyclerView.Adapter {

    public ArrayList<Info_List_Item> lista;

    //infla o item
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View elementoPai = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item,parent,false);
        gaveta_itens minhaGaveta = new gaveta_itens(elementoPai);
        return minhaGaveta;
    }

    //manda atualizar os itens da lista
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

        gaveta_itens minhaGaveta = (gaveta_itens) holder;
        Info_List_Item daVez = lista.get(i);
        minhaGaveta.atualiza_ItemLista( daVez );

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}