package com.example.lixiang.softlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainHuxiaoanTwoAciivity extends AppCompatActivity {

    Button btn_change,btn_alpha,btn_translate,btn_scale,btn_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_huxiaoan_two_aciivity);
        getSupportActionBar().hide();
        final Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        final Animation alpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        final Animation translate = AnimationUtils.loadAnimation(this, R.anim.translate);
        final Animation scale = AnimationUtils.loadAnimation(this, R.anim.scale);



        final ImageView iv = (ImageView) findViewById(R.id.imageView1);


        btn_change = (Button) findViewById(R.id.change);
        btn_alpha = (Button) findViewById(R.id.alpha);
        btn_scale = (Button) findViewById(R.id.scale);
        btn_translate = (Button) findViewById(R.id.translate);
        btn_three = (Button) findViewById(R.id.three);

        btn_change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                iv.startAnimation(rotate);
            }
        });
        btn_alpha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                iv.startAnimation(alpha);
            }
        });
        btn_translate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                iv.startAnimation(translate);
            }
        });
        btn_scale.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                iv.startAnimation(scale);
            }
        });
        btn_three.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainHuxiaoanTwoAciivity.this, MainHuxiaoanThreeActivity.class);
                startActivity(intent);


            }
        });
    }
}
