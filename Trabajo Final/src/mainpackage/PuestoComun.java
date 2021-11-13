package mainpackage;

public class PuestoComun extends Puesto {

	public PuestoComun(int codigo, String nombre, String area) {
		super(codigo, nombre, area);
	}

	public boolean isCompatible(int op) {
		return op == 2;
	}
}
