package com.app.bpsip.Model;

import com.google.gson.annotations.SerializedName;

public class Pimpinan {

	@SerializedName("pimpinan_foto")
	private String pimpinanFoto;

	@SerializedName("pimpinan_tempat_lahir")
	private String pimpinanTempatLahir;

	@SerializedName("pimpinan_nama")
	private String pimpinanNama;

	@SerializedName("pimpinan_id")
	private int pimpinanId;

	@SerializedName("pimpinan_tanggal_lahir")
	private String pimpinanTanggalLahir;

	public void setPimpinanFoto(String pimpinanFoto){
		this.pimpinanFoto = pimpinanFoto;
	}

	public String getPimpinanFoto(){
		return pimpinanFoto;
	}

	public void setPimpinanTempatLahir(String pimpinanTempatLahir){
		this.pimpinanTempatLahir = pimpinanTempatLahir;
	}

	public String getPimpinanTempatLahir(){
		return pimpinanTempatLahir;
	}

	public void setPimpinanNama(String pimpinanNama){
		this.pimpinanNama = pimpinanNama;
	}

	public String getPimpinanNama(){
		return pimpinanNama;
	}

	public void setPimpinanId(int pimpinanId){
		this.pimpinanId = pimpinanId;
	}

	public int getPimpinanId(){
		return pimpinanId;
	}

	public void setPimpinanTanggalLahir(String pimpinanTanggalLahir){
		this.pimpinanTanggalLahir = pimpinanTanggalLahir;
	}

	public String getPimpinanTanggalLahir(){
		return pimpinanTanggalLahir;
	}
}