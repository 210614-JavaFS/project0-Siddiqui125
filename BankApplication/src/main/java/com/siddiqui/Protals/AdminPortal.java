package com.siddiqui.Protals;

import java.util.ArrayList;
import java.util.List;

import com.siddiqui.Driver;
import com.siddiqui.Authentication.Register;
import com.siddiqui.Utils.Utils;
import com.siddiqui.model.Account;
import com.siddiqui.model.Customer;

import ch.qos.logback.classic.pattern.Util;

public class AdminPortal {

	public static void showAdminPortal() {
		Utils.print("Hi Admin What you want to do today\n\n" + "Press 1 for view customer/employee info\n"
				+ "Press 2 for edit customer/employee info\n" + "Press 3 for view pending account requests\n"
				+ "Press 4 For withdraw from account\n" + "Press 5 to deposit to account\n"
				+ "Press 6 for transfer from one account to another\n" + "Press 7 for cancelling an account\n"
				+ "Press 8 for Create an Employee account" + "\nPress 9 for Logout");

		adminoptions();
	}

	private static void adminoptions() {

		boolean quit = false;
		do {

			int selection = Utils.scan.nextInt();

			switch (selection) {
			case 1:
				viewUserInfo();
				break;

			case 2:
				editUserInfo();
				break;

			case 3:
				viewPendingRequests();
				break;

			case 4:
				withdrawFromAccount();
				break;

			case 5:
				depostToAccount();
				break;

			case 6:
				transfertoAccount();
				break;

			case 7:
				cancelledAccount();
				break;

			case 8:
				createEmployeeAccount();
				break;
			case 9:
				Utils.print("Loging out");
				Driver.showLoginRegistrationScreen();
				quit = true;
				break;

			default:
				Utils.print("Wrong Input try again");
				break;
			}
			showAdminPortal();
		} while (!quit);
	}

//CASE#7
	private static void cancelledAccount() {
		displayAllUsers();

		Utils.print("\nWhich user you want you cancell");
		String username = Utils.scan.next();

		boolean isUsernameExist = false;
		Customer selectedCustomer = null;

		List<Customer> allUsers = Utils.customerServices.getAllCustomers();

		for (Customer customer : allUsers) {
			if (customer.getUserName().equals(username)) {
				isUsernameExist = true;
				selectedCustomer = customer;
				break;
			}

		}

		if (isUsernameExist) {
			Account selectedAccount = Utils.accountServices.findByCustomerId(selectedCustomer.getCustomerID());
			selectedAccount.setAccountId(selectedAccount.getAccountId());
			Utils.accountServices.removeAccount(selectedAccount);
			Utils.customerServices.removeCustomer(selectedCustomer);

		} else {
			Utils.print("You have entered wrong username");
		}

	}

//CASE#6
	private static void transfertoAccount() {
		Utils.print("Enter the username of an account :");
		String username = Utils.scan.next();

		boolean isUsernameExist = false;
		Customer selectedCustomer = null;

		List<Customer> allUsers = Utils.customerServices.getAllCustomers();

		for (Customer customer : allUsers) {
			if (customer.getUserName().equals(username)) {
				isUsernameExist = true;
				selectedCustomer = customer;
				break;
			}
		}

		if (isUsernameExist) {
			Account selectedAccount = Utils.accountServices.findByCustomerId(selectedCustomer.getCustomerID());
			Utils.print(selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName() + " Balance: "
					+ selectedAccount.getCurrentBalance());

			Utils.print("Enter the amount you want to Transfer");
			double amount = Utils.scan.nextDouble();

			selectedAccount.setCurrentBalance(selectedAccount.getCurrentBalance() + amount);
			Utils.accountServices.updateBalance(selectedAccount);

		} else {
			Utils.print("You have entered wrong username");
		}
	}

//CASE#2
	private static void editUserInfo() {
		Utils.print("Enter the username of User whose info you want to update");
		String username = Utils.scan.next();

		boolean isUsernameExist = false;
		Customer selectedCustomer = null;

		List<Customer> allUsers = Utils.customerServices.getAllCustomers();

		for (Customer customer : allUsers) {
			if (customer.getUserName().equals(username)) {
				isUsernameExist = true;
				selectedCustomer = customer;
				break;
			}
		}

		if (isUsernameExist) {
			Account selectedAccount = Utils.accountServices.findByCustomerId(selectedCustomer.getCustomerID());

			Utils.print("\nPersonalInformation");
			Utils.print(selectedCustomer.toString());

			Utils.print("\nPress 1 to update username" + "\nPress 2 to update password"
					+ "\nPress 3 to update first name" + "\nPress 4 to update last name"
					+ "\nPress 5 to update street address" + "\nPress 6 to update phonenumber"
					+ "\nPress 7 to update email" + "\nPress 8 to Update SSN");

			int selection = Utils.scan.nextInt();

			switch (selection) {
			case 1:
				updateUsername(selectedCustomer);
				break;
			case 2:
				updatePassword(selectedCustomer);
				break;
			case 3:
				updateFirstname(selectedCustomer);
				break;
			case 4:
				updatelastname(selectedCustomer);
				break;
			case 5:
				updateAddress(selectedCustomer);
				break;
			case 6:
				updatePhoneNumber(selectedCustomer);
				break;
			case 7:
				updateEmail(selectedCustomer);
				break;
			case 8:
				updateSSN(selectedCustomer);
				break;
			default:
				break;
			}

		} else {
			Utils.print("You have entered wrong username");

		}
	}

	private static void updateSSN(Customer selectedCustomer) {
		Utils.print("Enter new SSN");
		String ssn = Utils.scan.next();

		selectedCustomer.setSsn(ssn);
		Utils.customerServices.updateCustomerField(selectedCustomer, "customer_ssn");
	}

	private static void updateEmail(Customer selectedCustomer) {
		Utils.print("Enter new Email ID");
		String emailID = Utils.scan.next();

		selectedCustomer.setEmail(emailID);
		Utils.customerServices.updateCustomerField(selectedCustomer, "email");
	}

	private static void updatePhoneNumber(Customer selectedCustomer) {
		Utils.print("Enter new Phone Number");
		String phoneNumber = Utils.scan.next();

		selectedCustomer.setPhoneNumber(phoneNumber);
		Utils.customerServices.updateCustomerField(selectedCustomer, "phonenumber");

	}

	private static void updateAddress(Customer selectedCustomer) {

		Utils.print("Enter new Street Address");
		String address = Utils.scan.next();

		Utils.print("\nEnter new City");
		String city = Utils.scan.next();

		Utils.print("\nEnter new State ");
		String state = Utils.scan.next();

		Utils.print("\nEnter new Zipcode ");
		String zipcode = Utils.scan.next();

		selectedCustomer.setAddress(address);
		Utils.customerServices.updateCustomerField(selectedCustomer, "address");

		selectedCustomer.setCity(city);
		Utils.customerServices.updateCustomerField(selectedCustomer, "city");

		selectedCustomer.setState(state);
		Utils.customerServices.updateCustomerField(selectedCustomer, "state");

		selectedCustomer.setZipcode(zipcode);
		Utils.customerServices.updateCustomerField(selectedCustomer, "zipcode");

	}

	private static void updatelastname(Customer selectedCustomer) {
		Utils.print("Enter new lasr Name");
		String lastName = Utils.scan.next();

		selectedCustomer.setLastName(lastName);
		Utils.customerServices.updateCustomerField(selectedCustomer, "last_name");

	}

	private static void updateFirstname(Customer selectedCustomer) {
		Utils.print("Enter new First Name");
		String firstName = Utils.scan.next();

		selectedCustomer.setFirstName(firstName);
		Utils.customerServices.updateCustomerField(selectedCustomer, "first_name");
	}

	private static void updatePassword(Customer selectedCustomer) {
		Utils.print("Enter new password");
		String password = Utils.scan.next();

		selectedCustomer.setPassword(password);
		Utils.customerServices.updateCustomerField(selectedCustomer, "user_pass");
	}

	private static void updateUsername(Customer customer) {
		Utils.print("Enter new useranme");
		String username = Utils.scan.next();

		List<Customer> allUsers = Utils.customerServices.getAllCustomers();

		boolean isUniqueUsername = true;

		for (Customer customer1 : allUsers) {
			if (customer1.getUserName().equals(username)) {
				isUniqueUsername = false;
			}
		}

		if (isUniqueUsername) {
			customer.setUserName(username);
			Utils.customerServices.updateCustomerField(customer, "username");
		} else {
			// TODO add while loop
			Utils.print("Username already exist. Try again");
		}
	}

//CASE#
	private static void depostToAccount() {
		Utils.print("Enter the username of an account from which you want to depost funds");
		String username = Utils.scan.next();

		boolean isUsernameExist = false;
		Customer selectedCustomer = null;

		List<Customer> allUsers = Utils.customerServices.getAllCustomers();

		for (Customer customer : allUsers) {
			if (customer.getUserName().equals(username)) {
				isUsernameExist = true;
				selectedCustomer = customer;
				break;
			}
		}

		if (isUsernameExist) {
			Account selectedAccount = Utils.accountServices.findByCustomerId(selectedCustomer.getCustomerID());
			Utils.print(selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName() + " Balance: "
					+ selectedAccount.getCurrentBalance());

			Utils.print("Enter the amount you want to withdraw");
			double amount = Utils.scan.nextDouble();

			selectedAccount.setCurrentBalance(selectedAccount.getCurrentBalance() + amount);
			Utils.accountServices.updateBalance(selectedAccount);

		} else {
			Utils.print("You have entered wrong username");
		}
	}

//CASE#
	private static void withdrawFromAccount() {
		Utils.print("Enter the username of an account from which you want to withdraw funds");
		String username = Utils.scan.next();

		boolean isUsernameExist = false;
		Customer selectedCustomer = null;

		List<Customer> allUsers = Utils.customerServices.getAllCustomers();

		for (Customer customer : allUsers) {
			if (customer.getUserName().equals(username)) {
				isUsernameExist = true;
				selectedCustomer = customer;
				break;
			}
		}

		if (isUsernameExist) {
			Account selectedAccount = Utils.accountServices.findByCustomerId(selectedCustomer.getCustomerID());
			Utils.print(selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName() + " Balance: "
					+ selectedAccount.getCurrentBalance());

			Utils.print("Enter the amount you want to withdraw");
			double amount = Utils.scan.nextDouble();

			if (amount <= selectedAccount.getCurrentBalance()) {
				selectedAccount.setCurrentBalance(selectedAccount.getCurrentBalance() - amount);
				Utils.accountServices.updateBalance(selectedAccount);
			} else {
				Utils.logDebug("Insufficent Funds");
			}

		} else {
			Utils.print("You have entered wrong username");
		}
	}

//CASE#3
	static void viewPendingRequests() {

		List<Customer> allUsers = Utils.customerServices.getAllCustomers();
		List<Customer> pendingUsers = new ArrayList<>();

		for (Customer customer : allUsers) {
			Account account = Utils.accountServices.findByCustomerId(customer.getCustomerID());

			if (account != null) {
				if (!account.getIsAccountActive()) {
					pendingUsers.add(customer);
				}
			}
		}

		if (pendingUsers.size() == 0) {
			Utils.print("********************************************");
			Utils.print("No Pending User Account Request");
			Utils.print("********************************************");

			if (Utils.isAdmin)
				showAdminPortal();
			else
				EmployeeProtal.showEmployeePortal();
		} else {
			Utils.print("Pending User Account List");
			Utils.print("********************************************");

			for (Customer customer1 : pendingUsers) {
				Account account = Utils.accountServices.findByCustomerId(customer1.getCustomerID());

				if (account != null) {
					Utils.print(customer1.getUserInfoForAdmin());
					Utils.print(account.toString());
					Utils.print("********************************************");

				}
			}

			processPendingApplication();
		}

	}

	private static void processPendingApplication() {
		Utils.print("Enter the username of User whose application you want to approve or deny");
		String username = Utils.scan.next();
		if (username.equals(Utils.currentCustomer.getUserName())) {
			Utils.print("\nEmployee can't approve or Deny it's own application");
		} else {
			boolean isUsernameExist = false;
			Customer selectedCustomer = null;

			List<Customer> allUsers = Utils.customerServices.getAllCustomers();

			for (Customer customer : allUsers) {

				if (customer.getUserName().equals(username)) {
					isUsernameExist = true;
					selectedCustomer = customer;
					break;
				}

			}
			if (isUsernameExist) {
				Account selectedAccount = Utils.accountServices.findByCustomerId(selectedCustomer.getCustomerID());
				Utils.print("\nPress 1 to approve application" + "\nPress 2 to deny application");

				int selection = Utils.scan.nextInt();

				switch (selection) {
				case 1:
					approveApplication(selectedAccount);
					break;

				case 2:
					denyApplication(selectedAccount, selectedCustomer);
					break;

				default:
					break;
				}
			} else {
				Utils.print("You have entered wrong username");
			}
		}
	}

	private static void denyApplication(Account account, Customer customer) {
		Utils.accountServices.removeAccount(account);
		Utils.customerServices.removeCustomer(customer);

		Utils.print("Customer application removed successfully");
	}

	private static void approveApplication(Account selectedAccount) {
		selectedAccount.setIsAccountActive(true);
		String accountNumber = "021200" + selectedAccount.getAccountId() + selectedAccount.getCustomerId();
		selectedAccount.setAccountNumber(accountNumber);

		Utils.print("Account Information: ");
		Utils.print(selectedAccount.toString());

		Utils.accountServices.updateAccount(selectedAccount);
	}

//CASE#1
	static void viewUserInfo() {
		Utils.print("List of all Users");
		displayAllUsers();
		viewInfoOfSpecificUser();

	}

	private static void viewInfoOfSpecificUser() {
		Utils.print("\nEnter the username of User you want to see their full info");
		String username = Utils.scan.next();

		boolean isUsernameExist = false;
		Customer selectedCustomer = null;

		List<Customer> allUsers = Utils.customerServices.getAllCustomers();

		for (Customer customer : allUsers) {
			if (customer.getUserName().equals(username)) {
				isUsernameExist = true;
				selectedCustomer = customer;
				break;
			}
		}

		// TODO add while looop until admin enter correct username
		if (isUsernameExist) {
			Utils.print("Personal Information: ");
			Utils.print(selectedCustomer.toString());

			Account selectedAccount = Utils.accountServices.findByCustomerId(selectedCustomer.getCustomerID());

			if (selectedAccount != null) {
				Utils.print("\nAccount Information: ");
				Utils.print(selectedAccount.toString());
			}
		} else {
			Utils.print("youn have entered wrong username.");
		}

	}

	private static void displayAllUsers() {
		List<Customer> allUsers = Utils.customerServices.getAllCustomers();

		for (Customer customer : allUsers) {
			if (Utils.isAdmin) {
				Utils.print(customer.getUserInfoForAdmin());
			} else if (Utils.isEmployee && !customer.isEmployee()) {
				Utils.print(customer.getUserInfoForAdmin());
			}

		}
	}

	private static void createEmployeeAccount() {
		Register.register(true);

	}
}
