/*
	Use this to run this application	
		mysql.server start;
		terminal ¤ mysql -u root
		create database demo;
		show databases;
		use demo;
		
		CREATE TABLE myContact (
		id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
		firstname VARCHAR(30) NOT NULL,
		lastname VARCHAR(30) NOT NULL,
		age VARCHAR(3)
		
		);
 */

package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import Database.Contact;

import java.util.List;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

public class DBConnector {

	// public static void main(String[] args) {
	public Connection conn = null;
	public Statement st = null;
	public ResultSet rs = null;
	public PreparedStatement pst = null;
	public String message;
	public Boolean dbConnection;
	// public Dictionary dit;
	private Contact contact;
	// private int i =1;
	private List<Contact> contactList;

	public void dbConnection() {
		System.out.println("hello");
		try {
			// The newInstance() call is a work around for some
			// broken Java implementations
			conn = DriverManager.getConnection("jdbc:mysql://localhost/demo?"
					+ "user=root&password=root");
		} catch (Exception ex) {
			dbConnection = false;
			System.out.println("Database not connected");
		}

	}

	public void closeConnection() {
		try {
			System.out.println("Close connection");
			this.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	// insert data
	public String save(String firstName, String lastName, String age) {
		dbConnection();
		// String author;
		// author = "krishna";
		try {
			pst = this.conn
					.prepareStatement("INSERT INTO mycontact(firstname,lastname,age) VALUES(?,?,?)");
			pst.setString(1, firstName);
			pst.setString(2, lastName);
			pst.setString(3, age);
			pst.executeUpdate();
			message = "Data Saved";
		} catch (Exception ex) {
			message = "Error!! Data not able to save";
		} finally {
			closeConnection();
		}
		return message;

	}

	public ArrayList<Contact> show() {
		contactList = new ArrayList<Contact>();
		dbConnection();
		try {
			// retrive data
			pst = conn.prepareStatement("SELECT * FROM mycontact");
			rs = pst.executeQuery();
			System.out.println("Show all Data from DBConnector");

			while (rs.next()) {

				contact = new Contact();
				contact.id = rs.getInt(1);
				contact.firstName = rs.getString(2);
				contact.lastName = rs.getString(3);
				contact.age = rs.getString(4);
				contactList.add(contact);
				// System.out.print(rs.getInt(1));
				// System.out.print(": ");
				// System.out.println(rs.getString(2));

			}
		} catch (Exception ex) {
			System.out.println("Unable to show data");

		} finally {
			closeConnection();
		}
		return (ArrayList<Contact>) contactList;

	}

	public String delete(int id) {
		dbConnection();
		try {
			// delete record
			pst = conn
					.prepareStatement("DELETE FROM mycontact  WHERE id=" + id);
			pst.executeUpdate();
			message = "Data Sucessfully Deleted.";
		} catch (Exception ex) {
			message = "Unsucessful to Delete Data";
		} finally {
			closeConnection();
		}

		return message;
	}

	public String update(String firstNameUpdate, String lastNameUpdate,
			String ageUpdate, int id) {
		dbConnection();
		try {
			// delete record
			pst = conn.prepareStatement("UPDATE mycontact SET firstname='"
					+ firstNameUpdate + "',lastname='" + lastNameUpdate
					+ "',age='" + ageUpdate + "' WHERE id=" + id);
			pst.executeUpdate();
			message = "Data Sucessfully Updated.";
		} catch (Exception ex) {
			message = "Unsucessful to Update Data";
		} finally {
			closeConnection();
		}

		return message;
	}
}
