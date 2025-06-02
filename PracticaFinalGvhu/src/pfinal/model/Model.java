package pfinal.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;



import pfinal.model.entities.Coches;
import pfinal.model.entities.FiltroGasto;
import pfinal.model.entities.Gastos;
import pfinal.model.entities.Usuarios;



public class Model implements IModel{

	private Connection connection;
	
	public Model() throws ClassNotFoundException, SQLException, IOException {
		
		this.connection = DatabaseConnection.getConnection();
	}
	
	
	public boolean validName(String nombre) {
	    String query = "SELECT COUNT(*) FROM Usuarios WHERE nombre = ?";
	    
	    try {
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, nombre);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count == 0; // true si no existe, false si ya está tomado
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Puedes loguear o manejar de otra forma
	    }

	    return false; // En caso de error o si no hay resultado
	}

	
	public boolean register(Usuarios usuarios) {
		try {
			String query = "INSERT INTO usuarios (nombre, contraseña, uuid) values (?, ?, ?)";
			PreparedStatement ps1 = connection.prepareStatement(query);
			
			ps1.setString(1, usuarios.getNombre());
			ps1.setString(2, usuarios.getContraseña());
			ps1.setString(3, usuarios.getUuid());
			
			
			ps1.executeUpdate();
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	
	public Usuarios byName(String nombre) {
		String query = "SELECT id, nombre, contraseña, uuid FROM usuarios WHERE nombre like ?";
		
		try {
			PreparedStatement ps2 = connection.prepareStatement(query);

			ps2.setString(1, nombre);
			
			
			ResultSet rs = ps2.executeQuery();
			
			if (rs.next()) {
	            System.out.println("Si encontrado");
	            int id = rs.getInt(1);
	            String nombreLog = rs.getString(2);
	            String contraseña = rs.getString(3);
	            String uuid = rs.getString(4);
				
				

				Usuarios user = new Usuarios(nombreLog,contraseña);
				user.setId(id);
				user.setUuid(uuid);
				return user;
				
			} else {
	            return null;
	        }
		} catch (Exception e) {
            return null;
		}
	} 
	
	@Override
	public boolean add(Coches coche, Usuarios usuario) throws SQLException {
	    String query = "INSERT INTO Coches (marca, modelo, matricula, anio) VALUES (?, ?, ?, ?)";
	    String query2 = "INSERT INTO CochesPropietarios (coche_id, uuid) VALUES (?, ?)";

	    try {
	        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

	        ps.setString(1, coche.getMarca());
	        ps.setString(2, coche.getModelo());
	        ps.setString(3, coche.getMatricula());
	        ps.setInt(4, coche.getAnio());

	        int affectedRows = ps.executeUpdate();
	        if (affectedRows == 0) {
	            return false;
	        }

	        ResultSet cocheID = ps.getGeneratedKeys();

	        if (cocheID.next()) {
	            PreparedStatement ps2 = connection.prepareStatement(query2);
	            ps2.setInt(1, cocheID.getInt(1));
	            ps2.setString(2, usuario.getUuid());

	            int res = ps2.executeUpdate();
	            return res > 0;
	        } else {
	            return false;
	        }

	    } catch (Exception e) {
	        e.printStackTrace(); 
	        return false;
	    }
	}

	
	@Override
	public Coches findById(int id) throws Exception {
		String query = "SELECT  marca, modelo, matricula, anio FROM Coches where id = ?";
		PreparedStatement ps = connection.prepareStatement(query);
		Coches coche = null;
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		List<Coches> list = new ArrayList<Coches>();
		while(rs.next()) {
			String marca = rs.getString(1);
			String modelo = rs.getString(2);
			String matricula = rs.getString(3);
			int anio = rs.getInt(4);
			
			
			coche = new Coches( marca, modelo, matricula, anio);
			coche.setId(id);
			
		}
		return coche;
	}
	
	public void editCar( int id, Coches modifiedCar) throws Exception {
		
		String query = "UPDATE Coches set marca = ?, modelo = ?, matricula = ?, anio = ? where id = ?";
		PreparedStatement ps = connection.prepareStatement(query);
		
		
		ps.setString(1, modifiedCar.getMarca());
		ps.setString(2, modifiedCar.getModelo());
		ps.setString(3, modifiedCar.getMatricula());
		ps.setInt(4, modifiedCar.getAnio());
		ps.setInt(5, id);
		
		ps.executeUpdate();
		
	}
	
	@Override
	public void removeCar(Coches carForRemove) throws Exception {
	    // 1. Eliminar relaciones en CochesPropietarios
	    String deleteRelations = "DELETE FROM CochesPropietarios WHERE coche_id = ?";
	    PreparedStatement ps1 = connection.prepareStatement(deleteRelations);
	    ps1.setInt(1, carForRemove.getId());
	    ps1.executeUpdate();

	    // 2. Eliminar el coche
	    String deleteCar = "DELETE FROM Coches WHERE id = ?";
	    PreparedStatement ps2 = connection.prepareStatement(deleteCar);
	    ps2.setInt(1, carForRemove.getId());
	    ps2.executeUpdate();
	}

	
	@Override
	public List<Coches> list(Usuarios usuario) throws SQLException {
		String query = "SELECT c.id, c.marca, c.modelo, c.matricula, c.anio FROM Coches c JOIN CochesPropietarios p ON c.id = p.coche_id JOIN Usuarios u ON p.uuid = u.uuid WHERE  u.uuid = ? ";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, usuario.getUuid());
		
		ResultSet rs = ps.executeQuery();
		List<Coches> list = new ArrayList<Coches>();
		while(rs.next()) {
			int id = rs.getInt(1);
			String marca = rs.getString(2);
			String modelo = rs.getString(3);
			String matricula = rs.getString(4);
			int anio = rs.getInt(5);
			
			Coches coche = new Coches(marca, modelo, matricula, anio);
			coche.setId(id);
			list.add(coche);
		}
		return list;
	}
	
	
	public boolean añadirPropietario(int cocheId, String uuidNuevoPropietario) throws SQLException {
	    String query = "INSERT INTO CochesPropietarios (coche_id, uuid) VALUES (?, ?)";
	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setInt(1, cocheId);
	    ps.setString(2, uuidNuevoPropietario);
	    int res = ps.executeUpdate();
	    return res > 0;
	}
	
	
	public boolean addGasto(Gastos gasto) throws SQLException {
	    String query = "INSERT INTO Gastos (coche_id, tipo, kilometraje, fecha, importe, descripcion) VALUES (?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement ps = connection.prepareStatement(query)) {
	        ps.setInt(1, gasto.getCoche_id());
	        ps.setString(2, gasto.getTipo());
	        ps.setInt(3, gasto.getKilometraje());
	        ps.setDate(4, new java.sql.Date(gasto.getFecha().getTime()));
	        ps.setDouble(5, gasto.getImporte());
	        ps.setString(6, gasto.getDescripcion());

	        return ps.executeUpdate() > 0;
	    }
	}
	
	public List<Gastos> listarGastosPorCoche(int cocheId) throws SQLException {
	    List<Gastos> gastos = new ArrayList<>();
	    String query = "SELECT * FROM Gastos WHERE coche_id = ?";
	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setInt(1, cocheId);
	    ResultSet rs = ps.executeQuery();

	    while (rs.next()) {
	        Gastos gasto = new Gastos();
	        gasto.setId(rs.getInt("id"));
	        gasto.setCoche_id(rs.getInt("coche_id"));
	        gasto.setTipo(rs.getString("tipo"));
	        gasto.setKilometraje(rs.getInt("kilometraje"));
	        gasto.setFecha(rs.getDate("fecha"));
	        gasto.setImporte(rs.getFloat("importe"));
	        gasto.setDescripcion(rs.getString("descripcion"));
	        gastos.add(gasto);
	    }

	    return gastos;
	}
	
	public List<Gastos> obtenerGastosFiltrados(int cocheId, FiltroGasto filtro) throws SQLException {
	    StringBuilder query = new StringBuilder(
	        "SELECT tipo, kilometraje, fecha, importe, descripcion FROM Gastos WHERE coche_id = ?"
	    );
	    List<Object> params = new ArrayList<>();
	    params.add(cocheId);

	    if (filtro.getTipo() != null) {
	        query.append(" AND tipo = ?");
	        params.add(filtro.getTipo());
	    }

	    if (filtro.getFecha() != null) {
	        query.append(" AND fecha = ?");
	        params.add(java.sql.Date.valueOf(filtro.getFecha()));
	    }

	    if (filtro.getKilometraje() != null) {
	        query.append(" AND kilometraje = ?");
	        params.add(filtro.getKilometraje());
	    }

	    try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
	        for (int i = 0; i < params.size(); i++) {
	            ps.setObject(i + 1, params.get(i));
	        }

	        ResultSet rs = ps.executeQuery();
	        List<Gastos> gastos = new ArrayList<>();

	        while (rs.next()) {
	            String tipo = rs.getString("tipo");
	            int kilometraje = rs.getInt("kilometraje");
	            Date fecha = rs.getDate("fecha");
	            BigDecimal importe = rs.getBigDecimal("importe");
	            String descripcion = rs.getString("descripcion");

	            Gastos gasto = new Gastos();
	            gasto.setTipo(tipo);
	            gasto.setKilometraje(kilometraje);
	            gasto.setFecha(fecha);
	            gasto.setImporte(importe.floatValue());
	            gasto.setDescripcion(descripcion);
	            gasto.setCoche_id(cocheId);

	            gastos.add(gasto);
	        }

	        return gastos;
	    }
	}

	}






	

