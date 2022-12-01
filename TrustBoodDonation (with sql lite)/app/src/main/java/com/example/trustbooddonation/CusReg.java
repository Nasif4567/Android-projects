package com.example.trustbooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class CusReg extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText fn,ln,db,ad,bg,em,pn ;
    Button reg;
    Spinner gn;
    DatePickerDialog.OnDateSetListener setListener;
    String Gender;
    DbHelper DB;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CusReg.this,Dashboard.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_reg);
        DB = new DbHelper(this);
        reg = findViewById(R.id.reg1);

        fn = findViewById(R.id.fn1);
        ln = findViewById(R.id.ln1);
        db = findViewById(R.id.da1);
        ad = findViewById(R.id.ad);
        bg = findViewById(R.id.bg1);
        em = findViewById(R.id.em);
        pn = findViewById(R.id.pn1);
        gn = findViewById(R.id.gn);
//---------------------------------------------------calender -------------------
        Calendar calender = Calendar.getInstance();
        final int year = calender.get(Calendar.YEAR);
        final int month = calender.get(Calendar.MONTH);
        final int day = calender.get(Calendar.DAY_OF_MONTH);

        db.setOnClickListener(v -> {

            DatePickerDialog dpd = new DatePickerDialog(CusReg.this, android.R.style.Theme_Holo_Light_Dialog,setListener,year,month,day);
            dpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dpd.show();
        });


        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
               month = month +1;
               String date = dayOfMonth+"/"+month+"/"+year ;
               db.setText(date);


            }
        };
//---------------------------------------------------------------------------
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gn.setAdapter(adapter);
        gn.setOnItemSelectedListener(this);
//----------------------------------------------------------------------


        //Regirstration
        reg.setOnClickListener(v -> {

            if(fn.getText().toString().equals("")
                    || ln.getText().toString().equals("") || Gender.equals("")
                    || db.getText().toString().equals("") || pn.getText().toString().equals("")
                    || em.getText().toString().equals("") || ad.getText().toString().equals("")
                    || bg.getText().toString().equals("")) {

                Toast.makeText(this, "Please fill up the form", Toast.LENGTH_SHORT).show();

            }

            else {


               //-----------------------------code for registration-------------------

                try {


                    Boolean checkcustomer = DB.checkcustomer(pn.getText().toString());
                    if (checkcustomer == false) {

                        Boolean insert = DB.insertCustomers(
                                fn.getText().toString()
                                , ln.getText().toString()
                                , Gender
                                , db.getText().toString()
                                , pn.getText().toString()
                                , em.getText().toString()
                                , ad.getText().toString()
                                , bg.getText().toString());


                        if (insert == true) {
                            Toast.makeText(CusReg.this, "Registration successfull", Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(CusReg.this, "Registration error", Toast.LENGTH_SHORT).show();
                        }


                    } //end of checkcus

                    else {
                        Toast.makeText(CusReg.this, "Customer already exists please use different phone number", Toast.LENGTH_SHORT).show();
                    } // end of else


                }//tryyyyyyyy

                catch (Exception e) {

                    Toast.makeText(CusReg.this, "Error : "+e+" ", Toast.LENGTH_SHORT).show();
                }

            }

        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Gender = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}