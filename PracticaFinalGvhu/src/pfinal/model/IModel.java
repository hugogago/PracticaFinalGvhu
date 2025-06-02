package pfinal.model;

import java.sql.SQLException;
import java.util.List;

import pfinal.model.entities.Coches;
import pfinal.model.entities.FiltroGasto;
import pfinal.model.entities.Gastos;
import pfinal.model.entities.Usuarios;

public interface IModel {

	boolean register(Usuarios usuarios);

	Usuarios byName(String name);

	boolean add(Coches coche, Usuarios usuario) throws SQLException;

	boolean validName(String nombre);

	Coches findById(int id) throws Exception;

	void editCar(int id, Coches modifiedCar) throws Exception;

	void removeCar(Coches carForRemove) throws Exception;

	List<Coches> list(Usuarios usuario) throws SQLException;

	boolean añadirPropietario(int cocheId, String uuidNuevoPropietario) throws SQLException;
	
	boolean addGasto(Gastos gasto) throws SQLException;
	
	List<Gastos> listarGastosPorCoche(int cocheId) throws SQLException;

	List<Gastos> obtenerGastosFiltrados(int cocheId, FiltroGasto filtro) throws SQLException;
	

}
