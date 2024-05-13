package Windows;

import javax.swing.*;
import Classes.Config;
import processing.core.*;

import java.awt.*;
import java.awt.event.*;

/*
 * Mostrará por pantalla un laberinto hecho con imágenes que representen todos los
elementos del laberinto (pared, casilla andable, casilla entrada, casilla salida, etc…). Debe
tener un botón para volver atrás.
 */

public class SeeMaze extends JFrame implements ActionListener{

	public Logged logged;
	public Caminos caminos;
	private Container contenedor;
	private JLabel titulo, labelInfo;
	private JButton exit;
	private JTextArea textArea;
	
	public SeeMaze(Logged logged) {
		
		this.logged=logged;
		this.caminos=null;
		this.setTitle("Maze_Solver "+Config.VERSION_CODE);
		this.setBounds(600,200,773,638);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		this.contenedor=this.getContentPane();
		this.contenedor.setLayout(null);
		
		this.titulo();
		this.labels();
		this.botones();
		pintar();	

	}
	
	public SeeMaze(Caminos caminos) {
		
		this.caminos=caminos;
		this.logged=null;
		this.setTitle("Maze_Solver "+Config.VERSION_CODE);
		this.setBounds(600,200,773,638);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		this.contenedor=this.getContentPane();
		this.contenedor.setLayout(null);
		
		this.titulo();
		this.labels();
		this.botones();
		pintar();	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.exit) {
			
			this.volver();
			
		}
		
	}
	
	private void titulo() {

		this.titulo = new JLabel("VISUALIZACION LABERINTO");
		this.titulo.setFont(new Font("Arial", Font.BOLD, 24));
		this.titulo.setBounds(219, 11, 331, 40);
		this.contenedor.add(this.titulo);

	}
	
	private void botones() {
		
		this.exit=new JButton("Volver");
		this.exit.setFont(new Font("Arial", Font.PLAIN, 16));
		this.exit.setBounds(303, 563, 160, 25);
		this.exit.addActionListener(this);
		this.contenedor.add(this.exit);
		
		this.textArea = new JTextArea();
		textArea.setBounds(10, 49, 737, 503);
		getContentPane().add(textArea);

	}

	private void labels() {

		// label info
		this.labelInfo = new JLabel("");
		this.labelInfo.setFont(new Font("Arial", Font.BOLD, 16));
		this.labelInfo.setBounds(320, 563, 160, 25);
		this.contenedor.add(this.labelInfo);

	}

	private void volver() {
		
		this.setVisible(false);
		
		if(this.logged!=null) {
			this.logged.setVisible(true);
		}else {
			//this.caminos.setVisible(true);
		}
		
	}
	
	private void pintar() {
		
		ShowMaze vista = new ShowMaze(this.logged.currentMaze.showMaze(),logged);
		PApplet.runSketch(new String[]{"Windows/ShowMaze"}, vista);
		
	}
	
}