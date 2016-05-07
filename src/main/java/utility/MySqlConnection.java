package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection
{
    private static String dbUrl = "jdbc:mysql://localhost:3306/cmd_example";
    private static String dbUsername = "root";
    private static String dbPassword = "Abcabc";

    public static Connection getConnection() throws SQLException
    {
    	
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }

    public static void setDbUrl(String dbUrl) {
		MySqlConnection.dbUrl = dbUrl;
	}
    
    
}