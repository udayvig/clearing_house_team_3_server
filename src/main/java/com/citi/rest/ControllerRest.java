package com.citi.rest;

import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ControllerRest {

	@RequestMapping(produces = MediaType.TEXT_HTML, method = RequestMethod.GET, value = "")
	@ResponseBody

	public String index() {
		return "Hello World!";
	}
}
