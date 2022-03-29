package practicas_1a4;

import java.util.Scanner;

public class Ej4 {
//Leer un entero y luego una lista de 20 enteros. Guardar los mayores al número inicial y mostrarlos al final. Usar arrays, no otras colecciones.
	
	static Scanner sc;
	
	
	private static int leerEntero(String msj) {	
		System.out.print(msj);
		
		try {
			return sc.nextInt();			
		}
		catch(Exception e){
			System.out.println("No es un entero");
			System.exit(-1);
			return 0;
		}
		
	}
	
	private static int sizeArrFinal(int[] arr, int filtro) {
		int finalSize=0;
		for(int x:arr) {
			if(x>filtro) { finalSize++; }
		}	
		return finalSize;
	}
	
	private static void moverMayores(int[] arrInit, int[] arrFinal, int filtro) {
		int i=0;
		for(int x:arrInit) {
			if(x>filtro) {
				arrFinal[i]=x;
				i++;
			}
		}
	}
	
	private static void mostrar(int[] arr) {
		for(int x:arr) {
			System.out.println(x);
		}
	}
	
	public static void main(int size) {
		sc=new Scanner(System.in);
		
		int filtro=leerEntero("Ingresar numero filtro: ");
		
		int arrInput[]= new int[size];
		for(int i=0; i<size; i++) {
			arrInput[i]=leerEntero(i+"-Ingresar numero :");
		}
		
		int arrFinal[]=new int[sizeArrFinal(arrInput, filtro)];
		
		moverMayores(arrInput, arrFinal, filtro);
		
		mostrar(arrFinal);
		
		
		
		sc.close();
	}

}
