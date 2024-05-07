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
	
	public User currentUser;
	public DAO db;
	
	public Session() {
		
		this.currentUser= new User(); // CREA UN OBJETO USER
		this.db= new DAO(); // CREA UN OBJETO BASE DE DATOS
		
	}
	
	public boolean login(String user, String password) {
		
		password= Utils.encryptMd5(password);
		
		this.currentUser = db.login(user, password);

		if (currentUser!=null) { // BUSCA UNA COOINCIDENCIA EN LA BASE DE DATOS
			
			Log.insertLog(Log.LOGIN,"Login exitoso: "+user);
			return true;

		} else {
			
			this.currentUser=new User();
			Log.insertLog(Log.LOGIN,"Login fallido: "+user);
			return false;
		}

	}
	
	public boolean signup(String[] userdata) {

		User newUser = new User("0", userdata[0], userdata[4], userdata[1], userdata[2], userdata[5], userdata[6],
				"user");

		if (db.signup(newUser, userdata[3])) {

			return true;

		}

		return false;

	}
	
	public String showUser() {
		
		return currentUser.info();
		
	}
	
	public void logOut() {
		
		this.currentUser= new User(); // EL USUARIO SE PONE A NULL
		
	}
	
	public boolean checkData(String[] userdata) {
				
		if(!Utils.validateUserName(userdata[0])) {
				
			return false;
			
		}
		
		if(!Utils.validatePassword(userdata[3])) {
	
			return false;
				
		}
			
		if(!Utils.validateNif(userdata[1])) {
					
			return false;

		}
		
		if(!Utils.validateEmail(userdata[2])) {
					
			return false;

		}
		
		User test = new User();
		test.setUsername(userdata[0]);
		test.setNif(userdata[1]);
		test.setEmail(userdata[2]);
		
		if(db.checkUser(test)) { // COMPRUEBA QUE SEAN UNICAS LAS PK
			
			return false;
			
		}
			
		if(!Utils.validateName(userdata[4])) {
				
			return false;
				
		}

		if(!Utils.validateDate(userdata[6])) {
				
			return false;
				
		}else {
			
			userdata[6]=Utils.formatDateSQL(userdata[6]);
			
		}
				
		return true;
		
	}
	
}