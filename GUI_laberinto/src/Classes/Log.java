package Classes;

/**
 * @author Adrián Peñalver Fernández
 * @version 1.4.0
 * 
 */

import java.io.FileWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Log{
	
	private static final String LOG_NAME = "syslog.txt";
	
	public static final String INIT = "---------------------------------------------------------\n[Inicio del Programa - "+Config.VERSION_CODE+"]"; 
	public static final String EXIT = "[Final del Programa - "+Config.VERSION_CODE+"]"; 
	public static final String LOGIN = "[Evento login]"; 
	public static final String SIGNUP = "[Evento de registro]"; 
	public static final String LOGOUT = "[Evento logout]"; 
	public static final String MAZE_IN_OUT = "[Casillas Laberinto]"; 
	public static final String SHOW_MAZE = "[Visualizacion laberinto]"; 
	public static final String CHARGE_MAZE = "[Cargar laberinto]"; 
	public static final String FIND_PATH = "[Solucionar laberinto]";
	public static final String USER = "[Datos usuario]";
	public static final String FIRST_WAY = "[Primer camino]";
	public static final String SHORTER_WAY = "[Camino mas corto]";
	
	public static boolean insertLog(String event, String dataEvent) {
		
		String log = time()+" - "+event+" - "+dataEvent;
		
		try {
			
			FileWriter writer = new FileWriter(Config.LOG_PATH+LOG_NAME,true);
			
			writer.write(log+"\n");
			writer.close();
			
			return true;
			
		} catch (Exception e) {
			
			return false;
			
		}
		
	}
	
	private static String time() {
        
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return format.format(date);
		
	}
	
}