package mainpackage;

import java.util.ArrayList;
import java.util.Hashtable;

public class Empleado extends Persona {
	private Hashtable<String, Integer> experiencia;
	private Puesto puesto;
	private Fecha fechaDeIngreso;
	private ArrayList <Inscripcion> inscripciones;
	
	public Empleado(int dn, String nom, Puesto pues, Fecha fe) {
		super(dn, nom);
		experiencia = new Hashtable<String, Integer>();
		puesto = pues;
		fechaDeIngreso = fe;
	}
	
	public void agregarExperiencia(String nombre, Integer years) {
		experiencia.put(nombre, years);
	}
}
