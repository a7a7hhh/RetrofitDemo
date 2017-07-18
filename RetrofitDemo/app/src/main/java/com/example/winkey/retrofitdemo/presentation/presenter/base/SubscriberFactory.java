package com.example.winkey.retrofitdemo.presentation.presenter.base;

import com.example.winkey.retrofitdemo.view.utils.Logger;

/**
 * Created by Winkey on 2017/7/18.
 */

public class SubscriberFactory<T> {

    private static volatile SubscriberFactory sInstance;

    private SubscriberFactory(){

    }

    public static SubscriberFactory getInstance(){
        if(sInstance==null){
            synchronized (SubscriberFactory.class){
                if(sInstance==null){
                    sInstance=new SubscriberFactory();
                }
            }
        }
        return sInstance;
    }

    public BaseSubscriber createSubscribe(CallBackListener<T> listener, BasePresenter presenter){
        BaseSubscriber<T> subscriber = BaseSubscriber.builder()
                .callback(listener)
                .presenter(presenter)
                .build();
        return subscriber;
    }
}
