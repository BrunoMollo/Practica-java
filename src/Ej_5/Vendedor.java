package Ej_5;

public class Vendedor extends Empleado{

	float porcenComision;
	float totalVentas;
	
	
	public Vendedor(String dni, String nombre, String apellido, String email, float sueldoBase, float porcenComision,
			float totalVentas) {
		super(dni, nombre, apellido, email, sueldoBase);
		this.porcenComision = porcenComision;
		this.totalVentas = totalVentas;
	}


	@Override
	public float getSueldo() {
		return sueldoBase + (porcenComision*totalVentas/100);
	}
	
}
