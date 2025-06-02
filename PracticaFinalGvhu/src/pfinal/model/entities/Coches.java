package pfinal.model.entities;

public class Coches {
	private int id;
	private String marca;
	private String modelo;
	private String matricula;
	private int anio;
	
	public Coches(String marca, String modelo, String matricula, int anio) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.matricula = matricula;
		this.anio = anio;
	}
	public Coches() {
		super();
		
	}

	@Override
	public String toString() { 
		return "Coches [id= " + id + ", marca= " + marca + ", modelo= " + modelo + ", matricula= " + matricula + ", anio= "
				+ anio + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	
	
}
