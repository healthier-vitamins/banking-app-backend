package com.service.banking.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.service.banking.exception.BankAccIdNotFoundException;
import com.service.banking.model.BankAccount;
import com.service.banking.model.Customer;
import com.service.banking.model.Offer;
import com.service.banking.repo.CustomerRepo;
import com.service.banking.repo.OfferRepo;
import com.service.banking.utility.CreditCardUtility;
import com.service.banking.utility.DateFormatterUtility;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	private BankAccountService bankAccService;

	@Autowired
	private OfferRepo offerRepo;

	@Override
	public Offer save(Offer offer) {
		return offerRepo.save(offer);
	}

	@Override
	public void deleteById(Long id) {
		offerRepo.deleteById(id);
	}

	@Override
	public Offer getById(Long id) {
		return offerRepo.getReferenceById(id);
	}

	@Override
	public Offer getByName(String name) {
		return offerRepo.findByName(name);
	}

	@Override
	public List<Offer> getAll() {
		return offerRepo.findAll();
	}

	@Override
	public Offer getCreditCard(Long bankAccId) throws BankAccIdNotFoundException, ParseException {
		BankAccount bankAcc = bankAccService.getBankAccountById(bankAccId);
		return CreditCardUtility.getCreditCardOffer(bankAcc);

	}

	public Boolean hasCreditCardOffer(Long bankAccId) throws BankAccIdNotFoundException {
		List<Offer> listOfOffers = bankAccService.getBankAccountById(bankAccId).getCustomer().getOffers();
		return listOfOffers.stream().anyMatch(offer -> offer.getOfferName().equalsIgnoreCase("Credit Card"));
	}
	
	public Object getCreditCardOffer(Long bankAccId) throws BankAccIdNotFoundException {
		return bankAccId;
	}
}