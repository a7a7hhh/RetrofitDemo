package com.example.winkey.retrofitdemo.data.api.download;

import android.os.Looper;
import android.os.Message;

/**
 * Created by Winkey on 2017/7/19.
 */

//抽象类继承抽象类，可以只重写部分抽象方法，
public abstract class DownloadProgressHandler extends ProgressHandler {
    private static final int DOWNLOAD_PROGRESS = 0x0001;

    protected ResponseHandler mHandler = new ResponseHandler(this, Looper.getMainLooper());

    @Override
    public void sendMessage(Download download) {
        mHandler.obtainMessage(DOWNLOAD_PROGRESS,download).sendToTarget();
    }

    @Override
    public void handleMessage(Message message) {
        switch (message.what){
            case DOWNLOAD_PROGRESS:
                Download progressBean = (Download) message.obj;
                onProgress(progressBean.getProgress());

        }
    }

}
