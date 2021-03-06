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

public class DataRol {
	
	public LinkedList<Rol> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Rol> roles= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from rol");
			if(rs!=null) {
				while(rs.next()) {
					Rol r=new Rol();
					r.setId(rs.getInt("id"));
					r.setDescripcion(rs.getString("descripcion"));
					roles.add(r);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return roles;
	}
	
	public Rol getById(Rol rolToSearch) {
		Rol r=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from rol where id=?"
					);
			stmt.setInt(1, rolToSearch.getId());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				r=new Rol();
				r.setId(rs.getInt("id"));
				r.setDescripcion(rs.getString("descripcion"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return r;
	}
	
	public Rol getByDesc(Rol rolToSearch) {
		Rol r=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from rol where descripcion=?"
					);
			stmt.setString(1, rolToSearch.getDescripcion());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				r=new Rol();
				r.setId(rs.getInt("id"));
				r.setDescripcion(rs.getString("descripcion"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return r;
	}
	
	public void LoadRoles(Persona per) {
		Connection con=DbConnector.getInstancia().getConn();
		
		try {
			con.setAutoCommit(false);
			
			for(Map.Entry<Integer, Rol> entry: per.getRoles().entrySet()) {
				PreparedStatement pstmt=con.prepareStatement("insert into rol_persona(id_persona, id_rol) values(?,?)");
				pstmt.setInt(1, per.getId()); 
				pstmt.setInt(2, entry.getValue().getId()); 
				pstmt.executeUpdate();
			}
			
			con.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			DbConnector.getInstancia().tryRollback();
		}
		finally { DbConnector.getInstancia().releaseConn(); }
		
		
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
	
	public void setRoles(Persona per) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					  "select rol.* "
					+ "from rol "
					+ "inner join rol_persona "
					+ "on rol.id=rol_persona.id_rol "
					+ "where id_persona=?"
					);
			stmt.setInt(1, per.getId());
			rs= stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Rol r=new Rol();
					r.setId(rs.getInt("id"));
					r.setDescripcion(rs.getString("descripcion"));
					per.addRol(r);;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from rol where id=?");
			stmt.setInt(1, rol.getId());
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
}
