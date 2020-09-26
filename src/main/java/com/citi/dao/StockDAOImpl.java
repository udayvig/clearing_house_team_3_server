package com.citi.dao;

import java.util.Random;

public class StockDAOImpl implements StockDAO {
	
	private static final double rangeMin = 0.5;
	private static final double rangeMax = 1.0;

	@Override
	public double generateRandomRate() {
		Random r = new Random();
		double rate = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		return rate;
	}
	
}
