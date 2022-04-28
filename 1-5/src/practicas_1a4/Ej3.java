package practicas_1a4;

import java.util.ArrayList;
import java.util.Scanner;

public class Ej3 {
	//Leer 10 palabras, luego leer una nueva palabra e indicar si la misma ya había sido ingresada en las 10 primeras.
	static public void DetectarSiUnaPalabraEstaEnArray(int cant) {
		Scanner sc= new Scanner(System.in);
		ArrayList<String> arr=new ArrayList<String>();
		
		for(int i=0; i<cant; i++) {
			System.out.print("Ingresar palabra "+i+" del array: ");
			arr.add(sc.nextLine());
		}
		
		System.out.print("Ingresar palabra: ");
		String palabra=sc.nextLine();
		
		if(arr.contains(palabra)) {
			System.out.println("La palabra "+palabra+" SI esta en el arreglo "+arr);
		}
		else {
			System.out.println("La palabra "+palabra+" NO esta en el arreglo "+arr);
		}
		
		
	
	}
}
