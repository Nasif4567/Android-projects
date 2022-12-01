package com.example.eventreasureee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RedirectionPage extends AppCompatActivity {

    public FirebaseUser user111 = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirection_page);


        Button Signinr = findViewById(R.id.signin);
        Button Signupr = findViewById(R.id.signup);





        if(!isConnected(getApplicationContext())) {
            startActivity(new Intent(getApplicationContext() , NoConnection.class));

        }


        Signinr.setOnClickListener(v -> {


            if (user111 == null) {
                startActivity(new Intent(getApplicationContext(), login.class));
            }

        });


        Signupr.setOnClickListener(v -> {

            startActivity(new Intent(getApplicationContext(), Registration.class));
        });


        


        if (user111 != null) {
            startActivity(new Intent(getApplicationContext(),Saving_Schedular.class));
        }



    }

    private boolean isConnected(Context applicationContext) {

        ConnectivityManager CM = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo WN = CM.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo MN = CM.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if( (     WN != null && WN.isConnected() || (MN != null && MN.isConnected())    ) )  {

            return true;

        }

        else {
            return  false;
        }



        
    }
}