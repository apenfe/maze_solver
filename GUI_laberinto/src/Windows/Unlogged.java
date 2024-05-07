package Windows;

import javax.swing.*;

import Classes.Config;
import Classes.Log;
import Classes.Session;

import java.awt.*;
import java.awt.event.*;

public class Unlogged extends JFrame implements ActionListener{
	
	public  Session currentSession = new Session();
	
	private Container contenedor;
	private JLabel titulo, labelInfo;
	private JButton login, signup, exit;
	
	public Unlogged() {
		
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
		
		if(e.getSource()==this.login) {
			
			this.login();
			
		}else if(e.getSource()==this.signup) {
			
			this.signup();

		}else if(e.getSource()==this.exit) {
			
			this.exit();

		}
		
	}
	
	private void titulo() {

		this.titulo = new JLabel("GUI MAZE SOLVER");
		this.titulo.setFont(new Font("Arial", Font.BOLD, 24));
		this.titulo.setBounds(50, 50, 500, 40);
		this.contenedor.add(this.titulo);

	}
	
	private void botones() {
		
		this.login=new JButton("Login");
		this.login.setFont(new Font("Arial", Font.PLAIN, 16));
		this.login.setBounds(50,330,95,25);
		this.login.addActionListener(this);
		this.contenedor.add(this.login);
		
		this.signup=new JButton("Signup");
		this.signup.setFont(new Font("Arial", Font.PLAIN, 16));
		this.signup.setBounds(150,330,95,25);
		this.signup.addActionListener(this);
		this.contenedor.add(this.signup);	
		
		this.exit=new JButton("Salir");
		this.exit.setFont(new Font("Arial", Font.PLAIN, 16));
		this.exit.setBounds(250,330,95,25);
		this.exit.addActionListener(this);
		this.contenedor.add(this.exit);
		
	}
	
	private void labels() {

		// label info
		this.labelInfo = new JLabel("");
		this.labelInfo.setFont(new Font("Arial", Font.BOLD, 20));
		this.labelInfo.setBounds(300, 150, 400, 100);
		this.contenedor.add(this.labelInfo);

	}

	private void login() {
		
		Login login = new Login(this);
		login.setVisible(true);
		this.setVisible(false);

	}
	
	private void exit() {
		
		Log.insertLog(Log.EXIT,"");
		System.exit(0);

	}
	
	private void signup() {
		
		Signup registro = new Signup(this);
		registro.setVisible(true);
		this.setVisible(false);
		
	}

}