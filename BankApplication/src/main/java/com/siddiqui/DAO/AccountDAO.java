package com.siddiqui.DAO;

import java.util.List;

import com.siddiqui.model.Account;

public interface AccountDAO {

	public List<Account> findAll();

	public Account findByCustomerId(int customer_id);

	public boolean addAccount(Account account);

	public void updateAccount(Account account);
	
	public void updateBalance(Account account);
	
	public void removeAccount(Account account);

}
