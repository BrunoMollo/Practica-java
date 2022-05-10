package Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import bdManage.Canal;
import logic.Product;


public class ProductosDao {
	
	private Canal canal;
	private Statement st;
	private PreparedStatement prst;
	private ResultSet rs;
	
	
	public ProductosDao() throws ClassNotFoundException{
		this.canal=Canal.getCanal();
	}
	
	
	public LinkedList<Product> getAll() throws SQLException{
		LinkedList<Product> arr=new LinkedList<Product>();
		try {
			Connection con =canal.getConection();
			st=con.createStatement();
			rs=st.executeQuery("Select * from product");
			
			while(rs.next()) {
				Product p=new Product();
				mapResulSetToProduct(p);
				arr.add(p);
			}
			return arr;
		} 
		catch (SQLException ex) { throw ex; }
		finally { closeResourses(); }
		
	}
	
	
	public Product getOne(Product p) throws SQLException {
		try {
			Connection con=canal.getConection();
			prst=con.prepareStatement("Select * from product where id=?");
			prst.setInt(1, p.getId());
			
			rs=prst.executeQuery();
			if(rs.next()) {
				mapResulSetToProduct(p);
				return p;
			}
			else { return null; }
				
		} 
		catch (SQLException ex) { throw ex; }
		finally { closeResourses(); }
		
	}
	
	
	public Product add(Product p) throws SQLException {
		try {
			Connection con=canal.getConection();
			prst=con.prepareStatement("INSERT INTO product (name,description,price,stock,shippingIncluded) VALUES (?,?,?,?,?)", 
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			prst.setString(2, p.getDescripcion());
			prst.setString(1, p.getName());
			prst.setDouble(3, p.getPrice());
			prst.setInt(4, p.getStock());
			prst.setBoolean(5, p.isShippingIncluded());
			
			prst.executeUpdate();
			
			rs=prst.getGeneratedKeys();
			if(rs.next()) {
				p.setId(rs.getInt(1));
			}
			
			return p;
		} 
		catch (SQLException ex) { throw ex; }
		finally { closeResourses(); }
	}
	
	
	public void delete(Product p) throws SQLException {
		try {
			Connection con=canal.getConection();
			prst=con.prepareStatement("delete from product where id=?");
			prst.setInt(1, p.getId());
			
			int modifiedRows=prst.executeUpdate();
			
			if(modifiedRows==0) {
				//tira appExeption
			}
		} 
		catch (SQLException ex) { throw ex; }
		finally { closeResourses(); }
			
	}
	
	
	public void update(Product p) throws SQLException {
		try {
			Connection con=canal.getConection();
			
			prst=con.prepareStatement("Update product set name=?, description=?, price=?, stock=?, shippingIncluded=? where id=?");
			prst.setString(1, p.getName());
			prst.setString(2, p.getDescripcion());
			prst.setDouble(3, p.getPrice());
			prst.setInt(4, p.getStock());
			prst.setBoolean(5, p.isShippingIncluded());
			
			prst.setInt(6, p.getId()); 
			
			int modifiedRows=prst.executeUpdate();
			
			if(modifiedRows==0) {
				//tira appExeption
			}
		} 
		catch (SQLException ex) { throw ex; }
		finally { closeResourses(); }
	}
	
//----------------------------------------------------------------------------------------
	
	private void closeResourses() throws SQLException {
		canal.releaseConection();
		if(prst!=null) { prst.close(); }
		if(st!=null) { st.close(); }
		if(rs!=null) { rs.close(); }
	}
	
	
	private Product mapResulSetToProduct(Product p) throws SQLException {
			p.setId(rs.getInt("id"));
			p.setName(rs.getString("name"));
			p.setDescripcion(rs.getString("description"));
			p.setPrice(rs.getDouble("price"));
			p.setStock(rs.getInt("stock"));
			p.setShippingIncluded(rs.getBoolean("shippingIncluded"));
			
		return p;
	}
	
}
