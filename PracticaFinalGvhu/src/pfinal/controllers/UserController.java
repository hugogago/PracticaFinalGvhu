package pfinal.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import pfinal.model.IModel;
import pfinal.model.Model;
import pfinal.model.entities.Coches;
import pfinal.model.entities.FiltroGasto;
import pfinal.model.entities.Gastos;
import pfinal.model.entities.Usuarios;

public class UserController implements IUserController {
private IModel model;
	
	public UserController() throws ClassNotFoundException, SQLException, IOException {
	
		this.model = new Model();
	}
	
	public boolean addCar(Coches carForCreate, Usuarios usuarios) throws SQLException {
		return model.add(carForCreate, usuarios);
	}
	
	public Coches findById(int id) throws Exception {
		Coches coches = model.findById(id);
		return coches;
	}
	
	public void editCar(int id, Coches modifiedCar) throws Exception {
		model.editCar(id, modifiedCar);
	}
	
	public void removeCar(Coches carForRemove) throws Exception {
		model.removeCar(carForRemove);
	}
	
	public List<Coches> listForView(Usuarios usuario) throws SQLException{
		List<Coches> list = model.list(usuario);
		return list;
	}
	
	public boolean añadirPropietario(int cocheId, String uuidNuevoPropietario) throws SQLException {
		return model.añadirPropietario(cocheId, uuidNuevoPropietario );
	}
	
	public boolean addGasto(Gastos gasto) throws SQLException {
		return model.addGasto(gasto);
	}
	
	public List<Gastos> listarGastosPorCoche(int cocheId) throws SQLException{
		List<Gastos> list = model.listarGastosPorCoche(cocheId);
		return list;
	}

	@Override
	public List<Gastos> obtenerGastosFiltrados(int cocheId, FiltroGasto filtro)  throws SQLException {
		List<Gastos> list = model.obtenerGastosFiltrados(cocheId, filtro);
		return list;
	}
}
