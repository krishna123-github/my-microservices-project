package com.kp;

public class Stock {
	private int stockId;
	private String stockName;
	private double stockPrice;
	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Stock(int stockId, String stockName, double stockPrice) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.stockPrice = stockPrice;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public double getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}
	
	
}
