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
			// ARREGLAR NRS
			System.out.println("TP FINAL");
			System.out.println("1- Agregar un empleado.");
			System.out.println("2- Agregar un puesto.");
			System.out.println("3- Agregar una convocatoria.");
			System.out.println("4- Agregar una inscripcion.");
			System.out.println("5- Agregar una especializacion.");
			System.out.println("6- Agregar experiencia a un empleado.");
			System.out.println("8- Cerrar una convocatoria.");
			System.out.println("9- Informar convocatorias.");
			System.out.println("10- Informar puestos.");
			System.out.println("11- Informar empleados.");
			System.out.println("12- Informar inscripciones.");
			System.out.println("13- Informar especializaciones.");
			System.out.println("14- Informar empleados jerarquicos.");
			System.out.println("15- Informar puestos postulados por un empleado.");
			System.out.println("16- Informar empleado con mas inscripciones.");
			System.out.println("17- Informar postulantes aprobados.");
			// ARREGLAR NRS

			op = s.nextInt();
			switch (op) {
			case 1: {
				e.agregarEmpleado();
			}
				break;
			case 2: {
				e.agregarPuesto();
			}
				break;
			case 3: {
				e.agregarConvocatoria();
			}
				break;
			case 4: {
				e.agregarInscripcion();
			}
				break;
			case 5: {
				e.agregarEspecializacion();
			}
				break;
			case 6: {
				e.agregarExperienciaEmpleado();
			}
				break;
			case 8: {
				e.cerrarConvocatoria();
			}
			case 9: {
				// e.informarConvocatorias();
			}
			case 10: {
				// e.informar();
			}
			case 11: {
				// e.informar();
			}
			case 12: {
				// e.informar();
			}
			}
		} while (op != 0);

		s.close();
	}
}