package br.com.saks.tipoImovelservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TipoImovelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TipoImovelServiceApplication.class, args);
	}

}
