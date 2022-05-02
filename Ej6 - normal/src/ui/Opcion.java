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
		p.setId(Integer.parseInt(sc.nextLine()));
		
		try { pDao.getOne(p); }
		catch (SQLException e) { e.printStackTrace(); }	
		
		if(!p.esNull()) {
			System.out.println("\nProducto buscado: \n"+p.toCard());
		}
		else {
			System.out.println("No se ha encontrado el producto con id "+p.getId());
		}
	}
	
	
	public static void AgregarNuevoProducto(Scanner sc, ProductosDao pDao) {
		Product p=new Product();
		cargar(sc,p);
		
		try { 
			pDao.add(p); 
			if(p.getId()!=null) { System.out.println("\nSe dio de alta: \n"+p); } //que pasa si esto lo bajo del try??
			else {System.out.println("No se pudo dar de alta");}				//	Es necesario este control????
		}
		catch (SQLException e) { e.printStackTrace(); }
		
	}


	
//	public static void modificarProducto(Scanner sc, ProductosDao pDao) {
//		System.out.print("\nMODIFICACION DE PRODUCTO\n"+
//				"Ingresar id: ");
//
//		int id=Integer.parseInt(sc.nextLine());
//		Product pTarget=pDao.getOne(id);
//		
//		if(pTarget==null) {System.out.println("NO exite ningun producto con la id indicada"); return;}
//			
//		System.out.println("\nProducto a modificar: \n"+pTarget.toCard());
//		cargar(sc, pTarget);
//		System.out.println("\n Registros modificados: "+pDao.update(pTarget));
//	}

//	public static void eliminarProducto(Scanner sc, ProductosDao pDao) {
//		System.out.print("\nELIMINACION DE PRODUCTO\n"+
//				"Ingresar id: ");
//		
//		int id=Integer.parseInt(sc.nextLine());
//		System.out.println("\nRegistros eliminados: "+pDao.delete(id));		
//	}
//



	
	
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
		
		p.marcarComoCargado();;
	}
}
