package com.example.eventreasureee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class wages_q extends AppCompatActivity {
    Button next;
    EditText xwages ;
    String ywages ;


    private FirebaseUser user;
    private DatabaseReference Dr;
    private String userID;

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
        setContentView(R.layout.activity_wages_q);

        xwages = findViewById(R.id.A1);
        ywages = "11";
        next = findViewById(R.id.btnnext);


        user = FirebaseAuth.getInstance().getCurrentUser();
        Dr = FirebaseDatabase.getInstance().getReference("Bar");
        userID = user.getUid();


        setListeners();
    }

    private void setListeners() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(xwages.getText())){
                    xwages.setError("cost is required");
                    return;
                }


                DatabaseReference add = Dr.child(userID);

                Float x=Float.parseFloat(xwages.getText().toString());
                int y=Integer.parseInt(ywages);
                String u = "xwages";
                String u1 = "ywages";


                DatabaseReference add1 = add.child(u);
                DatabaseReference add2 = add.child(u1);
                add1.setValue(x);
                add2.setValue(y);

                startActivity(new Intent(getApplicationContext(), entertainment_q.class));


            }
        });
    }
}























