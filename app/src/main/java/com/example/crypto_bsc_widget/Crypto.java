package com.example.crypto_bsc_widget;


import android.graphics.Bitmap;


public class Crypto {

    private String symbol;
    private String contractAddress;

    private double price;
    private Bitmap logo;
    private int logoCoinId;
    private String percent;

    public Crypto(String contractAddress, double price, String symbol, Bitmap logo, String percent) {
        this.contractAddress = contractAddress;
        this.price = price;
        this.symbol = symbol;
        this.logo = logo;
        this.percent = percent;
    }

    public Crypto(String symbol) {
        this.symbol = symbol;
    }

    public void setLogo(Bitmap logo) {
        this.logo = logo;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getPercent() {
        return percent;
    }

    public Bitmap getLogo() {
        return logo;
    }

    public double getPrice() {
        return price;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public String getSymbol() {
        return symbol;
    }

    public String toString() {
        return symbol;
    }

    public int getLogoCoinId() {
        return logoCoinId;
    }

    public void setLogoCoinId(int logoCoinId) {
        this.logoCoinId = logoCoinId;
    }

}
