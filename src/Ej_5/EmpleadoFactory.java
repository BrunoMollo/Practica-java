package Ej_5;

import java.util.Scanner;

public class EmpleadoFactory {

	static public Empleado createEmpleado(Scanner sc) {
		
		System.out.print("Tipo de empleado[A/V]: ");
		String tipoEmpleado =sc.nextLine();
		
		System.out.print("Ingresar dni: ");
		String _dni=sc.nextLine();
		System.out.print("Ingresar nombre: ");
		String _nombre=sc.nextLine();
		System.out.print("Ingresar apellido: ");
		String _apellido=sc.nextLine();
		System.out.print("Ingresar email: ");
		String _email=sc.nextLine();
		System.out.print("Ingresar sueldo base: ");
		float _sueldoBase=Float.parseFloat(sc.nextLine());
		
		
		if(tipoEmpleado.equalsIgnoreCase("A")) {
			System.out.print("Ingresar hs extra: ");
			float _hsExtra=Float.parseFloat(sc.nextLine());
			System.out.print("Ingresar hs mes: ");
			float _hsMes=Float.parseFloat(sc.nextLine());
			
			return new Administrativo(_dni, _nombre, _apellido, _email, _sueldoBase,_hsExtra , _hsMes);
		}
		else if(tipoEmpleado.equalsIgnoreCase("V")) {
			System.out.print("Ingresar porcentaje de comision: ");
			float _porcenComision=Float.parseFloat(sc.nextLine());
			System.out.print("Ingresar total ventas: ");
			float _totalVentas=Float.parseFloat(sc.nextLine());
			
			return new Vendedor(_dni, _nombre, _apellido, _email, _sueldoBase, _porcenComision, _totalVentas);
		}
		else {
			return null;
		}
			
	}
}
