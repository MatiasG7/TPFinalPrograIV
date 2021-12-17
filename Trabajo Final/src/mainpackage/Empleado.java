package mainpackage;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public abstract class Empleado extends Persona {
	private Hashtable<String, Integer> experiencia;
	private Puesto puesto;
	private Fecha fechaIngreso;
	private ArrayList<Inscripcion> inscripciones;

	public Empleado(String dni, String nombre, Fecha fechaNac, String cuil, Puesto puesto, Fecha fechaIngreso,
			Hashtable<String, Integer> exp) {
		super(dni, nombre, fechaNac, cuil);
		this.experiencia = exp;
		this.puesto = puesto;
		this.fechaIngreso = fechaIngreso;
		this.inscripciones = new ArrayList<Inscripcion>();
	}

	public boolean sosJerarquico() {
		return false;
	}

	public Fecha getFechaIngreso() {
		return fechaIngreso;
	}

	public Hashtable<String, Integer> getExperiencia() {
		return experiencia;
	}

	public ArrayList<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public int getCantInscripciones() {
		return inscripciones.size();
	}

	public void setInscripciones(ArrayList<Inscripcion> ins) {
		inscripciones = ins;
	}

	public void setExperiencia(Hashtable<String, Integer> exp) {
		this.experiencia = exp;
	}

	public void setPuesto(Puesto p) {
		this.puesto = p;
	}

	private boolean verificarAniosEnEmpresaPostulante() {
		return !fechaIngreso.entre(Fecha.hoy().restarAnios(puesto.getMinimoAnios()), Fecha.hoy());
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

	// sumarExperiencia suma la cantidad de anios que se pasen por parametro a la
	// especializacion dada.
	private void sumarExperiencia(String esp, Integer anios) {
		Integer aniosActuales = experiencia.get(esp);
		experiencia.put(esp, anios + aniosActuales);
	}

	// existEspExperiencia es para saber si el empleado tiene la especializacion
	// dada como experiencia. Es decir, si la key esta en el hashtable.
	private boolean existEspExperiencia(String esp) {
		return experiencia.containsKey(esp);
	}

	public boolean isAptoPuesto(Convocatoria convocatoria) {
		if (verificarExperiencia(convocatoria)) {
			if (convocatoria.isPuestoJerarquico()) {
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

	public void agregarInscripcion(Inscripcion ins) {
		inscripciones.add(ins);
	}

	public void removerInscripcion(int cod) {
		Inscripcion ins = this.buscarInscripcion(cod);

		if (ins != null) {
			this.inscripciones.remove(ins);
		}
	}

	public void borrarInscripciones() {
		this.inscripciones = new ArrayList<Inscripcion>();
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

	public void mostrarse() {
		super.mostrarse();
		puesto.mostrarse();
		System.out.println("-Fecha de ingreso: ");
		fechaIngreso.mostrarse();
		System.out.println();
		this.mostrarExperiencia();
	}

	public void mostrarExperiencia() {
		if (experiencia.size() > 0) {
			System.out.println("LISTADO DE EXPERIENCIA");
			Enumeration<String> listaExp = experiencia.keys();
			while (listaExp.hasMoreElements()) {
				String esp = listaExp.nextElement();
				System.out.println("-" + esp + " " + experiencia.get(esp));
			}
		} else {
			System.out.println(this.getNombre() + " no cuenta con experiencia");
		}

	}

	public void mostrarInscripciones() {
		if (inscripciones.size() > 0) {
			for (Inscripcion ins : inscripciones) {
				ins.mostrarse();
			}
		} else {
			System.out.println(this.getNombre() + " no cuenta con inscripciones");
		}

	}
}
