package mainpackage;

public abstract class Persona {
	int dni;
	String nombre;

	public Persona(int dni, String nombre) {
		this.dni = dni;
		this.nombre = nombre;
	}

	public int getDni() {
		return dni;
	}
}
