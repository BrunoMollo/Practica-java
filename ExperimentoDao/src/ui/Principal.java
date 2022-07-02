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
		for(Persona per: dp.FindAll()){
			System.out.println(per);
		}
		System.out.println("-------------------------");
		for(Persona per: dp.FindAllHabilitados()){
			System.out.println(per);
		}
		
		
		
	}

}
