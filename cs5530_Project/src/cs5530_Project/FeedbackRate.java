package cs5530_Project;

import java.sql.*;

public class FeedbackRate {
	public FeedbackRate(){
		
	}
	public String get(Statement stmt,String fid, String rating, String login) throws Exception
	{
		String query;
		int parsedScore;
		try{
			parsedScore = Integer.parseInt(rating);
		}catch (Exception e)
   	 	{
			return "Invalid rating";
   	 	}
		if(parsedScore < 0 || parsedScore > 2)
		{
			return "Invalid rating";
		}
		
		query = ("INSERT INTO Rate (fid, login, rating) VALUES ('" + fid + "', '" + login + "', '" + parsedScore + "')");
			
		try{
			int results = stmt.executeUpdate(query);
	    } catch(Exception e) {
	    	if(e.getMessage().contains("Incorrect integer"))
	        {
	            return "Invalid rating";
	        }else if (e.getMessage().contains("Duplicate entry"))
	        {
	        	return "You have already rated this feedback.";
	        }
	        else
	        {
	    		System.err.println("Unable to execute query:"+query+"\n");
	    	    System.err.println(e.getMessage());
	    		throw(e);
	        }
		}
		return "Rating Added.";
	}

}
