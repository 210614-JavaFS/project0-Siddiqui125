package com.siddiqui.Protals;

import com.siddiqui.Utils.Utils;
import com.siddiqui.model.Account;
import com.siddiqui.services.AccountServices;

public class CustomerProtal {

	public static Account customerAccount;

	public static void welcomecustomerScreen() {
		Utils.print("\nHi " + Utils.currentCustomer.getFirstName().toUpperCase() + " "
				+ Utils.currentCustomer.getLastName().toUpperCase() + " Welcome to Customer Portal");
		checkApplicationStatus();

	}

	private static void checkApplicationStatus() {

		customerAccount = Utils.accountServices.findByCustomerId(Utils.currentCustomer.getCustomerID());

		if (!customerAccount.getIsAccountActive()) {
			Utils.logDebug("Your application approval is pending");
		} else {
			customerScreen();
		}
	}

	private static void customerScreen() {

		Account.showCustomerAccount();
	}

}
