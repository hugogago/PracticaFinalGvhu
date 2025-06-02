package pfinal.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Gastos {
	private int id;
	private int coche_id;
	private String tipo;
	private Date fecha;
	private float importe;
	private String descripcion;
	private int kilometraje;
	
	public Gastos(int id, int coche_id, String tipo, Date fecha, float importe, String descripcion, int kilometraje) {
		super();
		this.id = id;
		this.coche_id = coche_id;
		this.tipo = tipo;
		this.fecha = fecha;
		this.importe = importe;
		this.descripcion = descripcion;
		this.kilometraje = kilometraje;
	}
	public Gastos() {
		super();
		
	}

	

	public Gastos(String tipo2, int kilometraje2, LocalDate localDate, BigDecimal importe2, String descripcion2) {
		super();
	}
	public int getKilometraje() {
		return kilometraje;
	}



	public void setKilometraje(int kilometraje) {
		this.kilometraje = kilometraje;
	}



	@Override
	public String toString() {
		return "Gastos [id=" + id + ", coche_id=" + coche_id + ", tipo=" + tipo + ", fecha=" + fecha + ", importe="
				+ importe + ", descripcion=" + descripcion + ", kilometraje=" + kilometraje + "]";
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCoche_id() {
		return coche_id;
	}

	public void setCoche_id(int coche_id) {
		this.coche_id = coche_id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
