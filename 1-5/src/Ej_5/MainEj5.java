package Ej_5;

import java.util.ArrayList;


public class MainEj5 {
	static final int MAX_EMPLEADOS=20;

	public static void main(String[] args) {
		
		//Empleado[] arr = cargaEstatica(factory);
		ArrayList<Empleado> arr=cargaDinamica();
		
		for(Empleado e:arr) {
			if(e!=null) {
				System.out.println(e.getDetalle());
			}
		}
	}
	
	
	private static ArrayList<Empleado> cargaDinamica(){
		ArrayList<Empleado> arr= new ArrayList<Empleado>();
		EmpleadoFactory factory= new EmpleadoFactory(System.in);
		
		for(int i=0; i<MAX_EMPLEADOS;i++) {
			arr.add(factory.createEmpleado());
			if(factory.continuarEnLoop()==false)break;
		}
		factory.close();
		return arr;
	}

	
	private static Empleado[] cargaEstatica() {
		Empleado[] arr=new Empleado[MAX_EMPLEADOS];
		EmpleadoFactory factory= new EmpleadoFactory(System.in);
		
		for(int i=0; i<MAX_EMPLEADOS;i++) {
			arr[i]=factory.createEmpleado();
			if(factory.continuarEnLoop()==false)break;
		}
		factory.close();
		return arr;
	}
	
	

}
