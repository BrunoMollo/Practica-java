package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import bdManage.Canal;
import logic.Product;


public abstract class Dao<ENTITY extends identifiable<ID_TYPE>, ID_TYPE> {

	private Canal canal;
	private Statement st;
	private PreparedStatement prst;
	private ResultSet rs;
	

	//IMPEMENTAR:
	public String tableName() {return "---";}
	private ENTITY mapfromResultSet() { return null; }
	//
	
	public Dao() throws ClassNotFoundException{
		this.canal=Canal.getCanal();
	}
	
	private void closeResourses() throws SQLException {
		canal.releaseConection();
		if(prst!=null) { prst.close(); }
		if(st!=null) { st.close(); }
		if(rs!=null) { rs.close(); }
	}
	
	
	
	public LinkedList<ENTITY> getAll() throws SQLException{
		LinkedList<ENTITY> arr=new LinkedList<ENTITY>();
		try {
			Connection con =canal.getConection();
			st=con.createStatement();
			rs=st.executeQuery("Select * from product");
			
			while(rs.next()) {
				arr.add(mapfromResultSet());
			}
			return arr;
		} 
		catch (SQLException ex) { throw ex; }
		finally { closeResourses(); }
		
	}
	
	
	public ENTITY getOne(ENTITY p) throws SQLException {
		try {
			Connection con=canal.getConection();
			prst=con.prepareStatement("Select * from "+tableName()+" where "+p.idFieldName()+"=?");
			
			if(p.id() instanceof Integer) { prst.setInt(1, p.id()); }
			else if(p.id() instanceof Integer) { prst.setString(1, p.id());}
			prst.setObject(1, p.id());
			
			prst.setDouble(0, 0);
			prst.setBlob(0, null);
			prst.setBoolean(0, false);
			prst.setFloat(0, 0);
			prst.setInt(0, 0);
			prst.setObject(0, con);
			prst.setString(0, null);
			prst.setTimestamp(0, null);
			
			rs=prst.executeQuery();
			if(rs.next()) {
				return mapfromResultSet();
			}
			else { return null; }
				
		} 
		catch (SQLException ex) { throw ex; }
		finally { closeResourses(); }
		
	}
	
}
