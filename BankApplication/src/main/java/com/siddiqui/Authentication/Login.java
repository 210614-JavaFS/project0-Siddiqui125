package com.siddiqui.Authentication;

import com.siddiqui.Protals.AdminPortal;
import com.siddiqui.Protals.CustomerProtal;
import com.siddiqui.Protals.EmployeeProtal;
import com.siddiqui.Utils.Utils;
import com.siddiqui.model.Customer;
import com.siddiqui.services.CustomerServices;

public class Login {

	public static void showLoginScreen() {
		userlogin();
	}

	public static void userlogin() {

		Boolean isLogined;

		do {
			isLogined = true;

			Utils.print("Enter Username: ");
			String username = Utils.scan.next();

			Utils.print("Enter Password: ");
			String password = Utils.scan.next();

			if (username.equals("admin") && password.equals("admin")) {
				Utils.isAdmin = true;
				AdminPortal.showAdminPortal();
			} else {
				try {
					if (username != null && password != null) {
						Customer currentCustomer = CustomerServices.findByName(username);
						if (currentCustomer.getPassword().equals(password)) {
							Utils.currentCustomer = currentCustomer;

							if (!currentCustomer.isEmployee()) {
								CustomerProtal.welcomecustomerScreen();
							} else {
								Utils.isEmployee = true;
								EmployeeProtal.showEmployeePortal();
							}

						} else {
							Utils.print("Incorrect Password. Try again");
							isLogined = false;
						}

					} else {
						Utils.print("Incorrect Password. Try again");
						isLogined = false;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} while (!isLogined);

	}

}
