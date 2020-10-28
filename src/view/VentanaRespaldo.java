package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.hibernate.Session;

import com.mongodb.MongoCommandException;

import src.RespaldarBaseDeDatos;

public class VentanaRespaldo extends JFrame{
	
	JPanel miPanel;
	JLabel titulo;
	JLabel leyenda;
	JLabel leyenda2;
	
	JButton respaldarProductos;
	JButton respaldarClientes;
	JButton respaldarPedidos;
	
	JButton migrarProductos;
	JButton migrarPedidos;
	JButton migrarClientes;
	
	public VentanaRespaldo(Session mySession) {
		setSize(700, 400);
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
		titulo.setFont(new Font("arial", Font.BOLD, 30));
		miPanel.add(titulo);
		
		leyenda = new JLabel();
		leyenda.setText("<html><body>Aquí puede realizar respaldo de su información.<br />Cada tabla se respalda en un archivo CSV, <br />Y se guarda donde usted lo indique.</html></body>");
		leyenda.setBounds(15, 50, 600, 60);
		leyenda.setFont(new Font("arial", Font.PLAIN, 13));
		miPanel.add(leyenda);
		
		leyenda2 = new JLabel();
		leyenda2.setText("<html><body>Aquí puede migrar su información,<br />cada tabla se envia al gestor MongoDB.<br />Para ello es necesario contar con ese gestor instalado.</html></body>");
		leyenda2.setBounds(320, 50, 600, 60);
		leyenda2.setFont(new Font("arial", Font.PLAIN, 13));
		miPanel.add(leyenda2);
	}
	
	public void agregarBotones() {
		respaldarProductos = new JButton("Respaldar productos");
		respaldarClientes = new JButton("Respaldar clientes");
		respaldarPedidos = new JButton("Respaldar pedidos");
		
		respaldarProductos.setBounds(50, 120, 200, 30);
		respaldarClientes.setBounds(50, 170, 200, 30);
		respaldarPedidos.setBounds(50, 220, 200, 30);
		
		respaldarProductos.setBackground(new Color(0, 153, 0));
		respaldarClientes.setBackground(new Color(0, 153, 0));
		respaldarPedidos.setBackground(new Color(0, 153, 0));
		
		respaldarProductos.setForeground(Color.WHITE);
		respaldarClientes.setForeground(Color.WHITE);
		respaldarPedidos.setForeground(Color.WHITE);
		
		miPanel.add(respaldarProductos);
		miPanel.add(respaldarClientes);
		miPanel.add(respaldarPedidos);
		
		migrarProductos = new JButton("Migrar Productos");
		migrarPedidos = new JButton("Migrar Pedidos");
		migrarClientes = new JButton("Migrar Clientes");
		
		migrarProductos.setBounds(360, 120, 200, 30);
		migrarPedidos.setBounds(360, 170, 200, 30);
		migrarClientes.setBounds(360, 220, 200, 30);
		
		migrarProductos.setBackground(new Color(0, 153, 0));
		migrarPedidos.setBackground(new Color(0, 153, 0));
		migrarClientes.setBackground(new Color(0, 153, 0));
		
		migrarProductos.setForeground(Color.WHITE);
		migrarPedidos.setForeground(Color.WHITE);
		migrarClientes.setForeground(Color.WHITE);
		
		miPanel.add(migrarProductos);
		miPanel.add(migrarPedidos);
		miPanel.add(migrarClientes);
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
					exc.printStackTrace();
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
		
		//migraciones
		ActionListener migracionProductos = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					RespaldarBaseDeDatos.migracionListaProductos(mySession);
					JOptionPane.showMessageDialog(null, "Migracion de productos a MongoDB Realizada con exito.");
				} catch (MongoCommandException exc) {
					JOptionPane.showMessageDialog(null, "La tabla ya ha sido migrada a una colleccion de Mongo. Chequee su gestor MongoDBCompass.");
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, "Error al intentar migrar la tabla, chequee si su gestor esta instalado y funcionando.");
				}	
			}
		};
		
		ActionListener migracionPedidos = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					RespaldarBaseDeDatos.migracionListaPedidos(mySession);
					JOptionPane.showMessageDialog(null, "Migracion de pedidos a MongoDB Realizada con exito.");
				} catch (MongoCommandException exc) {
					JOptionPane.showMessageDialog(null, "La tabla ya ha sido migrada a una colleccion de Mongo. Chequee su gestor MongoDBCompass.");
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, "Error al intentar migrar la tabla, chequee si su gestor esta instalado y funcionando.");
				}
			}
		};
		
		ActionListener migracionClientes = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					RespaldarBaseDeDatos.migracionListaClientes(mySession);
					JOptionPane.showMessageDialog(null, "Migracion de clientes a MongoDB Realizada con exito.");
				} catch (MongoCommandException exc) {
					JOptionPane.showMessageDialog(null, "La tabla ya ha sido migrada a una colleccion de Mongo. Chequee su gestor MongoDBCompass");
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, "Error al intentar migrar la tabla, chequee si su gestor esta instalado y funcionando.");
				}
			}
		};
			
		respaldarProductos.addActionListener(respaldarProd);
		respaldarClientes.addActionListener(respaldarClient);
		respaldarPedidos.addActionListener(respaldarPed);
		migrarProductos.addActionListener(migracionProductos);
		migrarPedidos.addActionListener(migracionPedidos);
		migrarClientes.addActionListener(migracionClientes);
	}
	

}
