package Probando;

public class Persona {
	Integer id;
	String nombre;
	String apellido;
	String tipoDni;
	int nroDni;
	boolean estaHabilitado;
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setTipoDni(String tipoDni) {
		this.tipoDni = tipoDni;
	}
	public void setNroDni(int nroDni) {
		this.nroDni = nroDni;
	}
	public void setEstaHabilitado(boolean estaHabilitado) {
		this.estaHabilitado = estaHabilitado;
	}
	
	
	public Integer getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public String getTipoDni() {
		return tipoDni;
	}
	public int getNroDni() {
		return nroDni;
	}
	public boolean GetEstaHabilitado() {
		return estaHabilitado;
	}
	
	
	@Override
	public String toString() {
		return "["+id+"-"+nombre+"-"+apellido+"-"+tipoDni+"-"+nroDni+"-"+estaHabilitado+"]";
	}
}
