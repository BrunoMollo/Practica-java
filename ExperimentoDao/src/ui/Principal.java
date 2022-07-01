package ui;

import data.DataPersona;
import data.DataRol;
import entities.Persona;
import entities.Rol;

public class Principal {

	public static void main(String[] args) {
		DataPersona dp= new DataPersona();
		DataRol dr= new DataRol();
	
		
//		for(Persona p: dp.FindAll()) {
//			System.out.println(p);
//		}
		
		Persona p=new Persona();
		p.setId(7);
		p.setEmail("dia.molodmeqwo1gmial.com");
		System.out.println(dp.getOneByEmail(p));
		
		
		
		
	}

}
