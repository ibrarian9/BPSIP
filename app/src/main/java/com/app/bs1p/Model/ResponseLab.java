package com.app.bs1p.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseLab{

	@SerializedName("kode")
	private int kode;

	@SerializedName("hasil")
	private Lab lab;

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}

	public void setHasil(Lab lab){
		this.lab = lab;
	}

	public Lab getHasil(){
		return lab;
	}
}