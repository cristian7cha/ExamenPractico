package com.example.examenpractico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class Final extends AppCompatActivity {

    private RadioButton respuesta;
    private Button finalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        respuesta = findViewById(R.id.radioRespuesta);
        finalizar = findViewById(R.id.btnFinalizar);

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mensaje = "";
                if (respuesta.isChecked()){
                    Intent i = new Intent(Final.this, MainActivity.class);
                    startActivity(i);
                    mensaje = "Finalizaste exitosamente el cuestionario, Hasta pronto";
                }else{
                    mensaje = "Respuesta incorrecta, vuelve a intentar";
                }
                Toast.makeText(getApplicationContext(),mensaje, Toast.LENGTH_SHORT).show();

            }
        });
    }
}