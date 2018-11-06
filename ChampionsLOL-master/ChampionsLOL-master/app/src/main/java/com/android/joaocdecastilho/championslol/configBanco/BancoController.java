package com.android.joaocdecastilho.championslol.configBanco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;


import com.android.joaocdecastilho.championslol.DetalheChampionActivity;
import com.android.joaocdecastilho.championslol.models.Champion;

public class BancoController {

    private SQLiteDatabase db;
    private CriaBanco banco;


    public BancoController(Context context){
        banco = new CriaBanco(context);
    }

    public String insereDados(String key,String name,String title,String tags,double hp,double mp,String icon,String description){

        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.KEY,key);
        valores.put(CriaBanco.NAME,name);
        valores.put(CriaBanco.TITLE,title);
        valores.put(CriaBanco.TAGS,tags);
        valores.put(CriaBanco.HP,hp);
        valores.put(CriaBanco.MP,mp);
        valores.put(CriaBanco.ICON,icon);
        valores.put(CriaBanco.DESCRIPTION,description);

        resultado = db.insert(CriaBanco.TABELA,null,valores);

        db.close();

        if(resultado == -1){
            return "Erro ao inserir registro";
        }else{
            return "Registro inserido com sucesso";
        }
    }


    public Cursor carregarDados(){

        Cursor cursor;
        String[] campos = {banco.ID,banco.ICON,banco.NAME,banco.TITLE,banco.KEY,banco.TAGS,banco.HP,banco.MP,banco.DESCRIPTION};
        db = banco.getReadableDatabase();
        cursor = db.query(true,banco.TABELA,campos,null,null,banco.NAME,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public void deletarRegistro(int id){

        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(CriaBanco.TABELA,where,null);
        db.close();

    }


}
