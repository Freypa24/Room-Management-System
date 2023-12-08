package Backend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Queue;
import java.util.LinkedList;

// Update Panel Group Layout
class groupOne extends JPanel
{
	private final int Limit = 8;
	private JPanel[] updatePanel = new JPanel[Limit];
	private JLabel[] updateLabel = new JLabel[Limit];
	private Queue<JPanel> updateQueue = new LinkedList<>();
	
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
			updateLabel[i].setText("Good Morning Mr.Sunshine"); // 24 char
			updateLabel[i].setFont(new Font("Century", Font.PLAIN, 10));
			updatePanel[i] = new JPanel();
			updatePanel[i].setPreferredSize(new Dimension(150, 22));
			updatePanel[i].add(updateLabel[i]);
			add(updatePanel[i], gbc);
			gbc.gridy++;
		}
		
	}
	
	// public String getMessageLabel() {
	//   return messageLabel.getText();
	// }
	//public void setMessageLabel(String name) {
	//   messageLabel.setText(name);
	// }
	
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
		messageLabel.setText("Hello World");
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
	private final JTextField userField, passField;
	
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
		add((passField = new JTextField(25)), gbc);

	}
	public String getUserText()
	{
          return userField.getText();
	}
    public void setUserText(String name)
    {
          userField.setText(name);
    }
    public String getPassText()
	{
          return passField.getText();
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
public class loginFormGui extends JFrame implements ActionListener 
{
		private groupOne updatePart;
		private groupTwo messagePart;
		private groupThree inputPart;
		private groupFour buttonPart;
		// Constructor of class, These work once an instance of the class is instantiated.
		public loginFormGui() 
		{
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
			String passName = inputPart.getPassText();
			
			if(userName.equals("Hello"))
			{
				messagePart.setMessageLabel("You have entered");
				System.out.print("hi");
			}
			else {
				messagePart.setMessageLabel("Unrecognized Account");
				inputPart.setUserText("");
				inputPart.setPassText("");
			}
			
		}
}
