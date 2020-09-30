package com.citi.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.citi.bean.Authentication;

public class AuthenticationMapper implements RowMapper<Authentication> {

	@Override
	public Authentication mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Authentication authentication = new Authentication();
		authentication.setAuthentication_id(rs.getInt("authentication_id"));
		authentication.setUsername(rs.getString("username"));
		authentication.setPassword(rs.getString("password"));
		authentication.setRole(rs.getString("role"));
		authentication.setClearingMemberID(rs.getInt("clearing_member_id"));
		
		return authentication;
	}
}
