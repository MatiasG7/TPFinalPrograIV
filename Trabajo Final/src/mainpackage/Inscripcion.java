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

	public boolean sos(int cod) {
		return codigo == cod;
	}

	public boolean sosInscripto(String dni) {
		return (empleado.getDni().compareToIgnoreCase(dni) == 0);
	}

	public boolean sosConvocatoria(int cod) {
		return (convocatoria.getCodigo() == cod);
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDniEmpleado() {
		return empleado.getDni();
	}

	public Convocatoria getConvocatoria() {
		return convocatoria;
	}

	public boolean isApto() {
		return empleado.isAptoPuesto(convocatoria);
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void eliminarInscripcionDentroDeEmpleado() {
		this.empleado.removerInscripcion(this.codigo);
	}

	public void mostrarse() {
		this.mostrarCodigo();
		this.mostrarEmpleado();
	}

	public void mostrarCodigo() {
		System.out.println("Codigo: " + codigo);
	}

	public void mostrarEmpleado() {
		empleado.mostrarse();
	}

	public void mostrarConvocatoria() {
		this.convocatoria.mostrarCodYPuesto();
	}
}
