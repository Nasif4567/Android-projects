package com.example.eventreasureee;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Saving_Schedular extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving__schedular);

        if(!isConnected(getApplicationContext())) {
            startActivity(new Intent(getApplicationContext() , NoConnection.class));

        }


        settings_fragment swt = new settings_fragment();
        int stof = swt.colourchange;

        BottomNavigationView botomnav = findViewById(R.id.navbar);
        botomnav.setOnNavigationItemSelectedListener(navlistener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conatiner,

        new com.example.eventreasureee.home_fragment()).commit();
    }


    //--------------------------------------connection alert-----------------------------

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


    //--------------------------------------------------------------------


    private BottomNavigationView.OnNavigationItemSelectedListener navlistener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;

                        switch (item.getItemId()) {

                            case R.id.nav_home:
                                selectedFragment = new com.example.eventreasureee.home_fragment();
                                break;
                            case R.id.nav_profile:
                                selectedFragment = new com.example.eventreasureee.ProfileDetails();
                                break;

                                case R.id.nav_setting:
                                selectedFragment = new com.example.eventreasureee.settings_fragment();
                                break;

                            case R.id.nav_money:
                                selectedFragment = new com.example.eventreasureee.money_fragment();
                                break;

                        }

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conatiner,
                                selectedFragment).commit();
                        return true;
                    }
                };



    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }




    }
