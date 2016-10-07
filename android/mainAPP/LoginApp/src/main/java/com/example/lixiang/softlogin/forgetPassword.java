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

public class forgetPassword extends AppCompatActivity implements View.OnClickListener {

    SqLiteHelper mysqlHelper;
    SQLiteDatabase myuserdb;

    Button buttonSureChange;
    EditText usersetPass,usersetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_forget_password);
        getSupportActionBar().hide();

        find();
//        mysqlHelper = new SqLiteHelper(this,"user.db",null,1);
//        myuserdb =mysqlHelper.getReadableDatabase();
        myuserdb = mySqliteInit.getInstance(forgetPassword.this);
    }

    private void find() {
        buttonSureChange = (Button) findViewById(R.id.buttonSureChange);
        usersetName = (EditText) findViewById(R.id.usersetName);
        usersetPass = (EditText) findViewById(R.id.usersetPass);

        buttonSureChange.setOnClickListener(this);
    }

    private void alertDialog(final String dataname, final String datapass){
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("温馨提示");
        builder.setMessage("修改密码成功，是否需要立即登录");
        builder.setIcon(R.drawable.q0);//设置提示头像

        builder.setNegativeButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setClass(forgetPassword.this, MainActivity.class);
                intent.putExtra("name", dataname);
                intent.putExtra("pass", datapass);
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
        String name = usersetName.getText().toString();
        String pass = usersetPass.getText().toString();
        boolean flog = true;
        int id = 0;
        Cursor cursor = myuserdb.query("user", null, null, null, null, null, null);

        if(pass.length() < 4 || pass.length() > 8){
            Toast.makeText(this,"密码长度大于4小于8",Toast.LENGTH_SHORT).show();
            return;
        }
        while(cursor.moveToNext()){
            id = cursor.getInt(0);
            String nName = cursor.getString(1);

            if(nName.equals(name)){
                flog = false;
                break;
            }
        }
        cursor.close();
        if(flog){
            Toast.makeText(this,"用户不存在",Toast.LENGTH_SHORT).show();
        }else{
            String str = id + "";
            ContentValues cv = new ContentValues();
            cv.put("pass",pass);

            myuserdb.update("user", cv, "id=?", new String[]{str});
            Toast.makeText(this,"修改密码成功",Toast.LENGTH_SHORT).show();

            alertDialog(name,pass);
        }
    }
}
