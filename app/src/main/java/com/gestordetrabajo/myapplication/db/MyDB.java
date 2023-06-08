package com.gestordetrabajo.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "miDatabase.db";
    public static final int DB_VERSION = 1;
    public static final String DB_TABLE_NAME = "Evento_Table";

    public static final String COL_1 = "id";
    public static final String COL_2 = "Nombre";

    public static final String COL_3 = "Hora";
    public static final String COL_4 = "Fecha";

    Context context;
    public MyDB(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_CREATE =
                "CREATE TABLE "
                        + DB_TABLE_NAME+ "(" + COL_1 +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COL_2 + "TEXT," +  COL_3 +" TEXT,"+ COL_4 +"TEXT ); ";
        sqLiteDatabase.execSQL(SQL_CREATE);
    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String SQL_DELETE = "DROP TABLE IF EXISTS " + DB_TABLE_NAME ;
        sqLiteDatabase.execSQL(SQL_DELETE);
        onCreate(sqLiteDatabase);
    }


    public void insertData(String nombre, String hora, String fecha){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, nombre);
        values.put(COL_3, hora);
        values.put(COL_4, fecha);

        //Inserta una nueva fila
        long result = database.insert(DB_TABLE_NAME, null,values);
        if (result == -1) {
            Toast.makeText(context, "los datos no se han podido insertar", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context, "los datos se han insertado satisfactoriamente", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateData(){

    }
    public void deleteData(){

    }
    public Cursor readData(){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + DB_TABLE_NAME;
        return database.rawQuery(query,null);
    }

}
