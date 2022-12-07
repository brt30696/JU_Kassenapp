package com.example.ju_kassenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtSum, txtReturn, txtCash;
    private Button btnNum1, btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7;
    private Button btnNum8, btnNum9, btnNum0, btnNumComma;
    private Button btnClear, btnPfandPlus, btnPfandMinus, btn1, btn2, btn3, btn4, btn5, btn6;
    private ListView lstResults;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSum = findViewById(R.id.txt_sum);
        txtReturn = findViewById(R.id.txt_return);
        txtCash = findViewById(R.id.txt_cash);

        assignId(btnNum1, R.id.btn_number1);
        assignId(btnNum2, R.id.btn_number2);
        assignId(btnNum3, R.id.btn_number3);
        assignId(btnNum4, R.id.btn_number4);
        assignId(btnNum5, R.id.btn_number5);
        assignId(btnNum6, R.id.btn_number6);
        assignId(btnNum7, R.id.btn_number7);
        assignId(btnNum8, R.id.btn_number8);
        assignId(btnNum9, R.id.btn_number9);
        assignId(btnNum0, R.id.btn_number0);
        assignId(btnNumComma, R.id.btn_Comma);

        assignId(btnClear, R.id.btn_delete);
        assignId(btnPfandPlus, R.id.btn_PfandPlus);
        assignId(btnPfandMinus, R.id.btn_PfandMinus);
        assignId(btn1, R.id.btn_Article1);
        assignId(btn2, R.id.btn_Article2);
        assignId(btn3, R.id.btn_Article3);
        assignId(btn4, R.id.btn_Article4);
        assignId(btn5, R.id.btn_Article5);
        assignId(btn6, R.id.btn_Article6);
        lstResults = findViewById(R.id.list_articles);

    }

    private void assignId(Button btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    private String keyboardString = "";
    private boolean readKeyboard(Button btn){
        int btnId = btn.getId();
        if (btnId == R.id.btn_Comma && !keyboardString.contains(btn.getText().toString())){
            if (keyboardString.isEmpty()){
                keyboardString = "0";
            }
            keyboardString = keyboardString + btn.getText().toString();
            return true;
        }
        else if (btnId == R.id.btn_number0 || btnId == R.id.btn_number1 || btnId == R.id.btn_number2 || btnId == R.id.btn_number3 ||
                btnId == R.id.btn_number4 || btnId == R.id.btn_number5 || btnId == R.id.btn_number6 || btnId == R.id.btn_number7 ||
                btnId == R.id.btn_number8 || btnId == R.id.btn_number9) {
            keyboardString = keyboardString + btn.getText().toString();
            return true;
        }
        return false;
    }
    private boolean readDelete(Button btn){
        if (btn.getId() == R.id.btn_delete) {
            keyboardString = "";
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        String btnText = btn.getText().toString();
        int btnId = btn.getId();
        if (readKeyboard(btn)){
            txtCash.setText("gegeben: " + keyboardString);
        }
        else if (readDelete(btn)){
            txtCash.setText("gegeben: " + keyboardString);
        }
    }

}
