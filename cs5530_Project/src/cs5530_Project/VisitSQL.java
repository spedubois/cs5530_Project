package cs5530_Project;

import java.sql.*;

public class VisitSQL {
	public VisitSQL(){}
	
	public void setVisit(Statement stmt, String login) throws SQLException
	{
		String query;
		String resultstr="";
		ResultSet results;
		query = "Select hid, pid from"+
			"(select login, pid, hid from Reserve where (login, pid, hid) not in (select login, pid, hid from Visit) union all "+
			"select login, pid, hid from Visit where (login, pid, hid) not in (select login, pid, hid from Reserve)) as a "+
			"where login = '"+login+"';";
		try{
			results = stmt.executeQuery(query);
        } catch(Exception e) {
			System.err.println("Unable to execute query:"+query+"\n");
	                System.err.println(e.getMessage());
			throw(e);
		}
		System.out.println("TH:Visit query = "+query+"\n");
		while (results.next()){
			resultstr += results.getString("hid") + "         " + results.getString("pid")+"\n";	
		}
		System.out.println("hid:    pid:\n"+resultstr);
	}
	public void addVisit(Statement stmt, String login, String hid, String pid) throws Exception
	{
		String query;
		query = "insert into Visit(login, hid, pid) values('"+login+"', '"+hid+"', '"+pid+"')";
		try{
			stmt.executeUpdate(query);
        } catch(Exception e) {
			System.err.println("Unable to execute query:"+query+"\n");
	                System.err.println(e.getMessage());
			throw(e);
		}
		System.out.println("You have successfully added your visit!");
	}

}
