package com.lvtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSiteApplication.class, args);
		System.out.println("Welcome to LVtech");
	}

}
