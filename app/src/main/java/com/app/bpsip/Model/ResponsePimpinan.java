package com.app.bpsip.Model;

import com.google.gson.annotations.SerializedName;

public class ResponsePimpinan {

	@SerializedName("kode")
	private int kode;
	@SerializedName("hasil")
	private Pimpinan pimpinan;

	public int getKode(){
		return kode;
	}

	public Pimpinan getHasil(){
		return pimpinan;
	}
}