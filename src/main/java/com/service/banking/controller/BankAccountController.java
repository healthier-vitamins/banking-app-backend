package com.service.banking.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.banking.exception.EmptyDatabaseException;
import com.service.banking.exception.UsernameIsTakenException;
import com.service.banking.model.BankAccount;
import com.service.banking.service.BankAccountService;

@RestController
@RequestMapping("/api/bank-acc")
@CrossOrigin(origins = "http://localhost:4200")
public class BankAccountController {
	
	@Autowired
	private BankAccountService bankAccService;
	
	@PostMapping("/save-user")
	public ResponseEntity<?> saveBankAccAndUser(@RequestBody BankAccount bankAcc) {
		BankAccount savedBankAcc;
		try {
			savedBankAcc = bankAccService.saveBankAccAndUser(bankAcc);
		} catch (UsernameIsTakenException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Username taken");
		}
		return ResponseEntity.ok(savedBankAcc);
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<?> getAllBankAcc() {
		List<BankAccount> listOfBankAccs;
		try {
			listOfBankAccs = bankAccService.getAllBankAccounts();
		} catch (EmptyDatabaseException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Database is empty");
		}
		return ResponseEntity.ok().body(listOfBankAccs);
	}
	
	@PutMapping("/update-bank-acc")
	public ResponseEntity<?> updateBankAcc(@RequestBody BankAccount bankAcc) {
		BigDecimal tempBig = new BigDecimal(bankAcc.getAccBal());
		Currency sgd = Currency.getInstance("SGD");
		NumberFormat currency = DecimalFormat.getCurrencyInstance();
		currency.setCurrency(sgd);
//		System.out.println(Float.parseFloat(currency.format(bankAcc.getAccBal())));
		bankAcc.setAccBal(currency.format(tempBig));
		try {
			return ResponseEntity.ok(bankAccService.updateBankAcc(bankAcc));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
		}
	}
}
