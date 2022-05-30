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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {

    private CheckBox verdadero1, verdadero2, falso1, falso2;
    private Button siguiente;
    //Variable para gestionar FirebaseAuth
    private FirebaseAuth mAuth;
    //Variables opcionales para desloguear de google tambien
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;


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
        mAuth = FirebaseAuth.getInstance();

        //Configurar las gso para google signIn con el fin de luego desloguear de google
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    public void cerrarSesion(View l) {
        mAuth.signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //Abrir MainActivity con SigIn button
                if (task.isSuccessful()) {
                    Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainActivity);
                    Home.this.finish();
                } else {
                    Toast.makeText(getApplicationContext(), "No se pudo cerrar sesión con google",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    public void iniciarServicio(View m){
        //Intent irService = new Intent(this, MyService.class);
        //startService(irService);
        if(UtilsNetwork.isOnline(this)){
            Toast.makeText(getApplicationContext(),"Tiene internet ", Toast.LENGTH_SHORT).show();
        }else{
            cerrarSesion(m);
        }
    }
}
