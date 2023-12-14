/*
 * 					Copyright (c) [2023] [Lance Gabriel T. Trias]
 * 
 * 
 *							SPDX-License-Identifier: MIT
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */


package User;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import backend.loginFormGui;

public class UserInterface 
{

	public static void main(String[] args) 
	{
		JFrame frame = new JFrame();
		frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 300);
        ImageIcon imageIcon = new ImageIcon("C:/Users/LanceyFreypa/Downloads/freypt-logo-zip-file/png/logo2.png");
        JLabel imageLabel = new JLabel(imageIcon);
        frame.getContentPane().add(imageLabel);
        frame.setUndecorated(true);
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		
		executorService.schedule(() -> 
		{
			frame.setVisible(false);
			loginFormGui loginForm = new loginFormGui();
			loginForm.setSize(778,290);
			loginForm.setVisible(true );
			loginForm.setResizable(false);
			loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			loginForm.setLocationRelativeTo(null);
			executorService.shutdown();
		}, 2, TimeUnit.SECONDS);
        
	}

}
