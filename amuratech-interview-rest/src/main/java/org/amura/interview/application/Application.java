package org.amura.interview.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"org.amura.interview.config"})
public class Application {
	
	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}
	
}
