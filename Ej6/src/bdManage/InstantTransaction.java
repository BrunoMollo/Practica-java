package bdManage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InstantTransaction extends Transaction{
	private Statement st;
	
	public InstantTransaction(Connection _con) { super(_con); }

	@Override
	public void close(){
		try {
			if(st!=null) {st.close();}
			if(this.getResultSet()!=null) {this.getResultSet().close();}
		} 
		catch (SQLException e) { Logger.log(e); }
	}
	
	public void executeQuery(String querry) throws SQLException{
		st=this.getCon().createStatement();
		this.setResultSet(st.executeQuery(querry));
	}
	
}
