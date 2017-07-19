package com.example.winkey.retrofitdemo.presentation.presenter.base;

import android.support.annotation.NonNull;

import com.example.winkey.retrofitdemo.data.DataManage;
import com.example.winkey.retrofitdemo.data.api.ApiService;
import com.example.winkey.retrofitdemo.data.api.base.ApiManager;
import com.example.winkey.retrofitdemo.data.api.download.Download;
import com.example.winkey.retrofitdemo.data.model.vo.HomeBannerVO;

import java.io.InputStream;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Winkey on 2017/7/18.
 */

public abstract class BasePresenter  {

    protected ApiService api() {
        return ApiManager.getInstance().getApiService();
    }

    /**
     * 获取DownloadApiService
     *
     * @return
     */
    protected ApiService downloadFile() {
        return ApiManager.getInstance().getDownloadService();
    }

    private DataManage getDataManage() {
        return DataManage.getInstance();
    }

    private BaseSubscriber<?> getSubscriber(CallBackListener<?> callbackListener) {
        return SubscriberFactory.getInstance().createSubscribe(callbackListener, this);
    }

    protected void execute(CallBackListener<?> callbackListener, Observable<?> observable) {
        getDataManage().execute(getSubscriber(callbackListener), observable);
    }
    protected void executeDownload(Action1<ResponseBody> action, CallBackListener<?> callbackListener, Observable<ResponseBody> observable) {
        getDataManage().executeDownload(action,getSubscriber(callbackListener), observable);
    }


}
