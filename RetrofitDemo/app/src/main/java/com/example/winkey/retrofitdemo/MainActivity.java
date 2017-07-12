package com.example.winkey.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_enum)
    TextView btnEnum;
    @BindView(R.id.btn_retrofit)
    TextView btnRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_enum, R.id.btn_retrofit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_enum:
                EnumActivity.start(this);
                break;
            case R.id.btn_retrofit:
                break;
        }
    }
}
