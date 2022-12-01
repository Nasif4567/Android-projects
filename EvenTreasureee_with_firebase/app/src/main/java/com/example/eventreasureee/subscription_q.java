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

public class subscription_q extends AppCompatActivity {
    EditText x6value ;
    String y6value;
    Button A8nxtbtn;

    private FirebaseUser user;
    private DatabaseReference Dr;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_q);
        x6value = findViewById(R.id.A8);
        y6value = "7";
        A8nxtbtn = findViewById(R.id.A8nextbtn);

        user = FirebaseAuth.getInstance().getCurrentUser();
        Dr = FirebaseDatabase.getInstance().getReference("Bar");
        userID = user.getUid();

        setListeners();
    }

    private void setListeners() {
        A8nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(x6value.getText())){
                    x6value.setError("cost is required");
                    return;
                }

                DatabaseReference add = Dr.child(userID);

                Float x6=Float.parseFloat(x6value.getText().toString());
                int y6=Integer.parseInt(y6value);
                String u = "x6";
                String u1 = "y6";

                //recordx1 info = new recordx1(x1,y1);

                DatabaseReference add1 = add.child(u);
                DatabaseReference add2 = add.child(u1);
                add1.setValue(x6);
                add2.setValue(y6);

                startActivity(new Intent(getApplicationContext(), self_shopping_q.class));

            }
        });
    }
}
