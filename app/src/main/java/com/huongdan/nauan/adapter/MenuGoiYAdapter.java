package com.huongdan.nauan.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huongdan.nauan.R;
import com.huongdan.nauan.model.MonAn;
import com.huongdan.nauan.model.ThucDonNgay;
import com.huongdan.nauan.utils.Global;
import com.huongdan.nauan.utils.MyUtils;
import com.huongdan.nauan.view.ChiTietThucDonActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuGoiYAdapter extends RecyclerView
        .Adapter<MenuGoiYAdapter
        .DataObjectHolder> {
    private List<ThucDonNgay> mDataset;
    private Context mContext;
    private Activity mActivity;
    private static final int spanCount = 4;
    private int itemHeight;
    public MenuGoiYAdapter(Activity activity, List<ThucDonNgay> dataset) {
        mActivity = activity;
        mContext = mActivity.getBaseContext();
        mDataset = dataset;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float width = displayMetrics.widthPixels/spanCount;
        int bonus =0;
        if(dataset.size()%spanCount>0){
            bonus = 1;
        }
        itemHeight = (int) (width*((dataset.size()/spanCount)+bonus)+MyUtils.convertDpToPixel(36,mContext));
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        RecyclerView rvMonAn;
        RelativeLayout rlBackground;
        Button btDetail;
        View view;

        public DataObjectHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            tvName = itemView.findViewById(R.id.tvName);
            rvMonAn = itemView.findViewById(R.id.rvMonAn);
            rlBackground = itemView.findViewById(R.id.rlBackground);
            btDetail = itemView.findViewById(R.id.btDetail);

            GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), spanCount);
            rvMonAn.setLayoutManager(layoutManager);
        }

    }


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_goi_y, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        final ThucDonNgay thucDonNgay = mDataset.get(position);
        final Set<MonAn> linkedHashSet = new HashSet<>();
        linkedHashSet.add(thucDonNgay.getBuaSang());
        for (MonAn monAn : thucDonNgay.getBuaTrua()) {
            linkedHashSet.add(monAn);
        }
        for (MonAn monAn : thucDonNgay.getBuaToi()) {
            linkedHashSet.add(monAn);
        }


        List<MonAn> monAns = new ArrayList<>();
        monAns.addAll(linkedHashSet);
        final String name = MyUtils.getThu(position);
        holder.tvName.setText(name);
        final int color = MyUtils.getColor(position);
        holder.rlBackground.setBackgroundColor(color);
        holder.tvName.setBackgroundColor(color);
        holder.rvMonAn.setAdapter(new MonAnGoiYAdapter(mActivity, monAns));


        holder.btDetail.setHeight(itemHeight);
        holder.btDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ChiTietThucDonActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Global.EXTRA_DATA_KEY, new Gson().toJson(thucDonNgay));
                intent.putExtra(Global.EXTRA_NAME_KEY, name);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

