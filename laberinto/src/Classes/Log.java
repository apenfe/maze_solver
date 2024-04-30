package Classes;

import java.io.FileWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Log{
	
	private static final String LOG_NAME = "syslog.txt";
	
	public static final String INIT = "[Inicio del Programa - "+Config.VERSION+"]"; 
	public static final String EXIT = "[Final del Programa - "+Config.VERSION+"]"; 
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
	
	/*
	 * El programa deberá ser capaz de escribir sobre ese fichero y crearlo si no existiera
	 * previamente, para poder escribir en él.
	 * 
	 * Los eventos deben registrase en el fichero en el siguiente formato:
	 * fecha y hora - tipo de evento - datos del evento
	 * 
	 * Por ejemplo, para un login fallido, la línea que se escribiría sería la siguiente:
	 * 25/04/2022 09:47:54 - login fallido - usuario: dmunueraa
	 * 
	 * Para una carga de laberinto podría ser lo siguiente:
	 * 25/04/2022 10:01:19 - cargar laberinto - archivo: laberinto2.txt
	 * 
	 * Para establecer las casillas de entrada/salida podría ser lo siguiente:
	 * 25/04/2022 10:02:01 - casillas establecidas con éxito - Entrada (1, 19) Salida (17, 4)
	 * 
	 */
	
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