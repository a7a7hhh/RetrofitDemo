package com.example.winkey.retrofitdemo.view.activity.base;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.winkey.retrofitdemo.view.utils.ToastUtils;

import butterknife.ButterKnife;

/**
 * Created by Winkey on 2017/7/15.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static ToastUtils toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        super.setContentView(getLayoutId());
        //将activity加入到AppManager堆栈中
        ButterKnife.bind(this);
        initViews();
        parsentData();
    }

    /**
     * 获取布局id
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * view相关
     */
    protected abstract void initViews();

    /**
     * 数据相关
     */
    protected abstract void parsentData();

    /**
     * 添加Fragment到布局
     *
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.add(containerViewId, fragment);
        transaction.commit();
    }

    /**
     * 弹出 toast
     *
     * @param content
     */
    /**
     * @param message
     */
    protected void showMsg(String message) {
        if (toast == null) {
            toast = new ToastUtils(BaseActivity.this);
        }
        toast.show(message);
    }

    protected void status() {
        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

}
