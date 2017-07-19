package com.example.winkey.retrofitdemo.data.api.download;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by Winkey on 2017/7/19.
 */

public abstract class ProgressHandler {
    public abstract void sendMessage(Download download);
    public abstract void handleMessage(Message message);
    public abstract void onProgress(long progress);

    protected class ResponseHandler extends Handler {
        private ProgressHandler mHandler;

        public ResponseHandler(ProgressHandler mHandler, Looper looper) {
            super(looper);
            this.mHandler = mHandler;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mHandler.handleMessage(msg);
        }
    }
}
