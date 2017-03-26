package cs5530_Project;

import java.sql.*;


public class THDetails {
	private String choice;
public THDetails(){}
	
	public String get(Statement stmt, String i) throws Exception
	{
		choice = i;
		String query;
		String resultstr="";
		ResultSet results;
		query="Select t.pid, t.from_date, t.to_date, a.cost_per_night from Available a,"
				+ "(Select p.pid, s.hid, p.from_date, p.to_date from Period p, "
				+ "(select pid, hid from Available where (pid, hid) not in (select pid, hid from Reserve) union all "
				+ "select pid, hid from Reserve where (pid, hid) not in (select pid, hid from Available)) as s "
				+ "where hid = "+i+" and p.pid = s.pid) as t "
				+ "where a.hid = t.hid and a.pid = t.pid;";
		try{
			results = stmt.executeQuery(query);
        } catch(Exception e) {
			System.err.println("Unable to execute query:"+query+"\n");
	                System.err.println(e.getMessage());
			throw(e);
		}
		System.out.println("TH:getHousing query = "+query+"\n");
		while (results.next()){
			resultstr += results.getString("pid") + "      " + results.getString("from_date") + "      " + results.getString("to_date") + 
					"      " + results.getString("cost_per_night") + "\n";	
		}
		
		return "Select:   From:          To:        Cost:\n" + resultstr + "\n Which time would you like? (from Select column)\n";
	}
	public String Confirm(Statement stmt, String i) throws Exception
	{
		String query;
		String resultstr="";
		ResultSet results;
		query = "Select t.* From (Select p.pid, from_date, to_date, cost_per_night From Period p, (Select pid, cost_per_night from Available Where hid = "+choice+") as s "
				+ "WHere p.pid = s.pid) as t where t.pid = " + i;
		try{
			results = stmt.executeQuery(query);
        } catch(Exception e) {
			System.err.println("Unable to execute query:"+query+"\n");
	                System.err.println(e.getMessage());
			throw(e);
		}
		System.out.println("TH:confirmHousing query = "+query+"\n");
		while (results.next()){
			resultstr += results.getString("pid") + "      " + results.getString("from_date") + "      " + results.getString("to_date") + 
					"      " + results.getString("cost_per_night") + "\n";	
		}
		return "Select:   From:          To:        Cost:\n" + resultstr + "\n Are you sure you want to reserve this house? (Select 1 for yes, 2 for no)\n";
	}

}
