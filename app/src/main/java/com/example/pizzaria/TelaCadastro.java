package com.example.pizzaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.models.NotificationManagerM;
import com.example.models.Usuario;

public class TelaCadastro extends AppCompatActivity {
    private EditText txtNome;
    private EditText txtSenha;
    private Button btnCadastrar;
    private Spinner spinner;
    private Button btnVoltar;
    private NotificationManagerM nM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        //pegar componentes
        this.txtNome = (EditText)findViewById(R.id.tela2TxtNome);
        this.txtSenha = (EditText)findViewById(R.id.tela2TxtSenha);
        this.btnCadastrar = (Button)findViewById(R.id.tela2BtnCadastrar);
        this.spinner = (Spinner) findViewById(R.id.tela2Spinner);
        this.btnVoltar = (Button) findViewById(R.id.tela2BtnVoltar);
        //ação do botão
        this.btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.nM.send(getApplicationContext(), R.drawable.ic_launcher_background, "Usuario cadastrado", "titulo");
                String nome = txtNome.getText().toString();
                String senha = txtSenha.getText().toString();
                int tipo = spinner.getSelectedItemPosition();

                Usuario u = new Usuario(0, nome, senha, tipo);
                u.save(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Usuario cadastrado", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(TelaCadastro.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });

        this.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TelaCadastro.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}