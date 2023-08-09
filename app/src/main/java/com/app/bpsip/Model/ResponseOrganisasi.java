package com.app.bpsip.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseOrganisasi {

	@SerializedName("kode")
	private int kode;

	@SerializedName("hasil")
	private ListOrganisasi listOrganisasi;

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}

	public void setHasil(ListOrganisasi listOrganisasi){
		this.listOrganisasi = listOrganisasi;
	}

	public ListOrganisasi getHasil(){
		return listOrganisasi;
	}
}