package practicas_1a4;

public class Ej1 {
	//Mostrar por consola los 10 primeros números enteros y los 10 primeros números impares
	static public void listarEnteros(int cantidad) {
		for(int i=0; i<cantidad; i++) {
			System.out.print(i+" ");
		}
	}
	
	static public void listarImpares(int cantidad) {
		for(int i=0; i<cantidad*2; i++) {
			if(i%2==1) {
				System.out.print(i+" ");				
			}
		}
	}
}
