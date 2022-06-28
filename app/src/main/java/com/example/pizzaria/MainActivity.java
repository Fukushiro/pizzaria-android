package com.example.pizzaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.models.NotificationManagerM;
import com.example.models.Usuario;

public class MainActivity extends AppCompatActivity {
    private EditText txtNome;
    private EditText txtSenha;
    private Button btnLogar;
    private Button btnCadastrar;
    public static NotificationManagerM nM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         nM = new NotificationManagerM();
        //pegar componentes
        this.txtNome = (EditText) findViewById(R.id.tela1TxtNome);
        this.txtSenha = (EditText) findViewById(R.id.tela1TxtSenha);
        this.btnLogar = (Button) findViewById(R.id.tela1BtnLogar);
        this.btnCadastrar = (Button) findViewById(R.id.tela1BtnCadastrar);
        nM.createNotificationChannel(this);

        //ação dos botões
        this.btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = txtNome.getText().toString();
                String senha = txtSenha.getText().toString();
                Usuario u = new Usuario(0, nome, senha, 0);
                Usuario authUser = u.auth(getApplicationContext());

                if(authUser != null){

                    if(authUser.getTipo() == 0){

                        Intent i = new Intent(MainActivity.this, MainCliente.class);
                        startActivity(i);
                    }else if(authUser.getTipo() == 1){
                        Intent i = new Intent(MainActivity.this, MainAdmin.class);
                        startActivity(i);
                    }
                }
            }
        });
        this.btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TelaCadastro.class);
                startActivity(i);
                finish();
            }
        });
    }
}