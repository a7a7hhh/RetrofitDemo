package com.example.winkey.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_enum)
    TextView btnEnum;
    @BindView(R.id.btn_retrofit)
    TextView btnRetrofit;
    @BindView(R.id.btn_sophix)
    TextView btnSophix;
    @BindView(R.id.btn_rxbus)
    TextView btnRxbus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_enum, R.id.btn_retrofit, R.id.btn_sophix, R.id.btn_rxbus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_enum:
                EnumActivity.start(this);
                break;
            case R.id.btn_retrofit:
                break;
            case R.id.btn_sophix:
                SophixActivity.start(this);
                break;
            case R.id.btn_rxbus:
                Toast.makeText(this, "rxbus待开发中", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
