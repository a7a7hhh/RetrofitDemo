package com.example.winkey.retrofitdemo.presentation.presenter.download;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.winkey.retrofitdemo.data.api.ApiService;
import com.example.winkey.retrofitdemo.data.api.download.Download;
import com.example.winkey.retrofitdemo.data.api.download.DownloadProgressHelper;
import com.example.winkey.retrofitdemo.data.model.vo.AppInfoVO;
import com.example.winkey.retrofitdemo.data.model.vo.HomeBannerVO;
import com.example.winkey.retrofitdemo.presentation.presenter.base.BasePresenter;
import com.example.winkey.retrofitdemo.presentation.presenter.base.CallBackListener;
import com.example.winkey.retrofitdemo.view.utils.FileUtils;
import com.example.winkey.retrofitdemo.view.utils.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Winkey on 2017/7/18.
 */

public class DownloadPresenter extends BasePresenter {
    private Context context;

    public DownloadPresenter(Context context){
        this.context = context;
    }

    public void downloadFile(Action1<ResponseBody> action,CallBackListener<ResponseBody> callBackListener, @NonNull String url) {
         executeDownload(action,callBackListener,downloadFile().download(url));


        //上面的实现大致类似如下：1.提供OkhttpClient；2.提供Retrofit；3.在doOnNext中执行写文件操作将ResponseBody写入文件 4.在订阅者Subscribe中执行之后的操作
        /*String HOST = "http://flm-resource.oss-cn-shanghai.aliyuncs.com";

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        client.readTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        DownloadProgressHelper.addClient(client);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(client.build())
                .build();

        retrofit.create(ApiService.class)
                .download(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new Func1<ResponseBody, InputStream>() {
                    @Override
                    public InputStream call(ResponseBody responseBody) {
                        Logger.debug("xwb"+responseBody.contentLength());



                        return responseBody.byteStream();
                    }
                })
                .observeOn(Schedulers.computation())
                .doOnNext(new Action1<InputStream>() {
                    @Override
                    public void call(InputStream o) {

                        String path = FileUtils.getDownloadDirPath(context);
                        File files = new File(path);
                        if (!files.exists()) {
                            files.mkdirs();
                        }
                        FileUtils.writeResponseBodyToDisk(o, "123.apk",context);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        //downloadCompleted();
                        Logger.debug("downloadComplete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        //downloadCompleted();
                        //Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Object o) {

                        Logger.debug("Download onNext");
                    }
                });*/


        //retrofit.create(DownloadService.class)
    }

    public void getBanner(CallBackListener<HomeBannerVO> callBackListener) {
        execute(callBackListener, api().getBannerData());
    }

    public void getVersion(CallBackListener<AppInfoVO> callBackListener, @NonNull String url) {
        execute(callBackListener, api().getAppInfo(url));
    }

}
