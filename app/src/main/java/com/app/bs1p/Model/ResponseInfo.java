package com.app.bs1p.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseInfo{

	@SerializedName("kode")
	private int kode;
	@SerializedName("hasil")
	private Info info;

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}

	public void setHasil(Info info){
		this.info = info;
	}

	public Info getHasil(){
		return info;
	}
}