package com.citi.bean;

public class Authentication {

	private int authentication_id;
	private String username;
	private String password;
	private String role;
	private int clearingMemberID;
	
	public int getClearingMemberID() {
		return clearingMemberID;
	}
	public void setClearingMemberID(int clearingMemberID) {
		this.clearingMemberID = clearingMemberID;
	}
	public int getAuthentication_id() {
		return authentication_id;
	}
	public void setAuthentication_id(int authentication_id) {
		this.authentication_id = authentication_id;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
