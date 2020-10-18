package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.*;
import src.*;

import javax.swing.*;

public class Ventana extends JFrame{
	
	JPanel miPanel;
	
	JButton boton1;
	JButton boton2;
	JButton boton3;
	JButton boton4;
	JButton boton5;
	JButton boton6;
	JButton boton7;
	JButton boton8;
	JButton boton9;
	JButton boton10;
	JButton boton11;
	JButton boton12;
	JButton boton13;
	
	JTextArea visor;
	JScrollPane scroll;
	
	public Ventana(SessionFactory sF, Session mySession){
		//x, y
		setSize(1200, 900);
		setTitle("Gestor de bases de datos MySQL");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		iniciarComponentes();
		agregarComponentes(sF, mySession);
		
		setVisible(true);
		
	}
	
	private void iniciarComponentes(){
		//agregamos un panel
		miPanel = new JPanel();
		miPanel.setBackground(Color.white);
		miPanel.setLayout(null);
		add(miPanel);
		
		//agregamos titulo
		JLabel titulo = new JLabel();
		titulo.setText("Gestor de Clientes y Pedidos");
		titulo.setBounds(15, 15, 450, 30);
		titulo.setFont(new Font("arial", Font.BOLD, 30));
		miPanel.add(titulo);
		
		JLabel instruccion = new JLabel();
		instruccion.setText("Aqu� aparecer�n los resultados de sus consultas:");
		instruccion.setBounds(300, 100, 450, 30);
		instruccion.setFont(new Font("arial", Font.PLAIN, 15));
		miPanel.add(instruccion);

	}
	
	
	
	private void agregarComponentes(SessionFactory sF, Session mySession){
		//boton1
		boton1 = new JButton("Buscar clientes por ID");
		boton2 = new JButton("Buscar pedidos por ID");
		boton13 = new JButton("Buscar producto por ID");
		boton3 = new JButton("Eliminar clientes");
		boton4 = new JButton("Eliminar pedidos");
		boton5 = new JButton("Actualizar un cliente");
		boton6 = new JButton("Actualizar pedidos del cliente");
		boton7 = new JButton("Ver todos los clientes");
		boton8 = new JButton("Ver todos los pedidos");
		boton9 = new JButton("Crear cliente");
		boton10 = new JButton("Crear pedido");
		boton12 = new JButton("Crear producto");
		boton11 = new JButton("Limpiar");
		
		boton1.setBounds(35, 150, 200, 30);
		boton2.setBounds(35, 200, 200, 30);
		boton13.setBounds(35, 250, 200, 30);
		boton3.setBounds(35, 300, 200, 30);
		boton4.setBounds(35, 350, 200, 30);
		boton5.setBounds(35, 400, 200, 30);
		boton6.setBounds(35, 450, 200, 30);
		boton7.setBounds(35, 500, 200, 30);
		boton8.setBounds(35, 550, 200, 30);
		boton9.setBounds(35, 600, 200, 30);
		boton10.setBounds(35, 650, 200, 30);
		boton12.setBounds(35, 700, 200, 30);
		boton11.setBounds(35, 750, 200, 30);
		
		boton1.setBackground(new Color(0, 153, 204));
		boton2.setBackground(new Color(0, 153, 204));
		boton13.setBackground(new Color(0, 153, 204));
		boton3.setBackground(new Color(255, 0, 0));
		boton4.setBackground(new Color(255, 0, 0));
		boton5.setBackground(new Color(0, 153, 0));
		boton6.setBackground(new Color(0, 153, 0));
		boton7.setBackground(new Color(0, 153, 204));
		boton8.setBackground(new Color(0, 153, 204));
		boton9.setBackground(new Color(0, 153, 204));
		boton10.setBackground(new Color(0, 153, 204));
		boton11.setBackground(new Color(255, 0, 0));
		boton12.setBackground(new Color(0, 153, 204));
		
		
		boton1.setForeground(Color.WHITE);
		boton2.setForeground(Color.WHITE);
		boton3.setForeground(Color.WHITE);
		boton4.setForeground(Color.WHITE);
		boton5.setForeground(Color.WHITE);
		boton6.setForeground(Color.WHITE);
		boton7.setForeground(Color.WHITE);
		boton8.setForeground(Color.WHITE);
		boton9.setForeground(Color.WHITE);
		boton10.setForeground(Color.WHITE);
		boton11.setForeground(Color.WHITE);
		boton12.setForeground(Color.WHITE);
		boton13.setForeground(Color.WHITE);
		
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
		miPanel.add(boton12);
		miPanel.add(boton13);
		
		visor = new JTextArea(); 
		visor.setBounds(300, 150, 800, 550);
		visor.setBackground(Color.WHITE);
		
		scroll = new JScrollPane(visor);
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
				visor.append("\nDirecci�n del cliente ingresado: " + clienteEncontrado.getDireccion());
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
		
		//buscar productos
		ActionListener buscarProducto = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer idProd = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del producto a buscar"));
				Producto productoEncontrado = ConsultarProductos.consultaProducto(sF, mySession, idProd);
				
				visor.append("\nID del producto: " + productoEncontrado.getId());
				visor.append("\nNombre del producto: " + productoEncontrado.getNombre());
				visor.append("\nPrecio del producto: " + productoEncontrado.getPrecio());
				visor.append("\nStock del producto: " + productoEncontrado.getStock());
				visor.append("\nDetalle del producto: " + productoEncontrado.getDetalle());
				visor.append("\nSeccion del producto: " + productoEncontrado.getSeccion());
				visor.append("\n");
				visor.append("\n");
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
				visor.append("\nDirecci�n del cliente actualizado: " + cActualizado.getDireccion());
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
					visor.append("\nDirecci�n: " + i.getDireccion());
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
				
				String [] metodosPago = {"Tarjeta", "Efectivo", "Cheque", "Pagare", "Transferencia"};
				
				String metodoElegido = (String) JOptionPane.showInputDialog(null,"Seleccione forma de pago del cliente: ", "Elegir",JOptionPane.QUESTION_MESSAGE,null, metodosPago, metodosPago[1]);
				
				try {
					InsertPedido.insertarPedido(sF, mySession, idCliente, fecha, metodoElegido);
				} catch (Exception exception) {
					System.out.println(exception);
				}
				visor.append("\nID de cliente ingresado: " + idCliente);
				visor.append("\nMetodo de pago ingresado: " + metodoElegido);
			}
		};
		
		//crear producto
		ActionListener crearProducto = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//insertar cliente
				String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre del producto: ");
				Integer stock = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese stock del producto: "));
				Integer precio = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese precio del producto: "));
				String detalle = JOptionPane.showInputDialog(null, "Ingrese detalle del producto: ");
				
				String [] seccionesDisp = {"Informatica", "Libreria", "Textiles", "Deportes", "Muebles"};
				String seccion = (String) JOptionPane.showInputDialog(null,"Elija la secci�n: ", "Elegir",JOptionPane.QUESTION_MESSAGE,null, seccionesDisp, seccionesDisp[1]);
				
				try {
					Producto miProducto = InsertProducto.insertarPrducto(sF, mySession, nombre, precio, stock, detalle, seccion);
					if(miProducto != null) {
						visor.append(miProducto.getNombre() + " Insertado correctamente.");
					}
				} catch (Exception exception) {
					System.out.println(exception);
				}
				visor.append("\nNombre ingresado: " + nombre);
				visor.append("\nStock ingresado: " + stock);
				visor.append("\nPrecio ingresado: "+ precio);
				visor.append("\nDetalle ingresado: "+ detalle);
				visor.append("\nSecci�n ingresadas: "+ seccion);
				visor.append("\n ");
				visor.append("\n ");
			}
		};
		
		//limpia el visor
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
		boton12.addActionListener(crearProducto);
		boton13.addActionListener(buscarProducto);
		
	}

}
