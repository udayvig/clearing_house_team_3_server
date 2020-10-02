package com.citi.displaybeans;

public class ClearingMemberCorporateActionReportPerStockDisplay {
	
	private int clearingMemberID;
	private String clearingMemberName;
	
	private int stockid;
	private String stockName;
	
	private int openingBalance;
	private int dailyObligation;
	private int netTotal;
/**/	private String corporateActionName;
	private double corporateAction;
	private double closingBalance;
	
/**/	public String getCorporateActionName() {
		return corporateActionName;
	}
	public void setCorporateActionName(String corporateActionName) {
		this.corporateActionName = corporateActionName;
	}/**/
	public int getClearingMemberID() {
		return clearingMemberID;
	}
	public void setClearingMemberID(int clearingMemberID) {
		this.clearingMemberID = clearingMemberID;
	}
	public String getClearingMemberName() {
		return clearingMemberName;
	}
	public void setClearingMemberName(String clearingMemberName) {
		this.clearingMemberName = clearingMemberName;
	}
	public int getStockid() {
		return stockid;
	}
	public void setStockid(int stockid) {
		this.stockid = stockid;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public int getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(int openingBalance) {
		this.openingBalance = openingBalance;
	}
	public int getDailyObligation() {
		return dailyObligation;
	}
	public void setDailyObligation(int dailyObligation) {
		this.dailyObligation = dailyObligation;
	}
	public int getNetTotal() {
		return netTotal;
	}
	public void setNetTotal(int netTotal) {
		this.netTotal = netTotal;
	}
	public double getCorporateAction() {
		return corporateAction;
	}
	public void setCorporateAction(double corporateAction) {
		this.corporateAction = corporateAction;
	}
	public double getClosingBalance() {
		return closingBalance;
	}
	public void setClosingBalance(double closingBalance) {
		this.closingBalance = closingBalance;
	}
}
