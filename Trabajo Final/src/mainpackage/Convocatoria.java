package mainpackage;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class Convocatoria {
	private int codigo;
	private Hashtable<String, Integer> experienciaReq;
	private Puesto puesto;
	private ArrayList<Inscripcion> inscripciones;
	private Empleado ganador;
	private Estado estado;

	public Convocatoria(int cod, Puesto pues, Hashtable<String, Integer> exp) {
		codigo = cod;
		puesto = pues;
		experienciaReq = exp;
		inscripciones = new ArrayList<Inscripcion>();
		estado = Estado.ABIERTO;
		ganador = null;
	}

	private enum Estado {
		ABIERTO {
			public String toString() {
				return "ABIERTO";
			}
		},

		CERRADO {
			public String toString() {
				return "CERRADO";
			}
		}
	}

	public boolean sos(int cod) {
		return codigo == cod;
	}

	public void setExperienciaReq(Hashtable<String, Integer> exp) {
		this.experienciaReq = exp;
	}

	public int getCodigo() {
		return codigo;
	}

	public boolean isAbierta() {
		return this.estado == Estado.ABIERTO;
	}

	public void mostrarse() {
		this.mostrarCodYPuesto();
		this.mostrarExpRequerida();

		if (this.isAbierta()) {
			for (Inscripcion i : this.inscripciones) {
				i.mostrarse();
			}
		} else {
			ganador.mostrarse();
		}
	}

	public void mostrarCodYPuesto() {
		System.out.println("Codigo: " + codigo);
		System.out.println("Puesto: ");
		puesto.mostrarse();
	}

	public void mostrarExpRequerida() {
		System.out.println("++ Listado de experiencia requerida ++");
		Enumeration<String> listaExp = experienciaReq.keys();
		while (listaExp.hasMoreElements()) {
			String esp = listaExp.nextElement();
			System.out.println(esp + experienciaReq.get(esp));
		}
	}

	public void addInscripcion(Inscripcion ins) {
		inscripciones.add(ins);
	}

	public Inscripcion verificarInscripcion(Empleado emp) {
		int i = 0;
		while (i < inscripciones.size() && inscripciones.get(i).sosInscripto(emp.getDni())) {
			i++;
		}
		if (i < inscripciones.size()) {
			return inscripciones.get(i);
		} else {
			return null;
		}
	}

	public void elegirGanador() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter(System.getProperty("line.separator"));

		int i = 0;
		ArrayList<Inscripcion> inscripcionesAprobadas = new ArrayList<Inscripcion>();
		for (Inscripcion inscripcion : inscripciones) {
			if (verificarPostulante(inscripcion)) {
				inscripcionesAprobadas.add(inscripcion);
			}
		}

		System.out.println("++Listado de inscripciones aprobadas++");
		for (i = 0; i <= inscripciones.size(); i++) {
			System.out.println(" - " + i + 1 + " - ");
			inscripciones.get(i).mostrarEmpleado();
		}

		if (i == 0) {
			System.out.println("Ninguna de los inscripciones tiene la experiencia requerida.");
		} else {
			System.out.println("Elija ganador: ");
			int gan = s.nextInt();

			this.ganador = inscripcionesAprobadas.get(gan - 1).getEmpleado();
			this.ganador.actualizarPuesto(puesto);
			this.ganador.mostrarse();
			this.eliminarInscripcionesDeEmpleados();
		}
	}

	public void cerrar() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter(System.getProperty("line.separator"));

		System.out.println("Desea elegir un ganador ?");
		System.out.println("1- SI");
		System.out.println("2- NO");
		System.out.println("0- Cancelar");
		int op = s.nextInt();

		if (op == 1) {
			this.elegirGanador();
		}

		if (op == 1 || op == 2) {
			this.estado = Estado.CERRADO;
		}
	}

	public boolean verificarPostulante(Inscripcion ins) {
		return ins.isApto();
	}

	public Hashtable<String, Integer> getExpReq() {
		return experienciaReq;
	}

	public boolean esPuestoJerarquico() {
		return puesto.esJerarquico();
	}

	public void eliminarInscripcionesDeEmpleados() {
		for (Inscripcion ins : inscripciones) {
			ins.eliminarInscripcionDentroDeEmpleado();
		}
	}
}
