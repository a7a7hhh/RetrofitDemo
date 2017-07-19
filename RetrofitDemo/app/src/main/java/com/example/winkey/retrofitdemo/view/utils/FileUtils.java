package com.example.winkey.retrofitdemo.view.utils;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by Winkey on 2017/7/19.
 */

public class FileUtils {



    public static File getAppDataDir(Context context) {
        final String appDir = "/Android/data/" + context.getPackageName();
        File file = new File(Environment.getExternalStorageDirectory()
                .getPath() + appDir);
        return file;
    }

    public static File getDownloadDir(Context context) {
        File file = new File(getAppDataDir(context), "download");
        return file;
    }

    public static String getDownloadDirPath(Context context) {
        return getDownloadDir(context).getAbsolutePath();
    }

    public static File writeResponseBodyToDisk(ResponseBody body, String fileName,Context context) {
        File file = null;
        try {
            String path = FileUtils.getDownloadDirPath(context);
            File files = new File(path);
            if (!files.exists()) {
                if(!files.mkdirs()){
                    Logger.debug("mkdir fail");
                }
            }
            file = new File(path, fileName);
            InputStream is = body.byteStream();
            FileOutputStream fos = null;
            BufferedInputStream bis = null;
            try {
                fos = new FileOutputStream(file);
                bis = new BufferedInputStream(is);
                byte[] buffer = new byte[4096];
                int len;
                while ((len = bis.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                fos.flush();

            } catch (Exception e) {
                e.printStackTrace();
                return file;
            } finally {
                fos.close();
                bis.close();
                is.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return file;
        }

        return file;
    }

}
