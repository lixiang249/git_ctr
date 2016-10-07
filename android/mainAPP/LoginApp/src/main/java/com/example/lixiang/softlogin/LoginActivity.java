package com.example.lixiang.softlogin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    Button[] myButton = new Button[5];
    Button quitAppButton,appInformation;
    static TextView textview;
    Handler handler;
    boolean flag = true;
    SharedPreferences informationShader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        informationShader = getSharedPreferences("userinformation", Context.MODE_PRIVATE);
        find();
        handler = new myHandler();
        new MyThread().start();
        
    }

    private void find() {
        quitAppButton = (Button) findViewById(R.id.quitAppButton);
        appInformation = (Button) findViewById(R.id.appInformationButton);
        myButton[0] = (Button) findViewById(R.id.button1);
        myButton[1] = (Button) findViewById(R.id.button2);
        myButton[2] = (Button) findViewById(R.id.button3);
        myButton[3] = (Button) findViewById(R.id.button4);
        myButton[4] = (Button) findViewById(R.id.button5);
        textview = (TextView) findViewById(R.id.textview);

        for (int i = 0; i < 5; i++) {
            myButton[i].setOnClickListener(this);
        }
        quitAppButton.setOnClickListener(this);
        appInformation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent();
        switch (id){
            case R.id.quitAppButton:
                alertDialogQuit();
//                finish();
                break;

            case  R.id.appInformationButton:
                alertDialogInformation();
                break;
            case R.id.button1:
                intent.setClass(LoginActivity.this,compassFengjubo.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent.setClass(LoginActivity.this,RainbowYangdeng.class);
                startActivity(intent);

                break;
            case R.id.button3:
                intent.setClass(LoginActivity.this,MainHuxiaoan.class);
                startActivity(intent);

                break;
            case R.id.button4:
                intent.setClass(LoginActivity.this, MainCalculatorLiuyue.class);
                startActivity(intent);

                break;
            case R.id.button5:
        }
    }
    //注销对话框
    private void alertDialogQuit(){
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("系统提示");
        builder.setMessage("注销当前用户，会取消当前用户记住的密码，确定要注销吗？");
        builder.setNegativeButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor userEdit = informationShader.edit();
                userEdit.putString("remember_check", "false");
                userEdit.putString("login_check","false");
                userEdit.putString("password","");
                userEdit.commit();
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setPositiveButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    //用于弹出开发人员选项的对话框
    private void alertDialogInformation(){
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("APP Information");
        builder.setMessage("APP name: MyStyle\n" +
                "Project responsibility: 李翔\nInterface: 李翔\n" +
                "Compass: 冯巨博\nRainbow: 杨登\nAcdsee: 胡小岸\n" +
                "Calculator: 刘月\nMusicPlayer: 杨礼林");
        builder.setNeutralButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog = builder.create();

        dialog.show();
    }
    class MyThread extends Thread{

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss  ");

        @Override
        public void run() {
            super.run();
            while (flag){
                try {
                    Date curDate = new Date(System.currentTimeMillis());
                    Thread.sleep(1000);
                    String str = formatter.format(curDate);
                    Message msg = new Message();
                    msg.obj = str;
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class myHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str = (String) msg.obj;
            textview.setText(str);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        flag = false;
    }

    private void alertDialog(String str){
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("系统提示");
        builder.setMessage("确定要" + str + "吗？");

        builder.setNegativeButton("确定", listener);
        builder.setPositiveButton("取消", listener);

        dialog = builder.create();

        dialog.show();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            alertDialog("退出");
        }
        return super.onKeyDown(keyCode, event);
    }

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener(){

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case AlertDialog.BUTTON_POSITIVE:
                    break;
                case AlertDialog.BUTTON_NEGATIVE:
                    finish();
                    break;
                default:break;
            }
        }
    };
}
