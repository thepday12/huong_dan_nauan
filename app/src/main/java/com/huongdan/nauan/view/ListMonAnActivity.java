package com.huongdan.nauan.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.huongdan.nauan.R;
import com.huongdan.nauan.adapter.MenuHomeAdapter;
import com.huongdan.nauan.adapter.MonAnAdapter;
import com.huongdan.nauan.model.MenuMonAn;
import com.huongdan.nauan.model.MonAn;
import com.huongdan.nauan.utils.Global;
import com.huongdan.nauan.utils.MySQLiteDatabase;
import com.huongdan.nauan.utils.MyUtils;
import com.huongdan.nauan.utils.animation.view_recycler.SlideInUpAnimator;

import java.util.List;

public class ListMonAnActivity extends BaseActivity {

    private ImageView ivIcon;
    private TextView tvName;
    private RecyclerView rvMonAn;
    private ProgressBar pbLoading;
    private MySQLiteDatabase mySQLiteDatabase;
    private MonAnAdapter mAdapter;
    private String mLoaiMonAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public int myView() {
        return R.layout.activity_list_mon_an;
    }

    @Override
    public void findView() {
        ivIcon = findViewById(R.id.ivIcon);
        tvName = findViewById(R.id.tvName);
        pbLoading = findViewById(R.id.pbLoading);
        rvMonAn = findViewById(R.id.rvMonAn);
    }

    @Override
    public void configView() {
        mySQLiteDatabase = new MySQLiteDatabase(this);
        //Lay du lieu duoc truyen tu activity trc do
        Intent intent = getIntent();
        String tenMonAn = intent.getStringExtra(Global.EXTRA_NAME_KEY);
        String hinhAnhMonAn = intent.getStringExtra(Global.EXTRA_IMAGE_KEY);
        mLoaiMonAn = hinhAnhMonAn.replace("ic_","");
        //Dat tieu de
        setTitle(tenMonAn);
        tvName.setText(tenMonAn);
        //Thiet lap hien thi hinh anh
        Glide.with(this)
                .load(MyUtils.getImage(this,hinhAnhMonAn))
                .into(ivIcon);
        //Thiet lap che do hien thi dang grid cho Recyclerview
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        rvMonAn.setLayoutManager(layoutManager);
        //Thiet lap hieu ung cho Recyclerview
        rvMonAn.setItemAnimator(new SlideInUpAnimator());

        //Lay danh sach mon an
        new GetListMonAn().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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


    class GetListMonAn extends AsyncTask<Void,Void,List<MonAn>>{

        @Override
        protected List<MonAn> doInBackground(Void... voids) {
            return mySQLiteDatabase.getListMonAnByLoai(mLoaiMonAn);
        }

        @Override
        protected void onPostExecute(List<MonAn> monAns) {
            pbLoading.setVisibility(View.INVISIBLE);
            mAdapter = new MonAnAdapter(ListMonAnActivity.this,monAns);
            rvMonAn.setAdapter(mAdapter);
            super.onPostExecute(monAns);
        }
    }
}
