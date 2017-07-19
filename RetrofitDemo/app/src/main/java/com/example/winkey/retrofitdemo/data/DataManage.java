package com.example.winkey.retrofitdemo.data;

import com.example.winkey.retrofitdemo.view.utils.FileUtils;
import com.example.winkey.retrofitdemo.view.utils.Logger;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by xupg on 2017/3/29.
 */

public class DataManage {

    private static DataManage sInstance=null;

    private DataManage(){

    }

    public static DataManage getInstance(){
        if(sInstance==null){
            synchronized (DataManage.class){
                if(sInstance==null){
                    sInstance=new DataManage();
                }
            }
        }
        return sInstance;
    }

    public void execute(Subscriber subscriber, Observable<?> observable){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void executeDownload(Action1<ResponseBody> action,Subscriber subscriber, Observable<ResponseBody> observable){
        observable.subscribeOn(Schedulers.io())
                .doOnNext(action)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
