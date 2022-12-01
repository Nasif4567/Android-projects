package com.example.trustbooddonation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class ManageCustomer extends AppCompatActivity {
    ListView lv;
    SearchView search;
    CustomerListAdapter adapter;
    DbHelper DB;
    ArrayList<CustomersCons> CusList;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ManageCustomer.this,Dashboard.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB = new DbHelper(this);
        setContentView(R.layout.activity_manage_customer);
        lv = findViewById(R.id.listview);
        search = findViewById(R.id.searchlist);
        CustomersCons cus = null;
        CusList = new ArrayList<CustomersCons>();


        //

        try {

                CusList = DB.ViewCustomer();
                //Toast.makeText(getApplicationContext(), CusList.toString(), Toast.LENGTH_SHORT).show();
                adapter = new CustomerListAdapter(this, CusList);
                lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();





        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error"+e+"", Toast.LENGTH_SHORT).show();
        }




        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ManageCustomer.this.adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ManageCustomer.this.adapter.getFilter().filter(newText);
                return false;
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ManageCustomer.this, EditDelete.class);


                CustomersCons cus = (CustomersCons) lv.getItemAtPosition(position);

                i.putExtra("Fn", cus.getFn());
                i.putExtra("Ln", cus.getLn());
                i.putExtra("Gn", cus.getGn());
                i.putExtra("Db", cus.getDb());
                i.putExtra("Pn", cus.getPn());
                i.putExtra("Em", cus.getEm());
                i.putExtra("Ad", cus.getAd());
                i.putExtra("Bg", cus.getBg());



                startActivity(i);




            }


        });











    }











}