package Classes;

import java.io.FileWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Log{
	
	private static final String LOG_NAME = "syslog.txt";
	
	private static final String INIT = "[Inicio del Programa - "+Config.VERSION+"]"; 
	private static final String EXIT = "[Final del Programa - "+Config.VERSION+"]"; 
	private static final String LOGIN = "syslog.txt"; 
	private static final String SIGNUP = "syslog.txt"; 
	private static final String LOGOUT = "syslog.txt"; 
	
	/*
	 * Inicio de programa ***
	 * Registro exitoso (al menos nombre de usuario. Nunca la contraseña)
	 * Registro fallido (al menos nombre de usuario. Nunca la contraseña)
	 * Login exitoso (nombre de usuario)
	 * Login fallido (nombre de usuario)
	 * Cargar laberinto (nombre del archivo)
	 * Mostrar laberinto (nombre del archivo)
	 * Error mostrar laberinto (motivo: no se ha cargado laberinto)
	 * Establecer casillas con éxito (valores de las coordenadas)
	 * Establecer casillas con error (valores de las coordenadas)
	 * Error establecer casillas (motivo: no se ha cargado laberinto)
	 * Acceder a la selección buscar caminos
	 * Error acceder a la selección buscar caminos (motivo: no se ha cargado laberinto o no hay casillas)
	 * Resolver primer camino (éxito o sin solución + cantidad de pasos)
	 * Resolver camino más corto (éxito o sin solución + cantidad de pasos)
	 * Mostrar el usuario actual (nombre de usuario + id)
	 * Cerrar sesión (nombre de usuario)
	 * Finalizar programa ***
	 */
	
	
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