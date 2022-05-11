package ui;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;

import Dao.ProductosDao;
import logic.Product;

public class Opcion {
	
	
	public static void listarTodos(Scanner sc, ProductosDao pDao) {
		System.out.println("\nTODOS LOS PRODUCTOS");
		
		LinkedList<Product> arr=new LinkedList<Product>();
		try { arr = pDao.getAll(); } 
		catch (SQLException e) { e.printStackTrace(); }
		
		for(Product pro: arr) {
			System.out.println(pro);
			}
	}
	
	
	public static void buscarProducto(Scanner sc, ProductosDao pDao) {
	
		System.out.print("\nBUSQUEDA DE PRODUCTO\n"+
						 "Ingresar id: ");
		
		Product p=new Product();
		Integer idTarget=Integer.parseInt(sc.nextLine());
		p.setId(idTarget);
		
		try { p=pDao.getOne(p); }
		catch (SQLException e) { e.printStackTrace(); }	
		
		if(p!=null) {
			System.out.println("\nProducto buscado: \n"+p.toCard());
		}
		else {
			System.out.println("No se ha encontrado el producto con id "+idTarget);
		}
	}
	
	
	public static void AgregarNuevoProducto(Scanner sc, ProductosDao pDao) {
		Product p=new Product();
		cargar(sc,p);
		
		try { 
			pDao.add(p); 
			if(p.getId()!=null) { System.out.println("\nSe dio de alta: \n"+p); } 
			else {System.out.println("No se pudo dar de alta");}				
		}
		catch (SQLException e) { e.printStackTrace(); }
		
	}


	public static void eliminarProducto(Scanner sc, ProductosDao pDao) {
		System.out.print("\nELIMINACION DE PRODUCTO\n"+
				"Ingresar id: ");
		
		Product p=new Product();
		p.setId(Integer.parseInt(sc.nextLine()));
		
		try { 
			p=pDao.getOne(p); 
			if(p!=null) {
				System.out.print(p.toCard()+"\n¿Seguro que quiere borrar este producto?[S/N]: ");
				if(sc.nextLine().equalsIgnoreCase("S")) {
					try {
						pDao.delete(p);
						System.out.println("Producto borrado");
					} 
					catch (SQLException e) { e.printStackTrace(); }	
				}
			}
			else {
				System.out.println("No se ha encontrado el producto con id "+p.getId());
			}
			
			
		}
		catch (SQLException e) { e.printStackTrace(); }
	}


	
	public static void modificarProducto(Scanner sc, ProductosDao pDao) {
		System.out.print("\nMODIFICACION DE PRODUCTO\n"+
				"Ingresar id: ");

		Product p=new Product();
		p.setId(Integer.parseInt(sc.nextLine()));
		
		try { 
			p=pDao.getOne(p); 
			if(p!=null) {
				System.out.println("\nProducto a modificar: \n"+p.toCard());
				
				cargar(sc, p);
				pDao.update(p);
				System.out.println("\nSe modifico el registro");
			}
			else{
				System.out.println("NO exite ningun producto con la id indicada");
			}
		}
		catch (SQLException e) { e.printStackTrace(); }	
			
	}




	
	//----------------------------------
	
	private static void cargar(Scanner sc,Product p) {
		String input;
		
		System.out.print("\t|Nombre: ");
		input=sc.nextLine();
		if(input!="") { p.setName(input); }
		
		System.out.print("\t|Descrpicion: ");
		input=sc.nextLine();
		if(input!="") { p.setDescripcion(input); }
		
		System.out.print("\t|Precio: ");
		input=sc.nextLine();
		if(input!="") { p.setPrice(Double.parseDouble(input)); }
				
		System.out.print("\t|Stock: ");
		input=sc.nextLine();
		if(input!="") { p.setStock(Integer.parseInt(input)); }
		
		System.out.print("\t|Envio incluido: ");
		input=sc.nextLine();
		if(input!="") { p.setShippingIncluded(Boolean.parseBoolean(input)); }

	}
}
