package com.example.examenpractico;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MostrarHora obj = new MostrarHora();
        obj.execute();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public  class  MostrarHora extends AsyncTask<Void,Integer,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 10; i++) {
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Toast.makeText(getApplicationContext(),"hola servicio: "+values[0], Toast.LENGTH_SHORT).show();
        }
    }



}