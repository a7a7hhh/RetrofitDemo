package com.example.winkey.retrofitdemo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.winkey.retrofitdemo.R;
import com.example.winkey.retrofitdemo.presentation.Enum.SexEnum;
import com.umeng.message.PushAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnumActivity extends AppCompatActivity {


    @BindView(R.id.enum_result)
    TextView enumResult;
    @BindView(R.id.edt_enum)
    EditText edtEnum;
    @BindView(R.id.tv_enum_confirm)
    TextView tvEnumConfirm;

    public static void start(Context context) {
        Intent intent = new Intent(context, EnumActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PushAgent.getInstance(this).onAppStart();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enum);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.tv_enum_confirm)
    public void onViewClicked() {
        if(edtEnum.getText()!=null){
            int value = Integer.parseInt(edtEnum.getText().toString());
            enumResult.setText(SexEnum.Unkonwn.getKey(value).toString());
        }
    }
}
