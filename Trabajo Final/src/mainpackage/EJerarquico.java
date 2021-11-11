package mainpackage;

public class EJerarquico extends Empleado {
	private Fecha fechaIngresoPuestoFecha;
	private static int minimoCambioJerarquico;
	
	public EJerarquico(int dn, String nom, Puesto pues, Fecha fe) {
		super(dn,nom,pues,fe);
	}
}
