package com.citi.bean;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * A class which represents a single trade (transaction).
 */
public class Trade {
	private int tradeID;
	private int stockID;
	private int quantity;
	private double price;
	private int buyerClearingMemberID;
	private int sellerClearingMemberID;
	
	//Getter and Setter functions.
	public int getTradeID() {
		return tradeID;
	}
	
	public void setTradeID(int tradeID) {
		this.tradeID = tradeID;
	}
	
	public int getStockID() {
		return stockID;
	}
	
	public void setStockID(int stockID) {
		this.stockID = stockID;
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
	
	public int getBuyerClearingMemberID() {
		return buyerClearingMemberID;
	}
	
	public void setBuyerClearingMemberID(int buyerClearingMemberID) {
		this.buyerClearingMemberID = buyerClearingMemberID;
	}
	
	public int getSellerClearingMemberID() {
		return sellerClearingMemberID;
	}
	
	public void setSellerClearingMemberID(int sellerClearingMemberID) {
		this.sellerClearingMemberID = sellerClearingMemberID;
	}
	
	public double getTradeValue() {
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.DOWN);
		double tv = (double)(this.quantity * this.price);
		double tradeValue = new Double(df.format(tv));
		return tradeValue;
	}
}
