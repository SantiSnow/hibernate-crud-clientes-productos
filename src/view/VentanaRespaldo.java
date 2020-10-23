package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

import org.hibernate.Session;

import src.RespaldarBaseDeDatos;

public class VentanaRespaldo extends JFrame{
	
	JPanel miPanel;
	JLabel titulo;
	
	JButton respaldarProductos;
	JButton respaldarClientes;
	JButton respaldarPedidos;
	
	public VentanaRespaldo(Session mySession) {
		setSize(600, 400);
		setTitle("Respaldar tablas");
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		agregaPanel();
		agregarTitulo();
		agregarBotones();
		addActions(mySession);
		
		setVisible(true);
	}
	
	public void agregaPanel() {
		miPanel = new JPanel();
		miPanel.setBackground(Color.white);
		miPanel.setLayout(null);
		add(miPanel);
	}
	
	public void agregarTitulo() {
		titulo = new JLabel();
		titulo.setText("Respaldar tablas completas:");
		titulo.setBounds(15, 15, 600, 30);
		titulo.setFont(new Font("arial", Font.BOLD, 25));
		miPanel.add(titulo);
	}
	
	public void agregarBotones() {
		respaldarProductos = new JButton("Respaldar productos");
		respaldarClientes = new JButton("Respaldar clientes");
		respaldarPedidos = new JButton("Respaldar pedidos");
		
		respaldarProductos.setBounds(50, 100, 200, 30);
		respaldarClientes.setBounds(50, 150, 200, 30);
		respaldarPedidos.setBounds(50, 200, 200, 30);
		
		respaldarProductos.setBackground(new Color(0, 153, 0));
		respaldarClientes.setBackground(new Color(0, 153, 0));
		respaldarPedidos.setBackground(new Color(0, 153, 0));
		
		respaldarProductos.setForeground(Color.WHITE);
		respaldarClientes.setForeground(Color.WHITE);
		respaldarPedidos.setForeground(Color.WHITE);
		
		miPanel.add(respaldarProductos);
		miPanel.add(respaldarClientes);
		miPanel.add(respaldarPedidos);
	}
	
	public void addActions(Session mySession) {
		//respaldo de productos
		ActionListener respaldarProd = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					RespaldarBaseDeDatos.respaldarProd(mySession);
					JOptionPane.showInternalMessageDialog(null, "Respaldo de productos creado en: " + "\nC:\\users\\Santiago\\respaldo-productos.csv");
				}
				catch(Exception exc){
					JOptionPane.showInternalMessageDialog(null, "Error al respaldar la base de datos");
				}
				
			}
		};
		
		//respaldo de clientes
		ActionListener respaldarClient = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				try {
		            RespaldarBaseDeDatos.respaldarCli(mySession);
		            JOptionPane.showInternalMessageDialog(null, "Respaldo de clientes creado en: " + "\nC:\\users\\Santiago\\respaldo-clientes.csv");
				}
				catch(Exception exc){
					JOptionPane.showInternalMessageDialog(null, "Error al respaldar la base de datos");
				}
				
			}
		};

		//respaldo de pedidos
		ActionListener respaldarPed = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					RespaldarBaseDeDatos.respaldarPed(mySession);
			        JOptionPane.showInternalMessageDialog(null, "Respaldo de pedidos creado en: " + "\nC:\\users\\Santiago\\respaldo-pedidos.csv");
				}
				catch(Exception exc){
					JOptionPane.showInternalMessageDialog(null, "Error al respaldar la base de datos");
				}
				
			}
		};
	
		respaldarProductos.addActionListener(respaldarProd);
		respaldarClientes.addActionListener(respaldarClient);
		respaldarPedidos.addActionListener(respaldarPed);
	}
	

}
