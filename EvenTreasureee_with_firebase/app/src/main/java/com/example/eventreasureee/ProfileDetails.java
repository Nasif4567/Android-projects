package com.example.eventreasureee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class ProfileDetails extends Fragment {

    EditText pname,pemail,ppassword;
    private FirebaseAuth fireA;
    private FirebaseDatabase fireD;
    private FirebaseUser user;
    private DatabaseReference Dr;
    private String userID;
    Button editprofilebtn;




    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View w = inflater.inflate(R.layout.activity_profile_details, container, false);


        ConnectivityManager CM = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo WN = CM.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo MN = CM.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if( (     WN != null && WN.isConnected() || (MN != null && MN.isConnected())    ) ) {

        }

        else {

            startActivity(new Intent(getActivity() , NoConnection.class));
        }



        pname = (EditText) w.findViewById(R.id.sname);
        pemail = (EditText) w.findViewById(R.id.semail);
        ppassword = (EditText) w.findViewById(R.id.spass);
        editprofilebtn = (Button) w.findViewById(R.id.savebtn);


        user = FirebaseAuth.getInstance().getCurrentUser();
        Dr = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        Dr.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                Userinfo info = snapshot.getValue(Userinfo.class);

                String fn = info.name;
                String em = info.email;
                String pa = info.password;

                pname.setText(fn);
                pemail.setText(em);
                ppassword.setText(pa);






            }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        editprofilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), current_password.class);
                startActivity(in);
            }
        });








        return w;

    }
}