package Probando;

import java.sql.*;
import java.util.LinkedList;


public class testSql {

	public static void main(String[] args) {
		
		try {
			new com.mysql.cj.jdbc.Driver();
			//Class.forName("com.mysql.cl.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Persona pIns=new Persona();
		pIns.setNombre("Bruno");
		pIns.setApellido("Moyo");
		pIns.setTipoDni("DNI");
		pIns.setNroDni(53313296);
		pIns.setEstaHabilitado(false);
		
		insertarNew(pIns);

		
		for(Persona p :getAll()) {
			System.out.println(p);
		}
	
	}
	

	
	private static Persona getOne(Integer id) {
		Connection con=null;
		Persona p=new Persona();
		
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost/javaTest","java","himitsu");
			
			PreparedStatement st=con.prepareStatement("select * from persona where id=?");
			st.setInt(1, id);
			
			ResultSet rs=st.executeQuery();
			
			
			if(rs.next()) {
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setTipoDni(rs.getString("tipo_doc"));
				p.setNroDni(rs.getInt("nro_doc"));
				p.setEstaHabilitado(rs.getBoolean("estaHabilitado"));
			}
			
			if(rs!=null) {rs.close();}
			if(st!=null) {st.close();}
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	private static LinkedList<Persona> getAll() {
		Connection con=null;
		LinkedList<Persona> arr= new LinkedList<>();
		
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost/javaTest","java", "himitsu");
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("Select * from persona");
			
			while(rs.next()) {
				Persona p=new Persona();
				
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setTipoDni(rs.getString("tipo_doc"));
				p.setNroDni(rs.getInt("nro_doc"));
				p.setEstaHabilitado(rs.getBoolean("estaHabilitado"));
				arr.add(p);
			}
			
			if(rs!=null) {rs.close();}
			if(st!=null) {st.close();}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return arr;
	}

	private static void insertarNew(Persona p) {
		Connection con=null;
		
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost/javaTest","java", "himitsu");
			PreparedStatement st=
					con.prepareStatement("insert into persona(tipo_doc,nro_doc,nombre,apellido,estaHabilitado) values (?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setString(1, p.getTipoDni());
			st.setInt(2, p.getNroDni());
			st.setString(3, p.getNombre());
			st.setString(4, p.getApellido());
			st.setBoolean(5, p.GetEstaHabilitado());
			
			st.executeUpdate();
			
			ResultSet genKey=st.getGeneratedKeys();
			if(genKey!=null && genKey.next()) {
				int id=genKey.getInt("id");
				p.setId(id);
			}
			
			if(st!=null) {st.close();}
			if(genKey!=null) {genKey.close();}
			con.close();
		
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
