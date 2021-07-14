package com.siddiqui.Authentication;

import java.util.List;

import com.siddiqui.Driver;
import com.siddiqui.Utils.Utils;
import com.siddiqui.model.Customer;

public class Register {

	public static void register(boolean isemployee) {
		Utils.print("Enter username: ");
		String userName = Utils.scan.next();
		getUniqueUserName(userName);
		Utils.print("Enter Password: ");
		String password = Utils.scan.next();

		Utils.print("Enter First Name: ");
		String firstName = Utils.scan.next();

		Utils.print("Enter Last Name: ");
		String lastName = Utils.scan.next();

		Utils.print("Enter Phone Number: ");
		String phoneNumber = Utils.scan.next();
		while (true) {
			if (phoneNumber.matches("\\d{10}")) {
				break;

			} else {
				Utils.logError("Wrong Phone Format, Try again");
				phoneNumber = Utils.scan.next();
				break;
			}

		}
		Utils.print("Enter Email ID: ");
		String email = Utils.scan.next();

		Utils.print("Enter Address : ");
		String address = Utils.scan.next();

		Utils.print("Enter City: ");
		String city = Utils.scan.next();

		Utils.print("Enter state ID: ");
		String state = Utils.scan.next();

		Utils.print("Enter zipcode ID: ");
		String zipcode = Utils.scan.next();

		Utils.print("Enter ssn: ");
		String ssn = Utils.scan.next();

		Customer customer = new Customer(userName, password, firstName, lastName, phoneNumber, email, address, city,
				state, ssn, zipcode, isemployee);

		Utils.customerServices.addCustomer(customer);

	}

	private static String getUniqueUserName(String userName) {
		List<Customer> allUsers = Utils.customerServices.getAllCustomers();
		for (Customer customer : allUsers) {
			if (customer.getUserName().equals(userName)) {
				Utils.print(userName + " User Name Exits Try Different one");
				String newUsername = Utils.scan.next();
				getUniqueUserName(newUsername);
				break;
			}
		}
		return userName;

	}

	public static void showSignUpScreem() {
		Utils.print("Sign Up Screen \n\n");
		Register.register(false);
		Utils.logDebug("Account created successfully");

		Driver.showLoginRegistrationScreen();
	}

}
