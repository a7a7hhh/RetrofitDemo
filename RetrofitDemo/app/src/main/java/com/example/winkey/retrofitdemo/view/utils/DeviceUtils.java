package com.example.winkey.retrofitdemo.view.utils;

import android.content.Context;

/**
 * Created by Winkey on 2017/7/17.
 */

public class DeviceUtils {


    public static int dpToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
