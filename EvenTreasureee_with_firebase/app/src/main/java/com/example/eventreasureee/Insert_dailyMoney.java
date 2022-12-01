package com.example.eventreasureee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Insert_dailyMoney extends AppCompatActivity {

    TextView dattxt;
    EditText record;
    Button insertrecord;

    private FirebaseUser user;
    private DatabaseReference Dr;
    private String userID;
    private DatabaseReference Drs;


    private Calendar calendar;
    private String date11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_daily_money);

        user = FirebaseAuth.getInstance().getCurrentUser();
        Dr = FirebaseDatabase.getInstance().getReference("Linechart");
        Drs = FirebaseDatabase.getInstance().getReference("DateStart");
        userID = user.getUid();

        dattxt = findViewById(R.id.date);
        record = findViewById(R.id.dailycosttxt);
        insertrecord = findViewById(R.id.insertcost);


        SimpleDateFormat dateFormat =new SimpleDateFormat("dd/MM/yyyy");
       date11 = dateFormat.format(calendar.getInstance().getTime());
       dattxt.setText(date11);


        //--------------------------date validation if statement -------------------------------

        SimpleDateFormat dateFmat =new SimpleDateFormat("dd-M-yyyy");
        String daval = dateFmat.format(calendar.getInstance().getTime());

        DatabaseReference dateval = Dr.child(userID).child("datevalidation");



        dateval.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                String readdate = snapshot.getValue().toString(); // reads the date


                if (snapshot.child(daval).exists()) {
                    Dr.child(userID).child("datevalidation").child(daval).child(daval);

                }

                else {
                    Dr.child(userID).child("datevalidation").child(daval).setValue("0");
                }



            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });





























        insertrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(record.getText())){
                    record.setError("Insert the record please");
                    return;
                }


                //--------------------------date validation -------------------------------

                SimpleDateFormat dateFmat =new SimpleDateFormat("dd-M-yyyy");
                String daval = dateFmat.format(calendar.getInstance().getTime());





                DatabaseReference dateval = Dr.child(userID).child("datevalidation").child(daval);

                dateval.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                        String dates = snapshot.getValue().toString();

                        if (dates.equals(daval)) {
                            Toast.makeText(Insert_dailyMoney.this, "Today's date's cost already inserted", Toast.LENGTH_SHORT).show();



                        }



                        else {


                            //---------------------------------------------------------------------------------------


                            DatabaseReference ref1 = Drs.child(userID).child("Boolean"); // indentifies date start


                            String y = String.valueOf(record.getText().toString());


                            Drs.child(userID).child("Boolean").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                                    String booll = snapshot.getValue().toString();

                                    try {
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy");
                                        String lastdate = formatter.format(calendar.getInstance().getTime());
                                        Date todaydate = formatter.parse(lastdate);


                                        if (booll.equals("Yes")) {

                                            //--------------------------------------start of the if statement---------------------------------------------------
                                            Date day0 = null;
                                            String dayy0 = null;


                                            Date day1 = null;
                                            String dayy1 = null;

                                            Date day2 = null;
                                            String dayy2 = null;
                                            ;

                                            Date day3 = null;
                                            String dayy3 = null;
                                            ;

                                            Date day4 = null;
                                            String dayy4 = null;
                                            ;

                                            Date day5 = null;
                                            String dayy5 = null;
                                            ;

                                            Date day6 = null;
                                            String dayy6 = null;
                                            ;

                                            Date day7 = null;
                                            String dayy7 = null;


                                            //-----------------for day 0 -------------------

                                            Calendar cal0 = Calendar.getInstance();
                                            cal0.setTime(todaydate);

                                            // add 0 days to current day
                                            cal0.add(Calendar.DAY_OF_MONTH, 0);
                                            day0 = cal0.getTime();

                                            dayy0 = formatter.format(day0);


                                            //-----------------for day 1 -------------------

                                            Calendar cal = Calendar.getInstance();
                                            cal.setTime(todaydate);

                                            // add 1 days to current day
                                            cal.add(Calendar.DAY_OF_MONTH, 1);
                                            day1 = cal.getTime();

                                            dayy1 = formatter.format(day1);


                                            //-----------------for day 2 -------------------


                                            Calendar cal2 = Calendar.getInstance();
                                            cal2.setTime(todaydate);

                                            // add 2 days to current day
                                            cal2.add(Calendar.DAY_OF_MONTH, 2);
                                            day2 = cal2.getTime();

                                            dayy2 = formatter.format(day2);


                                            //-----------------for day 3 -------------------


                                            Calendar cal3 = Calendar.getInstance();
                                            cal3.setTime(todaydate);

                                            // add 2 days to current day
                                            cal3.add(Calendar.DAY_OF_MONTH, 3);
                                            day3 = cal3.getTime();

                                            dayy3 = formatter.format(day3);


                                            //-----------------for day 4 -------------------


                                            Calendar cal4 = Calendar.getInstance();
                                            cal4.setTime(todaydate);

                                            // add 4 days to current day
                                            cal4.add(Calendar.DAY_OF_MONTH, 4);
                                            day4 = cal4.getTime();

                                            dayy4 = formatter.format(day4);


                                            //-----------------for day 5 -------------------

                                            Calendar cal5 = Calendar.getInstance();
                                            cal5.setTime(todaydate);

                                            // add 4 days to current day
                                            cal5.add(Calendar.DAY_OF_MONTH, 5);
                                            day5 = cal5.getTime();

                                            dayy5 = formatter.format(day5);


                                            //-----------------for day 6 -------------------


                                            Calendar cal6 = Calendar.getInstance();
                                            cal6.setTime(todaydate);

                                            // add 4 days to current day
                                            cal6.add(Calendar.DAY_OF_MONTH, 6);
                                            day6 = cal6.getTime();

                                            dayy6 = formatter.format(day6);


                                            //-----------------for day 7 -------------------

                                            Calendar cal7 = Calendar.getInstance();
                                            cal7.setTime(todaydate);

                                            // add 7 days to current day
                                            cal7.add(Calendar.DAY_OF_MONTH, 7);
                                            day7 = cal7.getTime();

                                            dayy7 = formatter.format(day7);

                                            DatabaseReference refspeding = Dr.child(userID).child("Spending"); // line chart

                                            SimpleDateFormat form0 = new SimpleDateFormat("dd-M-yyyy");


                                            String datevaal0 = form0.format(day0);  // gets all the dates and format them
                                            String datevaal1 = form0.format(day1);
                                            String datevaal2 = form0.format(day2);
                                            String datevaal3 = form0.format(day3);
                                            String datevaal4 = form0.format(day4);
                                            String datevaal5 = form0.format(day5);
                                            String datevaal6 = form0.format(day6);
                                            String datevaal7 = form0.format(day7);

                                            // sets the week
                                            DatabaseReference df0 = refspeding.child(datevaal0);
                                            DatabaseReference df1 = refspeding.child(datevaal1);
                                            DatabaseReference df2 = refspeding.child(datevaal2);
                                            DatabaseReference df3 = refspeding.child(datevaal3);
                                            DatabaseReference df4 = refspeding.child(datevaal4);
                                            DatabaseReference df5 = refspeding.child(datevaal5);
                                            DatabaseReference df6 = refspeding.child(datevaal6);
                                            DatabaseReference df7 = refspeding.child(datevaal7);

                                            // sets the value of the dates
                                            df0.setValue("0");
                                            df1.setValue("0");
                                            df2.setValue("0");
                                            df3.setValue("0");
                                            df4.setValue("0");
                                            df5.setValue("0");
                                            df6.setValue("0");
                                            df7.setValue("0");
                                            //------------------------------------------- end of the if statement------------------------------------------

                                            DatabaseReference sd = Drs.child(userID).child("Start");
                                            sd.setValue(date11);  // done with setting start value and boolean value to "no"

                                        }


                                        ref1.setValue("No"); // sets boolean value


                                        DatabaseReference ref = Dr.child(userID);// line chart reference with id
                                        DatabaseReference ref2 = Dr.child(userID);
                                        // format the date
                                        DatabaseReference add1 = ref.child("Spending").child(lastdate);
                                        add1.setValue(y); // sets the spending


                                        String id = ref.push().getKey(); // gets a random id
                                        ref.child("ListDate").child(id).setValue(date11);
                                        // inserts the date into the ListDate


                                        String id1 = ref2.push().getKey(); // gets a random id
                                        ref2.child("ListSpending").child(id1).setValue(y);
                                        ;
                                        // inserts the date into the ListSpending


                                        Dr.child(userID).child("datevalidation").child(daval).setValue(daval);
                                        // inserting values to datevalidation


                                        Toast.makeText(getApplicationContext(), "Record is saved", Toast.LENGTH_SHORT).show();






                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }


                                }// end of snapshot;

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });


                        } // end of else ----------------------------------------------------------





                    }//------------------------------------- end of first value event listener


                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });










            }


        });

        
    }
}