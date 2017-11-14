import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DB_Code {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		 setProfile();
		// setID("sample");
		// updateSalary("Skywalker2");
		// updateSalary("sample");
		// updateGoal("sample");
		// createExpense("sample");
		// updateExpenseAmount("sample", "Car");
		// System.out.println(getExpense("sample","Car"));
		// addToExpense("Sample", "Car");
		// System.out.println(createYearlySalary("sample"));
		// addYearlySalary("Skywalker2");
		
	}

	// Set up new profile page. This gets name, age, username, email, and
	// password.
	public static void setProfile() throws SQLException, ClassNotFoundException {

		try {
			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			// Get first and last name and insert into db
			System.out.print("Please enter your first and last name: ");
			String userNameFL = scan.nextLine();
			String userNameFLQuery = "insert into User_Table (userNameFL) values ('" + userNameFL + "')";
			boolean in1 = sta.execute(userNameFLQuery);
			System.out.println();

			// get age
			System.out.print("Please enter your age: ");
			int userAge = scan.nextInt();
			String userAgeQuery = "update User_Table set userAge = " + userAge + " where userNameFL = " + "'"
					+ userNameFL + "'";
			boolean in2 = sta.execute(userAgeQuery);
			System.out.println();

			// get username
			System.out.print("Please enter your username: ");
			String userName = scan.next();
			String userNameQuery = "update User_Table set userName = " + "'" + userName + "'" + " where userNameFL = "
					+ "'" + userNameFL + "'";
			boolean in5 = sta.execute(userNameQuery);
			System.out.println();

			// get email
			System.out.print("Please enter your email: ");
			String userEmail = scan.next();
			String userEmailQuery = "update User_Table set userEmail = " + "'" + userEmail + "'"
					+ " where userNameFL = " + "'" + userNameFL + "'";
			boolean in3 = sta.execute(userEmailQuery);
			System.out.println();

			// create user specific table
			String userTable = "CREATE TABLE " + userName + " (ID int, Monthly_Salary FLOAT, Monthly_Goal FLOAT, Yearly_Salary FLOAT);";
			boolean in8 = sta.execute(userTable);

			// add ID to user specific table
			setID(userName);

			// get password
			System.out.print("Please enter a desired password: ");
			String userPass = scan.next();
			String userPassQuery = "update User_Table set userPass = " + "'" + userPass + "'" + " where userNameFL = "
					+ "'" + userNameFL + "'";
			boolean in6 = sta.execute(userPassQuery);
			System.out.println();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	// Sets the ID column to 1 in the user's expenses table.
	public static void setID(String user) throws SQLException, ClassNotFoundException {

		try {
			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			String userID = "insert into " + user + " (ID) values ('1')";
			boolean in = sta.execute(userID);

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	/*
	 * Method to insert the user's monthly salary for the first time. public
	 * static void setSalary(String user) throws SQLException,
	 * ClassNotFoundException {
	 * 
	 * try { // Get a connection to a database and create a statement String url
	 * =
	 * "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
	 * Connection conn = DriverManager.getConnection(url); Statement sta =
	 * conn.createStatement();
	 * 
	 * System.out.print("What is your monthly salary: $"); float
	 * userMonthlySalary = scan.nextFloat(); String userMonthlySalaryQuery =
	 * "insert into " + user + " (Monthly_Salary) values (" + userMonthlySalary
	 * + ")"; boolean in = sta.execute(userMonthlySalaryQuery);
	 * 
	 * } catch (Exception exc) { exc.printStackTrace(); } }
	 */

	// Method to update the user's monthly salary
	public static void updateSalary(String user) throws SQLException, ClassNotFoundException {

		try {
			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			System.out.print("What is your monthly salary: ");
			float update = scan.nextFloat();
			String updateSalaryQuery = "update " + user + " set Monthly_Salary = " + "'" + update + "'"
					+ " where ID = 1";
			boolean in = sta.execute(updateSalaryQuery);

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	// get monthly salary for calculations purposes. Make sure you parse to
	// float.
	public static String getSalary(String user) throws SQLException, ClassNotFoundException {

		try {
			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			String salary = "select Monthly_Salary from " + user + " where ID = 1";
			ResultSet in = sta.executeQuery(salary);

			while (in.next()) {
				user = in.getString("Monthly_Salary");
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return user;
	}

	/*
	 * Method to insert the user's monthly savings goal for the first time.
	 * public static void setGoal(String user) throws SQLException,
	 * ClassNotFoundException {
	 * 
	 * try { // Get a connection to a database and create a statement String url
	 * =
	 * "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
	 * Connection conn = DriverManager.getConnection(url); Statement sta =
	 * conn.createStatement();
	 * 
	 * System.out.print("What is your monthly savings goal: "); float goal =
	 * scan.nextFloat(); String goalQuery = "insert into " + user +
	 * " (Monthly_Goal) values (" + goal + ")"; boolean in =
	 * sta.execute(goalQuery);
	 * 
	 * } catch (Exception exc) { exc.printStackTrace(); } }
	 */

	// Method to update the user's monthly savings goal.
	public static void updateGoal(String user) throws SQLException, ClassNotFoundException {

		try {
			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			System.out.print("What is your monthly savings goal: ");
			float goal = scan.nextFloat();
			String goalQuery = "update " + user + " set Monthly_Goal = " + "'" + goal + "'" + " where ID = 1";
			boolean in = sta.execute(goalQuery);

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	// get the monthly goal for calculations. Make sure you parse to float
	// because this return type is a string.
	public static String getGoal(String user) throws SQLException, ClassNotFoundException {

		try {
			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			String goal = "select Monthly_Goal from " + user + " where ID = 1";
			ResultSet in = sta.executeQuery(goal);

			while (in.next()) {
				user = in.getString("Monthly_Goal");
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return user;
	}

	// create user expense in the database.
	public static void createExpense(String user) throws SQLException, ClassNotFoundException {

		try {
			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			System.out.print("Please enter a personal expense: ");
			String expense = scan.next();
			String expenseQuery = "ALTER TABLE " + user + " ADD " + expense + " FLOAT";
			boolean in = sta.execute(expenseQuery);

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	// method to update the expense cost, not to add to its existing value.
	public static void updateExpenseAmount(String user, String expense) throws SQLException, ClassNotFoundException {

		try {
			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			System.out.print("Please enter the expense amount: ");
			float upExpense = scan.nextFloat();
			String upExpenseQuery = "update " + user + " set " + expense + " = " + "'" + upExpense + "'"
					+ " where ID = 1";
			boolean in = sta.execute(upExpenseQuery);

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	// method to get the expense value so you can perform functions with the
	// value. Make sure you change to float when using it.
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

	// method to add value to the existing expense.
	public static void addToExpense(String user, String expense) throws SQLException, ClassNotFoundException {

		try {
			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			System.out.print("Please enter how much you wish to add to your current expense cost: ");
			float addExp = scan.nextFloat();

			float newExp = Float.valueOf(getExpense(user, expense)) + addExp;

			String addExpQuery = "update " + user + " set " + expense + " = " + "'" + newExp + "'" + " where ID = 1";
			boolean in = sta.execute(addExpQuery);

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	//Method to create yearly salary from the user's monthly salary value. 
	public static float createYearlySalary(String user) throws SQLException, ClassNotFoundException {

		float yearSalary = Float.valueOf(getSalary(user)) * 12;
		return yearSalary;

	}
	
	//add yearly salary into DB
	public static void addYearlySalary(String user) throws SQLException, ClassNotFoundException {
		
		float salary = createYearlySalary(user);

		try {
			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();
			
			String salaryQuery = "update " + user + " set Yearly_Salary = " + salary + " where ID = 1";
			boolean in = sta.execute(salaryQuery);
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
