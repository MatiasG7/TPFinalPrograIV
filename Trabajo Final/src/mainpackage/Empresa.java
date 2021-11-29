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

		System.out.println(" --- INGRESO DE NUEVO EMPLEADO ---");
		System.out.println("Ingrese dni: ");
		int dni = s.nextInt();
		Empleado nuevoEmpleado = verificarDni(dni);

		if (nuevoEmpleado != null) {
			System.out.println("Ya existe un empleado con ese dni. ");
		} else {

			System.out.println("Ingrese nombre: ");
			String nombre = s.next();

			System.out.println("Ingrese fecha de nacimiento: ");
			Fecha fechaNac = Fecha.nuevaFecha();

			System.out.println("Ingrese cuil: ");
			String cuil = s.next();

			System.out.println("Ingrese codigo de puesto: ");
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
						nuevoEmpleado = new EmpleadoJerarquico(dni, nombre, fechaNac, cuil, puesto, fechaIngreso,
								this.crearExperiencia());
					} else {
						nuevoEmpleado = new EmpleadoComun(dni, nombre, fechaNac, cuil, puesto, fechaIngreso,
								this.crearExperiencia());
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

		System.out.println("Agregar especializaciones");
		System.out.println("1- Si");
		System.out.println("2- No");
		s.useDelimiter(System.getProperty("line.separator"));
		System.out.println("0- En cualquier momento para salir.");
		int op = s.nextInt();

		if (op == 1) {
			do {
				System.out.println("++Listado de especializaciones++");
				for (int i = 1; i <= especializaciones.size(); i++) {
					System.out.println("" + i++ + " - " + especializaciones.get(i));
				}

				System.out.println("Elija especialización a agregar: ");
				int esp = s.nextInt();

				System.out.println("Ingrese años de experiencia para la especialización: ");
				int anios = s.nextInt();

				ht.put(especializaciones.get(esp - 1), anios);

				System.out.println("Agregar mas especializaciones?");
				System.out.println("1- SI");
				System.out.println("2- NO");
				System.out.println("0- En cualquier momento para salir.");
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

		System.out.println(" --- INGRESO DE NUEVO PUESTO ---");
		System.out.println("Ingrese codigo de puesto: ");
		int cod = s.nextInt();

		Puesto pu = verificarPuesto(cod);

		if (pu != null) {
			System.out.println("El codigo ingresado ya esta relacionado a un puesto");
		} else {
			System.out.println("Ingrese nombre de puesto: ");
			String nombre = s.next();

			System.out.println("Ingrese area de puesto: ");
			String area = s.next();

			System.out.println("Es un puesto jerarquico?");
			System.out.println("1- SI");
			System.out.println("2- NO");
			int op = s.nextInt();

			if (op == 1) {
				pu = new PuestoJerarquico(cod, nombre, area);
			} else {
				pu = new PuestoComun(cod, nombre, area);
			}
			puestos.add(pu);
		}

		s.close();
	}

	public void agregarConvocatoria() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter(System.getProperty("line.separator"));

		System.out.println(" --- INGRESO DE NUEVA CONVOCATORIA ---");
		System.out.println("Ingrese codigo de convocatoria: ");
		int cod = s.nextInt();

		Convocatoria con = verificarConvocatoria(cod);

		if (con == null) {

			System.out.println("El codigo ingresado ya esta relacionado a una convocatoria");

		} else {
			System.out.println("Ingrese codigo de puesto para la convocatoria: ");
			int codPuesto = s.nextInt();

			Puesto pu = verificarPuesto(cod);

			if (pu == null) {
				System.out.println("El codigo ingresado no esta relacionado a un puesto");
			} else {
				con = new Convocatoria(codPuesto, pu, this.crearExperiencia());

				con.mostrarse();

				convocatorias.add(con);
			}
		}

		s.close();
	}

	public void agregarEspecializacion() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter(System.getProperty("line.separator"));

		System.out.println(" --- INGRESO DE ESPECIALIZACIONES ---");

		System.out.println("Agregar especializacion?");
		System.out.println("1- Si");
		System.out.println("2- No");
		int op = s.nextInt();
		while (op != 2) {
			System.out.println("Ingrese nombre de especializacion: ");
			String esp = s.next();

			String espe = verificarEspecializacion(esp);

			if (espe == null) {
				especializaciones.add(esp);
			} else {
				System.out.println("Ya existe esa especializacion.");
			}

			System.out.println("Agregar otra especializacion?");
			System.out.println("1- Si");
			System.out.println("2- No");
			op = s.nextInt();
		}

		s.close();
	}

	public String verificarEspecializacion(String e) {
		int i = 0;
		while (i < especializaciones.size() && e.compareToIgnoreCase(especializaciones.get(i)) != 0) {
			i++;
		}
		if (i < especializaciones.size())
			return especializaciones.get(i);
		else
			return null;
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

	public void agregarInscripcion() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter(System.getProperty("line.separator"));

		System.out.println(" --- INGRESO DE NUEVA INSCRIPCION ---");

		System.out.println("Ingrese dni empleado a inscribirse: ");
		int dni = s.nextInt();

		Empleado empleado = verificarDni(dni);

		if (empleado == null) {
			System.out.println("Empleado no existe.");
		} else {
			System.out.println("EMPLEADO");

			empleado.mostrarse();

			System.out.println("Ingrese codigo de convocatoria: ");
			int codConvocatoria = s.nextInt();

			Convocatoria convocatoria = verificarConvocatoria(codConvocatoria);

			if (convocatoria == null) {
				System.out.println("No existe convocatoria con ese codigo.");
			} else {
				convocatoria.mostrarse();

				Inscripcion inscri = convocatoria.verificarInscripcion(empleado);

				if (inscri == null) {
					inscri = new Inscripcion(codConvocatoria, empleado, convocatoria);
					convocatoria.addInscripcion(inscri);
				} else {
					System.out.println("Este empleado ya esta inscripto en esta convocatoria.");
				}

			}

		}

		s.close();
	}

}
