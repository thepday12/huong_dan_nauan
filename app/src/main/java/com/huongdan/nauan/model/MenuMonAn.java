package com.huongdan.nauan.model;

import com.huongdan.nauan.R;

import java.util.ArrayList;
import java.util.List;

public class MenuMonAn {
    private String name;
    private String image;

    public static List<MenuMonAn> getDanhSachLoaiMonAn() {
        List<MenuMonAn> arr = new ArrayList<MenuMonAn>();
        arr.add(new MenuMonAn("Món chiên", "ic_mon_chien"));
        arr.add(new MenuMonAn("Món Rau", "ic_mon_rau"));
        arr.add(new MenuMonAn("Món xào", "ic_mon_xao"));
        return arr;
    }

    public MenuMonAn(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
