package mainpackage;

public class Inscripcion {

	private int codigo;
	private Empleado empleado;
	private Convocatoria convocatoria;

	public Inscripcion(int cod, Empleado emp, Convocatoria conv) {
		codigo = cod;
		empleado = emp;
		convocatoria = conv;
	}

	public int getCodigo() {
		return codigo;
	}

	public int getDniEmpleado() {
		return empleado.getDni();
	}

	public void mostrarEmpleado() {
		empleado.mostrarse();
	}

	public Convocatoria getConvocatoria() {
		return convocatoria;
	}

	public boolean isApto() {
		return empleado.isAptoPuesto(convocatoria);
	}
}
