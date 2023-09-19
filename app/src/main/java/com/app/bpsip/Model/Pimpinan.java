package com.app.bpsip.Model;

import com.google.gson.annotations.SerializedName;

public class Pimpinan {

	@SerializedName("pimpinan_alamat")
	private String pimpinanAlamat;

	@SerializedName("pimpinan_foto")
	private String pimpinanFoto;

	@SerializedName("pimpinan_email")
	private String pimpinanEmail;

	@SerializedName("pimpinan_no_hp")
	private String pimpinanNoHp;

	@SerializedName("pimpinan_nama")
	private String pimpinanNama;

	@SerializedName("pimpinan_id")
	private String pimpinanId;

	public String getPimpinanAlamat(){
		return pimpinanAlamat;
	}

	public String getPimpinanFoto(){
		return pimpinanFoto;
	}

	public String getPimpinanEmail(){
		return pimpinanEmail;
	}

	public String getPimpinanNoHp(){
		return pimpinanNoHp;
	}

	public String getPimpinanNama(){
		return pimpinanNama;
	}

	public String getPimpinanId(){
		return pimpinanId;
	}
}