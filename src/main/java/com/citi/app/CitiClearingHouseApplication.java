package com.citi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.citi.config.ProjectConfig;
import com.citi.datageneration.RandomDataGeneration;
import com.citi.service.ClearingHouseService;
import com.citi.service.ClearingMemberService;
import com.citi.servicebeans.FundObligation;
import com.citi.servicebeans.StockObligation;

@SpringBootApplication
@Configuration
@ComponentScan({ "com.citi.*" })
@EnableWebMvc
@EnableAutoConfiguration
public class CitiClearingHouseApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ProjectConfig.class, args);
		RandomDataGeneration rdg = context.getBean(RandomDataGeneration.class);
		rdg.initialise();
		rdg.generateInterestRateForFunds();
		rdg.generateStockBorrowingRates();
		rdg.generateOpeningStockBalances();
		rdg.generateTradesEfficient(100);
		rdg.generateOpeningFundBalances();
		rdg.generateCorporateActionFactors();

		StockObligation stockObligation = context.getBean(StockObligation.class);
		FundObligation fundObligation = context.getBean(FundObligation.class);
		
		stockObligation.initialise();
		stockObligation.setStockObligation();
		stockObligation.generateStockObligationReport();
		stockObligation.setShortage();
		
		fundObligation.initFundObligation();
		fundObligation.initFundShortage();
		fundObligation.setFundShortageDisplayList();
		
		ClearingHouseService cls = context.getBean(ClearingHouseService.class);
		cls.initialise();

		ClearingMemberService cms = context.getBean(ClearingMemberService.class);
		cms.initialise();
	}
}