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

	public int getCodigo() {
		return codigo;
	}

	public int getMinimoAños() {
		return 0;
	}
}
