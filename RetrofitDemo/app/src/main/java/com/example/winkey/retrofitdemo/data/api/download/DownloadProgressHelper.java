package com.example.winkey.retrofitdemo.data.api.download;

import com.example.winkey.retrofitdemo.view.utils.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by Winkey on 2017/7/18.
 */

public class DownloadProgressHelper {
    public static Download download =  new Download();


    public static OkHttpClient.Builder addClient(OkHttpClient.Builder builder) {
        if (builder == null) {
            return builder = new OkHttpClient.Builder();
        }

        final DownloadProgressListener downloadProgressListener = new DownloadProgressListener() {
            @Override
            public void update(long bytesRead, long contentLength, boolean done) {
            }

        };

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
}
