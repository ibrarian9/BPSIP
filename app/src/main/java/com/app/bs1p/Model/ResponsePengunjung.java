package com.app.bs1p.Model;

import com.google.gson.annotations.SerializedName;

public class ResponsePengunjung{

	@SerializedName("kode")
	private int kode;

	@SerializedName("hasil")
	private Hasil hasil;

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}

	public void setHasil(Hasil hasil){
		this.hasil = hasil;
	}

	public Hasil getHasil(){
		return hasil;
	}
}