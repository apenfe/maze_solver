package Windows;

import javax.swing.*;
import Classes.Config;
import java.awt.*;
import java.awt.event.*;

public class SetInOut extends JFrame implements ActionListener{

	public Logged logged;
	private Container contenedor;
	private JLabel titulo, labelInfo;
	private JButton exit;
	
	public SetInOut(Logged logged) {
		
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
			
			this.volver();
			
		}
		
	}
	
	private void titulo() {

		this.titulo = new JLabel("CARGAR ENTRADA Y SALIDA");
		this.titulo.setFont(new Font("Arial", Font.BOLD, 24));
		this.titulo.setBounds(50, 30, 500, 40);
		this.contenedor.add(this.titulo);

	}
	
	private void botones() {
		
		this.exit=new JButton("Volver");
		this.exit.setFont(new Font("Arial", Font.PLAIN, 16));
		this.exit.setBounds(115, 295, 160, 25);
		this.exit.addActionListener(this);
		this.contenedor.add(this.exit);

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

}