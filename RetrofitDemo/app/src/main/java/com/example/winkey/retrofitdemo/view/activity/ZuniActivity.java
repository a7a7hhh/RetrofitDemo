package com.example.winkey.retrofitdemo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.winkey.retrofitdemo.R;
import com.example.winkey.retrofitdemo.view.utils.Logger;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;
import com.umeng.message.PushAgent;

public class ZuniActivity extends AppCompatActivity{

    private SpringSystem mSpringSystem;
    private Spring mSpring;
    private Button btnCircle;
    public static void start(Context context) {
        Intent intent = new Intent(context, ZuniActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PushAgent.getInstance(this).onAppStart();
        setContentView(R.layout.activity_zuni);
        final RelativeLayout llItem = (RelativeLayout) findViewById(R.id.ll_item);
        btnCircle = (Button) findViewById(R.id.btn_circle);
        llItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                initSpring();
                return false;
            }
        });

    }

    private void initSpring(){
        Logger.debug("initSpring");
        mSpringSystem = SpringSystem.create();
        mSpring = mSpringSystem
                .createSpring()
                .setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(90, 3))
                .addListener(new SimpleSpringListener() {
                    @Override
                    public void onSpringUpdate(Spring spring) {
                        float value = (float) spring.getCurrentValue();

                        Logger.debug("springupdate"+value);
                        btnCircle.setScaleX(value);
                        btnCircle.setScaleY(value);
                    }
                });


        mSpring.setEndValue(1f);
    }


}
