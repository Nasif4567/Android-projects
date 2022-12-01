package com.example.trustbooddonation;

import android.widget.EditText;

public class CustomersCons {
    String fn;
    String ln;
    String db;
    String gn;
    String pn;
    String em;
    String ad;
    String bg ;

    public CustomersCons(String fn, String ln, String gn, String db, String pn, String em, String ad, String bg) {
        this.fn = fn;
        this.ln = ln;
        this.db = db;
        this.gn = gn;
        this.pn = pn;
        this.em = em;
        this.ad = ad;
        this.bg = bg;
    }

    public CustomersCons(){

    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getGn() {
        return gn;
    }

    public void setGn(String gn) {
        this.gn = gn;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String toString() {
        return pn;
    }


    public String getEm() {
        return em;
    }

    public void setEm(String em) {
        this.em = em;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }
}
