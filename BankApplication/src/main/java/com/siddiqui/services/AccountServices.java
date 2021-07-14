package com.siddiqui.services;

import java.util.List;

import com.siddiqui.DAO.AccountDAO;
import com.siddiqui.DAO.AccountDAOImpl;
import com.siddiqui.model.Account;

public class AccountServices {

	private static AccountDAO accountDAO = new AccountDAOImpl();

	public List<Account> getAllCustomers() {
		return null;
	}

	public boolean addAccount(Account account) {
		return accountDAO.addAccount(account);
	}

	public static Account findByCustomerId(int customerId) {
		return accountDAO.findByCustomerId(customerId);

	}

	public void updateAccount(Account customerAccount) {
		accountDAO.updateAccount(customerAccount);
	}

	public void updateBalance(Account customerAccount) {
		accountDAO.updateBalance(customerAccount);
	}

	public void removeAccount(Account account) {
		accountDAO.removeAccount(account);
	}

}
