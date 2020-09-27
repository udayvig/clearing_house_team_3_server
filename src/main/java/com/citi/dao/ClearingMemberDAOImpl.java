package com.citi.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.citi.bean.ClearingMember;
import com.citi.mappers.ClearingMemberMapper;

@Repository
public class ClearingMemberDAOImpl implements ClearingMemberDAO {

	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public void addClearingMember(String clearingMemberName, int clearingMemberOpeningFundBalance) {
		// TODO Auto-generated method stub
		String addClearingMemberQuery = "INSERT INTO clearing_member(opening_fund_balance, clearing_member_name) VALUES (?, ?)";
		jdbcTemplateObject.update(addClearingMemberQuery, clearingMemberOpeningFundBalance, clearingMemberName);
		System.out.println("Created Record Name = " + clearingMemberName + " Balance = " + clearingMemberOpeningFundBalance);
	}

	@Override
	public ClearingMember getClearingMember(int clearingMemberID) {
		// TODO Auto-generated method stub
		String getClearingMemberQuery = "SELECT * FROM clearing_member WHERE clearing_member_id = ?";
		ClearingMember clearingMember = jdbcTemplateObject.queryForObject(getClearingMemberQuery, new Object[]{clearingMemberID}, new ClearingMemberMapper());
		return clearingMember;
	}

	@Override
	public void updateClearingMemberName(int clearingMemberID, String clearingMemberName) {
		// TODO Auto-generated method stub
		String updateClearingMemberNameQuery = "UPDATE clearing_member SET clearing_member_name = ? WHERE clearing_member_id = ?";
		jdbcTemplateObject.update(updateClearingMemberNameQuery, clearingMemberName, clearingMemberID);
		System.out.println("Clearing Member ID " + clearingMemberID + "'s name changed to: " + clearingMemberName);
	}

	@Override
	public void updateClearingMemberFundBalance(int clearingMemberID, double clearingMemberOpeningFundBalance) {
		// TODO Auto-generated method stub
		String updateClearingMemberFundQuery = "UPDATE clearing_member SET opening_fund_balance = ? WHERE clearing_member_id = ?";
		jdbcTemplateObject.update(updateClearingMemberFundQuery, clearingMemberOpeningFundBalance, clearingMemberID);
		System.out.println("Clearing Member ID " + clearingMemberID + "'s funds changed to: " + clearingMemberOpeningFundBalance);		
	}

	@Override
	public void deleteClearingMember(int clearingMemberID) {
		// TODO Auto-generated method stub
		String deleteClearingMemberQuery = "DELETE FROM clearing_member WHERE clearing_member_id = ?";
		jdbcTemplateObject.update(deleteClearingMemberQuery, clearingMemberID);
		System.out.println("Deleted Record ID = " + clearingMemberID);
		
		// Resetting Auto-increment value accordingly
		String autoIncrementCurrentQuery = "SELECT MAX(clearing_member_id) AS max FROM clearing_member";
		int resetID = jdbcTemplateObject.queryForObject(autoIncrementCurrentQuery, Integer.class) + 1;
		String autoIncrementResetQuery = "ALTER TABLE clearing_member AUTO_INCREMENT = ?";
		jdbcTemplateObject.update(autoIncrementResetQuery, resetID);
		System.out.println("Auto-increment reset complete: " + resetID);
	}

	@Override
	public List<ClearingMember> getAllClearingMembers() {
		// TODO Auto-generated method stub
		String getAllClearingMembersQuery = "SELECT * FROM clearing_member";
		List<ClearingMember> allClearingMembers = jdbcTemplateObject.query(getAllClearingMembersQuery, new ClearingMemberMapper());
		return allClearingMembers;
	}
	
	
}
