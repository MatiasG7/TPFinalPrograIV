package mainpackage;

import java.util.Hashtable;

public class EmpleadoJerarquico extends Empleado {
	private static int minimoCambioJerarquico;
	private Fecha fechaIngresoPuestoFecha;

	public EmpleadoJerarquico(int dni, String nombre, Fecha fechaNac, String cuil, Puesto puesto, Fecha fecha,
			Hashtable<String, Integer> exp) {
		super(dni, nombre, fechaNac, cuil, puesto, fecha, exp);
	}
}