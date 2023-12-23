package com.app.bs1p.Model;

import com.google.gson.annotations.SerializedName;

public class Magang {
    @SerializedName("magang_nama")
    private String nama;
    @SerializedName("magang_nim")
    private String nim;
    @SerializedName("magang_sekolah")
    private String sekolah;
    @SerializedName("magang_jurusan")
    private String jurusan;
    @SerializedName("magang_no_hp")
    private String noHp;
    @SerializedName("magang_waktu")
    private String waktu;
    @SerializedName("magang_surat")
    private String surat;

    public Magang(String nama, String nim, String sekolah, String jurusan, String noHp, String waktu, String surat) {
        this.nama = nama;
        this.nim = nim;
        this.sekolah = sekolah;
        this.jurusan = jurusan;
        this.noHp = noHp;
        this.waktu = waktu;
        this.surat = surat;
    }

    public Magang(){}

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getSekolah() {
        return sekolah;
    }

    public void setSekolah(String sekolah) {
        this.sekolah = sekolah;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getSurat() {
        return surat;
    }

    public void setSurat(String surat) {
        this.surat = surat;
    }
}
