package pfinal.views;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;



import pfinal.controllers.IMainController;
import pfinal.controllers.IUserController;
import pfinal.controllers.UserController;
import pfinal.model.entities.Coches;
import pfinal.model.entities.FiltroGasto;
import pfinal.model.entities.Gastos;
import pfinal.model.entities.Usuarios;
import pfinal.utils.TerminalUtils;

public class UserView {
private IUserController userController;
	
	public UserView() throws ClassNotFoundException, SQLException, IOException {
		super();
		this.userController = new UserController();
	}

	public void present(Usuarios user) {
		int option = 0;
		do {
			TerminalUtils.out("Menú coches");
			TerminalUtils.out("================");

			TerminalUtils.out("1.- Lista de coches");
			TerminalUtils.out("2.- Crear coche");
			TerminalUtils.out("3.- Editar coche");
			TerminalUtils.out("4.- Eliminar coche");
			TerminalUtils.out("5.- Añadir Propietario");
			TerminalUtils.out("6.- Añadir gasto a coche");
			TerminalUtils.out("7.- Ver gastos de un coche");
			
			TerminalUtils.out("0.- Salir");
			option = TerminalUtils.getInt();
			switch (option) {
			case 0: //Salir
				TerminalUtils.out("Volvemos al Menú principal");
				break;
			case 1: //Lista de coches
				try {
					TerminalUtils.out("Lista de salas");
					List<Coches> listForPrint = userController.listForView(user);
					list(listForPrint);
				} catch (Exception e) {
					showError(e);
				}
				break;
			case 2: //Crear coche
				try {
					TerminalUtils.out("Crear coche");
					Coches carForCreate = add();
					userController.addCar(carForCreate, user);
				} catch (Exception e){
					showError(e);
				}
				
				
				break;
			case 3: //Editar coche
			try {
				TerminalUtils.out("Editar coche");
				List<Coches> listForEdit = userController.listForView(user);
				list(listForEdit);
				
				int idForEdit = selectId();
				Coches carForEdit = userController.findById(idForEdit);
				
				if(carForEdit != null) {
					Coches modifiedCar = editCar(carForEdit);
					userController.editCar(idForEdit, modifiedCar);
				} else {
					show("No se ha encontrado una sala para el id introducido");
				}
			}catch (Exception e) {
				showError(e);
			}
				break;
			case 4: //Eliminar Coche
				try {
					TerminalUtils.out("Eliminar coche");
					List<Coches> listForRemove = userController.listForView(user);
					list(listForRemove);
					
					int idForRemove = selectId();
					Coches carForRemove = userController.findById(idForRemove);
					
					if(carForRemove != null) {
						userController.removeCar(carForRemove);
					} else {
						show("No se ha encontrado una sala para el id introducido");
					}
					
				}catch (Exception e){
					showError(e);
				}
			
				break;
			case 5: //Añadir propietario
				try {
					TerminalUtils.out("Añadir propietario");
					List<Coches> listUserCars = userController.listForView(user);
			        list(listUserCars);

			        int cocheId = selectId();
			        TerminalUtils.out("Introduce el UUID del nuevo propietario:");
			        String nuevoUuid = TerminalUtils.inputText();

			        boolean success = userController.añadirPropietario(cocheId, nuevoUuid);
			        if (success) {
			            show("Propietario añadido correctamente.");
			        } else {
			            show("No se ha podido añadir el propietario. Verifica el UUID.");
			        }
				} catch (Exception e) {
					showError(e);
				}
				
				break;
			case 6: //Añadir gasto a coche
			    try {
			        TerminalUtils.out("Añadir gasto a un coche");
			        List<Coches> coches = userController.listForView(user);
			        list(coches);
			        
			        int cocheId = selectId();
			        Gastos nuevoGasto = leerGasto(cocheId);
			        
			        boolean success = userController.addGasto(nuevoGasto);
			        if (success) {
			            show("Gasto añadido correctamente.");
			        } else {
			            show("No se pudo añadir el gasto.");
			        }
			    } catch (Exception e) {
			        showError(e);
			    }
			    break;
			case 7:
			    try {
			        TerminalUtils.out("Ver gastos de un coche");
			        List<Coches> coches = userController.listForView(user);
			        list(coches);
			        int cocheId = selectId();

			        FiltroGasto filtro = leerFiltros();

			        List<Gastos> gastos = userController.obtenerGastosFiltrados(cocheId, filtro);
			        mostrarGastos(gastos);
			    } catch (Exception e) {
			        showError(e);
			    }
			    break;



			}
		} while (option != 0);
	}
	
	public void list(List<Coches> list) {
		TerminalUtils.out("Lista de coches");
		TerminalUtils.out("Id - Marca - Modelo - Matricula - Año");
		
		for (Coches coches : list) {
			TerminalUtils.out(coches.toString());
		}
	}
	
	public Coches add() {
		Coches coche = new Coches();
		TerminalUtils.out("Nueva coche");
		TerminalUtils.out("================");
		
		TerminalUtils.out("Introduzca la marca");
		String marca = TerminalUtils.inputText();
		coche.setMarca(marca);
		
		TerminalUtils.out("Introduzca el modelo");
		String modelo = TerminalUtils.inputText();
		coche.setModelo(modelo);
		
		TerminalUtils.out("Introduzca la matricula");
		String matricula = TerminalUtils.inputText();
		coche.setMatricula(matricula);
		
		TerminalUtils.out("Introduzca el año");
		int anio = TerminalUtils.getInt();
		coche.setAnio(anio);
		
		
		
		return coche;
	}
	public int selectId() {
		TerminalUtils.out("Introduzca el id");
		int id = TerminalUtils.getInt();	
		return id;
	}
	public Coches editCar(Coches carForEdit) {
		TerminalUtils.out("Editar coche");
		TerminalUtils.out("================");
		
		TerminalUtils.out("Introduzca el nuevo nombre de la marca (si lo deja vacío no se modificará)");
		String marca = TerminalUtils.inputText();
		if(isValidString(marca)) {
			carForEdit.setMarca(marca);
		}
		
		TerminalUtils.out("Introduzca el nuevo modelo (si lo deja vacío no se modificará)");
		String modelo = TerminalUtils.inputText();
		if(isValidString(modelo)) {
			carForEdit.setModelo(modelo);
		}
		
		TerminalUtils.out("Introduzca la nueva matricula (si lo deja vacío no se modificará)");
		String matricula = TerminalUtils.inputText();
		if(isValidString(matricula)) {
			carForEdit.setModelo(matricula);
		}
		
		
		TerminalUtils.out("Introduzca el nuevo año(si lo deja vacío no se modificará)");
		String anio = TerminalUtils.inputText();
		if(isValidString(anio)) {
			carForEdit.setAnio(Integer.parseInt(anio));
		}
		
		
		return carForEdit;
		
		
		
	}
	
	private Gastos leerGasto(int cocheId) {
		Gastos gastos = new Gastos();
		gastos.setCoche_id(cocheId);
		
		TerminalUtils.out("Introduzca el tipo de gasto (Gasolina, revisión, ITV, cambio de aceite, otros)");
		String tipo = TerminalUtils.inputText();
		if(isValidString(tipo)) {
			gastos.setTipo(tipo);
		}
		
		
		TerminalUtils.out("Introduzca el kilometraje (si lo deja vacío no se modificará)");
		String km = TerminalUtils.inputText();
		if(isValidString(km)) {
			gastos.setKilometraje(Integer.parseInt(km));
		}
		
	    TerminalUtils.out("Fecha (YYYY-MM-DD):");
	    String fechaStr = TerminalUtils.inputText();
	    java.sql.Date fecha = java.sql.Date.valueOf(fechaStr);
	    gastos.setFecha(fecha);
	    
	    TerminalUtils.out("Introduzca el importe (si lo deja vacío no se modificará)");
		String importe = TerminalUtils.inputText();
		if(isValidString(importe)) {
			gastos.setImporte(Integer.parseInt(importe));
		}
		
		TerminalUtils.out("Introduzca el tipo de gasto (si lo deja vacío no se modificará)");
		String descripcion = TerminalUtils.inputText();
		if(isValidString(descripcion)) {
			gastos.setDescripcion(descripcion);
		}
	    
	    return gastos;
	}

	private void mostrarGastos(List<Gastos> gastos) {
	    if (gastos == null || gastos.isEmpty()) {
	        TerminalUtils.out("No hay gastos registrados para este coche.");
	        return;
	    }

	    TerminalUtils.out("ID - Tipo - Km - Fecha - Importe - Descripción");
	    for (Gastos g : gastos) {
	        TerminalUtils.out(g.getId() + " - " + g.getTipo() + " - " +
	            g.getKilometraje() + "km - " + g.getFecha() + " - " +
	            g.getImporte() + "€ - " + g.getDescripcion());
	    }
	}
	
	private FiltroGasto leerFiltros() {
	    TerminalUtils.out("Filtrado de gastos:");
	    TerminalUtils.out("Introduce el tipo (o 'Todos'):");
	    String tipo = TerminalUtils.inputText();

	    TerminalUtils.out("Introduce fecha exacta (yyyy-mm-dd) o 'Todos':");
	    String fecha = TerminalUtils.inputText();

	    TerminalUtils.out("Introduce el kilometraje (o 'Todos'):");
	    String km = TerminalUtils.inputText();

	    FiltroGasto filtro = new FiltroGasto();

	    if (!tipo.equalsIgnoreCase("Todos")) {
	        filtro.setTipo(tipo);
	    }

	    if (!fecha.equalsIgnoreCase("Todos")) {
	        try {
	            filtro.setFecha(LocalDate.parse(fecha));
	        } catch (DateTimeParseException e) {
	            TerminalUtils.out("Fecha no válida. Asegúrate de usar el formato yyyy-mm-dd.");
	            // Manejar el error según sea necesario
	        }
	    }

	    if (!km.equalsIgnoreCase("Todos")) {
	        try {
	            filtro.setKilometraje(Integer.parseInt(km));
	        } catch (NumberFormatException e) {
	            TerminalUtils.out("Kilometraje no válido. Debe ser un número.");
	            // Manejar el error según sea necesario
	        }
	    }

	    return filtro;
	}



	
	public boolean isValidString(String value) {
		return value != null && !value.isEmpty() && !value.isBlank();
	}
	public void showError(Exception e) {
		TerminalUtils.out(e.getMessage());
	}
	private void show(String message) {
		TerminalUtils.out(message);
	}

}
