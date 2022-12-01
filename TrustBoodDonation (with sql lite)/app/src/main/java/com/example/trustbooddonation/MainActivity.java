package com.example.trustbooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userid, pass;
    Button login;
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userid = findViewById(R.id.userid);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        DB = new DbHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userid.getText().equals("")) {
                    userid.setError("Please enter usernamm");
                }

                if (pass.getText().equals("")) {
                    pass.setError("Please enter password");
                }

                Boolean checkuserpass = DB.checkuserpass(userid.getText().toString(),pass.getText().toString());

                if(checkuserpass == true) {
                    Toast.makeText(MainActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Dashboard.class));
                }

                else {
                    Toast.makeText(MainActivity.this, "Login error : Invalid username or password", Toast.LENGTH_SHORT).show();
                }






            }
        });


       //------------------------------------------------------



    }
}