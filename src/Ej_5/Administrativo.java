package Ej_5;

public class Administrativo extends Empleado{
	
	float hsExtra;
	float hsMes;
	
	//----------------------------------------------------------------------------------------------------

	
	public Administrativo(String dni, String nombre, String apellido, String email, float sueldoBase, float hsExtra,
			float hsMes) {
		super(dni, nombre, apellido, email, sueldoBase);
		this.hsExtra = hsExtra;
		this.hsMes = hsMes;
	}
	
	@Override
	public float getSueldo() {
		return sueldoBase * ((hsExtra * 1.5f)+ hsMes)/hsMes;
	}
}
