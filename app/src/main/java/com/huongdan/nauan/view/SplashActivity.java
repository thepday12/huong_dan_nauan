package com.huongdan.nauan.view;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Pair;

import com.huongdan.nauan.R;
import com.huongdan.nauan.view.menu_activity.MenuActivity;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        scheduleSplashScreen();
    }

    private void scheduleSplashScreen() {
        long splashScreenDuration = 1500;
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
                                          startActivity(intent);
                                          finish();
                                      }
                                  }, splashScreenDuration
        );
    }
}
