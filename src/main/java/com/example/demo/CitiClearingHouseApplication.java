package com.example.demo;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.citi.bean.Stock;
import com.citi.config.ProjectConfig;
import com.citi.dao.StockDAO;
import com.citi.dao.StockDAOImpl;

@SpringBootApplication
@Configuration
@ComponentScan({"com.citi.*"})
@EnableWebMvc
@EnableAutoConfiguration
public class CitiClearingHouseApplication {

	public static void main(String[] args) {
		//SpringApplication.run(CitiClearingHouseApplication.class, args);
		ApplicationContext context=SpringApplication.run(ProjectConfig.class, args);
		StockDAO test = context.getBean(StockDAOImpl.class);
		
		//test.addStock("trial3", 0.8, 0);
		
		/*
		 * List<Stock> stockList=test.getAllStocksList(); 
		 * for(Stock stock : stockList){
		 * System.out.println(stock.getStockID());
		 * System.out.println(stock.getStockName());
		 * System.out.println(stock.getBorrowingRate());
		 * System.out.println(stock.getCorporateAction()); 
		 * System.out.println(" ");
		 *  }
		 */		
		
		/*
		 * Stock stock = test.getStock(1); System.out.println(stock.getStockID());
		 * System.out.println(stock.getStockName());
		 * System.out.println(stock.getBorrowingRate());
		 * System.out.println(stock.getCorporateAction());
		 */
		
		//test.updateStockBorrowingRate(1, 0.6 );
		
		//test.updateStockCorporateAction(2, 1);
		
		//test.updateStockName(5, "Flipkart");
		
		//test.deleteStock(6);
		
	}

}
