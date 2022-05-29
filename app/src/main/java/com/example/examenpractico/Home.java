package com.example.examenpractico;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
