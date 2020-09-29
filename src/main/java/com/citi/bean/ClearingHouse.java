package com.citi.bean;

import org.springframework.stereotype.Component;

@Component
public class ClearingHouse {

	private String name;
	private String password;
	private double fundBorrowingRate;
	private int[] memberIds;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getFundBorrowingRate() {
		return fundBorrowingRate;
	} 
	public void setFundBorrowingRate(double fundBorrowingRate) {
		this.fundBorrowingRate = fundBorrowingRate;
	}
	public int[] getMemberIds() {
		return memberIds;
	}
	public void setMemberIds(int[] memberIds) {
		this.memberIds = memberIds;
	}
	
	
}
