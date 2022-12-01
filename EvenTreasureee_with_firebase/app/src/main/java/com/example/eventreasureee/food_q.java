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

public class food_q extends AppCompatActivity {

    EditText x1value ;
    String y1value;
    Button A3nxtbtn;

    private FirebaseUser user;
    private DatabaseReference Dr;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_q);

        x1value = findViewById(R.id.A3);
        y1value = "2";
        A3nxtbtn = findViewById(R.id.A3nextbtn);

        user = FirebaseAuth.getInstance().getCurrentUser();
        Dr = FirebaseDatabase.getInstance().getReference("Bar");
        userID = user.getUid();

        setListeners();
    }

    private void setListeners() {
        A3nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(x1value.getText())){
                    x1value.setError("cost is required");
                    return;
                }

               DatabaseReference add = Dr.child(userID);

                Float x1=Float.parseFloat(x1value.getText().toString());
                int y1=Integer.parseInt(y1value);
                String u = "x1";
                String u1 = "y1";

                //recordx1 info = new recordx1(x1,y1);

                DatabaseReference add1 = add.child(u);
                DatabaseReference add2 = add.child(u1);
                add1.setValue(x1);
                add2.setValue(y1);
                startActivity(new Intent(getApplicationContext(), food_out_side_order.class));
            }
        });
    }
}