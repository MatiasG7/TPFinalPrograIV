package mainpackage;

public abstract class Persona {
	private String dni;
	private String nombre;
	private Fecha fechaNacimiento;
	private String cuil;

	public Persona(String dni, String nombre, Fecha fechaNac, String cuil) {
		this.dni = dni;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNac;
		this.cuil = cuil;
	}

	public boolean sos(String dni) {
		return (this.dni.compareToIgnoreCase(dni) == 0);
	}

	public String getDni() {
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
