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

	public int getCodigo() {
		return codigo;
	}
	
	public void mostrarse() {
		System.out.print("\nCodigo convocatoria: " + codigo);
		System.out.print("\nPuesto: ");
		puesto.mostrarse();
	}
	
	public void addInscripcion(Inscripcion ins) {
		inscripciones.add(ins);
	}
	
	public Inscripcion verificarInscripcion(Empleado emp) {
		int i = 0;
		while( i < inscripciones.size() && emp.getDni()!=inscripciones.get(i).getEmpleado().getDni()) {
			i++;
		}
		if ( i < inscripciones.size() ) {
			return inscripciones.get(i);
		} else {
			return null;
		}
	}	
	
}
