/**
 * 
 */
package com.citi.bean;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class ClearingMember {

		private int memberID;
		private String memberName;
		private String username;
		private String password;
		private HashMap<Integer, Integer> openingStockBalance;
		private double openingFundBalance;
		
		public int getMemberID() {
			return memberID;
		}
		public void setMemberID(int memberID) {
			this.memberID = memberID;
		}
		public String getMemberName() {
			return memberName;
		}
		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public HashMap<Integer, Integer> getOpeningStockBalance() {
			return openingStockBalance;
		}
		public void setOpeningStockBalance(HashMap<Integer, Integer> openingStockBalance) {
			this.openingStockBalance = openingStockBalance;
		}
		public double getOpeningFundBalance() {
			return openingFundBalance;
		}
		public void setOpeningFundBalance(double openingFundBalance) {
			this.openingFundBalance = openingFundBalance;
		}
}
