package ui;

import entities.Documento;
import entities.Persona;
import entities.Rol;
import utils.Input;
import java.util.LinkedList;

public class FormPersona {
	
	private Input in;
	
	public FormPersona(Input _in) {
		this.in= _in;
	}
	
	public Persona fill(String ...atributos) {
		LinkedList<String> atr = new LinkedList<String>();
        for(String a:atributos) { atr.add(a); }
		
		Persona p=new Persona();
		Documento doc=new Documento();
		
		if(atr.contains("nombre")) {
			p.setNombre(in.getString("Nombre: "));
		}
		if(atr.contains("apellido")) {
			p.setApellido(in.getString("Apellido: "));
		}
		if(atr.contains("mail")) {
			p.setEmail(in.getString("Email: "));
		}
		if(atr.contains("psw")) {
			p.setPassword(in.getString("Psw: "));
		}
		if(atr.contains("tel")) {
			p.setTel(in.getString("tel: "));
		}
		if(atr.contains("habilitado")) {
			p.setHabilitado(in.getBool("Esta hablitado? [S/N]: "));
		}
		if(atr.contains("doc")) {
			doc.setTipo(in.getString("Tipo documento: "));
			doc.setNro(in.getString("Numero de documento:"));
			p.setDocumento(doc);
		}
		return p;
	}

	
	public Persona selectRol(Persona p, LinkedList<Rol> roles) {
			int idRolElegido;
			do {
				idRolElegido=in.getIntBetween("Id del rol [Salir-> 0 ]: ", 0, roles.getLast().getId());
				for(Rol r: roles) {
					if(r.getId()==idRolElegido) {
						p.addRol(r);
					} 
				}
			} while (idRolElegido!=0);
		
			
			return p;
	}
	
	public Persona optionalFill(Persona p,String ...atributos) {
		LinkedList<String> atr = new LinkedList<String>();
        for(String a:atributos) { atr.add(a); }
	
		if(atr.contains("nombre")) {
			in.optionGetString("-Nombre: ", (per,s)->per.setNombre(s), p);
		}
		if(atr.contains("apellido")) {
			in.optionGetString("-Apellido: ", (per,s)->per.setApellido(s), p);
		}
		if(atr.contains("mail")) {
			in.optionGetString("-Email: ", (per,s)->per.setEmail(s), p);
		}
		if(atr.contains("psw")) {
			in.optionGetString("-Contraseña: ", (per,s)->per.setPassword(s), p);
		}
		if(atr.contains("tel")) {
			in.optionGetString("-Telefono: ", (per,s)->per.setTel(s), p);
		}
		if(atr.contains("habilitado")) {
			in.optionalGetBool("-Habilitado: ", (per,s)->per.setHabilitado(s), p);
		}
		if(atr.contains("doc")) {
			in.optionGetString("-Tipo Doc: ", (per,s)->per.getDocumento().setTipo(s), p);
			in.optionGetString("-Nro doc", (per, s)->per.getDocumento().setNro(s), p);
		}
		return p;
	}
	
	
}
