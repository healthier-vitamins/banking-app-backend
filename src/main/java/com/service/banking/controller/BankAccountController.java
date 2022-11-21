package com.service.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.banking.exception.BankAccIdNotFoundException;
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
	@PreAuthorize("hasAnyRole('ADMIN')")
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
	@PreAuthorize("hasAnyRole('ADMIN')")
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
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> updateBankAcc(@RequestBody BankAccount bankAcc) {
		try {
			return ResponseEntity.ok(bankAccService.updateBankAcc(bankAcc));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
		}
	}
	
	@DeleteMapping("/del-bank-acc/{bankAccId}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> delBankAcc(@PathVariable Long bankAccId) {
		try {
			bankAccService.delBankAccAndUser(bankAccId);
			return ResponseEntity.ok().body(bankAccId + " deleted");
		} catch (BankAccIdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid bank account.");
		}
	}
	
	@GetMapping("/bank-acc-count")
	public ResponseEntity<?> getBankAccCount() {
		return ResponseEntity.ok(bankAccService.getBankAccCount());
	}
	
}
