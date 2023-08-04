package com.app.bpsip.Model;

import com.google.gson.annotations.SerializedName;

public class Labor {
    @SerializedName("laboratorium_nama")
    private String nama;
    @SerializedName("laboratorium_alamat")
    private String alamat;
    @SerializedName("laboratorium_instansi")
    private String instansi;
    @SerializedName("laboratorium_no_hp")
    private String noHp;
    @SerializedName("laboratorium_sampel")
    private String sampel;

    public Labor(){}

    public Labor(String nama, String alamat, String instansi, String noHp, String sampel) {
        this.nama = nama;
        this.alamat = alamat;
        this.instansi = instansi;
        this.noHp = noHp;
        this.sampel = sampel;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getInstansi() {
        return instansi;
    }

    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getSampel() {
        return sampel;
    }

    public void setSampel(String sampel) {
        this.sampel = sampel;
    }
}
