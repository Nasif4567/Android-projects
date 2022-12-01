package com.example.eventreasureee;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class money_fragment extends Fragment {

    EditText Entcosttxt;
    EditText  Outfcosttxt;
    EditText Grocosttxt;
    EditText Healthcosttxt;
    EditText Transcosttxt;
    EditText Billcosttxt;
    EditText Selfcosttxt;
    EditText Subscosttxt;
    Switch EnableEditing;
    Button UpdateBtn ;
    TextView test ;
    LineChart lineChart;
    LineData lineData;
    LineDataSet lineDataSet;
    ArrayList lineEntries;


    private FirebaseUser user;
    private DatabaseReference Dr;
    private String userID;
    private DatabaseReference Drl;
    private DatabaseReference DrS;

    EditText ws;
    EditText ss;

    Button track;
    Button history;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View m = inflater.inflate(R.layout.fragment_money, container, false);


        //----------------------------checking internet connection----------------
        ConnectivityManager CM = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo WN = CM.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo MN = CM.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if( (     WN != null && WN.isConnected() || (MN != null && MN.isConnected())    ) ) {

        }

        else {

            startActivity(new Intent(getActivity() , NoConnection.class));
        }

        //-------------------------------------------------------------------------









        user = FirebaseAuth.getInstance().getCurrentUser();

        Dr = FirebaseDatabase.getInstance().getReference("Bar");
        Drl = FirebaseDatabase.getInstance().getReference("Linechart");
        DrS = FirebaseDatabase.getInstance().getReference("DateStart");
        userID = user.getUid();

        track = (Button) m.findViewById(R.id.trackbtn);
        history = (Button) m.findViewById(R.id.showhistory);
        test = (TextView) m.findViewById(R.id.tes);
        lineChart = (LineChart) m.findViewById(R.id.linechart);



        Entcosttxt = (EditText) m.findViewById(R.id.entcost);
        Outfcosttxt = (EditText) m.findViewById(R.id.outfcost);
        Grocosttxt = (EditText) m.findViewById(R.id.grocost);
        Healthcosttxt = (EditText) m.findViewById(R.id.healthcost);
        Transcosttxt = (EditText) m.findViewById(R.id.transcost);
        Billcosttxt = (EditText) m.findViewById(R.id.billcost);
        Selfcosttxt = (EditText) m.findViewById(R.id.selfcost);
        Subscosttxt = (EditText) m.findViewById(R.id.subscost);
        EnableEditing =(Switch) m.findViewById(R.id.switchediting);
        UpdateBtn = (Button) m.findViewById(R.id.updatebtn);
        ws = (EditText) m.findViewById(R.id.wagesm);
        ss = (EditText) m.findViewById(R.id.savingm);


        TextView day0txt = (TextView) m.findViewById(R.id.day0txt);
        TextView day1txt = (TextView) m.findViewById(R.id.day1txt);
        TextView day2txt = (TextView) m.findViewById(R.id.day2txt);
        TextView day3txt = (TextView) m.findViewById(R.id.day3txt);
        TextView day4txt = (TextView) m.findViewById(R.id.day4txt);
        TextView day5txt = (TextView) m.findViewById(R.id.day5txt);
        TextView day6txt = (TextView) m.findViewById(R.id.day6txt);
        TextView day7txt = (TextView) m.findViewById(R.id.day7txt);











        final boolean[] editturnon = {true}; //switch closed


        EnableEditing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ;

                if(editturnon[0]) {
                    editturnon[0] = false; // means open
                    Entcosttxt.setEnabled(true);
                    Outfcosttxt.setEnabled(true);
                    Grocosttxt.setEnabled(true);
                    Healthcosttxt.setEnabled(true);
                    Transcosttxt.setEnabled(true);
                    Billcosttxt.setEnabled(true);
                    Selfcosttxt.setEnabled(true);
                    Subscosttxt.setEnabled(true);
                    ws.setEnabled(true);
                    ss.setEnabled(true);


                }

                else  {
                    Entcosttxt.setEnabled(false);
                    Outfcosttxt.setEnabled(false);
                    Grocosttxt.setEnabled(false);
                    Healthcosttxt.setEnabled(false);
                    Transcosttxt.setEnabled(false);
                    Billcosttxt.setEnabled(false);
                    Selfcosttxt.setEnabled(false);
                    Subscosttxt.setEnabled(false);
                    ws.setEnabled(false);
                    ss.setEnabled(false);


                    editturnon[0] = true;  // switch colsed


                }



            }
        });





        Dr.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                records entertainemnt = snapshot.getValue(records.class); //for entertainment
                recordx1 Groceries = snapshot.getValue(recordx1.class); //for food groceries
                recordx2 OutF = snapshot.getValue(recordx2.class); // for outside food
                recordx3 health = snapshot.getValue(recordx3.class); // for health care
                recordx4 transport = snapshot.getValue(recordx4.class); // for transport
                recordx5 bills = snapshot.getValue(recordx5.class); // for bills
                recordx6 subscription = snapshot.getValue(recordx6.class); // for subscription
                recordx7 selfshopping = snapshot.getValue(recordx7.class); // for selp shopping
                recordXwages wages = snapshot.getValue(recordXwages.class); // for wages
                recordsXsavings savings = snapshot.getValue(recordsXsavings.class); // for saving


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


                Entcosttxt.setText(String.valueOf(entcost));
                Grocosttxt.setText(String.valueOf(grocercost));
                Outfcosttxt.setText(String.valueOf(outfcost));
                Healthcosttxt.setText(String.valueOf(healthcost));
                Transcosttxt.setText(String.valueOf(transportcost));
                Billcosttxt.setText(String.valueOf(billscost));
                Subscosttxt.setText(String.valueOf(subscriptioncost));
                Selfcosttxt.setText(String.valueOf(selfcost));
                ws.setText(String.valueOf(wagesmoney));
                ss.setText(String.valueOf(savingmoney));









            }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(Entcosttxt.getText())){
                    Entcosttxt.setError("Please enter the speding cost");
                    return;
                }

                if(TextUtils.isEmpty(Grocosttxt.getText())){
                    Grocosttxt.setError("Please enter the speding cost");
                    return;
                }

                if(TextUtils.isEmpty(Outfcosttxt.getText())){
                    Outfcosttxt.setError("Please enter the speding cost");
                    return;
                }

                if(TextUtils.isEmpty(Healthcosttxt.getText())){
                    Healthcosttxt.setError("Please enter the speding cost");
                    return;
                }

                if(TextUtils.isEmpty(Billcosttxt.getText())){
                    Billcosttxt.setError("Please enter the speding cost");
                    return;
                }

                if(TextUtils.isEmpty(Transcosttxt.getText())){
                    Transcosttxt.setError("Please enter the speding cost");
                    return;
                }

                if(TextUtils.isEmpty(Subscosttxt.getText())){
                    Subscosttxt.setError("Please enter the speding cost");
                    return;
                }

                if(TextUtils.isEmpty(Selfcosttxt.getText())){
                    Selfcosttxt .setError("Please enter the speding cost");
                    return;
                }

                if(TextUtils.isEmpty(ws.getText())){
                    ws.setError("Please enter the speding cost");
                    return;
                }

                if(TextUtils.isEmpty(ss.getText())){
                    ss.setError("Please enter the speding cost");
                    return;
                }



                if (!editturnon[0]) { // means open

                    AlertDialog.Builder alertb = new AlertDialog.Builder(getActivity());

                    alertb.setMessage("Are you sure you want update the changes ? ")
                            .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                    String yvalue = "1";
                                    String y1value = "2";
                                    String y2value = "3";
                                    String y3value = "4";
                                    String y4value = "5";
                                    String y5value = "6";
                                    String y6value = "7";
                                    String y7value = "8";

                                    DatabaseReference add = Dr.child(userID); // for entertainment

                                    Float x = Float.parseFloat(Entcosttxt.getText().toString());
                                    int y = Integer.parseInt(yvalue);
                                    String u = "x";
                                    String u1 = "y";


                                    DatabaseReference add1 = add.child(u);
                                    DatabaseReference add2 = add.child(u1);
                                    add1.setValue(x);
                                    add2.setValue(y);

//--------------------------------------------------------------------------------------------------
                                    DatabaseReference addg = Dr.child(userID); // for groceries

                                    Float x1 = Float.parseFloat(Grocosttxt.getText().toString());
                                    int y1 = Integer.parseInt(y1value);
                                    String ug = "x1";
                                    String ug1 = "y1";


                                    DatabaseReference addg1 = addg.child(ug);
                                    DatabaseReference addg2 = addg.child(ug1);
                                    addg1.setValue(x1);
                                    addg2.setValue(y1);

                                    //----------------------------------------------------------------------------------------------------

                                    DatabaseReference addo = Dr.child(userID); // for outside food

                                    Float x2 = Float.parseFloat(Outfcosttxt.getText().toString());
                                    int y2 = Integer.parseInt(y2value);
                                    String uo = "x2";
                                    String uo1 = "y2";


                                    DatabaseReference addo1 = addo.child(uo);
                                    DatabaseReference addo2 = addo.child(uo1);
                                    addo1.setValue(x2);
                                    addo2.setValue(y2);
                                    //------------------------------------------------------------------------------------------------------

                                    DatabaseReference addh = Dr.child(userID); // for Healthcare

                                    Float x3 = Float.parseFloat(Healthcosttxt.getText().toString());
                                    int y3 = Integer.parseInt(y3value);
                                    String uh = "x3";
                                    String uh1 = "y3";


                                    DatabaseReference addh1 = addh.child(uh);
                                    DatabaseReference addh2 = addh.child(uh1);
                                    addh1.setValue(x3);
                                    addh2.setValue(y3);


                                    //--------------------------------------------------------------------------------------------

                                    DatabaseReference addt = Dr.child(userID); // for transport

                                    Float x4 = Float.parseFloat(Transcosttxt.getText().toString());
                                    int y4 = Integer.parseInt(y4value);
                                    String ut = "x4";
                                    String ut1 = "y4";


                                    DatabaseReference addt1 = addt.child(ut);
                                    DatabaseReference addt2 = addt.child(ut1);
                                    addt1.setValue(x4);
                                    addt2.setValue(y4);


                                    //---------------------------------------------------------------------------------------------------

                                    DatabaseReference addb = Dr.child(userID); // for bills

                                    Float x5 = Float.parseFloat(Billcosttxt.getText().toString());
                                    int y5 = Integer.parseInt(y5value);
                                    String ub = "x5";
                                    String ub1 = "y5";


                                    DatabaseReference addb1 = addb.child(ub);
                                    DatabaseReference addb2 = addb.child(ub1);
                                    addb1.setValue(x5);
                                    addb2.setValue(y5);

                                    //--------------------------------------------------------------------------------------------------
                                    DatabaseReference adds = Dr.child(userID); // for subscription

                                    Float x6 = Float.parseFloat(Subscosttxt.getText().toString());
                                    int y6 = Integer.parseInt(y6value);
                                    String us = "x6";
                                    String us1 = "y6";


                                    DatabaseReference adds1 = addo.child(us);
                                    DatabaseReference adds2 = addo.child(us1);
                                    adds1.setValue(x6);
                                    adds2.setValue(y6);

                                    //------------------------------------------------------------------------------------------------
                                    DatabaseReference addsf = Dr.child(userID); // for selfcost

                                    Float x7 = Float.parseFloat(Selfcosttxt.getText().toString());
                                    int y7 = Integer.parseInt(y7value);
                                    String usf = "x7";
                                    String usf1 = "y7";


                                    DatabaseReference addsf1 = addsf.child(usf);
                                    DatabaseReference addsf2 = addsf.child(usf1);
                                    addsf1.setValue(x7);
                                    addsf2.setValue(y7);

                                    //----------------------------------------------------------------------------------------------

                                     Dr.child(userID).child("xsaving").setValue(Float.parseFloat(ss.getText().toString() ) );
                                    Dr.child(userID).child("xwages").setValue(Float.parseFloat(ws.getText().toString() ) );



                                    Toast.makeText(getActivity(), "Spendings Updated", Toast.LENGTH_SHORT).show();

                                }
                            })
                            .setNegativeButton("No", null);

                    AlertDialog al = alertb.create();
                    al.show();

                    //------------------------------------------------------------------------

                } else {
                    Toast.makeText(getActivity(), "Please turn on enable editing", Toast.LENGTH_SHORT).show();

                }
              //----------------------------------------------------------------------

            } //--------------------------------------end of alert dialog box

            //----------------------------------------------------------------------

            });







        track.setOnClickListener(v ->
                startActivity(new Intent(getActivity(),Insert_dailyMoney.class ))
        );


        history.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(),ViewHistory.class ));
        });







        DrS.child(userID).child("Start").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {



                    String firstday = snapshot.getValue().toString();

                Calendar calendar = null;

                SimpleDateFormat formatter =new SimpleDateFormat("dd/MM/yyyy");


                try {


                     Date firstdate = formatter.parse(firstday);


                    String lastdate = formatter.format(calendar.getInstance().getTime());
                    Date todaydate = formatter.parse(lastdate);

                    long diff = todaydate.getTime() - firstdate.getTime();

                    TimeUnit time = TimeUnit.DAYS;
                    long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);


                    if (diffrence >= 7) {

                        // ------------------------------------------------------
                        //insert the the new date after the prvious date expired ------------------------------------------------

                        DatabaseReference ref = DrS.child(userID);

                        SimpleDateFormat form =new SimpleDateFormat("dd/MM/yyyy");
                        String lastd = form.format(calendar.getInstance().getTime());

                        DatabaseReference add = ref.child("Start");


                        Date dayadd7 = null;
                        String dayyadd7 = null;

                        Calendar caladd7 = Calendar.getInstance();
                        caladd7.setTime(firstdate);

                        // add 7 days to current day
                        caladd7.add(Calendar.DAY_OF_MONTH, 7);
                        dayadd7 = caladd7.getTime();

                        dayyadd7 = formatter.format(dayadd7);

                        add.setValue(dayyadd7);



                        //----------------------------------------------------------------------------------

                        Date day0 = null;
                        String dayy0 = null;


                        Date day1 = null;
                        String dayy1 = null;

                        Date day2 = null;
                        String dayy2= null;;

                        Date day3= null;
                        String dayy3= null;;

                        Date day4= null;
                        String dayy4= null;;

                        Date day5= null;
                        String dayy5= null;;

                        Date day6= null;
                        String dayy6= null;;

                        Date day7= null;
                        String dayy7 = null;


                        //-----------------for day 0 -------------------

                        Calendar cal0 = Calendar.getInstance();
                        cal0.setTime(firstdate);

                        // add 0 days to current day
                        cal0.add(Calendar.DAY_OF_MONTH, 0);
                        day0 = cal0.getTime();

                        dayy0 = formatter.format(day0);






                        //-----------------for day 1 -------------------

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(firstdate);

                        // add 1 days to current day
                        cal.add(Calendar.DAY_OF_MONTH, 1);
                        day1 = cal.getTime();

                        dayy1 = formatter.format(day1);




                        //-----------------for day 2 -------------------



                        Calendar cal2 = Calendar.getInstance();
                        cal2.setTime(firstdate);

                        // add 2 days to current day
                        cal2.add(Calendar.DAY_OF_MONTH, 2);
                        day2 = cal2.getTime();

                        dayy2 = formatter.format(day2);


                        //-----------------for day 3 -------------------


                        Calendar cal3 = Calendar.getInstance();
                        cal3.setTime(firstdate);

                        // add 2 days to current day
                        cal3.add(Calendar.DAY_OF_MONTH, 3);
                        day3 = cal3.getTime();

                        dayy3 = formatter.format(day3);



                        //-----------------for day 4 -------------------


                        Calendar cal4 = Calendar.getInstance();
                        cal4.setTime(firstdate);

                        // add 4 days to current day
                        cal4.add(Calendar.DAY_OF_MONTH, 4);
                        day4 = cal4.getTime();

                        dayy4 = formatter.format(day4);






                        //-----------------for day 5 -------------------

                        Calendar cal5 = Calendar.getInstance();
                        cal5.setTime(firstdate);

                        // add 4 days to current day
                        cal5.add(Calendar.DAY_OF_MONTH, 5);
                        day5 = cal5.getTime();

                        dayy5 = formatter.format(day5);




                        //-----------------for day 6 -------------------


                        Calendar cal6 = Calendar.getInstance();
                        cal6.setTime(firstdate);

                        // add 4 days to current day
                        cal6.add(Calendar.DAY_OF_MONTH, 6);
                        day6 = cal6.getTime();

                        dayy6 = formatter.format(day6);



                        //-----------------for day 7 -------------------

                        Calendar cal7 = Calendar.getInstance();
                        cal7.setTime(firstdate);

                        // add 7 days to current day
                        cal7.add(Calendar.DAY_OF_MONTH, 7);
                        day7 = cal7.getTime();

                        dayy7 = formatter.format(day7);


                        //--------------------------------------------------------------------------------------------------


                        Date finalDay = day0;
                        Date finalDay1 = day1;
                        Date finalDay2 = day2;
                        Date finalDay3 = day3;
                        Date finalDay4 = day4;
                        Date finalDay5 = day5;
                        Date finalDay6 = day6;
                        Date finalDay7 = day7;



                        DatabaseReference refspeding = Drl.child(userID).child("Spending");

                        SimpleDateFormat form0 = new SimpleDateFormat("dd-M-yyyy");

                        String datevaal0 = form0.format(finalDay);
                        String datevaal1 = form0.format(finalDay1);
                        String datevaal2 = form0.format(finalDay2);
                        String datevaal3 = form0.format(finalDay3);
                        String datevaal4 = form0.format(finalDay4);
                        String datevaal5 = form0.format(finalDay5);
                        String datevaal6 = form0.format(finalDay6);
                        String datevaal7 = form0.format(finalDay7);


                        DatabaseReference df0 = refspeding.child(datevaal0);
                        DatabaseReference df1 = refspeding.child(datevaal1);
                        DatabaseReference df2 = refspeding.child(datevaal2);
                        DatabaseReference df3 = refspeding.child(datevaal3);
                        DatabaseReference df4 = refspeding.child(datevaal4);
                        DatabaseReference df5 = refspeding.child(datevaal5);
                        DatabaseReference df6 = refspeding.child(datevaal6);
                        DatabaseReference df7 = refspeding.child(datevaal7);


                        df0.setValue("0");
                        df1.setValue("0");
                        df2.setValue("0");
                        df3.setValue("0");
                        df4.setValue("0");
                        df5.setValue("0");
                        df6.setValue("0");
                        df7.setValue("0");

                    } //------------------end of if the 7th day validation
                    







                    Date day0 = null;
                    String dayy0 = null;
                    

                    Date day1 = null;
                    String dayy1 = null;

                    Date day2 = null;
                    String dayy2= null;;

                    Date day3= null;
                    String dayy3= null;;

                    Date day4= null;
                    String dayy4= null;;

                    Date day5= null;
                    String dayy5= null;;

                    Date day6= null;
                    String dayy6= null;;

                    Date day7= null;
                    String dayy7 = null;


                    //-----------------for day 0 -------------------

                        Calendar cal0 = Calendar.getInstance();
                        cal0.setTime(firstdate);

                        // add 0 days to current day
                        cal0.add(Calendar.DAY_OF_MONTH, 0);
                        day0 = cal0.getTime();

                        dayy0 = formatter.format(day0);






                    //-----------------for day 1 -------------------

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(firstdate);

                        // add 1 days to current day
                        cal.add(Calendar.DAY_OF_MONTH, 1);
                        day1 = cal.getTime();

                         dayy1 = formatter.format(day1);




                    //-----------------for day 2 -------------------



                        Calendar cal2 = Calendar.getInstance();
                        cal2.setTime(firstdate);

                        // add 2 days to current day
                        cal2.add(Calendar.DAY_OF_MONTH, 2);
                        day2 = cal2.getTime();

                        dayy2 = formatter.format(day2);


                    //-----------------for day 3 -------------------


                        Calendar cal3 = Calendar.getInstance();
                        cal3.setTime(firstdate);

                        // add 2 days to current day
                        cal3.add(Calendar.DAY_OF_MONTH, 3);
                        day3 = cal3.getTime();

                        dayy3 = formatter.format(day3);



                    //-----------------for day 4 -------------------


                        Calendar cal4 = Calendar.getInstance();
                        cal4.setTime(firstdate);

                        // add 4 days to current day
                        cal4.add(Calendar.DAY_OF_MONTH, 4);
                        day4 = cal4.getTime();

                        dayy4 = formatter.format(day4);






                    //-----------------for day 5 -------------------

                        Calendar cal5 = Calendar.getInstance();
                        cal5.setTime(firstdate);

                        // add 4 days to current day
                        cal5.add(Calendar.DAY_OF_MONTH, 5);
                        day5 = cal5.getTime();

                        dayy5 = formatter.format(day5);




                    //-----------------for day 6 -------------------


                        Calendar cal6 = Calendar.getInstance();
                        cal6.setTime(firstdate);

                        // add 4 days to current day
                        cal6.add(Calendar.DAY_OF_MONTH, 6);
                        day6 = cal6.getTime();

                        dayy6 = formatter.format(day6);



                    //-----------------for day 7 -------------------

                        Calendar cal7 = Calendar.getInstance();
                        cal7.setTime(firstdate);

                        // add 7 days to current day
                        cal7.add(Calendar.DAY_OF_MONTH, 7);
                        day7 = cal7.getTime();

                        dayy7 = formatter.format(day7);


                    Date finalDay = day0;
                    Date finalDay1 = day1;
                    Date finalDay2 = day2;
                    Date finalDay3 = day3;
                    Date finalDay4 = day4;
                    Date finalDay5 = day5;
                    Date finalDay6 = day6;
                    Date finalDay7 = day7;


                    DrS.child(userID).child("Boolean").addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                       String bool = snapshot.getValue().toString();


                       if (bool.equals("Yes")) {


                           DatabaseReference refspedings = Drl.child(userID).child("Spending");

                           SimpleDateFormat form0 = new SimpleDateFormat("dd-M-yyyy");

                           String datevaall0 = form0.format(finalDay);
                           String datevaall1 = form0.format(finalDay1);
                           String datevaall2 = form0.format(finalDay2);
                           String datevaall3 = form0.format(finalDay3);
                           String datevaall4 = form0.format(finalDay4);
                           String datevaall5 = form0.format(finalDay5);
                           String datevaall6 = form0.format(finalDay6);
                           String datevaall7 = form0.format(finalDay7);


                           DatabaseReference df00 = refspedings.child(datevaall0);
                           DatabaseReference df11 = refspedings.child(datevaall1);
                           DatabaseReference df22 = refspedings.child(datevaall2);
                           DatabaseReference df33 = refspedings.child(datevaall3);
                           DatabaseReference df44 = refspedings.child(datevaall4);
                           DatabaseReference df55 = refspedings.child(datevaall5);
                           DatabaseReference df66 = refspedings.child(datevaall6);
                           DatabaseReference df77 = refspedings.child(datevaall7);


                           df00.setValue("0");
                           df11.setValue("0");
                           df22.setValue("0");
                           df33.setValue("0");
                           df44.setValue("0");
                           df55.setValue("0");
                           df66.setValue("0");
                           df77.setValue("0");

                       }




                         /*  DatabaseReference refspeding = Drl.child(userID).child("Spending");

                           SimpleDateFormat form0 = new SimpleDateFormat("dd-M-yyyy");

                           String datevaal0 = form0.format(finalDay);
                           String datevaal1 = form0.format(finalDay1);
                           String datevaal2 = form0.format(finalDay2);
                           String datevaal3 = form0.format(finalDay3);
                           String datevaal4 = form0.format(finalDay4);
                           String datevaal5 = form0.format(finalDay5);
                           String datevaal6 = form0.format(finalDay6);
                           String datevaal7 = form0.format(finalDay7);


                           DatabaseReference df0 = refspeding.child(datevaal0);
                           DatabaseReference df1 = refspeding.child(datevaal1);
                           DatabaseReference df2 = refspeding.child(datevaal2);
                           DatabaseReference df3 = refspeding.child(datevaal3);
                           DatabaseReference df4 = refspeding.child(datevaal4);
                           DatabaseReference df5 = refspeding.child(datevaal5);
                           DatabaseReference df6 = refspeding.child(datevaal6);
                           DatabaseReference df7 = refspeding.child(datevaal7);   */





                       //---------------------------------------------------nest value event listener


                       SimpleDateFormat form = new SimpleDateFormat("dd-M-yyyy");
                       String dateval0 = form.format(finalDay);



                       Date day1val = finalDay1;
                       Date day2val = finalDay2;
                       Date day3val = finalDay3;
                       Date day4val = finalDay4;
                       Date day5val = finalDay5;
                       Date day6val = finalDay6;
                       Date day7val = finalDay7;



                       Drl.child(userID).child("Spending").child(dateval0).addValueEventListener(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                               String readday0 = snapshot.getValue().toString();


                               SimpleDateFormat form = new SimpleDateFormat("dd-M-yyyy");
                               String dateval1 = form.format(day1val);

                               //-------------------------------------------------------nested


                               Drl.child(userID).child("Spending").child(dateval1).addValueEventListener(new ValueEventListener() {
                                   @Override
                                   public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                                       String readday1 = snapshot.getValue().toString();


                                       SimpleDateFormat form = new SimpleDateFormat("dd-M-yyyy");
                                       String dateval2 = form.format(day2val);

                                       //---------------------------------------------------------nested


                                       Drl.child(userID).child("Spending").child(dateval2).addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                               String readday2 = snapshot.getValue().toString();


                                               SimpleDateFormat form = new SimpleDateFormat("dd-M-yyyy");
                                               String dateval3 = form.format(day3val);


                                               //-----------------------------------------------------nested

                                               Drl.child(userID).child("Spending").child(dateval3).addValueEventListener(new ValueEventListener() {
                                                   @Override
                                                   public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                                       String readday3 = snapshot.getValue().toString();


                                                       SimpleDateFormat form = new SimpleDateFormat("dd-M-yyyy");
                                                       String dateval4 = form.format(day4val);

                                                       //------------------------------------------------------nested

                                                       Drl.child(userID).child("Spending").child(dateval4).addValueEventListener(new ValueEventListener() {
                                                           @Override
                                                           public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                                               String readday4 = snapshot.getValue().toString();


                                                               SimpleDateFormat form = new SimpleDateFormat("dd-M-yyyy");
                                                               String dateval5 = form.format(day5val);

                                                               //----------------------------------------------------Nested

                                                               Drl.child(userID).child("Spending").child(dateval5).addValueEventListener(new ValueEventListener() {
                                                                   @Override
                                                                   public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                                                       String readday5 = snapshot.getValue().toString();


                                                                       SimpleDateFormat form = new SimpleDateFormat("dd-M-yyyy");
                                                                       String dateval6 = form.format(day6val);

                                                                       //------------------------------------------------------nested

                                                                       Drl.child(userID).child("Spending").child(dateval6).addValueEventListener(new ValueEventListener() {
                                                                           @Override
                                                                           public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                                                                               String readday6 = snapshot.getValue().toString();


                                                                               SimpleDateFormat form = new SimpleDateFormat("dd-M-yyyy");
                                                                               String dateval7 = form.format(day7val);

                                                                               //--------------------------------------------------------nested

                                                                               Drl.child(userID).child("Spending").child(dateval7).addValueEventListener(new ValueEventListener() {
                                                                                   @Override
                                                                                   public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                                                                       String readday7 = snapshot.getValue().toString();

                                                                                       day0txt.setText(dateval0);
                                                                                       day1txt.setText(dateval1);
                                                                                       day2txt.setText(dateval2);
                                                                                       day3txt.setText(dateval3);
                                                                                       day4txt.setText(dateval4);
                                                                                       day5txt.setText(dateval5);
                                                                                       day6txt.setText(dateval6);
                                                                                       day7txt.setText(dateval7);



                                                                                       // from here the codes for graph
                                                                                       int y0 = Integer.parseInt(readday0);
                                                                                       int y1 = Integer.parseInt(readday1);
                                                                                       int y2 = Integer.parseInt(readday2);
                                                                                       int y3 = Integer.parseInt(readday3);
                                                                                       int y4 = Integer.parseInt(readday4);
                                                                                       int y5 = Integer.parseInt(readday5);
                                                                                       int y6 = Integer.parseInt(readday6);
                                                                                       int y7 = Integer.parseInt(readday7);


                                                                                       lineEntries = new ArrayList<>();
                                                                                       lineEntries.add(new Entry(0f, y0));
                                                                                       lineEntries.add(new Entry(1f, y1));
                                                                                       lineEntries.add(new Entry(2f, y2));
                                                                                       lineEntries.add(new Entry(3f, y3));
                                                                                       lineEntries.add(new Entry(4f, y4));
                                                                                       lineEntries.add(new Entry(5f, y5));
                                                                                       lineEntries.add(new Entry(6f, y6));
                                                                                       lineEntries.add(new Entry(7f, y7));

                                                                                       int yColorValue = Color.GRAY;


                                                                                       lineDataSet = new LineDataSet(lineEntries, "List chart");
                                                                                       lineData = new LineData(lineDataSet);
                                                                                       lineChart.setData(lineData);
                                                                                       lineDataSet.setColors(Color.GRAY);
                                                                                       lineDataSet.setDrawFilled(true);
                                                                                       lineDataSet.setFillColor(yColorValue);
                                                                                       lineDataSet.setValueTextColor(Color.BLACK);
                                                                                       lineDataSet.setValueTextSize(18f);








                                                                                   }

                                                                                   @Override
                                                                                   public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                                                                   }
                                                                               });


                                                                           }

                                                                           @Override
                                                                           public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                                                           }
                                                                       });

                                                                   }

                                                                   @Override
                                                                   public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                                                   }
                                                               });


                                                           }

                                                           @Override
                                                           public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                                           }
                                                       });

                                                   }

                                                   @Override
                                                   public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                                   }
                                               });


                                           }

                                           @Override
                                           public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                           }
                                       });


                                   }

                                   @Override
                                   public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                   }
                               });


                           }

                           @Override
                           public void onCancelled(@NonNull @NotNull DatabaseError error) {

                           }
                       });


                   }

                   @Override
                   public void onCancelled(@NonNull @NotNull DatabaseError error) {

                   }
               });
















                } catch (ParseException e) {
                    e.printStackTrace();
                }

}

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });




                return m;


    }

}
