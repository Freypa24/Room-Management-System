package User;

import javax.swing.JFrame;
import Backend.loginFormGui;

public class UserInterface {

	public static void main(String[] args) 
	{
		loginFormGui loginForm = new loginFormGui();
		loginForm.setSize(678,290);
		loginForm.setVisible(true);
		loginForm.setResizable(false);
		loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginForm.setLocationRelativeTo(null);;
	}

}
