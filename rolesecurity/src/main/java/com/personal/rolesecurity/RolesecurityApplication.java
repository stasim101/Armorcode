package com.personal.rolesecurity;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.personal.rolesecurity.model.Role;
import com.personal.rolesecurity.model.User;
import com.personal.rolesecurity.repository.RoleRepository;
import com.personal.rolesecurity.repository.UserRepository;

@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.personal.rolesecurity.*" })
public class RolesecurityApplication {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(RolesecurityApplication.class, args);
	}

	@Bean
	InitializingBean addInitialData() {
		return () -> {
			
			userRepository.deleteAll();
			roleRepository.deleteAll();
			
			Role role = roleRepository.save(new Role("admin", "token", "google"));
			userRepository.save(new User("test", "oauthuser111@gmail.com", role));
		};
	}

}
