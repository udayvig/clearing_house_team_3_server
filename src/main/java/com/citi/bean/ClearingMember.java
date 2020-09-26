/**
 * 
 */
package com.citi.bean;

import java.util.HashMap;

import org.springframework.stereotype.Component;

public class ClearingMember {

		private int clearingMemberID;
		private String clearingMemberName;
		private double clearingMemberOpeningFundBalance;
		
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
		public double getClearingMemberOpeningFundBalance() {
			return clearingMemberOpeningFundBalance;
		}
		public void setClearingMemberOpeningFundBalance(double clearingMemberOpeningFundBalance) {
			this.clearingMemberOpeningFundBalance = clearingMemberOpeningFundBalance;
		}
		
		
}
