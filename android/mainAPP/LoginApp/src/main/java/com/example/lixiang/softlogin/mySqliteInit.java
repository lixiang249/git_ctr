package com.example.lixiang.softlogin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class mySqliteInit {
    private static SqLiteHelper sqlHelper;
    private static SQLiteDatabase userdb;

    private mySqliteInit(){

    }
    public static SQLiteDatabase getInstance(Context myContext){
        if(sqlHelper == null){
            sqlHelper = new SqLiteHelper(myContext,"user.db",null,1);
            userdb = sqlHelper.getReadableDatabase();
        }
        return userdb;
    }

}
