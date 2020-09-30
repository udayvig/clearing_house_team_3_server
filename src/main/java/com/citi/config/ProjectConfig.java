package com.citi.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@ComponentScan({ "com.citi.*" })
@EnableAutoConfiguration
@EnableWebMvc
public class ProjectConfig {

}