package phase2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.jcraft.jsch.JSch; // YOU'LL NEED jsch-0.1.54.jar
import com.jcraft.jsch.Session;

public class Connector
{
public Statement stmt;

public Connection conn = null;
public Session session = null;

public Connector() throws Exception
{
String sshHost = "georgia.eng.utah.edu";
String sshUser = "5530u33";
String sshPassword = "o7r9gd20";

int lport = 5656;
String rhost = "localhost";
int rport = 3306;

// ?allowMultiQueryies=true this allows for multiple queries to be sent in one string // YOU MAY NOT WANT THIS FUNCTIONALITY
String url = "jdbc:mysql://localhost:" + lport + "/5530db33?allowMultiQueries=true";
String dbUser = "5530u33";
String dbPassword = "o7r9gd20";
String driverName = "com.mysql.jdbc.Driver";

try
{
// Set StrictHostKeyChecking property to no to avoid UnknownHostKey
// issue
java.util.Properties config = new java.util.Properties();
config.put("StrictHostKeyChecking", "no");
JSch jsch = new JSch();
session = jsch.getSession(sshUser, sshHost, 22);
session.setPassword(sshPassword);
session.setConfig(config);
session.connect();

int assigned_port = session.setPortForwardingL(lport, rhost, rport);


// mysql database connectivity
Class.forName(driverName).newInstance();
conn = DriverManager.getConnection(url, dbUser, dbPassword);
stmt = conn.createStatement();

} catch (SQLException sql)
{
sql.printStackTrace();
throw sql;
}
}

public void closeConnection() throws Exception
{
conn.close();
session.disconnect();
}
}