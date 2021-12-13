package mainpackage;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Convocatoria {

	private int codigo;
	private Hashtable<String, Integer> experienciaReq;
	private Puesto puesto;
	private ArrayList<Inscripcion> inscripciones;
	private Inscripcion ganadora;
	private String estado;

	public Convocatoria(int cod, Puesto pues) {
		codigo = cod;
		puesto = pues;
		experienciaReq = new Hashtable<String, Integer>();
		inscripciones = new ArrayList<Inscripcion>();
		estado = "abierto";
		ganadora = null;
	}

	public Convocatoria(int cod, Puesto pues, Hashtable<String, Integer> exp) {
		codigo = cod;
		puesto = pues;
		experienciaReq = exp;
		inscripciones = new ArrayList<Inscripcion>();
	}

	public void setExperienciaReq(Hashtable<String, Integer> exp) {
		this.experienciaReq = exp;
	}

	public int getCodigo() {
		return codigo;
	}

	public void mostrarse() {
		System.out.println("Codigo convocatoria: " + codigo);
		System.out.println("Puesto: ");
		puesto.mostrarse();
	}

	public void addInscripcion(Inscripcion ins) {
		inscripciones.add(ins);
	}

	public Inscripcion verificarInscripcion(Empleado emp) {
		int i = 0;
		while (i < inscripciones.size() && emp.getDni() != inscripciones.get(i).getDniEmpleado()) {
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

			this.ganadora = inscripcionesAprobadas.get(gan - 1);
			ganadora.mostrarEmpleado();
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

		if (op != 0) {
			this.estado = "CERRADO";
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
}
