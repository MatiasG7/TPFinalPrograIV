package mainpackage;

import java.util.Scanner;

public class PuestoJerarquico extends Puesto {
	private static int MinimoAņosEmpresa;

	public PuestoJerarquico(int codigo, String nombre, String area) {
		super(codigo, nombre, area);
	}

	public int getMinimoAņos() {
		return this.getMinimoAņosEmpresa();
	}

	public static void setMinimoAņosEmpresa(int ae) {
		MinimoAņosEmpresa = ae;
	}

	private static int getMinimoAņosEmpresa() {
		if (MinimoAņosEmpresa == 0) {
			Scanner s = new Scanner(System.in);
			System.out.println("La cantidad minima de aņos en la empresa todavía no fue seteada, ingrese un valor: ");
			int ae = s.nextInt();
			PuestoJerarquico.setMinimoAņosEmpresa(ae);
		}

		return MinimoAņosEmpresa;
	}
}
