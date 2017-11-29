
//	Login Screen GUI

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {

	//	Declaring labels
	private JLabel errorLabel;
	private JLabel loginLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
		
	//	Declaring buttons
	private JButton loginButton;
	private JButton exitButton;
	private JButton signupButton;
	
	
	//	Declaring JTextFields
	private JTextField usernameText;
	
	//	Declaring JPasswordFiled
	private JPasswordField passwordText;
	
	//	Declaring Final size of window		
	final int FRAME_WIDTH;
	final int FRAME_LENGTH;
		
	//	Constructor does all the work of making the GUI
	//	Initializes declared fields
	public Login() {
		
		//	Size of window
		FRAME_WIDTH = 600;
		FRAME_LENGTH = 400;	
		
		
		//	Give label a string and center it to the middle
		loginLabel = new JLabel("User Login");
		loginLabel.setBounds(270, 5, 100, 100);
		
		errorLabel = new JLabel();
        errorLabel.setBounds(175, 160, 100, 25);
		
		usernameLabel = new JLabel("Username: ");
		usernameLabel.setBounds(50, 75, 100, 100);
		
		passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(50, 175, 100, 100);
		
		//	Gives Buttons strings
		loginButton = new JButton("Login");
		loginButton.setBounds(175, 310, 100, 25);
		
		exitButton = new JButton("Exit");
		exitButton.setBounds(275, 310, 100, 25);
		
		signupButton = new JButton("Sign Up");
		signupButton.setBounds(375, 310, 100, 25);
		
		//	Gives size to text fields
		usernameText = new JTextField();
		usernameText.setBounds(175, 110, 300, 30);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(175, 210, 300, 30);
		
		//	Create and initialize action listeners
		//	Add action listeners to buttons 	
		exitButton exitListener = new exitButton();
		exitButton.addActionListener(exitListener);
		
		loginButton loginListener = new loginButton();
		loginButton.addActionListener(loginListener);
		
		signupButton signupListener = new signupButton();
		signupButton.addActionListener(signupListener);
		
		//	Set frame title and size
		setTitle("Login");
		setSize(FRAME_WIDTH, FRAME_LENGTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		//	Add to frame
		add(loginLabel);
		add(usernameLabel);
		add(passwordLabel);
		add(usernameText);
		add(passwordText);
		add(loginButton);
		add(exitButton);
		add(signupButton);
		add(errorLabel);
		
		getContentPane().setBackground(Color.WHITE);
		setVisible(true);
	}

	// Action Listeners

	// exitButton makes the exit button close
	private class exitButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	/*
	 * loginButton takes the text from usernameText and passwordText This is where
	 * the database should be accessed from The MainGUI myMain = new MainGUI(string,
	 * string) should only execute if there is a user with that password in the
	 * database else it should do nothing or give out an error message username and
	 * password are passed through to MainGUI
	 */
	private class loginButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String userExist;
			String passExist;
			boolean userTrue = false;
			boolean passTrue = false;

			
			try {
				String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
				Connection conn = DriverManager.getConnection(url);
				Statement sta = conn.createStatement();
				String username = usernameText.getText();
				String password = new String(passwordText.getPassword());

				passExist = getPass(password);
				userExist = getUser(username);
				try {
				if(username.equals("") || password.equals("")) {
					username = "1";
					password = "1";
				}

				if(username.equals("1") || password.equals("1")) {
					errorLabel.setText("Login Error");
				}
				if (passExist.equals(password) && userExist.equals(username)) {
					MainGUI myMain = new MainGUI(username, password);
					errorLabel.setText("");
				}
				} catch (Exception NullPointerException) {
					errorLabel.setText("Login Error");
				}

			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}

	public String getUser(String username) {

		try {

			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			String getUser = "select userName from User_Table where userName = '" + username + "'";
			ResultSet in = sta.executeQuery(getUser);

			while (in.next()) {
				username = in.getString("userName");
				return username;
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return null;
	}

	public String getPass(String password) {
		try {

			String url = "jdbc:sqlserver://localhost:1433;databaseName=App_DB;integratedSecurity=true;";
			Connection conn = DriverManager.getConnection(url);
			Statement sta = conn.createStatement();

			String getPass = "select userPass from User_Table where userPass = '" + password + "'";
			ResultSet in = sta.executeQuery(getPass);

			while (in.next()) {
				password = in.getString("userPass");
				return password;
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}

	// signupButton opens up the SignUp window
	private class signupButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SignUp mySignUp = new SignUp();
		}
	}

	// Calls Constructor
	public static void main(String[] args) {
		Login myLogin = new Login();
	}
}