package com.example.eventreasureee;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Registration extends AppCompatActivity {

    EditText ename,eusername , epass ;
    Button ergn , elogin ;

    FirebaseAuth fauth;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);







        ename = findViewById(R.id.nametxt);
        eusername = findViewById(R.id.emailtxt);
        epass = findViewById(R.id.passtxt);
        ergn = findViewById(R.id.regbtn);
        elogin = findViewById(R.id.signintxt);
        progressbar = findViewById(R.id.pgbar2);

        fauth = FirebaseAuth.getInstance();



        ergn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nam = ename.getText().toString().trim();
                String user = eusername.getText().toString().trim();
                String pass = epass.getText().toString().trim();

                if(TextUtils.isEmpty(nam)){
                    ename.setError("name is required");
                    return;
                }

                if (TextUtils.isEmpty(user)) {
                    eusername.setError("email is required");
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    epass.setError("password is required");
                    return;
                }

                progressbar.setVisibility(View.VISIBLE);

                fauth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if(task.isSuccessful()) {

                            Userinfo info = new Userinfo(nam,user,pass);

                            FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                              @Override
                                                                              public void onComplete(@NonNull Task<Void> task) {
                                                                                  if (task.isSuccessful()) {
                                                                                      Toast.makeText(Registration.this, "Registered", Toast.LENGTH_SHORT).show();



                                                                                      FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                                                      String userID = user.getUid();
                                                                                      FirebaseDatabase.getInstance().getReference("DateStart").child(userID).child("Boolean").setValue("Yes");

                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("x").setValue(Float.parseFloat("0"));
                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("x1").setValue(Float.parseFloat("0"));
                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("x2").setValue(Float.parseFloat("0"));
                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("x3").setValue(Float.parseFloat("0"));
                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("x4").setValue(Float.parseFloat("0"));
                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("x5").setValue(Float.parseFloat("0"));
                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("x6").setValue(Float.parseFloat("0"));
                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("x7").setValue(Float.parseFloat("0"));
                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("xsaving").setValue(Float.parseFloat("0"));
                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("xwages").setValue(Float.parseFloat("0"));


                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("y").setValue(Integer.parseInt("0"));
                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("y1").setValue(Integer.parseInt("0"));
                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("y2").setValue(Integer.parseInt("0"));
                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("y3").setValue(Integer.parseInt("0"));
                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("y4").setValue(Integer.parseInt("0"));
                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("y5").setValue(Integer.parseInt("0"));
                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("y6").setValue(Integer.parseInt("0"));
                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("y7").setValue(Integer.parseInt("0"));
                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("ysaving").setValue(Integer.parseInt("0"));
                                                                                      FirebaseDatabase.getInstance().getReference("Bar").child(userID).child("ywages").setValue(Integer.parseInt("0"));


                                                                                      Calendar calendar = null;
                                                                                      SimpleDateFormat form =new SimpleDateFormat("dd/MM/yyyy");
                                                                                      String lastd = form.format(calendar.getInstance().getTime());


                                                                                      FirebaseDatabase.getInstance().getReference("DateStart").child(userID).child("Start").setValue(lastd);


                                                                                      SimpleDateFormat dateFmt =new SimpleDateFormat("dd-M-yyyy");
                                                                                      String dava = dateFmt.format(calendar.getInstance().getTime());
                                                                                      FirebaseDatabase.getInstance().getReference("Linechart").child(userID).child("datevalidation").child(dava).setValue(0);





                                                                                      startActivity(new Intent(getApplicationContext(), askingques.class));






                                                                                  }


                                                                                  else {
                                                                                      Toast.makeText(Registration.this, "Error !" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                                                  }
                                                                              }
                                                                          });



                            progressbar.setVisibility(View.INVISIBLE);


                        }

                        else {
                            Toast.makeText(Registration.this, "Error !" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressbar.setVisibility(View.INVISIBLE);
                        }

                    }
                });



            }
        });





        elogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), login.class));
            }
        });

    }

}