package mainpackage;

import java.util.Scanner;

public class PuestoJerarquico extends Puesto {
	private static int MinimoA�osEmpresa;

	public PuestoJerarquico(int codigo, String nombre, String area) {
		super(codigo, nombre, area);
	}

	public int getMinimoA�os() {
		return this.getMinimoA�osEmpresa();
	}

	public static void setMinimoA�osEmpresa(int ae) {
		MinimoA�osEmpresa = ae;
	}

	private static int getMinimoA�osEmpresa() {
		if (MinimoA�osEmpresa == 0) {
			Scanner s = new Scanner(System.in);
			System.out.println("La cantidad minima de a�os en la empresa todav�a no fue seteada, ingrese un valor: ");
			int ae = s.nextInt();
			PuestoJerarquico.setMinimoA�osEmpresa(ae);
		}

		return MinimoA�osEmpresa;
	}
}
