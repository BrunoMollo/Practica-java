package Dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import bdManage.Canal;
import bdManage.InstantTransaction;
import bdManage.PreparedTransaction;
import logic.Product;


public class ProductosDao {
	
	private Canal canal;
	
	public ProductosDao(){
		this.canal=Canal.getCanal();
	}
	
	public LinkedList<Product> getAll(){
		return canal.executeInstantTransaction((InstantTransaction t)->{
			LinkedList<Product> arr= new LinkedList<>();
			t.executeQuery("Select * from product");
			while(t.getResultSet().next()) {
				Product p=mapResulSetToProduct(t.getResultSet());
				arr.add(p);
			}
			return arr;
		});
	}
	
	
	
	public Product getOne(Integer id) {
		return canal.executeTransaction((PreparedTransaction t)->{
			Product p=null;
			t.prepareStatement("select * from product where id=?");
			t.getStatement().setInt(1, id);
			t.executePreparedQuery();
			if(t.getResultSet().next()) {
				p=mapResulSetToProduct(t.getResultSet());
			}
			return p;
		});
	}
	
	public Product add(Product p) {
		return canal.executeTransaction((PreparedTransaction t)->{
			t.prepareStatement("INSERT INTO product (name,description,price,stock,shippingIncluded) VALUES (?,?,?,?,?)", 
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			t.getStatement().setString(1, p.getName());
			t.getStatement().setString(2, p.getDescripcion());
			t.getStatement().setDouble(3, p.getPrice());
			t.getStatement().setInt(4, p.getStock());
			t.getStatement().setBoolean(5, p.isShippingIncluded());
			
			t.executeUpdate();
			
			if(t.getResultSet()!=null && t.getResultSet().next()) {
				p.setId(t.getResultSet().getInt(1));
			}
			
			return p;
		});
	}
	
	public int delete(int targetId) {
		return canal.executeTransaction((PreparedTransaction t)->{
			t.prepareStatement("Delete from product where id=?");
			t.getStatement().setInt(1, targetId);
			return t.executeUpdate();
		});
		
	}
	
	public int update(Product p) {
		return canal.executeTransaction((PreparedTransaction t) -> {
			
			t.prepareStatement("Update product set name=?, description=?, price=?, stock=?, shippingIncluded=? where id=?");
			
			t.getStatement().setString(1, p.getName());
			t.getStatement().setString(2, p.getDescripcion());
			t.getStatement().setDouble(3, p.getPrice());
			t.getStatement().setInt(4, p.getStock());
			t.getStatement().setBoolean(5, p.isShippingIncluded());
			
			t.getStatement().setInt(6, p.getId());
			
			return t.executeUpdate();
		});
	}
	
//----------------------------------------------------------------------------------------
	
	private Product mapResulSetToProduct(ResultSet rs) throws SQLException {
		Product p=new Product();
			p.setId(rs.getInt("id"));
			p.setName(rs.getString("name"));
			p.setDescripcion(rs.getString("description"));
			p.setPrice(rs.getDouble("price"));
			p.setStock(rs.getInt("stock"));
			p.setShippingIncluded(rs.getBoolean("shippingIncluded"));
		return p;
	}
	
}
