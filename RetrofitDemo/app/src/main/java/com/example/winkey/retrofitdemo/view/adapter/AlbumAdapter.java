package com.example.winkey.retrofitdemo.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;

import com.example.winkey.retrofitdemo.R;
import com.example.winkey.retrofitdemo.view.utils.Logger;
import com.example.winkey.retrofitdemo.view.widget.recyclerview.CommonAdapter;
import com.example.winkey.retrofitdemo.view.widget.recyclerview.ViewHolder;

import java.util.List;

/**
 * Created by Winkey on 2017/7/24.
 */

public class AlbumAdapter extends CommonAdapter<Bitmap> {



    public AlbumAdapter(Context context, List datas) {
        super(context, R.layout.item_album, datas);
        Logger.debug("xwb"+datas.size());
    }

    @Override
    protected void convert(ViewHolder holder, Bitmap bitmap, int position) {

    }

}
