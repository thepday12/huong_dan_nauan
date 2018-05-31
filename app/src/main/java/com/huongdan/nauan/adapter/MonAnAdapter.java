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

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.google.gson.Gson;
import com.huongdan.nauan.R;
import com.huongdan.nauan.model.MonAn;
import com.huongdan.nauan.utils.Global;
import com.huongdan.nauan.utils.MyUtils;
import com.huongdan.nauan.view.ChiTietMonAnActivity;
import com.huongdan.nauan.view.ListMonAnActivity;

import java.util.List;

public class MonAnAdapter extends RecyclerView
        .Adapter<MonAnAdapter
        .DataObjectHolder> {
    private List<MonAn> mDataset;
    private Context mContext;
    private Activity mActivity;

    public MonAnAdapter(Activity activity, List<MonAn> dataset) {
        mActivity = activity;
        this.mContext = mActivity.getBaseContext();
        mDataset = dataset;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivIcon;
        View view;

        public DataObjectHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            tvName = itemView.findViewById(R.id.tvName);
            ivIcon = itemView.findViewById(R.id.ivIcon);

        }

    }


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_mon_an, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        final MonAn monAn = mDataset.get(position);
        final String name = monAn.getTen();
        final String imageName = monAn.getHinhAnh();

        holder.tvName.setText(name);

        MyUtils.loadImage(mContext,holder.ivIcon,imageName);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ChiTietMonAnActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Global.EXTRA_ID_KEY, monAn.getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

