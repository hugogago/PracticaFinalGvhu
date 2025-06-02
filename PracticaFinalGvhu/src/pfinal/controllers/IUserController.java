package pfinal.controllers;

import java.sql.SQLException;
import java.util.List;

import pfinal.model.entities.Coches;
import pfinal.model.entities.FiltroGasto;
import pfinal.model.entities.Gastos;
import pfinal.model.entities.Usuarios;

public interface IUserController {

	boolean addCar(Coches carForCreate, Usuarios usuarios) throws SQLException;

	Coches findById(int idForEdit) throws Exception;

	void editCar(int idForEdit, Coches modifiedCar) throws Exception;

	void removeCar(Coches carForRemove) throws Exception;

	List<Coches> listForView(Usuarios usuario) throws SQLException;

	boolean añadirPropietario(int cocheId, String nuevoUuid) throws SQLException;

	boolean addGasto(Gastos nuevoGasto) throws SQLException;

	List<Gastos> listarGastosPorCoche(int cocheId) throws SQLException;

	List<Gastos> obtenerGastosFiltrados(int cocheId, FiltroGasto filtro) throws SQLException;

	

}
