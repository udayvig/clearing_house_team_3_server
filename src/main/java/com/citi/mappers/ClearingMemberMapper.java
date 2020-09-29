package com.citi.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.citi.bean.ClearingMember;

public class ClearingMemberMapper implements RowMapper<ClearingMember>{

		@Override
		public ClearingMember mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			ClearingMember clearingMember = new ClearingMember();
			clearingMember.setClearingMemberID(rs.getInt("clearing_member_id"));
			clearingMember.setClearingMemberName(rs.getString("clearing_member_name"));
			clearingMember.setClearingMemberOpeningFundBalance(rs.getInt("opening_fund_balance"));
			
			return clearingMember;

		}

}