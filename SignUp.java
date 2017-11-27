
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class SignUp extends JFrame {
		
	//	Declaring Textfields
	private JTextField firstNameText;
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
		
		//	Creating Textfields
		//	.setBounds uses (x, y, width, height);
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
		exitButton.setBounds(150, 350, 100, 25);
		
		continueButton = new JButton("Continue");
		continueButton.setBounds(50, 350, 100, 25);
		
		
		//	Adding components to frame
		add(firstNameText);
		add(ageText);
		add(emailText);
		add(usernameText);
		add(passwordText);
		
		add(continueButton);
		add(exitButton);		
		
		//	Adding action listeners to buttons
		exitButton exitListener = new exitButton();
		exitButton.addActionListener(exitListener);
		
		continueButton continueListener = new continueButton();
		continueButton.addActionListener(continueListener);
		
		//	Finalizing Frame
		
		setLayout(null);	//	setLayout is null because of we are using .setBounds
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
	//	Throws error is no number in ageText
	//	Try catch?
	private class continueButton implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			String firstName = firstNameText.getText();
			int age = Integer.parseInt(ageText.getText());
			String email = emailText.getText();
			String username = usernameText.getText();
			String password = new String(passwordText.getPassword());
			
			
		}
	}
}			