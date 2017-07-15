package com.example.winkey.retrofitdemo.view.activity.base;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Winkey on 2017/7/15.
 */

public abstract class ToolbarActivity extends BaseActivity {

    protected Toolbar toolbar;
    protected TextView tbTitle;

    @Override
    protected void initViews() {
        //status();
        setupToolbar();
    }

    protected void setupToolbar(){
        if(getToolbarId()!=0){
            toolbar = (Toolbar)findViewById(getToolbarId());
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        if(getTbTitleId()!=0){
            tbTitle = (TextView)findViewById(getTbTitleId());
            if(!TextUtils.isEmpty(getTitleStr()))
                tbTitle.setText(getTitleStr());
        }


    }

    protected abstract int getToolbarId();
    protected abstract int getTbTitleId();
    protected abstract String getTitleStr();
}
