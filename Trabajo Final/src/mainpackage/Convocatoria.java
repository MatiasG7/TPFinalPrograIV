package mainpackage;

import java.util.ArrayList;
import java.util.Hashtable;

public class Convocatoria {
	
	private int codigo;
	private Hashtable<String, Integer>experienciaReq;
	private Puesto puesto;
	private ArrayList<Inscripcion>inscripciones;
	
	public Convocatoria(int cod, Puesto pues) {
		codigo = cod;
		puesto = pues;
		experienciaReq = new Hashtable<String, Integer>();
		inscripciones = new ArrayList<Inscripcion>();
	}
	
	public void setExperienciaReq(Hashtable<String, Integer> exp) {
		this.experienciaReq = exp;
	}
	
	public void agregarInscripcion(Inscripcion ins) {
		inscripciones.add(ins);
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void mostrarse() {
		System.out.print("\nCodigo convocatoria: " + codigo);
		System.out.print("\nPuesto: ");
		puesto.mostrarse();
	}
	
}