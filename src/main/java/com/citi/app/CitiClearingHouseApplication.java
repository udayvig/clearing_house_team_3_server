package com.citi.app;

import java.util.Arrays;

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
import com.citi.servicebeans.FundObligation;

@SpringBootApplication
@Configuration
@ComponentScan({"com.citi.*"})
@EnableWebMvc
@EnableAutoConfiguration
public class CitiClearingHouseApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(ProjectConfig.class, args);
//		RandomDataGeneration rdg = context.getBean(RandomDataGeneration.class);
//		rdg.generateTrades(10);
		FundObligation fundObligation = context.getBean(FundObligation.class);
		fundObligation.setFundObligationDisplayList();
		fundObligation.setFundShortage();
		System.out.println(fundObligation.getFundShortage());
//		System.out.println(Arrays.asList(fundObligation.getFundObligationDisplay()));
	}

}