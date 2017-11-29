
/*	Main GUI display
	This should update and display the users info
*/

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainGUI extends JFrame {
	private static ArrayList<Float> expenseValue;
	private static ArrayList<String> expenseName;

	// Strings are used to access database
	private String username;
	private String password;

	// Declaring buttons
	private JButton updateButton;
	private JButton logoutButton;
	private JButton displayButton;

	private JButton expensesButton;

	// Declaring textfields for user input
	private JTextField yearlySalaryText;
	private JTextField savingGoalText;
	private JTextField monthlyExpensesText;

	private JTextArea viewGoalsArea;

	final int FRAME_WIDTH;
	final int FRAME_LENGTH;

	public MainGUI(String username, String password) {
		
		expenseName = new ArrayList<String>();
		expenseValue = new ArrayList<Float>();

		this.username = username;
		this.password = password;

		FRAME_WIDTH = 700;
		FRAME_LENGTH = 500;

		// Creating textFields
		// bound them to the JFrame with .setBounds
		yearlySalaryText = new JTextField("Enter Yearly Salary");
		yearlySalaryText.setBounds(10, 50, 200, 30);

		savingGoalText = new JTextField("Enter Monthly Saving Goal");
		savingGoalText.setBounds(10, 100, 200, 30);

		// .setEditable prevents the user from messing with the output
		viewGoalsArea = new JTextArea(20, 20);
		viewGoalsArea.setBounds(350, 50, 320, 400);
		viewGoalsArea.setEditable(false);

		// Creating buttons
		updateButton = new JButton("Update");
		updateButton.setBounds(10, 350, 100, 25);

		expensesButton = new JButton("Expenses");
		expensesButton.setBounds(110, 350, 100, 25);

		logoutButton = new JButton("Logout");
		logoutButton.setBounds(110, 400, 100, 25);

		displayButton = new JButton("Display");
		displayButton.setBounds(10, 400, 100, 25);

		// Creating new instance of actionlisteners
		// Placing actionListeners to buttons
		logoutButton logoutListener = new logoutButton();
		logoutButton.addActionListener(logoutListener);

		updateButton updateListener = new updateButton();
		updateButton.addActionListener(updateListener);

		displayButton displayListener = new displayButton();
		displayButton.addActionListener(displayListener);

		expensesButton expensesListener = new expensesButton();
		expensesButton.addActionListener(expensesListener);

		// Add everything to frame
		add(yearlySalaryText);
		add(savingGoalText);
		add(viewGoalsArea);
		add(updateButton);
		add(logoutButton);
		add(displayButton);

		add(expensesButton);

		// Finalize Frame
		setLayout(null);
		setTitle("MAIN");
		setSize(FRAME_WIDTH, FRAME_LENGTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		getContentPane().setBackground(Color.WHITE);
		setVisible(true);
	}

	// Action listeners

	// logoutButton disposes of the JFrame
	// Quick and easy way to 'log out'
	private class logoutButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	// updateButton should take the user input and place it in the database
	// int variables getting the user input are created
	// Throw number format exception if there is something but int in there
	// Try catch maybe?
	private class updateButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			try {

				String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
				Connection conn = DriverManager.getConnection(url);
				Statement sta = conn.createStatement();

				int salary = Integer.parseInt(yearlySalaryText.getText());
				String updateSalaryQuery = "update " + username + " set Monthly_Salary = " + "'" + salary + "'"
						+ " where ID = 1";
				boolean in1 = sta.execute(updateSalaryQuery);

				int savings = Integer.parseInt(savingGoalText.getText());
				String goalQuery = "update " + username + " set Monthly_Goal = " + "'" + savings + "'"
						+ " where ID = 1";
				boolean in2 = sta.execute(goalQuery);

			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}

	public String getName(String username) {

		try {
			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			String getNameFL = "select userNameFL from User_table where userName = '" + username + "'";
			ResultSet in = sta.executeQuery(getNameFL);

			while (in.next()) {
				username = in.getString("userNameFL");
				return username;
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}

	public String getSalary(String username) {
		try {
			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			String getSalary = "select Monthly_Salary from " + username + " where ID = 1";
			ResultSet in = sta.executeQuery(getSalary);

			while (in.next()) {
				username = in.getString("Monthly_Salary");
				return username;
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}

	public String getSavings(String username) {
		try {
			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			String getSavings = "select Monthly_Goal from " + username + " where ID = 1";
			ResultSet in = sta.executeQuery(getSavings);

			while (in.next()) {
				username = in.getString("Monthly_Goal");
				return username;
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}

	private class expensesButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Expenses myExpenses = new Expenses(username);
		}
	}

	public static void getExpenseName(String user, String expense) {

		try {
			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			String query = "select " + expense + " from " + user;
			ResultSet in = sta.executeQuery(query);

			while (in.next()) {
				user = in.getString("Expense_Name");
				expenseName.add(user);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public static void getExpenseValue(String user) {

		try {
			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			for(int j = 0; j < expenseName.size(); j++) {
			String getExp = "select " + expenseName.get(j) + " from " + user;
			ResultSet in = sta.executeQuery(getExp);
			
			while (in.next()) {
				float i = in.getFloat(expenseName.get(j));
				expenseValue.add(i);
			}
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public static String getAllExpense() {
		String out = "";
		for(int i = 0; i < expenseName.size(); i++) {
			out = expenseName.get(i) + " = " + expenseValue.get(i) + "\n";
		}
		return out;
	}
	
	

	private class displayButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String savings = getSavings(username);
            String salary = getSalary(username);
            String name = getName(username);
            float monthlySalary = Float.parseFloat(salary) / 12;
            float monthlySavings = 12 * Float.parseFloat(savings);

            // This is a test that prints the username and password
            viewGoalsArea.setText("\tHello " + name + "\n" + "This is your current yearly salary: " + salary + "\n"
                    + "This is your current savings goal: " + savings + "\n" + "This is how much you can save a year : "
                    + monthlySavings);

            // Use viewGoalsArea.setText("Some string"); to display in text area
		}
	}
}