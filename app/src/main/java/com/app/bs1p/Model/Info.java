package com.app.bs1p.Model;

import com.google.gson.annotations.SerializedName;

public class Info {

	@SerializedName("informasi_magang")
	private String informasiMagang;

	@SerializedName("informasi_id")
	private int informasiId;

	@SerializedName("informasi_upbs")
	private String informasiUpbs;

	public void setInformasiMagang(String informasiMagang){
		this.informasiMagang = informasiMagang;
	}

	public String getInformasiMagang(){
		return informasiMagang;
	}

	public void setInformasiId(int informasiId){
		this.informasiId = informasiId;
	}

	public int getInformasiId(){
		return informasiId;
	}

	public void setInformasiUpbs(String informasiUpbs){
		this.informasiUpbs = informasiUpbs;
	}

	public String getInformasiUpbs(){
		return informasiUpbs;
	}
}