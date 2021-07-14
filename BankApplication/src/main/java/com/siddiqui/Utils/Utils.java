package com.siddiqui.Utils;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.siddiqui.Driver;
import com.siddiqui.model.*;
import com.siddiqui.services.AccountServices;
import com.siddiqui.services.CustomerServices;

public class Utils {
	public static Customer currentCustomer;
	public static Employee currentEmployee;
	
	public static boolean isAdmin = false;
	public static boolean isEmployee = false;

	public static CustomerServices customerServices = new CustomerServices();
	public static AccountServices accountServices = new AccountServices();
	
	public static Logger log = LoggerFactory.getLogger(Driver.class);
	public static Scanner scan = new Scanner(System.in);

	public static void print(String s) {
		System.out.println(s);
	}

	public static void logDebug(String s) {
		log.debug(s);
	}

	public static void logError(String s) {
		log.error(s);
	}

}
