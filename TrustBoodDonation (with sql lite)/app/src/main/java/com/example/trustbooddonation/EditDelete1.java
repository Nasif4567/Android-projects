package com.example.trustbooddonation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class EditDelete1 extends AppCompatActivity {

    private EditText fn3,ln3,bg3,pn3,da3;
    private Button del3,updt3;
    private DbHelper DB;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete1);


        fn3 = findViewById(R.id.fn3);
        ln3 = findViewById(R.id.ln3);
        da3 = findViewById(R.id.da3);
        bg3 = findViewById(R.id.bg3);
        pn3 = findViewById(R.id.pn3);
        updt3 = findViewById(R.id.Update3);
        del3 = findViewById(R.id.Del3);
        DB = new DbHelper(this);

        //---------------------------------------------------calender -------------------
        Calendar calender = Calendar.getInstance();
        final int year = calender.get(Calendar.YEAR);
        final int month = calender.get(Calendar.MONTH);
        final int day = calender.get(Calendar.DAY_OF_MONTH);

        da3.setOnClickListener(v -> {

            DatePickerDialog dpd = new DatePickerDialog(EditDelete1.this, android.R.style.Theme_Holo_Light_Dialog,setListener,year,month,day);
            dpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dpd.show();

        });


        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                String date = dayOfMonth+"/"+month+"/"+year ;
                da3.setText(date);


            }
        };
//---------------------------------------------------------------------------







        Bundle extras = getIntent().getExtras();
        if(extras == null) {

        } else {
            fn3.setText(extras.getString("Fna"));
            ln3.setText(extras.getString("Lna"));
            da3.setText(extras.getString("Dba"));
            bg3.setText(extras.getString("Bga"));
            pn3.setText(extras.getString("Pna"));
        }


        updt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                AlertDialog.Builder alertb = new AlertDialog.Builder(EditDelete1.this);

                alertb.setMessage("Are you sure you want to update appoinment date")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //----------
                                try {


                                    Boolean update = DB.UpdateApp(fn3.getText().toString(), ln3.getText().toString(), pn3.getText().toString(), bg3.getText().toString(), da3.getText().toString());

                                    if (update == true) {
                                        Toast.makeText(EditDelete1.this, "Updated appoinment date", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(EditDelete1.this, ManageAppoinment.class));
                                    } else {
                                        Toast.makeText(EditDelete1.this, "Error in updating", Toast.LENGTH_SHORT).show();
                                    }

                                    //---------------------------------

                                }

                                catch (Exception e) {
                                    Toast.makeText(EditDelete1.this, "Error in updating"+e+"", Toast.LENGTH_SHORT).show();
                                }


                            }
                        })

                        .setNegativeButton("No", null );


                AlertDialog al = alertb.create();
                al.show();


            }
        });
        //------------------------------------------------------------------------



        del3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                AlertDialog.Builder alertb = new AlertDialog.Builder(EditDelete1.this);

                alertb.setMessage("Are you sure you want to Delete the appoinment ")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //----------
                                Boolean delete = DB.DeleteApp(pn3.getText().toString());

                                if(delete == true ) {
                                    Toast.makeText(EditDelete1.this, "Appoinment delted", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(EditDelete1.this,ManageAppoinment.class));
                                }

                                else {
                                    Toast.makeText(EditDelete1.this, "Error in Deleting", Toast.LENGTH_SHORT).show();
                                }

                                //---------------------------------


                            }
                        })

                        .setNegativeButton("No", null );


                AlertDialog al = alertb.create();
                al.show();















            }
        });
        //------------------------------------------------------------------------



















    }
}