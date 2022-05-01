package ui;

import java.util.Scanner;
import Dao.ProductosDao;
import logic.Product;


public class Principal6 {
	
	static Scanner sc= new Scanner(System.in);
	static ProductosDao pDao=new ProductosDao();
	
	public static void main(String[] args) {	
		clearScreen();
		int opc=1;
		while(opc!=0) {
			System.out.print(
					"\t||JAVA MARKET||\n"+
					"1.Listar todos los productos\n"+
					"2.Buscar producto\n"+
					"3.Agregar nuevo Producto\n"+
					"4.Eliminar producto\n"+
					"5.Modificar producto\n"+
					"0.Salir\n"+
					"Opcion: "
					);
			opc=Integer.parseInt(sc.nextLine());
			
			switch (opc) {
			case 1:
				listarTodos();
				break;
			case 2:
				buscarProducto();
				break;
			case 3:
				AgregarNuevoProducto();
				break;
			case 4:
				eliminarProducto();
				break;
			case 5:
				modificarProducto();
				break;
			case 0:
				System.out.println("chua");
				break;
			default: break;
			}
			if(opc!=0) {
				 System.out.println("\t\tPress enter to continue.....");
				 sc.nextLine();
				 clearScreen();
			}  
		}	
	}

	//--------------------------------------------------------------------
	
	private static void modificarProducto() {
		System.out.print("\nMODIFICACION DE PRODUCTO\n"+
				"Ingresar id: ");

		int id=Integer.parseInt(sc.nextLine());
		Product pTarget=pDao.getOne(id);
		
		if(pTarget==null) {System.out.println("NO exite ningun producto con la id indicada"); return;}
			
		System.out.println("\nProducto a modificar: \n"+pTarget.toCard());
		cargar(pTarget);
		System.out.println("\n Registros modificados: "+pDao.update(pTarget));
	}

	private static void eliminarProducto() {
		System.out.print("\nELIMINACION DE PRODUCTO\n"+
				"Ingresar id: ");
		
		int id=Integer.parseInt(sc.nextLine());
		System.out.println("\nRegistros eliminados: "+pDao.delete(id));		
	}

	private static void AgregarNuevoProducto() {
		Product p=new Product();
		cargar(p);
		p=pDao.add(p);
		if(p!=null) { System.out.println("\nSe dio de alta: \n"+p); }
		else {System.out.println("No se pudo dar de alta");}	
	}

	private static void buscarProducto() {
		System.out.print("\nBUSQUEDA DE PRODUCTO\n"+
							"Ingresar id: ");
		int id=Integer.parseInt(sc.nextLine());
		System.out.println("\nProducto buscado: \n"+pDao.getOne(id).toCard());	
	}

	private static void listarTodos() {
		System.out.println("\nTODOS LOS PRODUCTOS");
		for(Product pro: pDao.getAll()) {
			System.out.println(pro);
		}
	}
	
	
	//----------------------------------
	
	private static void cargar(Product p) {
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
	
	private static void clearScreen() {
		for(int i=0; i<15;i++) {
			System.out.println(" ");
		}
	}

}
