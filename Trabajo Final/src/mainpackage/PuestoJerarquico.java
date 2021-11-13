package mainpackage;

import java.util.Scanner;

public class PuestoJerarquico extends Puesto {
	private static int MinimoAñosEmpresa;

	public PuestoJerarquico(int codigo, String nombre, String area) {
		super(codigo, nombre, area);
	}

	public int getMinimoAños() {
		return this.getMinimoAñosEmpresa();
	}

	public static void setMinimoAñosEmpresa(int ae) {
		MinimoAñosEmpresa = ae;
	}

	private static int getMinimoAñosEmpresa() {
		if (MinimoAñosEmpresa == 0) {
			Scanner s = new Scanner(System.in);
			System.out.println("La cantidad minima de años en la empresa todavía no fue seteada, ingrese un valor: ");
			int ae = s.nextInt();
			PuestoJerarquico.setMinimoAñosEmpresa(ae);
		}

		return MinimoAñosEmpresa;
	}
}
