package backend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import backend.RoomManagementSystem;
import backend.Control.Professor;
import backend.Control.Admin;


public class loginFormGui extends JFrame implements ActionListener 
{
	protected char[] password1 = {'c'};
	protected char[] password2 = {'a'};
	public Professor user1 = new Professor("Lance", password1);
	public Professor user2 = new Professor("Gabriel", password2);
	public User[] listOfUsers = new User[4];
	public Admin admin1 = new Admin("admin1", password1);
	public Admin admin2 = new Admin("admin2", password2);
	public ComputerLabRoom[] listOfRooms;
	
// Update Panel Group Layout
	class groupOne extends JPanel
	{
		private final int Limit = 8;
		private JPanel[] updatePanel = new JPanel[Limit];
		private JLabel[] updateLabel = new JLabel[Limit];
		
		public groupOne()
		{
			setLayout(new GridBagLayout());
			setBorder(BorderFactory.createLineBorder(Color.GRAY ,1));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 0;
			gbc.insets = new Insets(4,4,4,4);
			for(int i = 0; i < Limit; i++)
			{
				updateLabel[i] = new JLabel();
				updateLabel[i].setText(""); // 24 char
				updateLabel[i].setFont(new Font("Century", Font.PLAIN, 10));
				updatePanel[i] = new JPanel();
				updatePanel[i].setPreferredSize(new Dimension(250, 22));
				updatePanel[i].add(updateLabel[i]);
				add(updatePanel[i], gbc);
				gbc.gridy++;
			}
		}
		
		// Updates the list of recent updates from staffs and admins (Admin part not yet implemented)
		public void addUpdate(String compiledString)
		{
			for(int i = 7; i >= 0; i--)
			{
				if(i == 0)
				{
					updateLabel[i].setText(compiledString);
				}
				else
				{
					updateLabel[i].setText(updateLabel[i-1].getText());				
				}
			}			
		}
		
	}
	
// Message Panel Group Layout
	class groupTwo extends JPanel
	{
		private JPanel messagePanel;
		private JLabel messageLabel;
		
		public groupTwo()
		{
			setLayout(new GridBagLayout());
			setBorder(BorderFactory.createLineBorder(Color.GRAY ,1));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.anchor = GridBagConstraints.NORTH;
			
			
			messageLabel = new JLabel();
			messageLabel.setText("Welcome");
			messageLabel.setFont(new Font("Century", Font.PLAIN, 20));
			
			messagePanel = new JPanel();
			messagePanel.add(messageLabel);
			
			add(messagePanel, gbc);
			gbc.gridy++;
			gbc.gridx++;
			gbc.gridy = 0;
			gbc.weightx = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;
		}
		
		public String getMessageLabel() {
			return messageLabel.getText();
		}
		public void setMessageLabel(String name) {
			messageLabel.setText(name);
		}
		
	}
	
// Text fields and Labels Group Layout
	class groupThree extends JPanel
	{
		private JPanel userPanel, passPanel;
		private JLabel userLabel, passLabel;
		private final JTextField userField;
		private final JPasswordField passField;

		
		groupThree()
		{
			setLayout(new GridBagLayout());
			setBorder(BorderFactory.createLineBorder(Color.GRAY ,1));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(5,12,0,12);
			
			userLabel = new JLabel();
			userLabel.setText("Username");
			
			passLabel = new JLabel();
			passLabel.setText("Password");
			
			userPanel = new JPanel();
			userPanel.add(userLabel);
			passPanel = new JPanel();
			passPanel.add(passLabel);
			
			add(userPanel, gbc);
			gbc.gridy++;
			add(passPanel, gbc);
			gbc.gridx++;
			gbc.gridy = 0;
			gbc.weightx = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			
			add((userField = new JTextField(25)), gbc);
			gbc.gridy++;
			add((passField = new JPasswordField(25)), gbc);
			
		}
		public String getUserText()
		{
			return userField.getText();
		}
		public void setUserText(String name)
		{
			userField.setText(name);
		}
		public String getPassText() {
	        // Use getPassword() and convert it to a String securely
	        return new String(passField.getPassword());
	    }
		public void setPassText(String name)
		{
			passField.setText(name);
		}
	}
	
// Buttons and ActionListeners Group Layout
	class groupFour extends JPanel
	{
		private JPanel buttonPanel;
		public JButton submitButton;
		
		groupFour(loginFormGui login)
		{
			setLayout(new GridBagLayout());
			setBorder(BorderFactory.createLineBorder(Color.GRAY ,1));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.anchor = GridBagConstraints.EAST;
			gbc.insets = new Insets(4,0,0,0);
			
			submitButton = new JButton("Submit");
			submitButton.addActionListener(login);
			buttonPanel = new JPanel();
			buttonPanel.add(submitButton);
			
			add(buttonPanel, gbc);
			gbc.fill = GridBagConstraints.VERTICAL;
		}		
	}
	
	// Layout all the Group parts together to form login GUI
		private groupOne updatePart;
		private groupTwo messagePart;
		private groupThree inputPart;
		private groupFour buttonPart;
		
		// This method is used to compile details of logs and sends the compiled string into addUpdate() method of groupOne
		public void buildUpdate(String user, String room, String time)
		{
			String update = user + " REQ " + room + " at " + time;
			updatePart.addUpdate(update);
		}
		
		// Constructor of class, These work once an instance of the class is instantiated.
		public loginFormGui() 
		{ 
		
		listOfUsers[0] = user1;
		listOfUsers[1] = user2;
		listOfUsers[2] = admin1;
		listOfUsers[3] = admin2;
		
		User.initializeList();
		
		listOfRooms = Control.listOfRooms.generateRooms();
		
		setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.VERTICAL;
        add((updatePart = new groupOne()), gbc);
        gbc.gridx++;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weightx = 1;
        gbc.gridheight = GridBagConstraints.VERTICAL;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add((messagePart = new groupTwo()), gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        add((inputPart = new groupThree()), gbc);
        gbc.gridx++;
        buttonPart = new groupFour(this);
        add(buttonPart, gbc);
        
		setTitle("Login Form");
		
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String userName = inputPart.getUserText();
			char[] passWord = inputPart.getPassText().toCharArray();
		
			for(int i = 0; i < listOfUsers.length; i++)
			{
				String nameDummy = listOfUsers[i].getUserName();
				char[] passDummy = listOfUsers[i].getPassword();
				
				// Check for the equivalent username and passwords within the users array
				if(userName.equals(nameDummy) && Arrays.equals(passWord, passDummy))
				{
					// Check whether the selected instance within the array is a Professor
					if(listOfUsers[i] instanceof Professor)
					{
						Professor userDummy = (Professor) listOfUsers[i];
						messagePart.setMessageLabel("Welcome! " + nameDummy);
						buttonPart.submitButton.setEnabled(false);
						
						ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
						
						executorService.schedule(() -> 
						{
							// Shutdown the executor service to release resources
							this.setVisible(false);
							RoomManagementSystem managementForm = new RoomManagementSystem(userDummy, this, listOfRooms, listOfUsers);
							managementForm.loadData();
							managementForm.setSize(610,362);
							managementForm.setVisible(false);
							managementForm.setResizable(false);
							managementForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							managementForm.setLocationRelativeTo(null);
							managementForm.setVisible(true);
							messagePart.setMessageLabel("Welcome");
							inputPart.setUserText("");
							inputPart.setPassText("");
							buttonPart.submitButton.setEnabled(true);
							executorService.shutdown();
						}, 2, TimeUnit.SECONDS);
						break;
					}
					// if its not a Professor instance. Consider it as an Admin
					else if(listOfUsers[i] instanceof Admin)
					{
						// Store the instance for referrence
						Admin userDummy = (Admin) listOfUsers[i];
						messagePart.setMessageLabel("Welcome Admin! " + nameDummy);
						buttonPart.submitButton.setEnabled(false);
						
						ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
						
						executorService.schedule(() -> 
						{
							// Shutdown the executor service to release resources
							this.setVisible(false);
							AdminFormGui adminForm = new AdminFormGui(userDummy, this);
							adminForm.loadData(listOfRooms);
							adminForm.setSize(730,440);
							adminForm.setVisible(false);
							adminForm.setResizable(false);
							adminForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							adminForm.setLocationRelativeTo(null);
							adminForm.setVisible(true);
							messagePart.setMessageLabel("Welcome");
							inputPart.setUserText("");
							inputPart.setPassText("");
							buttonPart.submitButton.setEnabled(true);
							executorService.shutdown();
						}, 2, TimeUnit.SECONDS);
						break;
					}
				}
				
				// If no equivalent username and passwords are found. Consider it as no account found or no such account exists
				else {
					messagePart.setMessageLabel("Unrecognized Account");
					inputPart.setUserText("");
					inputPart.setPassText("");
				}
				
			}
			
		}
}
