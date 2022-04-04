package Ej_5;

public abstract class Empleado {
	String dni;
	String nombre;
	String apellido;
	String email;
	float sueldoBase;
	
	//----------------------------------------------------------------------------------------------------
	

	public Empleado(String dni, String nombre, String apellido, String email, float sueldoBase) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.sueldoBase = sueldoBase;
	}
	
	public String getDetalle() {
		return "=> "+dni+", "+nombre+" "+apellido+". Sueldo: $"+this.getSueldo();
	}
	
	public abstract float getSueldo();

	
}
