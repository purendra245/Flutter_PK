package com.example.myapplication.interfaces;

public class CokeImplProduct implements Product{
    @Override
    public String getProductName() {
        return "Coke";
    }

    @Override
    public int getProductPrice() {
        return 20;
    }


}
