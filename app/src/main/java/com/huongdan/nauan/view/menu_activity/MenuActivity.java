package com.huongdan.nauan.view.menu_activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.huongdan.nauan.R;
import com.huongdan.nauan.adapter.MenuViewAdapter;
import com.huongdan.nauan.utils.Global;
import com.huongdan.nauan.utils.animation.view_pager.DepthPageTransformer;
import com.huongdan.nauan.view.BaseActivity;
import com.huongdan.nauan.view.ListMonAnActivity;

public class MenuActivity extends BaseActivity {

    private  BottomNavigationView bottomMenu;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
    }

    @Override
    public int myView() {
        return R.layout.activity_menu;
    }

    @Override
    public void findView() {
        bottomMenu = findViewById(R.id.bottomMenu);
        viewPager = findViewById(R.id.viewPager);
    }



    @Override
    public void configView(){
        MenuViewAdapter adapter = new MenuViewAdapter(getSupportFragmentManager());
        viewPager.setPageTransformer(true,new DepthPageTransformer());//set hieu ung chuyen tab
        viewPager.setAdapter(adapter);
    }

    @Override
    public void initListener(){


        //Khi chon cac tab o bottom menu
        bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_dashboard:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_notifications:
                        viewPager.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            public void onPageSelected(int position) {
                bottomMenu.getMenu().getItem(position).setChecked(true);
            }
        });
    }

}
