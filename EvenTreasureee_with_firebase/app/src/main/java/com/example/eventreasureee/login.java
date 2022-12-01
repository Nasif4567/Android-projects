package com.example.eventreasureee;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(getApplicationContext(), RedirectionPage.class));


    }

    EditText Lemail, Lpassword;
    Button LLogin;

    FirebaseAuth fauth;
    ProgressBar progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Lemail = findViewById(R.id.usertxt);
        Lpassword = findViewById(R.id.passtxt);
        LLogin = findViewById(R.id.logbtn);
        progressbar = findViewById(R.id.pgbar);


        Button signuptxt = findViewById(R.id.signuptxt);


        fauth = FirebaseAuth.getInstance();


        LLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = Lemail.getText().toString().trim();
                String pass = Lpassword.getText().toString().trim();

                if (TextUtils.isEmpty(user)) {
                    Lemail.setError("username is required");
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    Lpassword.setError("password is required");
                    return;
                }

                progressbar.setVisibility(View.VISIBLE);

                fauth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {

                            Intent i = new Intent(getApplicationContext(), Saving_Schedular.class);
                            startActivity(i);
                            progressbar.setVisibility(View.INVISIBLE);
                        } else {
                            Toast.makeText(login.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressbar.setVisibility(View.INVISIBLE);
                        }


                    }
                });


            }
        });


        signuptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Registration.class));

            }
        });


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        if (user != null) {
            // User is signed in
            Intent i = new Intent(getApplicationContext(), Saving_Schedular.class);
            startActivity(i);
        }





    }

}

