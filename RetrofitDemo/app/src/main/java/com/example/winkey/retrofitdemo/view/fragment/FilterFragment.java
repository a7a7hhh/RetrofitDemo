package com.example.winkey.retrofitdemo.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.winkey.retrofitdemo.R;
import com.example.winkey.retrofitdemo.view.fragment.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Winkey on 2017/7/15.
 */

public class FilterFragment extends BaseFragment {

    @BindView(R.id.iv_filter)
    ImageView ivFilter;

    public static FilterFragment newInstance() {
        FilterFragment fragment = new FilterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void parsentData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
