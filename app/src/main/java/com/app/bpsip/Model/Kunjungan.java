package com.app.bpsip.Model;

import com.google.gson.annotations.SerializedName;

public class Kunjungan {

	@SerializedName("kunjungan_taman_agrostandar")
	private String kunjunganTamanAgrostandar;

	@SerializedName("kunjungan_id")
	private String kunjunganId;

	@SerializedName("kunjungan_perpustakaan")
	private String kunjunganPerpustakaan;

	@SerializedName("kunjungan_instalasi_pengujian")
	private String kunjunganInstalasiPengujian;

	public void setKunjunganTamanAgrostandar(String kunjunganTamanAgrostandar){
		this.kunjunganTamanAgrostandar = kunjunganTamanAgrostandar;
	}

	public String getKunjunganTamanAgrostandar(){
		return kunjunganTamanAgrostandar;
	}

	public void setKunjunganId(String kunjunganId){
		this.kunjunganId = kunjunganId;
	}

	public String getKunjunganId(){
		return kunjunganId;
	}

	public void setKunjunganPerpustakaan(String kunjunganPerpustakaan){
		this.kunjunganPerpustakaan = kunjunganPerpustakaan;
	}

	public String getKunjunganPerpustakaan(){
		return kunjunganPerpustakaan;
	}

	public void setKunjunganInstalasiPengujian(String kunjunganInstalasiPengujian){
		this.kunjunganInstalasiPengujian = kunjunganInstalasiPengujian;
	}

	public String getKunjunganInstalasiPengujian(){
		return kunjunganInstalasiPengujian;
	}
}