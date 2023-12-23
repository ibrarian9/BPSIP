package com.app.bs1p.Model;

import com.google.gson.annotations.SerializedName;

public class Pengunjung {
    @SerializedName("jumlah_pengunjung")
    private int jumlah;

    public Pengunjung(int jumlah) {
        this.jumlah = jumlah;
    }
    public int getJumlah() {
        return jumlah;
    }
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
