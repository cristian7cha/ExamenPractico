package com.example.examenpractico;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText ed1, ed2;
    Button btnIngresar;
    //Variable para gestionar FirebaseAuth
    private FirebaseAuth mAuth;
    //Agregar cliente de inicio de sesión de Google
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(this);

        // Configurar Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

    }

    public void Ingresar(View l){
        Intent ir = new Intent(this,Home.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TASK | ir.FLAG_ACTIVITY_CLEAR_TOP);
        if(ed1.getText().toString().matches("") || ed2.getText().toString().matches("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Debe diligenciar todos los campos");
            AlertDialog dialog = builder.create();
            dialog.show();
        }else {
            Bundle datos = new Bundle();
            datos.putString("name",ed1.getText().toString());
            ir.putExtras(datos);
            startActivity(ir);
        }
    }


    public  void dataVolley(View l){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://run.mocky.io/v3/45cae298-ed2b-4ef9-bd53-4d08d7c49e40";
        JsonObjectRequest req = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("usuarios");
                            String usuarios = "";
                            for (int i = 0; i < response.getInt("count"); i++) {
                                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                                usuarios += jsonObject2.getString("email") +"\n";
                                usuarios += jsonObject2.getString("contraseña") +"\n";
                                usuarios += jsonObject2.getString("nombre") +"\n";
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };
        queue.add(req);
    }


    @Override
    public void onClick(View view) {
        dataVolley(view);
        Ingresar(view);
    }
}