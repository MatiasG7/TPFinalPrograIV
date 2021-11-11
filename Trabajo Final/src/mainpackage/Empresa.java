package mainpackage;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Empresa {
	private ArrayList<Empleado> empleados;
	private ArrayList<Puesto> puestos;
	private ArrayList<Convocatoria> convocatorias;
	private ArrayList<String> especializaciones;

	public Empresa() {
		this.empleados = new ArrayList<Empleado>();
		this.puestos = new ArrayList<Puesto>();
		this.convocatorias = new ArrayList<Convocatoria>();
		this.especializaciones = new ArrayList<String>();
	}

	public void agregarEmpleado() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter(System.getProperty("line.separator"));

		System.out.print(" --- INGRESO DE NUEVO EMPLEADO ---");
		System.out.print("\nIngrese dni: ");
		int dni = s.nextInt();
		Empleado nuevoEmpleado = verificarDni(dni);

		if (nuevoEmpleado != null) {

			System.out.print("\nYa existe un empleado con ese dni. ");

		} else {

			System.out.print("\nIngrese nombre: ");
			String nombre = s.next();

			System.out.print("\nIngrese codigo de puesto: ");
			int codPuesto = s.nextInt();

			Puesto puesto = verificarPuesto(codPuesto);
			String nombrePuesto = null;
			String areaPuesto = null;

			// Si no encuentra ningun puesto pide mas datos pa crearlo
			if (puesto == null) {
				System.out.print("\nIngrese nombre de puesto: ");
				nombrePuesto = s.next();

				System.out.print("\nIngrese area de puesto: ");
				areaPuesto = s.next();
			}

			System.out.print("\nIngrese fecha de ingreso a la empresa: ");
			Fecha fechaIngresoEmpresa = Fecha.nuevaFecha();

			System.out.print("\nEs un empleado jerarquico?");
			System.out.print("\nSI | NO");
			String tieneguita = s.next();

			// un quilombito aca, si encontramos el puesto pero por ejemplo el puesto es
			// jerarquico pero el flaco pone que el empleado NO es JERARQUICO,
			// osea comun, tenemos una contradiccion y hay que arreglarlo.
			// Lo que me acabo de dar cuenta despues de hacer todo esto es que podes mandar
			// que si lo encuentra le sacamos la opcion de poner
			// que tipo de empleado es y que se joda el usuario. Si encuentra el puesto que
			// pone y es jerarquico PUMBA automaticamente el
			// empleado es jerarquico y que la chupe.

			if (puesto != null) {
				if (Objects.deepEquals(tieneguita, "SI") || Objects.deepEquals(tieneguita, "si")
						|| !Objects.deepEquals(puesto.getClass().getSimpleName(), "PuestoJerarquico")) {

					System.out.print(
							"\nA seleccionado un puesto no jerarquico pero a indicado que el empleado es jerarquico.");
					System.out.print(
							"\nQuiere cambiar el empleado a no jerarquico o quiere cambiar el puesto a jerarquico?");
					System.out.print("\nA - Cambiar empleado | B - Cambiar puesto");
					String queHacemo = s.next();

					if (Objects.deepEquals(queHacemo, "A")) {
						tieneguita = "NO";
					} else {
						// cambiamos el puesto a jerarquico, un quilombo que hare mas adelante lpm
					}
				} else if (Objects.deepEquals(tieneguita, "NO") || Objects.deepEquals(tieneguita, "no")
						|| !Objects.deepEquals(puesto.getClass().getSimpleName(), "PuestoComun")) {

					System.out.print(
							"\nA seleccionado un puesto jerarquico pero a indicado que el empleado es no jerarquico.");
					System.out.print(
							"\nQuiere cambiar el empleado a jerarquico o quiere cambiar el puesto a no jerarquico?");
					System.out.print("\nA - Cambiar empleado | B - Cambiar puesto");
					String queHacemo = s.next();
					if (Objects.deepEquals(queHacemo, "A")) {
						tieneguita = "SI";
					} else {
						// cambiamos el puesto a no jerarquico, un quilombo que hare mas adelante lpm
					}
				}

			}

			if (Objects.deepEquals(tieneguita, "SI") || Objects.deepEquals(tieneguita, "si")) {

				System.out.print("\nIngrese fecha de ingreso al puesto: ");
				Fecha fechaIngresoPuesto = Fecha.nuevaFecha();

				if (puesto == null) {
					puesto = new PuestoJerarquico(codPuesto, nombrePuesto, areaPuesto);
				}

				nuevoEmpleado = new EmpleadoJerarquico(dni, nombre, puesto, fechaIngresoEmpresa);

			} else {

				if (puesto == null) {
					puesto = new PuestoComun(codPuesto, nombrePuesto, areaPuesto);

				}
				nuevoEmpleado = new EmpleadoComun(dni, nombre, puesto, fechaIngresoEmpresa);
			}

			puestos.add(puesto);
			empleados.add(nuevoEmpleado);

			// Hay que hacer la verificacion de esto y agregarlo a el array
			// ++++++++++++++++++++++++++++++++++++++++++++++++++++

			do {

				System.out.print("\nIngrese especializacion: ");
				String especializacion = s.next();

				System.out.print("\nIngrese años de experiencia: ");
				int añosEspe = s.nextInt();

				nuevoEmpleado.agregarExperiencia(especializacion, añosEspe);

				System.out.print("\nAgregar mas experiencia a este empleado?");
				System.out.print("\nSI | NO");

			} while (Objects.deepEquals(tieneguita, "SI") || Objects.deepEquals(tieneguita, "si"));

		}

	}

	public Empleado verificarDni(int dni) {
		int i = 0;

		while (i < empleados.size() && empleados.get(i).getDni() != dni)
			;
		i++;

		if (i < empleados.size())
			return empleados.get(i);
		else
			return null;
	}

	public Puesto verificarPuesto(int cod) {
		int i = 0;

		while (i < puestos.size() && puestos.get(i).getCodigo() != cod)
			;
		i++;

		if (i < puestos.size())
			return puestos.get(i);
		else
			return null;
	}

}
