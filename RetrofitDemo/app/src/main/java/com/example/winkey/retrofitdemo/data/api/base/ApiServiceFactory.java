package com.example.winkey.retrofitdemo.data.api.base;

import java.util.concurrent.TimeUnit;

import com.example.winkey.retrofitdemo.data.api.ApiService;
import com.example.winkey.retrofitdemo.data.api.download.DownloadProgressHelper;
import com.example.winkey.retrofitdemo.data.api.download.DownloadProgressInterceptor;
import com.example.winkey.retrofitdemo.data.api.download.DownloadProgressListener;
import com.example.winkey.retrofitdemo.view.utils.Logger;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Winkey on 2017/7/7.
 */

public class ApiServiceFactory {

    private static ApiServiceFactory sInstance;

    private ApiServiceFactory() {
    }

    public static ApiServiceFactory getInstance() {
        if (sInstance == null) {
            synchronized (ApiServiceFactory.class) {
                if (sInstance == null) {
                    sInstance = new ApiServiceFactory();
                }
            }
        }
        return sInstance;
    }

    /**
     * @param baseUrl
     * @return
     */
    public ApiService provideApiService(String baseUrl) {
        OkHttpClient.Builder client = provideOkhttpClient();
        Retrofit retrofit = provideRestAdapter(client, baseUrl);
        return retrofit.create(ApiService.class);
    }

    /**
     * 提供OkhttpClient
     * @return
     */
    private OkHttpClient.Builder provideOkhttpClient() {

        Logger.debug("提供OkhttpClient");
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        client.readTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        //ProgressHelper.addClient(client);
        //client.addInterceptor(new TokenInterceptor());
//        File cacheDir = new File(App.getInstance().getCacheDir(), "response");
//        //缓存的最大尺寸10m
//        Cache cache = new Cache(cacheDir, 1024 * 1024 * 10);
//        client.cache(cache);
//        client.addNetworkInterceptor(netWorkInterceptor());
        return client;
    }

    /**
     * @param baseUrl
     * @return
     */
    public ApiService provideDownloadService(String baseUrl) {
        OkHttpClient.Builder client = provideDownloadClient();
        Retrofit retrofit = provideRestAdapter(client, baseUrl);
        return retrofit.create(ApiService.class);
    }


    /**
     * 提供Okhttp
     * @return
     */
    private OkHttpClient.Builder provideDownloadClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        client.readTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        DownloadProgressHelper.addClient(client);
        //ProgressHelper.addClient(client);
        //client.addInterceptor(new TokenInterceptor());
//        File cacheDir = new File(App.getInstance().getCacheDir(), "response");
//        //缓存的最大尺寸10m
//        Cache cache = new Cache(cacheDir, 1024 * 1024 * 10);
//        client.cache(cache);
//        client.addNetworkInterceptor(netWorkInterceptor());
        return client;
    }

    /**
     * 提供Retrofit
     *
     * @param client
     * @param baseUrl
     * @return
     */
    private Retrofit provideRestAdapter(OkHttpClient.Builder client, String baseUrl) {
        Logger.debug("提供Retrofit");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(client.build())
                .build();
        return retrofit;
    }
}
