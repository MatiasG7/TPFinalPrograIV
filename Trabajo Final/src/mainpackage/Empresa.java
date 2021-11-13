package mainpackage;

import java.util.ArrayList;
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

			System.out.print("\nIngrese fecha de nacimiento: ");
			Fecha fechaNac = Fecha.nuevaFecha();

			System.out.print("\nIngrese cuil: ");
			String cuil = s.next();

			System.out.print("\nIngrese codigo de puesto: ");
			int codPuesto = s.nextInt();

			Puesto puesto = verificarPuesto(codPuesto);
			if (puesto != null) {
				System.out.println("Ingrese fecha de ingreso a la empresa: ");
				Fecha fechaIngreso = Fecha.nuevaFecha();

				System.out.println("Es un empleado jerarquico?");
				System.out.println("1- SI");
				System.out.println("2- NO");
				int op = s.nextInt();

				if (puesto.isCompatible(op)) {
					if (op == 1) {
						nuevoEmpleado = new EmpleadoJerarquico(dni, nombre, fechaNac, cuil, puesto, fechaIngreso);
					} else {
						nuevoEmpleado = new EmpleadoComun(dni, nombre, fechaNac, cuil, puesto, fechaIngreso);
					}
				}

				empleados.add(nuevoEmpleado);

				// TO DO:
				// Crear un metodo privado que arme un HashTable para poder reutilizarlo aca y
				// en crear convocatoria.
				// do {
				// Listar la especializacion y que elija con un numero cual quiere sumarle.
				// } while ();
			} else {
				System.out.println("El puesto ingresado no existe.");
			}
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
