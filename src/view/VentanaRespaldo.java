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

public class VentanaRespaldo extends JFrame{
	
	JPanel miPanel;
	JLabel titulo;
	
	JButton respaldarProductos;
	JButton respaldarClientes;
	JButton respaldarPedidos;
	
	public VentanaRespaldo() {
		setSize(600, 400);
		setTitle("Respaldar tablas");
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		agregaPanel();
		agregarTitulo();
		agregarBotones();
		addActions();
		
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
	
	public void addActions() {
		//respaldo de productos
		ActionListener respaldarProd = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String filename = "C:\\users\\Santiago\\respaldo-productos.csv";
				try {
		            FileWriter fw = new FileWriter(filename);
		            Class.forName("com.mysql.jdbc.Driver");
		            Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernate?serverTimezone=UTC&useSSL=false", "root", "");
		            Statement miStatement = miConexion.createStatement();
		            String sentenciaSql = "SELECT * FROM producto";
		            ResultSet rs = miStatement.executeQuery(sentenciaSql);
	
		            while (rs.next()) {
		                fw.append(rs.getString(1));
		                fw.append(',');
		                fw.append(rs.getString(2));
		                fw.append(',');
		                fw.append(rs.getString(3));
		                fw.append(',');
		                fw.append(rs.getString(4));
		                fw.append(',');
		                fw.append(rs.getString(5));
		                fw.append(',');
		                fw.append(rs.getString(6));
		                fw.append('\n');
		            }
		            fw.flush();
		            fw.close();
		            miConexion.close();
		            JOptionPane.showInternalMessageDialog(null, "Respaldo de productos creado en: "
		            		+ "\nC:\\users\\Santiago\\respaldo-productos.csv");
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
				String filename = "C:\\users\\Santiago\\respaldo-clientes.csv";
				try {
		            FileWriter fw = new FileWriter(filename);
		            Class.forName("com.mysql.jdbc.Driver");
		            Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernate?serverTimezone=UTC&useSSL=false", "root", "");
		            Statement miStatement = miConexion.createStatement();
		            String sentenciaSql = "SELECT * FROM cliente";
		            ResultSet rs = miStatement.executeQuery(sentenciaSql);
	
		            while (rs.next()) {
		                fw.append(rs.getString(1));
		                fw.append(',');
		                fw.append(rs.getString(2));
		                fw.append(',');
		                fw.append(rs.getString(3));
		                fw.append(',');
		                fw.append(rs.getString(4));
		                fw.append(',');
		                fw.append(rs.getString(5));
		                fw.append(',');
		                fw.append(rs.getString(6));
		                fw.append('\n');
		            }
		            fw.flush();
		            fw.close();
		            miConexion.close();
		            JOptionPane.showInternalMessageDialog(null, "Respaldo de clientes creado en: "
		            		+ "\nC:\\users\\Santiago\\respaldo-clientes.csv");
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
				String filename = "C:\\users\\Santiago\\respaldo-pedidos.csv";
				try {
		            FileWriter fw = new FileWriter(filename);
		            Class.forName("com.mysql.jdbc.Driver");
		            Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernate?serverTimezone=UTC&useSSL=false", "root", "");
		            Statement miStatement = miConexion.createStatement();
		            String sentenciaSql = "SELECT * FROM pedido";
		            ResultSet rs = miStatement.executeQuery(sentenciaSql);
	
		            while (rs.next()) {
		                fw.append(rs.getString(1));
		                fw.append(',');
		                fw.append(rs.getString(2));
		                fw.append(',');
		                fw.append(rs.getString(3));
		                fw.append(',');
		                fw.append(rs.getString(4));
		                fw.append('\n');
		            }
		            fw.flush();
		            fw.close();
		            miConexion.close();
		            JOptionPane.showInternalMessageDialog(null, "Respaldo de pedidos creado en: "
		            		+ "\nC:\\users\\Santiago\\respaldo-pedidos.csv");
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
