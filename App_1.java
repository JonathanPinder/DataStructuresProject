import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.Scanner;

public class App_1 {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//setInfo();

		String avgMonthly = getMonthlyAvg("TestingTest");
		System.out.println("Average made a month: $" + avgMonthly);

		String avgYearly = getYearlyAvg("TestingTest	");
		System.out.println("Average made a year: $" + avgYearly);

		String avgBills = getAvgBills("TestingTest");
		System.out.println("Average cost in bills: $" + avgBills);
		
		String amountSave = getSave("TestingTest");
		System.out.println("Amount you want to save per month: $" + amountSave);
	}

	// Method to set all values for said user and insert/update values into db.
	public static void setInfo() throws SQLException, ClassNotFoundException {

		System.out.println("======= Welcome to the Budgeting App! Lets get started! =======");
		System.out.println();

		try {
			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=Prototype_0.1_App;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			// Get Username and insert into db
			System.out.print("Please enter your first and last name: ");
			String userInfo = scan.next();
			String userInfoQuery = "insert into User_Info (Username) values ('" + userInfo + "')";
			boolean in1 = sta.execute(userInfoQuery);
			System.out.println();

			// Get amount made a month and update into db
			System.out.print("Please enter the amount you make on average per month: ");
			String userMonthlySalary = scan.next();
			String userMonthlySalaryQuery = "update User_Info set [Avg Made A Month] = " + userMonthlySalary
					+ " where Username = " + "'" + userInfo + "'";
			boolean in2 = sta.execute(userMonthlySalaryQuery);
			System.out.println();

			// calculate user's salary and update into db
			int userSalary = Integer.parseInt(userMonthlySalary) * 12;
			String userSalaryQuery = "update User_Info set [Avg Made A Year] = " + userSalary + " where Username = "
					+ "'" + userInfo + "'";
			boolean in3 = sta.execute(userSalaryQuery);

			// get average cost of bills and update into db
			System.out.print("Please enter the average cost of your bills per month: ");
			int userBills = scan.nextInt();
			String userBillsQuery = "update User_Info set [Avg Cost In Bills A Month] = " + userBills
					+ " where Username = " + "'" + userInfo + "'";
			boolean in4 = sta.execute(userBillsQuery);
			System.out.println();

			// calculate amount left over per month and update into table
			int userLeft = Integer.parseInt(userMonthlySalary) - userBills;
			String userLeftQuery = "update User_Info set [Amount Left Over A Month] = " + userLeft + "where Username = "
					+ "'" + userInfo + "'";
			boolean in5 = sta.execute(userLeftQuery);
			System.out.println("The amount you have left over after bills per month is " + userLeft);
			System.out.println();

			// Get amount wanting to save and update to db
			System.out.print("Please enter the amount you wish to save per month: ");
			String userSave = scan.next();
			String userSaveQuery = "update User_Info set [Amount You Want To Save A Month] = " + userSave
					+ " where Username = " + "'" + userInfo + "'";
			boolean in6 = sta.execute(userSaveQuery);
			System.out.println();

		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

	public static String getMonthlyAvg(String user) throws SQLException, ClassNotFoundException {

		try {
			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=Prototype_0.1_App;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			String getMonthlyQuery = "select [Avg Made A Month] from User_Info where Username = ('" + user + "')";
			ResultSet in = sta.executeQuery(getMonthlyQuery);

			// set the user String = to the query's return result.
			while (in.next()) {
				user = in.getString("Avg Made A Month");
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return user;
	}

	public static String getYearlyAvg(String user) throws SQLException, ClassNotFoundException {

		try {
			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=Prototype_0.1_App;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			String getYearlySalary = "select [Avg Made A Year] from User_Info where Username = ('" + user + "')";
			ResultSet in = sta.executeQuery(getYearlySalary);

			while (in.next()) {
				user = in.getString("Avg Made A Year");
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return user;
	}

	public static String getAvgBills(String user) throws SQLException, ClassNotFoundException {

		try {
			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=Prototype_0.1_App;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			String getAvgBills = "select [Avg Cost In Bills A Month] from User_Info where Username = ('" + user + "')";
			ResultSet in = sta.executeQuery(getAvgBills);

			while (in.next()) {
				user = in.getString("Avg Cost In Bills A Month");
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return user;
	}

	public static String getSave(String user) throws SQLException, ClassNotFoundException {

		try {

			// Get a connection to a database and create a statement
			String url = "jdbc:sqlserver://localhost:1433;databaseName=Prototype_0.1_App;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();
			
			String getSave = "select [Amount You Want To Save A Month] from User_Info where Username = ('" + user + "')";
			ResultSet in = sta.executeQuery(getSave);
			
			while(in.next()) {
				user = in.getString("Amount You Want To Save A Month");
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return user;
	}
}
