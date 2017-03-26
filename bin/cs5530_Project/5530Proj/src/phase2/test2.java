package phase2;

import java.sql.*;
import java.io.*;

public class test2 {

	/**
	 * @param args
	 */
	static boolean loggedIn = false;
	static boolean reserve = false;
	
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
    	 System.out.println("2. Reserve:");
    	 System.out.println("3. Add new house:");
    	 System.out.println("4. Edit existing house:");
    	 System.out.println("5. exit:");
    	 System.out.println("Please enter your choice:");
	}
	
	public static void displayReservedMenu()
	{
		 System.out.println("        Welcome to the Uotel System     ");
    	 System.out.println("Select House:");
    	 System.out.println("2. Go Back:");
    	 System.out.println("Please enter your choice:");
	}
	
	public static void main(String[] args) 
	{
		Connector con=null;
		String choice;
        String login,name,password,phoneNum,address;
        login="";
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
	            		 if(reserve)
	            		 {
	            			 choice = in.readLine();
	            			 
	            			 THDetails test = new THDetails();
	            			 System.out.println(test.get(con.stmt, choice));
	            			 String choice1 = in.readLine();
	            			 if(choice1.equals("0")){
	            				 reserve = false;
	            				 continue;
	            			 }
	            			 System.out.println(test.Confirm(con.stmt, choice1));
	            			 String choice3 = in.readLine();
	            			 if(choice3.equals("1"))
	            			 {
	            				 reserve = false;
	            				 String query = "Insert into Reserve(login, hid, pid)"
	            						 +" values('"+login+"', "+choice+", "+choice1+");";
	            				 con.stmt.executeUpdate(query);
	            				 
	            				 System.out.println("You have succesfully made a reservation! returning you to the main screen.\n");
	            			 }
	            		 }
	            		 displayLoggedInMenu();
	            		 
	            		 while ((choice = in.readLine()) == null && choice.length() == 0);
		            	 try{
		            		 c = Integer.parseInt(choice);
		            	 }catch (Exception e)
		            	 {
		            		 
		            		 continue;
		            	 }
		            	 if (c<1 | c>6)
		            		 continue;
		            	 
	            		 if (c==1)
		            	 {
		            		 TestSQL test = new TestSQL();
		            		 System.out.println(test.get(con.stmt));
		            	 }else if(c == 2)
		            	 {
		            		 GetTH test = new GetTH();
		            		 System.out.println(test.get(con.stmt));
		            		 reserve = true;
		            		 continue;
		            	 }
		            	 else if(c == 3)
		            	 {
		            		 String category, address1, url, tele, built,next, city, state;
		            		 AddTHSQL test = new AddTHSQL();
		            		 System.out.println("Please enter the category:");
			            	 while ((category = in.readLine()) == null && login.length() == 0);
			            	 System.out.println("Please enter the address:");
			            	 while ((address1 = in.readLine()) == null && login.length() == 0);
			            	 System.out.println("Please enter the url:");
			            	 while ((url = in.readLine()) == null && login.length() == 0);
			            	 System.out.println("Please enter the phone number:");
			            	 while ((tele = in.readLine()) == null && login.length() == 0);
			            	 System.out.println("Please enter the year built:");
			            	 while ((built = in.readLine()) == null && login.length() == 0);
			            	 System.out.println("Please enter the city:");
			            	 while ((city = in.readLine()) == null && login.length() == 0);
			            	 System.out.println("Please enter the state:");
			            	 while ((state = in.readLine()) == null && login.length() == 0);
			            	 System.out.println("Are you sure you would like to add this house?\n"
			         				+ category + ", "+address1 + ", "+url + ", "+tele + ", "+built+
			         				" "+city+" "+state+"'\n"
			         				+ "Select 1 for yest, 0 for no.");
			            	 while ((next = in.readLine()) == null && login.length() == 0);
			            	 if(next.equals("1"))
			            	 {
			            		 test.addNew(con.stmt, category, address1, url, tele, built, city, state);
				            	 test.addList(con.stmt, login);
				            	 
			            	 }
			            	 
			            	 continue;
		            	 }
		            	 else if(c==4)
		            	 {
		            		 String category, address1, url, tele, built, hid, city, state, next;
		            		 AddTHSQL test = new AddTHSQL();
		            		 System.out.println(test.getList(con.stmt, login));
		            		 while ((hid = in.readLine()) == null && login.length() == 0);
		            		 System.out.println("Please enter the new category:");
			            	 while ((category = in.readLine()) == null && login.length() == 0);
			            	 System.out.println("Please enter the new address:");
			            	 while ((address1 = in.readLine()) == null && login.length() == 0);
			            	 System.out.println("Please enter the new url:");
			            	 while ((url = in.readLine()) == null && login.length() == 0);
			            	 System.out.println("Please enter the new phone number:");
			            	 while ((tele = in.readLine()) == null && login.length() == 0);
			            	 System.out.println("Please enter the new year built:");
			            	 while ((built = in.readLine()) == null && login.length() == 0);
			            	 System.out.println("Please enter the new city:");
			            	 while ((city = in.readLine()) == null && login.length() == 0);
			            	 System.out.println("Please enter the state:");
			            	 while ((state = in.readLine()) == null && login.length() == 0);
			            	 System.out.println("Are you sure you would like to make these changes to the house?\n"
			         				+ category + ", "+address1 + ", "+url + ", "+tele + ", "+built+
			         				" "+city+" "+state+"'\n"
			         				+ "Select 1 for yest, 0 for no.");
			            	 while ((next = in.readLine()) == null && login.length() == 0);
			            	 if(next.equals("1"))
			            	 {
			            		 test.update(con.stmt, category, address1, url, tele, built, hid, city, state);
			            	 }
			            	 continue;
		            	 }
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
