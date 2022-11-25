package com.service.banking.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// ignorejson properties
// https://jtuto.com/java/solved-how-to-ignore-handler-hibernatelazyinitializer-in-json-jackson-in-spring-hibernate-project/

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "offer_id")
	private Long offerId;

	@Column(name = "offer_name")
	private String offerName;

	@Column(name = "loan_amnt")
	private String loanAmnt;

	@Column(name = "interest_rate_percent")
	private String interestRatePercent;

	@Column(name = "interest_free_withdraw")
	private String interestFreeCashWithdrawal;

	@Column(name = "annual_fee")
	private String annualFee;

	@Column(name = "preclosure_charges")
	private String preclosureCharges;

	// lazy collection == false
//		https://stackoverflow.com/questions/4334970/hibernate-throws-multiplebagfetchexception-cannot-simultaneously-fetch-multipl
	
	// https://www.baeldung.com/hibernate-one-to-many
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "offer", fetch = FetchType.EAGER)
//	@JsonManagedReference
//	private Set<Customer> customers;
	
	@Column(name="customer_id")
	private Long custId;

	public Offer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Offer(Long offerId, String offerName, String loanAmnt, String interestRatePercent,
			String interestFreeCashWithdrawal, String annualFee, String preclosureCharges, Long custId) {
		super();
		this.offerId = offerId;
		this.offerName = offerName;
		this.loanAmnt = loanAmnt;
		this.interestRatePercent = interestRatePercent;
		this.interestFreeCashWithdrawal = interestFreeCashWithdrawal;
		this.annualFee = annualFee;
		this.preclosureCharges = preclosureCharges;
		this.custId = custId;
	}

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getLoanAmnt() {
		return loanAmnt;
	}

	public void setLoanAmnt(String loanAmnt) {
		this.loanAmnt = loanAmnt;
	}

	public String getInterestRatePercent() {
		return interestRatePercent;
	}

	public void setInterestRatePercent(String interestRatePercent) {
		this.interestRatePercent = interestRatePercent;
	}

	public String getInterestFreeCashWithdrawal() {
		return interestFreeCashWithdrawal;
	}

	public void setInterestFreeCashWithdrawal(String interestFreeCashWithdrawal) {
		this.interestFreeCashWithdrawal = interestFreeCashWithdrawal;
	}

	public String getAnnualFee() {
		return annualFee;
	}

	public void setAnnualFee(String annualFee) {
		this.annualFee = annualFee;
	}

	public String getPreclosureCharges() {
		return preclosureCharges;
	}

	public void setPreclosureCharges(String preclosureCharges) {
		this.preclosureCharges = preclosureCharges;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	@Override
	public String toString() {
		return "Offer [offerId=" + offerId + ", offerName=" + offerName + ", loanAmnt=" + loanAmnt
				+ ", interestRatePercent=" + interestRatePercent + ", interestFreeCashWithdrawal="
				+ interestFreeCashWithdrawal + ", annualFee=" + annualFee + ", preclosureCharges=" + preclosureCharges
				+ ", custId=" + custId + "]";
	}

}