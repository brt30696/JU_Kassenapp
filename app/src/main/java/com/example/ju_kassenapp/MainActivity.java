package com.example.ju_kassenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtSum, txtReturn, txtCash, txtResult1, txtResult2;
    private Button btnNum1, btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7;
    private Button btnNum8, btnNum9, btnNum0, btnNumComma;
    private Button btnClear, btnPfandPlus, btnPfandMinus, btn1, btn2, btn3, btn4, btn5, btn6;
    private String keyboardString = "";
    private final ArrayList<Article> articles = new ArrayList<Article>(6);

    private double cash = 0.0;
    private double returnMoney = 0.0;
    private double sum = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSum =    findViewById(R.id.txt_sum);
        txtReturn = findViewById(R.id.txt_return);
        txtCash =   findViewById(R.id.txt_cash);
        txtResult1 = findViewById(R.id.txt_result1);
        txtResult2 = findViewById(R.id.txt_result2);

        btnNum1 = assignId(R.id.btn_number1);
        btnNum2 = assignId(R.id.btn_number2);
        btnNum3 = assignId(R.id.btn_number3);
        btnNum4 = assignId(R.id.btn_number4);
        btnNum5 = assignId(R.id.btn_number5);
        btnNum6 = assignId(R.id.btn_number6);
        btnNum7 = assignId(R.id.btn_number7);
        btnNum8 = assignId(R.id.btn_number8);
        btnNum9 = assignId(R.id.btn_number9);
        btnNum0 = assignId(R.id.btn_number0);
        btnNumComma = assignId(R.id.btn_Comma);

        btnClear = assignId(R.id.btn_delete);
        btnPfandPlus = assignId(R.id.btn_PfandPlus);
        btnPfandMinus = assignId(R.id.btn_PfandMinus);
        btn1 = assignId(R.id.btn_Article1);
        btn2 = assignId(R.id.btn_Article2);
        btn3 = assignId(R.id.btn_Article3);
        btn4 = assignId(R.id.btn_Article4);
        btn5 = assignId(R.id.btn_Article5);
        btn6 = assignId(R.id.btn_Article6);

        Article.setDeposit(5.0);
        articles.add(0, new Article(btn1, "Feuerzangenbowle", 4.0, true,true));
        articles.add(1, new Article(btn2, "Kinderpunsch", 2.0, true,true));
        articles.add(2, new Article(btn4, "Maroni", 3.0, false, true));
//        articles.add(3, new Article(btn5, "Mandeln", 2.5, false, true));

        drawResult();
    }

    private Button assignId(int id){
        Button btn__;
        btn__ = findViewById(id);
        btn__.setOnClickListener(this);
        return btn__;
    }

    private void deleteOnClickListener(Button btn){
        if (btn.getId() == R.id.btn_delete) {
            /* clear article data */
            Article.clearDeposit();
            for (int i = 0; i < articles.toArray().length; i++){
                articles.get(i).clearCount();
            }
            /* Clear calculation data */
            returnMoney = 0.0;
            sum = 0.0;
            cash = 0.0;
            /* clear txtBoxes */
            drawResult();
            txtCash.setText(getString(R.string.gegeben) + "0.0");
            keyboardString = "";
        }
    }
    private void depositOnClickListener(Button btn){
        if (btn.getId() == R.id.btn_PfandMinus){
            Article.removeDeposit();
        }
        else if (btn.getId() == R.id.btn_PfandPlus){
            Article.addDeposit();
        }else {
            return;
        }
        calculate();
        drawResult();
    }
    private void articleOnClickListener(Button btn, ArrayList<Article> articles){
        for (int i = 0; i < articles.toArray().length; i++){
            if (btn.getId() == articles.get(i).getButtonId()){
                articles.get(i).addOne();
                calculate();
                drawResult();
                return;
            }
        }

    }
    private boolean keyboardOnClickListener(Button btn){
        int btnId = btn.getId();
        if (!(btnId == R.id.btn_number0 || btnId == R.id.btn_number1 || btnId == R.id.btn_number2 || btnId == R.id.btn_number3 ||
                btnId == R.id.btn_number4 || btnId == R.id.btn_number5 || btnId == R.id.btn_number6 || btnId == R.id.btn_number7 ||
                btnId == R.id.btn_number8 || btnId == R.id.btn_number9 || btnId == R.id.btn_Comma)) {
            return false;
        }

        if (btn.getText() == "." && !keyboardString.contains(".") && keyboardString.isEmpty()) {
            keyboardString = "0.";
            txtCash.setText(getString(R.string.gegeben) + keyboardString);
            cash = 0.0;
            return true;
        }
        if (btn.getText() == "." && keyboardString.contains(".") && !keyboardString.isEmpty()){
            return false;
        }
        keyboardString += btn.getText();
        cash = Double.parseDouble(keyboardString);
        txtCash.setText(getString(R.string.gegeben) + keyboardString);
        if (sum > 0.0) {
            calculate();
            drawResult();
        }
        return true;
    }

    private void drawResult(){
        /* draw article list */
        String resultString = "";
        for (int i = 0; i < articles.size(); i++){
            resultString = resultString + articles.get(i).toString() + "\n";
        }
        txtResult1.setText(resultString);
        /* draw deposit list */
        txtResult2.setText(Article.numberOfDepositToString());
        /* draw sum & return money */
        txtSum.setText( String.format("Summe: %.2f€", sum));
        txtReturn.setText(String.format("Rückgeld: %.2f", returnMoney));
    }
    private void calculate(){
        /* sum over all article */
        sum = 0.0;
        for (int i = 0; i < articles.size(); i++){
            sum += articles.get(i).getSum();
        }
        /* add deposit Sum */
        sum += Article.getDepositSum();
        /* calculate return money */
        if (cash > 0.0){
            returnMoney = cash - sum;
        }
    }
    @Override
    public void onClick(View view) {
        Button btn = (Button) view;

        keyboardOnClickListener(btn);
        deleteOnClickListener(btn);
        depositOnClickListener(btn);
        articleOnClickListener(btn, articles);
    }


}
