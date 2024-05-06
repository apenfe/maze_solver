package Windows;

import javax.swing.*;

import Classes.Config;

import java.awt.*;
import java.awt.event.*;

public class Signup extends JFrame implements ActionListener{
	
	private Unlogged unlogged;
	private Container contenedor;
	private JLabel titulo, labelNombre, labelEmail, labelInfo, labelNick, labelPassword, labelNif, labelDireccion, labelfecha;
	private JTextField campoNombre, campoEmail, campoNick, campoPassword, campoNif, campoDireccion, campoFecha;
	private JButton limpiar, enviar, volver;
	
	public Signup(Unlogged unlogged) {
		
		this.unlogged=unlogged;
		this.setTitle("Maze_Solver "+Config.VERSION_CODE);
		this.setBounds(100,100,400,700);
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

		this.titulo = new JLabel("REGISTRO DE USUARIO");
		this.titulo.setFont(new Font("Arial", Font.PLAIN, 24));
		this.titulo.setBounds(50, 30, 500, 40);
		this.contenedor.add(this.titulo);

	}
	
	private void botones() {
		
		this.enviar=new JButton("enviar");
		this.enviar.setFont(new Font("Arial", Font.PLAIN, 16));
		this.enviar.setBounds(50,550,95,25);
		this.enviar.addActionListener(this);
		this.contenedor.add(this.enviar);
		
		this.limpiar=new JButton("limpiar");
		this.limpiar.setFont(new Font("Arial", Font.PLAIN, 16));
		this.limpiar.setBounds(150,550,95,25);
		this.limpiar.addActionListener(this);
		this.contenedor.add(this.limpiar);	
		
		this.volver=new JButton("Volver");
		this.volver.setFont(new Font("Arial", Font.PLAIN, 16));
		this.volver.setBounds(250,550,95,25);
		this.volver.addActionListener(this);
		this.contenedor.add(this.volver);	
		
	}
	
	private void labels() {

		// nick
		this.labelNick = new JLabel("Nick name:");
		this.labelNick.setFont(new Font("Arial", Font.PLAIN, 18));
		this.labelNick.setBounds(50, 100, 200, 30);
		this.contenedor.add(this.labelNick);

		// campo nick
		this.campoNick = new JTextField();
		this.campoNick.setFont(new Font("Arial", Font.PLAIN, 16));
		this.campoNick.setBounds(50, 130, 200, 30);
		this.contenedor.add(this.campoNick);

		// nombre
		this.labelNombre = new JLabel("Nombre completo:");
		this.labelNombre.setFont(new Font("Arial", Font.PLAIN, 18));
		this.labelNombre.setBounds(50, 160, 200, 30);
		this.contenedor.add(this.labelNombre);

		// campo nombre
		this.campoNombre = new JTextField();
		this.campoNombre.setFont(new Font("Arial", Font.PLAIN, 16));
		this.campoNombre.setBounds(50, 190, 200, 30);
		this.contenedor.add(this.campoNombre);

		// email
		this.labelEmail = new JLabel("Email:");
		this.labelEmail.setFont(new Font("Arial", Font.PLAIN, 18));
		this.labelEmail.setBounds(50, 220, 200, 30);
		this.contenedor.add(this.labelEmail);

		// campo email
		this.campoEmail = new JTextField();
		this.campoEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		this.campoEmail.setBounds(50, 250, 200, 30);
		this.contenedor.add(this.campoEmail);
		
		// nif
		this.labelNif = new JLabel("NIF:");
		this.labelNif.setFont(new Font("Arial", Font.PLAIN, 18));
		this.labelNif.setBounds(50, 280, 200, 30);
		this.contenedor.add(this.labelNif);

		// campo nif
		this.campoNif = new JTextField();
		this.campoNif.setFont(new Font("Arial", Font.PLAIN, 16));
		this.campoNif.setBounds(50, 310, 200, 30);
		this.contenedor.add(this.campoNif);

		// direccion
		this.labelDireccion = new JLabel("Direccion:");
		this.labelDireccion.setFont(new Font("Arial", Font.PLAIN, 18));
		this.labelDireccion.setBounds(50, 340, 200, 30);
		this.contenedor.add(this.labelDireccion);

		// campo direccion
		this.campoDireccion = new JTextField();
		this.campoDireccion.setFont(new Font("Arial", Font.PLAIN, 16));
		this.campoDireccion.setBounds(50, 370, 200, 30);
		this.contenedor.add(this.campoDireccion);

		// fecha
		this.labelfecha = new JLabel("Fecha nacimiento:");
		this.labelfecha.setFont(new Font("Arial", Font.PLAIN, 18));
		this.labelfecha.setBounds(50, 400, 200, 30);
		this.contenedor.add(this.labelfecha);

		// campo fecha
		this.campoFecha = new JTextField();
		this.campoFecha.setFont(new Font("Arial", Font.PLAIN, 16));
		this.campoFecha.setBounds(50, 430, 200, 30);
		this.contenedor.add(this.campoFecha);

		// contraseña
		this.labelPassword = new JLabel("Contraseña:");
		this.labelPassword.setFont(new Font("Arial", Font.PLAIN, 18));
		this.labelPassword.setBounds(50, 460, 200, 30);
		this.contenedor.add(this.labelPassword);

		// campo contraseña
		this.campoPassword = new JTextField();
		this.campoPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		this.campoPassword.setBounds(50, 490, 200, 30);
		this.contenedor.add(this.campoPassword);

		// label info
		this.labelInfo = new JLabel("");
		this.labelInfo.setFont(new Font("Arial", Font.BOLD, 15));
		this.labelInfo.setBounds(50, 550, 400, 100);
		this.contenedor.add(this.labelInfo);

	}

	private void limpiarFormulario() {
		
		this.campoNombre.setText("");
		this.campoEmail.setText("");
		this.campoNif.setText("");
		this.campoDireccion.setText("");
		this.campoFecha.setText("");
		this.campoNick.setText("");
		this.campoPassword.setText("");
		this.labelInfo.setText("");

	}
	
	private void enviar() {
		
		String[] data =new String[7];
		data[0]=this.campoNick.getText().trim();
		data[1]=this.campoNif.getText().trim();
		data[2]=this.campoEmail.getText().trim();
		data[3]=this.campoPassword.getText().trim();
		data[4]= this.campoNombre.getText().trim();
		data[5]=this.campoDireccion.getText().trim();
		data[6]=this.campoFecha.getText().trim();
		
		for (int i = 0; i < data.length; i++) {
			
			if(data[i].length()==0) {
				
				this.labelInfo.setText("Todos los campos son obligatorios.");
				this.labelInfo.setForeground(Color.RED);
				return;
			}
			
		}
		
		if(Unlogged.currentSession.checkData(data)) {
			
			if(Unlogged.currentSession.signup(data)) {
				
				this.labelInfo.setText("Usuario creado correctamente.");
				this.labelInfo.setForeground(Color.GREEN);
				
			}else {
				
				this.labelInfo.setText("Error al crear usuario");
				this.labelInfo.setForeground(Color.RED);
				
			}
			
		}else {
			
			this.labelInfo.setText("El formato de los datos no es valido.");
			this.labelInfo.setForeground(Color.RED);
			
		}
		
		
	}
	
	private void volver() {
		
		this.setVisible(false);
		this.unlogged.setVisible(true);
		
	}

}