package com.siddiqui.Protals;

import com.siddiqui.Driver;

import com.siddiqui.DAO.CustomerDAOImpl;

import com.siddiqui.Utils.Utils;
import com.siddiqui.model.Account;

public class EmployeeProtal {

	public static void showEmployeePortal() {

		Utils.print("Hi " + Utils.currentCustomer.getFirstName().toUpperCase() + " "
				+ Utils.currentCustomer.getLastName().toUpperCase() + " What you want to do today\n\n"
				+ "Press 1 for view customer info\n" + "Press 2 for view pending account requests\n"
				+ "Press 3 for Logout to Main Screen");

		Account account = Utils.accountServices.findByCustomerId(Utils.currentCustomer.getCustomerID());

		if (account == null) {
			Utils.print("Press 4 to create your bank account");

		} else {
			Utils.print("Press 5 to view your bank account information");

		}

		employeeList();

	}

	private static void employeeList() {

		int choice = Utils.scan.nextInt();
		switch (choice) {

		case 1:
			AdminPortal.viewUserInfo();
			showEmployeePortal();
			break;
		case 2:
			AdminPortal.viewPendingRequests();
			showEmployeePortal();
			break;
		case 3:
			Utils.print("Log out");
			Driver.showLoginRegistrationScreen();
			break;
		case 4:
			createEmployeeBankAccount();
			showEmployeePortal();
			break;
		case 5:
			employeeAccount();
		default:
			Utils.print("Wrong input try Again");
			employeeList();
			break;
		}

	}

	private static void employeeAccount() {
		CustomerProtal.welcomecustomerScreen();

	}

	private static void createEmployeeBankAccount() {

		Utils.print("Select AccountType\n" + "Press 1 for checking\n" + "Press 2 for savings\n");
		String accountSelection = Utils.scan.next();

		Account account = new Account(Utils.currentCustomer.getCustomerID(), "",
				CustomerDAOImpl.getAccountType(accountSelection), false, 0);
		Utils.accountServices.addAccount(account);
	}

}
