/*	Main GUI display
	This should update and display the users info
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class MainGUI extends JFrame {
	
	//	Strings are used to access database
	private String username;
	private String password;
	
	//	Declaring buttons
	private JButton updateButton;
	private JButton logoutButton;
	private JButton displayButton;
	
	//	Declaring textfields for user input
	private JTextField yearlySalaryText;
	private JTextField savingGoalText;
	private JTextField monthlyExpensesText;
	
	private JTextArea viewGoalsArea;

	
	final int FRAME_WIDTH;
	final int FRAME_LENGTH;
	
	
	public MainGUI(String username, String password) {
		
		this.username = username;
		this.password = password;
	
		
		FRAME_WIDTH = 700;
		FRAME_LENGTH = 500;
		
		//	Creating textFields
		//	bound them to the JFrame with .setBounds
		yearlySalaryText = new JTextField("Enter Yearly Salary");
		yearlySalaryText.setBounds(10, 50, 200, 30);
		
		savingGoalText = new JTextField("Enter Monthly Saving Goal");
		savingGoalText.setBounds(10, 100, 200, 30);
		
		monthlyExpensesText = new JTextField("Enter Total Monthly Expenses");
		monthlyExpensesText.setBounds(10, 150, 200, 30);
		
		//	.setEditable prevents the user from messing with the output
		viewGoalsArea = new JTextArea(20, 20);
		viewGoalsArea.setBounds(350, 50, 320, 400);
		viewGoalsArea.setEditable(false);
		
		//	Creating buttons
		updateButton = new JButton("Update");
		updateButton.setBounds(10, 400, 100, 25);
		
		logoutButton = new JButton("Logout");
		logoutButton.setBounds(210, 400, 100, 25);
		
		displayButton = new JButton("Display");
		displayButton.setBounds(110, 400, 100, 25);
		
		//	Creating new instance of actionlisteners
		//	Placing actionListeners to buttons
		logoutButton logoutListener = new logoutButton();
		logoutButton.addActionListener(logoutListener);
		
		updateButton updateListener = new updateButton();
		updateButton.addActionListener(updateListener);
		
		displayButton displayListener = new displayButton();
		displayButton.addActionListener(displayListener);
		
		//	Add everything to frame
		add(yearlySalaryText);
		add(savingGoalText);
		add(monthlyExpensesText);
		add(viewGoalsArea);
		add(updateButton);
		add(logoutButton);
		add(displayButton);
		
		//	Finalize Frame
		setLayout(null);
		setTitle("MAIN");
		setSize(FRAME_WIDTH, FRAME_LENGTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		setVisible(true);
	}
	
	//	Action listeners
	
	//	logoutButton disposes of the JFrame
	//	Quick and easy way to 'log out'
	private class logoutButton implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			dispose();
		}
	}
	
	//	updateButton should take the user input and place it in the database
	//	int variables getting the user input are created
	//	Throw number format exception if there is something but int in there
	//	Try catch maybe?
	private class updateButton implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
			int salary = Integer.parseInt(yearlySalaryText.getText());
			int savings = Integer.parseInt(savingGoalText.getText());
			int expenses = Integer.parseInt(monthlyExpensesText.getText());
			
		}
	}
	
	//	displayButton should get the users data from database
	//	The users username and passwords is pass through the login and stored
	//	 in variable username and variable password
	private class displayButton implements ActionListener {
		public void actionPerformed (ActionEvent e) {
		
			//	This is a test that prints the username and password
			viewGoalsArea.setText(username + password + "\n");
			
			//	Use viewGoalsArea.setText("Some string"); to display in text area
		}
	}
	
}