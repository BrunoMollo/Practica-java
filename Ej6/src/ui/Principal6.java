package ui;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

import com.mysql.cj.protocol.Resultset;

import Dao.ProductosDao;
import logic.Product;




public class Principal6 {
	public static void main(String[] args) {
		
		
		ProductosDao dbc=new ProductosDao("com.mysql.cj.jdbc.Driver");
		
		System.out.println(dbc.getOne(2));
		System.out.println(dbc.getOne(20));
		System.out.println(dbc.getOne(3));
		
		for(Product p: dbc.getAll()) {
			System.out.println(p);
		}
		
	}

}
