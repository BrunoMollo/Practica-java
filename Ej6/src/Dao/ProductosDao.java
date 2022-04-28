package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.mysql.cj.protocol.Resultset;

import bdManage.Canal;
import bdManage.Transaction;
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
		return canal.makeTransaction((Transaction t)->{
			
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
		return canal.makeTransaction((t)->{
			Product p=null;
			t.prepareStatement("select * from product where id=?");
			t.pst.setInt(1, id);//RE VERRR
			
			
			t.executePreparedQuery();
			
			if(t.getResultSet().next()) {
				p=mapResulSetToProduct(t.getResultSet());
			}
			return p;
		});
	}
	
}
