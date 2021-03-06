package cs5530_Project;

import java.sql.*;

public class RegistrationSQL {
	public RegistrationSQL(){
		
	}
	public String get(Statement stmt,String login, String name, String password, String address, String phoneNum) throws Exception
	{
		String query;
		query="insert into Users (login, name, password, address, phone_number) Values ('" + 
		login + "', '" + name + "', '" + password + "', '" + address + "', '" + phoneNum + "');";
		try{
			int results = stmt.executeUpdate(query);
        } catch(Exception e) {
        	if(e.getMessage().contains("Duplicate entry"))
        	{
            	return "User already exists.";
        	}else
        	{
    			System.err.println("Unable to execute query:"+query+"\n");
    	        System.err.println(e.getMessage());
    			throw(e);
        	}
		}
		return "New User Registered.";
	}

}
