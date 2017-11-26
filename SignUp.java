
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class SignUp extends JFrame {
		
	//	Declaring Textfields
	private JTextField firstNameText;
	private JTextField lastNameText;
	private JTextField ageText;
	private JTextField emailText;
	private JTextField usernameText;
	
	//	Declaring PasswordField
	private JPasswordField passwordText;
	
	//	Declaring Buttons
	private JButton exitButton;
	private JButton continueButton;
	
	final int FRAME_WIDTH;
	final int FRAME_LENGTH;
	
	//	Constructor creates frame and everything in it
	public SignUp() {
		
		FRAME_WIDTH = 300;
		FRAME_LENGTH = 450;
	
		firstNameText = new JTextField("Enter Your First Name!");
		firstNameText.setBounds(50, 50, 150, 20);
		
		lastNameText = new JTextField("Enter Your Last Name!");
		lastNameText.setBounds(50, 100, 150, 20);
		
		ageText = new JTextField("Enter Your Age!");
		ageText.setBounds(50, 150, 150, 20);
		
		emailText = new JTextField("Enter Your Email!");
		emailText.setBounds(50, 200, 150, 20);
		
		usernameText = new JTextField("Enter Your Username!");
		usernameText.setBounds(50, 250, 150, 20);
		
		passwordText = new JPasswordField("Enter Your Password!");
		passwordText.setBounds(50, 300, 150, 20);
		
		exitButton = new JButton("Exit");
		exitButton.setBounds(50, 350, 100, 25);
		
		continueButton = new JButton("Continue");
		continueButton.setBounds(150, 350, 100, 25);
		
		
		add(firstNameText);
		add(lastNameText);
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
	
	//	Action Listeners
	
	//	Disposes of frame on click
	private class exitButton implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			dispose();
		}
	}
	
	//	This is where Button creates user in database
	private class continueButton implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			String firstName = firstNameText.getText();
			String lastName = lastNameText.getText();
			int age = Integer.parseInt(ageText.getText());
			String email = emailText.getText();
			String username = usernameText.getText();
			String password = new String(passwordText.getPassword());
			
			
		}
	}
}			