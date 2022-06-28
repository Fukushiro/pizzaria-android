package com.example.pizzaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.models.Pizza;

public class ClienteComprar extends AppCompatActivity {
    private TextView lblNome, lblSabor, lblTamanho, lblQuantidade, lblPrecoUnitario;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_comprar);
        Bundle b = getIntent().getExtras();
        int id = -1;
        if (b != null) {
            id = b.getInt("id");
        }
        //componentes
        this.lblNome = (TextView) findViewById(R.id.tela5NomeLbl);
        this.lblSabor = (TextView) findViewById(R.id.tela5SaborLbl);
        this.lblTamanho = (TextView) findViewById(R.id.tela5LblTamanho);
        this.lblQuantidade = (TextView) findViewById(R.id.tela5QuantidadeLbl);
        this.lblPrecoUnitario = (TextView) findViewById(R.id.tela5PrecoUnitarioLbl);

        this.btnCadastrar = (Button) findViewById(R.id.tela5CadastrarBtn);

        final Pizza pizza = new Pizza().getById(getApplicationContext(), id);
        System.out.println(pizza);
        if (pizza != null) {
            this.lblNome.setText(pizza.getNome());
            this.lblSabor.setText(pizza.getSabor());
            this.lblTamanho.setText(String.valueOf(pizza.getTamanho()));
            this.lblQuantidade.setText(String.valueOf(pizza.getQuantidade()));
            this.lblPrecoUnitario.setText(String.valueOf(pizza.getPreco()));
        }

        this.btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg= "Nome="+ pizza.getNome()+
                        "\nSabor="+pizza.getSabor()+
                        "\nTamanho="+pizza.getTamanho()+
                        "\nQuantidade="+1+
                        "\nPreco="+pizza.getPreco();
                enviar("5532998360917",msg);
            }
        });

    }


    public void enviar(String number, String msg){
        Intent i = new Intent(Intent.ACTION_VIEW);
        msg.replace(" ", "%20");
        i.setData(Uri.parse("https://api.whatsapp.com/send?phone="+ number+"&text="+msg));
        startActivity(i);
    }



}