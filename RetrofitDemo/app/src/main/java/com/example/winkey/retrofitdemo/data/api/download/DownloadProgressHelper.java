package com.example.winkey.retrofitdemo.data.api.download;

import com.example.winkey.retrofitdemo.view.utils.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Winkey on 2017/7/18.
 */

public class DownloadProgressHelper {

    public static Download mDownload  = new Download();

    public static ProgressHandler mProgressHandler;


    public static OkHttpClient.Builder addClient(OkHttpClient.Builder builder) {
        if (builder == null) {
            return builder = new OkHttpClient.Builder();
        }

        final DownloadProgressListener downloadProgressListener = new DownloadProgressListener() {
            @Override
            public void update(long bytesRead, long contentLength, boolean done) {
                if(mDownload == null){
                    return;
                }
                mDownload.setCurrentFileSize(bytesRead);
                mDownload.setTotalFileSize(contentLength);
                mDownload.setProgress((int) ((bytesRead * 100) / contentLength));

                mProgressHandler.sendMessage(mDownload);
            }

        };

        //添加网络拦截器
        builder.addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder().body(
                        new DownloadProgressResponseBody(originalResponse.body(), downloadProgressListener)).build();
            }
        });
        return builder;
    }

    public static void setDownloadStates(ProgressHandler progressHandler){
        mProgressHandler = progressHandler;
    }
}
