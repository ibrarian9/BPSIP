package com.app.bs1p.Model;

import com.google.gson.annotations.SerializedName;

public class ListOrganisasi {

	@SerializedName("organisasi_id")
	private int organisasiId;

	@SerializedName("organisasi_profile")
	private String organisasiProfile;

	@SerializedName("organisasi_visi")
	private String organisasiVisi;

	@SerializedName("organisasi_fungsi")
	private String organisasiFungsi;

	@SerializedName("organisasi_struktur")
	private String organisasiStruktur;

	@SerializedName("organisasi_misi")
	private String organisasiMisi;

	public void setOrganisasiId(int organisasiId){
		this.organisasiId = organisasiId;
	}

	public int getOrganisasiId(){
		return organisasiId;
	}

	public void setOrganisasiProfile(String organisasiProfile){
		this.organisasiProfile = organisasiProfile;
	}

	public String getOrganisasiProfile(){
		return organisasiProfile;
	}

	public void setOrganisasiVisi(String organisasiVisi){
		this.organisasiVisi = organisasiVisi;
	}

	public String getOrganisasiVisi(){
		return organisasiVisi;
	}

	public void setOrganisasiFungsi(String organisasiFungsi){
		this.organisasiFungsi = organisasiFungsi;
	}

	public String getOrganisasiFungsi(){
		return organisasiFungsi;
	}

	public void setOrganisasiStruktur(String organisasiStruktur){
		this.organisasiStruktur = organisasiStruktur;
	}

	public String getOrganisasiStruktur(){
		return organisasiStruktur;
	}

	public void setOrganisasiMisi(String organisasiMisi){
		this.organisasiMisi = organisasiMisi;
	}

	public String getOrganisasiMisi(){
		return organisasiMisi;
	}
}