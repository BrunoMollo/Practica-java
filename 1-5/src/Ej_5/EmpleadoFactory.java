package Ej_5;

import java.io.InputStream;
import java.util.Scanner;


public class EmpleadoFactory {
	
	Scanner sc;
	
	
	String inputString(String nombre_dato) {
		System.out.print("Ingresar "+nombre_dato+": ");
		return this.sc.nextLine();
	}
	
	
	float inputFloat(String nombre_dato) {
		return Float.parseFloat(inputString(nombre_dato));
	}
	
	//----------------------------------------------------------------------------------------------------
	
	
	public EmpleadoFactory(InputStream stream) {
		this.sc = new Scanner(stream);
	}
	
	public void close() {
		this.sc.close();
	}
	
	public Empleado createEmpleado() {
		
		String tipoEmpleado=inputString("Tipo de empleado[A/V]");
		
		String _dni=inputString("dni");
		String _nombre=inputString("nombre");
		String _apellido=inputString("apellido");
		String _email=inputString("email");
		float _sueldoBase=inputFloat("sueldo base");
		
		if(tipoEmpleado.equalsIgnoreCase("A")) {
			float _hsExtra=inputFloat("horas extra");
			float _hsMes=inputFloat("horas mes");
			
			return new Administrativo(_dni, _nombre, _apellido, _email, _sueldoBase,_hsExtra , _hsMes);
		}
		else if(tipoEmpleado.equalsIgnoreCase("V")) {
			float _porcenComision=inputFloat("porcentaje comision");
			float _totalVentas=inputFloat("total ventas");
					
			return new Vendedor(_dni, _nombre, _apellido, _email, _sueldoBase, _porcenComision, _totalVentas);
		}
		else {
			return null;
		}
			
	}
	
	
	public boolean continuarEnLoop() {
		System.out.print("¿Desea ingresar otro empleado? [S/N]: ");
		return (sc.nextLine().equalsIgnoreCase("S")); 	
	}
	
	

}
