package com.example.ju_kassenapp;

public class Drink extends Article {
    private double Deposit; // Pfandbetrag

    public Drink(double deposit) {
        Deposit = deposit;
    }

    public double getDeposit() {
        return Deposit;
    }

    public void setDeposit(double deposit) {
        Deposit = deposit;
    }
}
