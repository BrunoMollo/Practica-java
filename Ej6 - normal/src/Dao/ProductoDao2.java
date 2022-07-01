package Dao;

import logic.Product;

public class ProductoDao2 extends Dao<Product>{
	

	@Override
	public String getTabla() {return "productos"; }
	
	
	public ProductoDao2() throws ClassNotFoundException { super(); }

}
