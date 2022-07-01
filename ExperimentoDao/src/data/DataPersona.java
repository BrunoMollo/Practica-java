package data;

import entities.*;


import java.sql.*;
import java.util.LinkedList;
import java.util.Map;

import Dao.Dao;
import Dao.DbConnector;


public class DataPersona extends Dao<Persona>{
	
	private DataRol dataRol=new DataRol();
	
	
	@Override
	protected Persona mapFromResulset() throws SQLException { 
		Persona p=new Persona();
		p.setDocumento(new Documento());
		p.setId(rs.getInt("id"));
		p.setNombre(rs.getString("nombre"));
		p.setApellido(rs.getString("apellido"));
		p.getDocumento().setTipo(rs.getString("tipo_doc"));
		p.getDocumento().setNro(rs.getString("nro_doc"));
		p.setEmail(rs.getString("email"));
		p.setTel(rs.getString("tel"));
		p.setHabilitado(rs.getBoolean("habilitado"));
		
		for(Rol r: dataRol.findAllFromUser(p)) {
			p.addRol(r);
		}
		
		return p;
	}
	
	public LinkedList<Persona> FindAll(){
		return executeFindAll(()->{
			st= con.prepareStatement("select * from persona");
		});
	}
	
	public LinkedList<Persona> FindAllHabilitados(){
		return executeFindAll(()->{
			st= con.prepareStatement("select * from persona where habilitado=?");
			st.setBoolean(1, true);
		});
	}

	public Persona getOneById(Persona parameterized) {
		return executeGetOne(()->{
			st=con.prepareStatement("select * from persona where id=?");
			st.setInt(1, parameterized.getId());
		});
	}
	
	public Persona getOneByEmail(Persona parameterized) {
		return executeGetOne(()->{
			st=con.prepareStatement("select id,nombre,apellido,tipo_doc,nro_doc,email,tel,habilitado from persona where email=?");
			st.setString(1, parameterized.getEmail());
		});
	}
	
	public LinkedList<Persona> getAllBySurname(Persona p) {
		return executeFindAll(()->{
			st=con.prepareStatement("select id,nombre,apellido,tipo_doc,nro_doc,email,tel,habilitado from persona where apellido=?");
			st.setString(1, p.getApellido());
		});
	}
	
	public Persona getByDocumento(Persona per) {
		return executeGetOne(()->{
			st=con.prepareStatement("select id,nombre,apellido,tipo_doc,nro_doc,email,tel,habilitado from persona where tipo_doc=? and nro_doc=?");
			st.setString(1, per.getDocumento().getTipo());
			st.setString(2, per.getDocumento().getNro());
		});
	}
	
	public void  delete(Persona p) {
		executeDelete(()->{
			st=con.prepareStatement(
					"delete from rol_persona where id_persona=?");
			st.setInt(1, p.getId());
			st.executeUpdate();
			
			st=con.prepareStatement(
					"delete from persona where tipo_doc=? and nro_doc=?");
			st.setString(1, p.getDocumento().getTipo());
			st.setString(2, p.getDocumento().getNro());
			st.executeUpdate();
		});
	}	
	
	//--------------------------------------------.-.-.-.--------------------------------------------------------


	
	
	public void add(Persona p) {
		try {
			st=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into persona(nombre, apellido, tipo_doc, nro_doc, email, password, tel, habilitado) values(?,?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			st.setString(1, p.getNombre());
			st.setString(2, p.getApellido());
			st.setString(3, p.getDocumento().getTipo());
			st.setString(4, p.getDocumento().getNro());
			st.setString(5, p.getEmail());
			st.setString(6, p.getPassword());
			st.setString(7, p.getTel());
			st.setBoolean(8, p.isHabilitado());
			st.executeUpdate();
			
			rs=st.getGeneratedKeys();
            if(rs!=null && rs.next()){
                p.setId(rs.getInt(1));
            }	
               
		}  
		catch (SQLException e) { e.printStackTrace(); } 
		finally { closeResourses(); }
    }

	
	

	public void update(Persona p) {
		Connection con=DbConnector.getInstancia().getConn();
		try {
			con.setAutoCommit(false);
			
			//Modifico atributos no nulos
			st=con.prepareStatement(
					"update persona set "
					+ "tipo_doc=?,"
					+ "nro_doc=?,"
					+ "nombre=?,"
					+ "apellido=?,"
					+ "email=?,"
					+ "tel=?,"
					+ "habilitado=? "
					+ " where id=?");
			st.setString(1, p.getDocumento().getTipo());
			st.setString(2, p.getDocumento().getNro());
			st.setString(3,p.getNombre());
			st.setString(4, p.getApellido());
			st.setString(5, p.getEmail());
			st.setString(6, p.getTel());
			st.setBoolean(7,p.isHabilitado());
			st.setInt(8, p.getId());
			st.executeUpdate();
			
			//Modifico la psw, que puede ser nula porque no se la doy al ususario
			if(p.getPassword()!=null) {
				st=con.prepareStatement(
						"update persona set password=? where id=?");
				st.setString(1, p.getPassword());
				st.setInt(2, p.getId());
				st.executeUpdate();
			}
			
			//Borro los roles 
			st=DbConnector.getInstancia().getConn().prepareStatement(
					"delete from rol_persona where id_persona=?" );
			st.setInt(1, p.getId());
			st.executeUpdate();
			
			//Vuelvo a agregar los roles
			for(Map.Entry<Integer, Rol> entry: p.getRoles().entrySet()) {
				st=con.prepareStatement("insert into rol_persona(id_persona, id_rol) values(?,?)");
				st.setInt(1, p.getId()); 
				st.setInt(2, entry.getValue().getId()); 
				st.executeUpdate();
			}
		}
		catch (SQLException e) { 
			e.printStackTrace(); 
			DbConnector.getInstancia().tryRollback();
			} 
		finally { closeResourses(); }
	}
	
	public void clearRoles(Persona per) {
		PreparedStatement stmt=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"delete from rol_persona where id_persona=?" );
			stmt.setInt(1, per.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void LoadRoles(Persona per) {
		try {
			con.setAutoCommit(false);
			
			for(Map.Entry<Integer, Rol> entry: per.getRoles().entrySet()) {
				st=con.prepareStatement("insert into rol_persona(id_persona, id_rol) values(?,?)");
				st.setInt(1, per.getId()); 
				st.setInt(2, entry.getValue().getId()); 
				st.executeUpdate();
			}
			
			con.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			DbConnector.getInstancia().tryRollback();
		}
		finally { DbConnector.getInstancia().releaseConn(); }
	}

	
}
