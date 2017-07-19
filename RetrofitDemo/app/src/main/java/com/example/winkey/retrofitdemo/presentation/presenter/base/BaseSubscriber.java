package com.example.winkey.retrofitdemo.presentation.presenter.base;

import com.example.winkey.retrofitdemo.view.utils.Logger;

import rx.Subscriber;

/**
 * Created by Winkey on 2017/7/18.
 */

public class BaseSubscriber<T> extends Subscriber<T> {

    private BasePresenter presenter;
    private CallBackListener<T> callBackListener;
    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {
        if(callBackListener!=null){
            callBackListener.callBack(t);
        }
    }

    public void setCallBackListener(CallBackListener<T> callBackListener) {
        this.callBackListener = callBackListener;
    }


    /**
     * 设置getPresenter()
     *
     * @param presenter
     * @return
     */
    protected void setPresenter(BasePresenter presenter) {
        this.presenter = presenter;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder<T> {

        private BaseSubscriber<T> subscriber;

        private Builder() {
            subscriber = new BaseSubscriber<T>();
        }

        public BaseSubscriber<T> build() {
            return subscriber;
        }

        public Builder callback(CallBackListener<T> callbackListener) {
            subscriber.setCallBackListener(callbackListener);
            return this;
        }

        public Builder presenter(BasePresenter presenter) {
            subscriber.setPresenter(presenter);
            return this;
        }
    }

}
