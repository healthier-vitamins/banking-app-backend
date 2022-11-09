package com.service.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.banking.model.Customer;
import com.service.banking.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
	
	@Autowired
	private CustomerService custService;
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Customer> getById(@PathVariable Long id) {
		return ResponseEntity.ok().body(custService.findById(id));
	}
	
}
