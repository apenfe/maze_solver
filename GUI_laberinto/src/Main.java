import Classes.Log;
import Classes.Maze;
import Classes.Session;
import Windows.Unlogged;

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
	
	public static Session currentSession = new Session();
	
	public static Maze currentMaze = new Maze();
	
	public static void main(String[] args) {
		
		Unlogged menu = new Unlogged();
		Log.insertLog(Log.INIT,"");
		menu.setVisible(true);
		
	}
	
	public static void loggedOptions() {
		
		int option = 0; 
		
		if(option==1) { // CARGAR LABERINTO
			
			currentMaze.loadMaze();	
			
		}else if(option==2) { // METODO VER LABERINTO ACTUAL
			
			if(currentMaze.isLoaded()) {
			
				currentMaze.showMaze();
				
			}else {
				
				System.out.println("\n\tDebe de cargar un laberinto para poder visualizarlo.");
				Log.insertLog(Log.SHOW_MAZE,"ERROR: No se ha cargado el laberinto.");
			}
			
		}else if(option==3) { // METODO ESTABLECER ENTRADA Y SALIDA
			
			if(currentMaze.isLoaded()) {
				
				currentMaze.setStartEnd();
				
			}else {
				
				System.out.println("\n\tDebe de cargar un laberinto para poder establecer entrada y salida.");
				Log.insertLog(Log.SHOW_MAZE,"ERROR: No se ha cargado el laberinto.");
			}
			
		}else if(option==4) { // BUSCAR CAMINOS
			
			if(currentMaze.isLoaded() && currentMaze.inOutNotZero()) {
				
				Log.insertLog(Log.FIND_PATH,"Acceso menu algoritmos");
				solveMazeOptions();
				
			}else {
				
				System.out.println("\n\tDebe de cargar un laberinto para poder calcular una solución y establecer entrada y salida.");
				Log.insertLog(Log.SHOW_MAZE,"ERROR: No se ha cargado el laberinto o las casillas");

			}
			
		}else if(option==5) { // METODO INFO
			
			currentSession.showUser();
			
		}else if(option==6) { // METODO LOGOUT
			
			currentSession.logOut();
			
			if(currentMaze.isLoaded()&& !currentSession.isLogged()) { // SI LA SESION SE CIERRA Y EL LABERINTO ESTA CARGADO, LO PONGO A NULL
				currentMaze = new Maze();
			}
			
		}
		
	}
	
	public static void solveMazeOptions() {
		
		int option = -1;
		
		do {
			
			if(option == 0) {
				
			}else if(option == 1) {
				
				currentMaze.firstWay();
				
			}else if(option == 2) {
				
				currentMaze.shorterWay();
					
			}else {
				
				System.out.println("\n\tSeleccione una opción válida entre [0-2]");
				
			}
			
		}while(true);
		
	}
}