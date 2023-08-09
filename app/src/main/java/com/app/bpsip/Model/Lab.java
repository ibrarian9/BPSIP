package com.app.bpsip.Model;

import com.google.gson.annotations.SerializedName;

public class Lab {

	@SerializedName("profile_lab_struktur")
	private String profileLabStruktur;

	@SerializedName("profile_lab_tarif")
	private String profileLabTarif;

	@SerializedName("profile_lab_peralatan")
	private String profileLabPeralatan;

	@SerializedName("profile_lab_ruang_lingkup")
	private String profileLabRuangLingkup;

	@SerializedName("profile_lab_id")
	private int profileLabId;

	public void setProfileLabStruktur(String profileLabStruktur){
		this.profileLabStruktur = profileLabStruktur;
	}

	public String getProfileLabStruktur(){
		return profileLabStruktur;
	}

	public void setProfileLabTarif(String profileLabTarif){
		this.profileLabTarif = profileLabTarif;
	}

	public String getProfileLabTarif(){
		return profileLabTarif;
	}

	public void setProfileLabPeralatan(String profileLabPeralatan){
		this.profileLabPeralatan = profileLabPeralatan;
	}

	public String getProfileLabPeralatan(){
		return profileLabPeralatan;
	}

	public void setProfileLabRuangLingkup(String profileLabRuangLingkup){
		this.profileLabRuangLingkup = profileLabRuangLingkup;
	}

	public String getProfileLabRuangLingkup(){
		return profileLabRuangLingkup;
	}

	public void setProfileLabId(int profileLabId){
		this.profileLabId = profileLabId;
	}

	public int getProfileLabId(){
		return profileLabId;
	}
}