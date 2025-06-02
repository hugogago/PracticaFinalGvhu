package pfinal.model.entities;

public class Propietarios {
	private int coche_id;
	private int usuario_id;
	
	public Propietarios(int coche_id, int usuario_id) {
		super();
		this.coche_id = coche_id;
		this.usuario_id = usuario_id;
	}

	@Override
	public String toString() {
		return "Propietarios [coche_id= " + coche_id + ", usuario_id= " + usuario_id + "]";
	}

	public int getCoche_id() {
		return coche_id;
	}

	public void setCoche_id(int coche_id) {
		this.coche_id = coche_id;
	}

	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	
	
}
