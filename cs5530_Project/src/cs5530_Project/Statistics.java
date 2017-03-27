package cs5530_Project;

import java.sql.*;

public class Statistics {
	public Statistics(){
		
	}
	public String getMostPopular(Statement stmt, String input) throws Exception
	{
		String query;
		String result = "";
		
		int count;
		try
		{
			count = Integer.parseInt(input);
		}catch (Exception e)
		{
			return "Invalid number";
   	 	}
		
		query = ("SELECT t.category,v.hid, COUNT(v.hid) AS visits FROM Visit v JOIN (SELECT category,hid FROM TH) t ON t.hid=v.hid GROUP BY t.category,v.hid ORDER BY t.category,visits DESC");
			
		try{
				ResultSet rs = stmt.executeQuery(query);
				String category = "";
				int num = count-1;
				
				rs.next();
				category = rs.getString("category");
				result += "category: " + rs.getString("category") + " hid: " + rs.getString("hid") + " Number of visits: " + rs.getString("visits") + "\n";
				
				while(rs.next())
				{
					if(category.equalsIgnoreCase(rs.getString("category")))
					{
						num--;
					}
					else
					{
						num = count;
					}
					if(num > 0)
					{
						result += "category: " + rs.getString("category") + " hid: " + rs.getString("hid") + " Number of visits: " + rs.getString("visits") + "\n";
					}
					category = rs.getString("category");
				}
	    } catch(Exception e) 
		{
	        System.err.println("Unable to execute query:"+query+"\n");
	    	System.err.println(e.getMessage());
	    	throw(e);
		}
		return result;
	}
	
	public String getMostExpensive(Statement stmt, String input) throws Exception
	{
		String query;
		String result = "";
		
		int count;
		try
		{
			count = Integer.parseInt(input);
		}catch (Exception e)
		{
			return "Invalid number";
   	 	}
		
		query = ("SELECT t.category,v.hid, AVG(cost) AS avgCost FROM Visit v JOIN (SELECT category,hid FROM TH) t ON t.hid=v.hid GROUP BY t.category,v.hid ORDER BY t.category,avgCost DESC");
			
		try{
				ResultSet rs = stmt.executeQuery(query);
				String category;
				int num = count;
				
				rs.next();
				category = rs.getString("category");
				result += "category: " + rs.getString("category") + " hid: " + rs.getString("hid") + " Average cost: " + rs.getString("avgCost") + "\n";
				
				while(rs.next())
				{
					if(category.equalsIgnoreCase(rs.getString("category")))
					{
						num--;
					}
					else
					{
						num = count;
					}
					if(num > 0)
					{
						result += "category: " + rs.getString("category") + " hid: " + rs.getString("hid") + " Average cost: " + rs.getString("avgCost") + "\n";
					}
					category = rs.getString("category");
				}
	    } catch(Exception e) 
		{
	        System.err.println("Unable to execute query:"+query+"\n");
	    	System.err.println(e.getMessage());
	    	throw(e);
		}
		return result;
	}
	
	public String getHighestRated(Statement stmt, String input) throws Exception
	{
		String query;
		String result = "";
		
		int count;
		try
		{
			count = Integer.parseInt(input);
		}catch (Exception e)
		{
			return "Invalid number";
   	 	}
		
		query = ("SELECT t.category,f.hid, AVG(score) AS avgScore FROM Feedback f JOIN (SELECT category,hid FROM TH) t ON t.hid=f.hid GROUP BY t.category,f.hid ORDER BY t.category,avgScore DESC");
			
		try{
				ResultSet rs = stmt.executeQuery(query);
				String category;
				int num = count;
				
				rs.next();
				category = rs.getString("category");
				result += "category: " + rs.getString("category") + " hid: " + rs.getString("hid") + " Average feedback score: " + rs.getString("avgScore") + "\n";
				
				while(rs.next())
				{
					if(category.equalsIgnoreCase(rs.getString("category")))
					{
						num--;
					}
					else
					{
						num = count;
					}
					if(num > 0)
					{
						result += "category: " + rs.getString("category") + " hid: " + rs.getString("hid") + " Average feedback score: " + rs.getString("avgScore" + "") + "\n";
					}
					category = rs.getString("category");
				}
	    } catch(Exception e) 
		{
	        System.err.println("Unable to execute query:"+query+"\n");
	    	System.err.println(e.getMessage());
	    	throw(e);
		}
		return result;
	}

}
