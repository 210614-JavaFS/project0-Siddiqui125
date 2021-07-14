package com.siddiqui.services;

import java.util.List;

import com.siddiqui.DAO.CustomerDAO;
import com.siddiqui.DAO.CustomerDAOImpl;
import com.siddiqui.model.Customer;

public class CustomerServices {

	private static CustomerDAO customerDAO = new CustomerDAOImpl();

	public List<Customer> getAllCustomers() {
		return customerDAO.findAll();
	}

	public boolean addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerDAO.addCustomer(customer);
	}

	public static Customer findByName(String username) {
		return customerDAO.findByName(username);

	}

	public void removeCustomer(Customer customer) {
		customerDAO.removeCustomer(customer);
	}

	public void updateCustomerField(Customer customer, String field) {
		customerDAO.updateCustomerField(customer, field);
	}

}
