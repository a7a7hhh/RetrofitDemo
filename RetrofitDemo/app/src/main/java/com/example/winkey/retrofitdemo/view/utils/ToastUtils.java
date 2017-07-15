package com.example.winkey.retrofitdemo.view.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Winkey on 2017/7/15.
 */

public class ToastUtils {

    private Context context;
    private Toast toast = null;
    /**
     * toast默认时长
     */
    private final int TIME_TOAST = 5000;

    public ToastUtils(Context context) {
        this.context = context;
    }

    /**
     * 显示toast
     * @param text
     */
    public void show(String text) {
        show(text, TIME_TOAST);
    }

    /**
     * 显示自定义时长的toast
     * @param text
     * @param showLength
     */
    public void show(String text, int showLength) {
        if (toast == null) {
            toast = Toast.makeText(context, text, showLength);
        } else {
            toast.setText(text);
            toast.setDuration(showLength);
        }
        toast.show();
    }
}
