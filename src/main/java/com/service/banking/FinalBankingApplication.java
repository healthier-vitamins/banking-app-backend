package com.service.banking;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.service.banking.model.BankAccount;
import com.service.banking.model.Customer;
import com.service.banking.model.Role;
import com.service.banking.model.User;
import com.service.banking.repo.CustomerRepo;
import com.service.banking.service.OfferService;
import com.service.banking.service.UserService;
import com.service.banking.utility.DateFormatterUtil;

//@ServletComponentScan
// was for AddResponseHeaderFilter from Baeldung

@SpringBootApplication
public class FinalBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalBankingApplication.class, args);
	}

	// HOLY SHEET
	// https://stackoverflow.com/questions/40418441/spring-security-cors-filter
//	@SuppressWarnings("deprecation")
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("http://localhost:4200");
//			}
//		};
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService, OfferService offerService) {
		return args -> {
			Role userRole = userService.saveRole(new Role("ROLE_USER"));
			Role adminRole = userService.saveRole(new Role("ROLE_ADMIN"));
//			Set<Role> userRoleSet = new HashSet<Role>(); userRoleSet.add(userRole);
//			Set<Role> adminRoleSet = new HashSet<Role>(); adminRoleSet.add(adminRole);
//			
//			String encodedPw = passwordEncoder().encode("Aa@123");
			
			
//			userService.saveUser(new User(null, "admin", "admin-user", encodedPw, null, null));
//			userService.addRoleToUser("admin-user", "ROLE_ADMIN");

			// credit card
//			BankAccount bankAcc1 = new BankAccount(null, "Savings", 10000f, DateFormatterUtil.convertDateStringToMillisString("2011-01-01 09:09:09:09"));
//			BankAccount bankAcc1 = new BankAccount(null, "Savings", 5000f, DateFormatterUtil.convertDateStringToMillisString("2017-01-01 09:09:09:09"));
//			BankAccount bankAcc1 = new BankAccount(null, "Savings", 7000f, DateFormatterUtil.convertDateStringToMillisString("2020-01-01 09:09:09:09"));
			
			// home loan
//			BankAccount bankAcc1 = new BankAccount(null, "Savings", 7000f, DateFormatterUtil.convertDateStringToMillisString("2020-01-01 09:09:09:09"));
//			BankAccount bankAcc1 = new BankAccount(null, "Current", 7000f, DateFormatterUtil.convertDateStringToMillisString("2020-01-01 09:09:09:09"));
			
			// car loan
//			BankAccount bankAcc1 = new BankAccount(null, "Savings", 10000f, DateFormatterUtil.convertDateStringToMillisString("2020-01-01 09:09:09:09"));
//			BankAccount bankAcc1 = new BankAccount(null, "Current", 15000f, DateFormatterUtil.convertDateStringToMillisString("2020-01-01 09:09:09:09"));
//			Customer cust1 = new Customer(null, "firstName", "lastName", "city", "12345678", bankAcc1, null);
//			User user1 = new User(null, "user", "user-user", encodedPw, cust1, null);
//			user1 = userService.saveUser(user1);
			
			
			
		};
	}

}
