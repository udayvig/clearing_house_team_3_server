package com.citi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.citi.bean.Authentication;
import com.citi.mappers.AuthenticationMapper;

@Repository
public class AuthenticationDAO {
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	public int getCMID(String username) {
		// TODO Auto-generated method stub
		String SQL = "select * from authentication where username = ?";
		Authentication authObject = jdbcTemplateObject.queryForObject(SQL, new Object[] {username}, new AuthenticationMapper());
		return authObject.getClearingMemberID();
	}

	public String getCredentials(String username) {
		// TODO Auto-generated method stub
		String SQL = "select * from authentication where username = ?";
		try {
			Authentication authObject = jdbcTemplateObject.queryForObject(SQL, new Object[] {username}, new AuthenticationMapper());
			return authObject.getPassword();
		} catch(Exception e) {
			e.printStackTrace();
			return "Invalid Credentials";
		}
	}
}
