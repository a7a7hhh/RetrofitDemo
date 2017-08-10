package com.example.winkey.retrofitdemo.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.winkey.retrofitdemo.R;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.umeng.message.PushAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SophixActivity extends AppCompatActivity {


    @BindView(R.id.tv_sophix_check)
    TextView tvSophixCheck;
    @BindView(R.id.tv_sophix_details)
    TextView tvSophixDetails;

    public static void start(Context context) {
        Intent intent = new Intent(context, SophixActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PushAgent.getInstance(this).onAppStart();
        setContentView(R.layout.activity_sophix);
        ButterKnife.bind(this);

        MainApplication.msgDisplayListener = new MainApplication.MsgDisplayListener() {
            @Override
            public void handle(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvSophixDetails.setText(msg);
                    }
                });
            }
        };


        MainApplication.msgCoderListener = new MainApplication.MsgCoderListener() {
            @Override
            public void handle(final int msg) {
                Observable.just(msg).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        if(msg == PatchStatus.CODE_LOAD_RELAUNCH) {
                            AlertDialog.Builder normalDialog =
                                    new AlertDialog.Builder(SophixActivity.this);
                            normalDialog.setTitle("reboot?");
                            normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SophixManager.getInstance().killProcessSafely();
                                }
                            });
                            normalDialog.show();
                        }
                    }
                });
            }
        };
    }

    @OnClick(R.id.tv_sophix_check)
    public void onViewClicked() {
        SophixManager.getInstance().queryAndLoadNewPatch();
    }
}
