package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import src.DeletePedido;
import src.DeleteRegistro;
import src.Insert;
import src.InsertPedido;

import javax.swing.*;

public class Ventana extends JFrame{
	
	JPanel miPanel;
	
	public Ventana(SessionFactory sF, Session mySession){
		//x, y
		setSize(1200, 800);
		setTitle("Test");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		iniciarComponentes();
		agregarComponentes(sF, mySession);
		
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
		titulo.setText("Aplicacion de pr�ctica");
		titulo.setBounds(15, 15, 350, 30);
		titulo.setFont(new Font("arial", Font.BOLD, 30));
		miPanel.add(titulo);

	}
	
	private void agregarComponentes(SessionFactory sF, Session mySession){
		//boton1
		JButton boton1 = new JButton("Buscar clientes por ID");
		JButton boton2 = new JButton("Buscar pedidos por ID");
		JButton boton3 = new JButton("Eliminar clientes");
		JButton boton4 = new JButton("Eliminar pedidos");
		JButton boton5 = new JButton("Actualizar un cliente");
		JButton boton6 = new JButton("Actualizar pedidos del cliente");
		JButton boton7 = new JButton("Ver todos los clientes");
		JButton boton8 = new JButton("Ver todos los pedidos");
		JButton boton9 = new JButton("Crear cliente");
		JButton boton10 = new JButton("Crear pedido");
		
		boton1.setBounds(35, 150, 200, 30);
		boton2.setBounds(35, 200, 200, 30);
		boton3.setBounds(35, 250, 200, 30);
		boton4.setBounds(35, 300, 200, 30);
		boton5.setBounds(35, 350, 200, 30);
		boton6.setBounds(35, 400, 200, 30);
		boton7.setBounds(35, 450, 200, 30);
		boton8.setBounds(35, 500, 200, 30);
		boton9.setBounds(35, 550, 200, 30);
		boton10.setBounds(35, 600, 200, 30);
		
		miPanel.add(boton1);
		miPanel.add(boton2);
		miPanel.add(boton3);
		miPanel.add(boton4);
		miPanel.add(boton5);
		miPanel.add(boton6);
		miPanel.add(boton7);
		miPanel.add(boton8);
		miPanel.add(boton9);
		miPanel.add(boton10);
		
		JTextArea visor = new JTextArea(); 
		visor.setBounds(300, 150, 800, 550);
		visor.setBackground(Color.WHITE);
		visor.setText("Aqu� se colocar�n los resultados de su consulta");
		
		JScrollPane scroll = new JScrollPane(visor);
		scroll.setBounds(300, 150, 800, 550);
		scroll.setBackground(Color.WHITE);
		
		miPanel.add(scroll);
		
		
		
		
		
		
		//buscar cliente
		ActionListener buscarCliente = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer idCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del cliente a buscar"));
				
				visor.append("\nID del cliente ingresado: " + idCliente);
			}
		};
		
		//buscar pedidos
		ActionListener buscarPedido = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer idCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del cliente para buscar sus pedidos"));
				
				visor.append("\nID del cliente ingresado:" + idCliente);
			}
		};
		
		//Eliminar clientes
		ActionListener eliminarCliente = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer idCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del cliente a eliminar"));
				try {
					DeleteRegistro.deleteCliente(sF, mySession, idCliente);
					visor.append("\nID del cliente ingresado:" + idCliente);
				}
				catch (Exception exception) {
					System.out.println(exception);
				}
			}
		};
		
		//Eliminar pedidos
		ActionListener eliminarPedido = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer idPedido = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del pedido a eliminar"));
				try {
					DeletePedido.deletePedidos(sF, mySession, idPedido);
				} catch (Exception exception) {
					System.out.println(exception);
				}
				visor.append("\nID del pedido ingresado:" + idPedido);
			}
		};
		
		
		
		//crear cliente
		ActionListener crearCliente = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//insertar cliente
				String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre del cliente: ");
				String apellido = JOptionPane.showInputDialog(null, "Ingrese apellido del cliente: ");
				String direccion = JOptionPane.showInputDialog(null, "Ingrese direcci�n del cliente: ");
				Integer telefono = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese telefono del cliente: "));
				Integer compras = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese cantidad de compras del cliente: "));
				String correo = JOptionPane.showInputDialog(null, "Ingrese correo del cliente: ");
				String comentarios = JOptionPane.showInputDialog(null, "Ingrese comentarios del cliente: ");
				try {
					Insert.insertarCliente(nombre, apellido, direccion, telefono, compras, correo, comentarios, sF, mySession);
				} catch (Exception exception) {
					System.out.println(exception);
				}
				visor.append("\nNombre ingresado: " + nombre);
				visor.append("\nApellido ingresado: " + apellido);
				visor.append("\nDirecci�n ingresado: "+ direccion);
				visor.append("\nTelefono ingresado: "+ telefono);
				visor.append("\nCompras ingresadas: "+ compras);
				visor.append("\nCorreo ingresado: "+ correo);
				visor.append("\nComentarios ingresados: "+ comentarios);
				visor.append("\n "+ comentarios);
			}
		};
		
		//crear pedido
		ActionListener crearPedido = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//insertar pedido
				Integer idCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese id del cliente que realiza el pedido: "));
				Calendar fecha = Calendar.getInstance();
				String pago = JOptionPane.showInputDialog(null, "Ingrese forma de pago del cliente: ");
				try {
					InsertPedido.insertarPedido(sF, mySession, idCliente, fecha, pago);
				} catch (Exception exception) {
					System.out.println(exception);
				}
				visor.append("\nID de cliente ingresado: " + idCliente);
				visor.append("\nMetodo de pago ingresado: " + pago);
			}
		};
		
		//cargar todos los action listener
		boton1.addActionListener(buscarCliente);
		boton2.addActionListener(buscarPedido);
		boton3.addActionListener(eliminarCliente);
		boton4.addActionListener(eliminarPedido);
		
		boton9.addActionListener(crearCliente);
		boton10.addActionListener(crearPedido);
		
	}

}
