package com.example.eventreasureee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NoConnection extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection);


        Button retry = findViewById(R.id.retrybtn);



        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected(getApplicationContext())) {
                    startActivity(new Intent(getApplicationContext() , NoConnection.class));

                }

            }







            private boolean isConnected(Context applicationContext) {

                ConnectivityManager CM = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo WN = CM.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                NetworkInfo MN = CM.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

                if( (     WN != null && WN.isConnected() || (MN != null && MN.isConnected())    ) )  {

                    startActivity(new Intent(getApplicationContext() , RedirectionPage.class));
                    return true;

                }

                else {
                    return  false;
                }



            }





        });

    }
}