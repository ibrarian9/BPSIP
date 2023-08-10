package com.app.bpsip.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseStock{

	@SerializedName("kode")
	private int kode;

	@SerializedName("hasil")
	private List<Stock> hasil;

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}

	public void setHasil(List<Stock> hasil){
		this.hasil = hasil;
	}

	public List<Stock> getHasil(){
		return hasil;
	}
}