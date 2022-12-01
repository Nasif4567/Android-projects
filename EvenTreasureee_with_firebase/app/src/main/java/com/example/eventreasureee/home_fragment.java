package com.example.eventreasureee;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class  home_fragment extends Fragment {





    private FirebaseUser user;
    private DatabaseReference Dr;
    private String userID;

    TextView Entpertxt;
    TextView Outfpertxt;
    TextView Gropertxt;
    TextView Healthpertxt;
    TextView Transpertxt;
    TextView Billspertxt;
    TextView Selfpertxt;
    TextView Subspertxt;

    TextView demo;

    // the the declaration of the amount code

    TextView Entamount;
    TextView Outfamount;
    TextView Groamount;
    TextView Healthamount;
    TextView Billamount;
    TextView Subsamount;
    TextView Transamount;
    TextView Selfamount;
    TextView WWagestext ;
    TextView MMoneytext ;

    // the pogressbar declaration
    ProgressBar Entpbar;
    ProgressBar Oufpbar;
    ProgressBar Selfbar;
    ProgressBar Healthbar;
    ProgressBar Subsbar;
    ProgressBar Transbar;
    ProgressBar Billsbars;
    ProgressBar Grobar;
    LinearLayout Layouthome;



    @Nullable

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        Layouthome = (LinearLayout) v.findViewById(R.id.homelayout);




        ConnectivityManager CM = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo WN = CM.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo MN = CM.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if( (     WN != null && WN.isConnected() || (MN != null && MN.isConnected())    ) ) {
            
        }

        else {

            startActivity(new Intent(getActivity() , NoConnection.class));
        }






        user = FirebaseAuth.getInstance().getCurrentUser();
        Dr = FirebaseDatabase.getInstance().getReference("Bar");
        userID = user.getUid();
        BarChart bar1 =(BarChart) v.findViewById(R.id.barchat);
        
        
        //the variable declaration -------------------

        Entpertxt = (TextView) v.findViewById(R.id.entpertxt);
        Outfpertxt = (TextView) v.findViewById(R.id.outfpertxt);
        Gropertxt = (TextView) v.findViewById(R.id.gropertxt);
        Healthpertxt = (TextView) v.findViewById(R.id.healtextper);
        Transpertxt = (TextView) v.findViewById(R.id.transtxtper);
        Subspertxt = (TextView)  v.findViewById(R.id.subscriptionpertxt);
        Billspertxt = (TextView) v.findViewById(R.id.billtxtper);
        Selfpertxt = (TextView) v.findViewById(R.id.selftxtper);
        WWagestext = (TextView) v.findViewById(R.id.wagestxt);
        MMoneytext = (TextView) v.findViewById(R.id.moneysavetxt);


        demo = (TextView) v.findViewById(R.id.dpercentagetxt);


        // declareration of amount text fields

       Entamount = (TextView) v.findViewById(R.id.entamount) ;
       Outfamount = (TextView) v.findViewById(R.id.outfamount);
       Groamount = (TextView) v.findViewById(R.id.groamount);
       Healthamount = (TextView) v.findViewById(R.id.healthamount);
       Subsamount = (TextView) v.findViewById(R.id.subsamount);
       Selfamount = (TextView) v.findViewById(R.id.selfamount);
       Billamount = (TextView) v.findViewById(R.id.billsamount);
       Transamount = (TextView) v.findViewById((R.id.transamount));

       // declaration of pogressTextfield;

        Entpbar = (ProgressBar)  v.findViewById(R.id.entpbar);
        Oufpbar = (ProgressBar)  v.findViewById(R.id.outpbar);
        Selfbar = (ProgressBar) v.findViewById(R.id.selfbar);
        Healthbar = (ProgressBar)  v.findViewById(R.id.healthbar);
        Billsbars = (ProgressBar)  v.findViewById(R.id.billsbar);
        Transbar = (ProgressBar) v.findViewById(R.id.transbar);
        Subsbar = (ProgressBar) v.findViewById(R.id.subsbar);
        Grobar = (ProgressBar)  v.findViewById(R.id.grobar);

        


        Dr.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {








                ArrayList<BarEntry> visitor = new ArrayList<>();

                for (DataSnapshot d : snapshot.getChildren() ) {
                    records info = snapshot.getValue(records.class);
                    recordx1 info1 = snapshot.getValue(recordx1.class);
                    recordx2 info2 = snapshot.getValue(recordx2.class);
                    recordx3 info3 = snapshot.getValue(recordx3.class);
                    recordx4 info4 = snapshot.getValue(recordx4.class);
                    recordx5 info5 = snapshot.getValue(recordx5.class);
                    recordx6 info6 = snapshot.getValue(recordx6.class);
                    recordx7 info7 = snapshot.getValue(recordx7.class);


                    visitor.add(new BarEntry(info.y,info.x));
                    visitor.add(new BarEntry(info1.y1,info1 .x1));
                    visitor.add(new BarEntry(info2.y2,info2.x2));
                    visitor.add(new BarEntry(info3.y3,info3.x3));
                    visitor.add(new BarEntry(info4.y4,info4.x4));
                    visitor.add(new BarEntry(info5.y5,info5.x5));
                    visitor.add(new BarEntry(info6.y6,info6.x6));
                    visitor.add(new BarEntry(info7.y7,info7.x7));

                }


                BarDataSet bardatas = new BarDataSet(visitor, "My spendings");
                bardatas.setColors(ColorTemplate.MATERIAL_COLORS );
                bardatas.setValueTextColor(Color.BLACK);
                bardatas.setValueTextSize(16f);


                BarData bardata = new BarData(bardatas);
                bar1.setFitBars(true);
                bar1.setData(bardata);
                bar1.getDescription().setText("bar chart");
                bar1.animateY(2000);







            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


        Dr.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                recordXwages wages = snapshot.getValue(recordXwages.class); // for wages


                records entertainemnt = snapshot.getValue(records.class); //for entertainment
                recordx1 Groceries = snapshot.getValue(recordx1.class); //for food groceries
                recordx2 OutF = snapshot.getValue(recordx2.class); // for outside food
                recordx3 health = snapshot.getValue(recordx3.class); // for health care
                recordx4 transport = snapshot.getValue(recordx4.class); // for transport
                recordx5 bills = snapshot.getValue(recordx5.class); // for bills
                recordx6 subscription = snapshot.getValue(recordx6.class); // for subscription
                recordx7 selfshopping = snapshot.getValue(recordx7.class); // for selp shopping


                recordsXsavings savings = snapshot.getValue(recordsXsavings.class); // for wages


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


                if (entcost == 0 && grocercost == 0 && outfcost == 0 && healthcost == 0 && transportcost == 0 && billscost == 0 && subscriptioncost==0 && selfcost == 0 && savingmoney == 0 && wagesmoney == 0) {
                    LinearLayout alertlayout = (LinearLayout) v.findViewById(R.id.alertlayout);
                    TextView alerttext = (TextView)  v.findViewById(R.id.alertext);

                    alertlayout.setVisibility(View.VISIBLE);
                    alerttext.setVisibility(View.VISIBLE);
                }


                float totalmakeup = entcost + grocercost + outfcost + healthcost + transportcost + billscost + subscriptioncost + selfcost;
                float alreadysaved = wagesmoney - totalmakeup;
                float sumofmoney = totalmakeup + savingmoney + alreadysaved;
                float dpf = sumofmoney - wagesmoney;

                //The total percentage makeup
                // num = Math.round(num)
                // the validation must be done for insert saving money

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
                WWagestext.setText(String.valueOf(wagesmoney));
                MMoneytext.setText(String.valueOf(savingmoney));
                demo.setText(dp);

                //--------------------------------------the initial value variable declareations


                float entper2 = entper;
                float outfper2 = outfper;
                float billper2 = billper;
                float healthper2 = healthper;
                float transper2 = transper;
                float subsper2 = subsper;
                float groper2 = groper;
                float selfper2 = selfper;


                float entper3 = entper;
                float outper3 = outfper;
                float billper3 = billper;
                float healthper3 = healthper;
                float transper3 = transper;
                float subsper3 = subsper;
                float groper3 = groper;
                float selfper3 = selfper;


                float entper4 = entper;
                float outper4 = outfper;
                float billper4 = billper;
                float healthper4 = healthper;
                float transper4 = transper;
                float subsper4 = subsper;
                float groper4 = groper;
                float selfper4 = selfper;


                float entper5 = entper;
                float outper5 = outfper;
                float billper5 = billper;
                float healthper5 = healthper;
                float transper5 = transper;
                float subsper5 = subsper;
                float groper5 = groper;
                float selfper5 = selfper;


                float entper6 = entper;
                float outper6 = outfper;
                float billper6 = billper;
                float healthper6 = healthper;
                float transper6 = transper;
                float subsper6 = subsper;
                float groper6 = groper;
                float selfper6 = selfper;


                float entper7 = entper;
                float outper7 = outfper;
                float billper7 = billper;
                float healthper7 = healthper;
                float transper7 = transper;
                float subsper7 = subsper;
                float groper7 = groper;
                float selfper7 = selfper;


                float entper8 = entper;
                float outper8 = outfper;
                float billper8 = billper;
                float healthper8 = healthper;
                float transper8 = transper;
                float subsper8 = subsper;
                float groper8 = groper;
                float selfper8 = selfper;




                // the logical operation for the each dpf percentage this will have up to 30 percent but for now just just upto 15 percent


                //------------- percent rate - 1 %

                if (dpfper == 1 && entper >= 1) {
                    float entper1 = entper - 1;
                    Entpertxt.setText("-1%");
                }

                if (dpfper == 1 && entper == 0 && outfper >= 1) {
                    float outfper1 = outfper - 1;
                    Outfpertxt.setText("-1%");
                }

                if (dpfper == 1 && entper == 0 && outfper == 0 && selfper >= 1) {
                    float selfper1 = selfper - 1;
                    Selfpertxt.setText("1%");
                }

                if (dpfper == 1 && entper == 0 && outfper == 0 && selfper == 0 && groper >= 1) {
                    float groper1 = groper - 1;
                    Gropertxt.setText("1%");
                }

                if (dpfper == 1 && entper == 0 && outfper == 0 && selfper == 0 && groper == 0 && billper >= 1) {
                    float billsper1 = billper - 1;
                    Billspertxt.setText("1%");
                }

                if (dpfper == 1 && entper == 0 && outfper == 0 && selfper == 0 && groper == 0 && billper == 0 && subsper >= 1) {
                    float subsper1 = subsper - 1;
                    Subspertxt.setText("1%");
                }

                if (dpfper == 1 && entper == 0 && outfper == 0 && selfper == 0 && groper == 0 && billper == 0 && subsper == 0 && transper >= 1) {
                    float transper1 = transper - 1;
                    Transpertxt.setText("1%");
                }

                if (dpfper == 1 && entper == 0 && outfper == 0 && selfper == 0 && groper == 0 && billper == 0 && subsper == 0 && transper == 0 && healthper >= 1) {
                    float healthper1 = healthper - 1;
                    Healthpertxt.setText("1%");
                }

                if (dpfper == 1 && entper == 0 && outfper == 0 && selfper == 0 && groper == 0 && billper == 0 && subsper == 0 && transper == 0 && healthper == 0) {

                    Healthpertxt.setText("Error");
                }


                //----------------percent rate 2%--------------------------------------------------------------------------------

                // formula that will be used to to add up the leftover value
                float dpfround = dpfper;
                dpfround = Math.round(dpfround);


                if (dpfround < dpfper) {
                    dpfround = dpfround; // keeps the value as it is
                } else {
                    dpfround = dpfround - 1;
                } // if the dpfround is more than 0.5 then it the number bigger so thats why it is minus 1


                float dpfsubdif = (dpfperactual - dpfround);  // for the subtration from rounded value from actual value
                float dpfmulvalper = (dpfsubdif / 100) * sumofmoney; // this value should be used to add


//-------------------------------------------------------------------------------------------------------------------------------
                float rest;
                float used = 1;
//----------------------------------------------------------------------------------------------------------------------------------------

                if (dpfper >= 2 && dpfper < 3 && entper >= (2 + dpfsubdif)) {
                    float entper0 = entper - (2 + dpfsubdif);
                    float dpfsubdif2 = 2 + dpfsubdif;
                    String cutvalue = String.valueOf(dpfsubdif2);
                    Entpertxt.setText(cutvalue);

                    // the set value of amount
                    float amt = dpfsubdif2 / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Entamount.setText(amts);

                }


                if (dpfper >= 2 && dpfper < 3 && entper < (2 + dpfsubdif)) {  // nested if------------

                    rest = (2 + dpfsubdif) - entper;
                    String subentper = String.valueOf(entper);
                    Entpertxt.setText(subentper);   // showing to entper value in the text view

                    // the set value of amount
                    float amt = entper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Entamount.setText(amts);

                    if (outfper >= rest) {
                        float outfper0 = outfper - rest;
                        String rea = String.valueOf(rest);
                        Outfpertxt.setText(rea);
                        used = 0;

                        // the set value of amount
                        float am = rest / 100 * sumofmoney;
                        String amo = String.valueOf(am);
                        Outfamount.setText(amo);
                    }

                    if (outfper < rest && selfper >= rest && used != 0) {
                        float selfper0 = selfper - rest;
                        String rea = String.valueOf(rest);
                        Selfpertxt.setText(rea);
                        used = 0;

                        // the set value of amount
                        float am = rest / 100 * sumofmoney;
                        String amo = String.valueOf(am);
                        Selfamount.setText(amo);
                    }

                    if (selfper < rest && groper >= rest && used != 0) {
                        float groper0 = groper - rest;
                        String rea = String.valueOf(rest);
                        Gropertxt.setText(rea);
                        used = 0;

                        // the set value of amount
                        float am = rest / 100 * sumofmoney;
                        String amo = String.valueOf(am);
                        Groamount.setText(amo);
                    }


                    if (groper < rest && billper >= rest && used != 0) {
                        float billper0 = billper - rest;
                        String rea = String.valueOf(rest);
                        Billspertxt.setText(rea);
                        used = 0;

                        // the set value of amount
                        float am = rest / 100 * sumofmoney;
                        String amo = String.valueOf(am);
                        Billamount.setText(amo);
                    }


                    if (billper < rest && subsper >= rest && used != 0) {
                        float subsper0 = subsper - rest;
                        String rea = String.valueOf(rest);
                        Subspertxt.setText(rea);
                        used = 0;

                        // the set value of amount
                        float am = rest / 100 * sumofmoney;
                        String amo = String.valueOf(am);
                        Subsamount.setText(amo);
                    }


                    if (subsper < rest && transper >= rest && used != 0) {
                        float transper0 = transper - rest;
                        String rea = String.valueOf(rest);
                        Transpertxt.setText(rea);
                        used = 0;

                        // the set value of amount
                        float am = rest / 100 * sumofmoney;
                        String amo = String.valueOf(am);
                        Transamount.setText(amo);
                    }


                    if (transper < rest && healthper >= rest && used != 0) {
                        float Healthper0 = healthper - rest;
                        String rea = String.valueOf(rest);
                        Healthpertxt.setText(rea);
                        used = 0;

                        // the set value of amount
                        float am = rest / 100 * sumofmoney;
                        String amo = String.valueOf(am);
                        Healthamount.setText(amo);
                    }

                }


//--------------------------------------------------------------------------------------------------work on 3%----------------------


                //------------------------------------------------2% of three percent-------------------working on 3%-----

                float used2 = 1;


                if (dpfper >= 3 && dpfper < 4 && entper >= (2 + (dpfsubdif / 2))) {
                    entper2 = entper - (2 + (dpfsubdif / 2));
                    float cutvalue = (2 + (dpfsubdif / 2));
                    String getval = String.valueOf(cutvalue);
                    Entpertxt.setText(getval);


                    // set value of amount
                    float amt = (cutvalue / 100) * sumofmoney;
                    String amts = String.valueOf(amt);
                    Entamount.setText(amts);
                }


                if (dpfper >= 3 && dpfper < 4 && entper < (2 + (dpfsubdif / 2))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 2)) - entper;
                    String subentper = String.valueOf(entper);
                    Entpertxt.setText(subentper);

                    //set value of amount
                    float ammt = entper / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Entamount.setText((amtss));

                    if (outfper >= rest) {
                        outfper2 = outfper - rest;

                        String rea = String.valueOf(rest);
                        Outfpertxt.setText(rea);
                        used2 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Outfamount.setText(amts);
                    }


                    if (outfper < rest && selfper >= rest && used2 != 0) {
                        selfper2 = selfper - rest;
                        String rea = String.valueOf(rest);
                        Selfpertxt.setText(rea);
                        used2 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);
                    }

                    if (selfper < rest && groper >= rest && used2 != 0) {
                        groper2 = groper - rest;
                        String rea = String.valueOf(rest);
                        Gropertxt.setText(rea);
                        used2 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper < rest && billper >= rest && used2 != 0) {
                        billper2 = billper - rest;
                        String rea = String.valueOf(rest);
                        Billspertxt.setText(rea);
                        used2 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }

                    if (billper < rest && subsper >= rest && used2 != 0) {
                        subsper2 = subsper - rest;
                        String rea = String.valueOf(rest);
                        Subspertxt.setText(rea);
                        used2 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper < rest && transper >= rest && used2 != 0) {
                        transper2 = transper - rest;
                        String rea = String.valueOf(rest);
                        Transpertxt.setText(rea);
                        used2 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper < rest && healthper >= rest && used2 != 0) {
                        healthper2 = healthper - rest;
                        String rea = String.valueOf(rest);
                        Healthpertxt.setText(rea);
                        used2 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }


                    //----------------nested if
                }


                float used1 = 1; //// sees if the code is used


                //-------------------------------------------------work on other operations of 1% out of 3%

                if (dpfper >= 3 && dpfper < 4 && outfper2 >= (1 + (dpfsubdif / 2))) {
                    outper3 = outfper2 - (1 + (dpfsubdif / 2));
                    String textsum = String.valueOf(Outfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (1 + (dpfsubdif / 2));
                    String getval = String.valueOf(totalsumper);
                    Outfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Outfamount.setText(amts);
                }


                if (dpfper >= 3 && dpfper < 4 && outfper2 < (1 + (dpfsubdif / 2))) {  // nested if------------
                    rest = (1 + (dpfsubdif / 2)) - outfper2;
                    String subentper = String.valueOf(outfper2);
                    Outfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = entper / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Outfamount.setText((amtss));

                    if (selfper2 >= rest) {
                        selfper3 = selfper2 - rest;

                        String textsumw = String.valueOf(Selfpertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Selfpertxt.setText(totalsumperwstring);
                        used1 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);

                    }

                    if (selfper2 < rest && groper2 >= rest && used1 != 0) {
                        groper3 = groper2 - rest;

                        String textsum = String.valueOf(Gropertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Gropertxt.setText(totalsumperstring);
                        used1 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper2 < rest && billper2 >= rest && used1 != 0) {
                        billper3 = billper2 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText("-" + totalsumperstring + "%");
                        used1 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper2 < rest && subsper2 >= rest && used1 != 0) {
                        subsper3 = subsper2 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText("-" + totalsumperstring + "%");
                        used1 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper2 < rest && transper2 >= rest && used1 != 0) {
                        transper3 = transper2 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText("-" + totalsumperstring + "%");
                        used1 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper2 < rest && healthper2 >= rest && used1 != 0) {
                        healthper3 = healthper2 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText("-" + totalsumperstring + "%");
                        used1 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }


                    //----------------nested if
                }


// percentage rate 4% --------------------------------------------------------------------------------------------------------


                //------------------------------------------------2% of  4%-------------------working on 4%-----

                float used4 = 1;

                if (dpfper >= 4 && dpfper < 5 && entper >= (2 + (dpfsubdif / 2))) {
                    entper2 = entper - (2 + (dpfsubdif / 2));
                    // use this later -- String getvalueoftxt =  String.valueOf(Entpertxt.getText());
                    float cutvalue = (2 + (dpfsubdif / 2));
                    String getval = String.valueOf(cutvalue);
                    Entpertxt.setText(getval);


                    // set value of amount
                    float amt = cutvalue / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Entamount.setText(amts);
                }


                if (dpfper >= 4 && dpfper < 5 && entper < (2 + (dpfsubdif / 2))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 2)) - entper;
                    String subentper = String.valueOf(entper);
                    Entpertxt.setText(subentper);

                    //set value of amount
                    float ammt = entper / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Entamount.setText((amtss));

                    if (outfper >= rest) {
                        outfper2 = outfper - rest;

                        String rea = String.valueOf(rest);
                        Outfpertxt.setText(rea);
                        used4 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Outfamount.setText(amts);
                    }


                    if (outfper < rest && selfper >= rest && used4 != 0) {
                        selfper2 = selfper - rest;
                        String rea = String.valueOf(rest);
                        Selfpertxt.setText(rea);
                        used4 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);
                    }

                    if (selfper < rest && groper >= rest && used4 != 0) {
                        groper2 = groper - rest;
                        String rea = String.valueOf(rest);
                        Gropertxt.setText(rea);
                        used4 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper < rest && billper >= rest && used4 != 0) {
                        billper2 = billper - rest;
                        String rea = String.valueOf(rest);
                        Billspertxt.setText(rea);
                        used4 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }

                    if (billper < rest && subsper >= rest && used4 != 0) {
                        subsper2 = subsper - rest;
                        String rea = String.valueOf(rest);
                        Subspertxt.setText(rea);
                        used4 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper < rest && transper >= rest && used4 != 0) {
                        transper2 = transper - rest;
                        String rea = String.valueOf(rest);
                        Transpertxt.setText(rea);
                        used4 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper < rest && healthper >= rest && used4 != 0) {
                        healthper2 = healthper - rest;
                        String rea = String.valueOf(rest);
                        Healthpertxt.setText(rea);
                        used4 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }


                    if (healthper < rest && entper >= rest && used4 != 0) {
                        entper2 = entper - rest;
                        String rea = String.valueOf(rest);
                        Entpertxt.setText(rea);
                        used4 = 0;


                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);

                    }


                    //----------------nested if
                }


                //-----------------------------------------------------------working on 4%----- this the 2nd one ----

                float used42 = 1; //// sees if the code is used


                //-------------------------------------------------work on other operations another 2% out of 4------

                if (dpfper >= 4 && dpfper < 5 && outfper2 >= (2 + (dpfsubdif / 2))) {
                    outper3 = outfper2 - (2 + (dpfsubdif / 2));
                    String textsum = String.valueOf(Outfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (2 + (dpfsubdif / 2));
                    String getval = String.valueOf(totalsumper);
                    Outfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Outfamount.setText(amts);
                }


                if (dpfper >= 4 && dpfper < 5 && outfper2 < (2 + (dpfsubdif / 2))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 2)) - outfper2;
                    String subentper = String.valueOf(outfper2);
                    Outfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = outfper2 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Outfamount.setText((amtss));

                    if (selfper2 >= rest) {
                        selfper3 = selfper2 - rest;

                        String textsumw = String.valueOf(Selfpertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Selfpertxt.setText(totalsumperwstring);
                        used42 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);

                    }

                    if (selfper2 < rest && groper2 >= rest && used42 != 0) {
                        groper3 = groper2 - rest;

                        String textsum = String.valueOf(Gropertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Gropertxt.setText(totalsumperstring);
                        used42 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper2 < rest && billper2 >= rest && used42 != 0) {
                        billper3 = billper2 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used42 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper2 < rest && subsper2 >= rest && used42 != 0) {
                        subsper3 = subsper2 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used42 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper2 < rest && transper2 >= rest && used42 != 0) {
                        transper3 = transper2 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used42 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper2 < rest && healthper2 >= rest && used42 != 0) {
                        healthper3 = healthper2 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used42 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper2 < rest && entper2 >= rest && used42 != 0) {
                        entper3 = entper2 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //-----------------------------------rate 5% -----------------------------------------------------------------------------------------


                // --------------------work on 2% of of 5 %


                float used5 = 1;

                if (dpfper >= 5 && dpfper < 6 && entper >= (2 + (dpfsubdif / 2))) {
                    entper2 = entper - (2 + (dpfsubdif / 2));
                    float cutvalue = (2 + (dpfsubdif / 2));
                    String getval = String.valueOf(cutvalue);
                    Entpertxt.setText(getval);


                    // set value of amount
                    float amt = cutvalue / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Entamount.setText(amts);
                }


                if (dpfper >= 5 && dpfper < 6 && entper < (2 + (dpfsubdif / 2))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 2)) - entper;
                    String subentper = String.valueOf(entper);
                    Entpertxt.setText(subentper);

                    //set value of amount
                    float ammt = entper / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Entamount.setText((amtss));

                    if (outfper >= rest) {
                        outfper2 = outfper - rest;

                        String rea = String.valueOf(rest);
                        Outfpertxt.setText(rea);
                        used5 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Outfamount.setText(amts);
                    }


                    if (outfper < rest && selfper >= rest && used5 != 0) {
                        selfper2 = selfper - rest;
                        String rea = String.valueOf(rest);
                        Selfpertxt.setText(rea);
                        used5 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);
                    }

                    if (selfper < rest && groper >= rest && used5 != 0) {
                        groper2 = groper - rest;
                        String rea = String.valueOf(rest);
                        Gropertxt.setText(rea);
                        used5 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper < rest && billper >= rest && used5 != 0) {
                        billper2 = billper - rest;
                        String rea = String.valueOf(rest);
                        Billspertxt.setText(rea);
                        used5 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }

                    if (billper < rest && subsper >= rest && used5 != 0) {
                        subsper2 = subsper - rest;
                        String rea = String.valueOf(rest);
                        Subspertxt.setText(rea);
                        used5 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper < rest && transper >= rest && used5 != 0) {
                        transper2 = transper - rest;
                        String rea = String.valueOf(rest);
                        Transpertxt.setText(rea);
                        used5 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper < rest && healthper >= rest && used5 != 0) {
                        healthper2 = healthper - rest;
                        String rea = String.valueOf(rest);
                        Healthpertxt.setText(rea);
                        used5 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }


                    if (healthper2 < rest && entper2 >= rest && used5 != 0) {
                        entper2 = entper - rest;
                        String rea = String.valueOf(rest);
                        Entpertxt.setText(rea);
                        used5 = 0;


                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);

                    }


                    //----------------nested if
                }


                //-------------------------------------------------work on other operations another 2% out of 5%------
                float used52 = 1; //// sees if the code is used


                if (dpfper >= 5 && dpfper < 6 && outfper2 >= (2 + (dpfsubdif / 2))) {
                    outper3 = outfper2 - (2 + (dpfsubdif / 2));
                    String textsum = String.valueOf(Outfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (2 + (dpfsubdif / 2));
                    String getval = String.valueOf(totalsumper);
                    Outfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Outfamount.setText(amts);
                }

                if (dpfper >= 5 && dpfper < 6 && outfper2 < (2 + (dpfsubdif / 2))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 2)) - outfper2;
                    String subentper = String.valueOf(outfper2);
                    Outfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = outfper2 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Outfamount.setText((amtss));

                    if (selfper2 >= rest) {
                        selfper3 = selfper2 - rest;

                        String textsumw = String.valueOf(Selfpertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Selfpertxt.setText(totalsumperwstring);
                        used52 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);

                    }

                    if (selfper2 < rest && groper2 >= rest && used52 != 0) {
                        groper3 = groper2 - rest;

                        String textsum = String.valueOf(Gropertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Gropertxt.setText(totalsumperstring);
                        used52 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper2 < rest && billper2 >= rest && used52 != 0) {
                        billper3 = billper2 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used52 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper2 < rest && subsper2 >= rest && used52 != 0) {
                        subsper3 = subsper2 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used52 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper2 < rest && transper2 >= rest && used52 != 0) {
                        transper3 = transper2 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used52 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper2 < rest && healthper2 >= rest && used52 != 0) {
                        healthper3 = healthper2 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used52 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper2 < rest && entper2 >= rest && used52 != 0) {
                        entper3 = entper2 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //------------work 1% of out of 5 percent----------------------------------------------------------------------------

                float used51 = 1; //// sees if the code is used


                //-------------------------------------------------work on other operations another 1% out of 5%------

                if (dpfper >= 5 && dpfper < 6 && selfper3 >= 1) {
                    selfper4 = selfper3 - 1;
                    String textsum = String.valueOf(Selfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + 1;
                    String getval = String.valueOf(totalsumper);
                    Selfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Selfamount.setText(amts);
                }


                if (dpfper >= 5 && dpfper < 6 && selfper3 < 1) {  // nested if------------
                    rest = 1 - selfper3;
                    String subentper = String.valueOf(selfper3);
                    Selfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = selfper3 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Selfamount.setText((amtss));

                    if (groper3 >= rest) {
                        groper4 = groper3 - rest;

                        String textsumw = String.valueOf(Gropertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Gropertxt.setText(totalsumperwstring);
                        used51 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);

                    }


                    if (groper3 < rest && billper3 >= rest && used51 != 0) {
                        billper4 = billper3 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used51 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper3 < rest && subsper3 >= rest && used51 != 0) {
                        subsper4 = subsper3 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used51 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper3 < rest && transper3 >= rest && used51 != 0) {
                        transper4 = transper3 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used51 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper3 < rest && healthper3 >= rest && used51 != 0) {
                        healthper4 = healthper3 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used51 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper3 < rest && entper3 >= rest && used51 != 0) {
                        entper4 = entper3 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //------------------------------------------------------------rate 6% --------------------------------------------------------


                // 2% out of 6%-----------------------------------------------------------------------------


                float used6 = 1;

                if (dpfper >= 6 && dpfper < 7 && entper >= (2 + (dpfsubdif / 3))) {
                    entper2 = entper - (2 + (dpfsubdif / 3));
                    float cutvalue = (2 + (dpfsubdif / 3));
                    String getval = String.valueOf(cutvalue);
                    Entpertxt.setText(getval);


                    // set value of amount
                    float amt = cutvalue / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Entamount.setText(amts);
                }


                if (dpfper >= 6 && dpfper < 7 && entper < (2 + (dpfsubdif / 3))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 3)) - entper;
                    String subentper = String.valueOf(entper);
                    Entpertxt.setText(subentper);

                    //set value of amount
                    float ammt = entper / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Entamount.setText((amtss));

                    if (outfper >= rest) {
                        outfper2 = outfper - rest;

                        String rea = String.valueOf(rest);
                        Outfpertxt.setText(rea);
                        used6 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Outfamount.setText(amts);
                    }


                    if (outfper < rest && selfper >= rest && used6 != 0) {
                        selfper2 = selfper - rest;
                        String rea = String.valueOf(rest);
                        Selfpertxt.setText(rea);
                        used6 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);
                    }

                    if (selfper < rest && groper >= rest && used6 != 0) {
                        groper2 = groper - rest;
                        String rea = String.valueOf(rest);
                        Gropertxt.setText(rea);
                        used6 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper < rest && billper >= rest && used6 != 0) {
                        billper2 = billper - rest;
                        String rea = String.valueOf(rest);
                        Billspertxt.setText(rea);
                        used6 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }

                    if (billper < rest && subsper >= rest && used6 != 0) {
                        subsper2 = subsper - rest;
                        String rea = String.valueOf(rest);
                        Subspertxt.setText(rea);
                        used6 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper < rest && transper >= rest && used6 != 0) {
                        transper2 = transper - rest;
                        String rea = String.valueOf(rest);
                        Transpertxt.setText(rea);
                        used6 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper < rest && healthper >= rest && used6 != 0) {
                        healthper2 = healthper - rest;
                        String rea = String.valueOf(rest);
                        Healthpertxt.setText(rea);
                        used6 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }


                    if (healthper2 < rest && entper2 >= rest && used6 != 0) {
                        entper2 = entper - rest;
                        String rea = String.valueOf(rest);
                        Entpertxt.setText(rea);
                        used6 = 0;


                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);

                    }


                    //----------------nested if
                }


                //-------------------------------------------------work on other operations 2nd 2% out of 6%------
                float used62 = 1; //// sees if the code is used


                if (dpfper >= 6 && dpfper < 7 && outfper2 >= (2 + (dpfsubdif / 3))) {
                    outper3 = outfper2 - (2 + (dpfsubdif / 3));
                    String textsum = String.valueOf(Outfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (2 + (dpfsubdif / 3));
                    String getval = String.valueOf(totalsumper);
                    Outfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Outfamount.setText(amts);
                }

                if (dpfper >= 6 && dpfper < 7 && outfper2 < (2 + (dpfsubdif / 3))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 3)) - outfper2;
                    String subentper = String.valueOf(outfper2);
                    Outfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = outfper2 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Outfamount.setText((amtss));

                    if (selfper2 >= rest) {
                        selfper3 = selfper2 - rest;

                        String textsumw = String.valueOf(Selfpertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Selfpertxt.setText(totalsumperwstring);
                        used62 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);

                    }

                    if (selfper2 < rest && groper2 >= rest && used62 != 0) {
                        groper3 = groper2 - rest;

                        String textsum = String.valueOf(Gropertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Gropertxt.setText(totalsumperstring);
                        used62 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper2 < rest && billper2 >= rest && used62 != 0) {
                        billper3 = billper2 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used62 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper2 < rest && subsper2 >= rest && used62 != 0) {
                        subsper3 = subsper2 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used62 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper2 < rest && transper2 >= rest && used62 != 0) {
                        transper3 = transper2 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used62 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper2 < rest && healthper2 >= rest && used62 != 0) {
                        healthper3 = healthper2 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used62 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper2 < rest && entper2 >= rest && used62 != 0) {
                        entper3 = entper2 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //------------work 3rd  2% of out of 6% percent----------------------------------------------------------------------------

                float used622 = 1; //// sees if the code is used


                //-------------------------------------------------work on other operations another 1% out of 6%------

                if (dpfper >= 6 && dpfper < 7 && selfper3 >= (2 + (dpfsubdif / 3))) {
                    selfper4 = selfper3 - (2 + (dpfsubdif / 3));
                    String textsum = String.valueOf(Selfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (2 + (dpfsubdif / 3));
                    String getval = String.valueOf(totalsumper);
                    Selfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Selfamount.setText(amts);
                }


                if (dpfper >= 5 && dpfper < 6 && selfper3 < (2 + (dpfsubdif / 3))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 3)) - selfper3;
                    String subentper = String.valueOf(selfper3);
                    Selfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = selfper3 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Selfamount.setText((amtss));

                    if (groper3 >= rest) {
                        groper4 = groper3 - rest;

                        String textsumw = String.valueOf(Gropertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Gropertxt.setText(totalsumperwstring);
                        used622 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);

                    }


                    if (groper3 < rest && billper3 >= rest && used622 != 0) {
                        billper4 = billper3 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used622 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper3 < rest && subsper3 >= rest && used622 != 0) {
                        subsper4 = subsper3 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used622 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper3 < rest && transper3 >= rest && used622 != 0) {
                        transper4 = transper3 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used622 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper3 < rest && healthper3 >= rest && used622 != 0) {
                        healthper4 = healthper3 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used622 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper3 < rest && entper3 >= rest && used622 != 0) {
                        entper4 = entper3 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                // work on 7% rate -----------------------------------------------------------------------------------------------


// 2% out of 7%-----------------------------------------------------------------------------


                float used7 = 1;

                if (dpfper >= 7 && dpfper < 8 && entper >= (2 + (dpfsubdif / 3))) {
                    entper2 = entper - (2 + (dpfsubdif / 3));
                    float cutvalue = (2 + (dpfsubdif / 3));
                    String getval = String.valueOf(cutvalue);
                    Entpertxt.setText(getval);


                    // set value of amount
                    float amt = cutvalue / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Entamount.setText(amts);
                }


                if (dpfper >= 7 && dpfper < 8 && entper < (2 + (dpfsubdif / 3))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 3)) - entper;
                    String subentper = String.valueOf(entper);
                    Entpertxt.setText(subentper);

                    //set value of amount
                    float ammt = entper / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Entamount.setText((amtss));

                    if (outfper >= rest) {
                        outfper2 = outfper - rest;

                        String rea = String.valueOf(rest);
                        Outfpertxt.setText(rea);
                        used7 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Outfamount.setText(amts);
                    }


                    if (outfper < rest && selfper >= rest && used7 != 0) {
                        selfper2 = selfper - rest;
                        String rea = String.valueOf(rest);
                        Selfpertxt.setText(rea);
                        used7 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);
                    }

                    if (selfper < rest && groper >= rest && used7 != 0) {
                        groper2 = groper - rest;
                        String rea = String.valueOf(rest);
                        Gropertxt.setText(rea);
                        used7 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper < rest && billper >= rest && used7 != 0) {
                        billper2 = billper - rest;
                        String rea = String.valueOf(rest);
                        Billspertxt.setText(rea);
                        used7 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }

                    if (billper < rest && subsper >= rest && used7 != 0) {
                        subsper2 = subsper - rest;
                        String rea = String.valueOf(rest);
                        Subspertxt.setText(rea);
                        used7 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper < rest && transper >= rest && used7 != 0) {
                        transper2 = transper - rest;
                        String rea = String.valueOf(rest);
                        Transpertxt.setText(rea);
                        used7 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper < rest && healthper >= rest && used7 != 0) {
                        healthper2 = healthper - rest;
                        String rea = String.valueOf(rest);
                        Healthpertxt.setText(rea);
                        used7 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }


                    if (healthper2 < rest && entper2 >= rest && used7 != 0) {
                        entper2 = entper - rest;
                        String rea = String.valueOf(rest);
                        Entpertxt.setText(rea);
                        used7 = 0;


                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//-------------------------------------------------work on other operations 2nd 2% out of 6%------
                float used72 = 1; //// sees if the code is used


                if (dpfper >= 7 && dpfper < 8 && outfper2 >= (2 + (dpfsubdif / 3))) {
                    outper3 = outfper2 - (2 + (dpfsubdif / 3));
                    String textsum = String.valueOf(Outfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (2 + (dpfsubdif / 3));
                    String getval = String.valueOf(totalsumper);
                    Outfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Outfamount.setText(amts);
                }

                if (dpfper >= 7 && dpfper < 8 && outfper2 < (2 + (dpfsubdif / 3))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 3)) - outfper2;
                    String subentper = String.valueOf(outfper2);
                    Outfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = outfper2 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Outfamount.setText((amtss));

                    if (selfper2 >= rest) {
                        selfper3 = selfper2 - rest;

                        String textsumw = String.valueOf(Selfpertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Selfpertxt.setText(totalsumperwstring);
                        used72 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);

                    }

                    if (selfper2 < rest && groper2 >= rest && used72 != 0) {
                        groper3 = groper2 - rest;

                        String textsum = String.valueOf(Gropertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Gropertxt.setText(totalsumperstring);
                        used72 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper2 < rest && billper2 >= rest && used72 != 0) {
                        billper3 = billper2 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used72 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper2 < rest && subsper2 >= rest && used72 != 0) {
                        subsper3 = subsper2 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used72 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper2 < rest && transper2 >= rest && used72 != 0) {
                        transper3 = transper2 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used72 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper2 < rest && healthper2 >= rest && used72 != 0) {
                        healthper3 = healthper2 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used72 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper2 < rest && entper2 >= rest && used72 != 0) {
                        entper3 = entper2 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//------------work 3rd  2% of out of 7% percent----------------------------------------------------------------------------

                float used722 = 1; //// sees if the code is used


                if (dpfper >= 7 && dpfper < 8 && selfper3 >= (2 + (dpfsubdif / 3))) {
                    selfper4 = selfper3 - (2 + (dpfsubdif / 3));
                    String textsum = String.valueOf(Selfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (2 + (dpfsubdif / 3));
                    String getval = String.valueOf(totalsumper);
                    Selfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Selfamount.setText(amts);
                }


                if (dpfper >= 7 && dpfper < 8 && selfper3 < (2 + (dpfsubdif / 3))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 3)) - selfper3;
                    String subentper = String.valueOf(outfper2);
                    Selfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = selfper3 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Selfamount.setText((amtss));

                    if (groper3 >= rest) {
                        groper4 = groper3 - rest;

                        String textsumw = String.valueOf(Gropertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Gropertxt.setText(totalsumperwstring);
                        used722 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);

                    }


                    if (groper3 < rest && billper3 >= rest && used722 != 0) {
                        billper4 = billper3 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used722 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper3 < rest && subsper3 >= rest && used722 != 0) {
                        subsper4 = subsper3 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used722 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper3 < rest && transper3 >= rest && used722 != 0) {
                        transper4 = transper3 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used722 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper3 < rest && healthper3 >= rest && used722 != 0) {
                        healthper4 = healthper3 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used722 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper3 < rest && entper3 >= rest && used722 != 0) {
                        entper4 = entper3 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//------------work 4rd  1% of out of 7% percent----------------------------------------------------------------------------

                float used71 = 1; //// sees if the code is used


//-------------------------------------------------work on other operations another 1% out of 6%------

                if (dpfper >= 7 && dpfper < 8 && groper4 >= 1) {
                    groper5 = groper4 - 1;
                    String textsum = String.valueOf(Gropertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + 1;
                    String getval = String.valueOf(totalsumper);
                    Gropertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Groamount.setText(amts);
                }


                if (dpfper >= 7 && dpfper < 8 && groper4 < 1) {  // nested if------------
                    rest = 1 - groper4;
                    String subentper = String.valueOf(groper4);
                    Gropertxt.setText(subentper);

                    //set value of amount
                    float ammt = groper4 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Groamount.setText((amtss));

                    if (billper4 >= rest) {
                        groper4 = groper3 - rest;

                        String textsumw = String.valueOf(Billspertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Billamount.setText(totalsumperwstring);
                        used71 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }


                    if (billper4 < rest && subsper4 >= rest && used71 != 0) {
                        subsper5 = subsper4 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used71 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper4 < rest && transper4 >= rest && used71 != 0) {
                        transper5 = transper4 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used71 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper4 < rest && healthper4 >= rest && used71 != 0) {
                        healthper5 = healthper4 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used71 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper4 < rest && entper4 >= rest && used71 != 0) {
                        entper5 = entper4 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //------------------work on 8%---------------------------------------------------------------------------------

                // 3% out of 8%-----------------------------------------------------------------------------


                float used8 = 1;

                if (dpfper >= 8 && dpfper < 9 && entper >= (3 + (dpfsubdif / 3))) {
                    entper2 = entper - (3 + (dpfsubdif / 3));
                    float cutvalue = (3 + (dpfsubdif / 3));
                    String getval = String.valueOf(cutvalue);
                    Entpertxt.setText(getval);


                    // set value of amount
                    float amt = cutvalue / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Entamount.setText(amts);
                }


                if (dpfper >= 8 && dpfper < 9 && entper < (3 + (dpfsubdif / 3))) {  // nested if------------
                    rest = (3 + (dpfsubdif / 3)) - entper;
                    String subentper = String.valueOf(entper);
                    Entpertxt.setText(subentper);

                    //set value of amount
                    float ammt = entper / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Entamount.setText((amtss));

                    if (outfper >= rest) {
                        outfper2 = outfper - rest;

                        String rea = String.valueOf(rest);
                        Outfpertxt.setText(rea);
                        used8 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Outfamount.setText(amts);
                    }


                    if (outfper < rest && selfper >= rest && used8 != 0) {
                        selfper2 = selfper - rest;
                        String rea = String.valueOf(rest);
                        Selfpertxt.setText(rea);
                        used8 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);
                    }

                    if (selfper < rest && groper >= rest && used8 != 0) {
                        groper2 = groper - rest;
                        String rea = String.valueOf(rest);
                        Gropertxt.setText(rea);
                        used8 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper < rest && billper >= rest && used8 != 0) {
                        billper2 = billper - rest;
                        String rea = String.valueOf(rest);
                        Billspertxt.setText(rea);
                        used8 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }

                    if (billper < rest && subsper >= rest && used8 != 0) {
                        subsper2 = subsper - rest;
                        String rea = String.valueOf(rest);
                        Subspertxt.setText(rea);
                        used8 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper < rest && transper >= rest && used8 != 0) {
                        transper2 = transper - rest;
                        String rea = String.valueOf(rest);
                        Transpertxt.setText(rea);
                        used8 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper < rest && healthper >= rest && used8 != 0) {
                        healthper2 = healthper - rest;
                        String rea = String.valueOf(rest);
                        Healthpertxt.setText(rea);
                        used8 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }


                    if (healthper < rest && entper >= rest && used8 != 0) {
                        entper2 = entper - rest;
                        String rea = String.valueOf(rest);
                        Entpertxt.setText(rea);
                        used8 = 0;


                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//-------------------------------------------------work on  operations 2% out of 8%------
                float used82 = 1; //// sees if the code is used


                if (dpfper >= 8 && dpfper < 9 && outfper2 >= (2 + (dpfsubdif / 3))) {
                    outper3 = outfper2 - (2 + (dpfsubdif / 3));
                    String textsum = String.valueOf(Outfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (2 + (dpfsubdif / 3));
                    String getval = String.valueOf(totalsumper);
                    Outfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Outfamount.setText(amts);
                }

                if (dpfper >= 8 && dpfper < 9 && outfper2 < (2 + (dpfsubdif / 3))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 3)) - outfper2;
                    String subentper = String.valueOf(outfper2);
                    Outfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = outfper2 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Outfamount.setText((amtss));

                    if (selfper2 >= rest) {
                        selfper3 = selfper2 - rest;

                        String textsumw = String.valueOf(Selfpertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Selfpertxt.setText(totalsumperwstring);
                        used82 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);

                    }

                    if (selfper2 < rest && groper2 >= rest && used82 != 0) {
                        groper3 = groper2 - rest;

                        String textsum = String.valueOf(Gropertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Gropertxt.setText(totalsumperstring);
                        used82 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper2 < rest && billper2 >= rest && used82 != 0) {
                        billper3 = billper2 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used82 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper2 < rest && subsper2 >= rest && used82 != 0) {
                        subsper3 = subsper2 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used82 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper2 < rest && transper2 >= rest && used82 != 0) {
                        transper3 = transper2 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used82 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper2 < rest && healthper2 >= rest && used82 != 0) {
                        healthper3 = healthper2 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used82 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper2 < rest && entper2 >= rest && used82 != 0) {
                        entper3 = entper2 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//------------work  1% of out of 8% percent----------------------------------------------------------------------------

                float used81 = 1; //// sees if the code is used


                if (dpfper >= 7 && dpfper < 8 && selfper3 >= (1 + (dpfsubdif / 3))) {
                    selfper4 = selfper3 - (1 + (dpfsubdif / 3));
                    String textsum = String.valueOf(Selfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (1 + (dpfsubdif / 3));
                    String getval = String.valueOf(totalsumper);
                    Selfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Selfamount.setText(amts);
                }


                if (dpfper >= 8 && dpfper < 9 && selfper3 < (1 + (dpfsubdif / 3))) {  // nested if------------
                    rest = (1 + (dpfsubdif / 3)) - selfper3;
                    String subentper = String.valueOf(outfper2);
                    Selfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = selfper3 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Selfamount.setText((amtss));

                    if (groper3 >= rest) {
                        groper4 = groper3 - rest;

                        String textsumw = String.valueOf(Gropertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Gropertxt.setText(totalsumperwstring);
                        used81 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);

                    }


                    if (groper3 < rest && billper3 >= rest && used81 != 0) {
                        billper4 = billper3 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used81 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper3 < rest && subsper3 >= rest && used81 != 0) {
                        subsper4 = subsper3 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used81 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper3 < rest && transper3 >= rest && used82 != 0) {
                        transper4 = transper3 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used81 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper3 < rest && healthper3 >= rest && used82 != 0) {
                        healthper4 = healthper3 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used81 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper3 < rest && entper3 >= rest && used81 != 0) {
                        entper4 = entper3 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//------------work 2nd 1% of out of 8% percent----------------------------------------------------------------------------

                float used811 = 1; //// sees if the code is used


                if (dpfper >= 8 && dpfper < 9 && groper4 >= 1) {
                    groper5 = groper4 - 1;
                    String textsum = String.valueOf(Gropertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + 1;
                    String getval = String.valueOf(totalsumper);
                    Gropertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Groamount.setText(amts);
                }


                if (dpfper >= 8 && dpfper < 9 && groper4 < 1) {  // nested if------------
                    rest = 1 - groper4;
                    String subentper = String.valueOf(groper4);
                    Gropertxt.setText(subentper);

                    //set value of amount
                    float ammt = groper4 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Groamount.setText((amtss));

                    if (billper4 >= rest) {
                        billper4 = billper3 - rest;

                        String textsumw = String.valueOf(Billspertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Billamount.setText(totalsumperwstring);
                        used811 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }


                    if (billper4 < rest && subsper4 >= rest && used811 != 0) {
                        subsper5 = subsper4 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used811 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper4 < rest && transper4 >= rest && used811 != 0) {
                        transper5 = transper4 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used811 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper4 < rest && healthper4 >= rest && used811 != 0) {
                        healthper5 = healthper4 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used811 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper4 < rest && entper4 >= rest && used811 != 0) {
                        entper5 = entper4 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //------------------work rate 9% -----------------------------------------------------------------------------------------------------


                // 3% out of 9%-----------------------------------------------------------------------------


                float used9 = 1;

                if (dpfper >= 9 && dpfper < 10 && entper >= (3 + (dpfsubdif / 4))) {
                    entper2 = entper - (3 + (dpfsubdif / 4));
                    float cutvalue = (3 + (dpfsubdif / 4));
                    String getval = String.valueOf(cutvalue);
                    Entpertxt.setText(getval);


                    // set value of amount
                    float amt = cutvalue / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Entamount.setText(amts);
                }


                if (dpfper >= 9 && dpfper < 10 && entper < (3 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (3 + (dpfsubdif / 4)) - entper;
                    String subentper = String.valueOf(entper);
                    Entpertxt.setText(subentper);

                    //set value of amount
                    float ammt = entper / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Entamount.setText((amtss));

                    if (outfper >= rest) {
                        outfper2 = outfper - rest;

                        String rea = String.valueOf(rest);
                        Outfpertxt.setText(rea);
                        used9 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Outfamount.setText(amts);
                    }


                    if (outfper < rest && selfper >= rest && used9 != 0) {
                        selfper2 = selfper - rest;
                        String rea = String.valueOf(rest);
                        Selfpertxt.setText(rea);
                        used9 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);
                    }

                    if (selfper < rest && groper >= rest && used9 != 0) {
                        groper2 = groper - rest;
                        String rea = String.valueOf(rest);
                        Gropertxt.setText(rea);
                        used9 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper < rest && billper >= rest && used9 != 0) {
                        billper2 = billper - rest;
                        String rea = String.valueOf(rest);
                        Billspertxt.setText(rea);
                        used9 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }

                    if (billper < rest && subsper >= rest && used9 != 0) {
                        subsper2 = subsper - rest;
                        String rea = String.valueOf(rest);
                        Subspertxt.setText(rea);
                        used9 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper < rest && transper >= rest && used9 != 0) {
                        transper2 = transper - rest;
                        String rea = String.valueOf(rest);
                        Transpertxt.setText(rea);
                        used9 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper < rest && healthper >= rest && used9 != 0) {
                        healthper2 = healthper - rest;
                        String rea = String.valueOf(rest);
                        Healthpertxt.setText(rea);
                        used9 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }


                    if (healthper2 < rest && entper2 >= rest && used9 != 0) {
                        entper2 = entper - rest;
                        String rea = String.valueOf(rest);
                        Entpertxt.setText(rea);
                        used9 = 0;


                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//-------------------------------------------------work on  operations 2% out of 9%------
                float used92 = 1; //// sees if the code is used


                if (dpfper >= 9 && dpfper < 10 && outfper2 >= (2 + (dpfsubdif / 4))) {
                    outper3 = outfper2 - (2 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Outfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (2 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Outfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Outfamount.setText(amts);
                }

                if (dpfper >= 9 && dpfper < 10 && outfper2 < (2 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 4)) - outfper2;
                    String subentper = String.valueOf(outfper2);
                    Outfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = outfper2 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Outfamount.setText((amtss));

                    if (selfper2 >= rest) {
                        selfper3 = selfper2 - rest;

                        String textsumw = String.valueOf(Selfpertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Selfpertxt.setText(totalsumperwstring);
                        used92 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);

                    }

                    if (selfper2 < rest && groper2 >= rest && used92 != 0) {
                        groper3 = groper2 - rest;

                        String textsum = String.valueOf(Gropertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Gropertxt.setText(totalsumperstring);
                        used92 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper2 < rest && billper2 >= rest && used92 != 0) {
                        billper3 = billper2 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used92 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper2 < rest && subsper2 >= rest && used92 != 0) {
                        subsper3 = subsper2 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used92 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper2 < rest && transper2 >= rest && used92 != 0) {
                        transper3 = transper2 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used92 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper2 < rest && healthper2 >= rest && used92 != 0) {
                        healthper3 = healthper2 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used92 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper2 < rest && entper2 >= rest && used92 != 0) {
                        entper3 = entper2 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//------------work  2% of out of 9% percent----------------------------------------------------------------------------

                float used91 = 1; //// sees if the code is used


                if (dpfper >= 9 && dpfper < 10 && selfper3 >= (2 + (dpfsubdif / 4))) {
                    selfper4 = selfper3 - (2 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Selfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (2 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Selfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Selfamount.setText(amts);
                }


                if (dpfper >= 9 && dpfper < 10 && selfper3 < (2 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 4)) - selfper3;
                    String subentper = String.valueOf(outfper2);
                    Selfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = selfper3 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Selfamount.setText((amtss));

                    if (groper3 >= rest) {
                        groper4 = groper3 - rest;

                        String textsumw = String.valueOf(Gropertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Gropertxt.setText(totalsumperwstring);
                        used91 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);

                    }


                    if (groper3 < rest && billper3 >= rest && used91 != 0) {
                        billper4 = billper3 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used91 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper3 < rest && subsper3 >= rest && used91 != 0) {
                        subsper4 = subsper3 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used91 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper3 < rest && transper3 >= rest && used91 != 0) {
                        transper4 = transper3 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used91 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper3 < rest && healthper3 >= rest && used91 != 0) {
                        healthper4 = healthper3 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used91 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper3 < rest && entper3 >= rest && used91 != 0) {
                        entper4 = entper3 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//------------work 1% of out of 9% percent----------------------------------------------------------------------------

                float used911 = 1; //// sees if the code is used


                if (dpfper >= 9 && dpfper < 10 && groper4 >= (1 + (dpfsubdif / 4))) {
                    groper5 = groper4 - (1 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Gropertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (1 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Gropertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Groamount.setText(amts);
                }


                if (dpfper >= 9 && dpfper < 10 && groper4 < (1 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (1 + (dpfsubdif / 4)) - groper4;
                    String subentper = String.valueOf(groper4);
                    Gropertxt.setText(subentper);

                    //set value of amount
                    float ammt = groper4 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Groamount.setText((amtss));

                    if (billper4 >= rest) {
                        billper5 = billper4 - rest;

                        String textsumw = String.valueOf(Billspertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Billamount.setText(totalsumperwstring);
                        used911 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }


                    if (billper4 < rest && subsper4 >= rest && used911 != 0) {
                        subsper5 = subsper4 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used911 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper4 < rest && transper4 >= rest && used911 != 0) {
                        transper5 = transper4 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used911 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper4 < rest && healthper4 >= rest && used911 != 0) {
                        healthper5 = healthper4 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used911 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper4 < rest && entper4 >= rest && used911 != 0) {
                        entper5 = entper4 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //------------work 2nd 1% of out of 9% percent----------------------------------------------------------------------------

                float used9111 = 1; //// sees if the code is used


                if (dpfper >= 9 && dpfper < 10 && billper5 >= 1) {
                    billper6 = billper5 - (1 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Billspertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + 1;
                    String getval = String.valueOf(totalsumper);
                    Billamount.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Billamount.setText(amts);
                }


                if (dpfper >= 9 && dpfper < 10 && billper5 < 1) {  // nested if------------
                    rest = 1 - billper5;
                    String subentper = String.valueOf(billper5);
                    Billspertxt.setText(subentper);

                    //set value of amount
                    float ammt = billper5 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Billamount.setText((amtss));

                    if (subsper5 >= rest) {
                        subsper6 = subsper5 - rest;

                        String textsumw = String.valueOf(Subspertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Subsamount.setText(totalsumperwstring);
                        used9111 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);

                    }


                    if (subsper5 < rest && transper5 >= rest && used9111 != 0) {
                        transper6 = transper5 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used9111 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper5 < rest && healthper5 >= rest && used9111 != 0) {
                        healthper6 = healthper5 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used9111 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper5 < rest && entper5 >= rest && used9111 != 0) {
                        entper6 = entper5 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //-----------------------------rate 10%----------------------------------------------------------


                // 3% out of 10%-----------------------------------------------------------------------------


                float used10 = 1;

                if (dpfper >= 10 && dpfper < 11 && entper >= (3 + (dpfsubdif / 4))) {
                    entper2 = entper - (3 + (dpfsubdif / 4));
                    float cutvalue = (3 + (dpfsubdif / 4));
                    String getval = String.valueOf(cutvalue);
                    Entpertxt.setText(getval);


                    // set value of amount
                    float amt = cutvalue / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Entamount.setText(amts);
                }


                if (dpfper >= 10 && dpfper < 11 && entper < (3 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (3 + (dpfsubdif / 4)) - entper;
                    String subentper = String.valueOf(entper);
                    Entpertxt.setText(subentper);

                    //set value of amount
                    float ammt = entper / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Entamount.setText((amtss));

                    if (outfper >= rest) {
                        outfper2 = outfper - rest;

                        String rea = String.valueOf(rest);
                        Outfpertxt.setText(rea);
                        used10 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Outfamount.setText(amts);
                    }


                    if (outfper < rest && selfper >= rest && used10 != 0) {
                        selfper2 = selfper - rest;
                        String rea = String.valueOf(rest);
                        Selfpertxt.setText(rea);
                        used10 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);
                    }

                    if (selfper < rest && groper >= rest && used10 != 0) {
                        groper2 = groper - rest;
                        String rea = String.valueOf(rest);
                        Gropertxt.setText(rea);
                        used10 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper < rest && billper >= rest && used10 != 0) {
                        billper2 = billper - rest;
                        String rea = String.valueOf(rest);
                        Billspertxt.setText(rea);
                        used10 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }

                    if (billper < rest && subsper >= rest && used10 != 0) {
                        subsper2 = subsper - rest;
                        String rea = String.valueOf(rest);
                        Subspertxt.setText(rea);
                        used10 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper < rest && transper >= rest && used10 != 0) {
                        transper2 = transper - rest;
                        String rea = String.valueOf(rest);
                        Transpertxt.setText(rea);
                        used10 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper < rest && healthper >= rest && used10 != 0) {
                        healthper2 = healthper - rest;
                        String rea = String.valueOf(rest);
                        Healthpertxt.setText(rea);
                        used10 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }


                    if (healthper < rest && entper >= rest && used10 != 0) {
                        entper2 = entper - rest;
                        String rea = String.valueOf(rest);
                        Entpertxt.setText(rea);
                        used10 = 0;


                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//-------------------------------------------------work on  operations 2% out of 10%------
                float used102 = 1; //// sees if the code is used


                if (dpfper >= 10 && dpfper < 11 && outfper2 >= (2 + (dpfsubdif / 4))) {
                    outper3 = outfper2 - (2 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Outfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (2 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Outfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Outfamount.setText(amts);
                }

                if (dpfper >= 10 && dpfper < 11 && outfper2 < (2 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 4)) - outfper2;
                    String subentper = String.valueOf(outfper2);
                    Outfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = outfper2 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Outfamount.setText((amtss));

                    if (selfper2 >= rest) {
                        selfper3 = selfper2 - rest;

                        String textsumw = String.valueOf(Selfpertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Selfpertxt.setText(totalsumperwstring);
                        used102 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);

                    }

                    if (selfper2 < rest && groper2 >= rest && used102 != 0) {
                        groper3 = groper2 - rest;

                        String textsum = String.valueOf(Gropertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Gropertxt.setText(totalsumperstring);
                        used102 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper2 < rest && billper2 >= rest && used102 != 0) {
                        billper3 = billper2 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used102 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper2 < rest && subsper2 >= rest && used102 != 0) {
                        subsper3 = subsper2 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used102 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper2 < rest && transper2 >= rest && used102 != 0) {
                        transper3 = transper2 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used102 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper2 < rest && healthper2 >= rest && used102 != 0) {
                        healthper3 = healthper2 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used102 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper2 < rest && entper2 >= rest && used102 != 0) {
                        entper3 = entper2 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//------------work  2% of out of 10% percent----------------------------------------------------------------------------

                float used101 = 1; //// sees if the code is used


                if (dpfper >= 10 && dpfper < 11 && selfper3 >= (2 + (dpfsubdif / 4))) {
                    selfper4 = selfper3 - (2 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Selfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (2 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Selfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Selfamount.setText(amts);
                }


                if (dpfper >= 10 && dpfper < 11 && selfper3 < (2 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 4)) - selfper3;
                    String subentper = String.valueOf(outfper2);
                    Selfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = selfper3 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Selfamount.setText((amtss));

                    if (groper3 >= rest) {
                        groper4 = groper3 - rest;

                        String textsumw = String.valueOf(Gropertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Gropertxt.setText(totalsumperwstring);
                        used101 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);

                    }


                    if (groper3 < rest && billper3 >= rest && used101 != 0) {
                        billper4 = billper3 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used101 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper3 < rest && subsper3 >= rest && used101 != 0) {
                        subsper4 = subsper3 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used101 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper3 < rest && transper3 >= rest && used101 != 0) {
                        transper4 = transper3 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used101 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper3 < rest && healthper3 >= rest && used101 != 0) {
                        healthper4 = healthper3 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used101 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper3 < rest && entper3 >= rest && used101 != 0) {
                        entper4 = entper3 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//------------work 1% of out of 9% percent----------------------------------------------------------------------------

                float used1011 = 1; //// sees if the code is used


                if (dpfper >= 10 && dpfper < 11 && groper4 >= (2 + (dpfsubdif / 4))) {
                    groper5 = groper4 - (2 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Gropertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (2 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Gropertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Groamount.setText(amts);
                }


                if (dpfper >= 10 && dpfper < 11 && groper4 < (2 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 4)) - groper4;
                    String subentper = String.valueOf(groper4);
                    Gropertxt.setText(subentper);

                    //set value of amount
                    float ammt = groper4 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Groamount.setText((amtss));

                    if (billper4 >= rest) {
                        billper5 = billper4 - rest;

                        String textsumw = String.valueOf(Billspertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Billamount.setText(totalsumperwstring);
                        used911 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }


                    if (billper4 < rest && subsper4 >= rest && used1011 != 0) {
                        subsper5 = subsper4 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used1011 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper4 < rest && transper4 >= rest && used1011 != 0) {
                        transper5 = transper4 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used1011 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper4 < rest && healthper4 >= rest && used1011 != 0) {
                        healthper5 = healthper4 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used1011 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper4 < rest && entper4 >= rest && used1011 != 0) {
                        entper5 = entper4 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //------------work 2nd 1% of out of 9% percent----------------------------------------------------------------------------

                float used10111 = 1; //// sees if the code is used


                if (dpfper >= 10 && dpfper < 11 && billper5 >= 1) {
                    billper6 = billper5 - 1;
                    String textsum = String.valueOf(Billspertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + 1;
                    String getval = String.valueOf(totalsumper);
                    Billamount.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Billamount.setText(amts);
                }


                if (dpfper >= 9 && dpfper < 10 && billper5 < 1) {  // nested if------------
                    rest = 1 - billper5;
                    String subentper = String.valueOf(billper5);
                    Billspertxt.setText(subentper);

                    //set value of amount
                    float ammt = billper5 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Billamount.setText((amtss));

                    if (subsper5 >= rest) {
                        subsper6 = subsper5 - rest;

                        String textsumw = String.valueOf(Subspertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Subsamount.setText(totalsumperwstring);
                        used10111 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);

                    }


                    if (subsper5 < rest && transper5 >= rest && used10111 != 0) {
                        transper6 = transper5 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used10111 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper5 < rest && healthper5 >= rest && used10111 != 0) {
                        healthper6 = healthper5 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used10111 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper5 < rest && entper5 >= rest && used10111 != 0) {
                        entper6 = entper5 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                // -----------------------------------------------------------rate 11%---------------------------------------------------------------


                // 3% out of 11%-----------------------------------------------------------------------------


                float used11 = 1;

                if (dpfper >= 11 && dpfper < 12 && entper >= (3 + (dpfsubdif / 4))) {
                    entper2 = entper - (3 + (dpfsubdif / 4));
                    float cutvalue = (3 + (dpfsubdif / 4));
                    String getval = String.valueOf(cutvalue);
                    Entpertxt.setText(getval);


                    // set value of amount
                    float amt = cutvalue / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Entamount.setText(amts);
                }


                if (dpfper >= 11 && dpfper < 12 && entper < (3 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (3 + (dpfsubdif / 4)) - entper;
                    String subentper = String.valueOf(entper);
                    Entpertxt.setText(subentper);

                    //set value of amount
                    float ammt = entper / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Entamount.setText((amtss));

                    if (outfper >= rest) {
                        outfper2 = outfper - rest;

                        String rea = String.valueOf(rest);
                        Outfpertxt.setText(rea);
                        used11 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Outfamount.setText(amts);
                    }


                    if (outfper < rest && selfper >= rest && used11 != 0) {
                        selfper2 = selfper - rest;
                        String rea = String.valueOf(rest);
                        Selfpertxt.setText(rea);
                        used11 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);
                    }

                    if (selfper < rest && groper >= rest && used11 != 0) {
                        groper2 = groper - rest;
                        String rea = String.valueOf(rest);
                        Gropertxt.setText(rea);
                        used11 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper < rest && billper >= rest && used11 != 0) {
                        billper2 = billper - rest;
                        String rea = String.valueOf(rest);
                        Billspertxt.setText(rea);
                        used11 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }

                    if (billper < rest && subsper >= rest && used11 != 0) {
                        subsper2 = subsper - rest;
                        String rea = String.valueOf(rest);
                        Subspertxt.setText(rea);
                        used11 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper < rest && transper >= rest && used11 != 0) {
                        transper2 = transper - rest;
                        String rea = String.valueOf(rest);
                        Transpertxt.setText(rea);
                        used11 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper < rest && healthper >= rest && used11 != 0) {
                        healthper2 = healthper - rest;
                        String rea = String.valueOf(rest);
                        Healthpertxt.setText(rea);
                        used11 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }


                    if (healthper < rest && entper >= rest && used11 != 0) {
                        entper2 = entper - rest;
                        String rea = String.valueOf(rest);
                        Entpertxt.setText(rea);
                        used10 = 0;


                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//-------------------------------------------------work on  operations 3% out of 11%------
                float used113 = 1; //// sees if the code is used


                if (dpfper >= 11 && dpfper < 12 && outfper2 >= (3 + (dpfsubdif / 4))) {
                    outper3 = outfper2 - (3 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Outfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (3 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Outfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Outfamount.setText(amts);
                }

                if (dpfper >= 11 && dpfper < 12 && outfper2 < (3 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (3 + (dpfsubdif / 4)) - outfper2;
                    String subentper = String.valueOf(outfper2);
                    Outfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = outfper2 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Outfamount.setText((amtss));

                    if (selfper2 >= rest) {
                        selfper3 = selfper2 - rest;

                        String textsumw = String.valueOf(Selfpertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Selfpertxt.setText(totalsumperwstring);
                        used113 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);

                    }

                    if (selfper2 < rest && groper2 >= rest && used113 != 0) {
                        groper3 = groper2 - rest;

                        String textsum = String.valueOf(Gropertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Gropertxt.setText(totalsumperstring);
                        used113 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper2 < rest && billper2 >= rest && used113 != 0) {
                        billper3 = billper2 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used113 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper2 < rest && subsper2 >= rest && used113 != 0) {
                        subsper3 = subsper2 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used102 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper2 < rest && transper2 >= rest && used113 != 0) {
                        transper3 = transper2 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used113 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper2 < rest && healthper2 >= rest && used113 != 0) {
                        healthper3 = healthper2 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used113 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper2 < rest && entper2 >= rest && used113 != 0) {
                        entper3 = entper2 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//------------work  2% of out of 11% percent----------------------------------------------------------------------------

                float used1102 = 1; //// sees if the code is used


                if (dpfper >= 11 && dpfper < 12 && selfper3 >= (2 + (dpfsubdif / 4))) {
                    selfper4 = selfper3 - (2 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Selfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (2 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Selfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Selfamount.setText(amts);
                }


                if (dpfper >= 11 && dpfper < 12 && selfper3 < (2 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 4)) - selfper3;
                    String subentper = String.valueOf(outfper2);
                    Selfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = selfper3 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Selfamount.setText((amtss));

                    if (groper3 >= rest) {
                        groper4 = groper3 - rest;

                        String textsumw = String.valueOf(Gropertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Gropertxt.setText(totalsumperwstring);
                        used1102 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);

                    }


                    if (groper3 < rest && billper3 >= rest && used1102 != 0) {
                        billper4 = billper3 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used1102 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper3 < rest && subsper3 >= rest && used1102 != 0) {
                        subsper4 = subsper3 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used1102 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper3 < rest && transper3 >= rest && used1102 != 0) {
                        transper4 = transper3 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used1102 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper3 < rest && healthper3 >= rest && used1102 != 0) {
                        healthper4 = healthper3 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used1102 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper3 < rest && entper3 >= rest && used1102 != 0) {
                        entper4 = entper3 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//------------work 2% of out of 11% percent----------------------------------------------------------------------------

                float used11022 = 1; //// sees if the code is used


                if (dpfper >= 11 && dpfper < 12 && groper4 >= (2 + (dpfsubdif / 4))) {
                    groper5 = groper4 - (2 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Gropertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (2 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Gropertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Groamount.setText(amts);
                }


                if (dpfper >= 11 && dpfper < 12 && groper4 < (2 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 4)) - groper4;
                    String subentper = String.valueOf(groper4);
                    Gropertxt.setText(subentper);

                    //set value of amount
                    float ammt = groper4 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Groamount.setText((amtss));

                    if (billper4 >= rest) {
                        billper5 = billper4 - rest;

                        String textsumw = String.valueOf(Billspertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Billamount.setText(totalsumperwstring);
                        used911 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }


                    if (billper4 < rest && subsper4 >= rest && used1011 != 0) {
                        subsper5 = subsper4 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used1011 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper4 < rest && transper4 >= rest && used1011 != 0) {
                        transper5 = transper4 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used1011 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper4 < rest && healthper4 >= rest && used1011 != 0) {
                        healthper5 = healthper4 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used1011 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper4 < rest && entper4 >= rest && used1011 != 0) {
                        entper5 = entper4 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //------------work 1% of out of 11% percent----------------------------------------------------------------------------

                float used111 = 1; //// sees if the code is used


                if (dpfper >= 11 && dpfper < 12 && billper5 >= 1) {
                    billper6 = billper5 - 1;
                    String textsum = String.valueOf(Billspertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + 1;
                    String getval = String.valueOf(totalsumper);
                    Billamount.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Billamount.setText(amts);
                }


                if (dpfper >= 11 && dpfper < 12 && billper5 < 1) {  // nested if------------
                    rest = 1 - billper5;
                    String subentper = String.valueOf(billper5);
                    Billspertxt.setText(subentper);

                    //set value of amount
                    float ammt = billper5 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Billamount.setText((amtss));

                    if (subsper5 >= rest) {
                        subsper6 = subsper5 - rest;

                        String textsumw = String.valueOf(Subspertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Subsamount.setText(totalsumperwstring);
                        used111 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);

                    }


                    if (subsper5 < rest && transper5 >= rest && used111 != 0) {
                        transper6 = transper5 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used111 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper5 < rest && healthper5 >= rest && used111 != 0) {
                        healthper6 = healthper5 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used111 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper5 < rest && entper5 >= rest && used111 != 0) {
                        entper5 = entper4 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //-------------------------------------------- rate -----------12%------------------------------------------------------


                // 3% out of 12%-----------------------------------------------------------------------------


                float used1203 = 1;

                if (dpfper >= 12 && dpfper < 13 && entper >= (3 + (dpfsubdif / 4))) {
                    entper2 = entper - (3 + (dpfsubdif / 4));
                    float cutvalue = (3 + (dpfsubdif / 4));
                    String getval = String.valueOf(cutvalue);
                    Entpertxt.setText(getval);


                    // set value of amount
                    float amt = cutvalue / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Entamount.setText(amts);
                }


                if (dpfper >= 12 && dpfper < 13 && entper < (3 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (3 + (dpfsubdif / 4)) - entper;
                    String subentper = String.valueOf(entper);
                    Entpertxt.setText(subentper);

                    //set value of amount
                    float ammt = entper / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Entamount.setText((amtss));

                    if (outfper >= rest) {
                        outfper2 = outfper - rest;

                        String rea = String.valueOf(rest);
                        Outfpertxt.setText(rea);
                        used1203 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Outfamount.setText(amts);
                    }


                    if (outfper < rest && selfper >= rest && used1203 != 0) {
                        selfper2 = selfper - rest;
                        String rea = String.valueOf(rest);
                        Selfpertxt.setText(rea);
                        used1203 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);
                    }

                    if (selfper < rest && groper >= rest && used1203 != 0) {
                        groper2 = groper - rest;
                        String rea = String.valueOf(rest);
                        Gropertxt.setText(rea);
                        used1203 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper < rest && billper >= rest && used1203 != 0) {
                        billper2 = billper - rest;
                        String rea = String.valueOf(rest);
                        Billspertxt.setText(rea);
                        used1203 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }

                    if (billper < rest && subsper >= rest && used1203 != 0) {
                        subsper2 = subsper - rest;
                        String rea = String.valueOf(rest);
                        Subspertxt.setText(rea);
                        used1203 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper < rest && transper >= rest && used1203 != 0) {
                        transper2 = transper - rest;
                        String rea = String.valueOf(rest);
                        Transpertxt.setText(rea);
                        used1203 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper < rest && healthper >= rest && used1203 != 0) {
                        healthper2 = healthper - rest;
                        String rea = String.valueOf(rest);
                        Healthpertxt.setText(rea);
                        used1203 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }


                    if (healthper < rest && entper >= rest && used1203 != 0) {
                        entper2 = entper - rest;
                        String rea = String.valueOf(rest);
                        Entpertxt.setText(rea);
                        used1203 = 0;


                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//-------------------------------------------------work on  operations 3% out of 12%------
                float used12033 = 1; //// sees if the code is used


                if (dpfper >= 12 && dpfper < 13 && outfper2 >= (3 + (dpfsubdif / 4))) {
                    outper3 = outfper2 - (3 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Outfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (3 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Outfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Outfamount.setText(amts);
                }

                if (dpfper >= 12 && dpfper < 13 && outfper2 < (3 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (3 + (dpfsubdif / 4)) - outfper2;
                    String subentper = String.valueOf(outfper2);
                    Outfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = outfper2 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Outfamount.setText((amtss));

                    if (selfper2 >= rest) {
                        selfper3 = selfper2 - rest;

                        String textsumw = String.valueOf(Selfpertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Selfpertxt.setText(totalsumperwstring);
                        used12033 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);

                    }

                    if (selfper2 < rest && groper2 >= rest && used12033 != 0) {
                        groper3 = groper2 - rest;

                        String textsum = String.valueOf(Gropertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Gropertxt.setText(totalsumperstring);
                        used12033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper2 < rest && billper2 >= rest && used12033 != 0) {
                        billper3 = billper2 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used12033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper2 < rest && subsper2 >= rest && used12033 != 0) {
                        subsper3 = subsper2 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used12033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper2 < rest && transper2 >= rest && used12033 != 0) {
                        transper3 = transper2 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used12033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper2 < rest && healthper2 >= rest && used12033 != 0) {
                        healthper3 = healthper2 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used12033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper2 < rest && entper2 >= rest && used12033 != 0) {
                        entper3 = entper2 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//------------work  2% of out of 12% percent----------------------------------------------------------------------------

                float used12002 = 1; //// sees if the code is used


                if (dpfper >= 12 && dpfper < 13 && selfper3 >= (2 + (dpfsubdif / 4))) {
                    selfper4 = selfper3 - (2 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Selfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (2 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Selfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Selfamount.setText(amts);
                }


                if (dpfper >= 12 && dpfper < 13 && selfper3 < (2 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 4)) - selfper3;
                    String subentper = String.valueOf(outfper2);
                    Selfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = selfper3 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Selfamount.setText((amtss));

                    if (groper3 >= rest) {
                        groper4 = groper3 - rest;

                        String textsumw = String.valueOf(Gropertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Gropertxt.setText(totalsumperwstring);
                        used12002 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);

                    }


                    if (groper3 < rest && billper3 >= rest && used12002 != 0) {
                        billper4 = billper3 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used12002 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper3 < rest && subsper3 >= rest && used12002 != 0) {
                        subsper4 = subsper3 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used12002 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper3 < rest && transper3 >= rest && used12002 != 0) {
                        transper4 = transper3 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used12002 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper3 < rest && healthper3 >= rest && used12002 != 0) {
                        healthper4 = healthper3 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used12002 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper3 < rest && entper3 >= rest && used12002 != 0) {
                        entper4 = entper3 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//------------work 2% of out of 12% percent----------------------------------------------------------------------------

                float used120022 = 1; //// sees if the code is used


                if (dpfper >= 12 && dpfper < 13 && groper4 >= (2 + (dpfsubdif / 4))) {
                    groper5 = groper4 - (2 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Gropertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (2 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Gropertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Groamount.setText(amts);
                }


                if (dpfper >= 12 && dpfper < 13 && groper4 < (2 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 4)) - groper4;
                    String subentper = String.valueOf(groper4);
                    Gropertxt.setText(subentper);

                    //set value of amount
                    float ammt = groper4 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Groamount.setText((amtss));

                    if (billper4 >= rest) {
                        billper5 = billper4 - rest;

                        String textsumw = String.valueOf(Billspertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Billamount.setText(totalsumperwstring);
                        used120022 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }


                    if (billper4 < rest && subsper4 >= rest && used120022 != 0) {
                        subsper5 = subsper4 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used120022 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper4 < rest && transper4 >= rest && used120022 != 0) {
                        transper5 = transper4 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used120022 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper4 < rest && healthper4 >= rest && used120022 != 0) {
                        healthper5 = healthper4 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used120022 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper4 < rest && entper4 >= rest && used120022 != 0) {
                        entper5 = entper4 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //------------work 1% of out of 12% percent----------------------------------------------------------------------------

                float used120001 = 1; //// sees if the code is used


                if (dpfper >= 12 && dpfper < 13 && billper5 >= 1) {
                    billper6 = billper5 - 1;
                    String textsum = String.valueOf(Billspertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + 1;
                    String getval = String.valueOf(totalsumper);
                    Billamount.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Billamount.setText(amts);
                }


                if (dpfper >= 12 && dpfper < 13 && billper5 < 1) {  // nested if------------
                    rest = 1 - billper5;
                    String subentper = String.valueOf(billper5);
                    Billspertxt.setText(subentper);

                    //set value of amount
                    float ammt = billper5 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Billamount.setText((amtss));

                    if (subsper5 >= rest) {
                        subsper6 = subsper5 - rest;

                        String textsumw = String.valueOf(Subspertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Subsamount.setText(totalsumperwstring);
                        used120001 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);

                    }


                    if (subsper5 < rest && transper5 >= rest && used120001 != 0) {
                        transper6 = transper5 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used120001 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper5 < rest && healthper5 >= rest && used120001 != 0) {
                        healthper6 = healthper5 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used120001 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper5 < rest && entper5 >= rest && used120001 != 0) {
                        entper6 = entper4 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //------------work 2nd 1% of out of 12% percent----------------------------------------------------------------------------

                float use120001 = 1; //// sees if the code is used


                if (dpfper >= 12 && dpfper < 13 && subsper6 >= 1) {
                    subsper7 = subsper6 - 1;
                    String textsum = String.valueOf(Subspertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + 1;
                    String getval = String.valueOf(totalsumper);
                    Subspertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Subsamount.setText(amts);
                }


                if (dpfper >= 12 && dpfper < 13 && subsper6 < 1) {  // nested if------------
                    rest = 1 - subsper6;
                    String subentper = String.valueOf(subsper6);
                    Subspertxt.setText(subentper);

                    //set value of amount
                    float ammt = transper6 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Subsamount.setText((amtss));

                    if (transper6 >= rest) {
                        transper7 = transper6 - rest;

                        String textsumw = String.valueOf(Transpertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Transamount.setText(totalsumperwstring);
                        use120001 = 1;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);

                    }


                    if (transper6 < rest && healthper6 >= rest && use120001 != 0) {
                        healthper7 = healthper6 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        use120001 = 1;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper6 < rest && entper6 >= rest && use120001 != 0) {
                        entper7 = entper6 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //--------------------rate-------------------13%---------------------------------------------------


                // 3% out of 13%-----------------------------------------------------------------------------


                float used1303 = 1;

                if (dpfper >= 13 && dpfper < 14 && entper >= (3 + (dpfsubdif / 4))) {
                    entper2 = entper - (3 + (dpfsubdif / 4));
                    float cutvalue = (3 + (dpfsubdif / 4));
                    String getval = String.valueOf(cutvalue);
                    Entpertxt.setText(getval);


                    // set value of amount
                    float amt = cutvalue / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Entamount.setText(amts);
                }


                if (dpfper >= 13 && dpfper < 14 && entper < (3 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (3 + (dpfsubdif / 4)) - entper;
                    String subentper = String.valueOf(entper);
                    Entpertxt.setText(subentper);

                    //set value of amount
                    float ammt = entper / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Entamount.setText((amtss));

                    if (outfper >= rest) {
                        outfper2 = outfper - rest;

                        String rea = String.valueOf(rest);
                        Outfpertxt.setText(rea);
                        used1303 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Outfamount.setText(amts);
                    }


                    if (outfper < rest && selfper >= rest && used1303 != 0) {
                        selfper2 = selfper - rest;
                        String rea = String.valueOf(rest);
                        Selfpertxt.setText(rea);
                        used1303 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);
                    }

                    if (selfper < rest && groper >= rest && used1303 != 0) {
                        groper2 = groper - rest;
                        String rea = String.valueOf(rest);
                        Gropertxt.setText(rea);
                        used1303 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper < rest && billper >= rest && used1303 != 0) {
                        billper2 = billper - rest;
                        String rea = String.valueOf(rest);
                        Billspertxt.setText(rea);
                        used1303 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }

                    if (billper < rest && subsper >= rest && used1303 != 0) {
                        subsper2 = subsper - rest;
                        String rea = String.valueOf(rest);
                        Subspertxt.setText(rea);
                        used1303 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper < rest && transper >= rest && used1303 != 0) {
                        transper2 = transper - rest;
                        String rea = String.valueOf(rest);
                        Transpertxt.setText(rea);
                        used1303 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper < rest && healthper >= rest && used1303 != 0) {
                        healthper2 = healthper - rest;
                        String rea = String.valueOf(rest);
                        Healthpertxt.setText(rea);
                        used1303 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }


                    if (healthper < rest && entper >= rest && used1303 != 0) {
                        entper2 = entper - rest;
                        String rea = String.valueOf(rest);
                        Entpertxt.setText(rea);
                        used1303 = 0;


                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//-------------------------------------------------work on  operations 3% out of 13%------
                float used13033 = 1; //// sees if the code is used


                if (dpfper >= 13 && dpfper < 14 && outfper2 >= (3 + (dpfsubdif / 4))) {
                    outper3 = outfper2 - (3 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Outfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (3 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Outfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Outfamount.setText(amts);
                }

                if (dpfper >= 13 && dpfper < 14 && outfper2 < (3 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (3 + (dpfsubdif / 4)) - outfper2;
                    String subentper = String.valueOf(outfper2);
                    Outfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = outfper2 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Outfamount.setText((amtss));

                    if (selfper2 >= rest) {
                        selfper3 = selfper2 - rest;

                        String textsumw = String.valueOf(Selfpertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Selfpertxt.setText(totalsumperwstring);
                        used13033 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);

                    }

                    if (selfper2 < rest && groper2 >= rest && used13033 != 0) {
                        groper3 = groper2 - rest;

                        String textsum = String.valueOf(Gropertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Gropertxt.setText(totalsumperstring);
                        used13033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper2 < rest && billper2 >= rest && used13033 != 0) {
                        billper3 = billper2 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used13033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper2 < rest && subsper2 >= rest && used13033 != 0) {
                        subsper3 = subsper2 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used13033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper2 < rest && transper2 >= rest && used13033 != 0) {
                        transper3 = transper2 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used13033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper2 < rest && healthper2 >= rest && used13033 != 0) {
                        healthper3 = healthper2 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used13033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper2 < rest && entper2 >= rest && used13033 != 0) {
                        entper3 = entper2 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//------------work  2% of out of 13% percent----------------------------------------------------------------------------

                float used13002 = 1; //// sees if the code is used


                if (dpfper >= 13 && dpfper < 14 && selfper3 >= (2 + (dpfsubdif / 4))) {
                    selfper4 = selfper3 - (2 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Selfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (2 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Selfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Selfamount.setText(amts);
                }


                if (dpfper >= 13 && dpfper < 14 && selfper3 < (2 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 4)) - selfper3;
                    String subentper = String.valueOf(outfper2);
                    Selfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = selfper3 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Selfamount.setText((amtss));

                    if (groper3 >= rest) {
                        groper4 = groper3 - rest;

                        String textsumw = String.valueOf(Gropertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Gropertxt.setText(totalsumperwstring);
                        used13002 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);

                    }


                    if (groper3 < rest && billper3 >= rest && used13002 != 0) {
                        billper4 = billper3 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used13002 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper3 < rest && subsper3 >= rest && used13002 != 0) {
                        subsper4 = subsper3 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used13002 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper3 < rest && transper3 >= rest && used13002 != 0) {
                        transper4 = transper3 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used13002 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper3 < rest && healthper3 >= rest && used13002 != 0) {
                        healthper4 = healthper3 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used13002 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper3 < rest && entper3 >= rest && used13002 != 0) {
                        entper4 = entper3 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//------------work 2% of out of 13% percent----------------------------------------------------------------------------

                float us130022 = 1; //// sees if the code is used


                if (dpfper >= 13 && dpfper < 14 && groper4 >= (2 + (dpfsubdif / 4))) {
                    groper5 = groper4 - (2 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Gropertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (2 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Gropertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Groamount.setText(amts);
                }


                if (dpfper >= 13 && dpfper < 14 && groper4 < (2 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (2 + (dpfsubdif / 4)) - groper4;
                    String subentper = String.valueOf(groper4);
                    Gropertxt.setText(subentper);

                    //set value of amount
                    float ammt = groper4 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Groamount.setText((amtss));

                    if (billper4 >= rest) {
                        billper5 = billper4 - rest;

                        String textsumw = String.valueOf(Billspertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Billamount.setText(totalsumperwstring);
                        us130022 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }


                    if (billper4 < rest && subsper4 >= rest && us130022 != 0) {
                        subsper5 = subsper4 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        us130022 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper4 < rest && transper4 >= rest && us130022 != 0) {
                        transper5 = transper4 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        us130022 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper4 < rest && healthper4 >= rest && us130022 != 0) {
                        healthper5 = healthper4 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        us130022 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper4 < rest && entper4 >= rest && us130022 != 0) {
                        entper5 = entper4 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //------------work 1% of out of 13% percent----------------------------------------------------------------------------

                float used130001 = 1; //// sees if the code is used


                if (dpfper >= 13 && dpfper < 14 && billper5 >= 1) {
                    billper6 = billper5 - 1;
                    String textsum = String.valueOf(Billspertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + 1;
                    String getval = String.valueOf(totalsumper);
                    Billamount.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Billamount.setText(amts);
                }


                if (dpfper >= 13 && dpfper < 14 && billper5 < 1) {  // nested if------------
                    rest = 1 - billper5;
                    String subentper = String.valueOf(billper5);
                    Billspertxt.setText(subentper);

                    //set value of amount
                    float ammt = billper5 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Billamount.setText((amtss));

                    if (subsper5 >= rest) {
                        subsper6 = subsper5 - rest;

                        String textsumw = String.valueOf(Subspertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Subsamount.setText(totalsumperwstring);
                        used130001 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);

                    }


                    if (subsper5 < rest && transper5 >= rest && used130001 != 0) {
                        transper6 = transper5 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used130001 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper5 < rest && healthper5 >= rest && used130001 != 0) {
                        healthper6 = healthper5 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used130001 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper5 < rest && entper5 >= rest && used130001 != 0) {
                        entper5 = entper4 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //------------work 2nd 1% of out of 13% percent----------------------------------------------------------------------------

                float use130001 = 1; //// sees if the code is used


                if (dpfper >= 13 && dpfper < 14 && subsper6 >= 1) {
                    subsper7 = subsper6 - 1;
                    String textsum = String.valueOf(Subspertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + 1;
                    String getval = String.valueOf(totalsumper);
                    Subspertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Subsamount.setText(amts);
                }


                if (dpfper >= 13 && dpfper < 14 && subsper6 < 1) {  // nested if------------
                    rest = 1 - subsper6;
                    String subentper = String.valueOf(subsper6);
                    Subspertxt.setText(subentper);

                    //set value of amount
                    float ammt = transper6 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Subsamount.setText((amtss));

                    if (transper6 >= rest) {
                        transper7 = transper6 - rest;

                        String textsumw = String.valueOf(Transpertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Transamount.setText(totalsumperwstring);
                        use130001 = 1;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);

                    }


                    if (transper6 < rest && healthper5 >= rest && use130001 != 0) {
                        healthper6 = healthper5 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        use130001 = 1;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper5 < rest && entper5 >= rest && use130001 != 0) {
                        entper6 = entper5 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //------------work 3nd 1% of out of 13% percent----------------------------------------------------------------------------

                float use1300011 = 1; //// sees if the code is used


                if (dpfper >= 13 && dpfper < 14 && transper7 >= 7) {
                    transper8 = transper6 - 1;
                    String textsum = String.valueOf(Transpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + 1;
                    String getval = String.valueOf(totalsumper);
                    Transpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Transamount.setText(amts);
                }


                if (dpfper >= 13 && dpfper < 14 && transper7 < 1) {  // nested if------------
                    rest = 1 - subsper6;
                    String subentper = String.valueOf(subsper6);
                    Transpertxt.setText(subentper);

                    //set value of amount
                    float ammt = transper6 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Transamount.setText((amtss));

                    if (healthper7 >= rest) {
                        healthper8 = healthper7 - rest;

                        String textsumw = String.valueOf(Healthamount.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Healthamount.setText(totalsumperwstring);
                        use1300011 = 1;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);

                    }


                    if (healthper7 < rest && entper7 >= rest && use130001 != 0) {
                        entper8 = entper7 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }





                //----------------------rate 14% ----------------------------------------------------------------------------------

                // 3% out of 14%-----------------------------------------------------------------------------


                float used1403 = 1;

                if (dpfper >= 14 && dpfper < 15 && entper >= (4 + (dpfsubdif / 4))) {
                    entper2 = entper - (4 + (dpfsubdif / 4));
                    float cutvalue = (4 + (dpfsubdif / 4));
                    String getval = String.valueOf(cutvalue);
                    Entpertxt.setText(getval);


                    // set value of amount
                    float amt = cutvalue / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Entamount.setText(amts);
                }


                if (dpfper >= 14 && dpfper < 15 && entper < (4 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (4 + (dpfsubdif / 4)) - entper;
                    String subentper = String.valueOf(entper);
                    Entpertxt.setText(subentper);

                    //set value of amount
                    float ammt = entper / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Entamount.setText((amtss));

                    if (outfper >= rest) {
                        outfper2 = outfper - rest;

                        String rea = String.valueOf(rest);
                        Outfpertxt.setText(rea);
                        used1303 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Outfamount.setText(amts);
                    }


                    if (outfper < rest && selfper >= rest && used1403 != 0) {
                        selfper2 = selfper - rest;
                        String rea = String.valueOf(rest);
                        Selfpertxt.setText(rea);
                        used1403 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);
                    }

                    if (selfper < rest && groper >= rest && used1403 != 0) {
                        groper2 = groper - rest;
                        String rea = String.valueOf(rest);
                        Gropertxt.setText(rea);
                        used1403 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper < rest && billper >= rest && used1403 != 0) {
                        billper2 = billper - rest;
                        String rea = String.valueOf(rest);
                        Billspertxt.setText(rea);
                        used1403 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }

                    if (billper < rest && subsper >= rest && used1403 != 0) {
                        subsper2 = subsper - rest;
                        String rea = String.valueOf(rest);
                        Subspertxt.setText(rea);
                        used1403 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper < rest && transper >= rest && used1403 != 0) {
                        transper2 = transper - rest;
                        String rea = String.valueOf(rest);
                        Transpertxt.setText(rea);
                        used1403 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper < rest && healthper >= rest && used1403 != 0) {
                        healthper2 = healthper - rest;
                        String rea = String.valueOf(rest);
                        Healthpertxt.setText(rea);
                        used1403 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }


                    if (healthper < rest && entper >= rest && used1403 != 0) {
                        entper2 = entper - rest;
                        String rea = String.valueOf(rest);
                        Entpertxt.setText(rea);
                        used1403 = 0;


                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//-------------------------------------------------work on  operations 3% out of 14%------
                float used14033 = 1; //// sees if the code is used


                if (dpfper >= 14 && dpfper < 15 && outfper2 >= (3 + (dpfsubdif / 4))) {
                    outper3 = outfper2 - (3 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Outfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (3 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Outfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Outfamount.setText(amts);
                }

                if (dpfper >= 14 && dpfper < 15 && outfper2 < (3 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (3 + (dpfsubdif / 4)) - outfper2;
                    String subentper = String.valueOf(outfper2);
                    Outfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = outfper2 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Outfamount.setText((amtss));

                    if (selfper2 >= rest) {
                        selfper3 = selfper2 - rest;

                        String textsumw = String.valueOf(Selfpertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Selfpertxt.setText(totalsumperwstring);
                        used14033 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);

                    }

                    if (selfper2 < rest && groper2 >= rest && used14033 != 0) {
                        groper3 = groper2 - rest;

                        String textsum = String.valueOf(Gropertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Gropertxt.setText(totalsumperstring);
                        used14033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper2 < rest && billper2 >= rest && used14033 != 0) {
                        billper3 = billper2 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used14033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper2 < rest && subsper2 >= rest && used14033 != 0) {
                        subsper3 = subsper2 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used14033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper2 < rest && transper2 >= rest && used14033 != 0) {
                        transper3 = transper2 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used14033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper2 < rest && healthper2 >= rest && used14033 != 0) {
                        healthper3 = healthper2 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used14033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper2 < rest && entper2 >= rest && used14033 != 0) {
                        entper3 = entper2 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//------------work  3% of out of 14% percent----------------------------------------------------------------------------

                float used14002 = 1; //// sees if the code is used


                if (dpfper >= 14 && dpfper < 15 && selfper3 >= (3 + (dpfsubdif / 4))) {
                    selfper4 = selfper3 - (3 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Selfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (3 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Selfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Selfamount.setText(amts);
                }


                if (dpfper >= 14 && dpfper < 15 && selfper3 < (3 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (3 + (dpfsubdif / 4)) - selfper3;
                    String subentper = String.valueOf(outfper2);
                    Selfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = selfper3 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Selfamount.setText((amtss));

                    if (groper3 >= rest) {
                        groper4 = groper3 - rest;

                        String textsumw = String.valueOf(Gropertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Gropertxt.setText(totalsumperwstring);
                        used14002 = 1;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);

                    }


                    if (groper3 < rest && billper3 >= rest && used14002 != 0) {
                        billper4 = billper3 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used14002 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper3 < rest && subsper3 >= rest && used14002 != 0) {
                        subsper4 = subsper3 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used14002 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper3 < rest && transper3 >= rest && used14002 != 0) {
                        transper4 = transper3 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used14002 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper3 < rest && healthper3 >= rest && used14002 != 0) {
                        healthper4 = healthper3 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used14002 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper3 < rest && entper3 >= rest && used14002 != 0) {
                        entper4 = entper3 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//------------work 1% of out of 14% percent----------------------------------------------------------------------------

                float us140022 = 1; //// sees if the code is used


                if (dpfper >= 14 && dpfper < 15 && groper4 >= (1 + (dpfsubdif / 4))) {
                    groper5 = groper4 - (1 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Gropertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (1 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Gropertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Groamount.setText(amts);
                }


                if (dpfper >= 14 && dpfper < 15 && groper4 < (1 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (1 + (dpfsubdif / 4)) - groper4;
                    String subentper = String.valueOf(groper4);
                    Gropertxt.setText(subentper);

                    //set value of amount
                    float ammt = groper4 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Groamount.setText((amtss));

                    if (billper4 >= rest) {
                        billper5 = billper4 - rest;

                        String textsumw = String.valueOf(Billspertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Billamount.setText(totalsumperwstring);
                        us140022 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }


                    if (billper4 < rest && subsper4 >= rest && us140022 != 0) {
                        subsper5 = subsper4 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        us140022 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper4 < rest && transper4 >= rest && us140022 != 0) {
                        transper5 = transper4 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        us140022 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper4 < rest && healthper4 >= rest && us140022 != 0) {
                        healthper5 = healthper4 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        us140022 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper4 < rest && entper4 >= rest && us140022 != 0) {
                        entper5 = entper4 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //------------work 1% of out of 14% percent----------------------------------------------------------------------------

                float used140001 = 1; //// sees if the code is used


                if (dpfper >= 14 && dpfper < 15 && billper5 >= 1) {
                    billper6 = billper5 - 1;
                    String textsum = String.valueOf(Billspertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + 1;
                    String getval = String.valueOf(totalsumper);
                    Billspertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Billamount.setText(amts);
                }


                if (dpfper >= 14 && dpfper < 15 && billper5 < 1) {  // nested if------------
                    rest = 1 - billper5;
                    String subentper = String.valueOf(billper5);
                    Billspertxt.setText(subentper);

                    //set value of amount
                    float ammt = billper5 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Billamount.setText((amtss));

                    if (subsper5 >= rest) {
                        subsper6 = subsper5 - rest;

                        String textsumw = String.valueOf(Subspertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Subsamount.setText(totalsumperwstring);
                        used140001 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);

                    }


                    if (subsper5 < rest && transper5 >= rest && used140001 != 0) {
                        transper6 = transper5 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used140001 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper5 < rest && healthper5 >= rest && used140001 != 0) {
                        healthper6 = healthper5 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used140001 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper5 < rest && entper5 >= rest && used140001 != 0) {
                        entper6 = entper5 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }



                //-------------------------------------------------------1% --------------of 14----------------

                float use140040001 = 1; //// sees if the code is used


                if (dpfper >= 14 && dpfper < 15 && subsper6 >= 1) {
                    subsper7 = subsper6 - 1;
                    String textsum = String.valueOf(Subspertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + 1;
                    String getval = String.valueOf(totalsumper);
                    Subspertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Subsamount.setText(amts);
                }


                if (dpfper >= 14 && dpfper < 15 && subsper6 < 1) {  // nested if------------
                    rest = 1 - subsper6;
                    String subentper = String.valueOf(subsper6);
                    Subspertxt.setText(subentper);

                    //set value of amount
                    float ammt = transper6 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Subsamount.setText((amtss));

                    if (transper6 >= rest) {
                        transper7 = transper6 - rest;

                        String textsumw = String.valueOf(Transpertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Transamount.setText(totalsumperwstring);
                        use140040001 = 1;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);

                    }


                    if (transper6 < rest && healthper6 >= rest && use140040001 != 0) {
                        healthper7 = healthper6 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        use140040001 = 1;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper6 < rest && entper6 >= rest && use140040001 != 0) {
                        entper7 = entper6 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //------------work 3nd 1% of out of 15% percent----------------------------------------------------------------------------

                float use14900011 = 1; //// sees if the code is used


                if (dpfper >= 14 && dpfper < 15 && transper7 >= 1) {
                    transper8 = transper7 - 1;
                    String textsum = String.valueOf(Transpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + 1;
                    String getval = String.valueOf(totalsumper);
                    Transpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Transamount.setText(amts);
                }


                if (dpfper >= 14 && dpfper < 15 && transper7 < 1) {  // nested if------------
                    rest = 1 - transper7;
                    String subentper = String.valueOf(transper7);
                    Transpertxt.setText(subentper);

                    //set value of amount
                    float ammt = transper7 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Transamount.setText((amtss));

                    if (healthper7 >= rest) {
                        healthper8 = healthper7 - rest;

                        String textsumw = String.valueOf(Healthamount.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Healthamount.setText(totalsumperwstring);
                        use14900011  = 1;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);

                    }


                    if (healthper7 < rest && entper7 >= rest && use14900011  != 0) {
                        entper8 = entper7 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }





























                //-----------------------------15% rate-------------------------------------------------------


                //---------------------------------------------------4%-------------------out of 15 % -----------------


                float used1503 = 1;

                if (dpfper >= 15 && dpfper < 16 && entper >= (4 + (dpfsubdif / 4))) {
                    entper2 = entper - (4 + (dpfsubdif / 4));
                    float cutvalue = (4 + (dpfsubdif / 4));
                    String getval = String.valueOf(cutvalue);
                    Entpertxt.setText(getval);


                    // set value of amount
                    float amt = cutvalue / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Entamount.setText(amts);
                }


                if (dpfper >= 15 && dpfper < 16 && entper < (4 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (4 + (dpfsubdif / 4)) - entper;
                    String subentper = String.valueOf(entper);
                    Entpertxt.setText(subentper);

                    //set value of amount
                    float ammt = entper / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Entamount.setText((amtss));

                    if (outfper >= rest) {
                        outfper2 = outfper - rest;

                        String rea = String.valueOf(rest);
                        Outfpertxt.setText(rea);
                        used1303 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Outfamount.setText(amts);
                    }


                    if (outfper < rest && selfper >= rest && used1503 != 0) {
                        selfper2 = selfper - rest;
                        String rea = String.valueOf(rest);
                        Selfpertxt.setText(rea);
                        used1503 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);
                    }

                    if (selfper < rest && groper >= rest && used1503 != 0) {
                        groper2 = groper - rest;
                        String rea = String.valueOf(rest);
                        Gropertxt.setText(rea);
                        used1503 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper < rest && billper >= rest && used1503 != 0) {
                        billper2 = billper - rest;
                        String rea = String.valueOf(rest);
                        Billspertxt.setText(rea);
                        used1503 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }

                    if (billper < rest && subsper >= rest && used1503 != 0) {
                        subsper2 = subsper - rest;
                        String rea = String.valueOf(rest);
                        Subspertxt.setText(rea);
                        used1503 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper < rest && transper >= rest && used1503 != 0) {
                        transper2 = transper - rest;
                        String rea = String.valueOf(rest);
                        Transpertxt.setText(rea);
                        used1503 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper < rest && healthper >= rest && used1503 != 0) {
                        healthper2 = healthper - rest;
                        String rea = String.valueOf(rest);
                        Healthpertxt.setText(rea);
                        used1503 = 0;

                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }


                    if (healthper < rest && entper >= rest && used1503 != 0) {
                        entper2 = entper - rest;
                        String rea = String.valueOf(rest);
                        Entpertxt.setText(rea);
                        used1503 = 0;


                        // set value of amount
                        float amt = rest / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//-------------------------------------------------work on  operations 3% out of 14%------
                float used15033 = 1; //// sees if the code is used


                if (dpfper >= 15 && dpfper < 16 && outfper2 >= (3 + (dpfsubdif / 4))) {
                    outper3 = outfper2 - (3 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Outfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (3 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Outfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Outfamount.setText(amts);
                }

                if (dpfper >= 15 && dpfper < 16 && outfper2 < (3 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (3 + (dpfsubdif / 4)) - outfper2;
                    String subentper = String.valueOf(outfper2);
                    Outfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = outfper2 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Outfamount.setText((amtss));

                    if (selfper2 >= rest) {
                        selfper3 = selfper2 - rest;

                        String textsumw = String.valueOf(Selfpertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Selfpertxt.setText(totalsumperwstring);
                        used15033 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Selfamount.setText(amts);

                    }

                    if (selfper2 < rest && groper2 >= rest && used15033 != 0) {
                        groper3 = groper2 - rest;

                        String textsum = String.valueOf(Gropertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Gropertxt.setText(totalsumperstring);
                        used15033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);
                    }

                    if (groper2 < rest && billper2 >= rest && used15033 != 0) {
                        billper3 = billper2 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used15033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper2 < rest && subsper2 >= rest && used15033 != 0) {
                        subsper3 = subsper2 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used15033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper2 < rest && transper2 >= rest && used15033 != 0) {
                        transper3 = transper2 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used15033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper2 < rest && healthper2 >= rest && used15033 != 0) {
                        healthper3 = healthper2 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used15033 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper2 < rest && entper2 >= rest && used15033 != 0) {
                        entper3 = entper2 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//------------work  3% of out of 15% percent----------------------------------------------------------------------------

                float used15002 = 1; //// sees if the code is used


                if (dpfper >= 15 && dpfper < 16 && selfper3 >= (3 + (dpfsubdif / 4))) {
                    selfper4 = selfper3 - (3 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Selfpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (3 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Selfpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Selfamount.setText(amts);
                }


                if (dpfper >= 15 && dpfper < 16 && selfper3 < (3 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (3 + (dpfsubdif / 4)) - selfper3;
                    String subentper = String.valueOf(outfper2);
                    Selfpertxt.setText(subentper);

                    //set value of amount
                    float ammt = selfper3 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Selfamount.setText((amtss));

                    if (groper3 >= rest) {
                        groper4 = groper3 - rest;

                        String textsumw = String.valueOf(Gropertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Gropertxt.setText(totalsumperwstring);
                        used15002 = 1;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Groamount.setText(amts);

                    }


                    if (groper3 < rest && billper3 >= rest && used15002 != 0) {
                        billper4 = billper3 - rest;

                        String textsum = String.valueOf(Billspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Billspertxt.setText(totalsumperstring);
                        used15002 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);
                    }

                    if (billper3 < rest && subsper3 >= rest && used15002 != 0) {
                        subsper4 = subsper3 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        used15002 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper3 < rest && transper3 >= rest && used15002 != 0) {
                        transper4 = transper3 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used15002 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper3 < rest && healthper3 >= rest && used15002 != 0) {
                        healthper4 = healthper3 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used15002 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper3 < rest && entper3 >= rest && used15002 != 0) {
                        entper4 = entper3 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


//------------work 1% of out of 14% percent----------------------------------------------------------------------------

                float us150022 = 1; //// sees if the code is used


                if (dpfper >= 15 && dpfper < 16 && groper4 >= (1 + (dpfsubdif / 4))) {
                    groper5 = groper4 - (1 + (dpfsubdif / 4));
                    String textsum = String.valueOf(Gropertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + (1 + (dpfsubdif / 4));
                    String getval = String.valueOf(totalsumper);
                    Gropertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Groamount.setText(amts);
                }


                if (dpfper >= 15 && dpfper < 16 && groper4 < (1 + (dpfsubdif / 4))) {  // nested if------------
                    rest = (1 + (dpfsubdif / 4)) - groper4;
                    String subentper = String.valueOf(groper4);
                    Gropertxt.setText(subentper);

                    //set value of amount
                    float ammt = groper4 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Groamount.setText((amtss));

                    if (billper4 >= rest) {
                        billper5 = billper4 - rest;

                        String textsumw = String.valueOf(Billspertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Billamount.setText(totalsumperwstring);
                        us150022 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Billamount.setText(amts);

                    }


                    if (billper4 < rest && subsper4 >= rest && us150022 != 0) {
                        subsper5 = subsper4 - rest;

                        String textsum = String.valueOf(Subspertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Subspertxt.setText(totalsumperstring);
                        us150022 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);
                    }

                    if (subsper4 < rest && transper4 >= rest && us150022 != 0) {
                        transper5 = transper4 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        us150022 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper4 < rest && healthper4 >= rest && us150022 != 0) {
                        healthper5 = healthper4 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        us150022 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper4 < rest && entper4 >= rest && us150022 != 0) {
                        entper5 = entper4 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //------------work 1% of out of 14% percent----------------------------------------------------------------------------

                float used150001 = 1; //// sees if the code is used


                if (dpfper >= 15 && dpfper < 16 && billper5 >= 1) {
                    billper6 = billper5 - 1;
                    String textsum = String.valueOf(Billspertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + 1;
                    String getval = String.valueOf(totalsumper);
                    Billamount.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Billamount.setText(amts);
                }


                if (dpfper >= 15 && dpfper < 16 && billper5 < 1) {  // nested if------------
                    rest = 1 - billper5;
                    String subentper = String.valueOf(billper5);
                    Billspertxt.setText(subentper);

                    //set value of amount
                    float ammt = billper5 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Billamount.setText((amtss));

                    if (subsper5 >= rest) {
                        subsper6 = subsper5 - rest;

                        String textsumw = String.valueOf(Subspertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Subsamount.setText(totalsumperwstring);
                        used150001 = 0;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Subsamount.setText(amts);

                    }


                    if (subsper5 < rest && transper5 >= rest && used150001 != 0) {
                        transper6 = transper5 - rest;

                        String textsum = String.valueOf(Transpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Transpertxt.setText(totalsumperstring);
                        used150001 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);
                    }

                    if (transper5 < rest && healthper5 >= rest && used150001 != 0) {
                        healthper6 = healthper5 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        used150001 = 0;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper5 < rest && entper5 >= rest && used150001 != 0) {
                        entper6 = entper5 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //------------work 2nd 1% of out of 15% percent----------------------------------------------------------------------------

                float use150001 = 1; //// sees if the code is used


                if (dpfper >= 15 && dpfper < 16 && subsper6 >= 1) {
                    subsper7 = subsper6 - 1;
                    String textsum = String.valueOf(Subspertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + 1;
                    String getval = String.valueOf(totalsumper);
                    Subspertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Subsamount.setText(amts);
                }


                if (dpfper >= 15 && dpfper < 16 && subsper6 < 1) {  // nested if------------
                    rest = 1 - subsper6;
                    String subentper = String.valueOf(subsper6);
                    Subspertxt.setText(subentper);

                    //set value of amount
                    float ammt = transper6 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Subsamount.setText((amtss));

                    if (transper6 >= rest) {
                        transper7 = transper6 - rest;

                        String textsumw = String.valueOf(Transpertxt.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Transamount.setText(totalsumperwstring);
                        use150001 = 1;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Transamount.setText(amts);

                    }


                    if (transper6 < rest && healthper6 >= rest && use150001 != 0) {
                        healthper7 = healthper6 - rest;

                        String textsum = String.valueOf(Healthpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Healthpertxt.setText(totalsumperstring);
                        use150001 = 1;

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);
                    }

                    if (healthper6 < rest && entper6 >= rest && use150001 != 0) {
                        entper7 = entper6 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                //------------work 3nd 1% of out of 15% percent----------------------------------------------------------------------------

                float use1500011 = 1; //// sees if the code is used


                if (dpfper >= 15 && dpfper < 16 && transper7 >= 1) {
                    transper8 = transper7 - 1;
                    String textsum = String.valueOf(Transpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + 1;
                    String getval = String.valueOf(totalsumper);
                    Transpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Transamount.setText(amts);
                }


                if (dpfper >= 15 && dpfper < 16 && transper7 < 1) {  // nested if------------
                    rest = 1 - transper7;
                    String subentper = String.valueOf(transper7);
                    Transpertxt.setText(subentper);

                    //set value of amount
                    float ammt = transper7 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Transamount.setText((amtss));

                    if (healthper7 >= rest) {
                        healthper8 = healthper7 - rest;

                        String textsumw = String.valueOf(Healthamount.getText());
                        float Textsumw = Float.parseFloat(textsumw);
                        float totalsumperw = Textsumw + rest;
                        String totalsumperwstring = String.valueOf(totalsumperw);
                        Healthamount.setText(totalsumperwstring);
                        use1500011 = 1;

                        // set value of amount
                        float amt = totalsumperw / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Healthamount.setText(amts);

                    }


                    if (healthper7 < rest && entper7 >= rest && use1500011 != 0) {
                        entper8 = entper7 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                float us1500011 = 1; //// sees if the code is used


                if (dpfper >= 15 && dpfper < 16 && healthper8 >= 1) {
                    float healthper9 = healthper8 - 1;
                    String textsum = String.valueOf(Healthpertxt.getText());
                    float Textsum = Float.parseFloat(textsum);
                    float totalsumper = Textsum + 1;
                    String getval = String.valueOf(totalsumper);
                    Healthpertxt.setText(getval);


                    float amt = totalsumper / 100 * sumofmoney;
                    String amts = String.valueOf(amt);
                    Healthamount.setText(amts);
                }


                if (dpfper >= 15 && dpfper < 16 && healthper8 < 1) {  // nested if------------
                    rest = 1 - subsper6;
                    String subentper = String.valueOf(subsper6);
                    Healthpertxt.setText(subentper);

                    //set value of amount
                    float ammt = transper6 / 100 * sumofmoney;
                    String amtss = String.valueOf(ammt);
                    Healthamount.setText((amtss));


                    if (healthper8 < rest && entper7 >= rest && use1500011 != 0) {
                        entper8 = entper7 - rest;

                        String textsum = String.valueOf(Entpertxt.getText());
                        float Textsum = Float.parseFloat(textsum);
                        float totalsumper = Textsum + rest;
                        String totalsumperstring = String.valueOf(totalsumper);
                        Entpertxt.setText(totalsumperstring);

                        // set value of amount
                        float amt = totalsumper / 100 * sumofmoney;
                        String amts = String.valueOf(amt);
                        Entamount.setText(amts);

                    }


                    //----------------nested if
                }


                /// the pogrssion work ------------------------------------------


                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                String val = String.valueOf(Entpertxt.getText());
                float valint = Float.parseFloat(val);
                valint = Math.round(valint);
                int forval;

                for (forval = 0; forval <= valint; forval = forval + 1) {
                    Entpbar.setProgress(forval);
                }

                //pogrssion for outfper

                String val1 = String.valueOf(Outfpertxt.getText());
                float valint1 = Float.parseFloat(val1);
                int forval1;
                valint1 = Math.round(valint1);
                for (forval1 = 0; forval1 <= valint1; forval1 = forval1 + 1) {
                    Oufpbar.setProgress(forval1);
                }


                //pograssion bar for selfper

                String val2 = String.valueOf(Selfpertxt.getText());
                float valint2 = Float.parseFloat((val2));
                int forval2;
                valint2 = Math.round(valint2);
                for (forval2 = 0; forval2 <= valint2; forval2 = forval2 + 1) {
                    Selfbar.setProgress(forval2);
                }


                //pograssion bar for healthper

                String val3 = String.valueOf(Healthpertxt.getText());
                float valint3 = Float.parseFloat((val3));
                int forval3;
                valint3 = Math.round(valint3);
                for (forval3 = 0; forval3 <= valint3; forval3 = forval3 + 1) {
                    Healthbar.setProgress(forval3);
                }

                //pograssion bar for billper

                String val4 = String.valueOf(Billspertxt.getText());
                float valint4 = Float.parseFloat((val4));
                int forval4;
                valint4 = Math.round(valint4);
                for (forval4 = 0; forval4 <= valint4; forval4 = forval4 + 1) {
                    Billsbars.setProgress(forval4);
                }


                //pograssion bar for groper

                String val5 = String.valueOf(Gropertxt.getText());
                float valint5 = Float.parseFloat((val5));
                int forval5;
                valint5 = Math.round(valint5);
                for (forval5 = 0; forval5 <= valint5; forval5 = forval5 + 1) {
                    Grobar.setProgress(forval5);
                }

                //pograssion bar for transbar

                String val6 = String.valueOf(Transpertxt.getText());
                float valint6 = Float.parseFloat((val6));
                int forval6;
                valint6 = Math.round(valint6);
                for (forval6 = 0; forval6 <= valint6; forval6 = forval6 + 1) {
                    Transbar.setProgress(forval6);
                }


                //pograssion bar for subsper

                String val7 = String.valueOf(Subspertxt.getText());
                float valint7 = Float.parseFloat((val7));
                int forval7;
                valint7 = Math.round(valint7);
                for (forval7 = 0; forval7 <= valint7; forval7 = forval7 + 1) {
                    Subsbar.setProgress(forval7);
                }










            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


        return v;


    }





}
