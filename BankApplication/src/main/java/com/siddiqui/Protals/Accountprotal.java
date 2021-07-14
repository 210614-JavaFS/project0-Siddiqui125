package com.siddiqui.Protals;

import com.siddiqui.Driver;
import com.siddiqui.Utils.TransactionUtils;
import com.siddiqui.Utils.Utils;

public class Accountprotal {

	public static void showAccountInfo() {

		Utils.print("Current Balance \t\t $" + CustomerProtal.customerAccount.getCurrentBalance() + "\n\n");

		Utils.print("What you want to do today\n\n" + "Press 1 for Deposit\n" + "Press 2 for Withdraw\n"
				+ "Press 3 for Transfer\n" + "Press 4 For Check Personal Info\n" + "Press 5 to Logout\n");

		int choice = Utils.scan.nextInt();

		switch (choice) {
		case 1:
			TransactionUtils.deposit();
			break;
		case 2:
			TransactionUtils.withdraw();
			break;
		case 3:
			TransactionUtils.transferaccount();
			break;
		case 4:
			showCustomerInfo();
			break;
		case 5:
			Driver.showLoginRegistrationScreen();
			break;
		default:
			Utils.print("Invalid selection try again");
			showAccountInfo();
			break;
		}
	}

	public static void showCustomerInfo() {
		Utils.print("Personal Information: ");
		Utils.print(Utils.currentCustomer.toString());
		showAccountInfo();
	}

}
