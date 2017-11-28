import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Expenses extends JFrame {

	private String username;

	private JTextField nameText;
	private JTextField valueText;

	private JButton addButton;
	private JButton closeButton;

	final int FRAME_WIDTH;
	final int FRAME_LENGTH;

	public Expenses(String username) {

		this.username = username;

		FRAME_WIDTH = 300;
		FRAME_LENGTH = 250;

		nameText = new JTextField("Enter expense name");
		nameText.setBounds(50, 50, 150, 20);

		valueText = new JTextField("Enter expense value");
		valueText.setBounds(50, 100, 150, 20);

		addButton = new JButton("Add");
		addButton.setBounds(50, 150, 100, 25);

		closeButton = new JButton("Close");
		closeButton.setBounds(150, 150, 100, 25);

		closeButton closeListener = new closeButton();
		closeButton.addActionListener(closeListener);

		addButton addListener = new addButton();
		addButton.addActionListener(addListener);

		add(nameText);
		add(valueText);
		add(addButton);
		add(closeButton);

		setLayout(null);
		setTitle("Expenses");
		setSize(FRAME_WIDTH, FRAME_LENGTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		setVisible(true);

	}

	// method to update the expense cost, not to add to its existing value.
	public static void updateExpenseAmount(String user, String expense, float amount)
			throws SQLException, ClassNotFoundException {

		try {
			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			String upExpenseQuery = "update " + user + " set " + expense + " = " + "'" + amount + "'" + " where ID = 1";
			boolean in = sta.execute(upExpenseQuery);

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	// method to get the expense value so you can perform functions with the value.
	// Make sure you change to float when using it.
	public static String getSalary(String user) throws SQLException, ClassNotFoundException {

		try {
			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			String getSalary = "select Monthly_Salary from " + user + " where ID = 1";
			ResultSet in = sta.executeQuery(getSalary);

			while (in.next()) {
				user = in.getString("Monthly_Salary");
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return user;
	}

	// method to get the expense value so you can perform functions with the value.
	// Make sure you change to float when using it.
	public static String getExpense(String user, String expense) throws SQLException, ClassNotFoundException {

		try {
			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			String getExp = "select " + expense + " from " + user + " where ID = 1";
			ResultSet in = sta.executeQuery(getExp);

			while (in.next()) {
				user = in.getString(expense);
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return user;
	}

	// create user expense in the database.
	public static void createExpense(String user, String expense) throws SQLException, ClassNotFoundException {

		try {
			
			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			String expenseQuery = "ALTER TABLE " + user + " ADD " + expense + " FLOAT";
			boolean in = sta.execute(expenseQuery);

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private class closeButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	private class addButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				// Get a connection to a database and create a statement
				String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
				Connection conn = DriverManager.getConnection(url);
				Statement sta = conn.createStatement();
				
				String expense = nameText.getText();
				createExpense(username, expense);
				
				float expense_values = Float.parseFloat(valueText.getText());
				updateExpenseAmount(username, expense, expense_values);

			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}
}