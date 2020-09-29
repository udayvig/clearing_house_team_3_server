package com.citi.displaybeans;

public class CostSettlementDisplay {
	
	private double openingBalance;
	private double dailyObligation;
	private double costOfFunds;
	private double costOfSecurities;
	private double total;
	public double getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}
	public double getDailyObligation() {
		return dailyObligation;
	}
	public void setDailyObligation(double dailyObligation) {
		this.dailyObligation = dailyObligation;
	}
	public double getCostOfFunds() {
		return costOfFunds;
	}
	public void setCostOfFunds(double costOfFunds) {
		this.costOfFunds = costOfFunds;
	}
	public double getCostOfSecurities() {
		return costOfSecurities;
	}
	public void setCostOfSecurities(double costOfSecurities) {
		this.costOfSecurities = costOfSecurities;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal() {
		this.total = this.dailyObligation - this.costOfFunds - this.costOfSecurities;
	}
	@Override
	public String toString() {
		return "CostSettlementDisplay [openingBalance=" + openingBalance + ", dailyObligation=" + dailyObligation
				+ ", costOfFunds=" + costOfFunds + ", costOfSecurities=" + costOfSecurities + ", total=" + total + "]";
	}
}
