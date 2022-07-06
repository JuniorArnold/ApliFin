package com.upn.final_app.util;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {

    public BaseDatos(Context context){
        super(context, "bien.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE bienes "+
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "nombre TEXT NOT NULL, "+
                        "codigo TEXT NOT NULL, "+
                        "vida INTEGER NOT NULL, "+
                        "estado TEXT NOT NULL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
