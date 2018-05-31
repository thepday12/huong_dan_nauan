package com.huongdan.nauan.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huongdan.nauan.R;
import com.huongdan.nauan.adapter.MonAnAdapter;
import com.huongdan.nauan.model.ThucDonNgay;
import com.huongdan.nauan.utils.Global;
import com.huongdan.nauan.utils.MyUtils;
import com.huongdan.nauan.utils.animation.view_recycler.SlideInUpAnimator;

public class ChiTietThucDonActivity extends BaseActivity {

    private TextView tvBuaSang, tvBuaTrua, tvBuaToi;
    private RecyclerView rvBuaSang, rvBuaTrua, rvBuaToi;
    private ThucDonNgay mThucDonNgay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public int myView() {
        return R.layout.activity_chi_tiet_thuc_don;
    }

    @Override
    public void findView() {
        tvBuaSang = findViewById(R.id.tvBuaSang);
        tvBuaTrua = findViewById(R.id.tvBuaTrua);
        tvBuaToi = findViewById(R.id.tvBuaToi);
        rvBuaSang = findViewById(R.id.rvBuaSang);
        rvBuaTrua = findViewById(R.id.rvBuaTrua);
        rvBuaToi = findViewById(R.id.rvBuaToi);
    }

    @Override
    public void configView() {
        Intent intent = getIntent();
        setTitle("Thực đơn "+intent.getStringExtra(Global.EXTRA_NAME_KEY));
        tvBuaSang.setTextColor(MyUtils.getColor(1));
        tvBuaTrua.setTextColor(MyUtils.getColor(2));
        tvBuaToi.setTextColor(MyUtils.getColor(3));

        rvBuaSang.setLayoutManager(new GridLayoutManager(ChiTietThucDonActivity.this, 2));
        rvBuaTrua.setLayoutManager(new GridLayoutManager(ChiTietThucDonActivity.this, 2));
        rvBuaToi.setLayoutManager(new GridLayoutManager(ChiTietThucDonActivity.this, 2));

        mThucDonNgay = new Gson().fromJson(intent.getStringExtra(Global.EXTRA_DATA_KEY), ThucDonNgay.class);

        rvBuaSang.setAdapter(new MonAnAdapter(ChiTietThucDonActivity.this, mThucDonNgay.getListBuaSang()));
        rvBuaTrua.setAdapter(new MonAnAdapter(ChiTietThucDonActivity.this, mThucDonNgay.getBuaTrua()));
        rvBuaToi.setAdapter(new MonAnAdapter(ChiTietThucDonActivity.this, mThucDonNgay.getBuaToi()));
    }

    @Override
    public void initListener() {

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
}
