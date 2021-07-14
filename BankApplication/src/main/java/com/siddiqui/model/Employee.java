package com.siddiqui.model;

public class Employee extends Person {

	private String employeeID;
	Boolean isAdmin;
	Boolean isEmployee;

	public Employee() {
		super();
	}

	public Employee(String userName, String password, String firstName, String lastName, String phoneNumber,
			String email, String address, String city, String state, String ssn, String zipcode, String employeeID,
			Boolean isAdmin, Boolean isEmployee) {
		super(userName, password, firstName, lastName, phoneNumber, email, address, city, state, ssn, zipcode);
		this.employeeID = employeeID;
		this.isAdmin = isAdmin;
		this.isEmployee = isEmployee;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Boolean getIsEmployee() {
		return isEmployee;
	}

	public void setIsEmployee(Boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", toString()=" + super.toString() + "]";
	}

}
