package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class Ventana extends JFrame{
	
	JPanel miPanel;
	
	public Ventana(){
		//x, y
		setSize(1200, 800);
		setTitle("Test");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		iniciarComponentes();
		agregarBotones();
		agregarVisor();
		
		setVisible(true);
		
	}
	
	private void iniciarComponentes(){
		//agregamos un panel
		miPanel = new Panel();
		miPanel.setBackground(Color.white);
		miPanel.setLayout(null);
		add(miPanel);
		
		//agregamos titulo
		JLabel titulo = new JLabel();
		titulo.setText("Aplicacion de práctica");
		titulo.setBounds(15, 15, 350, 30);
		titulo.setFont(new Font("arial", Font.BOLD, 30));
		miPanel.add(titulo);

	}
	
	private void agregarBotones() {
		//boton1
		JButton boton1 = new JButton("Actualizar clientes");
		JButton boton2 = new JButton("Actualizar pedidos");
		JButton boton3 = new JButton("Eliminar clientes");
		JButton boton4 = new JButton("Eliminar pedidos");
		JButton boton5 = new JButton("Buscar un cliente");
		JButton boton6 = new JButton("Buscar pedidos por cliente");
		JButton boton7 = new JButton("Ver todos los clientes");
		JButton boton8 = new JButton("Ver todos los pedidos");
		
		boton1.setBounds(35, 150, 200, 30);
		boton2.setBounds(35, 200, 200, 30);
		boton3.setBounds(35, 250, 200, 30);
		boton4.setBounds(35, 300, 200, 30);
		boton5.setBounds(35, 350, 200, 30);
		boton6.setBounds(35, 400, 200, 30);
		boton7.setBounds(35, 450, 200, 30);
		boton8.setBounds(35, 500, 200, 30);
		
		miPanel.add(boton1);
		miPanel.add(boton2);
		miPanel.add(boton3);
		miPanel.add(boton4);
		miPanel.add(boton5);
		miPanel.add(boton6);
		miPanel.add(boton7);
		miPanel.add(boton8);
		
	}
	
	private void agregarVisor() {
		JTextArea visor = new JTextArea(); 
		visor.setBounds(300, 150, 800, 550);
		visor.setBackground(Color.WHITE);
		visor.setText("Aquí se colocarán los resultados de su consulta");
		
		JScrollPane scroll = new JScrollPane(visor);
		scroll.setBounds(300, 150, 800, 550);
		scroll.setBackground(Color.WHITE);
		
		miPanel.add(scroll);
	}

}
