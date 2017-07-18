package com.example.winkey.retrofitdemo.data.api.download;

/**
 * Created by Winkey on 2017/7/18.
 */

public interface DownloadProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}
