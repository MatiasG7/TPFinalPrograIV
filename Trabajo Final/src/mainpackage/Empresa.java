package mainpackage;

import java.util.ArrayList;
import java.util.Hashtable;
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
				System.out.println("\nIngrese fecha de ingreso a la empresa: ");
				Fecha fechaIngreso = Fecha.nuevaFecha();

				System.out.println("\nEs un empleado jerarquico?");
				System.out.println("\n1- SI");
				System.out.println("\n2- NO");
				int op = s.nextInt();

				if (puesto.isCompatible(op)) {
					if (op == 1) {
						nuevoEmpleado = new EmpleadoJerarquico(dni, nombre, fechaNac, cuil, puesto, fechaIngreso);
					} else {
						nuevoEmpleado = new EmpleadoComun(dni, nombre, fechaNac, cuil, puesto, fechaIngreso);
					}
				}

				nuevoEmpleado.setExperiencia(this.crearExperiencia());
				empleados.add(nuevoEmpleado);
			} else {
				System.out.println("El puesto ingresado no existe.");
			}
		}

		s.close();
	}

	private Hashtable<String, Integer> crearExperiencia() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter(System.getProperty("line.separator"));

		Hashtable<String, Integer> ht = new Hashtable<String, Integer>();

		System.out.println("\nAgregar especializaciones");
		System.out.println("\n1- Si");
		System.out.println("\n2- No");
		s.useDelimiter(System.getProperty("line.separator"));
		System.out.println("\n0- En cualquier momento para salir.");
		int op = s.nextInt();

		if (op == 1) {
			do {
				System.out.println("\n++Listado de especializaciones++");
				for (int i = 1; i <= especializaciones.size(); i++) {
					System.out.println("\n" + i++ + " - " + especializaciones.get(i));
				}

				System.out.println("\nElija especializaci�n a agregar: ");
				int esp = s.nextInt();

				System.out.println("\nIngrese a�os de experiencia para la especializaci�n: ");
				int anios = s.nextInt();

				ht.put(especializaciones.get(esp - 1), anios);

				System.out.println("\nAgregar mas especializaciones?");
				System.out.println("\n1- SI");
				System.out.println("\n2- NO");
				op = s.nextInt();
			} while (op != 0);
		}

		s.close();

		return ht;
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

	public void agregarPuesto() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter(System.getProperty("line.separator"));

		System.out.print(" --- INGRESO DE NUEVO PUESTO ---");
		System.out.print("\nIngrese codigo de puesto: ");
		int cod = s.nextInt();

		Puesto pu = verificarPuesto(cod);

		if (pu != null) {
			System.out.print("El codigo ingresado ya esta relacionado a un puesto");
		} else {
			System.out.print("\nIngrese nombre de puesto: ");
			String nombre = s.next();

			System.out.print("\nIngrese area de puesto: ");
			String area = s.next();

			System.out.println("\nEs un puesto jerarquico?");
			System.out.println("\n1- SI");
			System.out.println("\n2- NO");
			int op = s.nextInt();

			if (op == 1) {
				pu = new PuestoJerarquico(cod, nombre, area);
			} else {
				pu = new PuestoComun(cod, nombre, area);
			}
			puestos.add(pu);
		}
	}

	public void agregarConvocatoria() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter(System.getProperty("line.separator"));

		System.out.print(" --- INGRESO DE NUEVA CONVOCATORIA ---");
		System.out.print("\nIngrese codigo de convocatoria: ");
		int cod = s.nextInt();

		Convocatoria con = verificarConvocatoria(cod);

		if (con == null) {

			System.out.print("El codigo ingresado ya esta relacionado a una convocatoria");

		} else {
			System.out.print("\nIngrese codigo de puesto para la convocatoria: ");
			int codPuesto = s.nextInt();

			Puesto pu = verificarPuesto(cod);

			if (pu == null) {
				System.out.print("El codigo ingresado no esta relacionado a un puesto");
			} else {
				
				
				con = new Convocatoria(codPuesto, pu);
				con.setExperienciaReq(this.crearExperiencia());
				
				System.out.print("\nCONVOCATORIA CREADA");
				System.out.print("\nPUESTO");
				pu.mostrarse();
				System.out.print("\nCODIGO" + cod);
				
				convocatorias.add(con);
			}
		}
	}

	public Convocatoria verificarConvocatoria(int cod) {
		int i = 0;
		while (i < convocatorias.size() && convocatorias.get(i).getCodigo() != cod) {
			i++;
		}
		if (i < convocatorias.size())
			return convocatorias.get(i);
		else
			return null;
	}

}
