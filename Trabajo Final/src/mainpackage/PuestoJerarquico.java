package mainpackage;

import java.util.Scanner;

public class PuestoJerarquico extends Puesto {
	private static int minimoAņosEmpresa;

	public PuestoJerarquico(int codigo, String nombre, String area) {
		super(codigo, nombre, area);
	}

	public int getMinimoAņos() {
		return PuestoJerarquico.getMinimoAņosEmpresa();
	}

	public static void setMinimoAņosEmpresa(int ae) {
		minimoAņosEmpresa = ae;
	}

	private static int getMinimoAņosEmpresa() {
		if (minimoAņosEmpresa == 0) {
			Scanner s = new Scanner(System.in);

			System.out.println("La cantidad minima de aņos en la empresa todavía no fue seteada, ingrese un valor: ");
			int ae = s.nextInt();
			PuestoJerarquico.setMinimoAņosEmpresa(ae);

			s.close();
		}

		return minimoAņosEmpresa;
	}

	public boolean isCompatible(int op) {
		return op == 1;
	}

	public boolean esJerarquico() {
		return true;
	}
}
