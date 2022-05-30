package ui;



import java.util.LinkedList;
import java.util.Scanner;

import entities.*;
import logic.Login;
import utils.Input;

public class Menu {
	Scanner s=null;
	Login ctrlLogin = new Login();

	public void start() {
		s = new Scanner(System.in);
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
		
		s.close();
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
			
			break;
		case "delete":
			
			break;
		default:
			break;
		}
	}

	private void createPersona() {
		Persona p=new Persona();
		Documento doc=new Documento();
		
		p.setNombre(Input.getString("Nombre: ", s));
		p.setApellido(Input.getString("Apellido", s));
		p.setEmail(Input.getString("Email: ", s));
		p.setPassword(Input.getString("Psw: ", s));
		p.setTel(Input.getString("tel: ", s));
		p.setHabilitado(Input.getBool("Esta hablitado? [S/N]: ", s));
		
		doc.setTipo(Input.getString("Tipo documento: ", s));
		doc.setNro(Input.getString("Numero de documento:", s));
		p.setDocumento(doc);
		
		
		LinkedList<Rol> roles=ctrlLogin.getAllRoles();
		for(Rol r : roles) {
			System.out.println(r.getId()+ "-"+ r.getDescripcion());
		}
		
		int idRolElegido=Input.getIntBetween("Id del rol: ", s, roles.getFirst().getId(), roles.getLast().getId());
		for(Rol r: roles) {
			if(r.getId()==idRolElegido) {
				p.addRol(r);
			} 
		}
		
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
		System.out.print("comando: ");
		return s.nextLine();
	}
	
	public Persona login() {
		Persona p=new Persona();
		
		System.out.print("Email: ");
		p.setEmail(s.nextLine());	
		
		System.out.print("password: ");
		p.setPassword(s.nextLine());
		
		p=ctrlLogin.validate(p);
		return p;
	}
	
	private Persona find() {
		System.out.println();
		Persona p=new Persona();
		Documento d=new Documento();
		p.setDocumento(d);
		System.out.print("Tipo doc: ");
		d.setTipo(s.nextLine());

		System.out.print("Nro doc: ");
		d.setNro(s.nextLine());
		
		return ctrlLogin.getByDocumento(p);
	}
	
	private LinkedList<Persona> findAllBySurname(){
		Persona p=new Persona();
		
		System.out.println("Apellido: ");
		p.setApellido(s.nextLine());
		
		return ctrlLogin.getAllBySurname(p);
	}

}
