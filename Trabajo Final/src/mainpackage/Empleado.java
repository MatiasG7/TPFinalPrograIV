package mainpackage;

import java.util.ArrayList;
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

	public void agregarExperiencia(String nombre, Integer years) {
		experiencia.put(nombre, years);
	}
}
