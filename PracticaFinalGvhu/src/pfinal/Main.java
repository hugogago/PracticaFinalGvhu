package pfinal;

import pfinal.model.DatabaseConnection;
import pfinal.views.MenuPrincipal;

public class Main {
	public static void main(String[] args) {
		try {
			DatabaseConnection.getConnection();

			System.out.println("Conectado correctamente a Base de datos");

			MenuPrincipal menu = new MenuPrincipal();
			menu.present();
			//AuthViewSg authViewSg = new AuthViewSg();
			//authViewSg.present();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
