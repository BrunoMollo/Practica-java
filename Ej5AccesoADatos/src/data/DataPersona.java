package data;

import entities.*;

import java.sql.*;
import java.util.LinkedList;

public class DataPersona {
	
	private DataRol dr=new DataRol();
	private Statement stmt=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	
	private Persona mapPersonaFromRs() throws SQLException {
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
		
		dr.setRoles(p);
		
		return p;
	}
	
	private void closeResourses() {
		try {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			if(pstmt!=null) {pstmt.close();}
			DbConnector.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public LinkedList<Persona> getAll(){
		LinkedList<Persona> pers= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select id,nombre,apellido,tipo_doc,nro_doc,email,tel,habilitado from persona");
			//intencionalmente no se recupera la password
				while(rs!=null && rs.next()) {
					pers.add(mapPersonaFromRs());
				}	
		}
		catch (SQLException e) { e.printStackTrace(); } 
		finally { closeResourses(); }
		
		return pers;
	}

	
	
	public Persona getByUser(Persona per) {
		Persona p=null;
		try {
			pstmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre,apellido,tipo_doc,nro_doc,email,tel,habilitado from persona where email=? and password=?"
					);
			pstmt.setString(1, per.getEmail());
			pstmt.setString(2, per.getPassword());
			rs=pstmt.executeQuery();
			if(rs!=null && rs.next()) {
				p=mapPersonaFromRs();
			}
		} 
		catch (SQLException e) { e.printStackTrace(); } 
		finally { closeResourses(); }
		return p;
	}

	
	public Persona getByDocumento(Persona per) {
		Persona p=null;
		try {
			pstmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre,apellido,tipo_doc,nro_doc,email,tel,habilitado from persona where tipo_doc=? and nro_doc=?"
					);
			pstmt.setString(1, per.getDocumento().getTipo());
			pstmt.setString(2, per.getDocumento().getNro());
			rs=pstmt.executeQuery();
			if(rs!=null && rs.next()) {
				p=new Persona();
				p.setDocumento(new Documento());
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.getDocumento().setTipo(rs.getString("tipo_doc"));
				p.getDocumento().setNro(rs.getString("nro_doc"));
				p.setEmail(rs.getString("email"));
				p.setTel(rs.getString("tel"));
				p.setHabilitado(rs.getBoolean("habilitado"));
				
				dr.setRoles(p);
			}
		} 
		catch (SQLException e) { e.printStackTrace(); } 
		finally { closeResourses(); }
		
		return p;
	}

	
	
	public void add(Persona p) {
		try {
			pstmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into persona(nombre, apellido, tipo_doc, nro_doc, email, password, tel, habilitado) values(?,?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			pstmt.setString(1, p.getNombre());
			pstmt.setString(2, p.getApellido());
			pstmt.setString(3, p.getDocumento().getTipo());
			pstmt.setString(4, p.getDocumento().getNro());
			pstmt.setString(5, p.getEmail());
			pstmt.setString(6, p.getPassword());
			pstmt.setString(7, p.getTel());
			pstmt.setBoolean(8, p.isHabilitado());
			pstmt.executeUpdate();
			
			rs=pstmt.getGeneratedKeys();
            if(rs!=null && rs.next()){
                p.setId(rs.getInt(1));
            }	
               
		}  
		catch (SQLException e) { e.printStackTrace(); } 
		finally { closeResourses(); }
    }

	
	public LinkedList<Persona> getAllBySurname(Persona p) {
		LinkedList<Persona> arr= new LinkedList<Persona>();
		try {
			pstmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre,apellido,tipo_doc,nro_doc,email,tel,habilitado "
					+ "from persona "
					+ "where apellido=?");
			pstmt.setString(1, p.getApellido());
			
			rs=pstmt.executeQuery();
			while(rs!=null && rs.next()) {
				arr.add(mapPersonaFromRs());
			}
		}
		catch (SQLException e) { e.printStackTrace(); } 
		finally { closeResourses(); }

		return arr;
	}

	
	public void delete(Persona p) {
		Connection con=DbConnector.getInstancia().getConn();
		
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(
					"delete from rol_persona where id_persona=?");
			pstmt.setInt(1, p.getId());
			pstmt.executeUpdate();
			
			pstmt=con.prepareStatement(
					"delete from persona where tipo_doc=? and nro_doc=?");
			pstmt.setString(1, p.getDocumento().getTipo());
			pstmt.setString(2, p.getDocumento().getNro());
			pstmt.executeUpdate();
			
			con.commit();
		}
		catch (SQLException e) { 
			e.printStackTrace(); 
			try { if(con!=null) { con.rollback(); } } 
			catch (SQLException e1) { e1.printStackTrace(); }
			} 
		finally { closeResourses(); }
	}

	public void update(Persona p) {
		Connection con=DbConnector.getInstancia().getConn();
		try {
			con.setAutoCommit(false);
			
			//Modifico atributos no nulos
			pstmt=con.prepareStatement(
					"update persona set "
					+ "tipo_doc=?,"
					+ "nro_doc=?,"
					+ "nombre=?,"
					+ "apellido=?,"
					+ "email=?,"
					+ "tel=?,"
					+ "habilitado=? "
					+ " where id=?");
			pstmt.setString(1, p.getDocumento().getTipo());
			pstmt.setString(2, p.getDocumento().getNro());
			pstmt.setString(3,p.getNombre());
			pstmt.setString(4, p.getApellido());
			pstmt.setString(5, p.getEmail());
			pstmt.setString(6, p.getTel());
			pstmt.setBoolean(7,p.isHabilitado());
			pstmt.setInt(8, p.getId());
			pstmt.executeUpdate();
			
			//Modifico la psw, que puede ser nula porque no se la doy al ususario
			if(p.getPassword()!=null) {
				pstmt=con.prepareStatement(
						"update persona set password=? where id=?");
				pstmt.setString(1, p.getPassword());
				pstmt.setInt(2, p.getId());
				pstmt.executeUpdate();
			}
			
			dr.clearRoles(p);
			dr.LoadRoles(p);
			
			con.commit();
		}
		catch (SQLException e) { 
			e.printStackTrace(); 
			DbConnector.getInstancia().tryRollback();
			} 
		finally { closeResourses(); }
	}

	
}
