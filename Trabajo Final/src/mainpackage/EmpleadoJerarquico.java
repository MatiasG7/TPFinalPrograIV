package mainpackage;

public class EmpleadoJerarquico extends Empleado {
	private static int minimoCambioJerarquico;
	private Fecha fechaIngresoPuestoFecha;

	public EmpleadoJerarquico(int dni, String nombre, Fecha fechaNac, String cuil, Puesto puesto, Fecha fecha) {
		super(dni, nombre, fechaNac, cuil, puesto, fecha);
	}
}