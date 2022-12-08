package com.example.ju_kassenapp;
import android.view.View;
import android.widget.Button;

public class Article {
    private double price;
    private String name;
    private boolean visible;
    private boolean haveDeposit;
    private Button button;
    private int count;
    private static int numberOfDeposit = 0;
    private static double Deposit = 0.0; // Pfandbetrag

    public Article(Button _btn, String _name, double _price, boolean _haveDeposit, boolean _visible){
        button = _btn;
        name = _name;
        price = _price;
        haveDeposit = _haveDeposit;
        visible = _visible;
        count = 0;
        if (visible)
            button.setVisibility(View.VISIBLE);
        else
            button.setVisibility(View.INVISIBLE);
        button.setText(  String.format("%s\n%.2f€", name, price));
    }

    public int getButtonId() {
        return button.getId();
    }
    public void addOne(){
        count++;
        if (haveDeposit) {
            numberOfDeposit++;
        }
    }

    public static void addDeposit(){
        numberOfDeposit++;
    }

    public static void removeDeposit(){
        numberOfDeposit--;
    }

    public static void clearDeposit(){
        numberOfDeposit = 0;
    }

    public void clearCount(){
        count = 0;
    }

    public static int getNumberOfDeposit(){
        return numberOfDeposit;
    }

    public String toString(){
        String str = count + " " + name;
        return str;
    }

    public static String numberOfDepositToString(){
        return numberOfDeposit + " Pfand";
    }

    public double getSum(){
        return count * price;
    }

    public static double getDepositSum(){
        return numberOfDeposit * Deposit;
    }

    public static void setDeposit(double deposit){
        if (Deposit <= 0) Deposit = deposit;
    }
}

