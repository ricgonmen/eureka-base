package com.example.ricgonmen.cliente_balanceado_eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class ClienteBalanceadoEurekaApplication {
	@Autowired
	private RestTemplate plantillaRestBalanceada;
	
	public static void main(String[] args) {
		SpringApplication.run(ClienteBalanceadoEurekaApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate plantillaRestBalanceada() {
		return new RestTemplate();
	}
	
	@RequestMapping("/")
	public String llamaServicioBalanceado() {		
		return plantillaRestBalanceada.getForEntity("http://servicio-eureka", String.class).getBody();
	}
}
