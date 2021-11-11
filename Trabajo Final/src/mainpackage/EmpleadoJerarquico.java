package mainpackage;

public class EmpleadoJerarquico extends Empleado {
	private Fecha fechaIngresoPuestoFecha;
	private static int minimoCambioJerarquico;

	public EmpleadoJerarquico(int dni, String nombre, Puesto puesto, Fecha fecha) {
		super(dni, nombre, puesto, fecha);
	}
}