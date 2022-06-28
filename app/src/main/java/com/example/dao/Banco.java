package com.example.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Banco  extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Bando.db";



    public Banco(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Tabelas.Usuario.SQL_CREATE);
        sqLiteDatabase.execSQL(Tabelas.Pizza.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Tabelas.Usuario.SQL_DELETE);
        sqLiteDatabase.execSQL(Tabelas.Pizza.SQL_DELETE);
        onCreate(sqLiteDatabase);
    }
}
