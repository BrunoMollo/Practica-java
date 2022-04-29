package bdManage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InstantTransaction extends Transaction{
	private Statement st;
	
	public InstantTransaction(Connection _con) {
		super(_con);
	}

	@Override
	public void close() throws SQLException{
		if(st!=null) {st.close();}
		super.close();
	}
	
	public void executeQuery(String querry) throws SQLException{
		st=this.getCon().createStatement();
		this.setResultSet(st.executeQuery(querry));
	}
	
}
