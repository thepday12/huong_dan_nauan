package com.huongdan.nauan.model;

import java.util.ArrayList;
import java.util.List;

public class ThucDonNgay {
    MonAn buaSang;
    List<MonAn> buaTrua;
    List<MonAn> buaToi;


    public ThucDonNgay(MonAn buaSang) {
        this.buaSang = buaSang;
    }

    public ThucDonNgay(MonAn buaSang, List<MonAn> buaTrua, List<MonAn> buaToi) {
        this.buaSang = buaSang;
        this.buaTrua = buaTrua;
        this.buaToi = buaToi;
    }

    public MonAn getBuaSang() {
        return buaSang;
    }
    public List<MonAn> getListBuaSang() {
        List<MonAn> list = new ArrayList<>();
        list.add(buaSang);
        return list;
    }

    public void setBuaSang(MonAn buaSang) {
        this.buaSang = buaSang;
    }

    public List<MonAn> getBuaTrua() {
        return buaTrua;
    }

    public void setBuaTrua(List<MonAn> buaTrua) {
        this.buaTrua = buaTrua;
    }

    public List<MonAn> getBuaToi() {
        return buaToi;
    }

    public void setBuaToi(List<MonAn> buaToi) {
        this.buaToi = buaToi;
    }
}
