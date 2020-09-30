/**
 * 
 */
package com.citi.bean;

public class ClearingMember {

		private int clearingMemberID;
		private String clearingMemberName;
		private double clearingMemberOpeningFundBalance;
		private String sessionToken;
		
		public String getSessionToken() {
			return sessionToken;
		}
		public void setSessionToken(String sessionToken) {
			this.sessionToken = sessionToken;
		}
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
