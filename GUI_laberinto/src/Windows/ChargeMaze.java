package Windows;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Classes.Config;
import Classes.Maze;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChargeMaze extends JFrame implements ActionListener, MouseListener{
	
	/*
	 * Cargar laberinto:
Aparecerá el listado con los ficheros para seleccionar uno con el ratón y cargarlo con un
botón. También se permite la opción de escribir el nombre del fichero mediante un campo de
texto. Debe tener un botón para volver atrás.
	 */
	private Logged logged;
	private Maze maze;
	private Container contenedor;
	private JLabel titulo, labelBuscar, labelInfo;
	private JTextField campoBuscar;
	private JButton limpiar, enviar, volver;
	private JTable resultados;
	private JScrollPane tableScroll;
	private String[] columnas = {"Laberintos"};
	
	public ChargeMaze(Logged logged, Maze maze) {
		
		this.maze=maze;
		this.logged=logged;
		this.setTitle("Maze_Solver "+Config.VERSION_CODE);
		this.setBounds(600,200,400,500);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		this.contenedor=this.getContentPane();
		this.contenedor.setLayout(null);
		
		titulo();
		labels();
		botones();
		tabla();	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.enviar) {
			
			this.enviar();
			
		}else if(e.getSource()==this.limpiar) {
			
			this.limpiarFormulario();

		}else if(e.getSource()==this.volver) {
			
			this.volver();

		}
		
	}
	
	private void tabla() {
		
		this.resultados = new JTable(new String[0][0],this.columnas);
		this.tableScroll=new JScrollPane(this.resultados);
		this.tableScroll.setBounds(30,140,332,220);
		this.contenedor.add(this.tableScroll);
		this.rellenarTabla();
		
	}
	
	private void titulo() {

		this.titulo = new JLabel("CARGAR LABERINTO");
		this.titulo.setFont(new Font("Arial", Font.PLAIN, 24));
		this.titulo.setBounds(30, 21, 500, 40);
		this.contenedor.add(this.titulo);

	}
	
	private void botones() {
		
		this.enviar=new JButton("Cragar");
		this.enviar.setFont(new Font("Arial", Font.PLAIN, 16));
		this.enviar.setBounds(250,90,95,25);
		this.enviar.addActionListener(this);
		this.contenedor.add(this.enviar);
		
		this.limpiar=new JButton("Limpiar campos");
		this.limpiar.setFont(new Font("Arial", Font.PLAIN, 16));
		this.limpiar.setBounds(50,374,150,25);
		this.limpiar.addActionListener(this);
		this.contenedor.add(this.limpiar);	
		
		this.volver=new JButton("Volver");
		this.volver.setFont(new Font("Arial", Font.PLAIN, 16));
		this.volver.setBounds(224,374,95,25);
		this.volver.addActionListener(this);
		this.contenedor.add(this.volver);	
		
	}
	
	private void labels() {

		// buscar
		this.labelBuscar = new JLabel("Buscar laberinto:");
		this.labelBuscar.setFont(new Font("Arial", Font.PLAIN, 18));
		this.labelBuscar.setBounds(30, 61, 200, 30);
		this.contenedor.add(this.labelBuscar);

		// campo buscar

		this.campoBuscar = new JTextField();
		this.campoBuscar.setFont(new Font("Arial", Font.PLAIN, 16));
		this.campoBuscar.setBounds(30, 88, 200, 30);
		this.contenedor.add(this.campoBuscar);

		// label info

		this.labelInfo = new JLabel("");
		this.labelInfo.setFont(new Font("Arial", Font.BOLD, 20));
		this.labelInfo.setBounds(30, 410, 332, 40);
		this.contenedor.add(this.labelInfo);

	}

	private void limpiarFormulario() {
		
		this.campoBuscar.setText("");
		this.labelInfo.setText("");

	}
	
	private void enviar() {
		
		String nombre = this.campoBuscar.getText().trim();
		ArrayList<String> mazeNames = this.maze.obtainTxtNames(Config.MAZES_PATH);
		boolean listado=false;
		
		for (int i = 0; i < mazeNames.size(); i++) {
			
			if(nombre.equals(mazeNames.get(i))) {
				
				listado=true;
				
			}
			
		}
		
		if(nombre.length()==0) {
			
			this.labelInfo.setText("Todos los campos son obligatorios.");
			this.labelInfo.setForeground(Color.RED);
			return;
			
		}
		
		if(listado==false) {
			
			this.labelInfo.setText("Ese laberinto no existe");
			this.labelInfo.setForeground(Color.RED);
			return;
			
		}
		
		if(this.maze.loadMaze(nombre)) {
			
			this.labelInfo.setText("Laberinto cargado correctamente");
			this.labelInfo.setForeground(Color.GREEN);
			
		}else {
			
			this.labelInfo.setText("Error al cargar laberinto");
			this.labelInfo.setForeground(Color.RED);
			
		}
		
	}
	
	private void volver() {
		
		this.setVisible(false);
		this.logged.setVisible(true);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void rellenarTabla() {
		
		ArrayList<String> mazeNames = this.maze.obtainTxtNames(Config.MAZES_PATH);
		int filas = mazeNames.size();
		int columnas = this.columnas.length;
		
		String[][] datosTabla = new String[filas][columnas];
		
		for (int i = 0; i < mazeNames.size(); i++) {
			
			datosTabla[i][0] =  mazeNames.get(i);
			
		}
		
		this.resultados.setModel(new DefaultTableModel(datosTabla,this.columnas));
		
	}

}