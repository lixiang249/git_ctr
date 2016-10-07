package com.example.lixiang.softlogin;

/**
 * Created by lixiang on 2016/8/23.
 * 用于封装用户信息
 */
public class userInformation {
    private String name;
    private String passWord;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPassWord(String passWord){
        this.passWord = passWord;
    }

    public  String getPassWord(){
        return passWord;
    }
}
