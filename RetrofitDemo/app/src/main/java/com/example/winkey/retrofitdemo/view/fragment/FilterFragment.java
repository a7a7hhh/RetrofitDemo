package com.example.winkey.retrofitdemo.view.fragment;


import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.example.winkey.retrofitdemo.R;
import com.example.winkey.retrofitdemo.data.model.vo.FilterVO;
import com.example.winkey.retrofitdemo.view.adapter.FilterSection;
import com.example.winkey.retrofitdemo.view.fragment.base.BaseFragment;
import com.example.winkey.retrofitdemo.view.utils.DataUtils;
import com.example.winkey.retrofitdemo.view.utils.DeviceUtils;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Winkey on 2017/7/15.
 */

public class FilterFragment extends BaseFragment {

    @BindView(R.id.iv_filter)
    ImageView ivFilter;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    private Context mContext;
    private PopupWindow mPopupWindow;

    private RecyclerView rvFilter;
    private SectionedRecyclerViewAdapter mSectionAdapter;
    private RecyclerView.ItemDecoration mItemDecoration;

    private List<FilterVO.SectionsBean> mFilterDatas;

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

        getFilterData();
        initPopupWindow();

    }

    @Override
    public void parsentData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.iv_back, R.id.iv_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.iv_filter:
                backgroundAlpha(0.5f);
                mPopupWindow.showAtLocation(ivFilter, Gravity.RIGHT, 0, 0);
                break;
        }
    }

    private void initPopupWindow(){
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_popup_filter, null);
        rvFilter = (RecyclerView) view.findViewById(R.id.rv_filter);

        mItemDecoration = new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.left = DeviceUtils.dpToPx(mContext, 10);
            }
        };
        rvFilter.removeItemDecoration(mItemDecoration);
        rvFilter.addItemDecoration(mItemDecoration);

        mPopupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setAnimationStyle(R.style.RightFade);
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        mPopupWindow.setBackgroundDrawable(dw);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });

        //设定rvFilter的显示流为Grid显示，
        GridLayoutManager gLManager = new GridLayoutManager(mContext, 3);
        gLManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mSectionAdapter.getSectionItemViewType(position)) {
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                        return 3;
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER:
                        return 3;
                    default:
                        return 1;
                }
            }
        });
        rvFilter.setLayoutManager(gLManager);
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(lp);
    }

    public void getFilterData() {
        Observable.create(new Observable.OnSubscribe<FilterVO>() {
            @Override
            public void call(Subscriber<? super FilterVO> subscriber) {
                String json = DataUtils.getJsonFromAsset(mContext, "Filter.json");
                Gson gson = new Gson();
                FilterVO filterVO = gson.fromJson(json, FilterVO.class);
                subscriber.onNext(filterVO);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<FilterVO>() {
            @Override
            public void call(FilterVO filterVO) {
                mSectionAdapter = new SectionedRecyclerViewAdapter();
                mFilterDatas = filterVO.Sections;
                for (FilterVO.SectionsBean sectionBeans : filterVO.Sections) {
                        mSectionAdapter.addSection(new FilterSection(sectionBeans.values, sectionBeans.name, mContext));
                }
                rvFilter.setAdapter(mSectionAdapter);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
