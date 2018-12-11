package luizz.aula.br.calculo_autonomia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class Info_ListDAO {

    private static ArrayList<Info_List_Item> Cache = new ArrayList<Info_List_Item>();


    //salva o dado no Banco e na Cache
    public static boolean salvar(Context c, Info_List_Item itemSave){
        DbHelper DataBase = new DbHelper(c);
        SQLiteDatabase db = DataBase.getWritableDatabase();

        ContentValues chave = new ContentValues();
        chave.put("posto", itemSave.getPosto());
        chave.put("data", itemSave.getData());
        chave.put("distancia", itemSave.getDistancia());
        chave.put("litros", itemSave.getLitros());
        chave.put("latitude", itemSave.getLatitude());
        chave.put("longitude", itemSave.getLongitude());

        long id = db.insert("cadastros", null, chave);
        itemSave.setId(id);
        Cache.add(itemSave);
        return true;
    }
    //carrega a cache com a lista de itens
    public static  ArrayList<Info_List_Item> carrega_Lista(Context c){
        Cache = new ArrayList<Info_List_Item>();

        DbHelper DataBase = new DbHelper(c);
        SQLiteDatabase db = DataBase.getReadableDatabase();

        String SQLBuscaRegistros = "SELECT posto, data, distancia, litros, latitude, longitude, id FROM cadastros";
        Cursor ponteiro = db.rawQuery(SQLBuscaRegistros, null);

        while (ponteiro.moveToNext()){
            Info_List_Item item = new Info_List_Item();
            item.setPosto(ponteiro.getInt(0));
            item.setData(ponteiro.getString(1));
            item.setDistancia(ponteiro.getDouble(2));
            item.setLitros(ponteiro.getDouble(3));
            item.setLatitude(ponteiro.getDouble(4));
            item.setLongitude(ponteiro.getDouble(5));
            item.setId(ponteiro.getLong(6));
            Cache.add(item);
        }

        return Cache;
    }

}
