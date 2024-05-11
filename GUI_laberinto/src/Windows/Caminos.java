package Windows;

import javax.swing.*;
import Classes.Config;
import java.awt.*;
import java.awt.event.*;

public class Caminos extends JFrame implements ActionListener{

	public Logged logged;
	private Container contenedor;
	private JLabel titulo, labelInfo;
	private JButton exit, primerCamino, mejorCamino;
	
	public Caminos(Logged logged) {
		
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
			
		}else if(e.getSource()==this.primerCamino) {
			
			this.primerCamino();
			
		}else if(e.getSource()==this.mejorCamino) {
			
			this.mejorCamino();
			
		}
		
	}
	
	private void titulo() {

		this.titulo = new JLabel("BUSCAR CAMINOS");
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
		
		this.mejorCamino=new JButton("Camino mas corto");
		this.mejorCamino.setFont(new Font("Arial", Font.PLAIN, 16));
		this.mejorCamino.setBounds(115, 295, 160, 25);
		this.mejorCamino.addActionListener(this);
		this.contenedor.add(this.mejorCamino);
		
		this.primerCamino=new JButton("Primer camino encontrado");
		this.primerCamino.setFont(new Font("Arial", Font.PLAIN, 16));
		this.primerCamino.setBounds(115, 295, 160, 25);
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
		
		
		
	}
	
	private void primerCamino() {
		
		
		
	}

}