package com.example.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.dao.Banco;
import com.example.dao.Tabelas;

public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private int tipo;

    //construtores
    public Usuario(int id, String nome, String senha, int tipo) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
    }


    //funções
    //dao
    public boolean save(Context c) {
        try {
            Banco banco = new Banco(c);
            SQLiteDatabase db = banco.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Tabelas.Usuario.COLUMN_NAME, this.nome);
            values.put(Tabelas.Usuario.COLUMN_SENHA, this.senha);
            values.put(Tabelas.Usuario.COLUM_TIPO, this.tipo);
            long newRowId = db.insert(Tabelas.Usuario.TABLE_NAME, null, values);

            db.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public Usuario auth(Context c) {
        Banco banco = new Banco(c);
        SQLiteDatabase db = banco.getWritableDatabase();
        String[] projection = null;
        String selection = Tabelas.Usuario.COLUMN_NAME + "=? AND " + Tabelas.Usuario.COLUMN_SENHA + "= ?";
        String[] selectionArgs = {this.nome, this.senha};
        Cursor cursor = db.query(Tabelas.Usuario.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        Usuario u = null;
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(Tabelas.Usuario._ID));
            String nome = cursor.getString(cursor.getColumnIndexOrThrow(Tabelas.Usuario.COLUMN_NAME));
            String senha = cursor.getString(cursor.getColumnIndexOrThrow(Tabelas.Usuario.COLUMN_SENHA));
            int tipo = cursor.getInt(cursor.getColumnIndexOrThrow(Tabelas.Usuario.COLUM_TIPO));

            u = new Usuario(id, nome, senha, tipo);
        }
        if(u != null){
            Singleton.getInstance().setUsuarioLogado(u);
            Toast.makeText(c, "Usuario autorizado", Toast.LENGTH_SHORT).show();
            return u;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
