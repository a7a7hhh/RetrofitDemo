package com.example.winkey.retrofitdemo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.winkey.retrofitdemo.R;
import com.example.winkey.retrofitdemo.data.model.vo.AppInfoVO;
import com.example.winkey.retrofitdemo.data.model.vo.HomeBannerVO;
import com.example.winkey.retrofitdemo.presentation.presenter.base.CallBackListener;
import com.example.winkey.retrofitdemo.presentation.presenter.download.DownloadPresenter;
import com.example.winkey.retrofitdemo.view.utils.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Winkey on 2017/7/18.
 */

public class RetrofitActivity extends AppCompatActivity {


    @BindView(R.id.tv_current_version)
    TextView tvCurrentVersion;
    @BindView(R.id.tv_sever_version)
    TextView tvSeverVersion;
    @BindView(R.id.tv_get_version)
    TextView tvGetVersion;
    @BindView(R.id.tv_download_progress)
    TextView tvDownloadProgress;
    private String versionUrl = "http://flm-resource.oss-cn-shanghai.aliyuncs.com/Download/App.Android_com.ykx.flm.broker/Version.json";


    @BindView(R.id.btn_download)
    Button btnDownload;

    public static void start(Context context) {
        Intent intent = new Intent(context, RetrofitActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btn_download)
    public void onViewClicked() {

    }

    @OnClick({R.id.tv_get_version, R.id.btn_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_version:
                getServrVersion();
                break;
            case R.id.btn_download:
                break;
        }
    }

    private void getServrVersion() {
        DownloadPresenter presenter = new DownloadPresenter();
        /*presenter.downloadFile(new CallBackListener<Download>() {
            @Override
            public void callBack(Download download) {
            }
        }, apkUrl);*/
        presenter.getVersion(new CallBackListener<AppInfoVO>() {
            @Override
            public void callBack(AppInfoVO appInfoVO) {
                Logger.debug(appInfoVO.version);
                tvSeverVersion.setText(appInfoVO.version);
            }
        },versionUrl);
    }
}
