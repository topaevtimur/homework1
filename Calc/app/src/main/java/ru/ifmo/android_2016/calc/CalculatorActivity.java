package ru.ifmo.android_2016.calc;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public final class CalculatorActivity extends Activity implements View.OnClickListener {
    Button d0, d1, d2, d3, d4, d5, d6, d7, d8, d9, mul, div, sub, add, clear, eqv;
    TextView ins, result;
    double operand1, operand2;
    int flag;
    CharSequence copy = "";
    double res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        d0 = (Button) findViewById(R.id.d0);
        d1 = (Button) findViewById(R.id.d1);
        d2 = (Button) findViewById(R.id.d2);
        d3 = (Button) findViewById(R.id.d3);
        d4 = (Button) findViewById(R.id.d4);
        d5 = (Button) findViewById(R.id.d5);
        d6 = (Button) findViewById(R.id.d6);
        d7 = (Button) findViewById(R.id.d7);
        d8 = (Button) findViewById(R.id.d8);
        d9 = (Button) findViewById(R.id.d9);
        mul = (Button) findViewById(R.id.mul);
        sub = (Button) findViewById(R.id.sub);
        add = (Button) findViewById(R.id.add);
        div = (Button) findViewById(R.id.div);
        clear = (Button) findViewById(R.id.clear);
        eqv = (Button) findViewById(R.id.eqv);

        ins = (TextView) findViewById(R.id.ins);
        result = (TextView) findViewById(R.id.result);

        d0.setOnClickListener(this);
        d1.setOnClickListener(this);
        d2.setOnClickListener(this);
        d3.setOnClickListener(this);
        d4.setOnClickListener(this);
        d5.setOnClickListener(this);
        d6.setOnClickListener(this);
        d7.setOnClickListener(this);
        d8.setOnClickListener(this);
        d9.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        clear.setOnClickListener(this);
        eqv.setOnClickListener(this);

        operand1 = 0;
        operand2 = 0;
        flag = 0;
        ins.setText(Double.toString(operand1));

    }

    private void ClickNumber(int num) {
        if (flag == 0) {
            if (operand1 < 0) num = -num;
            operand1 = operand1 * 10 + num;
            ins.setText(Double.toString(operand1));
            result.setText(Double.toString(operand1));
        } else {
            operand2 = operand2 * 10 + num;

            switch (flag) {
                case 1:
                    res = operand1 + operand2;
                    break;
                case 2:
                    if (operand1 == 0) {
                        operand1 = -operand2;
                        res = operand1;
                        operand2 = 0;
                        flag = 0;
                        ins.setText(Double.toString(operand1));
                        result.setText(Double.toString(res));
                        return;
                    } else
                        res = operand1 - operand2;
                    break;
                case 3:
                    res = operand1 * operand2;
                    break;
                case 4:
                    res = operand1 / operand2;
                    break;
                default:
                    Toast.makeText(this, "Операция не задана", Toast.LENGTH_LONG);
            }
            if (copy == "") copy = ins.getText();
            ins.setText(copy + Double.toString(operand2));
            result.setText(Double.toString(res));
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.d0:
                ClickNumber(0);
                break;
            case R.id.d1:
                ClickNumber(1);
                break;
            case R.id.d2:
                ClickNumber(2);
                break;
            case R.id.d3:
                ClickNumber(3);
                break;
            case R.id.d4:
                ClickNumber(4);
                break;
            case R.id.d5:
                ClickNumber(5);
                break;
            case R.id.d6:
                ClickNumber(6);
                break;
            case R.id.d7:
                ClickNumber(7);
                break;
            case R.id.d8:
                ClickNumber(8);
                break;
            case R.id.d9:
                ClickNumber(9);
                break;

            case R.id.add:
                if (operand2 != 0) {
                    operand1 = res;
                    operand2 = 0;
                    copy = "";
                }
                flag = 1;
                ins.setText(Double.toString(operand1) + "+");
                break;
            case R.id.sub:
                if (operand2 != 0) {
                    operand1 = res;
                    operand2 = 0;
                    copy = "";
                }
                flag = 2;
                ins.setText(Double.toString(operand1) + "-");

                break;
            case R.id.mul:
                if (operand2 != 0) {
                    operand1 = res;
                    operand2 = 0;
                    copy = "";

                }
                flag = 3;
                ins.setText(Double.toString(operand1) + "*");
                break;
            case R.id.div:
                if (operand2 != 0) {
                    operand1 = res;
                    operand2 = 0;
                    copy = "";

                }
                flag = 4;
                ins.setText(Double.toString(operand1) + "/");
                break;
            case R.id.eqv:
                if (flag != 0) {
                    result.setText(Double.toString(res));
                    ins.setText(Double.toString(res));
                    copy = "";
                    operand2 = 0;
                    operand1 = res;
                    flag = 0;

                }

                break;
            case R.id.clear:
                operand1 = 0;
                operand2 = 0;
                copy = "";
                res = 0;
                flag = 0;
                ins.setText(Double.toString(operand1));
                result.setText(Double.toString(operand1));
                break;

        }

    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("Res", res);
        outState.putDouble("Op1", operand1);
        outState.putDouble("Op2", operand2);
        outState.putCharSequence("Copy", copy);
        outState.putInt("Flag", flag);
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        res = savedInstanceState.getDouble("Res");
        operand1 = savedInstanceState.getDouble("Op1");
        operand2 = savedInstanceState.getDouble("Op2");
        flag = savedInstanceState.getInt("Flag");
        copy = savedInstanceState.getCharSequence("Copy");
        if (res == 0) result.setText(Double.toString(operand1));
        else
            result.setText(Double.toString(res));
        ins.setText(Double.toString(operand1));

        if (flag != 0) {
            switch (flag) {
                case 1:
                    ins.setText(ins.getText() + "+");
                    break;
                case 2:
                    ins.setText(ins.getText() + "-");
                    break;
                case 3:
                    ins.setText(ins.getText() + "*");
                    break;
                case 4:
                    ins.setText(ins.getText() + "/");
                    break;
            }

        }
        if (operand2 != 0) ins.setText(ins.getText() + Double.toString(operand2));

    }

}
