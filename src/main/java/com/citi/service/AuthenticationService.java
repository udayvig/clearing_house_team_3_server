package com.citi.service;

import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.bean.ClearingMember;
import com.citi.dao.AuthenticationDAO;
import com.citi.dao.ClearingHouseDAO;
import com.citi.dao.ClearingMemberDAO;

@Service
public class AuthenticationService {
	
	@Autowired
	private ClearingMemberDAO clearingMemberDAO;
	
	@Autowired
	private ClearingHouseDAO clearingHouseDAO;
	
	@Autowired
	private AuthenticationDAO authenticationDAO;
	
	public HashMap<String, String> authenticate(String username, String password) {
		String pass = authenticationDAO.getCredentials(username);
		HashMap<String, String> map = new HashMap<>();
		if(password.equals(pass)) {
			int cmid = authenticationDAO.getCMID(username);
			//String sessionToken = new java.rmi.server.UID().toString().substring(0, 20).trim().replaceAll(":", "_");
			
			int leftLimit = 48;
		    int rightLimit = 122;
		    int targetStringLength = 20;
		    Random random = new Random();
		 
		    String generatedString = random.ints(leftLimit, rightLimit + 1)
		      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
		      .limit(targetStringLength)
		      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		      .toString();
		 
		    String sessionToken = generatedString;
		    
			if(cmid == 0) {
				clearingHouseDAO.updateCHSessionToken(0, sessionToken);
			}else {
				clearingMemberDAO.updateSessionToken(cmid, sessionToken);
			}
			
			map.put("token", sessionToken);
			if(cmid == 0) {
				map.put("type", "clearing_house");
			}else {
				map.put("type", "clearing_member");
			}
			return map;
		}
		
		map.put("token", "invalid_credentials");
		map.put("type", "invalid_credentials");
		return map;
	}
	
	public void logout(String sessionToken) {
		ClearingMember user = clearingMemberDAO.getClearingMemberFromToken(sessionToken);
		if(user.getClearingMemberID() != 0) {
			clearingMemberDAO.updateSessionToken(user.getClearingMemberID(), "default");
		}else {
			clearingHouseDAO.updateCHSessionToken(0, "default");
		}
	}
}
