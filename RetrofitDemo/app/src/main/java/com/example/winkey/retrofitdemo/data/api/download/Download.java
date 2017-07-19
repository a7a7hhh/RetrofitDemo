package com.example.winkey.retrofitdemo.data.api.download;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Winkey on 2017/7/18.
 */

public class Download{

    private int progress;
    private long currentFileSize;
    private long totalFileSize;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public long getCurrentFileSize() {
        return currentFileSize;
    }

    public void setCurrentFileSize(long currentFileSize) {
        this.currentFileSize = currentFileSize;
    }

    public long getTotalFileSize() {
        return totalFileSize;
    }

    public void setTotalFileSize(long totalFileSize) {
        this.totalFileSize = totalFileSize;
    }

}
