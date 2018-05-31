package com.huongdan.nauan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.huongdan.nauan.view.menu_activity.MenuGoiYFragment;
import com.huongdan.nauan.view.menu_activity.MenuHomeFragment;
import com.huongdan.nauan.view.menu_activity.MenuYeuThichFragment;

public class MenuViewAdapter extends FragmentPagerAdapter {
    public MenuViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return MenuGoiYFragment.newInstance();
            case 2:
                return MenuYeuThichFragment.newInstance();
            default:
                return MenuHomeFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}