package br.com.saks.interesseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InteresseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InteresseServiceApplication.class, args);
	}

}
