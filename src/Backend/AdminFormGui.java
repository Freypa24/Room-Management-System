package backend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import backend.Control.Admin;
import backend.Control.Professor;

public class AdminFormGui extends JFrame
{
	private Admin activeAccount;
	private loginFormGui loginForm;
	private User[] listOfUsers;
	private log[] listOfLogs = new log[10];
	private ComputerLabRoom[] listOfRooms;
	private int currentPage;

	private header group1;
	private selectionList group2;
	private logList group3;

	
	// will contain greeting, notifications, and logout
	class header extends JPanel implements ActionListener
	{
		private AdminFormGui adminForm;
		private JPanel logoutPanel, greetingPanel;
		private JButton logoutBtn;
		private JLabel greetingLabel;
		
		header(AdminFormGui adminForm)
		{
			this.adminForm = adminForm;
			
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			
			greetingPanel = new JPanel();
			greetingLabel = new JLabel();
			greetingLabel.setText("Welcome Back! " + activeAccount.getUserName());
			greetingPanel.add(greetingLabel);
			
			logoutPanel = new JPanel();
			logoutBtn = new JButton("Logout");
			logoutBtn.addActionListener(this);
			logoutPanel.add(logoutBtn);
			
			add(greetingPanel, gbc);
			gbc.gridx++;
			add(logoutPanel, gbc);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			adminForm.setVisible(false);
			loginForm.setVisible(true);
		}
	}
	
	// Lower Middle part, contains the page buttons for logList
	class selectionList extends JPanel 
	{
		private JPanel[] pagePanel;
		private JButton[] pageButton;
		private int count = 1;
		
		selectionList()
		{
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			
			pagePanel = new JPanel[3];
			pageButton = new JButton[3];
			
			// The loop has to start at 0. but the buttons should start 1.
			// This is because the array indexes start at 0. but to users/humans, we should see it start at 1
			for(int i = 0; i < 3; i++)
			{
				pagePanel[i] = new JPanel();
				pageButton[i] = new JButton("Page " + (count + i));
				pageButton[i].addActionListener(new pageListener(i));
				pagePanel[i].add(pageButton[i]);
				add(pagePanel[i], gbc);
				gbc.gridx++;
			}
		}
	}
	
	class pageListener implements ActionListener
	{
		private int pageNum;
		
		public pageListener(int pageNum)
		{
			this.pageNum = pageNum + 1;		// We add 1 because we want to make sure it the display starts from 1 and not 0.
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			currentPage = pageNum;
			listOfLogs = activeAccount.displayLogs(currentPage);
			for(int i = listOfLogs.length-1; i >= 0; i--)
			{
				System.out.println(listOfLogs[i].getStatus() + " " + listOfLogs[i].getUsername() + " " + listOfLogs[i].getRoom() + " " + listOfLogs[i].getTimeSchedule());
			
			}
			group3.updateList();
		}
		
	}
	
	// Upper Middle part, contains the list of logs from users
	class logList extends JPanel
	{
		private final int Limit = activeAccount.getTotalLogsAdmin();
		private JPanel[] roomNamePanel = new JPanel[Limit];
		private JPanel[] roomStatusPanel = new JPanel[Limit];
		private JPanel[] timePanel = new JPanel[Limit]; 
		private JLabel[] roomStatus = new JLabel[Limit];
		private JPanel[] usernamePanel = new JPanel[Limit]; 
		private JLabel[] username = new JLabel[Limit];
		private JLabel[] roomName = new JLabel[Limit];
		private JLabel[] timeSchedule = new JLabel[Limit];
		private JPanel[] approvePanel = new JPanel[Limit];
		private JPanel[] denyPanel = new JPanel[Limit];
		private JButton[] approve = new JButton[Limit];
		private JButton[] deny = new JButton[Limit];
		
		public logList()
		{
			setLayout(new GridBagLayout());
			setBorder(BorderFactory.createLineBorder(Color.GRAY ,1));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
		
			for(int i = 0; i < Limit-20; i++)
			{
				
				System.out.print(i);
				approvePanel[i] = new JPanel();
				denyPanel[i] = new JPanel();
				approvePanel[i].setBorder(BorderFactory.createLineBorder(Color.GRAY ,1));
				denyPanel[i].setBorder(BorderFactory.createLineBorder(Color.GRAY ,1));
				approve[i] = new JButton("Approve");
				deny[i] = new JButton("Deny");
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
				
				approvePanel[i].add(approve[i]);
				denyPanel[i].add(deny[i]);
				roomStatusPanel[i].add(roomStatus[i]);
				roomNamePanel[i].add(roomName[i]);
				timePanel[i].add(timeSchedule[i]);
				usernamePanel[i].add(username[i]);
				
				approve[i].addActionListener(new approveListener(i));
				deny[i].addActionListener(new denyListener(i));
				
				approve[i].setEnabled(false);
				deny[i].setEnabled(false);
				roomStatus[i].setText("None");
				username[i].setText("None");
				roomName[i].setText("None");
				timeSchedule[i].setText("None");
				
				approve[i].setPreferredSize(new Dimension(100, 20));
				deny[i].setPreferredSize(new Dimension(80, 20));
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
				add(approvePanel[i], gbc);
				gbc.gridx++;
				add(denyPanel[i], gbc);
				gbc.gridx = 0;
				gbc.gridy++;
				
			}	
		}
		
		// display the current page number's list of logs
		public void updateList()
		{
			for(int i = 0; i < listOfLogs.length; i++)
			{
				roomStatus[i].setText(listOfLogs[i].getStatus());
				username[i].setText(listOfLogs[i].getUsername());
				roomName[i].setText(listOfLogs[i].getRoom());
				timeSchedule[i].setText(listOfLogs[i].getTimeSchedule());
				
				// make this into a switch case
				if(listOfLogs[i].getStatus().equals("Pending"))
				{
					approve[i].setEnabled(true);
					deny[i].setEnabled(true);
				}
				else if(listOfLogs[i].getStatus().equals("Approved"))
				{
					approve[i].setEnabled(false);
					deny[i].setEnabled(false);
				}
				else 
				{
					approve[i].setEnabled(false);
					deny[i].setEnabled(false);	
				}
			}
		}
	}
	
	class approveListener implements ActionListener
	{
		int buttonNumber;
		
		approveListener(int buttonNumber)
		{
			this.buttonNumber = buttonNumber;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			activeAccount.approveLog(buttonNumber, currentPage, listOfRooms);
			group3.updateList();
		}
		
	}
	
	class denyListener implements ActionListener
	{

		int buttonNumber;
		
		denyListener(int buttonNumber)
		{
			this.buttonNumber = buttonNumber;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			activeAccount.denyLog(buttonNumber, currentPage);
			group3.updateList();
		}
		
	}
	
	// Load updated list into display
	public void loadData(ComputerLabRoom[] listOfRooms)
	{
		currentPage = 1;
		this.listOfRooms = listOfRooms;
		group3.updateList();
	}
	
	
	public AdminFormGui(Admin activeAccount, loginFormGui lfg)
	{
		this.activeAccount = activeAccount;
		loginForm = lfg;

		// We are initializing the default 10 recent logs. default display of logs is page 1
		listOfLogs = activeAccount.displayLogs(1);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.VERTICAL;
		
		activeAccount.showAllPendings();
		
		add((group1 = new header(this)), gbc);
		gbc.gridy++;
		add((group3 = new logList()), gbc);
		gbc.gridy++;
		add((group2 = new selectionList()), gbc);
		
	}
}
