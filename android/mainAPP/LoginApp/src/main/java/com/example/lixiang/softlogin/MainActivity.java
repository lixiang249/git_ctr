package com.example.lixiang.softlogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //控件类对象声明
    Button buttonLogin,buttonQuit,buttonRegister,buttonforget;
    EditText userName,passWord;
    CheckBox remberPassword,loginByself;
    SharedPreferences informationShader;


    //声明数据库需要用到的对象
    SqLiteHelper mysqlHelper;
    SQLiteDatabase myuserdb;


    //界面跳转对象声明
    Intent intentLogin = new Intent();
//    RegisterActivity r = new RegisterActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        informationShader = getSharedPreferences("userinformation", Context.MODE_PRIVATE);

        find();//找到控件和事件监听
        getUserInformation();//获取用户信息，在记住密码选中时才会有效

        myuserdb = mySqliteInit.getInstance(MainActivity.this);

    }

    //该方法用于找到控件以及监听相应控件
    private void find() {
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        buttonQuit  = (Button)findViewById(R.id.buttonQuit);
        buttonRegister = (Button)findViewById(R.id.buttonRegister);
        buttonforget = (Button) findViewById(R.id.buttonforget);

        remberPassword = (CheckBox) findViewById(R.id.remberPassword);
        loginByself = (CheckBox) findViewById(R.id.loginByself);

        userName = (EditText)findViewById(R.id.userName);
        passWord = (EditText)findViewById(R.id.passWord);

        buttonLogin.setOnClickListener(this);
        buttonQuit.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
        buttonforget.setOnClickListener(this);

        //两个复选框，分别代表记住密码和自动登录
        remberPassword.setOnCheckedChangeListener(new checkBox());
        loginByself.setOnCheckedChangeListener(new checkBox());
    }

    //获取用户信息，在记住密码选中时才会有效
    private void getUserInformation(){

        String username = informationShader.getString("username","");
        String password = informationShader.getString("password", "");
        String remember_check = informationShader.getString("remember_check", "");
        String login_check = informationShader.getString("login_check", "");
        userName.setText(username);
        passWord.setText(password);

        if(remember_check.equals("true")){
            remberPassword.setChecked(true);
            if(login_check.equals("true")){//若选中记住密码并且自动登录，直接加载到登录界面
                loginByself.setChecked(true);
                intentLogin.setClass(MainActivity.this, LoginActivity.class);
                startActivity(intentLogin);
                finish();
            }
            else{
                loginByself.setChecked(false);
            }
        }
        else{
            remberPassword.setChecked(false);
        }
    }

    private void login(){
        boolean flog = true;
        String username = userName.getText().toString();
        String password = passWord.getText().toString();
        Cursor cursor = myuserdb.query("user", null, null, null, null, null, null);
        while (cursor.moveToNext()){
            String nName = cursor.getString(1);
            String nPass = cursor.getString(2);
            if(nName.equals(username)){
                flog = false;
                if(nPass.equals(password)){
                    intentLogin.setClass(MainActivity.this, LoginActivity.class);
                    startActivity(intentLogin);
                    finish();
                    break;
                }
                else{
                    Toast.makeText(this,"密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        }
        cursor.close();
        if(flog){
            Toast.makeText(this,"用户不存在",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    //按钮的事件响应
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.buttonLogin://登录按钮
                login();
                break;
            case R.id.buttonQuit://退出按钮
                finish();
                break;
            case R.id.buttonRegister://注册按钮
                Intent intentRegister = new Intent();
                intentRegister.setClass(MainActivity.this, RegisterActivity.class);
                startActivityForResult(intentRegister, 1);
                break;
            case R.id.buttonforget://忘记密码
                intentLogin.setClass(MainActivity.this, forgetPassword.class);
                startActivityForResult(intentLogin, 2);
                break;
            default:break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String dataname = null;
        String datapass = null;

        if(requestCode == 2 && resultCode == RESULT_OK){
            dataname = data.getStringExtra("name");
            datapass = data.getStringExtra("pass");

        }else if(requestCode == 1 && resultCode == RESULT_OK){
            dataname = data.getStringExtra("name");
            datapass = data.getStringExtra("pass");

        }
        userName.setText(dataname);
        passWord.setText(datapass);
    }

    //复选框选中事件
    class checkBox implements CompoundButton.OnCheckedChangeListener{
        @Override

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int id = buttonView.getId();//被选中的checkbox的ID号
//            获取编辑器
            SharedPreferences.Editor userEdit = informationShader.edit();
            switch (id){
                case R.id.remberPassword:
                    if(isChecked){
                        userEdit.putString("username",userName.getText().toString());
                        userEdit.putString("password", passWord.getText().toString());
                        userEdit.putString("remember_check","true");
                    }else{
                        userEdit.putString("username","");
                        userEdit.putString("password","");
                        userEdit.putString("remember_check","false");
                    }
                    userEdit.commit();
                    break;
                //自动登录
                case R.id.loginByself:
                    if(isChecked){
                        userEdit.putString("login_check", "true");
                    }else{
                        userEdit.putString("login_check","false");
                    }
                    userEdit.commit();
                    break;
                default:break;
            }
        }
    }


    //    class MyThread extends  Thread{
//        @Override
//        public void run() {
//            super.run();
//            while(flag){
//                Intent intent = getIntent();
//                String dataname = intent.getStringExtra("name");
//                String datapass = intent.getStringExtra("pass");
//
//                Message msg = new Message();
//                msg.obj = dataname;
//                msg.obj = datapass;
//
//                handler.sendMessage(msg);
//            }
//        }
//    }
//
//    class  MyHander extends Handler{
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            String strname = (String) msg.obj;
//            String strpass = (String) msg.obj;
//            Toast.makeText(MainActivity.this,"strname = " + strname,Toast.LENGTH_LONG).show();
//            Toast.makeText(MainActivity.this,"strpass = " + strpass,Toast.LENGTH_LONG).show();
//            userName.setText(strname);
//            passWord.setText(strpass);
//            flag = false;
//        }
//    }
}



