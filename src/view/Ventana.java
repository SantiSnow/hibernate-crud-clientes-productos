package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import src.Cliente;
import src.ConsultarPedidos;
import src.Consultas;
import src.ConsultasGenerales;
import src.DeletePedido;
import src.DeleteRegistro;
import src.Insert;
import src.InsertPedido;
import src.Pedido;
import src.Update;
import src.UpdatePedido;

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
		titulo.setText("Aplicacion de práctica");
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
		JButton boton11 = new JButton("Limpiar");
		
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
		boton11.setBounds(35, 650, 200, 30);
		
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
		miPanel.add(boton11);
		
		JTextArea visor = new JTextArea(); 
		visor.setBounds(300, 150, 800, 550);
		visor.setBackground(Color.WHITE);
		visor.setText("Aquí se colocarán los resultados de su consulta");
		
		JScrollPane scroll = new JScrollPane(visor);
		scroll.setBounds(300, 150, 800, 550);
		scroll.setBackground(Color.WHITE);
		
		miPanel.add(scroll);
		
		
		
		//buscar cliente
		ActionListener buscarCliente = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer idCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del cliente a buscar"));
				
				Cliente clienteEncontrado = Consultas.buscarCliente(sF, mySession, idCliente);
				
				visor.append("\nCliente hallado: ");
				visor.append("\nID del cliente ingresado: " + idCliente);
				visor.append("\nNombre  del cliente ingresado: " + clienteEncontrado.getNombre());
				visor.append("\nApellido  del cliente ingresado: " + clienteEncontrado.getApellido());
				visor.append("\nDirección del cliente ingresado: " + clienteEncontrado.getDireccion());
				visor.append("\nCompras del cliente ingresado: " + clienteEncontrado.getCompras());
				visor.append("\nTelefono  del cliente ingresado: " + clienteEncontrado.getTelefono());
				visor.append("\nCorreo  del cliente ingresado: " + clienteEncontrado.getDetallesCliente().getCorreo());
				visor.append("\nComentarios  del cliente ingresado: " + clienteEncontrado.getDetallesCliente().getComentarios());
				visor.append("\n ");
				visor.append("\n ");
				
			}
		};
		
		//buscar pedidos
		ActionListener buscarPedido = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer idCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del cliente para buscar sus pedidos"));
				
				visor.append("\nID del cliente ingresado:" + idCliente);
				
				List <Pedido> listaPedidos = ConsultarPedidos.consultaPedido(sF, mySession, idCliente);
				
				for (Pedido i: listaPedidos){
					visor.append("\nPedido:");
					visor.append("\nID del pedido: " + i.getId());
					visor.append("\nForma de pago: " + i.getFormaPago());
					visor.append("\nTelefono del cliente: " + i.getCliente().getTelefono());
					visor.append("\nNombre del cliente que pidio: " + i.getCliente().getNombre());
					visor.append("\n ");
					visor.append("\n ");
				}
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
		
		//actualizar clientes
		ActionListener actualizarCliente = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer clienteIdUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del cliente a actualizar"));
				
				Cliente cActualizado = Update.UpdateCliente(clienteIdUpdate, sF, mySession);
				
				visor.append("\nCliente actualizado: ");
				visor.append("\nID del cliente actualizado: " + cActualizado);
				visor.append("\nNombre  del cliente actualizado: " + cActualizado.getNombre());
				visor.append("\nApellido  del cliente actualizado: " + cActualizado.getApellido());
				visor.append("\nDirección del cliente actualizado: " + cActualizado.getDireccion());
				visor.append("\nCompras del cliente actualizado: " + cActualizado.getCompras());
				visor.append("\nTelefono  del cliente actualizado: " + cActualizado.getTelefono());
				visor.append("\nCorreo  del cliente actualizado: " + cActualizado.getDetallesCliente().getCorreo());
				visor.append("\nComentarios  del cliente actualizado: " + cActualizado.getDetallesCliente().getComentarios());
				visor.append("\n ");
				visor.append("\n ");
			}
		};
		
		//actualizar pedidos
		ActionListener actualizarPedidos = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer idPedido = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del pedido a actualizar"));
				Pedido pActualizado = UpdatePedido.updatePedido(sF, mySession, idPedido);
				
				visor.append("\nPedido actualizado:");
				visor.append("\nID del pedido: " + pActualizado.getId());
				visor.append("\nForma de pago: " + pActualizado.getFormaPago());
				visor.append("\nTelefono del cliente: " + pActualizado.getCliente().getTelefono());
				visor.append("\nNombre del cliente que pidio: " + pActualizado.getCliente().getNombre());
				visor.append("\n ");
				visor.append("\n ");
				
			}
		};
		
		//ver todos los clientes
		ActionListener verClientes = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				List <Cliente> listaClientes = ConsultasGenerales.listaDeClientes(sF, mySession);
				
				for (Cliente i: listaClientes){
					visor.append("\nCliente:");
					visor.append("\nNombre: " + i.getNombre() + " " + i.getApellido());
					visor.append("\nDirección: " + i.getDireccion());
					visor.append("\nCompras: " + i.getCompras());
					visor.append("\nID del cliente: " + i.getId());
					visor.append("\nTelefono: " + i.getTelefono());
					visor.append("\nCorreo: " + i.getDetallesCliente().getCorreo());
					visor.append("\nComentarios: " + i.getDetallesCliente().getComentarios());
					
					visor.append("\n ");
					visor.append("\n ");
				}
			}
		};
		
		//ver todos los pedidos
		ActionListener verPedidos = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				List <Pedido> listaPedidos = ConsultasGenerales.listaDePedidos(sF, mySession);
				
				for (Pedido i: listaPedidos){
					visor.append("\nPedido:");
					visor.append("\nID del pedido: " + i.getId());
					visor.append("\nForma de pago: " + i.getFormaPago());
					visor.append("\nTelefono del cliente: " + i.getCliente().getTelefono());
					visor.append("\nNombre del cliente que pidio: " + i.getCliente().getNombre());
					visor.append("\n ");
					visor.append("\n ");
				}
				
			}
		};
		
		
		//crear cliente
		ActionListener crearCliente = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//insertar cliente
				String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre del cliente: ");
				String apellido = JOptionPane.showInputDialog(null, "Ingrese apellido del cliente: ");
				String direccion = JOptionPane.showInputDialog(null, "Ingrese dirección del cliente: ");
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
				visor.append("\nDirección ingresado: "+ direccion);
				visor.append("\nTelefono ingresado: "+ telefono);
				visor.append("\nCompras ingresadas: "+ compras);
				visor.append("\nCorreo ingresado: "+ correo);
				visor.append("\nComentarios ingresados: "+ comentarios);
				visor.append("\n "+ comentarios);
				visor.append("\n ");
				visor.append("\n ");
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
		
		ActionListener limpiar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				visor.setText(" ");
			}
			
		};
		
		//cargar todos los action listener
		boton1.addActionListener(buscarCliente);
		boton2.addActionListener(buscarPedido);
		boton3.addActionListener(eliminarCliente);
		boton4.addActionListener(eliminarPedido);
		
		boton5.addActionListener(actualizarCliente);
		boton6.addActionListener(actualizarPedidos);
		
		boton7.addActionListener(verClientes);
		boton8.addActionListener(verPedidos);
		boton9.addActionListener(crearCliente);
		boton10.addActionListener(crearPedido);
		boton11.addActionListener(limpiar);
		
	}

}
