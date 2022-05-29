package com.example.examenpractico;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    private CheckBox verdadero1, verdadero2, falso1, falso2;
    private Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        verdadero1 = findViewById(R.id.boxRespuesta1);
        verdadero2 = findViewById(R.id.boxRespuesta2);
        falso1 = findViewById(R.id.boxFalso1);
        falso2 = findViewById(R.id.boxFalso2);

        siguiente = findViewById(R.id.btnSiguiente);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verdadero1.isChecked() && verdadero2.isChecked() && !falso1.isChecked() && !falso2.isChecked()){
                    Intent i = new Intent(Home.this, Final.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Respuesta incorrecta, vuelve a intentar", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void atras(View l) {
        Intent ir = new Intent(this, MainActivity.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TASK | ir.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(ir);
    }

    public void iniciarServicio(View m){
        //Intent irService = new Intent(this, MyService.class);
        //startService(irService);
        if(UtilsNetwork.isOnline(this)){
            Toast.makeText(getApplicationContext(),"Tiene internet ", Toast.LENGTH_SHORT).show();
        }else{
            Intent ir = new Intent(this, MainActivity.class);
            ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TASK | ir.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(ir);
        }
    }
}
