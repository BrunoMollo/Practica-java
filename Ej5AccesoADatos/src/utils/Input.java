package utils;


import java.util.Scanner;

public class Input {
	public static String getString(String msj, Scanner sc) {
		System.out.print(msj);
		return sc.nextLine();
	}
	
	public static Boolean getBool(String msj, Scanner sc) {
		System.out.print(msj);
		String resp = sc.nextLine();
		if(resp.equalsIgnoreCase("S")) return true;
		else return false;
	}
	
	public static int getIntBetween(String msj, Scanner sc, int min, int max) {
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
