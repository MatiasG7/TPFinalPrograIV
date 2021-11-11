package mainpackage;

import java.util.Scanner;

public class EjecutoraFinal {
	public static void main(String[] args) {
		// ABRE EL MAIN
		Scanner input = new Scanner(System.in);
		input.useDelimiter(System.getProperty("line.separator"));
		
		byte opcion = 0;
		
		EmpresaSistemas adm = new EmpresaSistemas();
				
		do {
			System.out.println("\n\n PRACTICANDO LA PRACTICA 8.4");
			System.out.println("\t*****************************");
			System.out.println("1)Agregar un empleado");
			System.out.println("2)Agregar un puesto");
			System.out.println("3)Agregar una convocatoria");
			System.out.println("4)Agregar una inscripcion");
			System.out.println("5)Agregar una especializacion");
			opcion = input.nextByte();
			
			switch(opcion){
				case 1:{
					adm.agregarEmpleado();
				}break;
				case 2:{
					adm.agregarPuesto();
				}break;
				case 3:{
					adm.agregarConvocatoria();
				}break;
				case 4:{
					adm.agregarInscripcion();
				}break;
				case 5:{
					adm.agregarEspecializacion();
				}break;
				case 6:{
					adm.informar();
				}break;
			}
		}while(opcion != 0);
	}
}