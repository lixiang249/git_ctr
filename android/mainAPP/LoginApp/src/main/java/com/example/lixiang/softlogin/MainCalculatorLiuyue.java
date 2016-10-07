package com.example.lixiang.softlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainCalculatorLiuyue extends AppCompatActivity implements View.OnClickListener {

    Button one,two,three,four,five,six,seven,eight,nine,
            add,reduce,ride,division,equal,back,clear,zero;
    TextView tv;
    double num1=0,num2=0;
    double Result=0;
    int op=0;
    boolean isClickEqu=false;
    String str = "";
    SC sc = new SC(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calculator_liuyue);
        zero = (Button) findViewById(R.id.zero);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        add = (Button) findViewById(R.id.add);
        reduce = (Button) findViewById(R.id.reduce);
        ride = (Button) findViewById(R.id.ride);
        division= (Button) findViewById(R.id.division);
        equal = (Button) findViewById(R.id.equal);
        back = (Button) findViewById(R.id.back);
        clear = (Button) findViewById(R.id.clear);
        tv = (TextView) findViewById(R.id.textview1);

        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        add.setOnClickListener(this);
        reduce.setOnClickListener(this);
        ride.setOnClickListener(this);
        division.setOnClickListener(this);
        equal.setOnClickListener(this);
        back.setOnClickListener(this);
        clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zero:
                if(isClickEqu)
                {
                    tv.setText(null);
                    isClickEqu=false;
                }
                String myString=tv.getText().toString();
                myString+="0";
                tv.setText(myString);
                break;
            case R.id.one:
                if(isClickEqu)
                {
                    tv.setText(null);
                    isClickEqu=false;
                }
                String myString1=tv.getText().toString();
                myString1+="1";
                tv.setText(myString1);
                break;
            case R.id.two:
                if(isClickEqu)
                {
                    tv.setText(null);
                    isClickEqu=false;
                }
                String myString2=tv.getText().toString();
                myString2+="2";
                tv.setText(myString2);
                break;
            case R.id.three:
                if(isClickEqu)
                {
                    tv.setText(null);
                    isClickEqu=false;
                }
                String myString3=tv.getText().toString();
                myString3+="3";
                tv.setText(myString3);
                break;
            case R.id.four:
                if(isClickEqu)
                {
                    tv.setText(null);
                    isClickEqu=false;
                }
                String myString4=tv.getText().toString();
                myString4+="4";
                tv.setText(myString4);
                break;
            case R.id.five:
                if(isClickEqu)
                {
                    tv.setText(null);
                    isClickEqu=false;
                }
                String myString5=tv.getText().toString();
                myString5+="5";
                tv.setText(myString5);
                break;
            case R.id.six:
                if(isClickEqu)
                {
                    tv.setText(null);
                    isClickEqu=false;
                }
                String myString6=tv.getText().toString();
                myString6+="6";
                tv.setText(myString6);
                break;
            case R.id.seven:
                if(isClickEqu)
                {
                    tv.setText(null);
                    isClickEqu=false;
                }
                String myString7=tv.getText().toString();
                myString7+="7";
                tv.setText(myString7);
                break;
            case R.id.eight:
                if(isClickEqu)
                {
                    tv.setText(null);
                    isClickEqu=false;
                }
                String myString8=tv.getText().toString();
                myString8+="8";
                tv.setText(myString8);
                break;
            case R.id.nine:
                if(isClickEqu)
                {
                    tv.setText(null);
                    isClickEqu=false;
                }
                String myString9=tv.getText().toString();
                myString9+="9";
                tv.setText(myString9);
                break;
            case R.id.add:
                String myStringAdd=tv.getText().toString();
                if(myStringAdd.equals(null))
                {
                    return;
                }
                num1=Double.valueOf(myStringAdd);
                tv.setText(null);
                op=1;
                isClickEqu=false;
                break;
            case R.id.reduce:
                String myStringSub=tv.getText().toString();
                if(myStringSub.equals(null))
                {
                    return;
                }
                num1=Double.valueOf(myStringSub);
                tv.setText(null);
                op=2;
                isClickEqu=false;
                break;
            case R.id.ride:
                String myStringMul=tv.getText().toString();
                if(myStringMul.equals(null))
                {
                    return;
                }
                num1=Double.valueOf(myStringMul);
                tv.setText(null);
                op=3;
                isClickEqu=false;
                break;
            case R.id.division:
                String myStringDiv=tv.getText().toString();
                if(myStringDiv.equals(null))
                {
                    return;
                }
                num1=Double.valueOf(myStringDiv);
                tv.setText(null);
                op=4;
                isClickEqu=false;
                break;
            case R.id.equal:
                String myStringEqu=tv.getText().toString();
                if(myStringEqu.equals(null))
                {
                    return;
                }
                num2=Double.valueOf(myStringEqu);
                tv.setText(null);
                switch (op) {
                    case 0:
                        Result=num2;
                        break;
                    case 1:
                        Result=num1+num2;
                        break;
                    case 2:
                        Result=num1-num2;
                        break;
                    case 3:
                        Result=num1*num2;
                        break;
                    case 4:
                        Result=num1/num2;
                        break;
                    default:
                        Result=0;
                        break;
                }
                tv.setText(String.valueOf(Result));
                isClickEqu=true;
                break;
            case R.id.back:
                String myStr=tv.getText().toString();
                try {
                    tv.setText(myStr.substring(0, myStr.length()-1));
                } catch (Exception e) {
                    tv.setText("");
                }
                break;
            case R.id.clear:
                tv.setText(null);
                break;

            default:
                break;
        }


    }
}
