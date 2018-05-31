package com.huongdan.nauan.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huongdan.nauan.R;

import java.util.List;

public class NguyenLieuAdapter extends RecyclerView
        .Adapter<NguyenLieuAdapter
        .DataObjectHolder> {
    private List<String> mDataset;
    private Context mContext;
    private Activity mActivity;

    public NguyenLieuAdapter(Activity activity, List<String> dataset) {
        mActivity = activity;
        this.mContext = mActivity.getBaseContext();
        mDataset = dataset;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        View view;

        public DataObjectHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            tvName = itemView.findViewById(R.id.tvName);

        }

    }


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_nguyen_lieu, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {

        holder.tvName.setText((position+1) +"- "+mDataset.get(position));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

