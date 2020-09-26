package com.citi.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.bean.ClearingMember;

@Repository
public class ClearingMemberDAOImpl implements ClearingMemberDAO {

	@Override
	public void addClearingMember(String clearingMemberName, int clearingMemberOpeningFundBalance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClearingMember getClearingMember(int clearingMemberID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateClearingMemberName(int clearingMemberID, String clearingMemberName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateClearingMemberFundBalance(int clearingMemberID, double clearingMemberOpeningFundBalance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteClearingMember(int clearingMemberID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ClearingMember> getAllClearingMembers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
