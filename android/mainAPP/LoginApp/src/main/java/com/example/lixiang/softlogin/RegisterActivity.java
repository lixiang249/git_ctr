package com.example.lixiang.softlogin;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonSureRegister,buttonQuitRegister;
    EditText editTextNameRegister,editTextPassRegister,editTextPassAgain;

//    SqLiteHelper sqlHelper;
    SQLiteDatabase userdb;

    boolean flog = false;//标志用户注册的用户名是否存在，存在则为true

    String name;
    String pass;
    String passagain;

    ContentValues cvData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        find();
    }

    //本方法用于找到注册activity中各个控件以及事件监听
    private void find() {
        //找到Button
        buttonQuitRegister = (Button) findViewById(R.id.buttonQuitRegister);
        buttonSureRegister = (Button) findViewById(R.id.buttonSureRegister);

        //找到EditText
        editTextNameRegister = (EditText) findViewById(R.id.editTextNameRegister);
        editTextPassRegister = (EditText) findViewById(R.id.editTextPassRegister);
        editTextPassAgain = (EditText) findViewById(R.id.editTextPassAgain);

        //监听Button
        buttonSureRegister.setOnClickListener(this);
        buttonQuitRegister.setOnClickListener(this);

        //数据库操作
//        sqlHelper = new SqLiteHelper(this,"user.db",null,1);
//        userdb = sqlHelper.getWritableDatabase();
        userdb = mySqliteInit.getInstance(RegisterActivity.this);
    }

    //用户注册时出现错误的提示对话框
    private void alertDialog(String type,String infom){
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage(type+"输入错误，请重新输入\n"+ infom);
        builder.setIcon(R.drawable.q0);//设置提示头像
        builder.setNeutralButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog = builder.create();

        dialog.show();
    }

    private void alertDialog2(){
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("温馨提示");
        builder.setMessage("注册成功，是否需要立即登录");
        builder.setIcon(R.drawable.q0);//设置提示头像

        builder.setNegativeButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, MainActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("pass", pass);
                setResult(RESULT_OK,intent);
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
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            //退出注册界面，回到主界面
            case R.id.buttonQuitRegister:
                Intent intentQuit = new Intent();
                intentQuit.setClass(RegisterActivity.this,MainActivity.class);
                startActivity(intentQuit);
                break;

            //注册按钮按下，进行注册
            case R.id.buttonSureRegister:
                cvData = new ContentValues();
                name = editTextNameRegister.getText().toString();
                pass = editTextPassRegister.getText().toString();
                passagain = editTextPassAgain.getText().toString();

                cvData.put("name",name);
                cvData.put("pass", pass);

                //用于检查用户信息录入和进行注册
                if(checkInformation(name,pass,passagain,cvData)){
                    alertDialog2();
                }
                break;
        }
    }

    //该方法用于用户注册信息的判断，username长度大于等于4，password长度大于等于4
    protected boolean checkInformation(String userNameC,String passWordC,String passAgainC,ContentValues cv){
        boolean registerSuccess = false;
        int ulen = userNameC.length();
        int plen = passWordC.length();
        int palen = passAgainC.length();

        Cursor cursor = userdb.query("user",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            String nName = cursor.getString(1);
            if(userNameC.equals(nName)) {
                flog = true;
                break;
            }
        }
        cursor.close();
        if(flog){
            Toast.makeText(this, "用户已存在", Toast.LENGTH_SHORT).show();
            flog = false;
        }else if( ulen >= 4 && ulen < 8){
                if(plen >= 4 && plen <= 8 && palen >= 4 && palen <= 8){
                    if(passWordC.equals(passAgainC)){
                        userdb.insert("user", null, cv);
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        registerSuccess = true;
                    }else {
                        RegisterActivity.this.alertDialog("第二次密码", "");
                    }
                }else {
                    RegisterActivity.this.alertDialog("密码", "密码长度大于等于4，小于8");
                }
            }else {
            RegisterActivity.this.alertDialog("用户名", "用户名长度大于等于4，小于8");
            }
        return registerSuccess;
    }

}
