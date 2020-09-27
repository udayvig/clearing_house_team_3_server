package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.citi.config.ProjectConfig;
import com.citi.dao.TradeDAO;
import com.citi.dao.TradeDAOImpl;

@SpringBootApplication
public class CitiClearingHouseApplication {

	public static void main(String[] args) {
		//SpringApplication.run(CitiClearingHouseApplication.class, args);
		ApplicationContext context = SpringApplication.run(ProjectConfig.class, args);
		
		TradeDAO trade = context.getBean(TradeDAOImpl.class);
		//trade.addTrade(1, 2, 300.123, 4, 5);
		trade.getTradeByTradeID(2);
		trade.getTradesByBuyingClearingMemberID(1);
		trade.getTradesBySellingClearingMemberID(2); trade.getTradesByStockID(5);
		trade.getAllTrades();
	}
}
