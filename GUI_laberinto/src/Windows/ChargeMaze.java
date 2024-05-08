package Windows;

import javax.swing.*;

import Classes.Config;

import java.awt.*;
import java.awt.event.*;

public class ChargeMaze extends JFrame implements ActionListener{
	
	public Unlogged unlogged;
	private Container contenedor;
	private JLabel titulo, labelnick, labelPassword, labelInfo;
	private JTextField campoNick, campoPassword;
	private JButton limpiar, enviar, volver;
	
	public ChargeMaze(Unlogged unlogged) {
		
		this.unlogged=unlogged;
		this.setTitle("Maze_Solver "+Config.VERSION_CODE);
		this.setBounds(600,200,700,500);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		this.contenedor=this.getContentPane();
		this.contenedor.setLayout(null);
		
		titulo();
		
		labels();
			
		botones();

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
	
	private void titulo() {

		this.titulo = new JLabel("CARGAR LABERINTO");
		this.titulo.setFont(new Font("Arial", Font.PLAIN, 24));
		this.titulo.setBounds(50, 50, 500, 40);
		this.contenedor.add(this.titulo);

	}
	
	private void botones() {
		
		this.enviar=new JButton("Entrar");
		this.enviar.setFont(new Font("Arial", Font.PLAIN, 16));
		this.enviar.setBounds(50,330,95,25);
		this.enviar.addActionListener(this);
		this.contenedor.add(this.enviar);
		
		this.limpiar=new JButton("Limpiar campos");
		this.limpiar.setFont(new Font("Arial", Font.PLAIN, 16));
		this.limpiar.setBounds(150,330,150,25);
		this.limpiar.addActionListener(this);
		this.contenedor.add(this.limpiar);	
		
		this.volver=new JButton("Volver");
		this.volver.setFont(new Font("Arial", Font.PLAIN, 16));
		this.volver.setBounds(305,330,95,25);
		this.volver.addActionListener(this);
		this.contenedor.add(this.volver);	
		
	}
	
	private void labels() {

		// nombre
		this.labelnick = new JLabel("Nombre de usuario:");
		this.labelnick.setFont(new Font("Arial", Font.PLAIN, 18));
		this.labelnick.setBounds(50, 130, 200, 30);
		this.contenedor.add(this.labelnick);

		// campo nombre

		this.campoNick = new JTextField();
		this.campoNick.setFont(new Font("Arial", Font.PLAIN, 16));
		this.campoNick.setBounds(50, 160, 200, 30);
		this.contenedor.add(this.campoNick);

		// telefono

		this.labelPassword = new JLabel("Contraseña:");
		this.labelPassword.setFont(new Font("Arial", Font.PLAIN, 18));
		this.labelPassword.setBounds(50, 190, 200, 30);
		this.contenedor.add(this.labelPassword);

		// campo telefono

		this.campoPassword = new JTextField();
		this.campoPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		this.campoPassword.setBounds(50, 220, 200, 30);
		this.contenedor.add(this.campoPassword);

		// label info

		this.labelInfo = new JLabel("");
		this.labelInfo.setFont(new Font("Arial", Font.BOLD, 20));
		this.labelInfo.setBounds(300, 150, 400, 100);
		this.contenedor.add(this.labelInfo);

	}

	private void limpiarFormulario() {
		
		this.campoNick.setText("");
		this.campoPassword.setText("");
		this.labelInfo.setText("");

	}
	
	private void enviar() {
		
		String nombre = this.campoNick.getText().trim();
		String password = this.campoPassword.getText().trim();
		
		if(nombre.length()==0||password.length()==0) {
			
			this.labelInfo.setText("Todos los campos son obligatorios.");
			this.labelInfo.setForeground(Color.RED);
			return;
			
		}
		
		if(unlogged.currentSession.login(nombre,password)) {
			
			this.labelInfo.setText("Bienvenido"+unlogged.currentSession.currentUser.getName());
			this.labelInfo.setForeground(Color.GREEN);
			this.setVisible(false);
			
		}else {
			
			this.labelInfo.setText("Usuario y/o contarseña incorrectos.");
			this.labelInfo.setForeground(Color.RED);
			return;
			
		}
		
	}
	
	private void volver() {
		
		this.setVisible(false);
		this.unlogged.setVisible(true);
		
	}

}