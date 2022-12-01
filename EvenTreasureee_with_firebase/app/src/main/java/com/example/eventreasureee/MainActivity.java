package com.example.eventreasureee;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
   private static int SPASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

       new Handler().postDelayed(new Runnable(){
           @Override
           public void run() {


               //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
               //if (user != null) {
                   // User is signed in
                   Intent i = new Intent(getApplicationContext(), RedirectionPage.class);
                   startActivity(i);


           }


       },SPASH_TIME_OUT);
    }
}