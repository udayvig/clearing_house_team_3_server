package com.citi.servicebeans;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class StockObligation {
	private HashMap<Integer, HashMap<Integer, Integer>> stockObligation;
	private HashMap<String, HashMap<String, Integer>> stockObligationDisplay;
	
	public HashMap<Integer, HashMap<Integer, Integer>> getStockObligation() {
		return stockObligation;
	}
	public HashMap<String, HashMap<String, Integer>> getStockObligationDisplay() {
		return stockObligationDisplay;
	}
	
}
