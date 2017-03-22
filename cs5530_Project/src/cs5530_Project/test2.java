package cs5530_Project;

import java.sql.*;
import java.io.*;

public class test2 {

	/**
	 * @param args
	 */
	static boolean loggedIn = false;
	
	public static void displayMenu()
	{
		 System.out.println("        Welcome to the Uotel System     ");
    	 //System.out.println("1. Display all registered logins:");
    	 System.out.println("1. Register new user:");
    	 System.out.println("2. Exsisting user login:");
    	 System.out.println("3. exit:");
    	 System.out.println("Please enter your choice:");
	}
	
	public static void displayLoggedInMenu()
	{
		 System.out.println("        Welcome to the Uotel System     ");
    	 System.out.println("1. Display all registered logins:");
    	 System.out.println("2. exit:");
    	 System.out.println("Please enter your choice:");
	}
	
	public static void main(String[] args) 
	{
		Connector con=null;
		String choice;
        String login,name,password,phoneNum,address;
        String sql=null;
        int c=0;
         try
		 {
			//remember to replace the password
			 	 con= new Connector();
	             System.out.println ("Database connection established");
	         
	             BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	             
	             while(true)
	             {
	            	 if(loggedIn)
	            	 {
	            		 displayLoggedInMenu();
	            		 
	            		 while ((choice = in.readLine()) == null && choice.length() == 0);
		            	 try{
		            		 c = Integer.parseInt(choice);
		            	 }catch (Exception e)
		            	 {
		            		 
		            		 continue;
		            	 }
		            	 if (c<1 | c>3)
		            		 continue;
		            	 
	            		 if (c==1)
		            	 {
		            		 TestSQL test = new TestSQL();
		            		 System.out.println(test.get(con.stmt));
		            	 }else
		            	 {   
		            		 System.out.println("Thank you for using Uotel.");
		            		 con.stmt.close(); 
		        
		            		 break;
		            	 }
	            	 }
	            	 else
	            	 {
		            	 displayMenu();
		            	 while ((choice = in.readLine()) == null && choice.length() == 0);
		            	 try{
		            		 c = Integer.parseInt(choice);
		            	 }catch (Exception e)
		            	 {
		            		 
		            		 continue;
		            	 }
		            	 if (c<1 | c>3)
		            		 continue;
		            	 
		            	 if(c==1)
		            	 {	
		            		System.out.println("Please enter a login:");
		            	 	while ((login = in.readLine()) == null && login.length() == 0);
		            	 	
		            	 	ResultSet rs = con.stmt.executeQuery("SELECT login FROM Users WHERE login = '" + login + "'");
		            		ResultSetMetaData rsmd = rs.getMetaData();

		            		while(rs.isBeforeFirst())
		            		{
		            			System.out.println("That login is taken, please try another one.");
		            			System.out.println("Please enter a login:");
			            	 	while ((login = in.readLine()) == null && login.length() == 0);
			            	 	rs = con.stmt.executeQuery("SELECT login FROM Users WHERE login like '%" + login + "%'");
		            		}
		            		
		            		System.out.println("Please enter your name:");
		            	 	while ((name = in.readLine()) == null && login.length() == 0);
		            	 	System.out.println("Please enter your password:");
		            	 	while ((password = in.readLine()) == null && login.length() == 0);
		            	 	System.out.println("Please enter your address:");
		            	 	while ((address = in.readLine()) == null && login.length() == 0);
		            	 	System.out.println("Please enter your phone number:");
		            	 	while ((phoneNum = in.readLine()) == null && login.length() == 0);
	            		 	
		            		RegistrationSQL reg = new RegistrationSQL();
		         			String result = reg.get(con.stmt,login,name,password,address,phoneNum);
		         			System.out.println(result);
		         			
		         			rs = con.stmt.executeQuery("SELECT name FROM Users WHERE login = '" + login + "'");
		            		rs.next();
		            		System.out.println("Welcome " + rs.getString("name"));
		            		loggedIn = true;
		            	 }else if(c==2)
		            	 {	
		            		System.out.println("Please enter your login:");
		            	 	while ((login = in.readLine()) == null && login.length() == 0);
		            	 	System.out.println("Please enter your password:");
		            	 	while ((password = in.readLine()) == null && login.length() == 0);
		            	 	
		            	 	ResultSet rs = con.stmt.executeQuery("SELECT login,password FROM Users WHERE login = '" + login 
		            	 			+ "' and password = '" + password + "'");
		            		ResultSetMetaData rsmd = rs.getMetaData();

		            		while(!rs.isBeforeFirst())
		            		{
		            			System.out.println("Your login or password was incorrect.");
		            			System.out.println("Please enter your login:");
			            	 	while ((login = in.readLine()) == null && login.length() == 0);
			            	 	System.out.println("Please enter your password:");
			            	 	while ((password = in.readLine()) == null && login.length() == 0);
			            	 	
			            	 	rs = con.stmt.executeQuery("SELECT login,password FROM Users WHERE login = '" + login 
			            	 			+ "' and password = '" + password + "'");
		            		}
		            		
		            		rs = con.stmt.executeQuery("SELECT name FROM Users WHERE login = '" + login + "'");
		            		rs.next();
		            		System.out.println("Welcome " + rs.getString("name"));
		            		loggedIn = true;
		            	 }
		            	 else
		            	 {   
		            		 System.out.println("Thank you for using Uotel.");
		            		 con.stmt.close(); 
		        
		            		 break;
		            	 }
	            	 }
	            	 
	            	 
	             }
		 }
         catch (Exception e)
         {
        	 e.printStackTrace();
        	 System.err.println ("Either connection error or query execution error!");
         }
         finally
         {
        	 if (con != null)
        	 {
        		 try
        		 {
        			 con.closeConnection();
        			 System.out.println ("Database connection terminated");
        		 }
        	 
        		 catch (Exception e) { /* ignore close errors */ }
        	 }	 
         }
	}

}
