package com.siddiqui.model;

import com.siddiqui.Protals.Accountprotal;

public class Account {
	private int accountId;
	private int customerId;
	private String accountNumber;
	private String accountType;
	private Boolean isAccountActive;
	private double currentBalance;

	public Account() {
		super();
	}

	public Account(int customerId, String accountNumber, String accountType, Boolean isAccountActive,
			double currentBalance) {
		super();
		this.customerId = customerId;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.isAccountActive = isAccountActive;
		this.currentBalance = currentBalance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Boolean getIsAccountActive() {
		return isAccountActive;
	}

	public void setIsAccountActive(Boolean isAccountActive) {
		this.isAccountActive = isAccountActive;
	}

	public static void showCustomerAccount() {
		Accountprotal.showAccountInfo();
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	@Override
	public String toString() {
		return "\nAccount Opened: " + getIsAccountActive() + "\nCurrent Balance: " + getCurrentBalance()
				+ "\nAccount Type: " + getAccountType() + "\nAccount Number: " + getAccountNumber() + "\n";
	}

}
