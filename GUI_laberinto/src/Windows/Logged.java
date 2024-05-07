package Windows;

import javax.swing.*;

import Classes.Config;
import Classes.Log;

import java.awt.*;
import java.awt.event.*;

public class Logged extends JFrame implements ActionListener{
	
	private Login loginVentana;
	private Container contenedor;
	private JLabel titulo, labelInfo;
	private JButton cargarLaberinto, verLaberinto, exit, EntradaSalida, BuscarCaminos, info, logout;
	
	public Logged(Login login) {
		
		this.loginVentana=login;
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
		
		if(e.getSource()==this.cargarLaberinto) {
			
			this.cargarlaberinto();
			
		}else if(e.getSource()==this.exit) {
			
			this.exit();

		}else if(e.getSource()==this.verLaberinto) {
			
			this.verlaberinto();

		}else if(e.getSource()==this.EntradaSalida) {
			
			this.entradasalida();

		}else if(e.getSource()==this.BuscarCaminos) {
			
			this.buscarcaminos();

		}else if(e.getSource()==this.info) {
			
			this.info();

		}else if(e.getSource()==this.logout) {
			
			this.logout();

		}
		
	}
	
	private void titulo() {

		this.titulo = new JLabel("OPCIONES AVANZADAS");
		this.titulo.setFont(new Font("Arial", Font.BOLD, 24));
		this.titulo.setBounds(50, 30, 500, 40);
		this.contenedor.add(this.titulo);

	}
	
	private void botones() {
		
		this.cargarLaberinto=new JButton("Cargar Laberinto");
		this.cargarLaberinto.setFont(new Font("Arial", Font.PLAIN, 16));
		this.cargarLaberinto.setBounds(50,100,160,25);
		this.cargarLaberinto.addActionListener(this);
		this.contenedor.add(this.cargarLaberinto);
		
		this.verLaberinto=new JButton("Ver Laberinto");
		this.verLaberinto.setFont(new Font("Arial", Font.PLAIN, 16));
		this.verLaberinto.setBounds(50,130,160,25);
		this.verLaberinto.addActionListener(this);
		this.contenedor.add(this.verLaberinto);	
		
		this.EntradaSalida=new JButton("Establecer In Out");
		this.EntradaSalida.setFont(new Font("Arial", Font.PLAIN, 16));
		this.EntradaSalida.setBounds(50,160,160,25);
		this.EntradaSalida.addActionListener(this);
		this.contenedor.add(this.EntradaSalida);
		
		this.BuscarCaminos=new JButton("Buscar Caminos");
		this.BuscarCaminos.setFont(new Font("Arial", Font.PLAIN, 16));
		this.BuscarCaminos.setBounds(50,190,160,25);
		this.BuscarCaminos.addActionListener(this);
		this.contenedor.add(this.BuscarCaminos);
		
		this.info=new JButton("Info Usuario");
		this.info.setFont(new Font("Arial", Font.PLAIN, 16));
		this.info.setBounds(50,220,160,25);
		this.info.addActionListener(this);
		this.contenedor.add(this.info);
		
		this.logout=new JButton("Logout");
		this.logout.setFont(new Font("Arial", Font.PLAIN, 16));
		this.logout.setBounds(50,250,160,25);
		this.logout.addActionListener(this);
		this.contenedor.add(this.logout);
		
		this.exit=new JButton("Salir");
		this.exit.setFont(new Font("Arial", Font.PLAIN, 16));
		this.exit.setBounds(50, 280, 160, 25);
		this.exit.addActionListener(this);
		this.contenedor.add(this.exit);

	}

	private void labels() {

		// label info
		this.labelInfo = new JLabel("");
		this.labelInfo.setFont(new Font("Arial", Font.BOLD, 16));
		this.labelInfo.setBounds(250, 100, 500, 200);
		this.contenedor.add(this.labelInfo);

	}

	private void cargarlaberinto() {

	}

	private void exit() {

		Log.insertLog(Log.EXIT, "");
		System.exit(0);

	}

	private void verlaberinto() {

	}

	private void entradasalida() {

	}

	private void buscarcaminos() {

	}

	private void info() {
		
		this.labelInfo.setText(this.loginVentana.unlogged.currentSession.showUser());
		this.labelInfo.setForeground(Color.BLACK);

	}

	private void logout() {
		
		this.setVisible(false);
		this.loginVentana.unlogged.setVisible(true);
		this.loginVentana.unlogged.currentSession.logOut();
		
	}

}