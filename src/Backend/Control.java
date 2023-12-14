package backend;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

import backend.Control.Admin;
import backend.Control.Professor;

//Parent Class
	class Room
	{
		// Encapsulation
		protected String college = "College of Engineering and Architecture";
		private String[] timeSchedule = {"6:00am - 7:30am", "7:30am - 9:00am", "9:00am - 10:30am"};
		private boolean[] isAvailable = {true, true, true};
		private String roomIdentity;
		private int capacity;
		enum features
		{
			Door, StudentChair, TeacherTable
		}
		
		// Overloading Polymorphism technique & getters setters
		// Set the selected time schedule to available / not available
		public void availability(String selected, boolean set)
		{
			int index = indexOfTime(selected);
			
			// check if a time schedule is found
			if(index != -1)
			{
				isAvailable[index] = set; // Set the found time schedule to available / not available
			}
			else	// None found
			{
				System.out.println("Nothing found");
			}
		}
		
		public void availability(int selected, boolean set)
		{
			
			isAvailable[selected] = set; // Set the found time schedule to available / not available
			
		}
		
		public String[] availability(int total)
		{
			String[] str = new String[total];
			for(int i = 0; i < total; i++)
			{
				if(isAvailable[i] == true)
				{
					str[i] = timeSchedule[i];
				}
			}
			return str;
		}
		
		private int indexOfTime(String timeSched)
		{
			for(int i = 0; i < timeSchedule.length; i++)
			{
				if(timeSchedule[i].equals(timeSched))
				{
					return i;
				}
			}
			// Time not found? return nothing found
			return -1;
		}
		
		public void setCapacity(int num)
		{
			capacity = num;
		}
		
		public int getCapacity()
		{
			return capacity;
		}
		
		public void setRoomIdentity(String name)
		{
			roomIdentity = name;
		}
		
		public String getRoomIdentity()
		{
			return roomIdentity;
		}
		
		
		public void showFeatures()
		{
			for(features demo : features.values())
			{
				System.out.println(demo);
			}
		}
	}
	
//Inheritance
	class standardRoom extends Room
	{
		enum features
		{
			Door,
			ChalkBoard,
			StudentChair,
			TeacherTable
		}		
	}
	
	class ComputerLabRoom extends Room
	{
		enum features
		{
			Door,
			WhiteBoard,
			StudentChair,
			TeacherTable,
			Monitor,
			StudentPcUnits,
			AirCondition
		}
	}
	

	class log
	{
		private String username;
		private String status;
		private String room;
		private String timeSchedule;
		
		log(String username, String room, String timeSched)
		{
			this.status = "Pending";
			this.username = username;
			this.room = room;
			this.timeSchedule = timeSched;
		}

		public String getTimeSchedule() 
		{
			return timeSchedule;
		}

		public void setTimeSchedule(String timeSchedule) 
		{
			this.timeSchedule = timeSchedule;
		}

		public String getRoom() 
		{
			return room;
		}

		public void setRoom(String room) 
		{
			this.room = room;
		}
		
		public String getStatus()
		{
			return status;
		}
		
		public void setStatus(String status)
		{
			this.status = status;
		}

		public String getUsername() 
		{
			return username;
		}

		public void setUsername(String username) 
		{
			this.username = username;
		}
	}
	
	// Abstraction and Encapsulation (getters and setters)
	abstract class User
	{
		protected String username;
		protected char[] password;
		protected final static int LimitOfLogs = 6; // for Professors
		
		// for Admins
		protected final static int LimitOfLogsAdmin = 30;
		protected final static int MaximumPage = 10;
		protected static log[] logList;
		protected static log[] dummyLog = new log[MaximumPage];	// Because each page can only list 10 logs, we have a maximum of 10 logs only
		
		
		public static void initializeList()
		{
			logList = new log[LimitOfLogsAdmin];
			for(int i = 0; i < LimitOfLogsAdmin ; i++)
			{
				logList[i] = new log(" ", " ", " ");
				logList[i].setStatus("None");
			}
			
			for(int i = 0; i < MaximumPage; i++)
			{
				dummyLog[i] = new log("","","");
				dummyLog[i].setStatus("None");
			}
		}
		
		public static log[] getLogList()
		{
			return logList;
		}
		
		public static void updateLogList(log update)
		{
			for(int i = LimitOfLogsAdmin-1; i > 0; i--)
			{
				System.out.println("Show grabbed total " + logList[i].getStatus() + " " + logList[i].getUsername() + " " + logList[i].getRoom());
				logList[i].setStatus(logList[i-1].getStatus());
				logList[i].setRoom(logList[i-1].getRoom());
				logList[i].setUsername(logList[i-1].getUsername());
				logList[i].setTimeSchedule(logList[i-1].getTimeSchedule());
				
			}
			logList[0].setStatus(update.getStatus());
			logList[0].setRoom(update.getRoom());
			logList[0].setUsername(update.getUsername());
			logList[0].setTimeSchedule(update.getTimeSchedule());
		}
		
		public static void ClearList()
		{
			logList = null;
		}
		
		public void setPassword(char[] newPassword) {
            this.password = Arrays.copyOf(newPassword, newPassword.length);
        }

        // Securely get the password
        public char[] getPassword() {
            return Arrays.copyOf(password, password.length);
        }

        // this method clears the password from memory. increasing security of passwords
        public void clearPassword() {
            Arrays.fill(password, ' ');
        }
        
		
		public String getUserName()
		{
			return username;
		}
		public void setUserName(String name)
		{
			username = name;
		}
		
		public static int getTotalLogs()
		{
			return LimitOfLogs;
		}
		
		public static int getTotalLogsAdmin()
		{
			return LimitOfLogsAdmin;
		}
	}
	
	
public class Control 
{
	
	public static class Professor extends User
	{
		private log[] statusLogs = new log[LimitOfLogs];
		
		Professor(String name, char[] password)
		{
			this.username = name;
			
			// grab a copy of the password to secure password
			this.password = Arrays.copyOf(password, password.length);
			
			for(int i = 0; i < LimitOfLogs; i++)
			{
				statusLogs[i] = new log("","","");
				statusLogs[i].setStatus("None");
			}
			
		}
		
		// This is used to grab the user's list of logs
		public String getLogRoom(int index)
		{
			return statusLogs[index].getRoom();
		}
		
		public String getLogUser(int index)
		{
			return statusLogs[index].getUsername();
		}
		
		public String getLogStatus(int index)
		{
			return statusLogs[index].getStatus();
		}
		
		public String getLogTime(int index)
		{
			return statusLogs[index].getTimeSchedule();
		}
		// wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww
		
		// To be used for displaying in GUI, grabs the latest log
		public log getLatestLog()
		{
			return statusLogs[0];
		}
		
		// Generate a new log. Usually used when an log gets updated / added
		public void addLog(String name, String room, String time)
		{
			String nameToRemove = null; // This is a placeholder to store a name of a staff that has exceeded the 6 limit of logs
			// It is to keep the data in the main Loglist[] to store only a maximum of 6 logs for each staff

			for(int i = LimitOfLogs-1; i > 0; i--)
			{
				statusLogs[i].setStatus(statusLogs[i-1].getStatus());
				statusLogs[i].setRoom(statusLogs[i-1].getRoom());
				statusLogs[i].setUsername(statusLogs[i-1].getUsername());
				statusLogs[i].setTimeSchedule(statusLogs[i-1].getTimeSchedule());
				
			}
			
			statusLogs[0].setStatus("Pending");
			statusLogs[0].setRoom(room);
			statusLogs[0].setUsername(name);
			statusLogs[0].setTimeSchedule(time);
			
			updateLogList(statusLogs[0]);
			
			
			// It counts how many time a specific user has made logs
			// This outer loop, loops through the list and initializes the userLogCount to 0
			for(int i = LimitOfLogsAdmin-1; i > 0; i--)
			{
				if(logList[i].getUsername().equals(name))
				{
					int userLogsCount = 0;
					 for (int j = 0; j < LimitOfLogsAdmin; j++) 
					 {
			            if (logList[j].getUsername().equals(name)) 
			            {
			                userLogsCount++;
			            }
			            }

			            if (userLogsCount > 6) 
			            {
			                System.out.println("The account of user: " + name + " has exceeded 6 logs. Removing the last log");
			                nameToRemove = name;
			            }
			            break;
				}
				
			}
			
			// This removes the exceeding log from the specific user
			if(nameToRemove != null)
			{
				for (int i = LimitOfLogsAdmin - 1; i >= 0; i--) {
					//System.out.println("Before nameToRemove: " + statusLogs[i].getStatus() + " " + statusLogs[i].getUsername());
		            if (logList[i].getUsername().equals(nameToRemove)) {
		            	System.out.println(i);
		                logList[i].setStatus("None");
		                logList[i].setRoom(" ");
		                logList[i].setUsername(" ");
		                logList[i].setTimeSchedule(" ");
		                break;
		            }
			}
		}
		}
		
		// This method stores the 6 elements found in the array logList[] with a specific name (ex. Lance)
		// In the statusLogs[] array of the instance. hence, the count variable.
		public void updateLog(String name)
		{
			int count = 0;
			for(int i = LimitOfLogsAdmin-1 ; i >= 0; i--)
			{
				if(logList[i].getUsername().equals(name))
				{
					System.out.println("Show grabbed total " + logList[i].getStatus() + " " + logList[i].getUsername());
					statusLogs[count].setStatus(logList[i].getStatus());
					statusLogs[count].setRoom(logList[i].getRoom());
					statusLogs[count].setUsername(logList[i].getUsername());
					statusLogs[count].setTimeSchedule(logList[i].getTimeSchedule());	
					count++;
				}
				if(count == LimitOfLogs)
				{
					break;
				}
			}
			
		}
	}
	
	public static class Admin extends User
	{
		
		Admin(String name, char[] password)
		{
			this.username = name;
			
			// grab a copy of the password to secure password
			this.password = Arrays.copyOf(password, password.length);
			
		}
		
		public void showAllPendings()
		{
			log[] pendings = new log[logList.length];		// Array to store all found pendings
			for(int i = getTotalLogs()-1; i >= 0; i--)
			{
				// Use Switch case here
				if(logList[i].getStatus().equals("Pending"))
				{
					pendings[i] = logList[i];
					//System.out.println(pendings[i].getStatus());
				}
			}
		}
		
		public void showAllApproved()
		{
			log[] approved = new log[logList.length];		// Array to store all found approved
			for(int i = getTotalLogs()-1; i > 0; i--)
			{
				// Use Switch case here
				if(logList[i].getStatus().equals("Approved"))
				{
					approved[i] = logList[i];
				}
			}
			for(int i = 0; i <approved.length; i++)
			{
				System.out.println(approved[i].getStatus());
			}
		}
		
		public void showAllDenied()
		{
			
		}
		
		// this is the method that edits the log. Mainly, the status attribute
		public void approveLog(int index, int pageNum)
		{
			switch(pageNum)
			{
				case 0:
					logList[index].setStatus("Approved");
					break;
				case 1:
					logList[index+10].setStatus("Approved");
					break;
				case 2:
					logList[index+20].setStatus("Approved");
					break;
				default:
			}
			
		}
		
		public log[] displayLogs(int pageNum)
		{
			switch (pageNum)
			{
			case 0:
				// We are grabbing the most recent logs
				for(int i = 0; i < 10; i++)
				{
					dummyLog[i] = logList[i];
				}
				break;
			case 1:
				// We are grabbing the next set of 10 logs
				for(int i = 10; i < 20; i++)
				{
					dummyLog[i - 10] = logList[i];
				}
				break;
			case 2:
				// We are grabbing the remaining logs
				for(int i = 20; i < getTotalLogs(); i++)
				{
					dummyLog[i - 20] = logList[i];
				}
				break;
			default:
				System.out.println("No such Page Number is found");
				return null;
			}
			return dummyLog;
		}
	}
	
	public static class listOfRooms
	{
		private final static int totalRooms = 6;
		private final static int totalTimeSched = 3;
		
		public static standardRoom[] generateRooms()
		{
			standardRoom[] dummy = new standardRoom[totalRooms];
			
			// Generate Total number of rooms in a building floor.
			for(int i = 0; i < totalRooms; i++)
			{
				dummy[i] = new standardRoom();
				dummy[i].setRoomIdentity("Standard Room " + i);
				
				// Set all possible time schedules to default (Available / True)
				for(int j = 0; j < totalTimeSched; j++)
				{
					dummy[i].availability(j,true);
				}
			}
			return dummy;
			
		}
		
		public static int getTotalRooms()
		{
			return totalRooms;
		}
		
		public static int getTotalTime()
		{
			return totalTimeSched;
		}
		
		public static String[] getAvailableTime(standardRoom stdRoom)
		{
			return stdRoom.availability(totalTimeSched);
		}
	}
}
