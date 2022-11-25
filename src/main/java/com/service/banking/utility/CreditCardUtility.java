package com.service.banking.utility;

import java.math.BigDecimal;
import java.text.ParseException;

import com.service.banking.model.BankAccount;
import com.service.banking.model.Offer;

public class CreditCardUtility {

	final static Long TEN_YEAR_MIllI_S = 315569260000l;
	final static Long FIVE_YEAR_MIllI_S = 157784630000l;
	

	static public Offer getCreditCardOffer(BankAccount bankAcc) throws ParseException {
		Offer offerRes = new Offer(null, "Credit Card", null, null, null, null, null, null);
		Long accCreatedMilliS = DateFormatterUtility.convertDateStringToMillisLong(bankAcc.getAccCreationDate());
		
		Long currentDateMiliS = DateFormatterUtility.currentDateInLong();
		Long accCreatedDuration = currentDateMiliS - accCreatedMilliS;
		
		BigDecimal tempAccBal = AccBalanceUtility.parseDecimalAccBal(bankAcc.getAccBal());
		
		offerRes.setAnnualFee(annualFeeLogic(tempAccBal));
		offerRes.setInterestRatePercent(interestRatePercentLogic(accCreatedDuration));
		offerRes.setInterestFreeCashWithdrawal(InterestFreeWithdrawalLogic(tempAccBal, accCreatedDuration));
		return offerRes;
	}

	static public String annualFeeLogic(BigDecimal accBal) {
		if (accBal.compareTo(BigDecimal.valueOf(10000l)) >= 0) {
			return AccBalanceUtility.formatAccBal("50");
		}
		if (accBal.compareTo(BigDecimal.valueOf(7000l)) >= 0) {
			return AccBalanceUtility.formatAccBal("60");
		}
		return AccBalanceUtility.formatAccBal("100");
	}
	
	static public String interestRatePercentLogic(Long accCreatedDuration) {
		if(accCreatedDuration >= TEN_YEAR_MIllI_S) {
			return String.valueOf(6);
		}
		if(accCreatedDuration >= FIVE_YEAR_MIllI_S) {
			return String.valueOf(7);
		}
		return String.valueOf(10);
	}
	
	static public String InterestFreeWithdrawalLogic(BigDecimal accBal, Long accCreatedDuration) {
		if(accBal.compareTo(BigDecimal.valueOf(10000)) >= 0 && accCreatedDuration >= TEN_YEAR_MIllI_S) {
			return AccBalanceUtility.formatAccBal("1000");
		}
		if(accBal.compareTo(BigDecimal.valueOf(5000)) >= 0 && accCreatedDuration >= FIVE_YEAR_MIllI_S) {
			return AccBalanceUtility.formatAccBal("500");
		}
		return AccBalanceUtility.formatAccBal("200");
	}
}
