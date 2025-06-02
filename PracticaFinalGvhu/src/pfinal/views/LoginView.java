package pfinal.views;

import java.io.IOException;
import java.sql.SQLException;

import pfinal.controllers.IMainController;
import pfinal.controllers.MainController;
import pfinal.model.entities.Usuarios;
import pfinal.utils.TerminalUtils;



public class LoginView {
private IMainController mainController;
private UserView userView;
	
	public LoginView() throws ClassNotFoundException, SQLException, IOException {
		super();
		this.mainController = new MainController();
		this.userView = new UserView();
	}

	public void present() {
		TerminalUtils.out("2- login");
		TerminalUtils.out("Name");
		String name = TerminalUtils.getString();
		TerminalUtils.out("Password");
		String password = TerminalUtils.getString();
		
		Usuarios user = new Usuarios(name, password);
		Usuarios userDB = mainController.login(user);
		
		if(userDB == null) {
			System.out.println("Error al login");
		} else {
			System.out.println("Login correcto");
			this.userView.present(userDB);
		}
		
		
	}
}
