package com.huongdan.nauan.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huongdan.nauan.R;
import com.huongdan.nauan.model.MonAn;
import com.huongdan.nauan.utils.Global;
import com.huongdan.nauan.utils.MyUtils;
import com.huongdan.nauan.view.ChiTietMonAnActivity;

import java.util.List;

public class MonAnGoiYAdapter extends RecyclerView
        .Adapter<MonAnGoiYAdapter
        .DataObjectHolder> {
    private List<MonAn> mDataset;
    private Context mContext;
    private Activity mActivity;

    public MonAnGoiYAdapter(Activity activity, List<MonAn> dataset) {
        mActivity = activity;
        this.mContext = mActivity.getBaseContext();
        mDataset = dataset;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        View view;

        public DataObjectHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ivIcon = itemView.findViewById(R.id.ivIcon);

        }

    }


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_mon_an_goi_y, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        final MonAn monAn = mDataset.get(position);
        final String imageName = monAn.getHinhAnh();

        MyUtils.loadImage(mContext,holder.ivIcon,imageName);


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

