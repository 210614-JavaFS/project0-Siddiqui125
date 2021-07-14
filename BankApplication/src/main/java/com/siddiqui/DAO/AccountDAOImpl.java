package com.siddiqui.DAO;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.siddiqui.Utils.ConnectionUtils;
import com.siddiqui.Utils.Utils;
import com.siddiqui.model.Account;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public List<Account> findAll() {

		return null;
	}

	@Override
	public Account findByCustomerId(int customer_id) {

		try (Connection con = ConnectionUtils.getConnection()) {
			String sql = "SELECT * FROM account WHERE customer_id = ?;";

			PreparedStatement statement = con.prepareStatement(sql);

			statement.setInt(1, customer_id);

			ResultSet result = statement.executeQuery();
			Account account = null;

			while (result.next()) {
				account = new Account();
				account.setAccountId(result.getInt("account_id"));
				account.setCustomerId(result.getInt("customer_id"));
				account.setAccountNumber(result.getString("account_number"));
				account.setAccountType(result.getString("account_type"));
				account.setIsAccountActive(result.getBoolean("account_status"));
				account.setCurrentBalance(result.getDouble("current_balance"));
			}
			return account;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addAccount(Account account) {
		try (Connection conn = ConnectionUtils.getConnection()) {

			String sql = "INSERT INTO account(customer_id, account_number, account_type, account_status, current_balance)"
					+ "VALUES(?,?,?,?,?);";

			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setInt(++index, account.getCustomerId());
			statement.setString(++index, account.getAccountNumber());
			statement.setString(++index, account.getAccountType());
			statement.setBoolean(++index, account.getIsAccountActive());
			statement.setDouble(++index, account.getCurrentBalance());
			statement.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void updateAccount(Account account) {
		try (Connection conn = ConnectionUtils.getConnection()) {

			PreparedStatement statement = conn
					.prepareStatement("UPDATE account SET account_number = ?, account_status = ? WHERE account_id = ?;");

			int index = 0;
			statement.setString(++index, account.getAccountNumber());
			statement.setBoolean(++index, account.getIsAccountActive());
			statement.setInt(++index, account.getAccountId());

			statement.execute();

			Utils.logDebug("Account udpated successfully");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateBalance(Account account) {
		try (Connection conn = ConnectionUtils.getConnection()) {

			PreparedStatement statement = conn
					.prepareStatement("UPDATE account SET current_balance = ? WHERE account_id = ?;");

			int index = 0;
			statement.setDouble(++index, account.getCurrentBalance());
			statement.setInt(++index, account.getAccountId());

			statement.execute();

			Utils.logDebug("Balance udpated successfully");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeAccount(Account account) {
		try (Connection con = ConnectionUtils.getConnection()) {
			PreparedStatement st = con.prepareStatement("DELETE FROM account WHERE account_id = ?");
			st.setInt(1,account.getAccountId());
			st.executeUpdate(); 

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
