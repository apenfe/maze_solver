package Windows;

import javax.swing.*;
import Classes.Config;
import java.awt.*;
import java.awt.event.*;

public class Caminos extends JFrame implements ActionListener{
	
	/*
	 * Seleccionar algoritmo:
Mostrará dos botones de opciones: uno para el primer camino posible y otro para el camino
más corto. Al resolver el algoritmo mostrará por pantalla el laberinto hecho con imágenes y
el listado de pasos que ha seguido. Deberá tener un botón para volver atrás.
	 */

	public Logged logged;
	private Container contenedor;
	private JLabel titulo, labelInfo;
	private JButton exit, primerCamino, mejorCamino;
	
	public Caminos(Logged logged) {
		
		this.logged=logged;
		this.setTitle("Maze_Solver "+Config.VERSION_CODE);
		this.setBounds(600,200,400,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.contenedor=this.getContentPane();
		this.contenedor.setLayout(null);
		
		this.titulo();
		this.labels();
		this.botones();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.exit) {
			
			this.volver();
			
		}else if(e.getSource()==this.primerCamino) {
			
			this.primerCamino();
			
		}else if(e.getSource()==this.mejorCamino) {
			
			this.mejorCamino();
			
		}
		
	}
	
	private void titulo() {

		this.titulo = new JLabel("BUSCAR CAMINOS");
		this.titulo.setFont(new Font("Arial", Font.BOLD, 24));
		this.titulo.setBounds(84, 11, 233, 40);
		this.contenedor.add(this.titulo);

	}
	
	private void botones() {
		
		this.exit=new JButton("Volver");
		this.exit.setFont(new Font("Arial", Font.PLAIN, 16));
		this.exit.setBounds(114, 425, 160, 25);
		this.exit.addActionListener(this);
		this.contenedor.add(this.exit);
		
		this.mejorCamino=new JButton("Camino mas corto");
		this.mejorCamino.setFont(new Font("Arial", Font.PLAIN, 16));
		this.mejorCamino.setBounds(10, 62, 160, 25);
		this.mejorCamino.addActionListener(this);
		this.contenedor.add(this.mejorCamino);
		
		this.primerCamino=new JButton("Primer camino");
		this.primerCamino.setFont(new Font("Arial", Font.PLAIN, 16));
		this.primerCamino.setBounds(177, 62, 197, 25);
		this.primerCamino.addActionListener(this);
		this.contenedor.add(this.primerCamino);

	}

	private void labels() {

		// label info
		this.labelInfo = new JLabel("");
		this.labelInfo.setFont(new Font("Arial", Font.BOLD, 16));
		this.labelInfo.setBounds(10, 331, 364, 119);
		this.contenedor.add(this.labelInfo);

	}

	private void volver() {
		
		this.setVisible(false);
		this.logged.setVisible(true);
		
	}
	
	private void mejorCamino() {
		
		if(this.logged.currentMaze.shorterWay()) {
			
			this.labelInfo.setText("Solución mas corta encontrada");
			this.labelInfo.setForeground(Color.GREEN);
			
		}else {
			
			this.labelInfo.setText("No tiene solucion");
			this.labelInfo.setForeground(Color.RED);
			
		}
		
	}
	
	private void primerCamino() {
		
		if(this.logged.currentMaze.firstWay()) {
			
			this.labelInfo.setText("Solución encontrada");
			this.labelInfo.setForeground(Color.GREEN);
			
		}else {
			
			this.labelInfo.setText("No tiene solucion");
			this.labelInfo.setForeground(Color.RED);
			
		}
		
	}

}