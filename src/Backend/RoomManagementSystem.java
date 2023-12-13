package backend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import backend.Control.Admin;
import backend.Control.Professor;
import backend.User;
import backend.log;

public class RoomManagementSystem extends JFrame
{
	public Professor activeAccount;
	public User[] listOfUsers;
	public standardRoom listOfRooms[];
	public String[] availableTime = new String[Control.listOfRooms.getTotalTime()];
	public String[] availableRoom = new String[Control.listOfRooms.getTotalRooms()];
	public loginFormGui loginForm;
	
// List of availability of each room on selected time
	class middleBar extends JPanel
	{
		private final int Limit = activeAccount.getTotalLogs();
		private JPanel[] roomNamePanel = new JPanel[Limit];
		private JPanel[] roomStatusPanel = new JPanel[Limit];
		private JPanel[] timePanel = new JPanel[Limit]; 
		private JLabel[] roomStatus = new JLabel[Limit];
		private JPanel[] usernamePanel = new JPanel[Limit]; 
		private JLabel[] username = new JLabel[Limit];
		private JLabel[] roomName = new JLabel[Limit];
		private JLabel[] timeSchedule = new JLabel[Limit];

		public middleBar()
		{
			setLayout(new GridBagLayout());
			setBorder(BorderFactory.createLineBorder(Color.GRAY ,1));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.VERTICAL;
			//gbc.insets = new Insets(5,8,0,8);
			
			for(int i = 0; i < Limit; i++)
			{
				
				System.out.print(i);
				roomStatusPanel[i] = new JPanel();
				roomStatusPanel[i].setBorder(BorderFactory.createLineBorder(Color.GRAY ,1));
				roomNamePanel[i] = new JPanel();
				roomNamePanel[i].setBorder(BorderFactory.createLineBorder(Color.GRAY ,1));
				roomStatus[i] = new JLabel();
				roomName[i] = new JLabel();
				timePanel[i] = new JPanel();
				timePanel[i].setBorder(BorderFactory.createLineBorder(Color.GRAY ,1));
				timeSchedule[i] = new JLabel();
				usernamePanel[i] = new JPanel();
				usernamePanel[i].setBorder(BorderFactory.createLineBorder(Color.GRAY ,1));
				username[i] = new JLabel();
					
				roomStatusPanel[i].add(roomStatus[i]);
				roomNamePanel[i].add(roomName[i]);
				timePanel[i].add(timeSchedule[i]);
				usernamePanel[i].add(username[i]);
					
				roomStatus[i].setText(" None");
				username[i].setText("None");
				roomName[i].setText("None");
				timeSchedule[i].setText("None");
					
				username[i].setPreferredSize(new Dimension(80, 20));
				roomStatusPanel[i].setPreferredSize(new Dimension(80, 20));
				roomNamePanel[i].setPreferredSize(new Dimension(120, 20));
				timeSchedule[i].setPreferredSize(new Dimension(120, 20));
					
				add(roomStatusPanel[i], gbc);
				gbc.gridx++;
				add(usernamePanel[i], gbc);
				gbc.gridx++;
				add(roomNamePanel[i], gbc);
				gbc.gridx++;
				add(timePanel[i], gbc);
				gbc.gridx++;
				gbc.gridx = 0;
				gbc.gridy++;
			}
			
		}
		
		// if a user reconnected / logged-in then grab the list of logs and display
		public void reloadList()
		{
			activeAccount.updateLog(activeAccount.getUserName());
			for(int i = 0; i < Limit; i++)
			{
				roomStatus[i].setText(activeAccount.getLogStatus(i));
				username[i].setText(activeAccount.getLogUser(i));
				roomName[i].setText(activeAccount.getLogRoom(i));
				timeSchedule[i].setText(activeAccount.getLogTime(i));
			}
			
		}
		
		// if a Log got updated / added then update the list display
		void updateListLogin()
		{
			loginForm.buildUpdate(activeAccount.getUserName(), activeAccount.getLatestLog().getRoom(), timeSchedule[0].getText());
		}
	}
	
// User Information and logout Group Layout
	class upperBar extends JPanel implements ActionListener
	{
		private loginFormGui loginForm;
		private RoomManagementSystem rmsForm;
		private JPanel logoutPanel, greetingPanel, detailPanel;
		private JButton logoutBtn;
		private JLabel greetingLabel, detailLabel;
		upperBar(loginFormGui lfg, RoomManagementSystem rms)
		{
			loginForm = lfg;
			rmsForm = rms;
			
			setLayout(new GridBagLayout());
			setBorder(BorderFactory.createLineBorder(Color.GRAY ,1));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.anchor = GridBagConstraints.WEST;
			//gbc.fill = GridBagConstraints.VERTICAL;
			
			greetingPanel = new JPanel();
			greetingLabel = new JLabel();
			greetingLabel.setText("Good Morning!");
			greetingPanel.add(greetingLabel);
			greetingPanel.setPreferredSize(new Dimension(500, 26));
			
			detailPanel = new JPanel();
			detailLabel = new JLabel();
			detailLabel.setText(activeAccount.getUserName());
			detailPanel.add(detailLabel);
			detailPanel.setPreferredSize(new Dimension(500, 26));
			
			gbc.anchor = GridBagConstraints.EAST;
			logoutPanel = new JPanel();
			logoutBtn = new JButton("Logout");
			logoutBtn.addActionListener(this);
			logoutPanel.add(logoutBtn);
			
			add(greetingPanel, gbc);
			gbc.gridy++;
			add(detailPanel, gbc);
			gbc.gridy = 0;
			gbc.gridx++;
			add(logoutPanel, gbc);
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//System.out.print("You have logout");
			loginForm.setVisible(true);
			rmsForm.setVisible(false);
			
		}
		
	}
	
	class lowerBar extends JPanel implements ActionListener
	{
		private JPanel lowerPanel;
		private JButton requestBtn;
		private JComboBox<String> timeSelect;
		private JComboBox<String> roomSelect;

		lowerBar()
		{
			
			// Because the default dropdown selected is the first item, so we get the available time for the first room
			showAvailable(listOfRooms[0]);
			
			// We get all the available rooms from the previous array and initialize them here
			for(int i = 0; i < listOfRooms.length; i++)
			{
				availableRoom[i] = listOfRooms[i].getRoomIdentity();
			}
			
			setLayout(new GridBagLayout());
			setBorder(BorderFactory.createLineBorder(Color.GRAY ,1));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(5,20,0,20);
			
			
			lowerPanel = new JPanel();
			
			timeSelect = new JComboBox<String>(availableTime);
			timeSelect.addItemListener(new timeListener("time Select"));
			timeSelect.setVisible(true);
			lowerPanel.add(timeSelect);
			
			roomSelect = new JComboBox<String>(availableRoom);
			roomSelect.addItemListener(new roomListener("room Select"));
			roomSelect.setVisible(true);
			lowerPanel.add(roomSelect);
			
			
			requestBtn = new JButton("Request");
			requestBtn.addActionListener(this);
			lowerPanel.add(requestBtn);
			lowerPanel.setPreferredSize(new Dimension(500, 42));
			add(lowerPanel, gbc);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String dummyTime = timeSelect.getSelectedItem().toString();
			String dummyRoom = roomSelect.getSelectedItem().toString();
			//System.out.println("Requested for " + dummyRoom + " at " + dummyTime);
			activeAccount.addLog(activeAccount.getUserName(), dummyRoom, dummyTime);
			group2.reloadList();
			group2.updateListLogin();
		}
	}
	
	// This updates the selection dropdown list of the available time schedule for a selected room
	public void showAvailable(standardRoom stdRoom)
	{
		availableTime = new String[Control.listOfRooms.getTotalTime()];
		availableTime = Control.listOfRooms.getAvailableTime(stdRoom);
		//for(int i = 0; i < availableTime.length; i++)
		//{
		//	System.out.println(availableTime[i]);				
		//}
	}
	
	class timeListener implements ItemListener
	{
		private String dropDownName;
		
		public timeListener(String name)
		{
			this.dropDownName = name;
		}
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			 if (e.getStateChange() == ItemEvent.SELECTED) 
			 {
	                String selectedItem = (String) e.getItem();
			 }
		}
		
	}
	
	class roomListener implements ItemListener
	{
		private String dropDownName;
		
		public roomListener(String name)
		{
			this.dropDownName = name;
		}
	
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			 if (e.getStateChange() == ItemEvent.SELECTED) 
			 {
				 String selectedItem = (String) e.getItem();
				 for(int i = 0; i < listOfRooms.length; i++)
				 {
					 // Check if matching name found in list of rooms and grab that instance
					 if(selectedItem.equals(listOfRooms[i].getRoomIdentity()))
					 {
						 showAvailable(listOfRooms[i]);					 
					 }
					 else	// If none, assign nothing
					 {
						 
					 }
				 }
			 }
		}
	}
	
	private upperBar group1;
	private middleBar group2;
	private lowerBar group3;
	
	// Update the display list according to the active user's list of logs && update the user's log List. If there are changes made by admin
	public void loadData()
	{
		group2.reloadList();
	}
	
	public RoomManagementSystem(Professor prof, loginFormGui lfg, standardRoom[] lor, User[] user)
	{
		activeAccount = prof;
		listOfRooms = lor;
		loginForm = lfg;
		listOfUsers = user;
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.VERTICAL;

		add((group1 = new upperBar(lfg, this)), gbc);
		gbc.gridy++;
		add((group2 = new middleBar()), gbc);
		gbc.gridy++;
		add((group3 = new lowerBar()), gbc);
		
		setTitle("Room Management System");
		
		
	}

}
