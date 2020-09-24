package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@ComponentScan({"com.citi.*"})
@EnableWebMvc
@EnableAutoConfiguration
public class CitiClearingHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitiClearingHouseApplication.class, args);
	}

}
