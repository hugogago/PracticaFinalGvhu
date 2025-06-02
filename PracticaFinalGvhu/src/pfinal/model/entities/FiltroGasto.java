package pfinal.model.entities;

import java.time.LocalDate;

public class FiltroGasto {
	private String tipo;
	private LocalDate fecha;
	private Integer kilometraje;
	
	
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Integer getKilometraje() {
		return kilometraje;
	}
	public void setKilometraje(Integer kilometraje) {
		this.kilometraje = kilometraje;
	}
	
	
	
}
