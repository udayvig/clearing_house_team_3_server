package com.citi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.citi.config.ProjectConfig;
import com.citi.servicebeans.FundObligation;
import com.citi.servicebeans.StockObligation;

@SpringBootApplication
@Configuration
@ComponentScan({"com.citi.*"})
@EnableWebMvc
@EnableAutoConfiguration
public class CitiClearingHouseApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(ProjectConfig.class, args);
//		RandomDataGeneration rdg = context.getBean(RandomDataGeneration.class);
//		
//		rdg.generateTrades(10);
		
		StockObligation stockObligation = context.getBean(StockObligation.class);
		FundObligation fundObligation = context.getBean(FundObligation.class);
		
		stockObligation.initialise();
		fundObligation.initFundObligation();
		
		stockObligation.setStockObligation();
		fundObligation.setFundObligationDisplay();
		
		System.out.println(stockObligation.getStockObligationDisplay());
		System.out.println(fundObligation.getFundObligationDisplay());
	}

}