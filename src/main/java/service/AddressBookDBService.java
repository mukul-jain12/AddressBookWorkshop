package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.PersonInformation;

public class AddressBookDBService {

	private static AddressBookDBService addressBookDBService;

	public static AddressBookDBService getInstance() {
		if ( addressBookDBService == null)
			addressBookDBService = new AddressBookDBService();
		return addressBookDBService;
	}

	public List<PersonInformation> readData() {
		String sql = "SELECT * FROM contacts";
		return getEmployeePayrollDataUsingDB(sql);
	}

	private List<PersonInformation> getEmployeePayrollDataUsingDB(String sql) {
		List<PersonInformation> contactList = new ArrayList<>();
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				int id = result.getInt("id");
				String fname = result.getString("Name");
				String lname = result.getString("LastName");
				String address = result.getString("Address");
				String city = result.getString("City");
				String state = result.getString("State");
				int zip = result.getInt("Zip");
				int phoneNo = result.getInt("PhoneNumber");
				String email = result.getString("Email");
				contactList.add(new PersonInformation(id, fname, lname, address, city, state, zip, phoneNo, email));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	private Connection getConnection() throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/address_book_service?useSSL=false";
		String userName = "root";
		String password = "Mukul@1008";
		Connection connection;
		System.out.println("Connecting to database: "+jdbcURL);
		connection = DriverManager.getConnection(jdbcURL, userName, password);
		System.out.println("Connection is successful! " +connection);
		return connection;
	}
}
