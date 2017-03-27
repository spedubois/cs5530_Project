package cs5530_Project;

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
    	 System.out.println("3. Exit:");
    	 System.out.println("Please enter your choice:");
	}
	
	public static void displayLoggedInMenu()
	{
		 System.out.println("        Welcome to the Uotel System     ");
    	 System.out.println("2. Reserve:");
    	 System.out.println("3. Add new house:");
    	 System.out.println("4. Edit existing house:");
    	 System.out.println("5. Choose your favorite place to stay:");
    	 System.out.println("6. Enter feedback for a house:");
    	 System.out.println("7. Rate usefulness of feedback:");
    	 System.out.println("8. Declare another user as trusted:");
    	 System.out.println("9. Search for a house:");
    	 System.out.println("10. Find most useful feedbacks:");
    	 System.out.println("11. Add a visit:");
    	 System.out.println("12. Check degrees of seperation:");
    	 System.out.println("13. Check statistics:");
    	 System.out.println("15. Exit:");
    	 System.out.println("Please enter your choice:");
	}
	
	public static void main(String[] args) 
	{
		Connector con=null;
		String choice;
        String login = "",name,password,phoneNum,address,userInput;
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
	            				 
	            				 System.out.println("You have succesfully made a reservation! returning you to the main screen.\n"+
	            				 "Here are som suggested housing options for you.\n\n");
	            				 
	            				 System.out.println(test.getRec(con.stmt, login, choice));
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
		            	 if (c<1 | c>15)
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
	            		 
	            		 else if(c == 5) //Add a favorite
		            	 {
		            		 System.out.println("1. Add a favorite:");
		            		 System.out.println("2. Delete a favorite:");
		            		 System.out.println("3. Go back:");
		            		 while ((userInput = in.readLine()) == null && userInput.length() == 0);
		            		 int tempChoice;
		            		 try{
		            			 tempChoice = Integer.parseInt(userInput);
			            	 }catch (Exception e)
			            	 {
			            		 continue;
			            	 }
		            		 
		            		 switch(tempChoice)
		            		 {
		            		 	case 1:
		            		 	{
				            		 System.out.println("Please enter the house you would like to favorite (by the house's id number):");
					            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
					            	 
					            	 Favorites fav = new Favorites();
					            	 
					            	 String result = fav.get(con.stmt,userInput,login,true);
					            	 System.out.println(result);
					            	 break;
		            		 	}
		            		 	case 2:
		            		 	{
				            		 System.out.println("Please enter the house you would like to unfavorite (by the house's id number):");
					            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
					            	 
					            	 Favorites fav = new Favorites();
					            	 
					            	 String result = fav.get(con.stmt,userInput,login,false);
					            	 System.out.println(result);
		            		 	}
		            		 	default:
		            		 	{
		            		 		continue;
		            		 	}
		            		 }
			            	 
		            	 }else if(c == 6) //Add Feedback
		            	 {
		            		 System.out.println("Please enter the house you would like to leave feedback for (by the house's id number):");
			            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
			            	 String hid = userInput;
			            	 
			            	 ResultSet rs = con.stmt.executeQuery("SELECT hid FROM TH WHERE hid = '" + hid + "'");
			            	 ResultSetMetaData rsmd = rs.getMetaData();
			            	 
			            	 if(rs.isBeforeFirst())
			            	 {
			            		 System.out.println("Please enter the feedback score:");
				            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
				            	 String score = userInput;
				            	 System.out.println("Please enter any feedback you would like to leave:");
				            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
				            	 String feedback = "" + userInput;
				            	 
				            	 Feedback feed = new Feedback();
				            	 
				            	 String result = feed.get(con.stmt,hid,score,feedback,login);
				            	 System.out.println(result);
			            	 }
			            	 else
			            	 {
			            		 System.out.println("That house does not exist.");
			            	 }
			            	 
		            	 }else if(c == 7) //Rate Feedback
		            	 {
		            		 System.out.println("Please enter the feedback you would like to rate (by the feedback's id number):");
			            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
			            	 String fid = userInput;
			            	 
			            	 ResultSet rs = con.stmt.executeQuery("SELECT login FROM Feedback WHERE fid = '" + fid + "'");
			            	 ResultSetMetaData rsmd = rs.getMetaData();
			            	 
			            	 if(rs.isBeforeFirst())
			            	 {
			            		 rs.next();
			            		 if(!(rs.getString("login").toLowerCase() == login.toLowerCase()))
			            		 {
				            		 System.out.println("Please enter the rating (0-2):");
					            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
					            	 String rating = userInput;
					            	 
					            	 FeedbackRate rate = new FeedbackRate();
					            	 
					            	 String result = rate.get(con.stmt,fid,rating,login);
					            	 System.out.println(result);
			            		 }else
			            		 {
			            			 System.out.println("You cannot rate your own feedback.");
			            		 }
			            	 }
			            	 else
			            	 {
			            		 System.out.println("That feedback does not exist.");
			            	 }
			            	 
		            	 }else if(c == 8) //Trust or distrust
		            	 {
		            		 System.out.println("Please enter the login of the person you want to trust or not trust:");
			            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
			            	 String login2 = userInput;
			            	 
			            	 
			            	 System.out.println("Please enter 0 to not trust and 1 to trust:");
			            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
			            	 String isTrusted = userInput;
			            	 
			            	 Trust trusted = new Trust();
			            	 
			            	 String result = trusted.get(con.stmt,login,login2,isTrusted);
			            	 System.out.println(result);
			            	 
		            	 }else if(c == 9) //Search for houses
		            	 {
		            		 address = "";
		            		 String cityOrState = "";
		            		 String price = "";
		            		 String priceAndOr = "";
		            		 String addressAndOr = "";
		            		 String nameAndOr = "";
		            		 String houseName = "";
		            		 String category = "";
		            		 int lowPrice = 0;
		            		 int highPrice = 0;
		            		 String nameSearchTerm = "";
		            		 String addressSearchTerm = "";
		            		 String categorySearchTerm = "";
		            		 
		            		 System.out.println("Would you like to search by price? (Enter yes or no):");
			            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
			            	 price = userInput;
			            	 
			            	 if(price.equalsIgnoreCase("yes"))
			            	 {
			            		 System.out.println("Enter the low value for your price range:");
				            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
				            	 
				            	 try{
				            		 lowPrice = Integer.parseInt(userInput);
				            	 }catch (Exception e)
				            	 {
				            		 System.out.println("Invalid price");
				            		 continue;
				            	 }
				            	 
				            	 System.out.println("Enter the high value for your price range:");
				            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
				            	 
				            	 try{
				            		 highPrice = Integer.parseInt(userInput);
				            	 }catch (Exception e)
				            	 {
				            		 System.out.println("Invalid price");
				            		 continue;
				            	 }
			            		 
			            		 System.out.println("Would you like price ANDed or ORed with other options? (Enter and or or):");
				            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
				            	 priceAndOr = userInput;
				            	 
				            	 if(!(priceAndOr.equalsIgnoreCase("and") || priceAndOr.equalsIgnoreCase("or")))
				            	 {
				            		 System.out.println("Invalid option.");
				            		 continue;
				            	 }
			            	 }
			            	 
			            	 System.out.println("Would you like to search by address? (Enter yes or no):");
			            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
			            	 address = userInput;
			            	 
			            	 if(address.equalsIgnoreCase("yes"))
			            	 {
			            		 System.out.println("Would you like to search by city or state? (Enter city or state):");
				            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
				            	 cityOrState = userInput;
				            	 
				            	 if(!(cityOrState.equalsIgnoreCase("city") || cityOrState.equalsIgnoreCase("state")))
				            	 {
				            		 System.out.println("Invalid option.");
				            		 continue;
				            	 }
				            	 
				            	 System.out.println("Enter your address search term:");
				            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
				            	 addressSearchTerm = userInput;
				            	 
				            	 System.out.println("Would you like address ANDed or ORed with other options? (Enter and or or):");
				            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
				            	 addressAndOr = userInput;
				            	 
				            	 if(!(addressAndOr.equalsIgnoreCase("and") || addressAndOr.equalsIgnoreCase("or")))
				            	 {
				            		 System.out.println("Invalid option.");
				            		 continue;
				            	 }
			            	 }
			            	 
			            	 System.out.println("Would you like to search by name? (Enter yes or no):");
			            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
			            	 houseName = userInput;
			            	 
			            	 if(houseName.equalsIgnoreCase("yes"))
			            	 {
			            		 System.out.println("Enter your name search term:");
				            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
				            	 nameSearchTerm = userInput;
			            		 
			            		 System.out.println("Would you like name ANDed or ORed with other options? (Enter and or or):");
				            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
				            	 nameAndOr = userInput;
				            	 
				            	 if(!(nameAndOr.equalsIgnoreCase("and") || nameAndOr.equalsIgnoreCase("or")))
				            	 {
				            		 System.out.println("Invalid option.");
				            		 continue;
				            	 }
			            	 }
			            	 
			            	 System.out.println("Would you like to search by category? (Enter yes or no):");
			            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
			            	 category = userInput;
			            	 
			            	 if(category.equalsIgnoreCase("yes"))
			            	 {
			            		System.out.println("Enter your category search term:");
			            	 	while ((userInput = in.readLine()) == null && userInput.length() == 0);
			            	 	categorySearchTerm = userInput;
			            	 }
			            	 
			            	 String searchInput = "You have entered:";
			            	 
			            	 if(price.equalsIgnoreCase("yes"))
			            	 {
			            		 searchInput += " price: " + lowPrice + " to " + highPrice;
			            		 
			            		 if(address.equalsIgnoreCase("yes") || houseName.equalsIgnoreCase("yes") || category.equalsIgnoreCase("yes"))
				            	 {
				            		 searchInput += " " + priceAndOr;
				            	 }
			            	 }
			            	 
			            	 if(address.equalsIgnoreCase("yes"))
			            	 {
			            		 searchInput += " " + cityOrState + ": " + addressSearchTerm;
			            		 
			            		 if(houseName.equalsIgnoreCase("yes") || category.equalsIgnoreCase("yes"))
				            	 {
				            		 searchInput += " " + addressAndOr;
				            	 }
			            	 }
			            	 
			            	 if(houseName.equalsIgnoreCase("yes"))
			            	 {
			            		 searchInput += " name: " + nameSearchTerm;
			            		 
			            		 if(category.equalsIgnoreCase("yes"))
				            	 {
				            		 searchInput += " " + nameAndOr;
				            	 }
			            	 }
			            	 
			            	 if(category.equalsIgnoreCase("yes"))
			            	 {
			            		 searchInput += " category: " + categorySearchTerm;
			            	 }
			            	 
			            	 if(searchInput.equals("You have entered:"))
			            	 {
			            		 continue;
			            	 }
			            	 
			            	 System.out.println("Would you like to sort by: \n1.Price\n2.Average Feedback\n3.Average Feedback from Trusted Users:");
			            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
			            	 if(!(userInput.equalsIgnoreCase("1") || userInput.equalsIgnoreCase("2") || userInput.equalsIgnoreCase("3")))
			            	 {
			            		 System.out.println("Invalid input");
			            		 continue;
			            	 }
			            	 
			            	 System.out.println(searchInput);
			            	 
			            	 THSearching search = new THSearching();
			            	 String result = search.get(con.stmt,price,priceAndOr,lowPrice,highPrice,
			            			 address,addressAndOr,cityOrState,addressSearchTerm,
			            			 houseName,nameAndOr,nameSearchTerm,
			            			 category,categorySearchTerm,userInput,login);
			            	 
			            	 System.out.println(result);
			            	 
		            	 }else if(c == 10) //Useful feedbacks
		            	 {
		            		 System.out.println("Please enter the house you want the most useful feedback for (by house's id number):");
			            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
			            	 String hid = userInput;
			            	 
			            	 
			            	 System.out.println("Please enter how many feedbacks you would like:");
			            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
			            	 String count = userInput;
			            	 
			            	 Useful useful = new Useful();
			            	 
			            	 String result = useful.get(con.stmt,hid,count);
			            	 System.out.println(result);
			            	 
		            	 }else if(c==11)
		            	 {
		            		 String hid,pid,choice1;
		            		 VisitSQL test = new VisitSQL();
		            		 test.setVisit(con.stmt, login);
		            		 System.out.println("Select the house(hid):");
		            		 while ((hid = in.readLine()) == null && login.length() == 0);
		            		 System.out.println("Select the period(pid):");
		            		 while ((pid = in.readLine()) == null && login.length() == 0);
		            		 System.out.println("Are you sure you would like to reserve\n"+
		            		 "house: "+hid+" pid: "+pid+"?\nSelect 1 for yes, 0 for no");
		            		 while ((choice1 = in.readLine()) == null && login.length() == 0);
		            		 if(choice1.equals("1"))
		            			 test.addVisit(con.stmt, login, hid, pid);
		            		 continue;
		            	 }else if(c==12) //Two degrees of separation
		            	 {
		            		 String login1,login2;
		            		 
		            		 System.out.println("Please enter the first login:");
		            		 while ((userInput = in.readLine()) == null && userInput.length() == 0);
		            		 login1 = userInput;
		            		 System.out.println("Please enter the second login:");
		            		 while ((userInput = in.readLine()) == null && userInput.length() == 0);
		            		 login2 = userInput;
		            		 
		            		 Degrees deg = new Degrees();
		            		 String result = deg.get(con.stmt, login1, login2);
		            		 System.out.println(result);
		            		 
		            	 }else if(c==13) //Statistics
		            	 {
		            		 System.out.println("1. List the most popular houses for each category:");
		            		 System.out.println("2. List the most expensive houses for each category:");
		            		 System.out.println("3. List the most highly rated houses for each category:");
		            		 System.out.println("4. Go back:");
		            		 while ((userInput = in.readLine()) == null && userInput.length() == 0);
		            		 int tempChoice;
		            		 try{
		            			 tempChoice = Integer.parseInt(userInput);
			            	 }catch (Exception e)
			            	 {
			            		 continue;
			            	 }
		            		 
		            		 switch(tempChoice)
		            		 {
		            		 	case 1: // most popular
		            		 	{
				            		 System.out.println("Please enter the number of most popular houses you want to see:");
					            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
					            	 
					            	 Statistics stat = new Statistics();
					            	 
					            	 String result = stat.getMostPopular(con.stmt,userInput);
					            	 System.out.println(result);
					            	 break;
		            		 	}
		            		 	case 2: // most expensive
		            		 	{
				            		 System.out.println("Please enter the number of most expensive houses you want to see:");
					            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
					            	 
					            	 Statistics stat = new Statistics();
					            	 
					            	 String result = stat.getMostExpensive(con.stmt,userInput);
					            	 System.out.println(result);
					            	 break;
		            		 	}
		            		 	case 3: // most highly rated
		            		 	{
				            		 System.out.println("Please enter the number of most highly rated houses you want to see:");
					            	 while ((userInput = in.readLine()) == null && userInput.length() == 0);
					            	 
					            	 Statistics stat = new Statistics();
					            	 
					            	 String result = stat.getHighestRated(con.stmt,userInput);
					            	 System.out.println(result);
					            	 break;
		            		 	}
		            		 	default:
		            		 	{
		            		 		continue;
		            		 	}
		            		 }
		            		 
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
		            	 
		            	 if(c==1) //Create new login
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
		            	 }else if(c==2) // Login to existing account
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
