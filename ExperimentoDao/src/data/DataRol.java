package data;

import java.security.KeyStore.Entry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import entities.*;
import java.util.Map;

import Dao.Dao;
import Dao.DbConnector;

public class DataRol extends Dao<Rol>{
	

	@Override
	protected Rol mapFromResulset() throws SQLException {
		Rol r=new Rol();
		r.setId(rs.getInt("id"));
		r.setDescripcion(rs.getString("descripcion"));
		return r;
	}
	
	public LinkedList<Rol> findAll(){
		return executeFindAll(()->{
			st=con.prepareStatement("select * from rol");
		});
	}
	
	public Rol getById(Rol rolToSearch) {
		return executeGetOne(()->{
			st=con.prepareStatement("select * from rol where id=?");
			st.setInt(1, rolToSearch.getId());
		});
	}
	
	public Rol getByDesc(Rol rolToSearch) {
		return executeGetOne(()->{
			st=con.prepareStatement("select * from rol where descripcion=?");
			st.setString(1, rolToSearch.getDescripcion());
		});
	}
	
	public LinkedList<Rol> findAllFromUser(Persona per) {
		return executeFindAll(()->{
			st=con.prepareStatement("select rol.* from rol inner join rol_persona on rol.id=rol_persona.id_rol where id_persona=?");
			st.setInt(1, per.getId());
		});
	}
	
	

	

	
	public void add(Rol rol) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into rol(descripcion) values(?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, rol.getDescripcion());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                rol.setId(keyResultSet.getInt(1));
            }

			
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}

	}
	
	public void update(Rol rol) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update rol set descripcion=? where id=?");
			stmt.setString(1, rol.getDescripcion());
			stmt.setInt(2, rol.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
	public void remove(Rol rol) {
		executeDelete(()->{
			st=con.prepareStatement("delete from rol where id=?");
			st.setInt(1, rol.getId());
			st.executeUpdate();
		});
		
	}



}
