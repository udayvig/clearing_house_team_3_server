package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.citi.config.ProjectConfig;
import com.citi.dao.OpeningStockBalanceDAO;
import com.citi.dao.OpeningStockBalanceDAOImpl;

@SpringBootApplication
public class CitiClearingHouseApplication {

	public static void main(String[] args) {
//		SpringApplication.run(CitiClearingHouseApplication.class, args);
		
		ApplicationContext context=SpringApplication.run(ProjectConfig.class, args);
		
		OpeningStockBalanceDAO test = context.getBean(OpeningStockBalanceDAOImpl.class);
		
		test.addOpeningStockBalance(1, 200, 300);
		
//		test.updateStockBalanceQuantity(1, 99);
		System.out.println(test.getOpeningStockBalanceByClearingMemberID(1));
	}

}
