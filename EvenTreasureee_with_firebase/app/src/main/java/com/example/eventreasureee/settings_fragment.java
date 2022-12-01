package com.example.eventreasureee;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class settings_fragment extends Fragment {
    Button logout;
    TextView in;
    public Switch colourswitch;
    public int colourchange = 00;
    LinearLayout Layoutsettings;
    String score = "100";





    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View r = inflater.inflate(R.layout.fragment_setting, container, false);

        return r;

    }


    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       /* Layoutsettings = (LinearLayout) r.findViewById(R.id.settinglayout);
        in = (TextView) r.findViewById(R.id.Increment);
        incre = (Button) r.findViewById(R.id.deleteacc);
        logout = (Button) r.findViewById(R.id.logout);
        colourswitch = (Switch) r.findViewById(R.id.switch1);
        boolean[] switchposition = {true}; */

         Layoutsettings = getActivity().findViewById(R.id.settinglayout);
        Button deleteacc = (Button) getActivity().findViewById(R.id.deleteacc);
        logout = (Button) getActivity().findViewById(R.id.logout);
        //colourswitch = (Switch) getActivity().findViewById(R.id.switch1);
        boolean[] switchposition = {true};


        FirebaseAuth fa = FirebaseAuth.getInstance();
        final FirebaseUser user = fa.getCurrentUser();
        String userID= user.getUid();
        DatabaseReference DB = FirebaseDatabase.getInstance().getReference("Bar") ;
        DatabaseReference DS = FirebaseDatabase.getInstance().getReference("DateStart");
        DatabaseReference DL = FirebaseDatabase.getInstance().getReference("LineChart");
        DatabaseReference DP = FirebaseDatabase.getInstance().getReference("Users");









        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alertb = new AlertDialog.Builder(getActivity());

                alertb.setMessage("Are you sure you want to signout ? ")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                Intent in = new Intent(getActivity(), login.class);
                                startActivity(in);

                            }
                        })

                        .setNegativeButton("No", null );


                AlertDialog al = alertb.create();
                al.show();


            }
        });


       /* colourswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(switchposition[0]){
                    Layoutsettings.setBackgroundColor(Color.LTGRAY);
                    switchposition[0] = false;

                }

                else {
                    Layoutsettings.setBackgroundColor(Color.WHITE);
                    switchposition[0] = true;
                }


            }
        }); */



        deleteacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertb = new AlertDialog.Builder(getActivity());

                alertb.setMessage("Are you sure you want to delete your account ? Deleting your account will make you lose all your data and you will not have access to the app anymore.")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {




                                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<Void> task) {

                                        if(task.isSuccessful()){

                                            DL.child(userID).removeValue();
                                            DB.child(userID).removeValue();
                                            DP.child(userID).removeValue();
                                            DS.child(userID).removeValue();

                                          Toast.makeText(getActivity(), "Account deleted", Toast.LENGTH_SHORT).show();

                                          startActivity(new Intent(getActivity(), MainActivity2.class));
                                        }

                                        else {
                                            Toast.makeText(getActivity(), "Error !" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });



                            }
                        })

                        .setNegativeButton("Cancel", null);


                AlertDialog al = alertb.create();
                al.show();













            }
        });







    }
}
