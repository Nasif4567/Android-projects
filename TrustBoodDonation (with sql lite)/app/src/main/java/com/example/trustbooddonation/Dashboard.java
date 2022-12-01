package com.example.trustbooddonation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    ImageButton appreg,cusreg,manageapp,managecus,logout;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Dashboard.this,Dashboard.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        appreg = findViewById(R.id.appreg);
        cusreg = findViewById(R.id.cusreg);
        manageapp = findViewById(R.id.manageapp);
        managecus = findViewById(R.id.managecus);
        logout = findViewById(R.id.logout);


        cusreg.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),CusReg.class));
        });


        appreg.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),AppReg.class));
        });



        managecus.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),ManageCustomer.class));
        });


        manageapp.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),ManageAppoinment.class));
        });


        logout.setOnClickListener(v -> {

            AlertDialog.Builder alertb = new AlertDialog.Builder(Dashboard.this);

            alertb.setMessage("Are you sure you want to Logout ?")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }
                    })

                    .setNegativeButton("No", null);


            AlertDialog al = alertb.create();
            al.show();
            //--------------------------------------------------




        });



    }
}