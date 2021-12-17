package mainpackage;

import java.util.Hashtable;
import java.util.Scanner;

public class EmpleadoJerarquico extends Empleado {
	private static int minimoCambioJerarquico;
	private Fecha fechaIngresoPuesto;

	public EmpleadoJerarquico(String dni, String nombre, Fecha fechaNac, String cuil, Puesto puesto, Fecha fecha,
			Fecha fechaIngresoPues, Hashtable<String, Integer> exp) {
		super(dni, nombre, fechaNac, cuil, puesto, fecha, exp);
		fechaIngresoPuesto = fechaIngresoPues;
	}

	public boolean sosJerarquico() {
		return true;
	}

	public int getMinimoCambio() {
		return EmpleadoJerarquico.getMinimoCambioJerarquico();
	}

	private static int getMinimoCambioJerarquico() {
		if (minimoCambioJerarquico == 0) {
			Scanner s = new Scanner(System.in);

			System.out.println("La cantidad minima de anios en el puesto todavía no fue seteada, ingrese un valor: ");
			int ae = s.nextInt();
			PuestoJerarquico.setMinimoAniosEmpresa(ae);

		}

		return minimoCambioJerarquico;
	}

	public Fecha getfechaIngresoPuesto() {
		return fechaIngresoPuesto;
	}

	public static void setMinimoCambioJerarquico(int ae) {
		minimoCambioJerarquico = ae;
	}

	public boolean isAptoPuesto(Convocatoria convocatoria) {
		if (verificarAniosPuesto() && super.isAptoPuesto(convocatoria)) {
			return true;
		}

		return false;
	}

	public boolean verificarAniosPuesto() {
		return !fechaIngresoPuesto.entre(Fecha.hoy().restarAnios(getMinimoCambio()), Fecha.hoy());
	}
}