package cs5530_Project;

import java.sql.*;

public class THSearching {
	public THSearching(){
		
	}
	public String get(Statement stmt,
			String price,String priceAndOr,int lowPrice,int highPrice,
			String address,String addressAndOr,String cityOrState,String addressSearchTerm,
			String houseName,String nameAndOr,String nameSearchTerm,
			String category,String categorySearchTerm,
			String sortedBy, String login) throws Exception
	{
		String query;
		String result = "";
		
		if(houseName.equalsIgnoreCase("yes"))
   	 	{
			query = "SELECT * FROM TH JOIN (SELECT word, hid FROM Keywords k JOIN HasKeywords hk ON k.wid = hk.wid) k ON TH.hid = k.hid WHERE";
   	 	}else
   	 	{
   			query = "SELECT * FROM TH WHERE";
   	 	}
		
		if(sortedBy.equals("3"))
   	 	{
			query = "SELECT * FROM TH JOIN (SELECT f.hid, AVG(score) AS avgScore FROM Feedback f JOIN (SELECT login2 FROM Trust WHERE login1 = '" 
					+ login + "')k ON k.login2=login GROUP BY f.hid) l ON TH.hid=l.hid WHERE";
   	 	}
		
		
		if(price.equalsIgnoreCase("yes"))
   	 	{
			query += " cost > " + (lowPrice-1) + " AND cost < " + (highPrice+1);
   		 
   		 	if(address.equalsIgnoreCase("yes") || houseName.equalsIgnoreCase("yes") || category.equalsIgnoreCase("yes"))
   		 	{
   		 		query += " " + priceAndOr;
   		 	}
   	 	}
   	 
   	 	if(address.equalsIgnoreCase("yes"))
   	 	{
   	 		query += " " + cityOrState + " = '" + addressSearchTerm + "'";
   		 
   	 		if(houseName.equalsIgnoreCase("yes") || category.equalsIgnoreCase("yes"))
   	 		{
   	 			query += " " + addressAndOr;
   	 		}
   	 	}
   	 
   	 	if(houseName.equalsIgnoreCase("yes"))
   	 	{
   	 		query += " word = '" + nameSearchTerm + "'";
   		 
   	 		if(category.equalsIgnoreCase("yes"))
   	 		{
   	 			query += " " + nameAndOr;
   	 		}
   	 	}
   	 
   	 	if(category.equalsIgnoreCase("yes"))
   	 	{
   	 		query += " category = '" + categorySearchTerm + "'";
   	 	}
   	 	
   	 	if(sortedBy.equals("1"))
   	 	{
   	 		query += " ORDER BY cost DESC";
   	 	}else if(sortedBy.equals("2"))
   	 	{
   	 		query += " JOIN (SELECT f.hid, AVG(score) AS avgScore FROM Feedback f GROUP BY f.hid ORDER BY avgScore DESC) k ON k.hid=f.hid ORDER BY avgScore DESC";
   	 	}else if(sortedBy.equals("3"))
   	 	{
   	 		query += " ORDER BY avgScore DESC";
   	 	}
		
		//query = ("SELECT * FROM TH JOIN (SELECT category,hid FROM TH) t ON t.hid=v.hid GROUP BY t.category,v.hid ORDER BY t.category,visits DESC");
			
		try{
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next())
				{
					result += "hid: " + rs.getString("hid") + " Category: " + rs.getString("category") + " Address: " + rs.getString("address") + " City: " 
				+ rs.getString("city") + " State: " + rs.getString("state") + " URL: " + rs.getString("URL") + " Phone #: " + rs.getString("telephone_number") + 
				" Date built: " + rs.getString("built") + " Cost per person: " + rs.getString("cost") + "\n";
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
