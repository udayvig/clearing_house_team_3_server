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
		String addClearingMemberQuery = "INSERT INTO clearing_member(clearing_member_name, opening_fund_balance) VALUES (?, ?)";
		jdbcTemplateObject.update(addClearingMemberQuery, clearingMemberName, clearingMemberOpeningFundBalance);
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
	}

	@Override
	public void updateClearingMemberFundBalance(int clearingMemberID, double clearingMemberOpeningFundBalance) {
		// TODO Auto-generated method stub
		String updateClearingMemberFundQuery = "UPDATE clearing_member SET opening_fund_balance = ? WHERE clearing_member_id = ?";
		jdbcTemplateObject.update(updateClearingMemberFundQuery, clearingMemberOpeningFundBalance, clearingMemberID);		
	}

	@Override
	public void deleteClearingMember(int clearingMemberID) {
		// TODO Auto-generated method stub
		String deleteClearingMemberQuery = "DELETE FROM clearing_member WHERE clearing_member_id = ?";
		jdbcTemplateObject.update(deleteClearingMemberQuery, clearingMemberID);
		
		// Resetting Auto-increment value accordingly
		String autoIncrementCurrentQuery = "SELECT MAX(clearing_member_id) AS max FROM clearing_member";
		int resetID = jdbcTemplateObject.queryForObject(autoIncrementCurrentQuery, Integer.class) + 1;
		String autoIncrementResetQuery = "ALTER TABLE clearing_member AUTO_INCREMENT = ?";
		jdbcTemplateObject.update(autoIncrementResetQuery, resetID);
	}

	@Override
	public List<ClearingMember> getAllClearingMembers() {
		// TODO Auto-generated method stub
		String getAllClearingMembersQuery = "SELECT * FROM clearing_member";
		List<ClearingMember> allClearingMembers = jdbcTemplateObject.query(getAllClearingMembersQuery, new ClearingMemberMapper());
		return allClearingMembers;
	}
	
	@Override
	public void updateSessionToken(int cmID, String token) {
		String SQL = "update clearing_member set session_token = ? where clearing_member_id = ?";
		jdbcTemplateObject.update(SQL, token, cmID);
	}
	
	@Override
	public ClearingMember getClearingMemberFromToken(String sessionToken) {
		String SQL = "select * from clearing_member where session_token = ?";
		try {
			ClearingMember clearingMember = jdbcTemplateObject.queryForObject(SQL, new Object[] {sessionToken}, new ClearingMemberMapper());
			return clearingMember;
		}catch(Exception e) {
			e.printStackTrace();
			return new ClearingMember();
		}
	}
}
