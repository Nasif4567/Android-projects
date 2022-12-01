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

public class food_out_side_order extends AppCompatActivity {
    EditText x2value ;
    String y2value ;
    Button A4nxtbtn;

    private FirebaseUser user;
    private DatabaseReference Dr;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_out_side_order);


        x2value = findViewById(R.id.A4);
        y2value = "3";
        A4nxtbtn = findViewById(R.id.A4nextbtn);

        user = FirebaseAuth.getInstance().getCurrentUser();
        Dr = FirebaseDatabase.getInstance().getReference("Bar");
        userID = user.getUid();

        setListeners();
    }

    private void setListeners() {
        A4nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(x2value.getText())){
                    x2value.setError("cost is required");
                    return;
                }
                DatabaseReference add = Dr.child(userID);

                Float x2=Float.parseFloat(x2value.getText().toString());
                int y2=Integer.parseInt(y2value);
                String u = "x2";
                String u1 = "y2";

                //recordx1 info = new recordx1(x1,y1);

                DatabaseReference add1 = add.child(u);
                DatabaseReference add2 = add.child(u1);
                add1.setValue(x2);
                add2.setValue(y2);

                startActivity(new Intent(getApplicationContext(), healthcare_q.class));

            }
        });
    }
}