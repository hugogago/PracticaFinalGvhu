package pfinal.model.entities;

import java.util.UUID;

public class Usuarios {
	private int id;
	private String nombre;
	private String contraseña;
	private String uuid;
	
	public Usuarios(String nombre,String contraseña) {
		super();
		
		this.contraseña = contraseña;
		this.nombre = nombre;
		this.uuid = UUID.randomUUID().toString();
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public Usuarios() {
		super();
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "Usuarios [id= " + id + ", nombre= " + nombre + ", uuid= " + uuid + "]";
	}
	public String getUserPassword() {
		return contraseña;
	}
	public void setUserPassword(String contraseña) {
		this.contraseña = contraseña;
		
	}
	public String getName() {
		return this.nombre;
	}
	public String getPassword() {
		return this.contraseña;
	}

	
	
}
