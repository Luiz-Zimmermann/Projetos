package luizz.aula.br.calculo_autonomia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class abastecimentoDAO {

    private static ArrayList<abastecimentoInfo> Cache = new ArrayList<abastecimentoInfo>();


    //salva o dado no Banco e na Cache
    public static boolean salvar(Context c, abastecimentoInfo itemSave){
        DbHelper DataBase = new DbHelper(c);
        SQLiteDatabase db = DataBase.getWritableDatabase();

        ContentValues chave = new ContentValues();
        chave.put("posto", itemSave.getPosto());
        chave.put("data", itemSave.getData());
        chave.put("distancia", itemSave.getDistancia());
        chave.put("litros", itemSave.getLitros());
        chave.put("latitude", itemSave.getLatitude());
        chave.put("longitude", itemSave.getLongitude());

        long id = db.insert("registros", null, chave);
        itemSave.setId(id);
        Cache.add(itemSave);
        return true;
    }
    //carrega a cache com a lista de itens
    public static  ArrayList<abastecimentoInfo> carrega_Lista(Context c){
        Cache = new ArrayList<abastecimentoInfo>();

        DbHelper DataBase = new DbHelper(c);
        SQLiteDatabase db = DataBase.getReadableDatabase();

        String SQLBuscaRegistros = "SELECT posto, data, distancia, litros, latitude, longitude, id FROM registros";
        Cursor ponteiro = db.rawQuery(SQLBuscaRegistros, null);

        while (ponteiro.moveToNext()){
            abastecimentoInfo item = new abastecimentoInfo();
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
