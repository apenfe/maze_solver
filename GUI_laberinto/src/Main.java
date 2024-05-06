import Classes.AdminOptions;
import Classes.ChangeData;
import Classes.Config;
import Classes.Input;
import Classes.Log;
import Classes.Maze;
import Classes.Session;
import Classes.Utils;

/**
 * 
 * Clase principal del proyecto Maze.
 * 
 * Compuesto por varios métodos estáticos que facilitan la navegación del usuario entre las diferentes opciones.
 * 
 * @author Adrián Peñalver Fernández
 * @version 1.4.0
 * 
 */

public class Main {
	
	/**
	 * Esta es una variable pública y estática que representa si el usuario ha iniciado o no la sesión
	 */
	
	public static Session currentSession = new Session();
	
	/**
	 * Esta es una variable pública y estática que representa el laberinto cargado, con el se puede decidir
	 * si estan disponibles todas las opciones o no.
	 */
	
	public static Maze currentMaze = new Maze();
	
	/**
	 * Este método principal, da la bienvenida al usuario y le presenta un menu inicial
	 * con opciones de no loggeado.
	 * 
	 * Tras el loggin este método muestra el menu con las opciones correspondientes al inicio de sesión.
	 */
	
	public static int option = -1;
	
	public static void main(String[] args) {
		
		System.out.println(Config.WELCOME + " " + Config.VERSION);
		Log.insertLog(Log.INIT,"");
		Input.toContinue();
		
		do { /* SE REPITE CONTINUAMENTE MIENTRAS NO SE PULSE 0 */
			
			if(currentSession.isLogged()) { /* SI EL USUARIO SE HA LOGGEADO */
				
				if(currentSession.isAdmin()) {
					
					option = Input.getInt(Config.LOGGED_ADMIN_MENU, false);
					
				}else {
					
					option = Input.getInt(Config.LOGGED_MENU, false);
					
				}
				
				loggedOptions();

			}else { /* SI EL USUARIO NO SE HA LOGGEADO */
				
				option = Input.getInt(Config.UNLOGGED_MENU, false); 
				unloggedOptions();
		
			}
			
		} while (option!=0);
		
		Log.insertLog(Log.EXIT,"");
		System.out.println(Config.GOODBYE);
		
	}
	
	/* METODOS USADOS EN LA CLASE MAIN */
	
	/* OPCIONES PARA USUARIOS REGISTRADOS */
	
	/**
	 * Este método se encarga de mostrar al usuario las opciones disponibles una vez iniciada la sesión.
	 * @param option Entrada seleccionada por el usuario.
	 */
	
	public static void loggedOptions() {
		
		if(option==0) { // METODO EXIT
			
			System.out.println(Config.EXIT);
			
			if(!Utils.confirmExit()) {
				option=-1;
			}
			
		}else if(option==1) { // CARGAR LABERINTO
			
			System.out.println("\t"+Config.LOAD_MAZE);
			currentMaze.loadMaze();
			Input.toContinue();
			
		}else if(option==2) { // METODO VER LABERINTO ACTUAL
			
			if(currentMaze.isLoaded()) {
				System.out.println("\t"+Config.MAZE);
				Log.insertLog(Log.SHOW_MAZE,"Lanerinto: "+currentMaze.getFileName());
				currentMaze.showMaze();
				
			}else {
				
				System.out.println("\n\tDebe de cargar un laberinto para poder visualizarlo.");
				Log.insertLog(Log.SHOW_MAZE,"ERROR: No se ha cargado el laberinto.");
			}
			
			Input.toContinue();
			
		}else if(option==3) { // METODO ESTABLECER ENTRADA Y SALIDA
			
			if(currentMaze.isLoaded()) {
				
				System.out.println("\t"+Config.SET_IN_OUT);
				currentMaze.setStartEnd();
				
			}else {
				
				System.out.println("\n\tDebe de cargar un laberinto para poder establecer entrada y salida.");
				Log.insertLog(Log.SHOW_MAZE,"ERROR: No se ha cargado el laberinto.");
			}
			
			Input.toContinue();
			
		}else if(option==4) { // BUSCAR CAMINOS
			
			if(currentMaze.isLoaded() && currentMaze.inOutNotZero()) {
				
				System.out.println("\t"+Config.SOLVE_MAZE);
				Log.insertLog(Log.FIND_PATH,"Acceso menu algoritmos");
				solveMazeOptions();
				
			}else {
				
				System.out.println("\n\tDebe de cargar un laberinto para poder calcular una solución y establecer entrada y salida.");
				Log.insertLog(Log.SHOW_MAZE,"ERROR: No se ha cargado el laberinto o las casillas");

			}
			
			Input.toContinue();
			
		}else if(option==5) { // METODO INFO
			
			currentSession.showUser();
			Input.toContinue();
			
		}else if(option==6) { // METODO LOGOUT
			
			currentSession.logOut();
			
			if(currentMaze.isLoaded()&& !currentSession.isLogged()) { // SI LA SESION SE CIERRA Y EL LABERINTO ESTA CARGADO, LO PONGO A NULL
				currentMaze = new Maze();
			}
			
			Input.toContinue();
			
		}else if(option==7) { // METODO MODIFICACION DATOS USUARIO
			
			if(currentSession.isAdmin()) {
				
				AdminOptions.menu();
				
			}else {
				
				ChangeData.changeUserData(currentSession);
				
			}
			
			Input.toContinue();
			
		}
		
	}
	
	
	/* OPCIONES PARA USUARIOS NO REGISTRADOS */
	
	/**
	 * Este método se encarga de mostrar al usuario las opciones disponibles antes del inicio de sesión.
	 * 
	 * @param option Entrada seleccionada por el usuario.
	 */
	
	public static void unloggedOptions() {
		
		if(option==0) { // METODO SALIR
			
			System.out.println(Config.EXIT);
			
			if(!Utils.confirmExit()) {
				option=-1;
			}
			
		}else if(option==1) { // METODO LOGIN
		
			currentSession.login();
			
		}else if(option==2) { // METODO SIGNUP
			
			currentSession.signup();
			Input.toContinue();
			
		}
		
	}
	
	/* OPCIONES PARA LA RESOLUCION DE LABERINTOS */
	
	/**
	 * Este método se encarga de complementar el menu de opciones de usuario logueados.
	 * 
	 * Permite al usuario seleccionar las diferentes formas de resolver el laberinto.
	 */
	
	public static void solveMazeOptions() {
		
		int option = -1;
		
		do {
			
			option = Input.getInt(Config.SOLVE_MAZE_MENU, false);
			
			if(option == 0) {
				
				if(Utils.confirmExit()) {
					break;
				}else {
					option = -1;
				}
				
			}else if(option == 1) {
				
				currentMaze.firstWay();
				
			}else if(option == 2) {
				
				currentMaze.shorterWay();
					
			}else {
				
				System.out.println("\n\tSeleccione una opción válida entre [0-2]");
				Input.toContinue();
				
			}
			
		}while(true);
		
	}
}