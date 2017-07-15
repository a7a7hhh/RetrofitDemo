package com.example.winkey.retrofitdemo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.example.winkey.retrofitdemo.R;
import com.example.winkey.retrofitdemo.view.activity.base.BaseActivity;
import com.example.winkey.retrofitdemo.view.fragment.FilterFragment;
import  com.example.winkey.retrofitdemo.view.utils.Logger;


/**
 * Created by Winkey on 2017/7/15.
 */

public class FilterActivity extends BaseActivity {

    private FilterFragment mFragment;


    public static void start(Context context) {
        Intent intent = new Intent(context, FilterActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(R.id.fl_root,mFragment);
        status();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void parsentData() {
        mFragment=FilterFragment.newInstance();
    }
}
