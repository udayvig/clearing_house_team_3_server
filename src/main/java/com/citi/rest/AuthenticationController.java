package com.citi.rest;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.citi.service.AuthenticationService;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "*") 
public class AuthenticationController {
	
	@Autowired
	AuthenticationService authenticationService;
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/login")
	@ResponseBody
	public HashMap<String, String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
		return authenticationService.authenticate(username, password);
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/logout")
	@ResponseBody
	public void logout(@RequestParam("token") String token) {
		authenticationService.logout(token);
	}
}
