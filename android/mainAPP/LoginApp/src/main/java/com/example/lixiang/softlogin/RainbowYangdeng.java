package com.example.lixiang.softlogin;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class RainbowYangdeng extends AppCompatActivity implements View.OnClickListener {

    private int currentColor = 0;
    boolean flag=false;
    public Timer timer;

    Button btu_open,btu_close;

    final int[] colors = new int[] {R.color.red, R.color.blue, R.color.green,
            R.color.cyan, R.color.orange, R.color.purple };


    final int[] idName = new int[] { R.id.view01, R.id.view02, R.id.view03,
            R.id.view04, R.id.view05, R.id.view06 };


    TextView[] views = new TextView[idName.length];

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {

            if (msg.what == 1) {
                for (int i = 0; i < idName.length; i++) {
                    views[i].setBackgroundResource(colors[(i + currentColor)
                            % idName.length]);
                }
                currentColor++;
            }
        }


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rainbow_yangdeng);
        getSupportActionBar().hide();
        for (int i = 0; i < idName.length; i++) {
            views[i] = (TextView) findViewById(idName[i]);
        }
        btu_close=(Button) findViewById(R.id.close);
        btu_open=(Button) findViewById(R.id.open);

        btu_close.setOnClickListener(this);
        btu_open.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {

            case R.id.open:
                timer = new Timer();
                flag=true;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(flag)
                        {
                            handler.sendEmptyMessage(1);
                        }
                    }
                }, 0, 200);
                break;
            case R.id.close:
                if(timer!=null)
                {
                    flag=false;
                    timer.cancel();
                }
                break;
            default:
                break;
        }
    }
}
