package com.service.banking.service;

import java.text.ParseException;
import java.util.List;

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
		
//		Long accCreatedMillisLong = DateFormatterUtility.convertDateStringToMillisLong(bankAcc.getAccCreationDate());
//		Long currentDateMilissLong = DateFormatterUtility.currentDateInLong();
//		Long accCreatedDuration = currentDateMilissLong - accCreatedMillisLong;
		
//		Offer offerRes = new Offer(null, "Credit Card", null, null, null, null, null, null);
//		float annualFee;
		
//		if(bankAccBal >= 10000f) {
//			annualFee = 50f;
//		} else if(bankAccBal >= 7000f) {
//			annualFee = 60f;
//		} else {
//			annualFee = 100f;
//		}
//		offerRes.setAnnualFee(annualFee);
		
//		float interestRatePercent;
//		if(accCreatedDuration >= tenYearMilliS) {
//			interestRatePercent = 6f;
//		} else if(accCreatedDuration >= fiveYearMilliS) {
//			interestRatePercent = 7f;
//		} else {
//			interestRatePercent = 10f;
//		}
//		offerRes.setInterestRatePercent(interestRatePercent);
		
//		float interestFreeWithdrawal;
//		if(bankAccBal >= 10000f && accCreatedDuration >= tenYearMilliS) {
//			interestFreeWithdrawal = 1000f;
//		} else if(bankAccBal >= 5000f && accCreatedDuration >= fiveYearMilliS) {
//			interestFreeWithdrawal = 500f;
//		} else {
//			interestFreeWithdrawal = 200f;
//		}
//		offerRes.setInterestFreeCashWithdrawal(interestFreeWithdrawal);
//		
//		return ResponseEntity.ok().body(offerRes);
//		return null;
	}
	
	
}