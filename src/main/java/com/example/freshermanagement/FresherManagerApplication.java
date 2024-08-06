package com.example.freshermanagement;

import com.example.freshermanagement.repository.AreaRepository;
import com.example.freshermanagement.repository.LanguageRepository;
import com.example.freshermanagement.repository.RankRepository;
import com.example.freshermanagement.repository.RoleRepository;
import com.example.freshermanagement.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.example.fresher_manager"})
public class FresherManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FresherManagerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(AuthService authService, AreaRepository areaRepository, LanguageRepository languageRepository, RankRepository rankRepository, RoleRepository roleRepository){
		return args ->{

		};
	}

}
