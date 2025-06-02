package pfinal.controllers;

import java.io.IOException;
import java.sql.SQLException;



import pfinal.model.IModel;
import pfinal.model.Model;
import pfinal.model.entities.Usuarios;
import pfinal.utils.SecurePassword;


public class MainController implements IMainController {
	
	private IModel model;
	
	public MainController() throws ClassNotFoundException, SQLException, IOException {
	
		this.model = new Model();
	}
	
	public boolean validName(String nombre) {
		boolean result = this.model.validName(nombre);
		return result;
	}
	
	public boolean register(Usuarios usuarios) {
		
		String encrypted = SecurePassword.hashPassword(usuarios.getUserPassword());

		usuarios.setUserPassword(encrypted);
		
		boolean result = this.model.register(usuarios);
		
		return result;
	}

	
	public Usuarios login(Usuarios usuario) {
		
		Usuarios user = this.model.byName(usuario.getName());
		
		if(user == null) {
			return null;
		} else {
			boolean result =  SecurePassword.checkPassword(usuario.getPassword(), user.getPassword());
			if (result) {
				return user;
				
			} else {
				return null;
			}
			
		}
	} 
}
