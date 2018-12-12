package luizz.aula.br.calculo_autonomia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {

    private static final int VERSAO_BD = 1;

    public DbHelper(Context context) {
        super(context, "Banco_de_Registros" , null, DbHelper.VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQLCriaTabelas = "CREATE TABLE registros (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "POSTO INT," +
                "DATA TEXT," +
                "DISTANCIA DOUBLE," +
                "LITROS DOUBLE," +
                "LATITUDE DOUBLE," +
                "LONGITUDE DOUBE" +
                ");";
        db.execSQL(SQLCriaTabelas);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE registros");
        this.onCreate(db);
    }
}
