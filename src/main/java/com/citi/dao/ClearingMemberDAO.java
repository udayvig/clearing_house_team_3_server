package com.citi.dao;

import java.util.List;

import com.citi.bean.ClearingMember;

public interface ClearingMemberDAO {
	
	public void addClearingMember(String clearingMemberName, int clearingMemberOpeningFundBalance);
	public ClearingMember getClearingMember(int clearingMemberID);
	public void updateClearingMemberName(int clearingMemberID, String clearingMemberName);
	public void updateClearingMemberFundBalance(int clearingMemberID, double clearingMemberOpeningFundBalance);
	public void deleteClearingMember(int clearingMemberID);
	public List<ClearingMember> getAllClearingMembers();
	public void updateSessionToken(int cmID, String token);
	public ClearingMember getClearingMemberFromToken(String sessionToken);
}
