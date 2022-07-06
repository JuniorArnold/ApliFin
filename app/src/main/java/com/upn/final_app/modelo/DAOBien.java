package com.upn.final_app.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.upn.final_app.entidad.Bien;
import com.upn.final_app.util.BaseDatos;

public class DAOBien {
    BaseDatos base;
    SQLiteDatabase db;
    Context context;

    public  DAOBien(Context context){
        this.base = new BaseDatos(context);
        this.context = context;
    }

    public void abrirBD(){
        db = base.getWritableDatabase();
    }

    public String registrarBien(Bien bien){
        String rpta = "";
        try{
            ContentValues valores = new ContentValues();
            valores.put("nombre", bien.getNombre());
            valores.put("codigo", bien.getCodPat());
            valores.put("vida", bien.getVida());
            valores.put("estado", bien.getEstado());

            long resultado  = db.insert("bienes", null, valores);
            if (resultado == -1){
                rpta = "Error al registrar";
            }
            else {
                rpta = "se registro correctamente";
            }

        }catch (Exception e){
            Log.d("===>", e.toString());
        }


        return rpta;
    }
}
