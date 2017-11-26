//	Login Screen GUI

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class Login extends JFrame {
	
	//	Declaring panels
	private JPanel myPanel;
	private JPanel firstPanel;
	private JPanel secondPanel;
	private JPanel thirdPanel;
		
	//	Declaring labels
	private JLabel loginLabel;
	
	//	Declaring buttons
	private JButton loginButton;
	private JButton exitButton;
	
	//	Declaring JTextFields
	private JTextField usernameText;
	private JTextField passwordText;	//	NOTE TO SELF: CHANGE TO JPASSWORDFIELD
	
	//	Declaring Final size of window		
	final int FRAME_WIDTH;
	final int FRAME_LENGTH;
	
	//	Constructor does all the work of making the GUI
	//	Initializes declared fields
	public Login() {
		
		//	Size of window
		FRAME_WIDTH = 400;
		FRAME_LENGTH = 200;	
		
		myPanel = new JPanel();
		
		//	Panel for loginLabel
		firstPanel = new JPanel();
		firstPanel.setLayout(new GridLayout());
		
		//	Panel for buttons, uses box layout
		secondPanel = new JPanel();
		secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.X_AXIS));
		
		//	Give label a string and center it to the middle
		loginLabel = new JLabel("User Login", SwingConstants.CENTER);
		
		//	Gives Buttons strings
		loginButton = new JButton("Login");
		exitButton = new JButton("Exit");
		
		//	Gives size to text fields
		usernameText = new JTextField(30);
		passwordText = new JTextField(30);
		
		//	Add to panels
		firstPanel.add(loginLabel);
		
		secondPanel.add(loginButton);
		secondPanel.add(exitButton);
		
		myPanel.add(firstPanel);
		
		myPanel.add(usernameText);
		myPanel.add(passwordText);
	
		myPanel.add(secondPanel);
		
		//	Create and initialize action listeners
		//	Add action listeners to buttons 	
		exitButton exitListener = new exitButton();
		exitButton.addActionListener(exitListener);
		
		loginButton loginListener = new loginButton();
		loginButton.addActionListener(loginListener);
		
		//	Set frame title and size
		setTitle("Login");
		setSize(FRAME_WIDTH, FRAME_LENGTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		//	Add to frame
		add(myPanel);
		setVisible(true);
	}
	
	//	Action Listeners
	
	//	exitButton makes the exit button close
	private class exitButton implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			System.exit(0);
		}
	}
	
	/*	loginButton takes the text from usernameText and passwordText
		This is where the database should be accessed from
	*/
	private class loginButton implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			String username = usernameText.getText();
			String password = passwordText.getText();
		}
	}
	
	//	Calls Constructor
	public static void main(String[] args) {
		Login myLogin = new Login();
	}
}	