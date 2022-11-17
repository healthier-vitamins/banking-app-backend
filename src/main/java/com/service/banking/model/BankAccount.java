package com.service.banking.model;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id")
	private Long accId;

	@Column(name = "account_type")
	private String accType;

	@Column(name = "account_balance")
	private String accBal;

	@Column(name = "account_creation_date")
	private String accCreationDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	public BankAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BankAccount(Long accId, String accType, String accBal, String accCreationDate, Customer customer) {
		super();
		this.accId = accId;
		this.accType = accType;
		this.accBal = accBal;
		this.accCreationDate = accCreationDate;
		this.customer = customer;
	}

	public Long getAccId() {
		return accId;
	}

	public void setAccId(Long accId) {
		this.accId = accId;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getAccBal() {
		return accBal;
	}

	public void setAccBal(String accBal) {
		this.accBal = accBal;
	}

	public String getAccCreationDate() {
		return accCreationDate;
	}

	public void setAccCreationDate(String accCreationDate) {
		this.accCreationDate = accCreationDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "BankAccount [accId=" + accId + ", accType=" + accType + ", accBal=" + accBal + ", accCreationDate="
				+ accCreationDate + ", customer=" + customer + "]";
	}

}