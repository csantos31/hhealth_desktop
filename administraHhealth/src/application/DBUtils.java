package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;
import java.sql.DriverManager;

public class DBUtils {
	static Connection con = null;

	private static String host = "localhost";
	private static String user = "root";
	private static String password = "bcd127";
	private static String dbName = "hhealth";

	public static Connection getConnection(){
		if(con == null){

			try{
				//Class.forName("com.mysql.jdbc.Driver");
				DriverManager.registerDriver(new com.mysql.jdbc.Driver()); //Essa linha foi a diferença
	            con = DriverManager.getConnection("jdbc:mysql://localhost/hhealth", "root", "bcd127");
				//con = DriverManager.getConnection(String.format("jdbc:mysql//%s/%s?userSSL=false",host, dbName,user,password));
				//System.out.println(String.format("jdbc:mysql//%s/%s?userSSL=false",host, dbName,user,password));
				return con;
			}catch(Exception ex){
				ex.printStackTrace();
			}


		}
		return con;
	}


}
