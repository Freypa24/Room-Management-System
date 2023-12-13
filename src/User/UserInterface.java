/*
 * 					Copyright (c) [2023] [Lance Gabriel Trias]
 *
 * 			Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * 			The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * 			THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * 			This software utilizes the Java Swing API for graphical user interface components.
 * Java Swing is part of the Java Foundation Classes (JFC) and is subject to its own
 * licensing terms. Refer to the Oracle Binary Code License Agreement for the Java SE
 * Platform Products and JavaFX and the Oracle Technology Network License Agreement
 * for Oracle Java SE for details regarding the use of Java Swing.
*/


package User;

import javax.swing.JFrame;

import backend.RoomManagementSystem;
import backend.loginFormGui;

public class UserInterface 
{

	public static void main(String[] args) 
	{
		loginFormGui loginForm = new loginFormGui();
		loginForm.setSize(778,290);
		//loginForm.setUndecorated(true);
		loginForm.setVisible(true );
		loginForm.setResizable(false);
		loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginForm.setLocationRelativeTo(null);
		
	}

}
