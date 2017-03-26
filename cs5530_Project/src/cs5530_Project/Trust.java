package cs5530_Project;

import java.sql.*;

public class Trust {
	public Trust(){
		
	}
	public String get(Statement stmt,String login1, String login2, String trust) throws Exception
	{
		String query;
		int parsedTrust;
		try{
			parsedTrust = Integer.parseInt(trust);
		}catch (Exception e)
   	 	{
			return "Invalid value, must be 0 or 1.";
   	 	}
		if(parsedTrust < 0 || parsedTrust > 1)
		{
			return "Invalid value, must be 0 or 1.";
		}
		
		query = ("INSERT INTO Trust (login1, login2, isTrusted) VALUES ('" + login1 + "', '" + login2 + "', '" + parsedTrust + "')");
			
		try{
			int results = stmt.executeUpdate(query);
	    } catch(Exception e) {
	    	if (e.getMessage().contains("Duplicate entry"))
	        {
	    		if(parsedTrust == 0)
	    		{
	    			parsedTrust = 1;
	    		}else
	    		{
	    			parsedTrust = 0;
	    		}
	    		query = ("INSERT INTO Trust (login1, login2, isTrusted) VALUES ('" + login1 + "', '" + login2 + "', '" + parsedTrust + "')");
	    		return "Trust changed.";
	        }
	        else
	        {
	    		System.err.println("Unable to execute query:"+query+"\n");
	    	    System.err.println(e.getMessage());
	    		throw(e);
	        }
		}
		return "Trust/Not Trust Added.";
	}

}
