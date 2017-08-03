package com.example.winkey.retrofitdemo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.winkey.retrofitdemo.R;
import com.example.winkey.retrofitdemo.view.activity.base.BaseActivity;
import com.example.winkey.retrofitdemo.view.fragment.AlbumFragment;
import com.example.winkey.retrofitdemo.view.fragment.FilterFragment;

/**
 * Created by Winkey on 2017/7/24.
 */

public class AlbumActivity extends BaseActivity {

    private AlbumFragment mFragment;


    public static void start(Context context) {
        Intent intent = new Intent(context, AlbumActivity.class);

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
        mFragment= AlbumFragment.newInstance();
    }
}
