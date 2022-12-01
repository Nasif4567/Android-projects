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

public class self_shopping_q extends AppCompatActivity {
    EditText x7value ;
    String y7value;
    Button A9nxtbtn;

    private FirebaseUser user;
    private DatabaseReference Dr;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_shopping_q);
        x7value = findViewById(R.id.A9);
        y7value = "8";
        A9nxtbtn = findViewById(R.id.A9nextbtn);

        user = FirebaseAuth.getInstance().getCurrentUser();
        Dr = FirebaseDatabase.getInstance().getReference("Bar");
        userID = user.getUid();

        setListeners();
    }

    private void setListeners() {
        A9nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(x7value.getText())){
                    x7value.setError("cost is required");
                    return;
                }

                DatabaseReference add = Dr.child(userID);

                Float x7=Float.parseFloat(x7value.getText().toString());
                int y7=Integer.parseInt(y7value);
                String u = "x7";
                String u1 = "y7";

                //recordx1 info = new recordx1(x1,y1);

                DatabaseReference add1 = add.child(u);
                DatabaseReference add2 = add.child(u1);
                add1.setValue(x7);
                add2.setValue(y7);

                startActivity(new Intent(getApplicationContext(), totalcount.class));

            }
        });
    }
}

