package com.service.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.banking.model.Customer;
import com.service.banking.repo.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepo custRepo;
	
	@Override
	public Customer findById(Long custId) {
		return custRepo.getReferenceById(custId);
	}

}
