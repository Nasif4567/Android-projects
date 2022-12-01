package com.example.eventreasureee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ViewHistory extends AppCompatActivity {

    ListView listv;
    ListView lists;


    private FirebaseUser user;
    private DatabaseReference Dr;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);

        listv = findViewById(R.id.ListViewdates);
        lists = findViewById(R.id.listspending);


        user = FirebaseAuth.getInstance().getCurrentUser();
        Dr = FirebaseDatabase.getInstance().getReference("Linechart");
        userID = user.getUid();




        final ArrayList<String> list =  new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_view , list);
        listv.setAdapter(adapter);

        final ArrayList<String> listn =  new ArrayList<>();
        final ArrayAdapter adapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_views, listn);
        lists.setAdapter(adapter1);


        DatabaseReference datelist = Dr.child(userID).child("ListDate");
        DatabaseReference spendinglist = Dr.child(userID).child("ListSpending");


        datelist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                list.clear();


                for (DataSnapshot snap : snapshot.getChildren()){


                    list.add(snap.getValue().toString());

                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


        spendinglist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for (DataSnapshot snap : snapshot.getChildren()){


                    listn.add(snap.getValue().toString());

                }

                adapter1.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });



    }
}