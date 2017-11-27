import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUp extends JFrame {

	// Declaring Textfields
	private JTextField firstNameText;
	private JTextField lastNameText;
	private JTextField ageText;
	private JTextField emailText;
	private JTextField usernameText;

	// Declaring PasswordField
	private JPasswordField passwordText;

	// Declaring Buttons
	private JButton exitButton;
	private JButton continueButton;

	final int FRAME_WIDTH;
	final int FRAME_LENGTH;

	// Constructor creates frame and everything in it
	public SignUp() {

		FRAME_WIDTH = 300;
		FRAME_LENGTH = 450;
	
		firstNameText = new JTextField("Enter Your Name!");
		firstNameText.setBounds(50, 50, 150, 20);

		ageText = new JTextField("Enter Your Age!");
		ageText.setBounds(50, 100, 150, 20);
		
		emailText = new JTextField("Enter Your Email!");
		emailText.setBounds(50, 150, 150, 20);
		
		usernameText = new JTextField("Enter Your Username!");
		usernameText.setBounds(50, 200, 150, 20);
		
		passwordText = new JPasswordField("Enter Your Password!");
		passwordText.setBounds(50, 250, 150, 20);
		
		exitButton = new JButton("Exit");
		exitButton.setBounds(50, 350, 100, 25);
		
		continueButton = new JButton("Continue");
		continueButton.setBounds(150, 350, 100, 25);

		add(firstNameText);
		add(ageText);
		add(emailText);
		add(usernameText);
		add(passwordText);

		add(continueButton);
		add(exitButton);

		exitButton exitListener = new exitButton();
		exitButton.addActionListener(exitListener);

		continueButton continueListener = new continueButton();
		continueButton.addActionListener(continueListener);

		setLayout(null);
		setTitle("Sign Up");
		setSize(FRAME_WIDTH, FRAME_LENGTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
	}

	// Action Listeners

	// Disposes of frame on click
	private class exitButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	// This is where Button creates user in database
	private class continueButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				
				String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
				Connection conn = DriverManager.getConnection(url);
				Statement sta = conn.createStatement();
				
				String firstName = firstNameText.getText();
				String userNameFLQuery = "insert into User_Table (userNameFL) values ('" + firstName + "')";
				boolean in1 = sta.execute(userNameFLQuery);
				
				
				int age = Integer.parseInt(ageText.getText());
				String userAgeQuery = "update User_Table set userAge = " + age + " where userNameFL = " + "'" + firstName + "'";
				boolean in2 = sta.execute(userAgeQuery);
				
				
				String email = emailText.getText();
				String userEmailQuery = "update User_Table set userEmail = " + "'" + email + "'" + " where userNameFL = " + "'" + firstName + "'";
				boolean in3 = sta.execute(userEmailQuery);
				
				String username = usernameText.getText();
				String userNameQuery = "update User_Table set userName = " + "'" + username + "'" + " where userNameFL = " + "'" + firstName + "'";
				boolean in5 = sta.execute(userNameQuery);
				
				//create user specific table based on  their user name and generate the columns in that table: ID, monthly salary, and monthly goal. 
				String userTable = "CREATE TABLE " +  username + " (ID int, Monthly_Salary FLOAT, Monthly_Goal FLOAT);";
				boolean in8 = sta.execute(userTable);
				
				String password = new String(passwordText.getPassword());
				String userPassQuery = "update User_Table set userPass = " + "'" + password + "'" + " where userNameFL = " + "'" + firstName + "'";
				boolean in6 = sta.execute(userPassQuery);
				
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}
}