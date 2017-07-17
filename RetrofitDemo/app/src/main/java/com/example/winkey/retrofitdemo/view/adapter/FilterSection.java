package com.example.winkey.retrofitdemo.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.winkey.retrofitdemo.R;
import com.example.winkey.retrofitdemo.data.model.vo.FilterVO;
import com.example.winkey.retrofitdemo.view.utils.Logger;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by Winkey on 2017/7/17.
 */

public class FilterSection extends StatelessSection {


    private Context mContext;
    private String mFilterType;
    private List<FilterVO.SectionsBean.ValuesBean> mFilterContents;

    public FilterSection(List<FilterVO.SectionsBean.ValuesBean> filterContents, String filterType,  Context context) {
        super(R.layout.layout_filter_header, R.layout.layout_filter_footer, R.layout.layout_filter_item);
        mContext = context;
        this.mFilterContents = filterContents;
        this.mFilterType =filterType;
    }

    @Override
    public int getContentItemsTotal() {
        return mFilterContents.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.tvFilterElement.setText(mFilterContents.get(position).name);
        itemViewHolder.tvFilterElement.setSelected(mFilterContents.get(position).isSelected);
        itemViewHolder.tvFilterElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isSelected()) {
                    mFilterContents.get(position).isSelected = false;
                    //FilterHelper.checkItem(false, itemViewHolder.tvFilterContent);
                } else {
                    mFilterContents.get(position).isSelected = true;
                   // FilterHelper.checkItem(true, itemViewHolder.tvFilterContent);
                }
                itemViewHolder.tvFilterElement.setSelected(mFilterContents.get(position).isSelected);}
        });
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        headerViewHolder.tvFilterType.setText(mFilterType);
    }

    @Override
    public RecyclerView.ViewHolder getFooterViewHolder(View view) {
        return new FooterViewHolder(view);
    }

    @Override
    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindFooterViewHolder(holder);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView tvFilterElement;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvFilterElement = (TextView) itemView.findViewById(R.id.tv_filter_element);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{

        private TextView tvFilterType;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            tvFilterType = (TextView) itemView.findViewById(R.id.tv_filter_type);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
