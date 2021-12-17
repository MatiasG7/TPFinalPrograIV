package mainpackage;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ListIterator;
import java.util.Scanner;

public class Empresa {
	private ArrayList<Empleado> empleados;
	private ArrayList<Puesto> puestos;
	private ArrayList<Convocatoria> convocatorias;
	private ArrayList<String> especializaciones;
	private ArrayList<Inscripcion> inscripciones;

	public Empresa() {
		this.empleados = new ArrayList<Empleado>();
		this.puestos = new ArrayList<Puesto>();
		this.convocatorias = new ArrayList<Convocatoria>();
		this.especializaciones = new ArrayList<String>();
		this.inscripciones = new ArrayList<Inscripcion>();
	}

	// CU 1
	public void agregarEmpleado() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter(System.getProperty("line.separator"));

		System.out.println(" --- INGRESO DE NUEVO EMPLEADO ---");
		System.out.println("Ingrese dni: ");
		String dni = s.next();
		Empleado nuevoEmpleado = buscarEmpleado(dni);

		if (nuevoEmpleado != null) {
			System.out.println("Ya existe un empleado con ese dni. ");
		} else {

			System.out.println("Ingrese nombre: ");
			String nombre = s.next();

			System.out.println("Ingreso de fecha de nacimiento");
			Fecha fechaNac = Fecha.nuevaFecha();

			System.out.println("Ingrese cuil: ");
			String cuil = s.next();

			System.out.println("Ingrese codigo de puesto: ");
			int codPuesto = s.nextInt();

			Puesto puesto = buscarPuesto(codPuesto);
			if (puesto != null) {
				System.out.println("Ingreso de fecha de ingreso a la empresa");
				Fecha fechaIngreso = Fecha.nuevaFecha();

				System.out.println("Es un empleado jerarquico?");
				System.out.println("1- SI");
				System.out.println("2- NO");
				int op = s.nextInt();

				if (puesto.isCompatible(op)) {
					if (op == 1) {
						System.out.println("Ingrese fecha de ingreso en puesto");
						Fecha fechaIngresoPuesto = Fecha.nuevaFecha();

						nuevoEmpleado = new EmpleadoJerarquico(dni, nombre, fechaNac, cuil, puesto, fechaIngreso,
								fechaIngresoPuesto, this.crearExperiencia());
					} else {
						nuevoEmpleado = new EmpleadoComun(dni, nombre, fechaNac, cuil, puesto, fechaIngreso,
								this.crearExperiencia());
					}
					empleados.add(nuevoEmpleado);
				} else {
					System.out.println(
							"Empleado no pudo ser creado por que el puesto no es compatible con el tipo de empleado.");
				}

			} else {
				System.out.println("El puesto ingresado no existe.");
			}
		}
	}

	// CU 2
	public void agregarPuesto() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter(System.getProperty("line.separator"));

		System.out.println(" --- INGRESO DE NUEVO PUESTO ---");
		System.out.println("Ingrese codigo de puesto: ");
		int cod = s.nextInt();

		Puesto pu = buscarPuesto(cod);

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

	}

	// CU 3
	public void agregarConvocatoria() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter(System.getProperty("line.separator"));

		System.out.println(" --- INGRESO DE NUEVA CONVOCATORIA ---");
		System.out.println("Ingrese codigo de convocatoria: ");
		int cod = s.nextInt();

		Convocatoria con = buscarConvocatoria(cod);

		if (con == null) {
			System.out.println("El codigo ingresado ya esta relacionado a una convocatoria existente.");
		} else {
			System.out.println("Ingrese codigo de puesto para la convocatoria: ");
			int codPuesto = s.nextInt();

			Puesto pu = buscarPuesto(cod);

			if (pu == null) {
				System.out.println("El codigo ingresado no esta relacionado a un puesto.");
			} else {
				con = new Convocatoria(codPuesto, pu, this.crearExperiencia());

				con.mostrarse();

				convocatorias.add(con);
			}
		}

	}

	// CU 4
	public void agregarInscripcion() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter(System.getProperty("line.separator"));

		System.out.println(" --- INGRESO DE NUEVA INSCRIPCION ---");

		System.out.println("Ingrese dni empleado a inscribirse: ");
		String dni = s.next();

		Empleado empleado = buscarEmpleado(dni);
		if (empleado == null) {
			System.out.println("Empleado no existe.");
		} else {
			System.out.println("EMPLEADO");

			empleado.mostrarse();

			System.out.println("Ingrese codigo de convocatoria: ");
			int codConvocatoria = s.nextInt();

			Convocatoria convocatoria = buscarConvocatoria(codConvocatoria);

			if (convocatoria == null) {
				System.out.println("No existe convocatoria con ese codigo.");
			} else {
				convocatoria.mostrarse();

				Inscripcion ins = convocatoria.verificarInscripcion(empleado.getDni());

				if (ins == null) {

					System.out.println("Ingrese codigo de inscripcion: ");
					int codInscricion = s.nextInt();

					ins = buscarInscripcion(codInscricion);

					if (ins == null) {
						ins = new Inscripcion(codConvocatoria, empleado, convocatoria);
						convocatoria.addInscripcion(ins);
						inscripciones.add(ins);
						empleado.agregarInscripcion(ins);
					} else {
						System.out.println("Este codigo ya existe en una inscripcion.");
					}

				} else {
					System.out.println("Este empleado ya esta inscripto en esta convocatoria.");
				}
			}
		}

	}

	// CU 5
	public void agregarEspecializacion() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter(System.getProperty("line.separator"));

		System.out.println(" --- INGRESO DE ESPECIALIZACIONES ---");
		int op = 0;

		do {
			System.out.println("Ingrese nombre de especializacion: ");
			String esp = s.next();

			String espe = buscarEspecializacion(esp);

			if (espe == null) {
				especializaciones.add(esp);
			} else {
				System.out.println("Ya existe esa especializacion.");
			}

			System.out.println("Agregar otra especializacion?");
			System.out.println("1- Si");
			System.out.println("2- No");
			op = s.nextInt();
		} while (op != 2);

	}

	// CU 6
	public void agregarExperienciaEmpleado() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter(System.getProperty("line.separator"));

		System.out.println(" --- AGREGAR EXPERIENCIA A EMPLEADO ---");

		System.out.println("Ingrese dni del empleado: ");
		String dni = s.next();

		Empleado e = buscarEmpleado(dni);
		if (e == null) {
			System.out.println("El empleado no existe.");
		} else {
			e.mostrarExperiencia();

			this.informarEspecializaciones();
			System.out.println("0- Para salir.");

			int op = s.nextInt();

			do {
				System.out.println("Elija especializacion a agregar: ");
				int esp = s.nextInt();

				System.out.println("Ingrese anios de experiencia para la especializacion: ");
				int anios = s.nextInt();

				String especializacion = especializaciones.get(esp - 1);

				e.agregarExperiencia(especializacion, anios);

				System.out.println("Desea agregar mas experiencia? ");
				System.out.println("1- SI");
				System.out.println("2- NO");
				System.out.println("0- Para salir.");
				op = s.nextInt();
			} while (op != 0);
		}

	}

	// CU 7
	public void cerrarConvocatoria() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter(System.getProperty("line.separator"));

		System.out.println(" --- CERRAR CONVOCATORIA ---");

		System.out.println("Ingrese codigo de convocatoria a asignar: ");
		int codConvocatoria = s.nextInt();

		Convocatoria convocatoria = buscarConvocatoria(codConvocatoria);

		if (convocatoria == null) {
			System.out.println("No existe convocatoria con ese codigo.");
		} else {

			Empleado ganador = convocatoria.cerrar();
			eliminarInscripcionesGanador(convocatoria, ganador);
		}
	}

	private void eliminarInscripcionesGanador(Convocatoria c, Empleado gan) {
		if (!c.isAbierta()) {

			limpiarInscripciones(c.getCodigo());

			if (gan != null) {

				String dni = gan.getDni();

				limpiarInscripcionesGanador(dni);

				Empleado empleadoBuscado = buscarEmpleado(dni);
				if (empleadoBuscado != null) {
					if ((gan.sosJerarquico() && !empleadoBuscado.sosJerarquico())
							|| (!gan.sosJerarquico() && empleadoBuscado.sosJerarquico())) { // PROBAR
						empleados.remove(empleadoBuscado);
						empleados.add(gan);
					}
				}

				for (Convocatoria conv : convocatorias) {
					if (conv.isAbierta())
						conv.eliminarInscripcionPorDni(dni);
				}
			}
		}
	}

	private void limpiarInscripciones(int codConv) {
		for (ListIterator<Inscripcion> it = inscripciones.listIterator(); it.hasNext();) {
			Inscripcion ins = it.next();
			if (ins.sosConvocatoria(codConv)) {
				it.remove();
			}
		}
	}

	private void limpiarInscripcionesGanador(String dni) {
		for (ListIterator<Inscripcion> it = inscripciones.listIterator(); it.hasNext();) {
			Inscripcion ins = it.next();
			if (ins.sosInscripto(dni)) {
				it.remove();
			}
		}
	}

	// CU 8
	public void informarConvocatoriasAbiertas() {
		System.out.println(" --- LISTADO CONVOCATORIAS ABIERTAS ---");

		int i = 0;

		for (Convocatoria c : convocatorias) {
			if (c.isAbierta()) {
				System.out.println("--------------");
				c.mostrarse();
				System.out.println();

				i++;
			}
		}
		if (i == 0) {
			System.out.println("No hay convocatorias abiertas.");
		}
	}

	// CU 9
	public void informarPuestos() {
		if (puestos.size() > 0) {
			System.out.println(" --- LISTADO PUESTOS ---");

			for (Puesto p : puestos) {
				System.out.println("--------------");
				p.mostrarse();
				System.out.println();
			}
		} else {
			System.out.println("No hay puestos cargados.");
		}

	}

	// CU 10
	public void informarEmpleados() {
		if (empleados.size() > 0) {
			System.out.println(" --- LISTADO EMPLEADOS ---");

			for (Empleado e : empleados) {
				System.out.println("--------------");
				e.mostrarse();
				System.out.println();
			}
		} else {
			System.out.println("No hay empleados cargados.");
		}

	}

	// CU 11
	public void informarInscripciones() {
		if (inscripciones.size() > 0) {
			System.out.println(" --- LISTADO INSCRIPCIONES ---");

			for (Inscripcion ins : inscripciones) {
				System.out.println("--------------");
				ins.mostrarse();
				System.out.println();
			}
		} else {

			System.out.println("No hay inscripciones cargadas.");

		}

	}

	// CU 12
	public void informarEspecializaciones() {
		if (especializaciones.size() > 0) {
			System.out.println(" --- LISTADO ESPECIALIZACIONES ---");

			for (int i = 0; i < especializaciones.size(); i++) {
				System.out.println((i + 1) + " - " + especializaciones.get(i));
			}

		} else {
			System.out.println("No hay especializaciones cargadas");
		}

	}

	// CU 13
	public void informarEmpleadosJerarquicos() {
		System.out.println(" --- LISTADO EMPLEADOS JERARQUICOS ---");

		int i = 0;

		for (Empleado e : empleados) {
			if (e.getClass() == EmpleadoJerarquico.class) { // +++++++++++++++++++++++++++++++++++++++++++
				e.mostrarse();
				i++;
			}
		}
		if (i == 0) {
			System.out.println("No hay empleados jerarquicos cargados.");
		}
	}

	// CU 14
	public void informarInscripcionesEmpleado() {
		System.out.println(" --- LISTADO INSCRIPCIONES POR EMPLEADO ---");

		Scanner s = new Scanner(System.in);

		System.out.println("Ingrese DNI del empleado: ");
		String dni = s.next();

		Empleado e = this.buscarEmpleado(dni);

		if (e != null) {
			e.mostrarInscripciones();
		} else {
			System.out.println("El empleado no existe.");
		}

	}

	// CU 15
	public void informarEmpleadoMasInscripciones() {
		System.out.println(" --- EMPLEADO CON MAS INSCRIPCIONES ---");

		if (empleados.size() > 1) {
			Empleado empMas = empleados.get(0);
			int empMax = empleados.get(0).getCantInscripciones();

			int i = 1;
			Empleado empI;

			while (i < empleados.size()) {
				empI = empleados.get(i);
				if (empI.getCantInscripciones() > empMax) {
					empMas = empI;
					empMax = empI.getCantInscripciones();
				}
				i++;
			}

			System.out.println("El empleado con mas inscripciones es: ");
			empMas.mostrarse();
			System.out.println("Con un total de " + empMax + " inscripciones.");
		} else {
			System.out.println("No hay suficientes empleados para comparar. ");
		}

	}

	// CU 16
	public void informarEmpleadosAprobados() {
		System.out.println(" --- EMPLEADOS QUE CUMPLEN CON LOS REQUISITOS DE LA CONVOCATORIA ---");

		Scanner s = new Scanner(System.in);
		System.out.println("Ingrese codigo de convocatoria: ");
		int cod = s.nextInt();

		Convocatoria c = buscarConvocatoria(cod);

		if (c != null) {
			if (c.isAbierta()) {
				c.mostrarEmpleadosAprobados();
			} else {
				System.out.println("La convocatoria se encuentra cerrada.");
			}
		} else {
			System.out.println("El codigo ingresado no esta relacionado a una convocatoria existente.");
		}
	}

	private Empleado buscarEmpleado(String dni) {
		int i = 0;
		while (i < empleados.size() && !empleados.get(i).sos(dni))
			i++;
		if (i < empleados.size())
			return empleados.get(i);
		else
			return null;
	}

	private Puesto buscarPuesto(int cod) {
		int i = 0;
		while (i < puestos.size() && !puestos.get(i).sos(cod))
			i++;
		if (i < puestos.size())
			return puestos.get(i);
		else
			return null;
	}

	private String buscarEspecializacion(String e) {
		int i = 0;
		while (i < especializaciones.size() && e.compareToIgnoreCase(especializaciones.get(i)) != 0) {
			i++;
		}
		if (i < especializaciones.size())
			return especializaciones.get(i);
		else
			return null;
	}

	private Convocatoria buscarConvocatoria(int cod) {
		int i = 0;
		while (i < convocatorias.size() && !convocatorias.get(i).sos(cod)) {
			i++;
		}
		if (i < convocatorias.size())
			return convocatorias.get(i);
		else
			return null;
	}

	private Inscripcion buscarInscripcion(int cod) {
		int i = 0;
		while (i < inscripciones.size() && !inscripciones.get(i).sos(cod)) {
			i++;
		}
		if (i < inscripciones.size())
			return inscripciones.get(i);
		else
			return null;
	}

	private Hashtable<String, Integer> crearExperiencia() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter(System.getProperty("line.separator"));

		Hashtable<String, Integer> ht = new Hashtable<String, Integer>();

		if (especializaciones.size() > 0) {

			System.out.println("Agregar especializaciones");
			System.out.println("1- SI");
			System.out.println("0- SALIR");
			int op = s.nextInt();

			if (op == 1) {
				this.informarEspecializaciones();

				do {
					System.out.println("Elija especializacion a agregar: ");
					int esp = s.nextInt();

					System.out.println("Ingrese anios de experiencia para la especializacion: ");
					int anios = s.nextInt();

					ht.put(especializaciones.get(esp - 1), anios);

					System.out.println("Desea gregar mas especializaciones? ");
					System.out.println("1- SI");
					System.out.println("0- SALIR");
					op = s.nextInt();
				} while (op != 0);
			}

		}

		return ht;
	}

	public void CARGADATOS() {
		// Carga de especializaciones
		String e1 = "BASE DE DATOS";
		String e2 = "JAVA";
		String e3 = "C#";
		String e4 = "GIT";
		String e5 = "C++";
		especializaciones.add(e1);
		especializaciones.add(e2);
		especializaciones.add(e3);
		especializaciones.add(e4);
		especializaciones.add(e5);

		// Carga de puestos
		PuestoComun pc = new PuestoComun(1, "Desarrollador JR", "IT");
		PuestoComun pc2 = new PuestoComun(2, "Desarrollador SR", "IT");
		PuestoJerarquico pj = new PuestoJerarquico(3, "Gerente de IT", "Gerencia");
		PuestoJerarquico pj2 = new PuestoJerarquico(4, "Lider de proyecto", "IT");
		puestos.add(pc);
		puestos.add(pc2);
		puestos.add(pj);
		puestos.add(pj2);

		// Carga de empleados
		EmpleadoJerarquico ej = new EmpleadoJerarquico("12345678", "Ignacio Perez", new Fecha(1, 1, 1996),
				"20123456784", pj, new Fecha(1, 1, 2016), new Fecha(1, 1, 2018),
				this.CDEXPERIENCIA(e1, e2, e3, e4, 2, 2, 4, 2));
		EmpleadoComun ec = new EmpleadoComun("12345679", "Mario Sal", new Fecha(1, 1, 1990), "20123456794", pc2,
				new Fecha(1, 1, 2015), this.CDEXPERIENCIA(e3, e3, e1, e1, 0, 4, 0, 2));
		EmpleadoComun ec2 = new EmpleadoComun("12345680", "Gonzalo Puentes", new Fecha(1, 1, 2000), "20123456804", pc,
				new Fecha(1, 1, 2020), this.CDEXPERIENCIA(e2, e2, e4, e4, 0, 2, 0, 2));
		EmpleadoJerarquico ej2 = new EmpleadoJerarquico("12345681", "Lautaro Martinez", new Fecha(1, 1, 1980),
				"20123456814", pj2, new Fecha(1, 1, 2015), new Fecha(1, 1, 2015),
				this.CDEXPERIENCIA(e1, e5, e3, e4, 3, 3, 3, 5));
		empleados.add(ec);
		empleados.add(ec2);
		empleados.add(ej);
		empleados.add(ej2);

		// Carga de convocatorias
		Convocatoria c = new Convocatoria(1, pc, this.CDEXPERIENCIA(e1, e5, e3, e4, 2, 3, 2, 4));
		Convocatoria c2 = new Convocatoria(2, pj, this.CDEXPERIENCIA(e1, e1, e3, e3, 0, 2, 0, 4));
		convocatorias.add(c);
		convocatorias.add(c2);

		// Carga de inscripciones
		Inscripcion ins = new Inscripcion(1, ej, c2);
		Inscripcion ins2 = new Inscripcion(2, ec, c2);
		Inscripcion ins3 = new Inscripcion(3, ej2, c);
		Inscripcion ins4 = new Inscripcion(4, ec2, c);
		inscripciones.add(ins);
		inscripciones.add(ins2);
		inscripciones.add(ins3);
		inscripciones.add(ins4);

		c.addInscripcion(ins3);
		c.addInscripcion(ins4);
		c2.addInscripcion(ins);
		c2.addInscripcion(ins2);

		ej.agregarInscripcion(ins);
		ej2.agregarInscripcion(ins3);
		ec.agregarInscripcion(ins2);
		ec2.agregarInscripcion(ins4);
	}

	private Hashtable<String, Integer> CDEXPERIENCIA(String e1, String e2, String e3, String e4, int a1, int a2, int a3,
			int a4) {
		Hashtable<String, Integer> ht = new Hashtable<String, Integer>();

		ht.put(e1, a1);
		ht.put(e2, a2);
		ht.put(e3, a3);
		ht.put(e4, a4);

		return ht;
	}
}
