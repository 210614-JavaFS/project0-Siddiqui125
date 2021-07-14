package com.siddiqui;

import com.siddiqui.Authentication.Login;
import com.siddiqui.Authentication.Register;
import com.siddiqui.Utils.Utils;

public class Driver {

	public static void main(String[] args) {
		showWelcomeScreen();

	}

	public static void showWelcomeScreen() {
		Utils.print("****************************************");
		Utils.print("**         Welcom to MS - Bank        **");
		Utils.print("****************************************");

		Utils.print("\nPress Enter to continue...");

		try {
			System.in.read();
		} catch (Exception e) {
		}

		showLoginRegistrationScreen();
	}

	public static void showLoginRegistrationScreen() {

		Utils.print("\t\t\t MS Bank Screens \t\t\t" + " \nPress 1 Login:" + " \nPress 2 Register For Account: "
				+ "\nPress 3 Leave the Main Screen:");

		int response = Utils.scan.nextInt();

		switch (response) {
		case 1:
			Login.showLoginScreen();
			break;
		case 2:
			Register.showSignUpScreem();
			break;
		case 3:
			Utils.logDebug("You decide to leave the main screen");
			System.exit(0);
			break;

		default:
			Utils.logError("Invalid Entry Please Try again");
			break;
		}
	}

}
