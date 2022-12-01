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

public class entertainment_q extends AppCompatActivity {

    EditText xvalue ;
    String yvalue ;
    Button A2nxtbtn;

    Button skip;

    private FirebaseUser user;
    private DatabaseReference Dr;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment_q);

        xvalue = findViewById(R.id.A2);
        yvalue = "1";
        A2nxtbtn = findViewById(R.id.A2nextbtn);
        skip = findViewById(R.id.skipbtn1);


        user = FirebaseAuth.getInstance().getCurrentUser();
        Dr = FirebaseDatabase.getInstance().getReference("Bar");
        userID = user.getUid();

        setListeners();
    }

    private void setListeners() {
        A2nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(TextUtils.isEmpty(xvalue.getText())){
                    xvalue.setError("cost is required");
                    return;
                }

                DatabaseReference add = Dr.child(userID);

                Float x=Float.parseFloat(xvalue.getText().toString());
                int y=Integer.parseInt(yvalue);
                String u = "x";
                String u1 = "y";


                DatabaseReference add1 = add.child(u);
                DatabaseReference add2 = add.child(u1);
                add1.setValue(x);
                add2.setValue(y);

                startActivity(new Intent(getApplicationContext(), food_q.class));

            }
        });
    }
}