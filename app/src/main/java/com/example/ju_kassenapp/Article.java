package com.example.ju_kassenapp;
import android.widget.Button;

public class Article {
    private double price;
    private String name;
    private boolean visible;
    private double Deposit; // Pfandbetrag
    private Button button;

    public Article(Button _btn, String _name, double _price, double _deposit, boolean _visible){
        button = _btn;
        name = _name;
        price = _price;
        Deposit = _deposit;
        visible = _visible;
    }

    public Button getButton() {
        return button;
    }

}

