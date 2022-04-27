package Ej_6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.function.Function;

import com.mysql.cj.protocol.Resultset;


@FunctionalInterface  
interface ProsesamientoConeccion<T_OUT>{  
	T_OUT invoke(Transaction t)throws SQLException;  
}  


public class Canal {
	String url;
	String user;
	String psw;
	
	public Canal(String url, String user, String psw) {
		this.url = url;
		this.user = user;
		this.psw = psw;
	}

	

	public <T_OUT> T_OUT makeTransaction(ProsesamientoConeccion<T_OUT> func){
		Connection con = null;// no se pq esto es necesario¿?
		
		try {
			Transaction tr=new Transaction(url, user, psw);
	
			T_OUT output=func.invoke(tr);
			
			tr.close();
			
			return output;
			
				
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		return null;
	}
}
