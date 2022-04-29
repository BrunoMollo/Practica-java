package bdManage;

import java.sql.Connection;
import java.sql.ResultSet;


public abstract class Transaction {
	private Connection con;
	private ResultSet rs;
	
	public Transaction(Connection _con){ con=_con; }
	
	public abstract void close();
		
	public ResultSet getResultSet() { return rs; }
	
	protected Connection getCon() { return con; }
	
	protected void setResultSet(ResultSet newRs) { this.rs=newRs; }
	
}
