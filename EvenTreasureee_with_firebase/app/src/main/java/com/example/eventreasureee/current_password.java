package com.example.eventreasureee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class current_password extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference Dr;
    private String userID;

   private EditText cpasss;
    private Button goobutton;
    private Button canclebtnn;

    TextView ttest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_password);

         cpasss = (EditText) findViewById(R.id.ccpass);
        goobutton = (Button) findViewById(R.id.gobtn);
        canclebtnn = (Button) findViewById(R.id.canclbtn);
        ttest = (TextView) findViewById(R.id.testo);


        user = FirebaseAuth.getInstance().getCurrentUser();
        Dr = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();


        Dr.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                    Userinfo passinfo = snapshot.getValue(Userinfo.class);
                    String cps = passinfo.password;





                    goobutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String currentpassvalue = String.valueOf(cpasss.getText());

                            if(TextUtils.isEmpty(currentpassvalue)){
                                cpasss.setError("current password is required");
                                return;
                            }


                            if(currentpassvalue.equals(cps)) {

                                startActivity(new Intent(getApplicationContext(), EditUserDetails.class));

                            }

                            else{
                                Toast.makeText(current_password.this, "Your password is incorrect", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });


                    canclebtnn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            AlertDialog.Builder alertb = new AlertDialog.Builder(current_password.this);

                            alertb.setMessage("Are you sure you want to cancel ? ")
                                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            Intent direct = new Intent(getApplicationContext(), ProfileDetails.class);
                                            startActivity(direct);

                                        }
                                    })

                                    .setNegativeButton("No", null);


                            AlertDialog al = alertb.create();
                            al.show();


                        }
                    });








            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }



        });
















    }
}