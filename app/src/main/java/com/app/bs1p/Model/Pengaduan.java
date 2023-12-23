package com.app.bs1p.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Pengaduan {

	@SerializedName("pengaduan_pengaduan")
	private List<String> pengaduanPengaduan;

	@SerializedName("pengaduan_foto")
	private List<String> pengaduanFoto;

	@SerializedName("pengaduan_alamat")
	private List<String> pengaduanAlamat;

	@SerializedName("pengaduan_no_hp")
	private List<String> pengaduanNoHp;

	@SerializedName("pengaduan_nik")
	private List<String> pengaduanNik;

	@SerializedName("pengaduan_nama")
	private List<String> pengaduanNama;

	@SerializedName("pengaduan_tempat")
	private List<String> pengaduanTempat;

	@SerializedName("pengaduan_tanggal")
	private List<String> pengaduanTanggal;

	public void setPengaduanPengaduan(List<String> pengaduanPengaduan){
		this.pengaduanPengaduan = pengaduanPengaduan;
	}

	public List<String> getPengaduanPengaduan(){
		return pengaduanPengaduan;
	}

	public void setPengaduanFoto(List<String> pengaduanFoto){
		this.pengaduanFoto = pengaduanFoto;
	}

	public List<String> getPengaduanFoto(){
		return pengaduanFoto;
	}

	public void setPengaduanAlamat(List<String> pengaduanAlamat){
		this.pengaduanAlamat = pengaduanAlamat;
	}

	public List<String> getPengaduanAlamat(){
		return pengaduanAlamat;
	}

	public void setPengaduanNoHp(List<String> pengaduanNoHp){
		this.pengaduanNoHp = pengaduanNoHp;
	}

	public List<String> getPengaduanNoHp(){
		return pengaduanNoHp;
	}

	public void setPengaduanNik(List<String> pengaduanNik){
		this.pengaduanNik = pengaduanNik;
	}

	public List<String> getPengaduanNik(){
		return pengaduanNik;
	}

	public void setPengaduanNama(List<String> pengaduanNama){
		this.pengaduanNama = pengaduanNama;
	}

	public List<String> getPengaduanNama(){
		return pengaduanNama;
	}

	public List<String> getPengaduanTempat() {
		return pengaduanTempat;
	}

	public void setPengaduanTempat(List<String> pengaduanTempat) {
		this.pengaduanTempat = pengaduanTempat;
	}

	public List<String> getPengaduanTanggal() {
		return pengaduanTanggal;
	}

	public void setPengaduanTanggal(List<String> pengaduanTanggal) {
		this.pengaduanTanggal = pengaduanTanggal;
	}
}