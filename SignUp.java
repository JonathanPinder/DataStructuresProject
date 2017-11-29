import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUp extends JFrame {
	// Declaring Labels
	private JLabel nameLabel;
	private JLabel emailLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel repasswordLabel;

	// Declaring Textfields
	private JTextField firstNameText;
	private JTextField emailText;
	private JTextField usernameText;

	// Declaring PasswordField
	private JPasswordField passwordText;
	private JPasswordField repasswordText;

	// Declaring Buttons
	private JButton exitButton;
	private JButton continueButton;

	final int FRAME_WIDTH;
	final int FRAME_LENGTH;

	// Constructor creates frame and everything in it
	public SignUp() {

		FRAME_WIDTH = 500;
		FRAME_LENGTH = 500;

		nameLabel = new JLabel("Name: ");
		nameLabel.setBounds(50, 50, 100, 20);

		emailLabel = new JLabel("Email: ");
		emailLabel.setBounds(50, 100, 100, 20);

		usernameLabel = new JLabel("Username: ");
		usernameLabel.setBounds(50, 150, 100, 20);

		passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(50, 200, 100, 20);

		repasswordLabel = new JLabel("Type Again: ");
		repasswordLabel.setBounds(50, 250, 100, 30);

		// Creating Textfields
		// .setBounds uses (x, y, width, height);
		firstNameText = new JTextField();
		firstNameText.setBounds(200, 50, 150, 20);

		emailText = new JTextField();
		emailText.setBounds(200, 100, 150, 20);

		usernameText = new JTextField();
		usernameText.setBounds(200, 150, 150, 20);

		passwordText = new JPasswordField();
		passwordText.setBounds(200, 200, 150, 20);

		repasswordText = new JPasswordField();
		repasswordText.setBounds(200, 250, 150, 20);

		// Buttons
		exitButton = new JButton("Exit");
		exitButton.setBounds(300, 350, 100, 25);

		continueButton = new JButton("Continue");
		continueButton.setBounds(200, 350, 100, 25);

		// Adding components to frame
		add(nameLabel);
		add(emailLabel);
		add(usernameLabel);
		add(passwordLabel);
		add(repasswordLabel);
		add(firstNameText);
		add(emailText);
		add(usernameText);
		add(passwordText);
		add(repasswordText);

		add(continueButton);
		add(exitButton);

		// Adding action listeners to buttons
		exitButton exitListener = new exitButton();
		exitButton.addActionListener(exitListener);

		continueButton continueListener = new continueButton();
		continueButton.addActionListener(continueListener);

		// Finalizing Frame

		setLayout(null); // setLayout is null because of we are using .setBounds
		setTitle("Sign Up");
		setSize(FRAME_WIDTH, FRAME_LENGTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		getContentPane().setBackground(Color.WHITE);
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
				String email = emailText.getText();
				String username = usernameText.getText();
				String password = new String(passwordText.getPassword());
				String rePassword = new String(repasswordText.getPassword());

				if (password.equals(rePassword)) {

					// queries
					String userNameFLQuery = "insert into User_Table (userNameFL) values ('" + firstName + "')";
					boolean in1 = sta.execute(userNameFLQuery);

					String userEmailQuery = "update User_Table set userEmail = " + "'" + email + "'"
							+ " where userNameFL = " + "'" + firstName + "'";
					boolean in3 = sta.execute(userEmailQuery);

					String userNameQuery = "update User_Table set userName = " + "'" + username + "'"
							+ " where userNameFL = " + "'" + firstName + "'";
					boolean in5 = sta.execute(userNameQuery);

					// create user specific table based on their user name and generate the columns
					// in that table: ID, monthly salary, and monthly goal.
					String userTable = "CREATE TABLE " + username
							+ " (ID int, Monthly_Salary FLOAT, Monthly_Goal FLOAT, Expense_Name varchar(50));";
					boolean in8 = sta.execute(userTable);
					String userID = "insert into " + username + " (ID) values ('1')";
					boolean in = sta.execute(userID);
					
					String userPassQuery = "update User_Table set userPass = " + "'" + password + "'"
							+ " where userNameFL = " + "'" + firstName + "'";
					boolean in6 = sta.execute(userPassQuery);
					
					dispose();
				} else {

					final JOptionPane optionPane = new JOptionPane(
							"You have submitted\n" + "an invalid password\n" + "Do you understand?",
							JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);

				}

			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}
}