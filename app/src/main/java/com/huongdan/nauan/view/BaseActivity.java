package com.huongdan.nauan.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Window;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Slide());
            getWindow().setExitTransition(new Slide());

        }

        setContentView(myView());
        findView();
        configView();
        initListener();
    }

    /***
     * 0- Tra ve view hien thi cua activity
     * @return
     */
    public abstract int myView();

    /***
     * 1- Anh xa view tu layout -> bien trong java
     */
    public abstract void findView();

    /***
     * 2- Thiet lap hien thi mac dinh cho view
     */
    public abstract void configView();

    /***
     * Xu ly su kien
     */
    public abstract void initListener();
}
