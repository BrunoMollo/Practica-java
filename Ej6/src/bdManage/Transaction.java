package bdManage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class Transaction {
	private Connection con;
	private ResultSet rs;
	
	public Transaction(String url,String user,String psw)throws SQLException{
		con=DriverManager.getConnection(url,user,psw);
	}
	
	public void close() throws SQLException{
		if(rs!=null) {rs.close();}
		con.close();
	}
	
	
	public ResultSet getResultSet() {
		return rs;
	}
	protected Connection getCon() {
		return con;
	}
	
	protected void setResultSet(ResultSet newRs) {
		this.rs=newRs;
	}
	
	
	
}
