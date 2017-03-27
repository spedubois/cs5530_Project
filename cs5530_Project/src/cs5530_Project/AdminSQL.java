package cs5530_Project;

import java.sql.*;

public class AdminSQL {
	public AdminSQL(){}
	
	public String getTrusted(Statement stmt, String m) throws Exception
	{
		String query;
		String resultstr="";
		ResultSet results;
		query = "Select distinct a.login2, case when a.login2 = b.login2 then a.num1 = a.num1-b.num0 else a.num1 end as num1 from "
				+ "(select login2, count(isTrusted) as num1 from Trust where isTrusted = 1 group by login2) as a, "
				+ "(select login2, count(isTrusted) as num0 from Trust where isTrusted = 0 group by login2) as b "
				+ "order by(num1) desc limit "+m;
		try{
			results = stmt.executeQuery(query);
        } catch(Exception e) {
			System.err.println("Unable to execute query:"+query+"\n");
	                System.err.println(e.getMessage());
			throw(e);
		}
		System.out.println("TH:Visit query = "+query+"\n");
		while (results.next()){
			resultstr += results.getString("login2") + "      " + results.getString("num1")+"\n";	
		}
		return "User:      Score:\n"+resultstr+"\n";
	}
	
	public String getUseful(Statement stmt, String m) throws Exception
	{
		String query;
		String resultstr="";
		ResultSet results;
		query = "select f.login, a.avg from Feedback f, "
				+ "(select fid, round(avg(rating),1) as avg from Rate group by fid) as a "
				+ "where f.fid = a.fid order by (avg) desc limit "+m;
		try{
			results = stmt.executeQuery(query);
        } catch(Exception e) {
			System.err.println("Unable to execute query:"+query+"\n");
	                System.err.println(e.getMessage());
			throw(e);
		}
		System.out.println("TH:Visit query = "+query+"\n");
		while (results.next()){
			resultstr += results.getString("login") + "      " + results.getString("avg")+"\n";	
		}
		return "User:      Score:\n"+resultstr+"\n";
	}

}
