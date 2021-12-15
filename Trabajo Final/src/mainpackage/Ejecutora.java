package mainpackage;

import java.util.Scanner;

public class Ejecutora {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Empresa e = new Empresa();

		s.useDelimiter(System.getProperty("line.separator"));

		int op;

		// Bloque de prueba
		e.PRUEBAS();
		//

		do {
			System.out.println("TP FINAL");
			System.out.println("1- Agregar un empleado.");
			System.out.println("2- Agregar un puesto.");
			System.out.println("3- Agregar una convocatoria.");
			System.out.println("4- Agregar una inscripcion.");
			System.out.println("5- Agregar una especializacion.");
			System.out.println("6- Agregar experiencia a un empleado.");
			System.out.println("7- Cerrar una convocatoria.");
			System.out.println("8- Informar convocatorias abiertas.");
			System.out.println("9- Informar puestos.");
			System.out.println("10- Informar empleados.");
			System.out.println("11- Informar inscripciones.");
			System.out.println("12- Informar especializaciones.");
			System.out.println("13- Informar empleados jerarquicos.");
			System.out.println("14- Informar inscripciones por un empleado.");
			System.out.println("15- Informar empleado con mas inscripciones.");
			System.out.println("16- Informar empleados que cumplen los requisitos de la convocatoria.");

			op = s.nextInt();
			switch (op) {
			case 1:
				e.agregarEmpleado();
				break;
			case 2:
				e.agregarPuesto();
				break;
			case 3:
				e.agregarConvocatoria();
				break;
			case 4:
				e.agregarInscripcion();
				break;
			case 5:
				e.agregarEspecializacion();
				break;
			case 6:
				e.agregarExperienciaEmpleado();
			case 7:
				e.cerrarConvocatoria();
				break;
			case 8:
				e.informarConvocatoriasAbiertas();
				break;
			case 9:
				e.informarPuestos();
				break;
			case 10:
				e.informarEmpleados();
				break;
			case 11:
				e.informarInscripciones();
				break;
			case 12:
				e.informarEspecializaciones();
				break;
			case 13:
				e.informarEmpleadosJerarquicos();
				break;
			case 14:
				e.informarInscripcionesEmpleado();
				break;
			case 15:
				e.informarEmpleadoMasInscripciones();
				break;
			case 16:
				e.informarEmpleadosAprobados();
				break;
			}
		} while (op != 0);

		s.close();
	}
}