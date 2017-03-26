package cs5530_Project;

import java.sql.*;

public class Useful {
	public Useful(){
		
	}
	public String get(Statement stmt,String hid, String count) throws Exception
	{
		String query;
		String results = "";
		
		query = ("select * from Feedback f , ( select Rate.fid,AVG(rating) as aR from Rate, Feedback where Rate.fid = Feedback.fid GROUP BY Rate.fid) aF where f.fid = aF.fid and f.hid = '" + hid + "' order by aF.aR DESC");
			
		try{
			ResultSet rs = stmt.executeQuery(query);
			int num = Integer.parseInt(count);
			while(rs.next() && num > 0)
			{
				results += "fid: " + rs.getString("fid") + " Average: " + rs.getString("aR") + " Feedback: " + rs.getString("text") + "\n";
				num--;
			}
	    } catch(NumberFormatException e)
		{
	    	return "Invalid Feedback amount.";
		}
		catch(Exception e) 
		{
			System.err.println("Unable to execute query:"+query+"\n");
    	    System.err.println(e.getMessage());
    		throw(e);
		}
		if(results == "")
		{
			return "No Feedback ratings for that house.";
		}
		return results;
	}

}
