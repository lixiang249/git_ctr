package com.lilin.pages;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by lilin on 16-8-27.
 */
public class Image_Frament extends AppCompatActivity{
    Button btn_last,btn_start,btn_next,btn_jmp_list;
    ImageView iv_background;
    RoundImageView iv_circle;
    TextView tv;
    Bitmap bm_origin,bm_circle,bm_blured;
    Animation mAnimation;
    SeekBar seekBar;
    MediaPlayer mp;
    Intent intent_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findView();

        intit();

        setListener();

    }



    public void findView(){
        iv_circle=(RoundImageView)findViewById(R.id.iv_circle);
        iv_background=(ImageView)findViewById(R.id.iv_background);
        btn_jmp_list= (Button) findViewById(R.id.btn_jmp_list);


    }

    private void intit() {
        bm_origin= BitmapFactory.decodeResource(getResources(),R.mipmap.sea_star);

        mAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_repeat);
        mAnimation.setInterpolator(new LinearInterpolator());
        iv_circle.startAnimation(mAnimation);

        bm_blured= FastBlurUtil.doBlur(bm_origin,8,false);
        iv_background.setImageBitmap(bm_blured);

    }

    private void setListener() {
        btn_jmp_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void initSeekBar(){
        seekBar.setMax(mp.getDuration());
        seekBar.setProgress(0);
        //tv_totalTime.setText(toTime(mp.getDuration()));
    }
}
