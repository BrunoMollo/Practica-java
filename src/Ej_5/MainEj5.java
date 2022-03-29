package Ej_5;

import java.util.ArrayList;
import java.util.Scanner;

public class MainEj5 {
	static final int MAX_EMPLEADOS=20;

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		
		//Empleado[] arr = cargaEstatica(sc);
		ArrayList<Empleado> arr=cargaDinamica(sc);
		
		for(Empleado e:arr) {
			if(e!=null) {
				System.out.println(e.getDetalle());
			}
		}
		
		sc.close();

	}
	
	private static ArrayList<Empleado> cargaDinamica(Scanner sc){
		ArrayList<Empleado> arr= new ArrayList<Empleado>();
		
		for(int i=0; i<MAX_EMPLEADOS;i++) {
			arr.add(EmpleadoFactory.createEmpleado(sc));
			
			System.out.print("¿Desea ingresar otro empleado? [S/N]: ");
			if(sc.nextLine().equalsIgnoreCase("N"))break;
		}
		
		return arr;
	}

	
	private static Empleado[] cargaEstatica(Scanner sc) {
		Empleado[] arr=new Empleado[MAX_EMPLEADOS];
		
		for(int i=0; i<MAX_EMPLEADOS;i++) {
			arr[i]=EmpleadoFactory.createEmpleado(sc);
			
			System.out.print("¿Desea ingresar otro empleado? [S/N]: ");
			if(sc.nextLine().equalsIgnoreCase("N"))break;
		}
		return arr;
	}

}
