package com.service.banking.service;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.banking.exception.UsernameIsTakenException;
import com.service.banking.model.BankAccount;
import com.service.banking.model.Role;
import com.service.banking.model.User;
import com.service.banking.repo.BankAccountRepo;
import com.service.banking.repo.RoleRepo;
import com.service.banking.utility.DateFormatterUtil;

@Service
public class BankAccountServiceImpl implements BankAccountService{
	
	@Autowired
	private BankAccountRepo bankAccRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private UserService userService;

	@Override
	public BankAccount saveBankAccAndUser(BankAccount bankAcc) throws UsernameIsTakenException {
		
		if(isUsernameTaken(bankAcc.getCustomer().getCustFirstName() + "-user")) throw new UsernameIsTakenException(bankAcc.getCustomer().getCustFirstName() + "-user");
		
		bankAcc.setAccCreationDate(DateFormatterUtil.currentDateInString());
		BankAccount savedBankAcc = bankAccRepo.save(bankAcc);
		Role role = roleRepo.getByName("ROLE_USER"); 
		Set<Role> roleSet = new HashSet<Role>();
		roleSet.add(role);
		User user = new User(savedBankAcc.getAccId(), savedBankAcc.getCustomer().getCustFirstName(), savedBankAcc.getCustomer().getCustFirstName() + "-user", "Aa@123", roleSet);
		userService.encodeAndSaveUser(user);
		return savedBankAcc;
	}

	@Override
	public BankAccount updateBankAcc(BankAccount bankAcc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delBankAccAndUser(Long bankAccId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BankAccount getBankAccountById(Long bankAccId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankAccount> getAllBankAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isUsernameTaken(String username) {
		Optional<User> foundUser;
		try {
			foundUser = Optional.of(userService.getByUsername(username));
		} catch (Exception e) {
			return false;
		}
		if(foundUser.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
}
