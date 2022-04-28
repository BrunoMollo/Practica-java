package bdManage;

import java.sql.SQLException;
import java.sql.Statement;

public class InstantTransaction extends Transaction{
	private Statement st;
	
	public InstantTransaction(String url, String user, String psw) throws SQLException {
		super(url, user, psw);
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
