package com.android.joaocdecastilho.championslol.configBanco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper
{
    public static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "champions";
    public static final String ID = "_id";
    public static final String KEY = "key";
    public static final String NAME = "name";
    public static final String TITLE = "title";
    public static final String TAGS = "tags";
    public static final String HP = "hp";
    public static final String MP = "mp";
    public static final String ICON = "icon";
    public static final String DESCRIPTION = "description";
    public static final int VERSAO = 4;


    public CriaBanco(Context context)
    {
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE " + TABELA + "("
                     + ID + " INTEGER primary key autoincrement NOT NULL,"
                     + KEY + " text,"
                     + NAME + " text,"
                     + TITLE + " text,"
                     + TAGS + " text,"
                     + HP + " real,"
                     + MP + " real,"
                     + ICON + " text,"
                     + DESCRIPTION + " text"
                     + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
