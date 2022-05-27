package br.com.saks.serverimobiliaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServerImobiliariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerImobiliariaApplication.class, args);
	}

}
