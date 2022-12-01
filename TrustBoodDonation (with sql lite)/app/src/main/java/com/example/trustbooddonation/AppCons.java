package com.example.trustbooddonation;

public class AppCons {

    String fna;
    String lna;
    String pna;
    String bga;
    String daa;

    public AppCons(String fna, String lna, String pna, String bga, String daa) {
        this.fna = fna;
        this.lna = lna;
        this.pna = pna;
        this.bga = bga;
        this.daa = daa;
    }

    public String getFna() {
        return fna;
    }

    public void setFna(String fna) {
        this.fna = fna;
    }

    public String getLna() {
        return lna;
    }

    public String toString(){
        return pna;
    }

    public void setLna(String lna) {
        this.lna = lna;
    }

    public String getPna() {
        return pna;
    }

    public void setPna(String pna) {
        this.pna = pna;
    }

    public String getBga() {
        return bga;
    }

    public void setBga(String bga) {
        this.bga = bga;
    }

    public String getDaa() {
        return daa;
    }

    public void setDaa(String daa) {
        this.daa = daa;
    }

    public AppCons(){

    }
}
