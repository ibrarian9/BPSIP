package com.app.bpsip.Model;

import com.google.gson.annotations.SerializedName;

public class ResponsePimpinan{

	@SerializedName("kode")
	private int kode;

	@SerializedName("hasil")
	private Pimpinan pimpinan;

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}

	public void setHasil(Pimpinan pimpinan){
		this.pimpinan = pimpinan;
	}

	public Pimpinan getHasil(){
		return pimpinan;
	}
}