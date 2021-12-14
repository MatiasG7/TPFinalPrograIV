package mainpackage;

public abstract class Puesto {
	private int codigo;
	private String nombre;
	private String area;

	public Puesto(int codigo, String nombre, String area) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.area = area;
	}

	public boolean sos(int cod) {
		return codigo == cod;
	}

	public int getCodigo() {
		return codigo;
	}

	public int getMinimoAnios() {
		return 0;
	}

	public abstract boolean isCompatible(int op);

	public void mostrarse() {
		System.out.println("Codigo: " + codigo);
		System.out.println("Nombre: " + nombre);
		System.out.println("Area: " + area);
	}

	public boolean esJerarquico() {
		return false;
	}
}
