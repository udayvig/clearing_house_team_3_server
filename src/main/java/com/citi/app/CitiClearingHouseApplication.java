package com.citi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.citi.config.ProjectConfig;
import com.citi.dao.TradeDAO;
import com.citi.dao.TradeDAOImpl;
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
		//rdg.generateInterestRateForFunds();
		//rdg.generateTrades(10);
		//rdg.generateStockBorrowingRates();
		//rdg.generateOpeningStockBalances();
		rdg.generateTradesEfficient(100);
		
//		rdg.generateTradeList(10);

//		tradeDAO.addTradeList(rdg.generateTradeList(10));
//		rdg.generateOpeningStockBalances();
//		StockObligation stockObligation = context.getBean(StockObligation.class);
//		FundObligation fundObligation = context.getBean(FundObligation.class);
//		
//		stockObligation.initialise();
//		fundObligation.initFundObligation();
//		
//		stockObligation.setStockObligation();
//		fundObligation.setFundObligationDisplay();
//		
//		System.out.println(stockObligation.getStockObligationDisplay());
//		System.out.println(fundObligation.getFundObligationDisplay());

		ClearingHouseService cls = context.getBean(ClearingHouseService.class);
		cls.initialise();
//
		ClearingMemberService cms = context.getBean(ClearingMemberService.class);
		cms.initialise();

//		fundObligation.initFundObligation();
//		fundObligation.setFundObligationDisplayList();
//
//		fundObligation.initFundShortage();
//		fundObligation.setFundShortageDisplayList();
//
//		StockObligation stockObligation = context.getBean(StockObligation.class);
//
//		stockObligation.initialise();
//		stockObligation.generateStockObligationReport();
//		stockObligation.setShortage();
//
//		System.out.println(stockObligation.getStockShortage());
//
//		CostSettlement costSettlement = context.getBean(CostSettlement.class);
//
//		System.out.println(costSettlement.generateCostSettlementReport(1).toString());
//
//		System.out.println(fundObligation.getFundShortageDisplayList().toString());
	}

}