package com.citi.servicebeans;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class FundObligation {
	private HashMap<Integer, Double> fundObligation;
	private HashMap<String, Double> fundObligationDisplay;
	
	public HashMap<Integer, Double> getFundObligation() {
		return fundObligation;
	}
	public HashMap<String, Double> getFundObligationDisplay() {
		return fundObligationDisplay;
	}
	
}
