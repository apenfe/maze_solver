package Classes;

/**
 * @author Adrián Peñalver Fernández
 * @version 1.4.0
 * 
 */

public class ChangeData{
	
	public static Session session;

	public static void changeUserData(Session currentSession) {
		
		session = currentSession;
		
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
				
			}else if(option == 1) {
				
				password();
					
			}else if(option == 2) {
				
				name();				
					
			}else if(option == 3) {
				
				nif();
					
			}else if(option == 4) {
				
				email();
					
			}else if(option == 5) {
				
				addres();
					
			}else if(option == 6) {
				
				date();
					
			}else if(option == 7) {
				
				if(deleteUser()) {
					break;
				}
					
			}else {
				
				System.out.println("\n\tSeleccione una opción válida entre [0-7]");
				Input.toContinue();
				
			}
			
		}while(true);
				
	}
	
	public static void changeUserDataAdmin(Session currentSession) {
		
		session = currentSession;
		
		//System.out.println(Config.USER);
	
		int option = -1;
		
		do {
			
			option = Input.getInt(Config.CHANGE_USER_DATA_ADMIN, false);
			
			if(option == 0) {
				
				if(Utils.confirmExit()) {
					break;
				}else {
					option = -1;
				}
					
			}else if(option == 1) {
					
				passwordAdmin();
					
			}else if(option == 2) {
				
				name();				
					
			}else if(option == 3) {
				
				nif();
					
			}else if(option == 4) {
				
				email();
					
			}else if(option == 5) {
				
				addres();
					
			}else if(option == 6) {
				
				date();
					
			}else {
				
				System.out.println("\n\tSeleccione una opción válida entre [0-6]");
				Input.toContinue();
				
			}
			
		}while(true);
				
	}
	
	private static void password() {
		
		String password = Utils.encryptMd5(Input.getString("Inserte la contraseña actual: "));
		
		if(session.db.checkPassword(session.currentUser.username,password)) {
			
			password = Input.getString("\tIntroduzca una nueva contraseña: ");
			
			if(!Utils.validatePassword(password)) {
		
				System.out.println(Config.RED+"\t\tContraseña no valida."+Config.RESET);
					
			}else {
				
				String check = Input.getString("\tRepita la contraseña: ");
				
				if(check.equals(password)) {
					
					if(session.db.changeOneData(session.currentUser.id,"password",Utils.encryptMd5(check))) {
						
						System.out.println("Contraseña cambiada correctamente");
						
					}
					
				}else {
					
					System.out.println(Config.RED+"\t\tLa contraseña no es la misma."+Config.RESET);

				}
				
			}
			
		}else {
			
			System.out.println("Contraseña incorrecta.");
			
		}
		
	}
	
	private static void passwordAdmin() {
		
		String password = Input.getString("\tIntroduzca una nueva contraseña: ");
		
		if(!Utils.validatePassword(password)) {
		
			System.out.println(Config.RED+"\t\tContraseña no valida."+Config.RESET);
					
		}else {
				
			String check = Input.getString("\tRepita la contraseña: ");
				
			if(check.equals(password)) {
					
				if(session.db.changeOneData(session.currentUser.id,"password",Utils.encryptMd5(check))) {
						
					System.out.println("Contraseña cambiada correctamente");
						
				}
					
			}else {
					
				System.out.println(Config.RED+"\t\tLa contraseña no es la misma."+Config.RESET);

			}
				
		}
		
	}
	
	private static void name() {
		
		String name = Input.getString("\tIntroduzca su nuevo nombre completo (Nombre completo y apellidos, comenzando en mayúsculas): ");
		if(!Utils.validateName(name)) {
				
			System.out.println(Config.RED+"\t\tFormato de nombre no valido."+Config.RESET);
		
		}else {
			
			if(session.db.changeOneData(session.currentUser.id,"name",name)) {
				
				System.out.println("Nombre cambiado correctamente");
				session.currentUser.setName(name);
				
			}else {
				
				System.out.println("error al cambiar nombre");

			}
			
		}
		
	}
	
	private static void email() {
		
		String email = Input.getString("\tIntroduzca un nuevo email: ");
		
		if(!Utils.validateEmail(email)) {
					
			System.out.println(Config.RED+"\t\tFormato de Email incorrecto."+Config.RESET);

		}else {
			
			if(session.db.checkField("email",email)) { // COMPRUEBA QUE SEAN UNICAS LAS PK
				
				System.out.println(Config.RED+"\n\t\tLos datos no son unicos en la base de datos."+Config.RESET);
			
			}else {
				
				if(session.db.changeOneData(session.currentUser.id,"email",email)) {
					
					System.out.println("Email cambiado correctamente");
					session.currentUser.setEmail(email);
					
				}else {
					
					System.out.println("error al cambiar email");

				}
				
			}
			
		}
		
	}
	
	private static void addres() {
		
		String address = Input.getString("\tIntroduzca una dirección postal: ");
		
		if(session.db.changeOneData(session.currentUser.id,"address",address)) {
			
			System.out.println("Direccion postal cambiada correctamente");
			session.currentUser.setAddres(address);
			
		}else {
			
			System.out.println("error al cambiar direccion postal");

		}
		
	}
	
	private static void nif() {
		
		String nif = Input.getString("\tIntroduzca un nuevo NIF: ");
		
		if(!Utils.validateNif(nif)) {
					
			System.out.println(Config.RED+"\t\tFormato de NIF incorrecto."+Config.RESET);

		}else {
			
			if(session.db.checkField("nif",nif)) { // COMPRUEBA QUE SEAN UNICAS LAS PK
				
				System.out.println(Config.RED+"\n\t\tLos datos no son unicos en la base de datos."+Config.RESET);
			
			}else {
				
				if(session.db.changeOneData(session.currentUser.id,"nif",nif)) {
					
					System.out.println("NIF cambiado correctamente");
					session.currentUser.setNif(nif);
					
				}else {
					
					System.out.println("error al cambiar NIF");

				}
				
			}
			
		}
	}
	
	private static void date() {
		
		String date = Input.getString("\tIntroduzca su nueva fecha de nacimiento (DD/MM/AA): "); 
		if(!Utils.validateDate(date)) {
				
			System.out.println(Config.RED+"\t\tFormato de fecha no valido."+Config.RESET);
				
		}else {
			
			String date2 = Utils.formatDateSQL(date);
			
			if(session.db.changeOneData(session.currentUser.id,"birthdate",date2)) {
				
				System.out.println("Fecha de nacimiento cambiada correctamente");
				session.currentUser.setBirthdate(date2);
				
			}else {
				
				System.out.println("error al cambiar la fecha de nacimiento");

			}
			
		}
		
	}
	
	public static boolean deleteUser() {
		
		String password = Utils.encryptMd5(Input.getString("Inserte la contraseña actual: "));
		
		if(session.db.checkPassword(session.currentUser.username,password)) {
			
			if(session.db.deleteUser(session.currentUser.id)) {
				
				System.out.println(Config.LOGOUT);
				
				System.out.println("\n\tSESION CERRADA");
				System.out.println("\tUSUARIO ELIMINADO");
				session.logged=false; // SE PONE A FALSE PARA VOLVER A MENU INICIAL
				session.currentUser= new User(); // EL USUARIO SE PONE A NULL
				return true;
				
			}else {
				
				System.out.println("No se ha podido eliminar el usuario");
				
			}
			
		}else {
			
			System.out.println("Contraseña incorrecta.");
			
		}
		
		return false;
		
	}
	
	public static boolean deleteUserAdmin(Session currentSession) {
		
		String name = currentSession.currentUser.username;
		
		if(Utils.confirmExit("¿SEGURO DESEA BORRAR EL USUARIO? S - SI ","S")) {
			
			if(currentSession.db.deleteUser(currentSession.currentUser.id)) {
				
				System.out.println("\tUSUARIO ELIMINADO: "+Config.YELLOW+name+Config.RESET);
				return true;
					
			}else {
					
				System.out.println("No se ha podido eliminar el usuario");
				
			}
		
		}
		
		return false;
		
	}
	
}