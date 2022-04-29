package ui;

import Dao.ProductosDao;
import logic.Product;


public class Principal6 {
	public static void main(String[] args) {
		
		Product p=new Product();
		p.setId(70);
		p.setName("tvCambiado");
		p.setDescripcion("nuevo y cambiado");
		p.setPrice(14.5);
		p.setStock(20);
		p.setShippingIncluded(true);
		
		
		
		ProductosDao pDao=new ProductosDao();
		
		pDao.update(p);

		
		for(Product pro: pDao.getAll()) {
			System.out.println(pro);
		}
		
	}

}
