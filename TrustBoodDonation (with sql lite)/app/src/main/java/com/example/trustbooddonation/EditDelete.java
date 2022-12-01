package com.example.trustbooddonation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDelete extends AppCompatActivity {


    private EditText fn,ln,db,ad,bg,em,pn,gn;
    private Button del,updt;
    private DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete);
        del = findViewById(R.id.Del);
        updt = findViewById(R.id.Update);
        DB = new DbHelper(this);


        fn = findViewById(R.id.fn2);
        ln = findViewById(R.id.ln2);
        db = findViewById(R.id.da2);
        ad = findViewById(R.id.ad2);
        bg = findViewById(R.id.bg2);
        em = findViewById(R.id.em2);
        pn = findViewById(R.id.pn2);
        gn = findViewById(R.id.gn2);


        Bundle extras = getIntent().getExtras();
        if(extras == null) {

        } else {
            fn.setText(extras.getString("Fn"));
            ln.setText(extras.getString("Ln"));
            db.setText(extras.getString("Db"));
            ad.setText(extras.getString("Ad"));
            bg.setText(extras.getString("Bg"));
            em.setText(extras.getString("Em"));
            pn.setText(extras.getString("Pn"));
            gn.setText(extras.getString("Gn"));
        }


        updt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (fn.getText().toString().equals("")
                        || ln.getText().toString().equals("")
                        || pn.getText().toString().equals("")
                        || em.getText().toString().equals("")
                        || ad.getText().toString().equals("")
                        || bg.getText().toString().equals("")) {

                    Toast.makeText(EditDelete.this, "The form cannot be empty", Toast.LENGTH_SHORT).show();

                } else {


                    AlertDialog.Builder alertb = new AlertDialog.Builder(EditDelete.this);

                    alertb.setMessage("Are you sure you want to update customer details")
                            .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    String tpn = pn.getText().toString();

                                    //----------
                                    Boolean update = DB.UpdateCustomer(fn.getText().toString(), ln.getText().toString(), gn.getText().toString(), db.getText().toString(), pn.getText().toString(), em.getText().toString(), ad.getText().toString(), bg.getText().toString(), tpn);

                                    if (update == true) {
                                        Toast.makeText(EditDelete.this, "Updated customer details", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(EditDelete.this, ManageCustomer.class));
                                    } else {
                                        Toast.makeText(EditDelete.this, "Error in updating", Toast.LENGTH_SHORT).show();
                                    }

                                    //---------------------------------


                                }
                            })

                            .setNegativeButton("No", null);


                    AlertDialog al = alertb.create();
                    al.show();


                }
            }
        });
        //------------------------------------------------------------------------



        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fn.getText().toString().equals("")
                        || ln.getText().toString().equals("")
                        || pn.getText().toString().equals("")
                        || em.getText().toString().equals("")
                        || ad.getText().toString().equals("")
                        || bg.getText().toString().equals("")) {

                    Toast.makeText(EditDelete.this, "The form cannot be empty", Toast.LENGTH_SHORT).show();

                } else {


                    AlertDialog.Builder alertb = new AlertDialog.Builder(EditDelete.this);

                    alertb.setMessage("Are you sure you want to Delete customer details whosee phone number is " + pn.getText().toString() + " ")
                            .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    //----------
                                    Boolean delete = DB.DeleteCustomer(pn.getText().toString());

                                    if (delete == true) {
                                        Toast.makeText(EditDelete.this, "Customer delted", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(EditDelete.this, ManageCustomer.class));
                                    } else {
                                        Toast.makeText(EditDelete.this, "Error in Deleting", Toast.LENGTH_SHORT).show();
                                    }

                                    //---------------------------------


                                }
                            })

                            .setNegativeButton("No", null);


                    AlertDialog al = alertb.create();
                    al.show();
                    //--------------------------------------------------

                }













            }
        });
        //------------------------------------------------------------------------




    }
}