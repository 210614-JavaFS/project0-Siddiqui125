package com.siddiqui.DAO;

import java.util.List;

import com.siddiqui.model.Customer;

public interface CustomerDAO {

	public List<Customer> findAll();

	public Customer findByName(String customername);

	public boolean addCustomer(Customer customer);
	
	public void removeCustomer(Customer customer);
	
	public void updateCustomerField(Customer customer, String field);

}
