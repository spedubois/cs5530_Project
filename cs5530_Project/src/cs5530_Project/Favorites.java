package cs5530_Project;

import java.sql.*;

public class Favorites {
	public Favorites(){
		
	}
	public String get(Statement stmt,String hid, String login, boolean favoriting) throws Exception
	{
		String query;
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
	
		if(favoriting)
		{
			query = ("INSERT INTO Favorites (hid, login, fvdate) VALUES ('" + hid + "', '" + login + "', '" + sqlDate + "')");
			
			try{
				int results = stmt.executeUpdate(query);
	        } catch(Exception e) {
	        	if(e.getMessage().contains("Duplicate entry"))
	        	{
	            	return "House already favorited.";
	        	}else if(e.getMessage().contains("foreign key"))
	        	{
	        		return "House does not exist.";
	        	}
	        	else
	        	{
	    			System.err.println("Unable to execute query:"+query+"\n");
	    	        System.err.println(e.getMessage());
	    			throw(e);
	        	}
			}
			return "New Favorite Added.";
		}else
		{
			query = ("DELETE FROM Favorites WHERE login = '" + login + "' AND hid = '" + hid + "'" );
			
			try{
				int results = stmt.executeUpdate(query);
	        } catch(Exception e) {
	        	System.err.println("Unable to execute query:"+query+"\n");
	    	    System.err.println(e.getMessage());
	    		throw(e);
			}
			return "Favorite Deleted.";
		}
	}

}
