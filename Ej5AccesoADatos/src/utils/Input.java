package utils;


import java.io.InputStream;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.mysql.cj.x.protobuf.MysqlxExpr.FunctionCall;

import entities.Documento;
import entities.Persona;

public class Input {
	private Scanner sc;
	
	public Input(InputStream source) {
		this.sc = new Scanner(source);
	}
	
	public void close() {
		sc.close();
	}

	public String getString(String msj) {
		System.out.print(msj);
		return sc.nextLine();
	}
	
	public void optionGetString(String msj, BiConsumer<Persona, String> setter, Persona p) {
		String resp=this.getString(msj);
		if(!resp.equals("")) {
			setter.accept(p,resp);
		}
	}
	
	
	public void optionalGetBool(String msj, BiConsumer<Persona, Boolean> setter, Persona p) {
		String resp=getString(msj);
		if(!resp.equals("")) {
			setter.accept(p,strToBool(resp));
		}
	}
	
	public Boolean getBool(String msj) {
		System.out.print(msj);
		String resp = sc.nextLine();
		return strToBool(resp);
		
	}
	
	public static Boolean strToBool(String s) {
		if(s.equalsIgnoreCase("S")) return true;
		else return false;
	}
	
	public int getIntBetween(String msj, int min, int max) {
		int respInt=min-1;
		while(respInt<min || respInt>max) {
			System.out.print(msj);
			String resp = sc.nextLine();
			
			try { respInt=Integer.parseInt(resp); }
			catch(Exception e){respInt=min-1;}
		}
		return respInt;
	}
}
