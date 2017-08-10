package com.example.winkey.retrofitdemo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.winkey.retrofitdemo.R;
import com.umeng.message.PushAgent;

public class UpushActivity extends AppCompatActivity {


    public static void start(Context context) {
        Intent intent = new Intent(context, UpushActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upush);
        PushAgent.getInstance(this).onAppStart();
    }
}
