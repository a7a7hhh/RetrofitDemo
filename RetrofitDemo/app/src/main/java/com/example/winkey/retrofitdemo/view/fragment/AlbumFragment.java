package com.example.winkey.retrofitdemo.view.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.winkey.retrofitdemo.R;
import com.example.winkey.retrofitdemo.view.adapter.AlbumAdapter;
import com.example.winkey.retrofitdemo.view.fragment.base.BaseFragment;
import com.example.winkey.retrofitdemo.view.utils.DeviceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Winkey on 2017/7/24.
 */

public class AlbumFragment extends BaseFragment {

    @BindView(R.id.tv_back)
    ImageView tvBack;
    @BindView(R.id.tv_confirm_album)
    TextView tvConfirmAlbum;
    @BindView(R.id.rv_album)
    RecyclerView rvAlbum;

    private Context mContext;
    private AlbumAdapter mAlbumAdapter;
    private RecyclerView.ItemDecoration mItemDecoration;
    private List<Bitmap> mDatas;

    public static AlbumFragment newInstance() {
        AlbumFragment fragment = new AlbumFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, null);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void initViews(View view) {

        mItemDecoration = new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.left= DeviceUtils.dpToPx(mContext, 2);
                outRect.bottom= DeviceUtils.dpToPx(mContext, 2);
            }
        };
        rvAlbum.addItemDecoration(mItemDecoration);
        GridLayoutManager gLManager = new GridLayoutManager(mContext, 3);
        rvAlbum.setLayoutManager(gLManager);
        mDatas = new ArrayList<>();
        getAlbumPics();
        mAlbumAdapter = new AlbumAdapter(mContext, mDatas);
        rvAlbum.setAdapter(mAlbumAdapter);

    }

    private void getAlbumPics() {
        for (int i = 0;i<10;i++) {
            Observable.create(new Observable.OnSubscribe<Bitmap>() {
                @Override
                public void call(Subscriber<? super Bitmap> subscriber) {
                    try {
                        Bitmap bitmap = Glide.with(mContext).load(R.mipmap.ic_launcher).asBitmap().into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
                        subscriber.onNext(bitmap);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Action1<Bitmap>() {
                @Override
                public void call(Bitmap bitmap) {
                    mDatas.add(bitmap);
                    mAlbumAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void parsentData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @OnClick({R.id.tv_back, R.id.tv_confirm_album})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                getActivity().finish();
                break;
            case R.id.tv_confirm_album:
                break;
        }
    }
}
