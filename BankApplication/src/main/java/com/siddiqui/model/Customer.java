package com.siddiqui.model;

public class Customer extends Person {
	private int customerID;
	private boolean isEmployee;

	public Customer() {
		super();
	}

	public Customer(String userName, String password, String firstName, String lastName, String phoneNumber,
			String email, String address, String city, String state, String ssn, String zipcode, boolean isEmployee) {
		super(userName, password, firstName, lastName, phoneNumber, email, address, city, state, ssn, zipcode);
		this.isEmployee = isEmployee;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	@Override
	public String toString() {
		return "\nUsername: " + getUserName() + "\nPassword: " + getPassword() + "\nFirstname: " + getFirstName()
				+ "\nLastname: " + getLastName() + "\nAddress: " + getAddress() + " " + getCity() + " " + getState()
				+ " " + getZipcode() + "\nSSN: " + getSsn() + "\nPhonenumber: " + getPhoneNumber() + "\nEmail: "
				+ getEmail() + "\nIsEmployee: " + isEmployee();
	}

	public String getUserInfoForAdmin() {
		return "Username: " + getUserName() + " Name: " + getFirstName() + " " + getLastName() + " Email: "
				+ getEmail();
	}

}
