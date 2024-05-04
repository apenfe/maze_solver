package Classes;

public class ChangeData{

	public static void changeUserData(Session currentSession, boolean admin, boolean delete) {
		
		System.out.println(Config.USER);
	
		int option = -1;
		
		do {
			
			option = Input.getInt(Config.CHANGE_USER_DATA, false);
			
			if(option == 0) {
				
				if(Utils.confirmExit()) {
					break;
				}else {
					option = -1;
				}
				
			}else if(option == 1 && admin==false) {
				
				String password = Utils.encryptMd5(Input.getString("Inserte la contraseña actual: "));
				
				if(currentSession.db.checkPassword(currentSession.currentUser.username,password)) {
					
					password = Input.getString("\tIntroduzca una nueva contraseña: ");
					
					if(!Utils.validatePassword(password)) {
				
						System.out.println(Config.RED+"\t\tContraseña no valida."+Config.RESET);
							
					}else {
						
						String check = Input.getString("\tRepita la contraseña: ");
						
						if(check.equals(password)) {
							
							if(currentSession.db.changeOneData(currentSession.currentUser.id,"password",Utils.encryptMd5(check))) {
								
								System.out.println("Contraseña cambiada correctamente");
								
							}
							
						}else {
							
							System.out.println(Config.RED+"\t\tLa contraseña no es la misma."+Config.RESET);

						}
						
					}
					
				}else {
					
					System.out.println("Contraseña incorrecta.");
					
				}
					
			}else if(option == 1 && admin==true) {
					
				String password = Input.getString("\tIntroduzca una nueva contraseña: ");
					
				if(!Utils.validatePassword(password)) {
				
					System.out.println(Config.RED+"\t\tContraseña no valida."+Config.RESET);
							
				}else {
						
					String check = Input.getString("\tRepita la contraseña: ");
						
					if(check.equals(password)) {
							
						if(currentSession.db.changeOneData(currentSession.currentUser.id,"password",Utils.encryptMd5(check))) {
								
							System.out.println("Contraseña cambiada correctamente");
								
						}
							
					}else {
							
						System.out.println(Config.RED+"\t\tLa contraseña no es la misma."+Config.RESET);

					}
						
				}
					
			}else if(option == 2) {
				
				String name = Input.getString("\tIntroduzca su nuevo nombre completo (Nombre completo y apellidos, comenzando en mayúsculas): ");
				if(!Utils.validateName(name)) {
						
					System.out.println(Config.RED+"\t\tFormato de nombre no valido."+Config.RESET);
				
				}else {
					
					if(currentSession.db.changeOneData(currentSession.currentUser.id,"name",name)) {
						
						System.out.println("Nombre cambiado correctamente");
						currentSession.currentUser.setName(name);
						
					}else {
						
						System.out.println("error al cambiar nombre");

					}
					
				}
				
					
			}else if(option == 3) {
				
				String nif = Input.getString("\tIntroduzca un nuevo NIF: ");
				
				if(!Utils.validateNif(nif)) {
							
					System.out.println(Config.RED+"\t\tFormato de NIF incorrecto."+Config.RESET);

				}else {
					
					if(currentSession.db.checkField("nif",nif)) { // COMPRUEBA QUE SEAN UNICAS LAS PK
						
						System.out.println(Config.RED+"\n\t\tLos datos no son unicos en la base de datos."+Config.RESET);
					
					}else {
						
						if(currentSession.db.changeOneData(currentSession.currentUser.id,"nif",nif)) {
							
							System.out.println("NIF cambiado correctamente");
							currentSession.currentUser.setNif(nif);
							
						}else {
							
							System.out.println("error al cambiar NIF");

						}
						
					}
					
				}
				
					
			}else if(option == 4) {
				
				String email = Input.getString("\tIntroduzca un nuevo email: ");
				
				if(!Utils.validateEmail(email)) {
							
					System.out.println(Config.RED+"\t\tFormato de Email incorrecto."+Config.RESET);

				}else {
					
					if(currentSession.db.checkField("email",email)) { // COMPRUEBA QUE SEAN UNICAS LAS PK
						
						System.out.println(Config.RED+"\n\t\tLos datos no son unicos en la base de datos."+Config.RESET);
					
					}else {
						
						if(currentSession.db.changeOneData(currentSession.currentUser.id,"email",email)) {
							
							System.out.println("Email cambiado correctamente");
							currentSession.currentUser.setEmail(email);
							
						}else {
							
							System.out.println("error al cambiar email");

						}
						
					}
					
				}
					
			}else if(option == 5) {
				
				String address = Input.getString("\tIntroduzca una dirección postal: ");
				
				if(currentSession.db.changeOneData(currentSession.currentUser.id,"address",address)) {
					
					System.out.println("Direccion postal cambiada correctamente");
					currentSession.currentUser.setAddres(address);
					
				}else {
					
					System.out.println("error al cambiar direccion postal");

				}
					
			}else if(option == 6) {
				
				String date = Input.getString("\tIntroduzca su nueva fecha de nacimiento (DD/MM/AA): "); 
				if(!Utils.validateDate(date)) {
						
					System.out.println(Config.RED+"\t\tFormato de fecha no valido."+Config.RESET);
						
				}else {
					
					String date2 = Utils.formatDateSQL(date);
					
					if(currentSession.db.changeOneData(currentSession.currentUser.id,"birthdate",date2)) {
						
						System.out.println("Fecha de nacimiento cambiada correctamente");
						currentSession.currentUser.setBirthdate(date2);
						
					}else {
						
						System.out.println("error al cambiar la fecha de nacimiento");

					}
					
				}
				
					
			}else if(option == 7 && admin==false) {
				
				String password = Utils.encryptMd5(Input.getString("Inserte la contraseña actual: "));
				
				if(currentSession.db.checkPassword(currentSession.currentUser.username,password)) {
					
					if(currentSession.db.deleteUser(currentSession.currentUser.id)) {
						
						System.out.println(Config.LOGOUT);
						
						System.out.println("\n\tSESION CERRADA");
						System.out.println("\tUSUARIO ELIMINADO");
						currentSession.logged=false; // SE PONE A FALSE PARA VOLVER A MENU INICIAL
						currentSession.currentUser= new User(); // EL USUARIO SE PONE A NULL
						break;
						
					}else {
						
						System.out.println("No se ha podido eliminar el usuario");
						
					}
					
				}else {
					
					System.out.println("Contraseña incorrecta.");
					
				}
					
			}else if(option == 7 && admin==true && delete==true) {
					
				if(currentSession.db.deleteUser(currentSession.currentUser.id)) {
						
					System.out.println("\tUSUARIO ELIMINADO");
					break;
						
				}else {
						
					System.out.println("No se ha podido eliminar el usuario");
						
				}
					
			}else {
				
				System.out.println("\n\tSeleccione una opción válida entre [0-7]");
				Input.toContinue();
				
			}
			
		}while(true);
				
	}
	
}