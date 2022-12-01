package com.example.trustbooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AppReg extends AppCompatActivity {

    private EditText fn1,ln1,pn1,da1,bg1 ;
    Button reg1,search;
    DatePickerDialog.OnDateSetListener setListener;
    DbHelper DB;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AppReg.this,Dashboard.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_reg);

        fn1 = findViewById(R.id.fn1);
        ln1 = findViewById(R.id.ln1);
        da1 = findViewById(R.id.da1);
        pn1 = findViewById(R.id.pn1);
        bg1 = findViewById(R.id.bg1);
        DB = new DbHelper(this);
        search = findViewById(R.id.searchcus1);
        reg1 = findViewById(R.id.reg1);

        //---------------------------------------------------calender -------------------
        Calendar calender = Calendar.getInstance();
        final int year = calender.get(Calendar.YEAR);
        final int month = calender.get(Calendar.MONTH);
        final int day = calender.get(Calendar.DAY_OF_MONTH);

        da1.setOnClickListener(v -> {

            DatePickerDialog dpd = new DatePickerDialog(AppReg.this, android.R.style.Theme_Holo_Light_Dialog,setListener,year,month,day);
            dpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dpd.show();
        });


        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                String date = dayOfMonth+"/"+month+"/"+year ;
                da1.setText(date);


            }
        };








        //----------------------------------------------------------------------------



        //Regirstration
        reg1.setOnClickListener(v -> {

            if(        fn1.getText().toString().equals("")
                    || ln1.getText().toString().equals("")
                    || da1.getText().toString().equals("")
                    || pn1.getText().toString().equals("")
                    || bg1.getText().toString().equals("")) {

                Toast.makeText(this, "The form cannot be empty", Toast.LENGTH_SHORT).show();

            }

            else {


                //-----------------------------code for registration-------------------

                try {

                    Boolean checkcus = DB.checkcustomer(pn1.getText().toString());
                    if (checkcus == true) {


                        Boolean checkapp = DB.checkapp(pn1.getText().toString());
                        if (checkapp == false) {

                            Boolean insert = DB.insertAppoinment(
                                    fn1.getText().toString()
                                    , ln1.getText().toString()
                                    , pn1.getText().toString()
                                    , bg1.getText().toString()
                                    , da1.getText().toString());


                            if (insert == true) {
                                Toast.makeText(AppReg.this, "Appoinment created", Toast.LENGTH_SHORT).show();


                            } else {
                                Toast.makeText(AppReg.this, "Error in creating appoinment", Toast.LENGTH_SHORT).show();
                            }


                        } //end of check appoinment

                        else {
                            Toast.makeText(AppReg.this, "Appoinment already exists", Toast.LENGTH_SHORT).show();
                        } // end of else

                    } // end of // check cus in database


                    else {
                        Toast.makeText(AppReg.this, "Customer not found please check if the phone number is valid ", Toast.LENGTH_SHORT).show();
                    } // end of else


                    }//tryyyyyyyy

                catch(Exception e){

                        Toast.makeText(AppReg.this, "Error : " + e + " ", Toast.LENGTH_SHORT).show();
                    }



            }

        });


        search.setOnClickListener(v -> {

            if(pn1.getText().toString().equals("")) {
                   pn1.setError("Please the phone number to search");
            }

            else {
                //method to search
                try {
                    DB.searchcustomer(pn1.getText().toString(), fn1, ln1, bg1, AppReg.this);
                } catch (Exception e) {
                    Toast.makeText(this, "Error: " + e + "", Toast.LENGTH_SHORT).show();

                }

            }

        });






    }
}