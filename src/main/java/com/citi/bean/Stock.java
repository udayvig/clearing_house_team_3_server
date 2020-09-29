package com.citi.bean;

public class Stock {

	private int stockID;
	private String stockName;
	private double borrowingRate;
	private double corporateActionFactor;
	private String corporateAction;

	public int getStockID() {
		return stockID;
	}

	public void setStockID(int stockID) {
		this.stockID = stockID;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public double getBorrowingRate() {
		return borrowingRate;
	}

	public void setBorrowingRate(double borrowingRate) {
		this.borrowingRate = borrowingRate;
	}

	public double getCorporateActionFactor() {
		return corporateActionFactor;
	}

	public void setCorporateActionFactor(double corporateActionFactor) {
		this.corporateActionFactor = corporateActionFactor;
	}

	public String getCorporateAction() {
		return corporateAction;
	}

	public void setCorporateAction(String corporateAction) {
		this.corporateAction = corporateAction;
	}

}