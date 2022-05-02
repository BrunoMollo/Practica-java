package ui;

import java.util.Scanner;
import Dao.ProductosDao;


public class Principal6 {
	
	static Scanner sc= new Scanner(System.in);

	static ProductosDao pDao;
	
	public static void main(String[] args) {
		
		try {pDao=new ProductosDao();}
		catch(ClassNotFoundException ex){
			System.out.println("Error: NO se pudo instanciar el pDao");
			ex.printStackTrace(); 
			sc.close();
			return;
		}
		
		
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
				Opcion.listarTodos(sc, pDao);
				break;
			case 2:
				Opcion.buscarProducto(sc, pDao);
				break;
			case 3:
				Opcion.AgregarNuevoProducto(sc,pDao);
				break;
			case 4:
				Opcion.eliminarProducto(sc, pDao);
				break;
			case 5:
				Opcion.modificarProducto(sc, pDao);
				break;
			case 0:
				sc.close();
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
	
	
	
	private static void clearScreen() {
		for(int i=0; i<15;i++) {
			System.out.println(" ");
		}
	}

}
