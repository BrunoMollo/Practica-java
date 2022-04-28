package practicas_1a4;

import java.util.ArrayList;
import java.util.Scanner;

public class Ej2 {
	//Leer 10 palabras y mostrarlas en orden inverso al que fueron ingresadas.
	
	static public void InvertirArrayPalabras(int cant) {
		Scanner sc= new Scanner(System.in);
		ArrayList<String> arr=new ArrayList<String>();
		
		for(int i=0; i<cant; i++) {
			System.out.print("Ingresar palabra: ");
			arr.add(sc.nextLine());
		}
		
		for(int i=cant-1; i>=0; i--) {
			System.out.println(arr.get(i));
		}
	}
	
	//de aca ára abajo entendi mal la consigan, pero queda igual
	static public void invertirPalabrasIngresadas(int cantidad) {
		Scanner sc= new Scanner(System.in);
		for(int i=0; i<cantidad; i++) {
			System.out.print("Ingresar palabra: ");
			String input=sc.nextLine();
			System.out.println(inverse1(input));
			
		}
	}
	
	static private String inverse1(String input) {
		String newString="";
		for(char letra: input.toCharArray()) {
			newString=letra+newString;
		}
		return newString;
	}
	
	static private String inverse2(String input) {
		String newString="";
		for(int i=input.length()-1;i>=0; i--) {
			newString+=input.charAt(i);
		}
		return newString;
	}
	
}
