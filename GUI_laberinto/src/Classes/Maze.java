package Classes;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Clase Maze utilizada para cragar un laberinto desde un fichero y realizar varias acciones con él.
 * 
 * @author Adrián Peñalver Fernández
 * @version 1.4.0
 * 
 */

public class Maze{
	
	/* ATRIBUTOS DE LA CLASE MAZE */
	public Stack<Coordinate> path = new Stack<Coordinate>(); 
	public char[][] map = new char[0][0];
	public String fileName;
	private boolean loaded;
	private int startI;
	private int startJ;
	private int endI;
	private int endJ;
	private boolean find;
	
	/* CONSTRUCTOR DE LA CLASE MAZE */
	
	public Maze() {
		
		this.loaded=false;
		this.find=false;
		
	}
	
	/* METODOS DE LA CLASE MAZE */
	
	/* METODO PARA SABER SI ESTA CARGADO EL LABERINTO */
	
	public String getFileName() {
		return fileName;
	}

	/**
	 * Método que permite conocer el estado de carga del laberinto.
	 * 
	 * @return boolean True si se ha cargado y false en caso contrario.
	 * 
	 */
	
	public boolean isLoaded() {
		return loaded;
	}
	
	/* METODO PARA RESETEAR VALORES DEL LABERINTO */
	
	public void deleteMaze(boolean total) {
		
		if(total) { // SI QUEIRO RESETEARLOS TODOS
			
			this.fileName = "";
			this.loaded = false;
			this.startI=0;
			this.startJ=0;
			this.endI=0;
			this.endJ=0;
			
		}else { // SI SOLO QUIERO RESETEAR LA ENTRADA Y SALIDA
			
			this.startI=0;
			this.startJ=0;
			this.endI=0;
			this.endJ=0;
			
		}
		
	}
	
	/* METODO PARA CARGAR EL LABERINTO DESEADO */
	
	/**
	 * Método que permite cargar un laberinto.
	 * 
	 * Le muestra al usuario todos los laberintos disponibles y le permite seleccionar uno de ellos.
	 * 
	 * En caso de que el proceso se realice bien, se pone el boolean loaded a true
	 * y se resetean todos los valores del laberinto.
	 * 
	 */

	public boolean loadMaze(String file) {
		
		if(isLoaded()) { // EN CASO DE SELECCIONAR OTRO LABERINTO Y QUE YA TUVIESEMOS OTRO CARGADO...
			
			deleteMaze(true); // SE RESETEAN LOS VALORES COMPLETOS
			
		}
		
		if(readMaze(Config.MAZES_PATH+file)) { // SI EL LABERINTO SE LEE CORRECTAMENTE
			
			this.fileName=file; // SE LE DA NOMBRE
			this.loaded=true; // SE VUELVE A INDICAR QUE ESTÁ CARGADO
			return true;
		}
		
		return false;
		
	}
	
	/* METODO PARA OBTENER LOS NOMBRE DE LOS TXT */
	
	public ArrayList<String> obtainTxtNames(String path) {
		
        ArrayList<String> namesFiles = new ArrayList<String>();
        File[] files = new File[0];
        
        try {
        	
        	File route = new File(path);
            files = route.listFiles();
			
		} catch (Exception e) {
			
			System.err.println("\n\tERROR - CONTACTAR SERVICIO TECNICO.");
		}

        if (files != null) {
        	
            for (File file : files) { // POR CADA ARCHIVO...
                
                if (file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
                    namesFiles.add(file.getName());
                }
            }
            
        }

        return namesFiles;
    }
	
	/* METODO LEER LOS DATOS DE UN ARCHIVO */
	
	private boolean readMaze(String fullPath) {
		
		ArrayList<String> lines = new ArrayList<>();
		File userMaze = new File(fullPath);
		
        try(Scanner reader = new Scanner(userMaze)) {
        	
        	while(reader.hasNextLine()) {
        		
        		String line = reader.nextLine();
        		lines.add(line);
        		
        	}
        	
        }catch(Exception e) {
        	
        	System.err.println("Error. Pongase en contacto con el soporte técnico.");
        	return false;
        }
        
    
        try {
        	
        	 this.map = new char[lines.size()][lines.get(0).length()];
             
             for (int i = 0; i < lines.size(); i++) {
             	
             	String currentLine = lines.get(i);
             	
             	for (int j = 0; j < lines.get(0).length(); j++) {
             		
             		this.map[i][j]=currentLine.charAt(j);
         			
         		}
             	
     		}
             
            return true;
			
		} catch (Exception e) {
			
			return false;
		}
        
    }
	
	/* METODO PARA VISUALIZAR EL LABERINTO */
	
	/**
	 * Método que permite visualizar el laberinto cargado.
	 * 
	 * Le muestra al usuario el laberinto con las casillas de entrada y salida, asi como la solución en caso
	 * de existir una y si ha sido calculada.
	 * 
	 */
	
	public String showMaze() {
		
		String exit = "";
		
		exit += numberVertically(); // SE MUESTRAN LOS NUMEROS DE COLUMNA EN VERTICAL

		for (int i = 0; i < map.length; i++) {

			exit+="\t" + i + " - ";

			for (int j = 0; j < map[0].length; j++) {

				boolean check = true;

				if ((i == startI && j == startJ) && (startI != 0 && startJ != 0)) {
					exit += "I ";
					check = false;
				} else if ((i == endI && j == endJ) && (endI != 0 && endJ != 0)) {
					exit += "F ";
					check = false;
				}
					
				if (check) {
						
					boolean camino = false;
						
					for (int k = 0; k < path.size(); k++) {
							
						if(i==path.get(k).getX() && j==path.get(k).getY()) {
							
							exit += path.get(k).getDirection()+" ";
							camino=true;
								
						}
							
					}
						
					if(!camino) {
						exit += map[i][j]+" ";
					}

				}

			}

			exit += "\n";

		}
		
		return exit;

	}
	
	/* METODO PARA ASIGNAR DIRECCIONES SEGUN LA CASILLA ANTERIOR */
	
	private void setDirections() {

		for (int i = 1; i < path.size(); i++) {
			
			if(path.get(i-1).getX() > path.get(i).getX()) { // si esta por arriba abajo
				
				path.get(i).setDirection('^');
				
			}else if(path.get(i-1).getX() < path.get(i).getX()) { // si esta por abajo arriba
				
				path.get(i).setDirection('V');
				
			}else if(path.get(i-1).getY() < path.get(i).getY()) { // si esta por derecha abajo
				
				path.get(i).setDirection('>');
				
			}else if(path.get(i-1).getY() > path.get(i).getY()) { // si esta por izquierda abajo
				
				path.get(i).setDirection('<');
				
			}
			
		}
		
	}
	
	/* METODO PARA MOSTRAR LOS NUMEROS DE COLUMNA EN VERTICAL */
	
	private String numberVertically() {
		
		String exit="";
		
		int figure = maxFigure(map[0].length); // SE BUSCAN LAS CIFRAS DEL NUMERO MAS GRANDE
		
		for (int i = 0; i < figure; i++) { // SE RECORREN PRIMERO LAS ALTURAS POR EL NUMERO MAXIMO DE CIFRAS
			
			exit+="\n\t";
			
			for (int j = 0; j < map[0].length; j++) { // SE RECORRE EL TAMAÑO DE LAS COLUMNAS DE LA MATRIZ
				
				int jFigure = maxFigure(j); // SE CALCULAN LAS CIFRAS DE LA COLUMNA ACTUAL
				
				if(jFigure < (i+1)) { // SI LAS CIFRAS DE LA COLMNA SON MENORES QUE EL Nº DE FILA
					
					if(jFigure==1 && i==0) { // SI LAS CIFRAS SON 1 Y LA FILA ES LA PRIMERA ENTONCES PINTA EL NUMERO
						exit+=j+" ";
					}else { // EN OTRO CASO, PONE UN SIMBOLO INDICADOR
						exit+="| ";
					}
				
				}else if(jFigure >= (i+1)) { // SI LAS CIFRAS DE LA COLUMNA SON MAYOR O IGUAL QUE EL Nº DE FILA
					
					String number =""+j; // CREAMOS UN STRING CON EL NUMERO DE LA COLUMNA
					int index =0;
					
					if(i<number.length()) { // SI LA FILA ES MENOR QUE LAS CIFRAS DEL NUMERO... 
						index=i; // EL INDICE SERÁ EL DE LA FILA
					}else {
						index=number.length()-1; // EN OTRO CASO SERÁ EL ULTIMO NUMERO.
					}
					
					exit+=number.charAt(index)+" "; // IMPRIME LA CIFRA CORRESPONDIENTE
					
				}
				
			}
			
		}
		
		exit+="\n\t";
		
		for (int i = 0; i < map[0].length; i++) {
			
			String linea = "| ";
			
			exit+=linea;
			
		}
		
		exit+="\n\n";
		return exit;
	}
	
	/* METODO PARA CALCULAR LAS CIFRAS DE UN NUMERO */
	
	private int maxFigure(int number) {
		
		int figure =0;
		
		if(number==0) {
			return 1;
		}
		
		while(number>0) {
			
			number/=10;
			figure++;
		}
		
		return figure;
			
	}
	
	/* METODO PARA ESTABLECER CASILLAS DE ENTRADA Y SALIDA DEL LABERINTO */
	
	/* METODO PARA ESTABLECER CASILLA */
	
	public boolean setIJ(boolean in, int i, int j) { // SI ES TRUE SE ESTABLECE ENTRADA, SI ES FALSE LA SALIDA
		
		// ESTABLECE Y COMPRUEBA LA FILA
				
		if(i<0 || i>map.length-1) {
			deleteMaze(false); // SE RESETEAN ENTRADA Y SALIDA
			return false;
		}		
			
		if(in) {
			startI=i;
		}else {
			endI=i;
		}
			
		// ESTABLECE Y COMPRUEBA LA COLUMNA
					
		if(j<0 || j>map[0].length-1) {
				deleteMaze(false); // SE RESETEAN ENTRADA Y SALIDA
				return false;
		}
			
		if(in) {
			startJ=j;
		}else {
			endJ=j;
		}
	
		if(sameInOut()) { // SI LAS CASILLAS DE ENTRADA Y SALIDA SON LAS MISMAS
			
			deleteMaze(false); // SE RESETEAN ENTRADA Y SALIDA
			return false;
				
		} else {

			if (in && (map[startI][startJ] == ' ')) { // SI LA CASILLA ES VALIDA...
				
				return true;
				
			} else if (!in && (map[endI][endJ] == ' ')) { // SI LA CASILLA ES VALIDA...
				
				return true;
				
			} else { // EN OTRO CASO...
					
				deleteMaze(false); // SE RESETEAN ENTRADA Y SALIDA
				return false;
				
			}

		}
		
	}
	
	/* METODO PARA COMPROBAR SI ENTRADA Y SALIDA SON IGUALES */
	
	private boolean sameInOut() {
		
		if((startI==endI && endJ==startJ) && (startI!=0 && startJ!=0 )) {
			return true;
		}
		
		return false;
	}
	
	/* METODO PARA COMPROBAR SI ENTRADA Y SALIDA NO SE HAN CREADO*/
	
	/**
	 * Método para comprobar si la entrada y salida aun no han sido establecidas por el usuario.
	 * 
	 * @return boolean True si han sido establecidas y false en caso de no estar establecidas.
	 */
	
	public boolean inOutNotZero() {
		
		if((startI==endI && endJ==startJ) && (startI==0 && startJ==0 )) {
			return false;
		}
		
		return true;
	}

	/* METODO PARA BUSCAR EL PRIMER CAMINO POSIBLE */
	
	/**
	 * Método que permite buscar una solución cualquiera para el laberinto.
	 * 
	 * En caso de encontrarla se muestra el laberinto con la solución y en caso de no 
	 * encontrar la solución, se indica al usuario.
	 * 
	 */

	public boolean firstWay() {
		
		path.clear();
		
		char[][] maze = simplifyMaze();
		
		if (goAhead(startI, startJ,maze)) {
			
			printPath();
			showMaze();
			return true;

		} else {
			
			return false;
			
		}

	}
	
	/* COMPRUEBA SI LA CASILLA YA ESTÁ EN LAS VISITADAS */
	
	private boolean checkPath(int i, int j, Stack<Coordinate> path) {
		
		for (int k = 0; k < path.size(); k++) {
			
			if(path.get(k).getX()==i&&path.get(k).getY()==j) {
				return true;
			}
			
		}
		
		return false;
		
	}
	
	/* COMPRUEBA TODAS LAS COMBINACIONES HASTA ENCONTRAR UNA SOLUCION */
	
	private boolean goAhead(int i, int j, char[][] maze) {

		if (i == endI && j == endJ) {
			return true;
		}
		
		if (maze[i][j] == '#' || checkPath(i,j,path)) {
			return false; 
		}
		
		path.push(new Coordinate(i,j));
		
		if (goAhead(i, j+1,maze)) {
			return true;
		}
		
		if (goAhead(i-1, j,maze)) {
			return true;
		}
		
		if (goAhead(i, j-1,maze)) {
			return true;
		}
		
		if (goAhead(i+1, j,maze)) {
			return true;
		}
		
		path.pop();
		return false;
	}
	
	/* METODO PARA IMPRIMIR LAS CASILLAS Y EL TOTAL DE PASOS */
	
	private void printPath() {
		
		setDirections();
		int pasos = path.size();
		
		System.out.println("\n\tPasos: "+pasos);
		
		for (int i = 1; i < pasos; i++) {
			
			System.out.println("\t("+path.get(i).getX()+", "+path.get(i).getY()+") ---> "+path.get(i).getDirection());
			
		}
		
	}

	/* METODO PARA BUSCAR EL CAMINO MAS CORTO */
	
	/**
	 * Método que permite buscar la solución que represente el camino más corto posible.
	 * 
	 * En caso de encontrarla se muestra el laberinto con la solución y en caso de no 
	 * encontrar la solución, se indica al usuario.
	 * 
	 */

	public boolean shorterWay() {
		
		path.clear();
		char[][] maze = simplifyMaze();

		Stack<Coordinate> path2 = new Stack<>();
		
		int size = (map.length)+(map[0].length)*10;
		
		for (int i = 0; i < size; i++) {
			
			path.push(new Coordinate(0,i));
			
		}
		
		goAheadAllWays(startI, startJ, path2,maze);
		
		if(this.find) { 
			
			printPath();
			showMaze();
			return true;
			
		}else {
			
			return false;
			
		}

	}
	
	/* BUSCA TODOS LOS CAMINOS Y SE QUEDA CON EL MAS CORTO DE TODOS ELLOS */
	
	private boolean goAheadAllWays(int i, int j, Stack<Coordinate> path2, char[][] maze ) {

		if (i == endI && j == endJ) {
			
			if(path2.size()<path.size()) {
				
				path.clear();
				path.addAll(path2);
				find = true;
			}
			
			return true;
		}
		
		if (maze[i][j] == '#' || checkPath(i,j,path2)) {
			return false; 
		}
		
		if (path2.size()>path.size()) { 
			return false; 
		}
		
		path2.push(new Coordinate(i,j));
		
		goAheadAllWays(i, j+1, path2,maze);
		goAheadAllWays(i-1, j, path2,maze);
		goAheadAllWays(i, j-1, path2,maze);
		goAheadAllWays(i+1, j, path2,maze);
		
		path2.pop();
		return false;
		
	}
	
	/* METODO PARA SIMPLIFICAR EL LABERINTO, ELIMINANDO TODOS LOS CAMINOS SIN SALIDA */
	
	private char[][] simplifyMaze() {
		
		char[][] copyMap = new char[map.length][map[0].length];

        for (int i = 0; i < map.length; i++) {
        	
            for (int j = 0; j < map[i].length; j++) {
            	
                copyMap[i][j] = map[i][j];
                
            }
            
        }
		
		int min=countWhites(copyMap);
		
		do {
			
			copyMap=noExit(copyMap);
			int whites = countWhites(copyMap);
			
			if(whites<min) {
				min=whites;
			}else {
				break;
			}	
			
		}while(true);
		
		return copyMap;
		
	}
	
	/* METODO PARA CONTAR CUANTAS CASILLAS LIBRES QUEDAN EN LABERINTO */
	
	private int countWhites(char[][] copyMap) {
		
		int exit=0;
		
		for (int i = 0; i < copyMap.length; i++) {
			
			for (int j = 0; j < copyMap[0].length; j++) {
				
				if(copyMap[i][j]==' ') {
					exit++;	
				}
				
			}
			
		}
		
		return exit;	
		
	}
	
	/* METODO PARA CERRAR CASILLAS SIN SALIDA */
	
	private char[][] noExit(char[][] copyMap) {
		
		for (int i = 1; i < copyMap.length-1; i++) {
			
			for (int j = 1; j < copyMap[0].length-1; j++) {
				
				if(copyMap[i][j]==' ') {
					
					int cont =0;
					
					if(copyMap[i-1][j]==' ') {
						cont ++;
					}
					
					if(copyMap[i+1][j]==' ') {
						cont ++;
					}
					
					if(copyMap[i][j-1]==' ') {
						cont ++;
					}
					
					if(copyMap[i][j+1]==' ') {
						cont ++;
					}
					
					if(cont<=1 && !isInOrOut(i, j)) {
						
						copyMap[i][j]='#';	
						
					}
					
				}
				
			}
			
		}
		
		return copyMap;
		
	}
	
	/* METODO PARA SABER SI SE TRATA DE LA CASILLA DE SALIDA O ENTRADA */
	
	private boolean isInOrOut(int i, int j) {
		
		return (i==startI && j==startJ) || (i==endI && j==endJ);
		
	}
	
}