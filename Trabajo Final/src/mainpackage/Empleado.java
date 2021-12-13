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

	public void agregarExperiencia(String nombre, Integer years) {
		experiencia.put(nombre, years);
	}

	public void setExperiencia(Hashtable<String, Integer> exp) {
		this.experiencia = exp;
	}

	private boolean verificarAniosEnEmpresaPostulante() {
		return !fechaDeIngreso.entre(Fecha.hoy().restarAños(puesto.getMinimoAños()), Fecha.hoy());
	}

	public void mostrarse() {
		super.mostrarse();
		System.out.println("Puesto actual: ");
		puesto.mostrarse();
		System.out.println("Fecha de ingreso: ");
		fechaDeIngreso.mostrarse();
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
