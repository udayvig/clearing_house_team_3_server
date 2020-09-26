package com.citi.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@ComponentScan({ "com.citi.*" })
@EnableAutoConfiguration
public class ProjectConfig {
//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		// MySQL database we are using
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql://db-clearing-and-settlement.ccijxu77k0xg.us-west-2.rds.amazonaws.com:3306/db_clearing_and_settlement");
//		dataSource.setUsername("admin");
//		dataSource.setPassword("admin123");
//
//		return dataSource;
//	}

}
