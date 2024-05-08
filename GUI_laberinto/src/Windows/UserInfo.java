package Windows;

import javax.swing.*;

import Classes.Config;

import java.awt.*;
import java.awt.event.*;

public class UserInfo extends JFrame implements ActionListener{
	
	public Logged logged;
	private Container contenedor;
	private JLabel titulo, labelInfo;
	private JButton exit;
	
	public UserInfo(Logged logged) {
		
		this.logged=logged;
		this.setTitle("Maze_Solver "+Config.VERSION_CODE);
		this.setBounds(600,200,400,500);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		this.contenedor=this.getContentPane();
		this.contenedor.setLayout(null);
		
		this.titulo();
		this.labels();
		this.botones();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.exit) {
			
			this.exit();
			
		}
		
	}
	
	private void titulo() {

		this.titulo = new JLabel("DATOS DE USUARIO");
		this.titulo.setFont(new Font("Arial", Font.BOLD, 24));
		this.titulo.setBounds(50, 50, 500, 40);
		this.contenedor.add(this.titulo);

	}
	
	private void botones() {
		
		this.exit=new JButton("Volver");
		this.exit.setFont(new Font("Arial", Font.PLAIN, 16));
		this.exit.setBounds(250,330,95,25);
		this.exit.addActionListener(this);
		this.contenedor.add(this.exit);
		
	}
	
	private void labels() {

		// label info
		this.labelInfo = new JLabel("");
		this.labelInfo.setFont(new Font("Arial", Font.BOLD, 14));
		this.labelInfo.setBounds(40, 80, 400, 200);
		this.contenedor.add(this.labelInfo);
		
		this.labelInfo.setText(this.logged.loginVentana.unlogged.currentSession.showUser());
		this.labelInfo.setForeground(Color.BLACK);

	}
	
	private void exit() {
		
		this.setVisible(false);
		this.logged.setVisible(true);

	}

}