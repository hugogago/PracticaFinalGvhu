package pfinal.views;

import java.io.IOException;
import java.sql.SQLException;

import pfinal.controllers.MainController;
import pfinal.model.entities.Usuarios;
import pfinal.utils.TerminalUtils;



public class RegisterView {
private MainController mainController;
	
	public RegisterView() throws ClassNotFoundException, SQLException, IOException {
		super();
		this.mainController = new MainController();
	}

	public void present() {
		TerminalUtils.out("1-Registrar");
		TerminalUtils.out("Nombre");
		String nombre = TerminalUtils.getString();
		boolean validacionNombre = mainController.validName(nombre);
		if(validacionNombre) {
			TerminalUtils.out("Contraseña");
			String contraseña = TerminalUtils.getString();
			
			
			Usuarios user = new Usuarios(nombre, contraseña);
			boolean result = mainController.register(user);
			System.out.println("Resultado del registro: " + result);
		} else {
			System.out.println("Nombre de usuario ya en uso");
		}
		
		
	}
	
}
