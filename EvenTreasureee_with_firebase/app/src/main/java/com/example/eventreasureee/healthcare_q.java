package com.example.eventreasureee;

import androidx.appcompat.app.AppCompatActivity;

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

public class healthcare_q extends AppCompatActivity {


    EditText x3value ;
    String y3value;
    Button A5nxtbtn;

    private FirebaseUser user;
    private DatabaseReference Dr;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthcare_q);
        x3value = findViewById(R.id.A5);
        y3value = "4";
        A5nxtbtn = findViewById(R.id.A5nextbtn);

        user = FirebaseAuth.getInstance().getCurrentUser();
        Dr = FirebaseDatabase.getInstance().getReference("Bar");
        userID = user.getUid();

        setListeners();
    }

    private void setListeners() {
        A5nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(x3value.getText())){
                    x3value.setError("cost is required");
                    return;
                }


                DatabaseReference add = Dr.child(userID);

                Float x3=Float.parseFloat(x3value.getText().toString());
                int y3=Integer.parseInt(y3value);
                String u = "x3";
                String u1 = "y3";

                //recordx1 info = new recordx1(x1,y1);

                DatabaseReference add1 = add.child(u);
                DatabaseReference add2 = add.child(u1);
                add1.setValue(x3);
                add2.setValue(y3);

                startActivity(new Intent(getApplicationContext(), transport_q.class));

            }
        });
    }
}