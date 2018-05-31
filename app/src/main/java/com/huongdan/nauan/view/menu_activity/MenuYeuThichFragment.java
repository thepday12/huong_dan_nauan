package com.huongdan.nauan.view.menu_activity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.huongdan.nauan.R;
import com.huongdan.nauan.adapter.MenuHomeAdapter;
import com.huongdan.nauan.adapter.MonAnAdapter;
import com.huongdan.nauan.model.MenuMonAn;
import com.huongdan.nauan.model.MonAn;
import com.huongdan.nauan.utils.MySQLiteDatabase;
import com.huongdan.nauan.utils.animation.view_recycler.SlideInUpAnimator;
import com.huongdan.nauan.view.ListMonAnActivity;

import java.util.List;

public class MenuYeuThichFragment extends Fragment {

    private TextView tvEmptyData;
    private ProgressBar pbLoading;
    private RecyclerView rvMonAn;
    private Context mContext;
    private MySQLiteDatabase mySQLiteDatabase;

    public static MenuYeuThichFragment newInstance() {
        MenuYeuThichFragment fragment = new MenuYeuThichFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_menu_yeu_thich, container, false);
        mContext = rootView.getContext();
        tvEmptyData = rootView.findViewById(R.id.tvEmptyData);
        rvMonAn = rootView.findViewById(R.id.rvMonAn);
        pbLoading = rootView.findViewById(R.id.pbLoading);

        configView();

        return rootView;
    }



    private void configView(){
        mySQLiteDatabase = new MySQLiteDatabase(mContext);

        //Thiet lap che do hien thi dang list cho Recyclerview
        GridLayoutManager layoutManager = new GridLayoutManager(mContext,2);
        rvMonAn.setLayoutManager(layoutManager);
        //Thiet lap hieu ung cho Recyclerview
        rvMonAn.setItemAnimator(new SlideInUpAnimator());
    }

    @Override
    public void onResume() {
        new GetListMonAnYeuThich().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        super.onResume();
    }

    class GetListMonAnYeuThich extends AsyncTask<Void,Void,List<MonAn>> {

        @Override
        protected List<MonAn> doInBackground(Void... voids) {
            return mySQLiteDatabase.getListMonAnYeuThich();
        }

        @Override
        protected void onPostExecute(List<MonAn> monAns) {
            pbLoading.setVisibility(View.INVISIBLE);
            if(monAns.size()>0){
                rvMonAn.setVisibility(View.VISIBLE);
                tvEmptyData.setVisibility(View.INVISIBLE);
                rvMonAn.setAdapter(new MonAnAdapter(getActivity(),monAns));
            }else{
                tvEmptyData.setVisibility(View.VISIBLE);
                rvMonAn.setVisibility(View.INVISIBLE);

            }
            super.onPostExecute(monAns);
        }
    }

}

