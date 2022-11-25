package com.service.banking.utility;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Currency;

public class AccBalanceUtility {
	
	static Currency sgd;
	static NumberFormat currency = DecimalFormat.getCurrencyInstance();
	
	static public void init() {
		sgd = Currency.getInstance("SGD");
		currency.setCurrency(sgd);
	}
	
	static public String formatAccBal(String accBal) {
		init();
		BigDecimal tempBig = new BigDecimal(accBal);
		return currency.format(tempBig);
	}
	
	static public BigDecimal parseDecimalAccBal(String accBal) throws ParseException { 
		init();
		accBal = accBal.replaceAll(",", "");
		BigDecimal tempBig = new BigDecimal(currency.parse(accBal).longValue());
		return tempBig;
	}
}
