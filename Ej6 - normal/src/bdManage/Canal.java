package bdManage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Canal {
	
	private String driver="com.mysql.cj.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user="java";
	private String password="himitsu";
	private String db="javaMarket";
	
	private int conectados=0;
	private Connection con;
	private static Canal instancia;
	
	
	static public Canal getCanal() throws ClassNotFoundException {
		if(instancia==null) { instancia=new Canal(); }
		return instancia;
	}
	
	private Canal() throws ClassNotFoundException { Class.forName(driver); }
	
	public Connection getConection() throws SQLException {
			if(con==null || con.isClosed()) {
				con=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db, user, password);
				conectados=0;
			}
		conectados++;
		return con;
	}
	
	public void releaseConection() throws SQLException{
		conectados--;
		if(conectados==0) { con.close(); }
	}
	
}
