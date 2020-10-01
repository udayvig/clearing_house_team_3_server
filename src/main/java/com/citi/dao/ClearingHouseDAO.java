package com.citi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClearingHouseDAO {
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	public void updateCHSessionToken(int cmID, String token) {
		String SQL = "update clearing_house set session_token = ? where clearing_house_id = ?";
		jdbcTemplateObject.update(SQL, token, cmID);
	}
	
	public int getCHIDFromToken(String token) {
		String SQL = "select clearing_house_id from clearing_house where session_token = ?";
		return jdbcTemplateObject.queryForObject(SQL, Integer.class, new Object[] {token});
	}
}
