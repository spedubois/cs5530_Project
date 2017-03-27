package cs5530_Project;

import java.sql.*;

public class Degrees {
	public Degrees(){
		
	}
	public String get(Statement stmt,String login1, String login2) throws Exception
	{
		String query1, query2;
	
		if(login1.toLowerCase().equals(login2.toLowerCase()))
		{
			return "Those are the same people.";
		}
		
		query1 = ("SELECT * FROM Favorites f1 JOIN Favorites f2 ON f1.hid = f2.hid WHERE f1.login <> f2.login AND f1.login = '" + 
		login1 + "' AND f2.login = '" + login2 + "'");
		
		query2 = ("SELECT * FROM Favorites f1 JOIN (SELECT * FROM Favorites f WHERE f.login IN "+ 
		"( SELECT f2.login FROM Favorites f1 JOIN Favorites f2 ON f1.hid = f2.hid WHERE f1.login <> f2.login AND f1.login = '" +		
		login1  + "' )) f2 ON f1.hid = f2.hid WHERE f1.login <> f2.login AND f1.login = '" + login2 + "'");
			
		try{
				ResultSet rs = stmt.executeQuery(query1);
				if(!rs.isBeforeFirst())
        		{
					rs = stmt.executeQuery(query2);
					if(!rs.isBeforeFirst())
	        		{
						return "Zero degrees of seperation.";
					}else
					{
						return "Two degrees of seperation.";
					}
        		}else
        		{
        			return "One degree of seperation.";
        		}
	    } catch(Exception e) 
		{
	        System.err.println("Unable to execute query:"+query2+"\n");
	    	System.err.println(e.getMessage());
	    	throw(e);
		}
		//return "New Favorite Added.";
		
	}

}
