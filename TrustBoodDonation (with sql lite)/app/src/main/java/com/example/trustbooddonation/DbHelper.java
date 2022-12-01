package com.example.trustbooddonation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Donor.db";

    public DbHelper(Context context) {
        super(context,"Donor.db" , null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase Mydb) {
         Mydb.execSQL("create Table users(username TEXT,password TEXT)");
         Mydb.execSQL("create Table Customers(FirstName TEXT,LastName TEXT,Gender TEXT,Dob TEXT,Phone TEXT,Email TEXT,Address TEXT,BloodGroup TEXT)");
         Mydb.execSQL("create Table Appoinment(FirstName TEXT,LastName TEXT,Phone TEXT,BloodGroup TEXT,Date TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase Mydb, int oldVersion, int newVersion) {

        Mydb.execSQL("drop Table if exists users");
        Mydb.execSQL("drop Table if exists Customers");
        Mydb.execSQL("drop Table if exists Appoinment");
        onCreate(Mydb);

    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues() ;
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = MyDB.insert("users",null,contentValues);
        if(result == -1) {
            return false;
        }

        else {
            return true;
        }
    }


    //---------------------------------------------------------
    public Boolean insertCustomers(String fn, String ln,
                                   String gn , String db, String pn,
                                   String em, String ad, String bg){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues() ;
        contentValues.put("FirstName",fn);
        contentValues.put("LastName",ln);
        contentValues.put("Gender",gn);
        contentValues.put("Dob",db);
        contentValues.put("Phone",pn);
        contentValues.put("Email",em);
        contentValues.put("Address",ad);
        contentValues.put("BloodGroup",bg);
        long result = MyDB.insert("Customers",null,contentValues);
        if(result == -1) {
            return false;
        }

        else {
            return true;
        }
    }
//-------------------------------------------------------------------------------


    public boolean checkuser(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor curson = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});

        if (curson.getCount() > 0) {
            return true;
        }

        else {
            return false;
        }
    }

//----------------------------------------------------------------
    public boolean checkcustomer(String pn){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor curson = MyDB.rawQuery("Select * from Customers where Phone = ?", new String[] {pn});

        if (curson.getCount() > 0) {
            return true;
        }

        else {
            return false;
        }
    }
//----------------------------------------------------------------------


    public void searchcustomer(String pn, EditText fn ,EditText ln,EditText bg,Context con){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor curson = MyDB.rawQuery("Select * from Customers where Phone = ?", new String[] {pn});
        if (curson.getCount() > 0) {

            if(curson != null && curson.moveToFirst()) {
                fn.setText(curson.getString(0));
                ln.setText(curson.getString(1));
                bg.setText(curson.getString(7));

            }
        }

        else {
            Toast.makeText(con, "Record not found", Toast.LENGTH_SHORT).show();
        }
    }

    //-----------------------------------------------------------------------------


    public ArrayList<CustomersCons> ViewCustomer() {
        ArrayList<CustomersCons> arrayList = new ArrayList<CustomersCons>();
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor curson = MyDB.rawQuery("Select * from Customers", new String[] {});
        if (curson.moveToFirst()) {
            do {
                // add to list view
                CustomersCons cus = new CustomersCons(curson.getString(0), curson.getString(1), curson.getString(2), curson.getString(3), curson.getString(4), curson.getString(5), curson.getString(6), curson.getString(7));
                arrayList.add(cus);

            } while (curson.moveToNext());
        }

        curson.close();

        return arrayList;
    }





        //------------------------------------------------------------------------------


        public boolean checkuserpass (String username, String password){
            SQLiteDatabase MyDB = this.getWritableDatabase();
            Cursor curson = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});

            if (curson.getCount() > 0) {
                return true;
            } else {
                return false;
            }
        }

//-----------------------------------------------------------------------------
    public Boolean UpdateCustomer(String fn, String ln,
                                   String gn , String db, String pn,
                                   String em, String ad, String bg, String tpn){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues() ;
        contentValues.put("FirstName",fn);
        contentValues.put("LastName",ln);
        contentValues.put("Gender",gn);
        contentValues.put("Dob",db);
        contentValues.put("Phone",pn);
        contentValues.put("Email",em);
        contentValues.put("Address",ad);
        contentValues.put("BloodGroup",bg);
        long result = MyDB.update("Customers",contentValues,"Phone=?", new String[]{tpn});
        if(result == -1) {
            return false;
        }

        else {
            return true;
        }
    }
    //--------------------------------------------------------------------------------------


    public Boolean DeleteCustomer( String pn){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues() ;
        long result = MyDB.delete("Customers","Phone=?", new String[]{pn});
        if(result == -1) {
            return false;
        }

        else {
            return true;
        }
    }


    //-------------------------------------------------------------------------
    public Boolean insertAppoinment(String fn, String ln, String pn , String bg , String dd){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues() ;
        contentValues.put("FirstName",fn);
        contentValues.put("LastName",ln);
        contentValues.put("Phone",pn);
        contentValues.put("BloodGroup",bg);
        contentValues.put("Date",dd);
        long result = MyDB.insert("Appoinment",null,contentValues);
        if(result == -1) {
            return false;
        }

        else {
            return true;
        }
    }

    //-------------------------------------------------------------------------
    public boolean checkapp(String pn){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor curson = MyDB.rawQuery("Select * from Appoinment where Phone = ?", new String[] {pn});

        if (curson.getCount() > 0) {
            return true;
        }

        else {
            return false;
        }
    }
    //------------------------------------------------------------------------------

    public ArrayList<AppCons> ViewApp() {
        ArrayList<AppCons> arrayList = new ArrayList<AppCons>();
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor curson = MyDB.rawQuery("Select * from Appoinment", new String[] {});
        if (curson.moveToFirst()) {
            do {
                // add to list view
                AppCons app = new AppCons(curson.getString(0), curson.getString(1), curson.getString(2), curson.getString(3), curson.getString(4));
                arrayList.add(app);

            } while (curson.moveToNext());
        }

        curson.close();

        return arrayList;
    }



    //-----------------------------------------------------------------

    public Boolean UpdateApp(String fn, String ln, String pn,
                                  String bg, String db){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues() ;
        contentValues.put("FirstName",fn);
        contentValues.put("LastName",ln);
        contentValues.put("Phone",pn);
        contentValues.put("BloodGroup",bg);
        contentValues.put("Date",db);
        long result = MyDB.update("Appoinment",contentValues,"Phone=?", new String[]{pn});
        if(result == -1) {
            return false;
        }

        else {
            return true;
        }
    }
    //--------------------------------------------------------------------------------------


    public Boolean DeleteApp( String pn){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues() ;
        long result = MyDB.delete("Appoinment","Phone=?", new String[]{pn});
        if(result == -1) {
            return false;
        }

        else {
            return true;
        }
    }

















}
