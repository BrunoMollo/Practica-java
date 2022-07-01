package Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public abstract class Dao<ENTITY> {
	
	protected PreparedStatement st=null;
	protected ResultSet rs=null;
	protected Connection con=null;
	
	abstract protected ENTITY mapFromResulset() throws SQLException;
	
	
	protected void closeResourses(){
		try {
			if(rs!=null) {rs.close();}
			if(st!=null) {st.close();}
			DbConnector.getInstancia().releaseConn();
		} 
		catch (SQLException e) { e.printStackTrace();} 
		catch (Exception e) { e.printStackTrace(); }
	}
	
	
	//deberia retornar algo???
	public void executeDelete(queryLambda query) {
		
		try {
			con=DbConnector.getInstancia().getConn();
			con.setAutoCommit(false);
			query.apply();
			con.commit();
		}
		catch (SQLException e) { 
			e.printStackTrace(); 
			DbConnector.getInstancia().tryRollback();
			} 
		finally { closeResourses(); }
	}
	
	
	
	public LinkedList<ENTITY> executeFindAll(queryLambda query) {
		LinkedList<ENTITY> outputFound= new LinkedList<>();
		try {
			con=DbConnector.getInstancia().getConn();
			query.apply();
			
			rs= st.executeQuery();
			
			while(rs!=null && rs.next()) {
				outputFound.add(mapFromResulset());
			}	
		}
		catch (SQLException e) { e.printStackTrace(); } 
		finally { closeResourses(); }
		
		return outputFound;
	}
	

	protected ENTITY executeGetOne(queryLambda query) {
		ENTITY outputFound=null;
		try {
			con=DbConnector.getInstancia().getConn();
			
			query.apply();
		
			rs=st.executeQuery();
		
			if(rs!=null && rs.next()) {
				outputFound=mapFromResulset();
			}
		} 
		catch (SQLException e) { e.printStackTrace(); } 
		finally { closeResourses(); }
		return outputFound;//DEBERIA RETORNAR NULL????
	}
	
	
}
