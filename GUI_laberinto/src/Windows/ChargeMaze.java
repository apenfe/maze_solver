package Windows;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Classes.Config;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChargeMaze extends JFrame implements ActionListener, MouseListener{
	
	private Logged logged;
	private Container contenedor;
	private JLabel titulo, labelBuscar, labelInfo, lblNewLabel;
	private JTextField campoBuscar;
	private JButton limpiar, enviar, volver;
	private JTable resultados;
	private JScrollPane tableScroll;
	private String[] columnas = {"Laberintos"};
	private String filename="";
	
	public ChargeMaze(Logged logged) {
		
		this.filename="laberinto1.txt";
		this.logged=logged;
		this.setTitle("Maze_Solver "+Config.VERSION_CODE);
		this.setBounds(600,200,858,500);
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
		this.tableScroll.setBounds(10,156,338,260);
		this.resultados.addMouseListener(this); ///
		this.contenedor.add(this.tableScroll);
		this.rellenarTabla();
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	    if (e.getSource() == this.resultados) {
	        // Obtener la fila seleccionada
	        int row = this.resultados.getSelectedRow();
	        // Obtener el valor de la primera columna de la fila seleccionada
	        Object selectedValue = this.resultados.getValueAt(row, 0);
	        // Puedes hacer lo que desees con el valor seleccionado, por ejemplo, imprimirlo
	        
	        if(this.logged.currentMaze.loadMaze(selectedValue.toString())) {
				
	        	this.filename=selectedValue.toString();
	        	
	        	System.out.println(selectedValue.toString());
	        	updateImage();
	        	
	   
	    		System.out.println(selectedValue.toString());
				this.labelInfo.setText("Laberinto cargado correctamente "+selectedValue.toString());
				this.labelInfo.setForeground(Color.GREEN);
				
			}else {
				
				this.labelInfo.setText("Error al cargar laberinto");
				this.labelInfo.setForeground(Color.RED);
				
			}
	    }
	}
	
	private void titulo() {

		this.titulo = new JLabel("CARGAR LABERINTO");
		this.titulo.setFont(new Font("Arial", Font.PLAIN, 24));
		this.titulo.setBounds(30, 11, 500, 40);
		this.contenedor.add(this.titulo);

	}
	
	private void botones() {
		
		this.enviar=new JButton("Cargar");
		this.enviar.setFont(new Font("Arial", Font.PLAIN, 16));
		this.enviar.setBounds(72,124,95,25);
		this.enviar.addActionListener(this);
		this.contenedor.add(this.enviar);
		
		this.limpiar=new JButton("Limpiar");
		this.limpiar.setFont(new Font("Arial", Font.PLAIN, 16));
		this.limpiar.setBounds(201,124,95,25);
		this.limpiar.addActionListener(this);
		this.contenedor.add(this.limpiar);	
		
		this.volver=new JButton("Volver");
		this.volver.setFont(new Font("Arial", Font.PLAIN, 16));
		this.volver.setBounds(147,425,95,25);
		this.volver.addActionListener(this);
		this.contenedor.add(this.volver);	
		
		this.lblNewLabel = new JLabel("");
		this.lblNewLabel.setBounds(358, 11, 474, 439); // Establece el tamaño del JLabel
		this.contenedor.add(lblNewLabel);
		
		String imagePath = "C:\\Users\\adria\\DAW\\1º AÑO\\PROGRAMACION\\maze_solver\\GUI_laberinto\\images\\" +this.filename + ".jpg";
		ImageIcon imageIcon = new ImageIcon(imagePath);

		// Escalar la imagen al tamaño del JLabel
		Image image = imageIcon.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);

		// Crear un nuevo ImageIcon con la imagen escalada
		ImageIcon scaledImageIcon = new ImageIcon(image);

		// Establecer la imagen escalada en el JLabel
		this.lblNewLabel.setIcon(scaledImageIcon);
		
	}
	
	private void labels() {

		// buscar
		this.labelBuscar = new JLabel("Buscar laberinto:");
		this.labelBuscar.setFont(new Font("Arial", Font.PLAIN, 18));
		this.labelBuscar.setBounds(10, 47, 142, 30);
		this.contenedor.add(this.labelBuscar);

		// campo buscar

		this.campoBuscar = new JTextField();
		this.campoBuscar.setFont(new Font("Arial", Font.PLAIN, 16));
		this.campoBuscar.setBounds(148, 48, 200, 30);
		this.contenedor.add(this.campoBuscar);

		// label info

		this.labelInfo = new JLabel("");
		this.labelInfo.setFont(new Font("Arial", Font.BOLD, 20));
		this.labelInfo.setBounds(16, 88, 332, 30);
		this.contenedor.add(this.labelInfo);

	}

	private void limpiarFormulario() {
		
		this.campoBuscar.setText("");
		this.labelInfo.setText("");

	}
	
	private void enviar() {
		
		String nombre = this.campoBuscar.getText().trim();
		ArrayList<String> mazeNames = this.logged.currentMaze.obtainTxtNames(Config.MAZES_PATH);
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
		
		if(this.logged.currentMaze.loadMaze(nombre)) {
			
			this.labelInfo.setText("Laberinto cargado correctamente "+nombre);
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
		
		ArrayList<String> mazeNames = this.logged.currentMaze.obtainTxtNames(Config.MAZES_PATH);
		int filas = mazeNames.size();
		int columnas = this.columnas.length;
		
		String[][] datosTabla = new String[filas][columnas];
		
		for (int i = 0; i < mazeNames.size(); i++) {
			
			datosTabla[i][0] =  mazeNames.get(i);
			
		}
		
		this.resultados.setModel(new DefaultTableModel(datosTabla,this.columnas));
		
	}
	
	private void updateImage() {
		
		if (lblNewLabel != null) {
	    // Cargar la nueva imagen en un ImageIcon
	    ImageIcon newImageIcon = new ImageIcon("C:\\Users\\adria\\DAW\\1º AÑO\\PROGRAMACION\\maze_solver\\GUI_laberinto\\images\\" +this.filename + ".jpg");
	    
	    // Escalar la imagen al tamaño del JLabel
	    Image image = newImageIcon.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
	    
	    // Crear un nuevo ImageIcon con la imagen escalada
	    ImageIcon scaledImageIcon = new ImageIcon(image);
	    
	    // Establecer la nueva imagen escalada en el JLabel
	    this.lblNewLabel.setIcon(scaledImageIcon);
	    
		}
	}

}