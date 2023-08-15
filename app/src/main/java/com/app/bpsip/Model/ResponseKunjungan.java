package com.app.bpsip.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseKunjungan{

	@SerializedName("kode")
	private int kode;

	@SerializedName("hasil")
	private Kunjungan kunjungan;

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}

	public void setKunjungan(Kunjungan kunjungan){
		this.kunjungan = kunjungan;
	}

	public Kunjungan getKunjungan(){
		return kunjungan;
	}
}