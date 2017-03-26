package phase2;

import java.sql.*;

public class GetTH {
	public GetTH(){}
	
	public String get(Statement stmt) throws Exception
	{
		String query;
		String resultstr="";
		ResultSet results;
		query="Select * from TH";
		try{
			results = stmt.executeQuery(query);
        } catch(Exception e) {
			System.err.println("Unable to execute query:"+query+"\n");
	                System.err.println(e.getMessage());
			throw(e);
		}
		System.out.println("TH:getHousing query = "+query+"\n");
		while (results.next()){
			resultstr += results.getString("hid") + "           " + results.getString("category") + "      " + results.getString("address") + 
					"      " + results.getString("city") + "      " + results.getString("state") + "      " + results.getString("URL") + "      " + results.getString("telephone_number") + 
					"      " + results.getString("built") + "\n\n";	
		}
		
		return "Select:    Category:     Address:       city:        state:      URL:     Telephone #:     Year Built\n" 
			+ resultstr + "\n Which house would you like? (from Select column)\n";
	}
}
	


