package com.citi.displaybeans;

public class StockObligationDisplay {

	private String name;
	private int google;
	private int amazon;
	private int apple;
	private int netflix;
	private int facebook;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGoogle() {
		return google;
	}

	public void setGoogle(int google) {
		this.google = google;
	}

	public int getAmazon() {
		return amazon;
	}

	public void setAmazon(int amazon) {
		this.amazon = amazon;
	}

	public int getApple() {
		return apple;
	}

	public void setApple(int apple) {
		this.apple = apple;
	}

	public int getNetflix() {
		return netflix;
	}

	public void setNetflix(int netfilx) {
		this.netflix = netfilx;
	}

	public int getFacebook() {
		return facebook;
	}

	public void setFacebook(int facebook) {
		this.facebook = facebook;
	}

	@Override
	public String toString() {
		return "StockObligationDisplay [name=" + name + "\ngoogle=" + google + ", amazon=" + amazon + ", apple=" + apple
				+ ", netfilx=" + netflix + ", facebook=" + facebook + "]";
	}

}