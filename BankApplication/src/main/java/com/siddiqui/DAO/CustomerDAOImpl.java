package com.siddiqui.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.siddiqui.Protals.AdminPortal;
import com.siddiqui.Utils.ConnectionUtils;
import com.siddiqui.Utils.Utils;
import com.siddiqui.model.Account;
import com.siddiqui.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	@Override
	public List<Customer> findAll() {
		try (Connection con = ConnectionUtils.getConnection()) {
			String sql = "SELECT * FROM customer";

			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);

			List<Customer> list = new ArrayList<>();
			while (result.next()) {
				Customer customer = new Customer();
				customer.setUserName(result.getString("username"));
				customer.setPassword(result.getString("user_pass"));
				customer.setFirstName(result.getString("first_name"));
				customer.setLastName(result.getString("last_name"));
				customer.setAddress(result.getString("address"));
				customer.setCity(result.getString("city"));
				customer.setState(result.getString("state"));
				customer.setZipcode(result.getString("zipcode"));
				customer.setSsn(result.getString("customer_ssn"));
				customer.setPhoneNumber(result.getString("phonenumber"));
				customer.setEmail(result.getString("email"));
				customer.setCustomerID(result.getInt("customer_id"));
				customer.setEmployee(result.getBoolean("isemployee"));

				list.add(customer);

			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Customer findByName(String username) {

		try (Connection con = ConnectionUtils.getConnection()) {
			String sql = "SELECT * FROM customer WHERE username = ?;";

			PreparedStatement statement = con.prepareStatement(sql);

			statement.setString(1, username);

			ResultSet result = statement.executeQuery();
			Customer customer = new Customer();

			while (result.next()) {
				customer.setCustomerID(result.getInt("customer_id"));
				customer.setFirstName(result.getString("first_name"));
				customer.setLastName(result.getString("last_name"));
				customer.setPhoneNumber(result.getString("phonenumber"));
				customer.setUserName(result.getString("username"));
				customer.setPassword(result.getString("user_pass"));
				customer.setAddress(result.getString("address"));
				customer.setCity(result.getString("city"));
				customer.setEmail(result.getString("email"));
				customer.setSsn(result.getString("customer_ssn"));
				customer.setState(result.getString("state"));
				customer.setZipcode(result.getString("zipcode"));
				customer.setEmployee(result.getBoolean("isemployee"));

			}
			return customer;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addCustomer(Customer customer) {
		try (Connection conn = ConnectionUtils.getConnection()) {

			String sql = "INSERT INTO customer(username,user_pass,first_name,last_name,address, phonenumber, email, customer_ssn, state, city, zipcode, isemployee)"
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";

			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setString(++index, customer.getUserName());
			statement.setString(++index, customer.getPassword());
			statement.setString(++index, customer.getFirstName());
			statement.setString(++index, customer.getLastName());
			statement.setString(++index, customer.getAddress());
			statement.setString(++index, customer.getPhoneNumber());
			statement.setString(++index, customer.getEmail());
			statement.setString(++index, customer.getSsn());
			statement.setString(++index, customer.getState());
			statement.setString(++index, customer.getCity());
			statement.setString(++index, customer.getZipcode());
			statement.setBoolean(++index, customer.isEmployee());

			statement.execute();

			if (!Utils.isAdmin) {
				Customer cus = findByName(customer.getUserName());
				addAccount(cus.getCustomerID());
			} else {
				Utils.logDebug("Account created successfully");
				AdminPortal.showAdminPortal();
			}

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public void addAccount(int customerId) {
		Utils.print("Select AccountType\n" + "Press 1 for checking\n" + "Press 2 for savings\n");
		String accountSelection = Utils.scan.next();

		Account account = new Account(customerId, "", getAccountType(accountSelection), false, 0);
		Utils.accountServices.addAccount(account);

	}

	public static String getAccountType(String accountSelection) {
		String accountType = "";

		switch (accountSelection) {
		case "1":
			accountType = "checking";
			break;

		case "2":
			accountType = "savings";
			break;

		default:
			Utils.print("Wrong Selection. Try again!!!\n");
			break;
		}

		return accountType;
	}

	@Override
	public void removeCustomer(Customer customer) {
		try (Connection con = ConnectionUtils.getConnection()) {
			PreparedStatement st = con.prepareStatement("DELETE FROM customer WHERE customer_id = ?");
			st.setInt(1, customer.getCustomerID());
			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCustomerField(Customer customer, String field) {
		try (Connection conn = ConnectionUtils.getConnection()) {

			PreparedStatement statement = conn
					.prepareStatement("UPDATE customer SET " + field + " = ? WHERE customer_id = ?;");

			int index = 0;
			switch (field) {
			case "username":
				statement.setString(++index, customer.getUserName());
				break;
			case "user_pass":
				statement.setString(++index, customer.getPassword());
				break;
			case "first_name":
				statement.setString(++index, customer.getFirstName());
				break;
			case "last_name":
				statement.setString(++index, customer.getLastName());
				break;
			case "address":
				statement.setString(++index, customer.getAddress());

				break;
			case "city":
				statement.setString(++index, customer.getCity());
				break;

			case "state":
				statement.setString(++index, customer.getState());
				break;

			case "zipcode":
				statement.setString(++index, customer.getZipcode());
				break;

			case "customer_ssn":
				statement.setString(++index, customer.getSsn());
				break;
			case "phonenumber":
				statement.setString(++index, customer.getPhoneNumber());
				break;
			case "email":
				statement.setString(++index, customer.getEmail());
				break;

			default:
				Utils.print("Wrong Input try again");
				updateCustomerField(customer, field);
				break;
			}

			statement.setInt(++index, customer.getCustomerID());

			statement.execute();

			Utils.logDebug("Customer Infomation udpated successfully");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
