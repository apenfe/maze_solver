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
	
	public static void main(String[] args) {
		
		Unlogged menu = new Unlogged();
		Log.insertLog(Log.INIT,"");
		menu.setVisible(true);
		
	}

}