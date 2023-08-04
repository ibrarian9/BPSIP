package com.app.bpsip.Model;

import com.google.gson.annotations.SerializedName;

public class Upbs {
    @SerializedName("upbs_nama")
    private String nama;
    @SerializedName("upbs_nik")
    private String nik;
    @SerializedName("upbs_alamat")
    private String alamat;
    @SerializedName("upbs_nama_kelompok_tani")
    private String tani;
    @SerializedName("upbs_jabatan")
    private String jabatan;
    @SerializedName("upbs_luas_lahan")
    private String lahan;
    @SerializedName("upbs_komoditas")
    private String komoditas;
    @SerializedName("upbs_kebutuhan_benih")
    private String benih;
    @SerializedName("upbs_no_hp")
    private String noHp;
    @SerializedName("upbs_surat")
    private String surat;

    public Upbs(){}

    public Upbs(String nama, String nik, String alamat, String tani, String jabatan, String lahan, String komoditas, String benih, String noHp, String surat) {
        this.nama = nama;
        this.nik = nik;
        this.alamat = alamat;
        this.tani = tani;
        this.jabatan = jabatan;
        this.lahan = lahan;
        this.komoditas = komoditas;
        this.benih = benih;
        this.noHp = noHp;
        this.surat = surat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTani() {
        return tani;
    }

    public void setTani(String tani) {
        this.tani = tani;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getLahan() {
        return lahan;
    }

    public void setLahan(String lahan) {
        this.lahan = lahan;
    }

    public String getKomoditas() {
        return komoditas;
    }

    public void setKomoditas(String komoditas) {
        this.komoditas = komoditas;
    }

    public String getBenih() {
        return benih;
    }

    public void setBenih(String benih) {
        this.benih = benih;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getSurat() {
        return surat;
    }

    public void setSurat(String surat) {
        this.surat = surat;
    }
}
