package com.app.bs1p.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePengaduan {

	@SerializedName("success")
	@Expose
	private Boolean success;
	@SerializedName("message")
	@Expose
	private String message;
	@SerializedName("kode")
	@Expose
	private int kode;
	public int getKode() {
		return kode;
	}

	public void setKode(int kode) {
		this.kode = kode;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}