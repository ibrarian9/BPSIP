package com.app.bpsip.Model;

import com.google.gson.annotations.SerializedName;

public class Konsul {
    @SerializedName("konsultasi_nik")
    private String nik;
    @SerializedName("konsultasi_nama")
    private String nama;
    @SerializedName("konsultasi_alamat")
    private String alamat;
    @SerializedName("konsultasi_institusi")
    private String institusi;
    @SerializedName("konsultasi_email")
    private String email;
    @SerializedName("konsultasi_no_hp")
    private String noHp;
    @SerializedName("konsultasi_pesan")
    private int pesan;

    public Konsul(String nik, String nama, String alamat, String institusi, String email, String noHp, int pesan) {
        this.nik = nik;
        this.nama = nama;
        this.alamat = alamat;
        this.institusi = institusi;
        this.email = email;
        this.noHp = noHp;
        this.pesan = pesan;
    }

    public Konsul(){}

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
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

    public String getInstitusi() {
        return institusi;
    }

    public void setInstitusi(String institusi) {
        this.institusi = institusi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public int getPesan() {
        return pesan;
    }

    public void setPesan(int pesan) {
        this.pesan = pesan;
    }
}
