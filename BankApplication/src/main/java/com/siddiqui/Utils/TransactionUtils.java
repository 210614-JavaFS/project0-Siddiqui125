package com.siddiqui.Utils;

import java.util.List;

import com.siddiqui.Protals.Accountprotal;

import com.siddiqui.Protals.CustomerProtal;
import com.siddiqui.model.Account;
import com.siddiqui.model.Customer;

public class TransactionUtils {

	public static void deposit() {
		Utils.print("Enter amount to deposit in your account");
		Double amount = Utils.scan.nextDouble();
		if (amount < 0) {
			Utils.print("Negative Input try again");
			deposit();
		} else {
			Double newAmount = amount + CustomerProtal.customerAccount.getCurrentBalance();

			CustomerProtal.customerAccount.setCurrentBalance(newAmount);

			Utils.print("Current Balance " + CustomerProtal.customerAccount.getCurrentBalance());
			Utils.accountServices.updateBalance(CustomerProtal.customerAccount);

			Accountprotal.showAccountInfo();
		}
	}

	public static void withdraw() {
		Utils.print("Enter amount to Withdraw in your account");
		Double amount = Utils.scan.nextDouble();
		if (amount < 0) {
			Utils.print("Negative Input try again");
			withdraw();
		} else if (amount > CustomerProtal.customerAccount.getCurrentBalance()) {
			Utils.print("Non-Sufficient Funds. Your have $" + CustomerProtal.customerAccount.getCurrentBalance());
			withdraw();
		} else {
			Double newAmount = CustomerProtal.customerAccount.getCurrentBalance() - amount;

			CustomerProtal.customerAccount.setCurrentBalance(newAmount);

			Utils.print("Current Balance " + CustomerProtal.customerAccount.getCurrentBalance());

			Utils.accountServices.updateBalance(CustomerProtal.customerAccount);

			Accountprotal.showAccountInfo();
		}

	}

	public static void transferaccount() {

		Utils.print("Enter the User name To where you would to send amount \n");
		String otheruser = Utils.scan.next();
		boolean isUsernameExist = false;

		List<Customer> allUsers = Utils.customerServices.getAllCustomers();

		for (Customer customer : allUsers) {
			if (customer.getUserName().equals(otheruser)) {
				isUsernameExist = true;
				break;
			}
		}

		if (isUsernameExist) {
			Utils.print("Enter the amount to transfer \n");
			Double amount = Utils.scan.nextDouble();

			Customer othercustomer = Utils.customerServices.findByName(otheruser);
			Account otheruseraccount = Utils.accountServices.findByCustomerId(othercustomer.getCustomerID());

			CustomerProtal.customerAccount
					.setCurrentBalance(CustomerProtal.customerAccount.getCurrentBalance() - amount);
			otheruseraccount.setCurrentBalance(otheruseraccount.getCurrentBalance() + amount);

			Utils.accountServices.updateBalance(CustomerProtal.customerAccount);
			Utils.accountServices.updateBalance(otheruseraccount);

			Accountprotal.showAccountInfo();
		} else {
			Utils.print(otheruser + " doesn't exists.");
			transferaccount();
		}
	}

}
