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

public class bills_q extends AppCompatActivity {

    EditText x5value ;
    String y5value;
    Button A7nxtbtn;

    private FirebaseUser user;
    private DatabaseReference Dr;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills_q);

        x5value = findViewById(R.id.A7);
        y5value = "6";
        A7nxtbtn = findViewById(R.id.A7nextbtn);

        user = FirebaseAuth.getInstance().getCurrentUser();
        Dr = FirebaseDatabase.getInstance().getReference("Bar");
        userID = user.getUid();

        setListeners();
    }

    private void setListeners() {
        A7nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(x5value.getText())){
                    x5value.setError("cost is required");
                    return;
                }

                DatabaseReference add = Dr.child(userID);

                Float x5=Float.parseFloat(x5value.getText().toString());
                int y5=Integer.parseInt(y5value);
                String u = "x5";
                String u1 = "y5";

                //recordx1 info = new recordx1(x1,y1);

                DatabaseReference add1 = add.child(u);
                DatabaseReference add2 = add.child(u1);
                add1.setValue(x5);
                add2.setValue(y5);

                startActivity(new Intent(getApplicationContext(), subscription_q.class));

            }
        });
    }
}