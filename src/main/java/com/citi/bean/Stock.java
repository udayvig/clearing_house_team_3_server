package com.citi.bean;

public class Stock {
	
	private int stockID;
	private String stockName;
	private double borrowingRate;
	private int corporateAction;
	/**
	 * @return the stockID
	 */
	public int getStockID() {
		return stockID;
	}
	/**
	 * @param stockID the stockID to set
	 */
	public void setStockID(int stockID) {
		this.stockID = stockID;
	}
	/**
	 * @return the stockName
	 */
	public String getStockName() {
		return stockName;
	}
	/**
	 * @param stockName the stockName to set
	 */
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	/**
	 * @return the borrowingRate
	 */
	public double getBorrowingRate() {
		return borrowingRate;
	}
	/**
	 * @param borrowingRate the borrowingRate to set
	 */
	public void setBorrowingRate(double borrowingRate) {
		this.borrowingRate = borrowingRate;
	}
	/**
	 * @return the corporateAction
	 */
	public int getCorporateAction() {
		return corporateAction;
	}
	/**
	 * @param corporateAction the corporateAction to set
	 */
	public void setCorporateAction(int corporateAction) {
		this.corporateAction = corporateAction;
	}

}
