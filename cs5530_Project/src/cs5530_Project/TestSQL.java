package cs5530_Project;

import java.sql.*;

public class TestSQL {
	public TestSQL(){
		
	}
	public String get(Statement stmt) throws Exception
	{
		String query;
		String resultstr="";
		ResultSet results; 
		query="Select login from Users";
		try{
			results = stmt.executeQuery(query);
        } catch(Exception e) {
			System.err.println("Unable to execute query:"+query+"\n");
	                System.err.println(e.getMessage());
			throw(e);
		}
		System.out.println("Order:getOrders query="+query+"\n");
		while (results.next()){
			resultstr += "<b>"+results.getString("login");	
		}
		return resultstr;
	}

}
