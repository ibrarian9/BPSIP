package com.app.bs1p.Model;

import com.google.gson.annotations.SerializedName;

public class Hasil {

	@SerializedName("jumlah_pengunjung")
	private int jumlahPengunjung;

	@SerializedName("id")
	private int id;

	public void setJumlahPengunjung(int jumlahPengunjung){
		this.jumlahPengunjung = jumlahPengunjung;
	}

	public int getJumlahPengunjung(){
		return jumlahPengunjung;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
}