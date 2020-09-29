package com.citi.displaybeans;

public class OpeningBalanceDisplay {
	
	private String clearingMemberName;
	private double cash;
	private double apple;
	private double netflix;
	private double google;
	private double amazon;
	private double facebook;

	
	public String getClearingMemberName() {
		return clearingMemberName;
	}


	public void setClearingMemberName(String clearingMemberName) {
		this.clearingMemberName = clearingMemberName;
	}


	public double getCash() {
		return cash;
	}


	public void setCash(double cash) {
		this.cash = cash;
	}


	public double getApple() {
		return apple;
	}


	public void setApple(double apple) {
		this.apple = apple;
	}


	public double getNetflix() {
		return netflix;
	}


	public void setNetflix(double netflix) {
		this.netflix = netflix;
	}


	public double getGoogle() {
		return google;
	}


	public void setGoogle(double google) {
		this.google = google;
	}


	public double getAmazon() {
		return amazon;
	}


	public void setAmazon(double amazon) {
		this.amazon = amazon;
	}


	public double getFacebook() {
		return facebook;
	}


	public void setFacebook(double facebook) {
		this.facebook = facebook;
	}


	@Override
	public String toString() {
		return "OpeningBalanceDisplay [clearingMemberName=" + clearingMemberName + ", cash=" + cash + ", apple=" + apple
				+ ", netflix=" + netflix + ", google=" + google + ", amazon=" + amazon + ", facebook=" + facebook + "]";
	}
}
