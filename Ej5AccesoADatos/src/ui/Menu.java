package ui;



import java.util.LinkedList;
import java.util.Scanner;

import entities.*;
import logic.Login;
import utils.Input;

public class Menu {
	Input in=null;
	Login ctrlLogin = new Login();
	FormPersona form;

	public void start() {
		System.out.println();
		in=new Input(System.in);
		form= new FormPersona(in);
//		Persona p=login();
//		
//		System.out.println("Bienvenido "+p.getNombre()+" "+p.getApellido());
//		System.out.println();
		
		
		String command;
		do {
			command=getCommand();
			executeCommand(command);
			System.out.println();
			
		}while(!command.equalsIgnoreCase("exit"));
		
		in.close();
	}

	private void executeCommand(String command) {
		switch (command) {
		case "list":
			System.out.println(ctrlLogin.getAll());
			break;
		case "find":
			System.out.println(find());
			break;
		case "search":
			System.out.println(findAllBySurname());
			break;
		case "new":
			createPersona();
			break;
		case "edit":
			 updatePersona();
			break;
		case "delete":
			deletePersona();
			break;
		default:
			break;
		}
	}

	
	private void deletePersona() {
		Persona p=form.fill("doc");
		p=ctrlLogin.getByDocumento(p);
		
		if(p==null) {
			System.out.println("Error: Persona no existe!");
			return;
		}
		
		System.out.println(p);
		
		if(in.getBool("Seguro que desea borrarlo? [S/N]")==true) {
			ctrlLogin.delete(p);
			System.out.println("Se borro el registro");
		}
		else {
			System.out.println("No se borro");
		}
		
	}

	private void updatePersona() {
		Persona p=form.fill("doc");
		p=ctrlLogin.getByDocumento(p);
		
		if(p==null) {
			System.out.println("Error: Persona no existe!");
			return;
		}
		
		System.out.println(p);
		form.optionalFill(p, "nombre", "apellido", "mail", "psw", "tel", "habilitado", "doc");
		
		System.out.println(p);
	}

	private void createPersona() {
		
		Persona p=form.fill("nombre", "apellido", "mail", "psw", "tel", "habilitado", "doc");
	
		LinkedList<Rol> roles=ctrlLogin.getAllRoles();
		for(Rol r : roles) {
			System.out.println(r.getId()+ "-"+ r.getDescripcion());
		}
		form.selectRol(p, roles);
		
		ctrlLogin.savePersona(p);
	}

	private String getCommand() {
		System.out.println("Ingrese el comando segun la opcion que desee realizar");
		System.out.println("list\t\tlistar todos");
		System.out.println("find\t\tbuscar por tipo y nro de documento"); //solo debe devolver 1
		System.out.println("search\t\tlistar por apellido"); //puede devolver varios
		System.out.println("new\t\tcrea una nueva persona y asigna un rol existente");
		System.out.println("edit\t\tbusca por tipo y nro de documento y actualiza todos los datos");
		System.out.println("delete\t\tborra por tipo y nro de documento");
		System.out.println();
		return in.getString("comando: ");
	}
	
	public Persona login() {
		Persona p=form.fill("mail", "psw");
		p=ctrlLogin.validate(p);
		return p;
	}
	
	private Persona find() {
		Persona p=form.fill("doc");
		return ctrlLogin.getByDocumento(p);
	}
	
	private LinkedList<Persona> findAllBySurname(){
		Persona p=form.fill("apellido");
		return ctrlLogin.getAllBySurname(p);
	}

}
