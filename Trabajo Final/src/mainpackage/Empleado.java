package mainpackage;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Enumeration;

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

	public void mostrarse() {
		super.mostrarse();
		System.out.println("Puesto actual: ");
		puesto.mostrarse();
		System.out.println("Fecha de ingreso: ");
		fechaDeIngreso.mostrarse();
	}

	public boolean isAptoPuesto(Hashtable<String, Integer> expReq) {
		expReq.forEach((key,value)->
				
					
				
				);

	}

	public boolean verificarExperiencia() {

	}
}
