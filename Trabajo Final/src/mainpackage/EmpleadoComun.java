package mainpackage;

import java.util.Hashtable;

public class EmpleadoComun extends Empleado {

	public EmpleadoComun(String dni, String nombre, Fecha fechaNac, String cuil, Puesto puesto, Fecha fecha,
			Hashtable<String, Integer> exp) {
		super(dni, nombre, fechaNac, cuil, puesto, fecha, exp);
	}

}
