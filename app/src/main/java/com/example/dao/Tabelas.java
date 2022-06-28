package com.example.dao;

import android.provider.BaseColumns;

public class Tabelas {



    public static class Usuario implements BaseColumns{
        public static final String TABLE_NAME = "usuario";
        public static final String COLUMN_NAME = "nome";
        public static final String COLUMN_SENHA = "senha";
        public static final String COLUM_TIPO = "tipo";

        public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME + " TEXT UNIQUE," +
                COLUMN_SENHA + " TEXT," +
                COLUM_TIPO + " INTEGER)";
        public static final String SQL_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    }

    public static class Pizza implements BaseColumns{
        public static final String TABLE_NAME = "pizzas";
        public static final String COLUMN_NOME = "nome";
        public static final String COLUMN_SABOR = "sabor";
        public static final String COLUMN_TAMANHO = "tamanho";
        public static final String COLUMN_QUANTIDADE = "quantidade";
        public static final String COLUMN_PRECO = "preco";

        public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NOME + " TEXT UNIQUE," +
                COLUMN_SABOR + " TEXT UNIQUE," +
                COLUMN_TAMANHO + " INTEGER, " +
                COLUMN_QUANTIDADE + " INTEGER ," +
                COLUMN_PRECO + " REAL)";
        public static final String SQL_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
