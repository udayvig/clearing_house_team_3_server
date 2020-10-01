package com.citi.displaybeans;

public class TradeBookDisplay {
	private int tradeID;
	private String cmName;
	private String orderType;
	private String stockName;
	private int quantity;
	private double price;
	private double tradeValue;
	
	public double getTradeValue() {
		return tradeValue;
	}

	public void setTradeValue(double tradeValue) {
		this.tradeValue = tradeValue;
	}

	public int getTradeID() {
		return tradeID;
	}
	
	public void setTradeID(int tradeID) {
		this.tradeID = tradeID;
	}
	
	public String getCmName() {
		return cmName;
	}
	
	public void setCmName(String cmName) {
		this.cmName = cmName;
	}
	
	public String getOrderType() {
		return orderType;
	}
	
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	public String getStockName() {
		return stockName;
	}
	
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
}
