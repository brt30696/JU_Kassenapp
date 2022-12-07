package com.example.ju_kassenapp;
import android.widget.Button;

public class Article {
    private double price;
    private String name;
    private boolean visible;
    private double Deposit; // Pfandbetrag
    private Button button;
    private int count;
    private static int numberOfDeposit = 0;

    public Article(Button _btn, String _name, double _price, double _deposit, boolean _visible){
        button = _btn;
        name = _name;
        price = _price;
        Deposit = _deposit;
        visible = _visible;
        count = 0;
    }

    public Button getButton() {
        return button;
    }
    public void addOne(){
        count++;
        if (Deposit != 0.0) {
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
}

