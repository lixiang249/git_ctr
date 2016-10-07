package com.example.lixiang.softlogin;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.List;

public class compassFengjubo extends AppCompatActivity {
    private ImageView imageView;
    float currentDegree = 0f;

    SensorManager mSensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_compass_fengjubo);
        getSupportActionBar().hide();
        imageView = (ImageView) findViewById(R.id.imageView_FJB);


        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


        List<Sensor> list = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for (int i = 0; i < list.size(); i++) {
            Sensor s = list.get(i);
            System.out.println("当前传感器有：" + s.getName());
        }

        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        mSensorManager.registerListener(new MyListener(), sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    class MyListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {

            float[] degree = event.values;
            System.out.println("当前角度：" + degree[0]);

            RotateAnimation ra = new RotateAnimation(currentDegree, degree[0],
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            ra.setFillAfter(true);
            currentDegree = degree[0];
            imageView.startAnimation(ra);

        }
        @Override
        public void onAccuracyChanged(Sensor arg0, int arg1) {

        }
    }
}
