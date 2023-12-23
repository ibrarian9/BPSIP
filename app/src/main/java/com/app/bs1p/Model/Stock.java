package com.app.bs1p.Model;

import com.google.gson.annotations.SerializedName;

public class Stock {

	@SerializedName("stock_benih_jumlah")
	private String stockBenihJumlah;

	@SerializedName("stock_benih_id")
	private int stockBenihId;

	@SerializedName("stock_benih_nama")
	private String stockBenihNama;

	public void setStockBenihJumlah(String stockBenihJumlah){
		this.stockBenihJumlah = stockBenihJumlah;
	}

	public String getStockBenihJumlah(){
		return stockBenihJumlah;
	}

	public void setStockBenihId(int stockBenihId){
		this.stockBenihId = stockBenihId;
	}

	public int getStockBenihId(){
		return stockBenihId;
	}

	public void setStockBenihNama(String stockBenihNama){
		this.stockBenihNama = stockBenihNama;
	}

	public String getStockBenihNama(){
		return stockBenihNama;
	}
}