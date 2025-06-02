package pfinal.views;

import java.io.IOException;
import java.sql.SQLException;

import pfinal.utils.TerminalUtils;

public class MenuPrincipal {
	private RegisterView registerView;
	private LoginView loginView;
	
	
	public MenuPrincipal() throws ClassNotFoundException, SQLException, IOException {
		this.registerView = new RegisterView();
		this.loginView = new LoginView();
		
	}

	public void present() {
		int option;
		do {
			TerminalUtils.out("Menú de autenticación"
					+ "\n======================="
					+ "\n1- Register"
					+ "\n2- Login"
					+ "\n0- Salir"
					+ "\n-----------------------"
					+ "\nIntroduzca una opción: ");
			option = TerminalUtils.getInt();
			switch (option) {
			case 0:
				TerminalUtils.out("Adios");
				break;
			case 1:
				this.registerView.present();
				break;
			case 2: 
				this.loginView.present();			
				break;
			default:
				TerminalUtils.out("Unexpected value: " + option);
				option = -1;
			}
		} while (option != 0);
	}
}
