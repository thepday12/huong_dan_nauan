package com.huongdan.nauan.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.huongdan.nauan.R;
import com.huongdan.nauan.model.MenuMonAn;
import com.huongdan.nauan.utils.Global;
import com.huongdan.nauan.utils.MyUtils;
import com.huongdan.nauan.view.ListMonAnActivity;

import java.util.List;

public class MenuHomeAdapter extends RecyclerView
        .Adapter<MenuHomeAdapter
        .DataObjectHolder> {
    private List<MenuMonAn> mDataset;
    private Context mContext;
    private Activity mActivity;

    public MenuHomeAdapter(Activity activity, List<MenuMonAn> dataset) {
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
                .inflate(R.layout.item_menu_mon_an, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        final MenuMonAn menuMonAn = mDataset.get(position);
        final String name = menuMonAn.getName();
        final String imageName = menuMonAn.getImage();
        holder.tvName.setText(name);
        Glide.with(mContext)
                .load(MyUtils.getImage(mContext, imageName))
                .into(holder.ivIcon);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, MailDetailActivity.class);
//                intent.putExtra("MAIL_ID", menuMonAn.getId());
//                mContext.startActivity(intent);
                Intent intent = new Intent(mContext, ListMonAnActivity.class);
//                ActivityOptionsCompat options = ActivityOptionsCompat.
//                        makeSceneTransitionAnimation(mActivity,
//                                holder.ivIcon,
//                                ViewCompat.getTransitionName(holder.ivIcon));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Global.EXTRA_NAME_KEY, name);
                intent.putExtra(Global.EXTRA_IMAGE_KEY, imageName);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

    }

    public void addItem(MenuMonAn dataObj, int index) {
        mDataset.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

