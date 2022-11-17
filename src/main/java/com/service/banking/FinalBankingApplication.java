package com.service.banking;

import java.util.Arrays;
import java.util.List;

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
import com.service.banking.service.BankAccountService;
import com.service.banking.service.UserService;

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
	CommandLineRunner run(UserService userService, BankAccountService bankAccService) {
		return args -> {
			Role userRole = userService.saveRole(new Role("ROLE_USER"));
			Role adminRole = userService.saveRole(new Role("ROLE_ADMIN"));
			
			userService.encodeAndSaveUser(new User(0l, "admin", "admin-user", "Aa@123", null));
			userService.addRoleToUser("admin-user", "ROLE_ADMIN");
			userService.addRoleToUser("admin-user", "ROLE_USER");
			
			List<String> firstNames = Arrays.asList("user", "credit1", "credit2", "credit3", "homeLoan1", "homeLoan2", "carLoan1", "carLoan2");
			List<String> countries = Arrays.asList("singapore", "malaysia");
			List<String> numbers = Arrays.asList("12345678", "87654321");
			
			List<String> accTypes = Arrays.asList("Overseas", "Savings", "Savings", "Savings", "Savings", "Current", "Savings", "Current");
			
			List<String> accBals = Arrays.asList("SGD5000", "SGD10000", "SGD5000", "SGD7000", "SGD7000", "SGD7000", "SGD10000", "SGD15000");
//			List<Float> accBals = Arrays.asList(5000f ,10000f, 5000f, 7000f, 7000f, 7000f, 10000f, 15000f);
			List<String> accCreationDates = Arrays.asList("2022-01-01 09:09:09:09", "2011-01-01 09:09:09:09", "2017-01-01 09:09:09:09", "2020-01-01 09:09:09:09", "2020-01-01 09:09:09:09", "2020-01-01 09:09:09:09", "2020-01-01 09:09:09:09", "2020-01-01 09:09:09:09");
			
			
			for(int i = 0; i < firstNames.size(); i++) {
				Customer cust = new Customer(0l, firstNames.get(i), "lastName", countries.get(i%2), numbers.get(i%2), firstNames.get(i) + "@gmail.com" , null);
				BankAccount bankAcc = new BankAccount(0l, accTypes.get(i),accBals.get(i), accCreationDates.get(i), cust);
				bankAccService.saveBankAccAndUser(bankAcc);
			}
			
//			Customer cust1 = new Customer(0l, "user", "lastName", "singapore", "12345678", "user@gmail.com", null);
//			BankAccount bankAcc1 = new BankAccount(0l, "Current", 10000f, DateFormatterUtil.currentDateInString(), cust1);
//			bankAccService.saveBankAccAndUser(bankAcc1);
//			
//			Customer cust2 = new Customer(0l, "credit1", "lastName", "malaysia", "87654321", "user2@gmail.com", null);
//			BankAccount bankAcc2 = new BankAccount(0l, "Savings", 10000f, DateFormatterUtil.formatCustomDate("2011-01-01 09:09:09:09"), cust2);
//			bankAccService.saveBankAccAndUser(bankAcc2);
//			
//			Customer cust3 = new Customer(0l, "credit2", "lastName", "china", "12345678", "user3@gmail.com", null);
//			BankAccount bankAcc3 = new BankAccount(0l, "Savings", 5000f, DateFormatterUtil.formatCustomDate("2017-01-01 09:09:09:09"), cust3);
//			bankAccService.saveBankAccAndUser(bankAcc3);
//			
//			Customer cust4 = new Customer(0l, "credit3", "lastName", "singapore", "87654321", "user4@gmail.com", null);
//			BankAccount bankAcc4 = new BankAccount(0l, "Savings", 7000f, DateFormatterUtil.formatCustomDate("2020-01-01 09:09:09:09"), cust4);
//			bankAccService.saveBankAccAndUser(bankAcc3);
			
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
