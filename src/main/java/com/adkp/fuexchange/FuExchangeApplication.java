package com.adkp.fuexchange;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class FuExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuExchangeApplication.class, args);
	}

}
