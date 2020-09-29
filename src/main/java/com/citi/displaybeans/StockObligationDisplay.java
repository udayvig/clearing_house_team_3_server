package com.citi.displaybeans;

public class StockObligationDisplay {

	private String name;
	private double google;
	private double amazon;
	private double apple;
	private double netflix;
	private double facebook;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public double getFacebook() {
		return facebook;
	}

	public void setFacebook(double facebook) {
		this.facebook = facebook;
	}

	@Override
	public String toString() {
		return "StockObligationDisplay [name=" + name + "\ngoogle=" + google + ", amazon=" + amazon + ", apple=" + apple
				+ ", netfilx=" + netflix + ", facebook=" + facebook + "]";
	}

}