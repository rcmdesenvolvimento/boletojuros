package com.grid.info.boletosjuros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BoletosjurosApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoletosjurosApplication.class, args);
	}

}
