package com.huongdan.nauan.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.huongdan.nauan.R;
import com.huongdan.nauan.utils.Global;
import com.huongdan.nauan.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

public class ThucHienAdapter extends RecyclerView
        .Adapter<ThucHienAdapter
        .DataObjectHolder> {
    private List<String> mDataset;
    private Context mContext;
    private Activity mActivity;

    public ThucHienAdapter(Activity activity, List<String> dataset) {
        mActivity = activity;
        this.mContext = mActivity.getBaseContext();
        int step = 0;
        mDataset = new ArrayList<>();
        for (String item : dataset) {
            if (item.startsWith("http://")) {
                mDataset.add(item);
            } else {
                step++;
                mDataset.add("<b>Bước " + step + ": </b>" + item);
            }
        }
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
                .inflate(R.layout.item_thuc_hien, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        final String description = mDataset.get(position);
        if (description.startsWith("http://")) {
            holder.tvName.setVisibility(View.GONE);
            holder.ivIcon.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load(description)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            holder.ivIcon.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(holder.ivIcon);
        } else {
            holder.ivIcon.setVisibility(View.GONE);
            holder.tvName.setVisibility(View.VISIBLE);

            holder.tvName.setText(Html.fromHtml(description));
        }

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

