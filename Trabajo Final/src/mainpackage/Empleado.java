package mainpackage;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public abstract class Empleado extends Persona {
	private Hashtable<String, Integer> experiencia;
	private Puesto puesto;
	private Fecha fechaDeIngreso;
	private ArrayList<Inscripcion> inscripciones;

	public Empleado(int dni, String nombre, Fecha fechaNac, String cuil, Puesto puesto, Fecha fechaIngreso) {
		super(dni, nombre, fechaNac, cuil);
		this.experiencia = new Hashtable<String, Integer>();
		this.puesto = puesto;
		this.fechaDeIngreso = fechaIngreso;
	}

	public Empleado(int dni, String nombre, Fecha fechaNac, String cuil, Puesto puesto, Fecha fechaIngreso,
			Hashtable<String, Integer> exp) {
		super(dni, nombre, fechaNac, cuil);
		this.experiencia = exp;
		this.puesto = puesto;
		this.fechaDeIngreso = fechaIngreso;
	}

	public void setExperiencia(Hashtable<String, Integer> exp) {
		this.experiencia = exp;
	}

	private boolean verificarAniosEnEmpresaPostulante() {
		return !fechaDeIngreso.entre(Fecha.hoy().restarAnios(puesto.getMinimoAnios()), Fecha.hoy());
	}

	// sumarExperiencia suma la cantidad de a�os que se pasen por parametro a la
	// especializacion dada.
	private void sumarExperiencia(String esp, Integer anios) {
		Integer aniosActuales = experiencia.get(esp);
		experiencia.put(esp, anios + aniosActuales);
	}

	// agregarExperiencia suma la cantidad de a�os que se pasan por parametro a la
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
		System.out.println("++Listado de experiencia++");
		Enumeration<String> listaExp = experiencia.keys();
		while (listaExp.hasMoreElements()) {
			String esp = listaExp.nextElement();
			System.out.println(esp + experiencia.get(esp));
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

		Enumeration<String> enumExpReq = expReq.keys();

		Enumeration<String> enumExpEmp = experiencia.keys();

		boolean aux = true;

		String keyReq = null, keyEmp = null;
		Integer aniosReq = null, aniosEmp = null;

		while (enumExpReq.hasMoreElements() && aux == true) {

			keyReq = enumExpReq.nextElement();
			aniosReq = expReq.get(keyReq);

			while (enumExpEmp.hasMoreElements() && keyReq.compareToIgnoreCase(keyEmp) != 0) {

				keyEmp = enumExpEmp.nextElement();
				aniosEmp = experiencia.get(keyEmp);

			}

			if (keyReq.compareToIgnoreCase(keyEmp) != 0) {
				if (aniosEmp < aniosReq) {
					aux = false;
				}
			} else {
				aux = false;
			}

		}

		if (!enumExpReq.hasMoreElements() && aux == true) {
			return true;
		} else {
			return false;
		}
	}

	public Fecha getFechaDeIngreso() {
		return fechaDeIngreso;
	}
}
