package mainpackage;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public abstract class Empleado extends Persona {
	private Hashtable<String, Integer> experiencia;
	private Puesto puesto;
	private Fecha fechaDeIngreso;
	private ArrayList<Inscripcion> inscripciones;

	public Empleado(String dni, String nombre, Fecha fechaNac, String cuil, Puesto puesto, Fecha fechaIngreso,
			Hashtable<String, Integer> exp) {
		super(dni, nombre, fechaNac, cuil);
		this.experiencia = exp;
		this.puesto = puesto;
		this.fechaDeIngreso = fechaIngreso;
		this.inscripciones = new ArrayList<Inscripcion>();
	}

	public void setExperiencia(Hashtable<String, Integer> exp) {
		this.experiencia = exp;
	}

	private boolean verificarAniosEnEmpresaPostulante() {
		return !fechaDeIngreso.entre(Fecha.hoy().restarAños(puesto.getMinimoAnios()), Fecha.hoy());
	}

	// sumarExperiencia suma la cantidad de anios que se pasen por parametro a la
	// especializacion dada.
	private void sumarExperiencia(String esp, Integer anios) {
		Integer aniosActuales = experiencia.get(esp);
		experiencia.put(esp, anios + aniosActuales);
	}

	// agregarExperiencia suma la cantidad de anios que se pasan por parametro a la
	// especializacion dada, si la especializacion no existe como experiencia, se
	// crea.
	public void agregarExperiencia(String esp, Integer anios) {
		if (existEspExperiencia(esp)) {
			this.sumarExperiencia(esp, anios);
		} else {
			experiencia.put(esp, anios);
		}
	}

	// existEspExperiencia es para saber si el empleado tiene la especializacion
	// dada como experiencia. Es decir, si la key esta en el hashtable.
	private boolean existEspExperiencia(String esp) {
		return experiencia.containsKey(esp);
	}

	public void mostrarse() {
		super.mostrarse();
		System.out.println("Puesto actual: ");
		puesto.mostrarse();
		System.out.println("Fecha de ingreso: ");
		fechaDeIngreso.mostrarse();
		this.mostrarExperiencia();
	}

	public void mostrarExperiencia() {
		if (experiencia.size() > 0) {
			System.out.println("++ Listado de experiencia ++");
			Enumeration<String> listaExp = experiencia.keys();
			while (listaExp.hasMoreElements()) {
				String esp = listaExp.nextElement();
				System.out.println(esp + " - " + experiencia.get(esp));
			}
		} else {
			System.out.println(this.getNombre() + " no cuenta con experiencia");
		}

	}

	public void mostrarInscripciones() {
		if (inscripciones.size() > 0) {
			for (Inscripcion ins : inscripciones) {
				ins.mostrarCodigo();
				ins.mostrarConvocatoria();
			}
		} else {
			System.out.println(this.getNombre() + " no cuenta con inscripciones");
		}

	}

	public boolean isAptoPuesto(Convocatoria convocatoria) {
		if (verificarExperiencia(convocatoria)) {
			if (convocatoria.esPuestoJerarquico()) {
				return verificarAniosEnEmpresaPostulante();
			} else {
				return true;
			}
		}

		return false;
	}

	public boolean verificarExperiencia(Convocatoria convocatoria) {
		Hashtable<String, Integer> expReq = convocatoria.getExpReq();

		Enumeration<String> enumExpRequerida = expReq.keys();

		Enumeration<String> enumExpEmpleado = experiencia.keys();

		String keyRequerida = null;
		Integer aniosRequerida = null, aniosEmpleado = null;

		while (enumExpRequerida.hasMoreElements()) {

			keyRequerida = enumExpRequerida.nextElement();
			aniosRequerida = expReq.get(keyRequerida);

			if (experiencia.containsKey(keyRequerida)) {
				aniosEmpleado = experiencia.get(keyRequerida);

				if (aniosEmpleado < aniosRequerida) {
					return false;
				}
			} else {
				return false;
			}

		}

		return true;
	}

	/*
	 * public boolean verificarExperiencia(Convocatoria convocatoria) {
	 * Hashtable<String, Integer> expReq = convocatoria.getExpReq();
	 * 
	 * Enumeration<String> enumExpReq = expReq.keys();
	 * 
	 * Enumeration<String> enumExpEmp = experiencia.keys();
	 * 
	 * boolean aux = true;
	 * 
	 * String keyReq = null, keyEmp = null; Integer aniosReq = null, aniosEmp =
	 * null;
	 * 
	 * while (enumExpReq.hasMoreElements() && aux == true) {
	 * 
	 * keyReq = enumExpReq.nextElement(); aniosReq = expReq.get(keyReq);
	 * 
	 * while (enumExpEmp.hasMoreElements() && keyReq.compareToIgnoreCase(keyEmp) !=
	 * 0) {
	 * 
	 * keyEmp = enumExpEmp.nextElement(); aniosEmp = experiencia.get(keyEmp);
	 * 
	 * }
	 * 
	 * if (keyReq.compareToIgnoreCase(keyEmp) != 0) { if (aniosEmp < aniosReq) { aux
	 * = false; } } else { aux = false; }
	 * 
	 * }
	 * 
	 * if (!enumExpReq.hasMoreElements() && aux == true) { return true; } else {
	 * return false; } }
	 */

	public void actualizarPuesto(Puesto nuevoPuesto) {
		this.puesto = nuevoPuesto;
	}

	public void removerInscripcion(int cod) {
		Inscripcion ins = this.buscarInscripcion(cod);

		if (ins != null) {
			this.inscripciones.remove(ins);
		}
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

	public int getSizeInscripciones() {
		return inscripciones.size();
	}

	public boolean sosJerarquico() {
		return false;
	}

	public Fecha getFechaDeIngreso() {
		return fechaDeIngreso;
	}

	public Hashtable<String, Integer> getExperiencia() {
		return experiencia;
	}

	public ArrayList<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(ArrayList<Inscripcion> ins) {
		inscripciones = ins;
	}

	public void borrarInscripciones() {
		this.inscripciones = new ArrayList<Inscripcion>();
	}
}
