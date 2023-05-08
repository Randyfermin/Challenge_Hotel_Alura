package modelo;

import java.sql.Date;

public class Huesped {
	private int Id;
	private String Nombre;
	private String Apellido;
	private Date FechaNacimiento;
	private String Nacionalidad;
	private String Telefono;
	private String LocalizadorReserva;
	
	private Reservas reservas;
	
	public Huesped() {
	}

	
	
	public Huesped(int id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono,
			String localizadorReserva) {
		Id = id;
		Nombre = nombre;
		Apellido = apellido;
		FechaNacimiento = fechaNacimiento;
		Nacionalidad = nacionalidad;
		Telefono = telefono;
		LocalizadorReserva = localizadorReserva;
	}



	public Huesped(int id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono) {
		Id = id;
		Nombre = nombre;
		Apellido = apellido;
		FechaNacimiento = fechaNacimiento;
		Nacionalidad = nacionalidad;
		Telefono = telefono;
	}
	
	
	public int getId() {
		return Id;
	}

	
	public void setId(int id) {
		Id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return FechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		FechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return Nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		Nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public Reservas getReservas() {
		return reservas;
	}

	public void setReservas(Reservas reservas) {
		this.reservas = reservas;
	}

	public String getLocalizadorReserva() {
		return LocalizadorReserva;
	}

	public void setLocalizadorReserva(String localizadorReserva) {
		LocalizadorReserva = localizadorReserva;
	}
}
