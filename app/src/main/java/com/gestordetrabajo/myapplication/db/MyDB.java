package com.gestordetrabajo.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "miDatabase.db";
    public static final int DB_VERSION = 1;
    public static final String DB_TABLE_NAME = "Evento_Table";

    public static final String COL_1 = "id";
    public static final String COL_2 = "Nombre";
    public static final String COL_3 = "Descripcion";
    public static final String COL_4 = "Sesion";
    public static final String COL_5 = "Hora";

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
                        + COL_2 + "TEXT," + COL_3 +" TEXT, " + COL_4 + " TEXT, " + COL_5 +" TEXT ); ";
        sqLiteDatabase.execSQL(SQL_CREATE);
    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String SQL_DELETE = "DROP TABLE IF EXISTS " + DB_TABLE_NAME ;
        sqLiteDatabase.execSQL(SQL_DELETE);
        onCreate(sqLiteDatabase);
    }
}
