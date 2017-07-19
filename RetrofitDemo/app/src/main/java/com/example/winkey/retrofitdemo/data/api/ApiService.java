package com.example.winkey.retrofitdemo.data.api;

import com.example.winkey.retrofitdemo.data.api.download.Download;
import com.example.winkey.retrofitdemo.data.model.vo.AppInfoVO;
import com.example.winkey.retrofitdemo.data.model.vo.HomeBannerVO;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Winkey on 2017/7/7.
 */

public interface ApiService {

    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);

    /**
     * 获取Banner数据
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("App/Banner/Banner-Setting.json")
    Observable<HomeBannerVO> getBannerData();

    /**
     * 获取APP信息
     *
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET
    Observable<AppInfoVO> getAppInfo(@Url String url);
}
