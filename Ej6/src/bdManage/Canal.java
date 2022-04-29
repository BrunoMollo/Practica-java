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
	
	
	static public Canal getCanal() {
		if(instancia==null) { instancia=new Canal(); }
		return instancia;
	}
	
	private Canal() {
		try { Class.forName(driver); }
		catch (ClassNotFoundException e) { Logger.log(e); }
	}
	
	public Connection getConection(){
		try {
			if(con==null || con.isClosed()) {
				con=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db, user, password);
				conectados=0;
			}
		} catch (SQLException ex) { Logger.log(ex); }
		conectados++;
		return con;
	}
	
	public void releaseConection(){
		conectados--;
		if(conectados==0) { 
			try { con.close(); } 
			catch (SQLException ex) { Logger.log(ex);}
		}
	}

	
	public <T_OUT> T_OUT executeTransaction(FailableFunction<PreparedTransaction,T_OUT> func){
		PreparedTransaction tr=new PreparedTransaction(getConection());
		return processTransaction(tr, func);
	}
	
	public <T_OUT> T_OUT executeInstantTransaction(FailableFunction<InstantTransaction,T_OUT> func){
		InstantTransaction tr=new InstantTransaction(getConection());
			return processTransaction(tr, func);
		}
	
	
	//----------------------
	
	private <T_OUT, TIPO_TRANS extends Transaction> T_OUT processTransaction(TIPO_TRANS tr, FailableFunction<TIPO_TRANS, T_OUT> func) {
		try {
			T_OUT output=func.apply(tr);
			return output;		
		} 
		catch (SQLException ex) { Logger.log(ex); }
		finally {
			tr.close();
			releaseConection();
		}
		return null;
	}
	
}
