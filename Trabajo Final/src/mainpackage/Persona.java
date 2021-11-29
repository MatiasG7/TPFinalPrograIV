package mainpackage;

public abstract class Persona {
	private int dni;
	private String nombre;
	private Fecha fechaNacimiento;
	private String cuil;

	public Persona(int dni, String nombre, Fecha fechaNac, String cuil) {
		this.dni = dni;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNac;
		this.cuil = cuil;
	}

	public int getDni() {
		return dni;
	}

	public void mostrarse() {
		System.out.println("Dni: " + dni);
		System.out.println("Nombre: " + nombre);
		System.out.println("Cuil: " + cuil);
		System.out.println("Fecha de nacimiento: ");
		fechaNacimiento.mostrarse();
	}
}
