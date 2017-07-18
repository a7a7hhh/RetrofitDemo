package com.example.winkey.retrofitdemo.presentation.presenter.download;

import android.support.annotation.NonNull;

import com.example.winkey.retrofitdemo.data.api.download.Download;
import com.example.winkey.retrofitdemo.data.model.vo.AppInfoVO;
import com.example.winkey.retrofitdemo.data.model.vo.HomeBannerVO;
import com.example.winkey.retrofitdemo.presentation.presenter.base.BasePresenter;
import com.example.winkey.retrofitdemo.presentation.presenter.base.CallBackListener;

/**
 * Created by Winkey on 2017/7/18.
 */

public class DownloadPresenter extends BasePresenter {
    public void downloadFile(CallBackListener<Download> callBackListener, @NonNull String url){
        execute(callBackListener,downloadFile().download(url));
    }

    public void getBanner(CallBackListener<HomeBannerVO> callBackListener){
        execute(callBackListener,api().getBannerData());
    }

    public void getVersion(CallBackListener<AppInfoVO> callBackListener,@NonNull String url){
        execute(callBackListener,api().getAppInfo(url));
    }

}
