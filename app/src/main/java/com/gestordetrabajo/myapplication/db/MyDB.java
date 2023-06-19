package com.gestordetrabajo.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDB extends SQLiteOpenHelper {





        private static final String EVENTS_TABLE_CREATE = "CREATE TABLE events(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, hora TEXT, fecha TEXT)";
    private static final String INDICATOR_TABLE_CREATE = "CREATE TABLE events(_id INTEGER PRIMARY KEY AUTOINCREMENT, fecha TEXT)";
        private static final String DB_NAME = "events.sqlite";
        private static final int DB_VERSION = 1;
        private static final String NOMBRE_TABLE_EVENTS = "events";
        private static final String NOMBRE_TABALE_INDICATOR = "indicator";

        public MyDB(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(EVENTS_TABLE_CREATE);
            db.execSQL(INDICATOR_TABLE_CREATE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    public long insert(String nombre, String hora, String fecha){

        ContentValues cv = new ContentValues();
        cv.put("nombre", nombre);
        cv.put("hora", hora);
        cv.put("fecha", fecha);
        SQLiteDatabase db = this.getWritableDatabase();


        return db.insert("events", null, cv);
    }

    public void updateData(){

    }
    public void deleteData(String id){
            SQLiteDatabase database = this.getWritableDatabase();
            database.delete("events", "_id = ?", new String[]{id});



    }
    public Cursor readData(){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + NOMBRE_TABLE_EVENTS;
        return database.rawQuery(query,null);
    }

}
