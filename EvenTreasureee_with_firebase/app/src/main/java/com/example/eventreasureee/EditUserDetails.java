package com.example.eventreasureee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditUserDetails extends AppCompatActivity {

    private EditText eddname, eddemail , eddpass;
    private Button  btns;
    private FirebaseUser user;
    private DatabaseReference Dr;
    private String userID;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), home_fragment.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_details);

        eddname = (EditText) findViewById(R.id.edname);
        eddemail = (EditText) findViewById(R.id.edemail);
        eddpass = (EditText) findViewById(R.id.edpass);
        btns = (Button) findViewById(R.id.sbtn);



        user = FirebaseAuth.getInstance().getCurrentUser();
        Dr = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        Dr.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Userinfo info = snapshot.getValue(Userinfo.class);
                if (info != null) {
                    String Fn = info.name;
                    String ee = info.email;
                    String pass = info.password;

                    eddname.setText(Fn);
                    eddemail.setText(ee);
                    eddpass.setText(pass);
                }


            }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        btns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertb = new AlertDialog.Builder(EditUserDetails.this);

                alertb.setMessage("Are you sure you want to save the changes ? ")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseReference add = Dr.child(userID);

                                String namef =String.valueOf(eddname.getText());
                                String emailf = String.valueOf(eddemail.getText());
                                String passwordf = String.valueOf(eddpass.getText());
                                String u = "email";
                                String u1 = "name";
                                String u3 = "password";


                                DatabaseReference add1 = add.child(u);
                                DatabaseReference add2 = add.child(u1);
                                DatabaseReference add3 = add.child(u3);

                                add1.setValue(emailf);
                                add2.setValue(namef);
                                add3.setValue(passwordf);

                                Toast.makeText(EditUserDetails.this, "Profile updated", Toast.LENGTH_SHORT).show();

                            }
                        })

                        .setNegativeButton("No", null);


                AlertDialog al = alertb.create();
                al.show();



            }
        });





    }
}