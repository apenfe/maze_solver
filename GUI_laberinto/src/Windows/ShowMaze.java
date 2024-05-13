package Windows;

import java.io.File;
import java.util.ArrayList;

import processing.core.PApplet;

public class ShowMaze extends PApplet {

	private char[][] map;
	private Logged logged;
	private Caminos caminos;
	private int size;

	ShowMaze(char[][] map, Logged logged) {

		this.map = map;
		this.caminos = null;
		this.logged = logged;
		
		if(map.length>=80||map[0].length>=80) {
			this.size=10;
		}else {
			this.size=20;
		}

	}

	ShowMaze(char[][] map, Caminos caminos) {

		this.map = map;
		this.caminos = caminos;
		this.logged = null;
		
		if(map.length>=80||map[0].length>=80) {
			this.size=10;
		}else {
			this.size=20;
		}

	}

	// Método para configurar el lienzo
	public void settings() {

		size(map[0].length*size, map.length*size);

	}

	// Método para dibujar en el lienzo
	public void draw() {

		int veces = 0;
		background(255);
		stroke(0);
		
		// Iterar sobre la matriz
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                // Calcular la posición de la celda en la pantalla
                int x = j * size;
                int y = i * size;

                // Dibujar el carácter en la posición calculada
                drawChar(map[i][j], x, y, size);
            }
        }
        
        if(veces==0 && caminos==null) {
        	saveAsJPG();
        }
        
	}
	 
	 private void drawChar(char c, int x, int y, int size) {
		    // Establecer el tamaño de la forma
		    int shapeSize = size - 2; // Reducimos el tamaño en 2 para que haya espacio alrededor del cuadrado/círculo
		    
		    // Establecer el color del trazo
		    stroke(0); // Borde negro para todas las formas
		    
		    // Dibujar la forma en la posición especificada según su valor
		    switch (c) {
		        case '#': // Pared
		            fill(100); // Color gris medio para las paredes
		            rect(x + 1, y + 1, shapeSize, shapeSize); // Sumamos 1 para centrar el cuadrado en la celda
		            break;
		        case 'S': // Inicio
		            fill(0, 255, 0); // Verde para el inicio
		            ellipse(x + size/2, y + size/2, shapeSize, shapeSize); // Centramos el círculo en la celda
		            break;
		        case 'E': // Fin
		            fill(255, 0, 0); // Rojo para el fin
		            ellipse(x + size/2, y + size/2, shapeSize, shapeSize); // Centramos el círculo en la celda
		            break;
		        case ' ': // Espacio vacío
		           // fill(0, 0, ); // Azul para los espacios vacíos
		           // ellipse(x + size/2, y + size/2, shapeSize, shapeSize); // Centramos el círculo en la celda
		            break;
		        default: // Caracteres no especificados
		            fill(0, 0, 255); // Azul para los caracteres no especificados
		            ellipse(x + size/2, y + size/2, shapeSize, shapeSize); // Centramos el círculo en la celda
		            break;
		    }
		    
		}

	@Override
	public void exit() {

		if (caminos == null) {

			logged.setVisible(true);
			// Clear any additional info
			logged.labelInfo.setText("");

		} else {

			caminos.setVisible(true);
			// Clear any additional info
			caminos.labelInfo.setText("");

		}
		
		noLoop();

	}
	
	public void saveAsJPG() {
		
		 ArrayList<String> namesFiles = new ArrayList<String>();
	        File[] files = new File[0];
	        
	        try {
	        	
	        	File route = new File("images\\");
	            files = route.listFiles();
				
			} catch (Exception e) {
				
				System.err.println("\n\tERROR - CONTACTAR SERVICIO TECNICO.");
			}

	        if (files != null) {
	        	
	            for (File file : files) { // POR CADA ARCHIVO...
	                
	                if (file.isFile() && file.getName().toLowerCase().endsWith(".jpg")) {
	                    namesFiles.add(file.getName());
	                }
	            }
	            
	        }
	        
	        boolean guardado = false;
	        
	        for (int i = 0; i < namesFiles.size(); i++) {
	        	
	        	if(namesFiles.get(i).equals(logged.currentMaze.fileName + ".jpg")) {
	        		guardado = true;
	        		System.out.println("ya guardao: "+logged.currentMaze.fileName);
	        	}
				
			}
	        
	        if(!guardado) {
	        	 save("images\\"+logged.currentMaze.fileName + ".jpg");
	        		System.out.println("se esta guardadno");

	        }
	    
	}

}