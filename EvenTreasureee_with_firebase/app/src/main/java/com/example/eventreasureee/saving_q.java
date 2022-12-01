package com.example.eventreasureee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import org.jetbrains.annotations.NotNull;

public class saving_q extends AppCompatActivity {
    EditText x8value ;
    String y8value;
    Button A10nxtbtn;

    private FirebaseUser user;
    private DatabaseReference Dr;
    private String userID;
    TextView alreadys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_q);
        x8value = findViewById(R.id.A10);
        y8value = "9";
        A10nxtbtn = findViewById(R.id.A10nextbtn);

        user = FirebaseAuth.getInstance().getCurrentUser();
        Dr = FirebaseDatabase.getInstance().getReference("Bar");
        userID = user.getUid();


        Dr.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                records entertainemnt = snapshot.getValue(records.class); //for entertainment
                recordx1 Groceries = snapshot.getValue(recordx1.class); //for food groceries
                recordx2 OutF = snapshot.getValue(recordx2.class); // for outside food
                recordx3 health = snapshot.getValue(recordx3.class); // for health care
                recordx4 transport = snapshot.getValue(recordx4.class); // for transport
                recordx5 bills = snapshot.getValue(recordx5.class); // for bills
                recordx6 subscription = snapshot.getValue(recordx6.class); // for subscription
                recordx7 selfshopping = snapshot.getValue(recordx7.class); // for selp shopping
                recordsXsavings savings = snapshot.getValue(recordsXsavings.class); // for saving
                recordXwages wages = snapshot.getValue(recordXwages.class); // for wages


                float entcost = entertainemnt.x;
                float grocercost = Groceries.x1;
                float outfcost = OutF.x2;
                float healthcost = health.x3;
                float transportcost = transport.x4;
                float billscost = bills.x5;
                float subscriptioncost = subscription.x6;
                float selfcost = selfshopping.x7;
                float savingmoney = savings.xsaving;
                float wagesmoney = wages.xwages;


                float totalmakeup = entcost + grocercost + outfcost + healthcost + transportcost + billscost + subscriptioncost + selfcost;
                float alreadysaved = wagesmoney - totalmakeup;

                alreadys = findViewById(R.id.alreadysaved);
                alreadys.setText(String.valueOf(alreadysaved));

            }



                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });




        setListeners();
    }



    private void setListeners() {
        A10nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(TextUtils.isEmpty(x8value.getText())){
                    x8value.setError("cost is required");
                    return;
                }





                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference Dr = FirebaseDatabase.getInstance().getReference("Bar");
                String userID = user.getUid();


                Dr.child(userID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                        records entertainemnt = snapshot.getValue(records.class); //for entertainment
                        recordx1 Groceries = snapshot.getValue(recordx1.class); //for food groceries
                        recordx2 OutF = snapshot.getValue(recordx2.class); // for outside food
                        recordx3 health = snapshot.getValue(recordx3.class); // for health care
                        recordx4 transport = snapshot.getValue(recordx4.class); // for transport
                        recordx5 bills = snapshot.getValue(recordx5.class); // for bills
                        recordx6 subscription = snapshot.getValue(recordx6.class); // for subscription
                        recordx7 selfshopping = snapshot.getValue(recordx7.class); // for selp shopping
                        recordsXsavings savings = snapshot.getValue(recordsXsavings.class); // for saving
                        recordXwages wages = snapshot.getValue(recordXwages.class); // for wages



                        float entcost = entertainemnt.x;
                        float grocercost = Groceries.x1;
                        float outfcost = OutF.x2;
                        float healthcost = health.x3;
                        float transportcost = transport.x4;
                        float billscost = bills.x5;
                        float subscriptioncost = subscription.x6;
                        float selfcost = selfshopping.x7;
                        float savingmoney = savings.xsaving;
                        float wagesmoney = wages.xwages;



                        float totalmakeup = entcost + grocercost + outfcost + healthcost + transportcost + billscost + subscriptioncost + selfcost;
                        float alreadysaved = wagesmoney - totalmakeup;
                        float sumofmoney = totalmakeup + savingmoney + alreadysaved;
                        float dpf = sumofmoney - wagesmoney;


                        float entper = ((entcost / sumofmoney) * 100);
                        float groper = ((grocercost / sumofmoney) * 100);
                        float outfper = ((outfcost / sumofmoney) * 100);
                        float healthper = ((healthcost / sumofmoney) * 100);
                        float transper = ((transportcost / sumofmoney) * 100);
                        float billper = ((billscost / sumofmoney) * 100);
                        float subsper = ((subscriptioncost / sumofmoney) * 100);
                        float selfper = ((selfcost / sumofmoney) * 100);
                        float alreadysavedper = ((alreadysaved / sumofmoney) * 100);
                        float dpfper = ((dpf / sumofmoney) * 100);
                        float dpfperactual = dpfper;  //--- this value of dpfper is the value before round of



                        String dp = String.valueOf(dpfper);

                        if(dpfper > 16 ) {
                            Toast.makeText(getApplicationContext(), "Savings cannot be more 16% as our system dont allow referring to world saving satistic ", Toast.LENGTH_SHORT).show();
                        }


                        else {

                            DatabaseReference add = Dr.child(userID);

                            Float x8=Float.parseFloat(x8value.getText().toString());
                            int y8=Integer.parseInt(y8value);
                            String u = "xsaving";
                            String u1 = "ysaving";

                            //recordx1 info = new recordx1(x1,y1);

                            DatabaseReference add1 = add.child(u);
                            DatabaseReference add2 = add.child(u1);
                            add1.setValue(x8);
                            add2.setValue(y8);


                            startActivity(new Intent(getApplicationContext(),login.class ));

                        }





                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });








            }
        });
    }

}