package com.example.winkey.retrofitdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import presentation.Enum.SexEnum;

public class EnumActivity extends AppCompatActivity {


    @BindView(R.id.tv_enum_0)
    TextView tvEnum0;
    @BindView(R.id.tv_enum_1)
    TextView tvEnum1;
    @BindView(R.id.tv_enum_2)
    TextView tvEnum2;
    @BindView(R.id.tv_enum_unknown)
    TextView tvEnumUnknown;
    @BindView(R.id.enum_result)
    TextView enumResult;

    public static void start(Context context) {
        Intent intent = new Intent(context, EnumActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enum);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_enum_0, R.id.tv_enum_1, R.id.tv_enum_2, R.id.tv_enum_unknown})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_enum_0:
                enumResult.setText(SexEnum.Unkonwn.getKey(0).toString());
                break;
            case R.id.tv_enum_1:
                enumResult.setText(SexEnum.Unkonwn.getKey(1).toString());
                break;
            case R.id.tv_enum_2:
                enumResult.setText(SexEnum.Unkonwn.getKey(2).toString());
                break;
            case R.id.tv_enum_unknown:
                enumResult.setText(SexEnum.Unkonwn.getKey(3).toString());
                break;
        }
    }
}
