package mainpackage;

import java.util.Scanner;

public class PuestoJerarquico extends Puesto {
	private static int minimoAniosEmpresa;

	public PuestoJerarquico(int codigo, String nombre, String area) {
		super(codigo, nombre, area);
	}

	public int getMinimoAnios() {
		return PuestoJerarquico.getMinimoAniosEmpresa();
	}

	public static void setMinimoAniosEmpresa(int ae) {
		minimoAniosEmpresa = ae;
	}

	private static int getMinimoAniosEmpresa() {
		if (minimoAniosEmpresa == 0) {
			Scanner s = new Scanner(System.in);

			System.out.println("La cantidad minima de anios en la empresa todav�a no fue seteada, ingrese un valor: ");
			int ae = s.nextInt();
			PuestoJerarquico.setMinimoAniosEmpresa(ae);

		}

		return minimoAniosEmpresa;
	}

	public boolean isCompatible(int op) {
		return op == 1;
	}

	public boolean esJerarquico() {
		return true;
	}

	public void mostrarse() {
		super.mostrarse();
		System.out.println("-Minimo de anios requeridos en la empresa: " + PuestoJerarquico.getMinimoAniosEmpresa());
	}
}
