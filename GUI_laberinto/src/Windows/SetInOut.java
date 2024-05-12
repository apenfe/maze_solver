package Windows;

import javax.swing.*;
import Classes.Config;
import java.awt.*;
import java.awt.event.*;

public class SetInOut extends JFrame implements ActionListener{
	
/*
 * Tendrá un campo para introducir valores numéricos por cada una de las coordenadas de las
casillas. Deberá tener un botón para confirmar los valores y comprobará si son válidos o no.
Deberá tener un botón para volver atrás.
 */

	public Logged logged;
	private Container contenedor;
	private JLabel titulo, labelInfo, labelInfo2, labelEntrada, labelSalida, labelIin, labelJin, labelIout, labelJout;
	private JTextField campoIin, campoJin, campoIout, campoJout;
	private JButton volver, set, limpiar;
	
	public SetInOut(Logged logged) {
		
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
		
		if(e.getSource()==this.volver) {
			
			this.volver();
			
		}else if(e.getSource()==this.set) {
			
			this.set();
			
		}else if(e.getSource()==this.limpiar) {
			
			this.limpiar();
			
		}
		
	}
	
	private void titulo() {

		this.titulo = new JLabel("CARGAR ENTRADA Y SALIDA");
		this.titulo.setFont(new Font("Arial", Font.BOLD, 24));
		this.titulo.setBounds(10, 11, 364, 40);
		this.contenedor.add(this.titulo);

	}
	
	private void botones() {
		
		this.volver=new JButton("Volver");
		this.volver.setFont(new Font("Arial", Font.PLAIN, 16));
		this.volver.setBounds(105, 425, 160, 25);
		this.volver.addActionListener(this);
		this.contenedor.add(this.volver);
		
		this.set=new JButton("Comprobar Casillas");
		this.set.setFont(new Font("Arial", Font.PLAIN, 16));
		this.set.setBounds(180, 389, 187, 25);
		this.set.addActionListener(this);
		this.contenedor.add(this.set);
		
		this.limpiar=new JButton("Limpiar");
		this.limpiar.setFont(new Font("Arial", Font.PLAIN, 16));
		this.limpiar.setBounds(10, 389, 160, 25);
		this.limpiar.addActionListener(this);
		this.contenedor.add(this.limpiar);

	}

	private void labels() {

		// label entrada
		this.labelEntrada = new JLabel("Casilla de Entrada");
		this.labelEntrada.setFont(new Font("Arial", Font.BOLD, 16));
		this.labelEntrada.setBounds(111, 46, 154, 30);
		this.contenedor.add(this.labelEntrada);
		
		// label salida
		this.labelSalida = new JLabel("Casilla de Salida");
		this.labelSalida.setFont(new Font("Arial", Font.BOLD, 16));
		this.labelSalida.setBounds(111, 222, 154, 25);
		this.contenedor.add(this.labelSalida);
		
		// label fila IN
		this.labelIin = new JLabel("Fila de entrada:");
		this.labelIin.setFont(new Font("Arial", Font.BOLD, 16));
		this.labelIin.setBounds(20, 80, 154, 30);
		this.contenedor.add(this.labelIin);
		
		this.campoIin = new JTextField();
		this.campoIin.setFont(new Font("Arial", Font.PLAIN, 16));
		this.campoIin.setBounds(174, 81, 200, 30);
		this.contenedor.add(this.campoIin);
		
		// label columna IN
		this.labelJin = new JLabel("Columna de entrada:");
		this.labelJin.setFont(new Font("Arial", Font.BOLD, 16));
		this.labelJin.setBounds(10, 121, 170, 30);
		this.contenedor.add(this.labelJin);
				
		this.campoJin = new JTextField();
		this.campoJin.setFont(new Font("Arial", Font.PLAIN, 16));
		this.campoJin.setBounds(174, 122, 200, 30);
		this.contenedor.add(this.campoJin);
		
		// label fila OUT
		this.labelIout = new JLabel("Fila de salida:");
		this.labelIout.setFont(new Font("Arial", Font.BOLD, 16));
		this.labelIout.setBounds(10, 258, 170, 30);
		this.contenedor.add(this.labelIout);
				
		this.campoIout = new JTextField();
		this.campoIout.setFont(new Font("Arial", Font.PLAIN, 16));
		this.campoIout.setBounds(174, 259, 200, 30);
		this.contenedor.add(this.campoIout);
				
		// label columna OUT
		this.labelJout = new JLabel("Columna de salida:");
		this.labelJout.setFont(new Font("Arial", Font.BOLD, 16));
		this.labelJout.setBounds(4, 294, 170, 40);
		this.contenedor.add(this.labelJout);
						
		this.campoJout = new JTextField();
		this.campoJout.setFont(new Font("Arial", Font.PLAIN, 16));
		this.campoJout.setBounds(174, 300, 200, 30);
		this.contenedor.add(this.campoJout);
		
		// label info
		this.labelInfo = new JLabel("");
		this.labelInfo.setFont(new Font("Arial", Font.BOLD, 16));
		this.labelInfo.setBounds(10, 341, 364, 47);
		this.contenedor.add(this.labelInfo);
		
		// label info2
		this.labelInfo2 = new JLabel("");
		this.labelInfo2.setFont(new Font("Arial", Font.BOLD, 16));
		this.labelInfo2.setBounds(10, 162, 364, 49);
		this.contenedor.add(this.labelInfo2);
		
	}

	private void volver() {
		
		this.setVisible(false);
		this.logged.setVisible(true);
		
	}
	
	private void limpiar() {
		
		this.campoIin.setText("");
		this.campoJin.setText("");
		this.campoIout.setText("");
		this.campoJout.setText("");
		this.labelInfo.setText("");
		this.labelInfo2.setText("");
		
	}
	
	private void set() {
		
		boolean in = false;
		boolean out = false;
		
		int inI = Integer.parseInt(this.campoIin.getText().trim());
		int inJ = Integer.parseInt(this.campoJin.getText().trim());
		int outI = Integer.parseInt(this.campoIout.getText().trim());
		int outJ = Integer.parseInt(this.campoJout.getText().trim());
		
		this.logged.currentMaze.deleteMaze(false);
		
		if(this.logged.currentMaze.setIJ(true,inI,inJ)) { // SI LA CASILLA DE ENTRADA SE ESTABLECE
			
			this.labelInfo2.setText("Casilla de entrada fijada");
			this.labelInfo2.setForeground(Color.GREEN);
			in=true;
			
		}else { // EN OTRO CASO, SI EL USUARIO DESISTE SE TERMINA EL PROGRAMA.
			
			this.labelInfo2.setText("Error en casilla de entrada");
			this.labelInfo2.setForeground(Color.RED);
			in =false;
		}
		
		if(this.logged.currentMaze.setIJ(false,outI,outJ)) { // SI LA CASILLA DE SALIDA SE ESTABLECE
			
			this.labelInfo.setText("Casilla de salida fijada");
			this.labelInfo.setForeground(Color.GREEN);
			out =true;
			
		}else { // EN OTRO CASO, SI EL USUARIO DESISTE SE TERMINA EL PROGRAMA.
			
			this.labelInfo.setText("Error en casilla de salida");
			this.labelInfo.setForeground(Color.RED);
			out =false;
		}
		
		if(in == false || out== false) {
			
			this.logged.currentMaze.deleteMaze(false);
			
		}
			
	}

}