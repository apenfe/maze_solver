package Classes;

/**
 * Clase Coordinate utilizada para almacenar las casillas de la solución.
 * 
 * Incluye getters y setters
 * 
 * @author Adrián Peñalver Fernández
 * @version 1.4.0
 * 
 */

public class Coordinate{
	
	private int x;
	private int y;
	private char direction;
	
	/**
	 * Constructor de la clase.
	 * 
	 *  @param x Fila de la casilla.
	 *  @param y Columna de la casilla.
	 */
	
	public Coordinate(int x, int y) {
		
		this.x=x;
		this.y=y;
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Get de Dirección.
	 * 
	 * Devuelve un char que corresponde a la dirección del camino.
	 * 
	 * @return char Dirección del camino.
	 */

	public char getDirection() {
		return direction;
	}
	
	/**
	 * Set de Dirección.
	 * 
	 * Recibe un char que corresponde a la dirección del camino.
	 * 
	 * @param char Dirección del camino.
	 */

	public void setDirection(char direction) {
		this.direction = direction;
	}
	
}