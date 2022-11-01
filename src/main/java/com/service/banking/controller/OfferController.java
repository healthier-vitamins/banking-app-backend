package com.service.banking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.banking.model.Offer;
import com.service.banking.service.OfferService;

@RestController
@RequestMapping("/api/offer")
@CrossOrigin(origins = "http://localhost:4200")
public class OfferController {

	@Autowired
	private OfferService offerService;
	
//	multiple hasAnyRoles
//	https://stackoverflow.com/questions/57247649/multiple-roles-using-preauthorize
	
	@PostMapping("/save")
	public ResponseEntity<Offer> saveOffer(@RequestBody Offer offer) {
		return ResponseEntity.ok().body(offerService.save(offer));
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable Long id) {
		offerService.deleteById(id);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Offer> getById(@PathVariable Long id) { 
		return ResponseEntity.ok().body(offerService.getById(id));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Offer>> getAllOffers() {
		return ResponseEntity.ok().body(offerService.getAll());
	}
	
	@GetMapping("/cust-all/{custId}")
	public ResponseEntity<List<Offer>> getAllOffersFromCustId(@PathVariable Long custId) {
		List<Offer> offers = offerService.getAll(); 
		List<Offer> finalOffers = new ArrayList<Offer>();
//		Customer cust = custRepo.getReferenceById(custId);
		for(int i = 0; i < offers.size(); i++) {
			if(offers.get(i).getCustId() == custId) {
				finalOffers.add(offers.get(i));
			}
		}
		return ResponseEntity.ok().body(finalOffers);
	}
	
//	@PostMapping("/credit-card")
//	public ResponseEntity<Offer> getCreditCardOffer(@RequestBody BankAccount bankAcc) {
//		float bankAccBal = bankAcc.getAccBal();
//		Long accCreatedMillisLong = DateFormatterUtil.convertDateStringToMillisLong(bankAcc.getAccCreationDate());
//		Long currentDateMilissLong = DateFormatterUtil.currentDateInLong();
//		Long accCreatedDuration = currentDateMilissLong - accCreatedMillisLong;
////		System.out.println("created: " + accCreatedMillisLong);
////		System.out.println("current: " + currentDateMilissLong);
////		System.out.println("difference: " + accCreatedDuration);
//		Long tenYearMilliS = 315569260000l;
//		Long fiveYearMilliS = 157784630000l;
//		Offer offerRes = new Offer(null, "Credit Card", null, null, null, null, null, null);
//		float annualFee;
//		
//		if(bankAccBal >= 10000f) {
//			annualFee = 50f;
//		} else if(bankAccBal >= 7000f) {
//			annualFee = 60f;
//		} else {
//			annualFee = 100f;
//		}
//		offerRes.setAnnualFee(annualFee);
//		
//		float interestRatePercent;
//		if(accCreatedDuration >= tenYearMilliS) {
//			interestRatePercent = 6f;
//		} else if(accCreatedDuration >= fiveYearMilliS) {
//			interestRatePercent = 7f;
//		} else {
//			interestRatePercent = 10f;
//		}
//		offerRes.setInterestRatePercent(interestRatePercent);
//		
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
//	}
//	
//	@PostMapping("/home-loan")
//	public ResponseEntity<Offer> getHomeLoanOffer(@RequestBody User user) {
//		String accType = user.getCustomer().getBankAcc().getAccType();
//		
//		Offer offerRes = new Offer(null, "Home Loan", null, null, null, null, null, null);
//
//		float loanAmnt;
//		if(accType.equalsIgnoreCase("current")) {
//			loanAmnt = 25000f;
//		} else {
//			loanAmnt = 20000f;
//		}
//		offerRes.setLoanAmnt(loanAmnt);
//		
//		float interestRatePercent;
//		if(loanAmnt >= 25000f) {
//			interestRatePercent = 6f;
//		} else if(loanAmnt >= 20000f) {
//			interestRatePercent = 7f;
//		} else {
//			interestRatePercent = 10f;
//		}
//		offerRes.setInterestRatePercent(interestRatePercent);
//		
//		float preclosureCharges;
//		if(interestRatePercent >= 7f) {
//			preclosureCharges = 100f;
//		} else if(interestRatePercent >= 6f) {
//			preclosureCharges = 80f;
//		} else {
//			preclosureCharges = 200f;
//		}
//		offerRes.setPreclosureCharges(preclosureCharges);
//		
//		return ResponseEntity.ok().body(offerRes);
//	}
//	
//	@PostMapping("/car-loan")
//	public ResponseEntity<Offer> getCarLoanOffer(@RequestBody User user) {
//		String accType = user.getCustomer().getBankAcc().getAccType();
//		float accBal = user.getCustomer().getBankAcc().getAccBal();
//		Offer offerRes = new Offer(null, "Car Loan", null, null, null, null, null, null);
//		
//		float fixedloanAmnt = 1000f;
//		float loanAmnt;
//		if(accType.equalsIgnoreCase("current")) {
//			loanAmnt = fixedloanAmnt + 2500f;
//		} else if(accType.equalsIgnoreCase("savings")){
//			loanAmnt = fixedloanAmnt + 2000f;
//		} else {
//			loanAmnt = fixedloanAmnt;
//		}
//		offerRes.setLoanAmnt(loanAmnt);
//		
//		float fixedInterestRatePercent = 6f;
//		float interestRatePercent;
//		if(accBal >= 15000f) {
//			interestRatePercent = fixedInterestRatePercent + 1.2f;
//		} else if(accBal >= 10000f) {
//			interestRatePercent = fixedInterestRatePercent + 1.5f;
//		} else {
//			interestRatePercent = fixedInterestRatePercent + 3f;
//		}
//		offerRes.setInterestRatePercent(interestRatePercent);
//		
//		offerRes.setPreclosureCharges(0f);
//		
//		return ResponseEntity.ok().body(offerRes);
//	}

}
