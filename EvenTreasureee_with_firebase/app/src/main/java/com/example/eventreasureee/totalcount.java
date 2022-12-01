package com.example.eventreasureee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class totalcount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_totalcount);

        TextView bb = findViewById(R.id.textView15);
        Button next = findViewById(R.id.button3);

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


                    bb.setText(String.valueOf(totalmakeup));


                    next.setOnClickListener(v -> {


                        if (totalmakeup > wagesmoney) {
                            Toast.makeText(getApplicationContext(), "Your total spending cannot be more than your monthly wages it but must be with in your wages", Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(getApplicationContext(), saving_q.class));
                        }


                    });




                }



                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }





            }); //-----------------end of value event listener





      }


    }
