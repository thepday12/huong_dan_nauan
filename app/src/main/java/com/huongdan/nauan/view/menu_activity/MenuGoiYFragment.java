package com.huongdan.nauan.view.menu_activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.huongdan.nauan.R;
import com.huongdan.nauan.adapter.MenuGoiYAdapter;
import com.huongdan.nauan.adapter.MenuHomeAdapter;
import com.huongdan.nauan.adapter.MonAnAdapter;
import com.huongdan.nauan.model.MenuMonAn;
import com.huongdan.nauan.model.MonAn;
import com.huongdan.nauan.model.ThucDonNgay;
import com.huongdan.nauan.utils.MySQLiteDatabase;
import com.huongdan.nauan.utils.animation.view_recycler.SlideInUpAnimator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MenuGoiYFragment extends Fragment {

    private RecyclerView rvNgayTrongTuan;
    private ProgressBar pbLoading;
    private Context mContext;
    private MySQLiteDatabase mySQLiteDatabase;

    public static MenuGoiYFragment newInstance() {
        MenuGoiYFragment fragment = new MenuGoiYFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_menu_goi_y, container, false);
        mContext = rootView.getContext();

        rvNgayTrongTuan = rootView.findViewById(R.id.rvNgayTrongTuan);
        pbLoading = rootView.findViewById(R.id.pbLoading);

        configView();
        new GetListMonAnYeuThich().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        return rootView;
    }


    private void configView() {
        mySQLiteDatabase = new MySQLiteDatabase(mContext);
        //Thiet lap che do hien thi dang list cho Recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rvNgayTrongTuan.setLayoutManager(layoutManager);

        //Thiet lap hieu ung cho Recyclerview
        rvNgayTrongTuan.setItemAnimator(new SlideInUpAnimator());
    }

    class GetListMonAnYeuThich extends AsyncTask<Void, Void, List<ThucDonNgay>> {

        @Override
        protected List<ThucDonNgay> doInBackground(Void... voids) {
            List<ThucDonNgay> result = new ArrayList<>();
            List<MonAn> monAnSang = mySQLiteDatabase.getListMonAnSang();
            List<MonAn> monAnTruaToi = mySQLiteDatabase.getListMonAnTruaToi();
            List<MonAn> monRau = mySQLiteDatabase.getListMonRau();
            int size = monAnSang.size();
            Random random = new Random();
            for (int i = 0; i < 7; i++) {//Lay Ngau nhien 1 mon cho buoi sang
                boolean isSpecial = i > 4;
                result.add(i, new ThucDonNgay(monAnSang.get(random.nextInt(size)), createThucDonTruaToi(random, monRau, monAnTruaToi, isSpecial), createThucDonTruaToi(random, monRau, monAnTruaToi, isSpecial)));
            }

            return result;
        }

        @Override
        protected void onPostExecute(List<ThucDonNgay> monAns) {
            pbLoading.setVisibility(View.INVISIBLE);
//            if (monAns.size() > 0) {
//                rvMonAn.setVisibility(View.VISIBLE);
//                tvEmptyData.setVisibility(View.INVISIBLE);
//                rvMonAn.setAdapter(new MonAnAdapter(getActivity(), monAns));
//            } else {
//                tvEmptyData.setVisibility(View.VISIBLE);
//                rvMonAn.setVisibility(View.INVISIBLE);
//
//            }
            rvNgayTrongTuan.setAdapter(new MenuGoiYAdapter(getActivity(), monAns));
            super.onPostExecute(monAns);
        }
    }


    private List<MonAn> createThucDonTruaToi(Random random, List<MonAn> monRau, List<MonAn> monAnTruaToi, boolean isSpecial) {
        int sizeTruaToi = monAnTruaToi.size();
        int sizeRau = monRau.size();
        List<MonAn> monAns = new ArrayList<>();
        if (sizeRau > 0) {
            monAns.add(monRau.get(random.nextInt(sizeRau)));//1 mon rau
        }
        if (sizeTruaToi > 0) {

            if (sizeTruaToi == 2) {
                monAns.add(monAnTruaToi.get(0));
                monAns.add(monAnTruaToi.get(1));
            } else if (sizeTruaToi > 2) {
                int randomPosition = random.nextInt(sizeTruaToi);
                monAns.add(monAnTruaToi.get(randomPosition));
                Collections.shuffle(monAnTruaToi);
                monAns.add(monAnTruaToi.get(0));
                monAns.add(monAnTruaToi.get(1));
                if (isSpecial) {
                    monAns.add(monAnTruaToi.get(2));
                }
            } else {
                monAns.add(monAnTruaToi.get(0));
            }

        }
        return monAns;
    }
}

