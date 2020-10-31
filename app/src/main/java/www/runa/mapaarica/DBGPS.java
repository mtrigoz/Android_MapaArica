package www.runa.mapaarica;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBGPS extends SQLiteOpenHelper {

    public static final String db_GPS_ARICA="GPSARICA.db";
    public static final String table_GPS_ARICA="GPSARICA_table";

    public String col  = "id";
    public String col2 = "Longitud";
    public String col3 = "Latitud";

    public DBGPS(@Nullable Context context){
        super(context, db_GPS_ARICA, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+table_GPS_ARICA+"(ID INTEGER PRIMARY KEY, Longitud TEXT, Latitud TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    ///////////////////////////////////////
    public  boolean AgregarDatos(String Longitud,String Latitud) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2, Longitud);
        contentValues.put(col3, Latitud);

        long resultado = db.insert(table_GPS_ARICA, null, contentValues);

        if (resultado == -1) {
            return false;
        } else {
            return true;
        }
    }
    ///////////////////////////////////////
    public Cursor verDatos(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor respuesta = database.rawQuery("select * from "+table_GPS_ARICA,null);
        return respuesta;
    }

    ///////////////////////////////////////
    public  Integer EliminarDatos(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        return  database.delete(table_GPS_ARICA,"id = ?", new String[]{id});
    }
    ///////////////////////////////////////
    public boolean ActualizarDatos(String id, String Longitud,String Latitud){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col,id);
        contentValues.put(col2, Longitud);
        contentValues.put(col3, Latitud);

        sqLiteDatabase.update(table_GPS_ARICA,contentValues,"id = ?", new String[]{id});
        return true;
    }

    }
