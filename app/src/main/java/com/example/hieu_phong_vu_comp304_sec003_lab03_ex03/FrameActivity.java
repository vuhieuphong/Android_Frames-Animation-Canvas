package com.example.hieu_phong_vu_comp304_sec003_lab03_ex03;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FrameActivity extends AppCompatActivity {
    AnimationDrawable mframeAnimation = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_main);
        final Button buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View v) {
                startAnimation();
            }
        });

        // Handle Stop Button
        final Button buttonStop = (Button) findViewById(R.id.buttonStop);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stopAnimation();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void startAnimation()
    {
        ImageView img = (ImageView)findViewById(R.id.img);

        BitmapDrawable frame0 = (BitmapDrawable)getResources().getDrawable(R.drawable.frame_0);
        BitmapDrawable frame1 = (BitmapDrawable)getResources().getDrawable(R.drawable.frame_1);
        BitmapDrawable frame2 = (BitmapDrawable)getResources().getDrawable(R.drawable.frame_2);
        BitmapDrawable frame3 = (BitmapDrawable)getResources().getDrawable(R.drawable.frame_3);

        // Get the background, which has been compiled to an AnimationDrawable object.
        int duration = 200;
        mframeAnimation = new AnimationDrawable();
        mframeAnimation.setOneShot(false);	// loop continuously
        mframeAnimation.addFrame(frame0, duration);
        mframeAnimation.addFrame(frame1, duration);
        mframeAnimation.addFrame(frame2, duration);
        mframeAnimation.addFrame(frame3, duration);

        img.setBackground(mframeAnimation);

        mframeAnimation.setVisible(true,true);
        mframeAnimation.start();
    }
    private void stopAnimation()
    {
        mframeAnimation.stop();
        mframeAnimation.setVisible(false,false);
    }
}


