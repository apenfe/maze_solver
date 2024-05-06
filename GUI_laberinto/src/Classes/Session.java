package Classes;

/**
 * Clase Session utilizada para realizar todas las acciones del usuario durante una sesión,
 * asi como permitir que el usuario pueda registrarse o autenticarse.
 * 
 * @author Adrián Peñalver Fernández
 * @version 1.4.0
 * 
 */

public class Session{
	
	/* ATRIBUTOS PRIVADOS DE LA CLASE */
	
	/**
	 * Usuario asignado a la sesión actual.
	 */
	protected User currentUser;
	protected DAO db;
	
	/**
	 * boolean que nos permite conocer si el usuario se ha podido autenticar o no.
	 */
	protected boolean logged;
	private static final String ERROR = Config.RED+"\n\tPor favor, corrija los campos indicados antes de continuar con el registro."+Config.RESET;
	
	/* LISTADO DE MÉTODOS DE LA CLASE */
	
	/* CONSTRUCTOR DE LA CLASE */
	
	/**
	 * Constructor de la clase, el cual inicia la sesión con una autenticación en false
	 * y crea un usuario vacio.
	 */
	
	public Session() {
		
		this.logged=false; // INICIA COMO NO LOGGED
		this.currentUser= new User(); // CREA UN OBJETO USER
		this.db= new DAO(); // CREA UN OBJETO BASE DE DATOS
		
	}
	
	/* METODO LOGIN PARA EL ACCESO DE USUARIOS */
	
	/**
	 * Este método pregunata al ususuario su contraseña y nombre de usuario.
	 * En caso de encontrar coincidencia en el archivo, se establece el usuario
	 * y se especifica la autenticación a true.
	 */
	
	public void login() {

		System.out.println(Config.LOGIN);

		String[] userdata = new String[2]; // LEE USUARIO Y CONTRASEÑA
		userdata[0] = Input.getString("\n\tIntroduzca su nombre de usuario: ");
		userdata[1] = Input.getString("\tIntroduzca su contraseña: ");
		System.out.println();
		
		userdata[1]= Utils.encryptMd5(userdata[1]);
		
		this.currentUser = db.login(userdata[0], userdata[1]);

		if (currentUser!=null) { // BUSCA UNA COOINCIDENCIA EN LA BASE DE DATOS
			
			System.out.println(Config.GREEN+"\tLogin Correcto: Bienvenido "+userdata[0]+"."+Config.RESET);
			this.logged=true;
			Log.insertLog(Log.LOGIN,"Login exitoso: "+userdata[0]);

		} else {
			
			this.currentUser=new User();
			System.out.println(Config.RED+"\t\tNombre de usuario y/o contraseña incorrectos."+Config.RESET);
			Log.insertLog(Log.LOGIN,"Login fallido: "+userdata[0]);
		}

	}
	
	/* METODO SIGNUP PARA REGISTRAR NUEVOS USUARIOS */
	
	/**
	 * Este método permite crear un nuevo usuario.
	 * Primero se pregunta por un nombre de usuario único y en caso afirmativo,
	 * se preguntan el resto de datos.
	 * 
	 * Si todos los datos son correctos, el usuario se guarda en el fichero y ya podrá
	 * utilizar el método login().
	 */
	
	public void signup() {
		
		System.out.println(Config.SIGNUP);
		
		String[] userdata = new String[7]; 
			
		if(checkData(userdata)) { // COMPRUEBA QUE SEAN VALORES VALIDOS
				
			/* EN CASO AFIRMATIVO */
			System.out.println(Config.GREEN+"\t\tTodos los datos son correctos."+Config.RESET);
			
			User newUser = new User("0",userdata[0],userdata[4],userdata[1],userdata[2],userdata[5],userdata[6],"user");
				
			if(db.signup(newUser, userdata[3])) {
					
				System.out.println(Config.GREEN+"\t\tUsuario añadido de forma correcta."+Config.RESET);
				Log.insertLog(Log.SIGNUP,"Registro exitoso: "+userdata[0]);
				
			}else {
					
				System.out.println(Config.RED+"\t\tError al guardar usuario."+Config.RESET);
				Log.insertLog(Log.SIGNUP,"Registro fallido: "+userdata[0]);
			}
				
		}
				
	}
	
	/* METODO PARA VER LA INFORMACÓN COMPLETA DEL USUARIO */
	
	/**
	 * Este método se utiliza para mostrar toda la información referente al usuario logueado.
	 */
	
	public void showUser() {
		
		System.out.println(Config.USER);
		currentUser.info();
	}
	
	public boolean isAdmin() {
		
		return currentUser.role.equals("admin");
		
	}
	
	/* METODO PARA CERRAR LA SESION DEL USUARIO */
	
	/**
	 * Este método permite cerrar la sesión del actual usuario.
	 * Cambia la autenticación a false y pone el usuario a null.
	 */
	
	public void logOut() {
		
		System.out.println(Config.LOGOUT);
		
		if(Utils.confirmExit("\n\t¿Seguro que desea cerrar la sesión? SI - s ","S")) { // PIDE CONFIRMACION ANTES DE CERRAR SESION
			System.out.println("\n\tHasta la proxima " + currentUser.username + ".");
			System.out.println("\tSESION CERRADA");
			Log.insertLog(Log.LOGOUT,currentUser.username);
			logged=false; // SE PONE A FALSE PARA VOLVER A MENU INICIAL
			this.currentUser= new User(); // EL USUARIO SE PONE A NULL
		}
		
	}
	
	/* METODO PARA COMPROBAR SI EL USUARIO HA INICIADO O NO LA SESIÓN */
	
	/**
	 * Este método permite conocer el estado de autenticación del usuario de la sesión.
	 * 
	 * @return boolean True si está autenticado y false en caso de no estarlo.
	 */
	
	public boolean isLogged() {
		
		return this.logged;
	}
	
	/* COMPRUEBA QUE LOS DATOS INTRODUCIDOS SIGAN PAUTAS CORRECTAS PARA LAS PK */
	/* SE UTILIZA EN EL METODO signUp() */
	
	private boolean checkData(String[] userdata) {
				
		userdata[0] = Input.getString("\n\tIntroduzca un nombre de usuario (@nombre): ");
				
		if(!Utils.validateUserName(userdata[0])) {
					
			System.out.println(Config.RED+"\t\tFormato de Nickname incorrecto."+Config.RESET);
			System.out.println(ERROR);
			return false;
			
		}
		
		userdata[3] = Input.getString("\tIntroduzca una contraseña: ");
		if(!Utils.validatePassword(userdata[3])) {
	
			System.out.println(Config.RED+"\t\tContraseña no valida."+Config.RESET);
			System.out.println(ERROR);
			return false;
				
		}else {
			
			String check = Input.getString("\tRepita la contraseña: ");
			
			if(!check.equals(userdata[3])) {
				
				System.out.println(Config.RED+"\t\tLa contraseña no es la misma."+Config.RESET);
				System.out.println(ERROR);
				return false;
				
			}
			
		}
		
		userdata[1] = Input.getString("\tIntroduzca un NIF (12345678X): ");
		
		if(!Utils.validateNif(userdata[1])) {
					
			System.out.println(Config.RED+"\t\tFormato de NIF incorrecto."+Config.RESET);
			System.out.println(ERROR);
			return false;

		}
		
		userdata[2] = Input.getString("\tIntroduzca un email: ");
		
		if(!Utils.validateEmail(userdata[2])) {
					
			System.out.println(Config.RED+"\t\tFormato de Email incorrecto."+Config.RESET);
			System.out.println(ERROR);
			return false;

		}
		
		User test = new User();
		test.setUsername(userdata[0]);
		test.setNif(userdata[1]);
		test.setEmail(userdata[2]);
		
		if(db.checkUser(test)) { // COMPRUEBA QUE SEAN UNICAS LAS PK
			
			System.out.println(Config.RED+"\n\t\tLos datos no son unicos en la base de datos."+Config.RESET);
			return false;
		}
			
		userdata[4] = Input.getString("\tIntroduzca su nombre completo (Nombre completo y dos apellidos, comenzando en mayúsculas): ");
		if(!Utils.validateName(userdata[4])) {
				
			System.out.println(Config.RED+"\t\tFormato de nombre no valido."+Config.RESET);
			System.out.println(ERROR);
			return false;
				
		}
			
		userdata[5] = Input.getString("\tIntroduzca una dirección postal: ");
			
		userdata[6] = Input.getString("\tIntroduzca su fecha de nacimiento (DD/MM/AA): "); 
		if(!Utils.validateDate(userdata[6])) {
				
			System.out.println(Config.RED+"\t\tFormato de fecha no valido."+Config.RESET);
			System.out.println(ERROR);
			return false;
				
		}else {
			userdata[6]=Utils.formatDateSQL(userdata[6]);
		}
				
		System.out.println("\n\tCreando usuario, por favor espere...");
		System.out.println();
		return true;
		
	}

	public void changeUserData() {
		
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
				
				String password = Utils.encryptMd5(Input.getString("Inserte la contraseña actual: "));
				
				if(db.checkPassword(currentUser.username,password)) {
					
					password = Input.getString("\tIntroduzca una nueva contraseña: ");
					
					if(!Utils.validatePassword(password)) {
				
						System.out.println(Config.RED+"\t\tContraseña no valida."+Config.RESET);
							
					}else {
						
						String check = Input.getString("\tRepita la contraseña: ");
						
						if(check.equals(password)) {
							
							if(db.changeOneData(currentUser.id,"password",Utils.encryptMd5(check))) {
								
								System.out.println("Contraseña cambiada correctamente");
								
							}
							
						}else {
							
							System.out.println(Config.RED+"\t\tLa contraseña no es la misma."+Config.RESET);

						}
						
					}
					
				}else {
					
					System.out.println("Contraseña incorrecta.");
					
				}
					
			}else if(option == 2) {
				
				String name = Input.getString("\tIntroduzca su nuevo nombre completo (Nombre completo y apellidos, comenzando en mayúsculas): ");
				if(!Utils.validateName(name)) {
						
					System.out.println(Config.RED+"\t\tFormato de nombre no valido."+Config.RESET);
				
				}else {
					
					if(db.changeOneData(currentUser.id,"name",name)) {
						
						System.out.println("Nombre cambiado correctamente");
						currentUser.setName(name);
						
					}else {
						
						System.out.println("error al cambiar nombre");

					}
					
				}
				
					
			}else if(option == 3) {
				
				String nif = Input.getString("\tIntroduzca un nuevo NIF: ");
				
				if(!Utils.validateNif(nif)) {
							
					System.out.println(Config.RED+"\t\tFormato de NIF incorrecto."+Config.RESET);

				}else {
					
					if(db.checkField("nif",nif)) { // COMPRUEBA QUE SEAN UNICAS LAS PK
						
						System.out.println(Config.RED+"\n\t\tLos datos no son unicos en la base de datos."+Config.RESET);
					
					}else {
						
						if(db.changeOneData(currentUser.id,"nif",nif)) {
							
							System.out.println("NIF cambiado correctamente");
							currentUser.setNif(nif);
							
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
					
					if(db.checkField("email",email)) { // COMPRUEBA QUE SEAN UNICAS LAS PK
						
						System.out.println(Config.RED+"\n\t\tLos datos no son unicos en la base de datos."+Config.RESET);
					
					}else {
						
						if(db.changeOneData(currentUser.id,"email",email)) {
							
							System.out.println("Email cambiado correctamente");
							currentUser.setEmail(email);
							
						}else {
							
							System.out.println("error al cambiar email");

						}
						
					}
					
				}
					
			}else if(option == 5) {
				
				String address = Input.getString("\tIntroduzca una dirección postal: ");
				
				if(db.changeOneData(currentUser.id,"address",address)) {
					
					System.out.println("Direccion postal cambiada correctamente");
					currentUser.setAddres(address);
					
				}else {
					
					System.out.println("error al cambiar direccion postal");

				}
					
			}else if(option == 6) {
				
				String date = Input.getString("\tIntroduzca su nueva fecha de nacimiento (DD/MM/AA): "); 
				if(!Utils.validateDate(date)) {
						
					System.out.println(Config.RED+"\t\tFormato de fecha no valido."+Config.RESET);
						
				}else {
					
					String date2 = Utils.formatDateSQL(date);
					
					if(db.changeOneData(currentUser.id,"birthdate",date2)) {
						
						System.out.println("Fecha de nacimiento cambiada correctamente");
						currentUser.setBirthdate(date2);
						
					}else {
						
						System.out.println("error al cambiar la fecha de nacimiento");

					}
					
				}
				
					
			}else if(option == 7) {
				
				String password = Utils.encryptMd5(Input.getString("Inserte la contraseña actual: "));
				
				if(db.checkPassword(currentUser.username,password)) {
					
					if(db.deleteUser(currentUser.id)) {
						
						System.out.println(Config.LOGOUT);
						
						System.out.println("\n\tSESION CERRADA");
						System.out.println("\tUSUARIO ELIMINADO");
						logged=false; // SE PONE A FALSE PARA VOLVER A MENU INICIAL
						this.currentUser= new User(); // EL USUARIO SE PONE A NULL
						break;
						
					}else {
						
						System.out.println("No se ha podido eliminar el usuario");
						
					}
					
				}else {
					
					System.out.println("Contraseña incorrecta.");
					
				}
					
			}else {
				
				System.out.println("\n\tSeleccione una opción válida entre [0-7]");
				Input.toContinue();
				
			}
			
		}while(true);
				
	}
	
}