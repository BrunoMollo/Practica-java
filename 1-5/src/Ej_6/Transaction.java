package Ej_6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Transaction {
	Connection con;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	
	public Transaction(String url,String user,String psw)throws SQLException{
		con=DriverManager.getConnection(url,user,psw);
	}
	
	public void close() throws SQLException{
		if(st!=null) {st.close();}
		if(pst!=null) {pst.close();}
		if(rs!=null) {rs.close();}
		con.close();
	}
	
	public void prepareStatement(String querry)throws SQLException {
		pst=con.prepareStatement(querry);
	}
	
	public void executeQuery(String querry) throws SQLException{
			st=con.createStatement();
			rs=st.executeQuery(querry);
	}
	
	public void executePreparedQuery()throws SQLException {
		rs=pst.executeQuery();
	}
	
	public ResultSet getResultSet() {
		return rs;
	}
	
}
