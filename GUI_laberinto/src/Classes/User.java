package Classes;

/**
 * Clase User utilizada para crear un usuario y posteriormente poder usarlo en una sesión.
 * 
 * @author Adrián Peñalver Fernández
 * @version 1.4.0
 * 
 */

public class User{
	
	/* DECLARACIÓN DE LOS PARAMETROS */
	
	public int id;
	public String username;
	public String name;
	public String nif;
	public String email;
	public String addres;
	public String birthdate;
	public String role;
	
	/* CONSTRUCTOR VACIO */
	
	/**
	 * Constructor de la clase vacío.
	 */
	
	public User() { 
		
	}
	
	/* CONSTRUCTOR QUE RECIBE TODOS LOS DATOS, EN EL ORDEN QUE SE ESCRIBEN EN EL ARCHIVO */
	
	/**
	 * Constructor de la clase que recibe todos los datos del usuario.
	 * 
	 * @param username Nombre del usuario
	 * @param name Nombre real de usuario y apellidos
	 * @param nif DNI del usuario
	 * @param email Correo electrónico del usuario
	 * @param addres Dirección postal del usuario
	 * @param birthdate Fecha de nacimiento del usuario
	 * @param role Rol del usuario
	 * 
	 */
	
	public User(String id, String username, String name, String nif, String email, String addres, String birthdate, String role) {
		
		this.id=Integer.parseInt(id);
		this.username=username;
		this.name=name;
		this.nif=nif;
		this.email=email;
		this.addres=addres;
		this.birthdate=birthdate;
		this.role=role;
		
	}
	
	/* METODOS SET Y GET DE LOS PARAMETROS */
	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddres() {
		return this.addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public String getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public int getId() {
		return this.id;
	}
	
	/* METODO PUBLICO QUE DEVUELVE TODA LA INFORMACIÓN RELATIVA A UN USUARIO */
	
	/**
	 * Método info que muestra la información completa del usuario.
	 */

	public String info() {
		
		String exit = "";
		
		exit+="<html>- ID usuario: [" + this.id + "]<br>";
		exit+="\t- Nombre de usuario: [" + this.username + "]<br>";
		exit+="\t- Nombre completo: [" + this.name + "]<br>";
		exit+="\t- NIF: [" + this.nif + "]<br>";
		exit+="\t- Dirección de correo electónico: [" + this.email + "]<br>";
		exit+="\t- Dirección postal: [" + this.addres + "]<br>";
		exit+="\t- Fecha de nacimiento: [" + Utils.formatDateEU(birthdate) + "] ---> " + Utils.getAge(birthdate)+ " años.<br>";
		exit+="\t- Tipo de rol / permisos: [" + this.role + "]</html>";
		Log.insertLog(Log.USER,"Usuario: "+this.username+", ID: "+this.id);
		return exit;
		
	}

}