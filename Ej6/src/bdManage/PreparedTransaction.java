package bdManage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedTransaction extends Transaction{
	private PreparedStatement pst;
	
	public PreparedTransaction(String url, String user, String psw) throws SQLException {
		super(url, user, psw);
	}
	
	public void close() throws SQLException{
		if(pst!=null) {pst.close();}
		super.close();
	}
	
	
		public void prepareStatement(String querry)throws SQLException {
			pst=this.getCon().prepareStatement(querry);
		}
		
		
		public void executePreparedQuery()throws SQLException {
			this.setResultSet(pst.executeQuery());
		}
		
		public PreparedStatement getStatement(){
			return pst;
		}

	
	
}
