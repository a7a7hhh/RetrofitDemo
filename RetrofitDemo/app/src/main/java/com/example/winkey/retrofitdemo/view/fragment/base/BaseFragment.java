package com.example.winkey.retrofitdemo.view.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.winkey.retrofitdemo.view.utils.ToastUtils;

/**
 * Created by Winkey on 2017/7/15.
 */

public abstract class BaseFragment extends Fragment {
    private static ToastUtils toast;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        parsentData();
    }

    public abstract void initViews(View view);

    public abstract void parsentData();


    /**
     * @param message
     */
    protected void showMsg(String message) {
        if (toast == null) {
            toast = new ToastUtils(getActivity());
        }
        toast.show(message);
    }

    /**
     * 添加Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        if (!fragment.isAdded()) {
            FragmentTransaction transaction = this.getChildFragmentManager().beginTransaction();
            transaction.add(containerViewId, fragment);
            transaction.commit();
        }
    }
}
