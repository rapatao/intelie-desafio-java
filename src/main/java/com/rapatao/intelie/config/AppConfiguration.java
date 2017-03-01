package com.rapatao.intelie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.rapatao.intelie")
@EnableWebMvc
public class AppConfiguration {
}
