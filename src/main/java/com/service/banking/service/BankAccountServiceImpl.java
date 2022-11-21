package com.service.banking.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.banking.exception.BankAccIdNotFoundException;
import com.service.banking.exception.EmptyDatabaseException;
import com.service.banking.exception.UsernameIsTakenException;
import com.service.banking.model.BankAccount;
import com.service.banking.model.Role;
import com.service.banking.model.User;
import com.service.banking.repo.BankAccountRepo;
import com.service.banking.repo.RoleRepo;
import com.service.banking.utility.AccBalanceUtility;
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
		if(bankAcc.getAccCreationDate() == null) bankAcc.setAccCreationDate(DateFormatterUtil.currentDateInString()); 
		String temp = AccBalanceUtility.formatAccBal(bankAcc.getAccBal());
		bankAcc.setAccBal(temp);
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
		String temp = AccBalanceUtility.formatAccBal(bankAcc.getAccBal());
		bankAcc.setAccBal(temp);
		return bankAccRepo.save(bankAcc);
	}

	@Override
	public void delBankAccAndUser(Long bankAccId) throws BankAccIdNotFoundException {
		if(!bankAccRepo.existsById(bankAccId)) throw new BankAccIdNotFoundException(bankAccId);
		bankAccRepo.deleteById(bankAccId);
//		userService.deleteById(bankAccId);
	}

	@Override
	public BankAccount getBankAccountById(Long bankAccId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankAccount> getAllBankAccounts() throws EmptyDatabaseException {
		Optional<List<BankAccount>> listOfBankAccs = Optional.of(bankAccRepo.findAll());
		if(listOfBankAccs.isEmpty()) throw new EmptyDatabaseException();
		return listOfBankAccs.get();
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
	
	public Long getBankAccCount() {
		return bankAccRepo.count();
	}
	
}
