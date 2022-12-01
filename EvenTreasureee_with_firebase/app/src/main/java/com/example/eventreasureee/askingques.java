package com.example.eventreasureee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class askingques extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();


        AlertDialog.Builder alertb = new AlertDialog.Builder(getApplicationContext());

        alertb.setMessage("Are you sure you want to cancel ? ")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        startActivity(new Intent(getApplicationContext(),login.class ));


                    }
                })



                .setNegativeButton("No", null);

        AlertDialog al = alertb.create();
        al.show();


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_askingques);


        Button b = findViewById(R.id.button);
        Button b1 = findViewById(R.id.button2);


        b.setOnClickListener(v -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
           startActivity(new Intent(getApplicationContext(), wages_q.class));
        });

        b1.setOnClickListener(v -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                startActivity(new Intent(getApplicationContext(),Saving_Schedular.class));
            }

        });
    }
}