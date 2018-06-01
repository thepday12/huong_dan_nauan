package com.huongdan.nauan.view.menu_activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.huongdan.nauan.R;
import com.huongdan.nauan.adapter.MenuHomeAdapter;
import com.huongdan.nauan.model.MenuMonAn;
import com.huongdan.nauan.utils.Global;
import com.huongdan.nauan.utils.animation.view_recycler.SlideInUpAnimator;
import com.huongdan.nauan.view.ListMonAnActivity;

public class MenuHomeFragment extends Fragment {

    private TextView tvTitle;
    private RecyclerView rvMenuMonAn;
    private Context mContext;
    private Button btCachCheBien,btQuocGia;
    private ImageButton btSearch;
    private EditText etSearch;
    private MenuHomeAdapter menuCachCHeBienAdapter,menuQuocGiaAdapter;

    public static MenuHomeFragment newInstance() {
        MenuHomeFragment fragment = new MenuHomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_menu_home, container, false);
        mContext = rootView.getContext();

        tvTitle = rootView.findViewById(R.id.tvTitle);
        rvMenuMonAn = rootView.findViewById(R.id.rvMenuMonAn);
        btCachCheBien = rootView.findViewById(R.id.btCachCheBien);
        btQuocGia = rootView.findViewById(R.id.btQuocGia);
        btSearch = rootView.findViewById(R.id.btSearch);
        etSearch = rootView.findViewById(R.id.etSearch);

        configView();
        initListener();
        return rootView;
    }

    private void configView(){
        //Doi font cho tvTitle
        tvTitle.setTypeface(Typeface.createFromAsset(mContext.getAssets(),"fonts/scripti.ttf"));
        //Thiet lap che do hien thi dang list cho Recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rvMenuMonAn.setLayoutManager(layoutManager);
        menuCachCHeBienAdapter = new MenuHomeAdapter(getActivity(),MenuMonAn.getDanhSachLoaiMonAn());
        menuQuocGiaAdapter = new MenuHomeAdapter(getActivity(),MenuMonAn.getDanhSachQuocGia());
        //Thiet lap hieu ung cho Recyclerview
        rvMenuMonAn.setItemAnimator(new SlideInUpAnimator());
        selectCachCheBien(true);

    }

    private void initListener(){
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etSearch.getText().toString();
                if(text!=null && !text.isEmpty()){
                    Intent intent = new Intent(mContext, ListMonAnActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(Global.EXTRA_NAME_KEY, text);
                    mContext.startActivity(intent);
                }
            }
        });

        btCachCheBien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectCachCheBien(true);
            }
        });

        btQuocGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectCachCheBien(false);
            }
        });
    }
    private void selectCachCheBien(boolean isSelect) {
        if (isSelect) {
            btCachCheBien.setTextColor(Color.WHITE);
            btCachCheBien.setBackgroundColor(Color.DKGRAY);
            btQuocGia.setTextColor(Color.BLACK);
            btQuocGia.setBackgroundColor(Color.WHITE);
            rvMenuMonAn.setAdapter(menuCachCHeBienAdapter);
        } else {
            btQuocGia.setTextColor(Color.WHITE);
            btQuocGia.setBackgroundColor(Color.DKGRAY);
            btCachCheBien.setTextColor(Color.BLACK);
            btCachCheBien.setBackgroundColor(Color.WHITE);
            rvMenuMonAn.setAdapter(menuQuocGiaAdapter);
        }
    }


}

