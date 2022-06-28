package com.example.pizzaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.models.Pizza;

public class AdminCadastrarPizza extends AppCompatActivity {
    private Button btnCadastrar, btnVoltar;
    private EditText txtNome, txtQuantidade, txtPreco, txtTamanho, txtSabor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cadastrar_pizza);
        //componentes
        this.btnCadastrar = (Button) findViewById(R.id.tela3CadastrarBtn);
        this.btnVoltar = (Button) findViewById(R.id.tela3VoltarBtn);
        this.txtNome = (EditText) findViewById(R.id.tela3NomeTxt);
        this.txtQuantidade = (EditText) findViewById(R.id.tela3QuantidadeTxt);
        this.txtPreco = (EditText) findViewById(R.id.tela3PrecoTxt);
        this.txtTamanho = (EditText) findViewById(R.id.tela3TamanhoTxt);
        this.txtSabor = (EditText) findViewById(R.id.tela3SaborTxt);
        //btn ação
        this.btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String nome = txtNome.getText().toString();
                    int quantidade = Integer.valueOf(txtQuantidade.getText().toString());
                    double preco = Double.valueOf(txtPreco.getText().toString());
                    int tamanho = Integer.valueOf(txtTamanho.getText().toString());
                    String sabor = txtSabor.getText().toString();
                    Pizza p = new Pizza(0, nome, sabor, tamanho, quantidade, preco);
                    p.save(getApplicationContext());
                    txtNome.setText("");
                    txtQuantidade.setText("");
                    txtPreco.setText("");
                    txtTamanho.setText("");
                    txtSabor.setText("");
                } catch (NumberFormatException e) {

                }

            }
        });

    }
}