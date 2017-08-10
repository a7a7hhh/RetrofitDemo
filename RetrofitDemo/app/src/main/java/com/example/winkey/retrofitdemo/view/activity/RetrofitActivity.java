package com.example.winkey.retrofitdemo.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.winkey.retrofitdemo.R;
import com.example.winkey.retrofitdemo.data.api.download.Download;
import com.example.winkey.retrofitdemo.data.api.download.DownloadProgressHandler;
import com.example.winkey.retrofitdemo.data.api.download.DownloadProgressHelper;
import com.example.winkey.retrofitdemo.data.model.vo.AppInfoVO;
import com.example.winkey.retrofitdemo.data.model.vo.HomeBannerVO;
import com.example.winkey.retrofitdemo.presentation.presenter.base.CallBackListener;
import com.example.winkey.retrofitdemo.presentation.presenter.download.DownloadPresenter;
import com.example.winkey.retrofitdemo.view.utils.FileUtils;
import com.example.winkey.retrofitdemo.view.utils.Logger;
import com.umeng.message.PushAgent;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Winkey on 2017/7/18.
 */

public class RetrofitActivity extends AppCompatActivity {



    @BindView(R.id.btn_download)
    Button btnDownload;
    @BindView(R.id.tv_current_version)
    TextView tvCurrentVersion;
    @BindView(R.id.tv_sever_version)
    TextView tvSeverVersion;
    @BindView(R.id.tv_get_version)
    TextView tvGetVersion;
    @BindView(R.id.tv_download_progress)
    TextView tvDownloadProgress;


    private String versionUrl = "http://flm-resource.oss-cn-shanghai.aliyuncs.com/Download/App.Android_com.ykx.flm.broker/Version.json";
    private String apkUrl ;


    private final int REQUESTCODE_PERMISSION = 1;

    private String[] permissions = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WAKE_LOCK,
            Manifest.permission.GET_TASKS,
            Manifest.permission.WRITE_SETTINGS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.VIBRATE,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
    };


    DownloadPresenter presenter;

    public static void start(Context context) {
        Intent intent = new Intent(context, RetrofitActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PushAgent.getInstance(this).onAppStart();
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
        checkPermission();

        presenter = new DownloadPresenter(this);

    }

    /**
     * android 6.0上需要自主Check权限，进行动态权限申请，否则无法申请创建文件
     */

    private void checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(RetrofitActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(RetrofitActivity.this, permissions,
                        REQUESTCODE_PERMISSION);

                return;
            }
        }
    }

    @OnClick(R.id.btn_download)
    public void onViewClicked() {

    }

    @OnClick({R.id.tv_get_version, R.id.btn_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_version:
                getServerVersion();
                break;
            case R.id.btn_download:
                setDownloadListenter();
                downloadApk();
                break;
        }
    }

    private void getServerVersion() {
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
                apkUrl = appInfoVO.target;
            }
        },versionUrl);
    }

    private void downloadApk(){


        presenter.downloadFile(new Action1<ResponseBody>() {
            @Override
            public void call(ResponseBody responseBody) {
                FileUtils.writeResponseBodyToDisk(responseBody, "123.apk",RetrofitActivity.this);
            }
        },
        new CallBackListener<ResponseBody>() {
            @Override
            public void callBack(ResponseBody download) {
               //这里可以写完成下载之后的操作
            }
        },apkUrl);

    }

    private void setDownloadListenter() {
        DownloadProgressHandler downloadProgressHandler = new DownloadProgressHandler() {
            @Override
            public void onProgress(long progress) {
                tvDownloadProgress.setText(progress+"");
            }
        };
        DownloadProgressHelper.setDownloadStates(downloadProgressHandler);
    }
}
