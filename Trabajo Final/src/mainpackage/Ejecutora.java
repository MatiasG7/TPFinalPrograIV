package mainpackage;

import java.util.Scanner;

public class Ejecutora {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Empresa e = new Empresa();
		
		s.useDelimiter(System.getProperty("line.separator"));
				
		int op;
		
		do {
			System.out.println("TP FINAL");
			System.out.println("1- Agregar un empleado.");
			System.out.println("2- Agregar un puesto.");
			System.out.println("3- Agregar una convocatoria.");
			System.out.println("4- Agregar una inscripcion.");
			System.out.println("5- Agregar una especializacion.");
			System.out.println("6- Informar algo de algo.");
			
			op = s.nextInt();
			switch(op){
				case 1:{
					e.agregarEmpleado();
				}break;
				case 2:{
					//e.agregarPuesto();
				}break;
				case 3:{
					//e.agregarConvocatoria();
				}break;
				case 4:{
					//e.agregarInscripcion();
				}break;
				case 5:{
					//e.agregarEspecializacion();
				}break;
				case 6:{
					//e.informar();
				}break;
			}
		}while(op != 0);
		
		s.close();
	}
}