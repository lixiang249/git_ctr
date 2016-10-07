package com.example.lixiang.softlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainHuxiaoan extends AppCompatActivity implements View.OnClickListener {
    Button btn_crease, btn_decrease, btn_next, btn_forword,btn_change;
    ImageView iv_show;

    int currId;
    int alpha = 255;//设置透明度的值

    //设置一个图片资源数组
    int[] image = {R.drawable.i01, R.drawable.i02, R.drawable.i03, R.drawable.i04,
            R.drawable.i05, R.drawable.i06, R.drawable.i07, R.drawable.i08,
            R.drawable.i09, R.drawable.i10, R.drawable.i11, R.drawable.i12,
            R.drawable.i13, R.drawable.i14, R.drawable.i15, R.drawable.i16,
            R.drawable.i17,R.drawable.i18,R.drawable.i19,R.drawable.i20};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_huxiaoan);//加载界面
        getSupportActionBar().hide();

        //找到Button以及ImageView
        iv_show = (ImageView) findViewById(R.id.imageView1);
        btn_forword = (Button) findViewById(R.id.btn_forword);
        btn_crease = (Button) findViewById(R.id.btn_crease);
        btn_decrease = (Button) findViewById(R.id.btn_decrease);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_change = (Button) findViewById(R.id.btn_change);
        //为每一个按钮设置监听器
        btn_forword.setOnClickListener(this);
        btn_crease.setOnClickListener(this);
        btn_decrease.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        btn_change.setOnClickListener(this);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            //上一张
            case R.id.btn_forword:
                currId = (currId-1 + image.length)%image.length;//得到上一张图片
                iv_show.setImageResource(image[currId]);//将得到的图片设置上去
                break;

            //增加透明度
            case R.id.btn_crease:
                alpha -= 25;//是透明度的值减少，就是图片越来越模糊
                if(alpha < 0) {
                    alpha = 0;
                    Toast.makeText(this, "已经最透明了", Toast.LENGTH_SHORT).show();
                }
                iv_show.setAlpha(alpha);//设置透明度
                break;

            //减少透明度
            case R.id.btn_decrease:
                alpha += 25;//是透明度的值增加，就是图片越来越清晰
                if(alpha > 255) {
                    alpha = 255;
                    Toast.makeText(this, "已经最清晰了", Toast.LENGTH_SHORT).show();
                }
                iv_show.setAlpha(alpha);//设置透明度
                break;

            //下一张
            case R.id.btn_next:
                currId = (currId+1)%image.length;//得到下一张图片
                iv_show.setImageResource(image[currId]);//将得到的图片设置上去
                break;

            case R.id.btn_change:
                Intent intent = new Intent();
                intent.setClass(this,MainHuxiaoanTwoAciivity.class );
                startActivity(intent);
                break;


            default:
                break;
        }
    }

}
