package cs5530_Project;

import java.sql.*;

public class Feedback {
	public Feedback(){
		
	}
	public String get(Statement stmt,String hid, String score, String text,String login) throws Exception
	{
		String query;
		int parsedScore;
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		try{
			parsedScore = Integer.parseInt(score);
		}catch (Exception e)
   	 	{
			return "Invalid score";
   	 	}
		if(parsedScore < 0 || parsedScore > 10)
		{
			return "Invalid score";
		}
		
		query = ("INSERT INTO Feedback (hid, login, fbdate, text, score, fid) VALUES ('" + hid + "', '" + login + "', '" + sqlDate + "', '" + text + "', '" + score + "', '" + 0 + "')");
			
		try{
			int results = stmt.executeUpdate(query);
	    } catch(Exception e) {
	    	if(e.getMessage().contains("Incorrect integer"))
	        {
	            return "Invalid score";
	        }
	        else
	        {
	    		System.err.println("Unable to execute query:"+query+"\n");
	    	    System.err.println(e.getMessage());
	    		throw(e);
	        }
		}
		return "Feedback Added.";
	}

}
