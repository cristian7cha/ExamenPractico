package com.example.examenpractico;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class UtilsNetwork {
    public static boolean isOnline(Home context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        if(cm != null){
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if(ni != null){
                return ni.isConnected();
            }
        }
        return false;
    }
}
