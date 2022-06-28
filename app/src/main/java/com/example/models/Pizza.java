package com.example.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.dao.Banco;
import com.example.dao.Tabelas;

import java.util.ArrayList;

public class Pizza {
    private int id;
    private String nome;
    private String sabor;
    private int tamanho;
    private int quantidade;
    private double preco;

    public Pizza(int id, String nome, String sabor, int tamanho, int quantidade, double preco) {
        this.id = id;
        this.nome = nome;
        this.sabor = sabor;
        this.tamanho = tamanho;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Pizza() {

    }

    //dao
    public boolean save(Context c) {
        try {
            Banco banco = new Banco(c);
            SQLiteDatabase db = banco.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Tabelas.Pizza.COLUMN_NOME, this.nome);
            values.put(Tabelas.Pizza.COLUMN_SABOR, this.sabor);
            values.put(Tabelas.Pizza.COLUMN_TAMANHO, this.tamanho);
            values.put(Tabelas.Pizza.COLUMN_QUANTIDADE, this.quantidade);
            values.put(Tabelas.Pizza.COLUMN_PRECO, this.preco);
            long newRowId = db.insert(Tabelas.Pizza.TABLE_NAME, null, values);

            db.close();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;
        }

    }

    public Pizza getById(Context c, int id) {
        try {
            Banco banco = new Banco(c);
            SQLiteDatabase db = banco.getReadableDatabase();
            String selection = Tabelas.Pizza._ID +  " = ?";
            String[] selectionArgs = {String.valueOf(id)};
            Cursor cursor = db.query(Tabelas.Pizza.TABLE_NAME, null, selection, selectionArgs, null, null, null);
            while(cursor.moveToNext()){
                int id2 = cursor.getInt(cursor.getColumnIndex(Tabelas.Pizza._ID));
                String nome = cursor.getString(cursor.getColumnIndex(Tabelas.Pizza.COLUMN_NOME));
                String sabor = cursor.getString(cursor.getColumnIndex(Tabelas.Pizza.COLUMN_SABOR));
                int tamanho = cursor.getInt(cursor.getColumnIndex(Tabelas.Pizza.COLUMN_TAMANHO));
                int quantidade = cursor.getInt(cursor.getColumnIndex(Tabelas.Pizza.COLUMN_QUANTIDADE));
                double preco = cursor.getDouble(cursor.getColumnIndex(Tabelas.Pizza.COLUMN_PRECO));
                db.close();
                return new Pizza(id2, nome, sabor, tamanho, quantidade, preco);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;

    }

    public ArrayList<Pizza> getAll(Context c) {
        ArrayList<Pizza> pizzas = new ArrayList<>();
        try {
            Banco banco = new Banco(c);
            SQLiteDatabase db = banco.getReadableDatabase();

            String selection = null;
            String[] selectionArgs = null;
            Cursor cursor = db.query(Tabelas.Pizza.TABLE_NAME, null, selection, selectionArgs, null, null, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Tabelas.Pizza._ID));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(Tabelas.Pizza.COLUMN_NOME));
                String sabor = cursor.getString(cursor.getColumnIndexOrThrow(Tabelas.Pizza.COLUMN_SABOR));
                int tamanho = cursor.getInt(cursor.getColumnIndexOrThrow(Tabelas.Pizza.COLUMN_TAMANHO));
                int quantidade = cursor.getInt(cursor.getColumnIndexOrThrow(Tabelas.Pizza.COLUMN_QUANTIDADE));
                double preco = cursor.getDouble(cursor.getColumnIndexOrThrow(Tabelas.Pizza.COLUMN_PRECO));
                pizzas.add(new Pizza(id, nome, sabor, tamanho, quantidade, preco));
            }
            db.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pizzas;
    }

    public Cursor getAllCursor(Context c) {
        try {
            Banco banco = new Banco(c);
            SQLiteDatabase db = banco.getReadableDatabase();
            String[] campos = {Tabelas.Pizza._ID, Tabelas.Pizza.COLUMN_NOME, Tabelas.Pizza.COLUMN_SABOR, Tabelas.Pizza.COLUMN_QUANTIDADE, Tabelas.Pizza.COLUMN_TAMANHO, Tabelas.Pizza.COLUMN_PRECO};
            String selection = null;
            String[] selectionArgs = null;
            Cursor cursor = db.query(Tabelas.Pizza.TABLE_NAME, campos, selection, selectionArgs, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            // db.close();
            return cursor;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    //get e set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
