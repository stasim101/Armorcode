package com.personal.bandit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BanditApplication {
//https://www.citusdata.com/blog/2018/02/13/using-hibernate-and-spring-to-build-multitenant-java-apps/
//https://github.com/jkutner/spring-boot-multi-tenancy/tree/master/src/main/java/com/example
	public static void main(String[] args) {
		SpringApplication.run(BanditApplication.class, args);
	}

}
