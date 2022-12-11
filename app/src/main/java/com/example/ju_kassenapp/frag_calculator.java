package com.example.ju_kassenapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frag_calculator#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frag_calculator extends Fragment implements View.OnClickListener, View.OnLongClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public frag_calculator() {
        // Required empty public constructor
    }

    private TextView txtSum, txtReturn, txtCash, txtResult1, txtResult2;
    private Button btnClear;
    private Button btnDelete, btnPfandPlus, btnPfandMinus, btn1, btn2, btn3, btn4, btn5, btn6;
    private String keyboardString = "";
    private final ArrayList<Article> articles = new ArrayList<Article>(6);

    // Components for the calculation
    private double cash = 0.0;
    private double returnMoney = 0.0;
    private double sum = 0.0;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frag_calculator.
     */
    // TODO: Rename and change types and number of parameters
    public static frag_calculator newInstance(String param1, String param2) {
        frag_calculator fragment = new frag_calculator();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        txtSum     = getView().findViewById(R.id.txt_sum);
        txtReturn  = getView().findViewById(R.id.txt_return);
        txtCash    = getView().findViewById(R.id.txt_cash);
        txtResult1 = getView().findViewById(R.id.txt_result1);
        txtResult2 = getView().findViewById(R.id.txt_result2);

        assignId(R.id.btn_number1);
        assignId(R.id.btn_number2);
        assignId(R.id.btn_number3);
        assignId(R.id.btn_number4);
        assignId(R.id.btn_number5);
        assignId(R.id.btn_number6);
        assignId(R.id.btn_number7);
        assignId(R.id.btn_number8);
        assignId(R.id.btn_number9);
        assignId(R.id.btn_number0);
        assignId(R.id.btn_Comma);

        btnClear      = assignId(R.id.btn_clear);
        btnClear.setLongClickable(true);
        btnClear.setOnLongClickListener(this);

        btnDelete     = assignId(R.id.btn_delete);
        btnPfandPlus  = assignId(R.id.btn_PfandPlus);
        btnPfandMinus = assignId(R.id.btn_PfandMinus);
        btn1 = assignId(R.id.btn_Article1);
        btn2 = assignId(R.id.btn_Article2);
        btn3 = assignId(R.id.btn_Article3);
        btn4 = assignId(R.id.btn_Article4);
        btn5 = assignId(R.id.btn_Article5);
        btn6 = assignId(R.id.btn_Article6);

        // define Articles
        Article.setDeposit(5.0);
        articles.add(0, new Article(btn1, "Feuerzangenbowle", 4.0, true,true));
        articles.add(1, new Article(btn2, "Kinderpunsch", 2.0, true,true));
        articles.add(2, new Article(btn4, "Maroni", 3.0, false, true));
//        articles.add(3, new Article(btn5, "Mandeln", 2.5, false, true));
        btnPfandMinus.setText(String.format("Pfand Minus\n%.2f€", -Article.getDeposit()));
        btnPfandPlus.setText(String.format("Pfand Plus\n%.2f€", Article.getDeposit()));

        drawResult();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_calculator, container, false);
    }

    @Override
    public boolean onLongClick(View view) {
        // for btnClear to clear the txtCash View complete
        keyboardString = "";
        manageData();
        return false;
    }

    private boolean deleteOnClickListener(Button btn){
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
            txtCash.setText(getString(R.string.gegeben) + " 0.00 €");
            keyboardString = "";
            return true;
        }
        return false;
    }
    private boolean depositOnClickListener(Button btn){
        if (btn.getId() == R.id.btn_PfandMinus){
            Article.removeDeposit();
        }
        else if (btn.getId() == R.id.btn_PfandPlus){
            Article.addDeposit();
        }else {
            return false;
        }
        calculate();
        drawResult();
        return true;
    }
    private boolean articleOnClickListener(Button btn, ArrayList<Article> articles){
        for (int i = 0; i < articles.toArray().length; i++){
            if (btn.getId() == articles.get(i).getButtonId()){
                articles.get(i).addOne();
                calculate();
                drawResult();
                return true;
            }
        }
        return false;
    }
    private boolean keyboardOnClickListener(Button btn) {
        int btnId = btn.getId();
        if (!(btnId == R.id.btn_number0 || btnId == R.id.btn_number1 || btnId == R.id.btn_number2 || btnId == R.id.btn_number3 ||
                btnId == R.id.btn_number4 || btnId == R.id.btn_number5 || btnId == R.id.btn_number6 || btnId == R.id.btn_number7 ||
                btnId == R.id.btn_number8 || btnId == R.id.btn_number9 || btnId == R.id.btn_Comma || btnId == R.id.btn_clear)) {
            return false;
        }
        String btnText = btn.getText().toString();
        switch (btnId) {
            case (R.id.btn_clear): {
                if ((keyboardString != null) && (keyboardString.length() > 0)) {
                    keyboardString = keyboardString.substring(0, keyboardString.length() - 1);
                }
                break;
            }
            case (R.id.btn_Comma): {
                if (!keyboardString.contains(".")) {
                    if (keyboardString.length() == 0) {
                        keyboardString = "0.";
                        txtCash.setText("gegeben: " + keyboardString);
                        cash = 0.0;
                        break;
                    }
                } else {
                    if (!keyboardString.isEmpty()) {
                        return false;
                    }
                }
            }
            default: {
                keyboardString += btn.getText();
                break;
            }
        }
        manageData();
        return true;
    }

    private void drawResult(){
        /* draw article list */
        String resultString1 = "";
        StringBuilder resultString2 = new StringBuilder();
        for (int i = 0; i < articles.size(); i++){
            resultString1 = resultString1 + articles.get(i).toString() + ":\n";
            resultString2.append(String.format("%.2f €\n", articles.get(i).getSum()));
        }
        resultString1 += Article.numberOfDepositToString() + ":";
        resultString2.append(String.format("%.2f €", Article.getDepositSum()));
        txtResult1.setText(resultString1);
        /* draw deposit list */
        txtResult2.setText(resultString2);

        /* draw sum & return money */
        txtSum.setText( String.format("%.2f €", sum));
        txtReturn.setText(String.format("%.2f €", returnMoney));
        // change color depending of the signe of returnMoney
        if (returnMoney < 0.0){
            txtReturn.setTextColor(ContextCompat.getColor(getActivity(), R.color.error));
        }else if (returnMoney > 0.0){
            txtReturn.setTextColor(ContextCompat.getColor(getActivity(), R.color.good));
        }else
            txtReturn.setTextColor(txtSum.getTextColors());

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
        else {
            returnMoney = 0.0;
        }
    }
    private void manageData(){
        if (keyboardString.length() == 0){
            cash = 0.0;
        }
        else {
            cash = Double.parseDouble(keyboardString);
        }
        txtCash.setText("gegeben: " + keyboardString + "€");
        if (sum > 0.0) {
            calculate();
            drawResult();
        }
    }
    private Button assignId(int id){
        Button btn__;
        btn__ = getView().findViewById(id);
        btn__.setOnClickListener(this);
        return btn__;
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;

        if (deleteOnClickListener(btn)) return;
        if (depositOnClickListener(btn)) return;
        if (articleOnClickListener(btn, articles)) return;
        if (keyboardOnClickListener(btn)) return;

    }
}