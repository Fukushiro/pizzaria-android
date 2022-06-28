package com.example.pizzaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.dao.Tabelas;
import com.example.models.Pizza;

import java.util.ArrayList;

public class ClienteListaPizza extends AppCompatActivity {
    private ListView lista;
    private Button btnComprar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_lista_pizza);
        //componentes
        this.lista = (ListView) findViewById(R.id.tela4Lista);
        this.btnComprar = (Button) findViewById(R.id.tela4BtnComprar);

        //config lista
        final Cursor pizzas = new Pizza().getAllCursor(getApplicationContext());
        String[] nomeCampos = new String[]{
                Tabelas.Pizza._ID, Tabelas.Pizza.COLUMN_NOME, Tabelas.Pizza.COLUMN_SABOR, Tabelas.Pizza.COLUMN_QUANTIDADE, Tabelas.Pizza.COLUMN_TAMANHO, Tabelas.Pizza.COLUMN_PRECO
        };
        int[] idViews = new int[]{R.id.tabelaPizzaId, R.id.tabelaPizzaNome, R.id.tabelaPizzaSabor, R.id.tabelaPizzaQuantidade, R.id.tabelaPizzaTamanho, R.id.tabelaPizzaPreco};


        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getBaseContext(),
                R.layout.table_pizza,
                pizzas,
                nomeCampos,
                idViews,
                0
        );
        this.lista.setAdapter(adapter);

        this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pizzas.moveToPosition(i);
                int id = Integer.valueOf(pizzas.getString(pizzas.getColumnIndexOrThrow(Tabelas.Pizza._ID)));
                Intent intent = new Intent(ClienteListaPizza.this, ClienteComprar.class);
                //Toast.makeText(getApplicationContext(), id+"", Toast.LENGTH_SHORT).show();
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }
}