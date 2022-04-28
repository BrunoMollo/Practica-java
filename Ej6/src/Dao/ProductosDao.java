package Dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import bdManage.Canal;
import bdManage.InstantTransaction;
import bdManage.PreparedTransaction;
import logic.Product;




public class ProductosDao {
	
	private Canal canal;
	
	public ProductosDao(String driverName){
		this.canal=new Canal("jdbc:mysql://localhost/javaMarket","java","himitsu");
		try {
			Class.forName(driverName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
	
	public LinkedList<Product> getAll(){
		return canal.executeTransaction((InstantTransaction t)->{
			
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
	
	public Product save(Product p) {
		return canal.executeTransaction((PreparedTransaction t)->{
			t.prepareStatement("NSERT INTO table_name (name,description,price,stock,shippingIncluded) VALUES (?,?,?,?,?)");
			t.getStatement().setString("name", );
			t.getStatement().setDouble("description", 0)
			t.getStatement().setInt(0, 0);
			t.getStatement().setBoolean(0, false);
			
			return null;
		});
	}
	
}
