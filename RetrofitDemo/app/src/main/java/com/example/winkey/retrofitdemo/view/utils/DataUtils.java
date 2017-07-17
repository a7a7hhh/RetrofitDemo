package com.example.winkey.retrofitdemo.view.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Winkey on 2017/7/17.
 */

public class DataUtils {

    /**
     * 从assets读取json文件
     * @param context
     * @param filePath
     * @return
     */
    public static String getJsonFromAsset(Context context, String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream is = null;
        BufferedReader bufferedReader = null;
        try {
            is = context.getAssets().open(filePath);
            bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
