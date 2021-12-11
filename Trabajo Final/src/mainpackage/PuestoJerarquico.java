package mainpackage;

import java.util.Scanner;

public class PuestoJerarquico extends Puesto {
	private static int minimoAñosEmpresa;

	public PuestoJerarquico(int codigo, String nombre, String area) {
		super(codigo, nombre, area);
	}

	public int getMinimoAños() {
		return PuestoJerarquico.getMinimoAñosEmpresa();
	}

	public static void setMinimoAñosEmpresa(int ae) {
		minimoAñosEmpresa = ae;
	}

	private static int getMinimoAñosEmpresa() {
		if (minimoAñosEmpresa == 0) {
			Scanner s = new Scanner(System.in);

			System.out.println("La cantidad minima de años en la empresa todavía no fue seteada, ingrese un valor: ");
			int ae = s.nextInt();
			PuestoJerarquico.setMinimoAñosEmpresa(ae);

			s.close();
		}

		return minimoAñosEmpresa;
	}

	public boolean isCompatible(int op) {
		return op == 1;
	}

	public boolean esJerarquico() {
		return true;
	}
}
