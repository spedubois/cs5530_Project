package cs5530_Project;

import java.sql.*;


public class AddTHSQL {
	private String hid;
	public AddTHSQL(){}
	
	public void addNew(Statement stmt, String category, String address, String url, String tele, String built, String city, String state) throws Exception
	{
		String query;
		query = "insert into TH (category, address, URL, telephone_number, built, city, state)"
				+ "values ('"+category+"', '"+address+"', '"+url+"', '"+tele+"', '"+built+"', '"+city+"' ,'"+state+"')";
		try{
			stmt.executeUpdate(query);
        } catch(Exception e) {
			System.err.println("Unable to execute query:"+query+"\n");
	                System.err.println(e.getMessage());
			throw(e);
		}
	}
	
	public void addList(Statement stmt, String login) throws Exception
	{
		String query;
		ResultSet results;
		query = "select max(hid) max from TH";
		try{
			results = stmt.executeQuery(query);
        } catch(Exception e) {
			System.err.println("Unable to execute query:"+query+"\n");
	                System.err.println(e.getMessage());
			throw(e);
		}
		System.out.println("TH:getHousing query = "+query+"\n");
		while (results.next()){
			hid = results.getString("max");
		}
		
		query = "insert into Lists(login, hid) values('"+login+"', "+hid+")";
		try{
			stmt.executeUpdate(query);
        } catch(Exception e) {
			System.err.println("Unable to execute query:"+query+"\n");
	                System.err.println(e.getMessage());
			throw(e);
		}
		System.out.println("Sucessfully added your new house!\n");	
	}

	public String getList(Statement stmt, String login) throws Exception
	{
		String query;
		String resultstr="";
		ResultSet results;
		query = "Select * from TH t, (select hid from Lists where login = '"+login+"') as a "
				+"Where t.hid = a.hid";
		try{
			results = stmt.executeQuery(query);
        } catch(Exception e) {
			System.err.println("Unable to execute query:"+query+"\n");
	                System.err.println(e.getMessage());
			throw(e);
		}
		System.out.println("TH:getHousing query = "+query+"\n");
		while (results.next()){
			resultstr += results.getString("hid") + "      " + results.getString("category") + "      " 
					+ results.getString("address") + "\n";	
		}
		
		return "Select:   category:         address:\n" 
		+ resultstr + "\n Which house would you like to edit? (from Select column)\n";
	}

	public void update(Statement stmt, String category, String address, String url, String tele, String built, String hid, String city, String state) throws Exception
	{
		String query;
		query = "update TH set category='"+category+"', address='"+address+"', URL='"
				+url+"' , telephone_number='"+tele+"' , built='"+built+"', city='"+city+"', state='"+state+"' where hid ="+hid;
		try{
			stmt.executeUpdate(query);
        } catch(Exception e) {
			System.err.println("Unable to execute query:"+query+"\n");
	                System.err.println(e.getMessage());
			throw(e);
		}
		System.out.println("You have sucessfully upadte your house!\n");
	}
}

