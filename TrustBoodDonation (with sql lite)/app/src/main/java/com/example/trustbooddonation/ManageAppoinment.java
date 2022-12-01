package com.example.trustbooddonation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ManageAppoinment extends AppCompatActivity {

    private ListView lv1;
    private SearchView search1;
    private AppListAdapter adapter1;
    private DbHelper DB1;
    private ArrayList<AppCons> AppList1;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ManageAppoinment.this,Dashboard.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB1 = new DbHelper(this);
        setContentView(R.layout.activity_manage_appoinment);
        lv1 = findViewById(R.id.listview1);
        search1 = findViewById(R.id.searchlist1);
        AppList1 = new ArrayList<AppCons>();


        //

        try {

            AppList1 = DB1.ViewApp();
            //Toast.makeText(getApplicationContext(), CusList.toString(), Toast.LENGTH_SHORT).show();
            adapter1 = new AppListAdapter(this, AppList1);
            lv1.setAdapter(adapter1);
            adapter1.notifyDataSetChanged();





        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error"+e+"", Toast.LENGTH_SHORT).show();
        }




        search1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ManageAppoinment.this.adapter1.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ManageAppoinment.this.adapter1.getFilter().filter(newText);
                return false;
            }
        });




        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ManageAppoinment.this, EditDelete1.class);


                AppCons app = (AppCons) lv1.getItemAtPosition(position);

                i.putExtra("Fna", app.getFna());
                i.putExtra("Lna", app.getLna());
                i.putExtra("Dba", app.getDaa());
                i.putExtra("Pna", app.getPna());
                i.putExtra("Bga", app.getBga());



                startActivity(i);

            }


        });











    }
}