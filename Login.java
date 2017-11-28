
//	Login Screen GUI

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends JFrame {

	// Declaring panels
	private JPanel myPanel;
	private JPanel firstPanel;
	private JPanel secondPanel;

	// Declaring labels
	private JLabel loginLabel;

	// Declaring buttons
	private JButton loginButton;
	private JButton exitButton;
	private JButton signupButton;

	// Declaring JTextFields
	private JTextField usernameText;

	// Declaring JPasswordFiled
	private JPasswordField passwordText;

	// Declaring Final size of window
	final int FRAME_WIDTH;
	final int FRAME_LENGTH;

	// Constructor does all the work of making the GUI
	// Initializes declared fields
	public Login() {

		// Size of window
		FRAME_WIDTH = 400;
		FRAME_LENGTH = 200;

		myPanel = new JPanel();

		// Panel for loginLabel
		firstPanel = new JPanel();
		firstPanel.setLayout(new GridLayout());

		// Panel for buttons, uses box layout
		secondPanel = new JPanel();
		secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.X_AXIS));

		// Give label a string and center it to the middle
		loginLabel = new JLabel("User Login", SwingConstants.CENTER);

		// Gives Buttons strings
		loginButton = new JButton("Login");
		exitButton = new JButton("Exit");
		signupButton = new JButton("Sign Up");

		// Gives size to text fields
		usernameText = new JTextField(30);
		passwordText = new JPasswordField(30);

		// Add to panels
		firstPanel.add(loginLabel);

		secondPanel.add(loginButton);
		secondPanel.add(signupButton);
		secondPanel.add(exitButton);

		myPanel.add(firstPanel);

		myPanel.add(usernameText);
		myPanel.add(passwordText);

		myPanel.add(secondPanel);

		// Create and initialize action listeners
		// Add action listeners to buttons
		exitButton exitListener = new exitButton();
		exitButton.addActionListener(exitListener);

		loginButton loginListener = new loginButton();
		loginButton.addActionListener(loginListener);

		signupButton signupListener = new signupButton();
		signupButton.addActionListener(signupListener);

		// Set frame title and size
		setTitle("Login");
		setSize(FRAME_WIDTH, FRAME_LENGTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// Add to frame
		add(myPanel);
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
		public void actionPerformed (ActionEvent e) {
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
			
			if(passExist.equals(password) && userExist.equals(username)) {
				MainGUI myMain = new MainGUI(username, password);
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
			
			while(in.next()) {
				password = in.getString("userPass");
			}		
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return password;
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