package com.huongdan.nauan.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huongdan.nauan.R;
import com.huongdan.nauan.adapter.MenuHomeAdapter;
import com.huongdan.nauan.adapter.NguyenLieuAdapter;
import com.huongdan.nauan.adapter.ThucHienAdapter;
import com.huongdan.nauan.model.MenuMonAn;
import com.huongdan.nauan.model.MonAn;
import com.huongdan.nauan.utils.Global;
import com.huongdan.nauan.utils.MySQLiteDatabase;
import com.huongdan.nauan.utils.MyUtils;
import com.huongdan.nauan.utils.animation.view_recycler.SlideInUpAnimator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChiTietMonAnActivity extends BaseActivity {

    private ImageView ivIcon;
    private ImageButton btYeuThich;
    private Button btXemVideo, btNguyenLieu, btCheBien;
    private RecyclerView rvData;
    private MonAn mMonAn;
    private MySQLiteDatabase mySQLiteDatabase;
    private NguyenLieuAdapter nguyenLieuAdapter;
    private ThucHienAdapter thucHienAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public int myView() {
        return R.layout.activity_chi_tiet_mon_an;
    }

    @Override
    public void findView() {
        ivIcon = findViewById(R.id.ivIcon);
        btYeuThich = findViewById(R.id.btYeuThich);
        btXemVideo = findViewById(R.id.btXemVideo);
        btNguyenLieu = findViewById(R.id.btNguyenLieu);
        btCheBien = findViewById(R.id.btCheBien);
        rvData = findViewById(R.id.rvData);
    }

    @Override
    public void configView() {
        mySQLiteDatabase = new MySQLiteDatabase(this);

        mMonAn = mySQLiteDatabase.getMonAn(getIntent().getIntExtra(Global.EXTRA_ID_KEY, 0));

        nguyenLieuAdapter = new NguyenLieuAdapter(this, new ArrayList<>(Arrays.asList(mMonAn.getNguyenLieu().split(";"))));

        thucHienAdapter = new ThucHienAdapter(this, new ArrayList<>(Arrays.asList(mMonAn.getCheBien().split(";"))));

        setTitle(mMonAn.getTen());

        MyUtils.loadImage(this, ivIcon, mMonAn.getHinhAnh());
        btCheBien.setText("Thực hiện: " + MyUtils.convertTime(mMonAn.getThoiGian()));

        //Thiet lap che do hien thi dang list cho Recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvData.setLayoutManager(layoutManager);
        //Thiet lap hieu ung cho Recyclerview
        rvData.setItemAnimator(new SlideInUpAnimator());

        selectNguyenLieu(true);

        if (mMonAn.getYeuThich() > 0) {
            Glide.with(ChiTietMonAnActivity.this)
                    .load(R.drawable.ic_favorite_selected)
                    .into(btYeuThich);
        } else {
            Glide.with(ChiTietMonAnActivity.this)
                    .load(R.drawable.ic_favorite_unselected)
                    .into(btYeuThich);
        }
    }

    @Override
    public void initListener() {
        btYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int yeuThich = 1;
                int imageDrawable = R.drawable.ic_favorite_selected;
                int message = R.string.message_favorite;
                if (mMonAn.getYeuThich() != 0) {
                    yeuThich = 0;
                    imageDrawable = R.drawable.ic_favorite_unselected;
                    message = R.string.message_unfavorite;
                }
                Glide.with(ChiTietMonAnActivity.this)
                        .load(imageDrawable)
                        .into(btYeuThich);

                if (mySQLiteDatabase.updateYeuThich(mMonAn.getId(), yeuThich)) {
                    mMonAn.setYeuThich(yeuThich);
                    Toast.makeText(ChiTietMonAnActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChiTietMonAnActivity.this, R.string.message_favorite_exception, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btXemVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTietMonAnActivity.this, VideoActivity.class);
                intent.putExtra(Global.EXTRA_NAME_KEY, mMonAn.getTen());
                intent.putExtra(Global.EXTRA_ID_KEY, mMonAn.getVideo());
                startActivity(intent);
            }
        });

        btNguyenLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectNguyenLieu(true);
            }
        });

        btCheBien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectNguyenLieu(false);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void selectNguyenLieu(boolean isSelect) {
        if (isSelect) {
            btNguyenLieu.setTextColor(Color.WHITE);
            btNguyenLieu.setBackgroundColor(Color.DKGRAY);
            btCheBien.setTextColor(Color.BLACK);
            btCheBien.setBackgroundColor(Color.WHITE);
            rvData.setAdapter(nguyenLieuAdapter);
        } else {
            btCheBien.setTextColor(Color.WHITE);
            btCheBien.setBackgroundColor(Color.DKGRAY);
            btNguyenLieu.setTextColor(Color.BLACK);
            btNguyenLieu.setBackgroundColor(Color.WHITE);
            rvData.setAdapter(thucHienAdapter);
        }
    }


}
