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

public class transport_q extends AppCompatActivity {
    EditText x4value ;
    String y4value;
    Button A6nxtbtn;

    private FirebaseUser user;
    private DatabaseReference Dr;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_q);
        x4value = findViewById(R.id.A6);
        y4value = "5";
        A6nxtbtn = findViewById(R.id.A6nextbtn);

        user = FirebaseAuth.getInstance().getCurrentUser();
        Dr = FirebaseDatabase.getInstance().getReference("Bar");
        userID = user.getUid();

        setListeners();
    }

    private void setListeners() {
        A6nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(x4value.getText())){
                    x4value.setError("cost is required");
                    return;
                }

                DatabaseReference add = Dr.child(userID);

                Float x4=Float.parseFloat(x4value.getText().toString());
                int y4=Integer.parseInt(y4value);
                String u = "x4";
                String u1 = "y4";

                //recordx1 info = new recordx1(x1,y1);

                DatabaseReference add1 = add.child(u);
                DatabaseReference add2 = add.child(u1);
                add1.setValue(x4);
                add2.setValue(y4);

                startActivity(new Intent(getApplicationContext(), bills_q.class));

            }
        });
    }
}