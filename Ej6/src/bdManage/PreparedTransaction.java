package bdManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedTransaction extends Transaction{
	private PreparedStatement pst;
	private int generatedKeyOption;
	
	public PreparedTransaction(Connection _con) {
		super(_con);
	}
	
	@Override
	public void close() throws SQLException{
		if(pst!=null) {pst.close();}
		super.close();
	}
	

	public void prepareStatement(String querry)throws SQLException {
		pst=this.getCon().prepareStatement(querry, PreparedStatement.NO_GENERATED_KEYS);
	}
	
	public void prepareStatement(String querry, int _generatedKeyOption)throws SQLException {
		pst=this.getCon().prepareStatement(querry, _generatedKeyOption);
		this.generatedKeyOption=_generatedKeyOption;
	}
	
	
	public void executePreparedQuery()throws SQLException {
		this.setResultSet(pst.executeQuery());
	}
	
	public int executeUpdate() throws SQLException{
		int modifiedRows=pst.executeUpdate();
		if(generatedKeyOption==PreparedStatement.RETURN_GENERATED_KEYS) {
			this.setResultSet(pst.getGeneratedKeys());
		}
		return modifiedRows;
	}
	
	
	public PreparedStatement getStatement(){
		return pst;
	}

}
