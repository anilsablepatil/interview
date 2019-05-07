package org.amura.interview.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
		"org.amura.interview.config", 
		"org.amura.interview.service.impl",
		"org.amura.interview.controller"})
public class ApplicationConfiguration {
	
}
