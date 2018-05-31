package com.huongdan.nauan.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Random;

public class MyUtils {
    static final String PACKAGE_NAME = "com.huongdan.nauan";

    public static int getImage(Context context, String imageName) {

        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", PACKAGE_NAME);

        return drawableResourceId;
    }

    public static void loadImage(Context context, ImageView imageView, String imageName) {
        if (imageName.startsWith("http://")) {//Anh tu url
            Glide.with(context)
                    .load(imageName)//.load(new File(pictureUri.getPath()))
                    .into(imageView);
        } else {//Anh trong resource
            Glide.with(context)
                    .load(MyUtils.getImage(context, imageName))
                    .into(imageView);
        }
    }

    public static String convertTime(int minutes) {
        if (minutes < 60) {
            return minutes + " phút";
        } else {
            int hours = minutes / 60;
            int mins = minutes % 60;
            if (mins == 0) {
                return hours + " giờ";
            } else {
                return hours + "h " + mins + "p";
            }
        }
    }

    public static int getColor(int position) {
        switch (position) {
            case 1:
                return Color.parseColor("#0097A7");
            case 2:
                return Color.parseColor("#FFA000");
            case 3:
                return Color.parseColor("#FF5722");
            case 4:
                return Color.parseColor("#673AB7");
            case 5:
                return Color.parseColor("#009866");
            case 6:
                return Color.parseColor("#00BCD4");
            default:
                return Color.parseColor("#795548");
        }
    }

    public static String getThu(int position) {
        switch (position) {
            case 1:
                return "Thứ ba";
            case 2:
                return "Thứ Tư";
            case 3:
                return "Thứ Năm";
            case 4:
                return "Thứ Sáu";
            case 5:
                return "Thứ Bảy";
            case 6:
                return "Chủ Nhật";
            default:
                return "Thứ Hai";
        }
    }


    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }
}
