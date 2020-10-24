package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
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
	JButton boton14;
	JButton boton15;
	JButton boton16;
	JButton boton17;
	JButton boton18;
	JButton boton19;
	JButton boton20;
	JButton boton21;
	JButton boton22;
	JButton boton23;
	
	JTextArea visor;
	JScrollPane scroll;
	
	public Ventana(SessionFactory sF, Session mySession){
		//x, y
		setSize(980, 710);
		setTitle("Gestor de bases de datos MySQL");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		iniciarComponentes();
		agregarBotones();
		agregarActions(sF, mySession);
		agregarVisor();
		
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
		titulo.setText("Gestor de Clientes, Pedidos y Productos");
		titulo.setBounds(15, 15, 600, 30);
		titulo.setFont(new Font("arial", Font.BOLD, 30));
		miPanel.add(titulo);
		
		//subtitulos
		JLabel eliminar = new JLabel();
		eliminar.setText("Vacias tablas completas");
		eliminar.setBounds(30, 590, 200, 30);
		eliminar.setFont(new Font("arial", Font.BOLD, 15));
		miPanel.add(eliminar);
		
		//subtitulos
		JLabel respaldar = new JLabel();
		respaldar.setText("Respaldar tablas");
		respaldar.setBounds(730, 590, 200, 30);
		respaldar.setFont(new Font("arial", Font.BOLD, 15));
		miPanel.add(respaldar);
		
		JLabel instruccion = new JLabel();
		instruccion.setText("Aquí aparecerán los resultados de sus consultas:");
		instruccion.setBounds(280, 70, 450, 30);
		instruccion.setFont(new Font("arial", Font.PLAIN, 15));
		miPanel.add(instruccion);

	}
	
	//este metodo agrega botones y les da estilo en la ventana
	private void agregarBotones() {
		//botones
		boton1 = new JButton("Buscar clientes por ID");
		boton2 = new JButton("Buscar pedidos por Cliente");
		boton13 = new JButton("Buscar producto por ID");
		boton3 = new JButton("Eliminar clientes");
		boton4 = new JButton("Eliminar pedidos");
		boton14 = new JButton("Eliminar producto");
		boton5 = new JButton("Actualizar un cliente");
		boton6 = new JButton("Actualizar pedido");
		boton15 = new JButton("Actualizar producto");
		
		boton7 = new JButton("Ver todos los clientes");
		boton8 = new JButton("Ver todos los pedidos");
		boton16 = new JButton("Ver todos los productos");
		boton9 = new JButton("Crear cliente");
		boton10 = new JButton("Crear pedido");
		boton12 = new JButton("Crear producto");
		boton11 = new JButton("Limpiar visor");
		
		boton17 = new JButton("Buscar cliente por nombre");
		boton18 = new JButton("Buscar producto por nombre");
		boton19 = new JButton("Ver productos de la seccion");
				
		boton20 = new JButton("Realizar respaldo de pedidos");
		boton21 = new JButton("Ver tabla historica de pedidos");
		
		boton22 = new JButton("Eliminar tablas completas");
		boton23 = new JButton("Respaldar tablas");
		
		//botones a la izq
		boton3.setBounds(30, 100, 150, 30);
		boton4.setBounds(30, 150, 150, 30);
		boton14.setBounds(30, 200, 150, 30);
		boton5.setBounds(30, 250, 150, 30);
		boton6.setBounds(30, 300, 150, 30);
		boton15.setBounds(30, 350, 150, 30);
		boton9.setBounds(30, 400, 150, 30);
		boton10.setBounds(30, 450, 150, 30);
		boton12.setBounds(30, 500, 150, 30);
		
		//botones a la derecha
		boton7.setBounds(730, 100, 200, 30);
		boton8.setBounds(730, 150, 200, 30);
		boton16.setBounds(730, 200, 200, 30);
		
		boton17.setBounds(730, 250, 200, 30);
		boton18.setBounds(730, 300, 200, 30);
		boton19.setBounds(730, 350, 200, 30);
		
		boton13.setBounds(730, 400, 200, 30);
		boton1.setBounds(730, 450, 200, 30);
		boton2.setBounds(730, 500, 200, 30);
				
		//bajo el visor
		boton11.setBounds(30, 550, 150, 30);
		boton20.setBounds(200, 550, 220, 30);
		boton21.setBounds(440, 550, 220, 30);
		
		//botones de eliminacion completa
		boton22.setBounds(30, 620, 200, 30);
		
		//boton respaldo
		boton23.setBounds(730, 620, 200, 30);
		
		//estilos de los botones
		boton1.setBackground(new Color(0, 153, 204));
		boton2.setBackground(new Color(0, 153, 204));
		boton13.setBackground(new Color(0, 153, 204));
		boton3.setBackground(new Color(255, 0, 0));
		boton4.setBackground(new Color(255, 0, 0));
		boton14.setBackground(new Color(255, 0, 0));
		boton5.setBackground(new Color(0, 153, 0));
		boton6.setBackground(new Color(0, 153, 0));
		boton15.setBackground(new Color(0, 153, 0));
		boton7.setBackground(new Color(0, 153, 204));
		boton8.setBackground(new Color(0, 153, 204));
		boton16.setBackground(new Color(0, 153, 204));
		boton9.setBackground(new Color(0, 153, 204));
		boton10.setBackground(new Color(0, 153, 204));
		boton11.setBackground(new Color(255, 0, 0));
		boton12.setBackground(new Color(0, 153, 204));
		boton17.setBackground(new Color(0, 153, 0));
		boton18.setBackground(new Color(0, 153, 0));
		boton19.setBackground(new Color(0, 153, 0));
		boton20.setBackground(new Color(0, 153, 0));
		boton21.setBackground(new Color(0, 153, 0));
		
		boton22.setBackground(new Color(255, 0, 0));
		boton23.setBackground(new Color(0, 153, 0));
		
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
		boton14.setForeground(Color.WHITE);
		boton15.setForeground(Color.WHITE);
		boton16.setForeground(Color.WHITE);
		boton17.setForeground(Color.WHITE);
		boton18.setForeground(Color.WHITE);
		boton19.setForeground(Color.WHITE);
		boton20.setForeground(Color.WHITE);
		boton21.setForeground(Color.WHITE);
		boton22.setForeground(Color.WHITE);
		boton23.setForeground(Color.WHITE);
		
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
		miPanel.add(boton14);
		miPanel.add(boton15);
		miPanel.add(boton16);
		miPanel.add(boton17);
		miPanel.add(boton18);
		miPanel.add(boton19);
		miPanel.add(boton20);
		miPanel.add(boton21);
		miPanel.add(boton22);
		miPanel.add(boton23);
	}
	
	//agrega el visor
	public void agregarVisor() {
		visor = new JTextArea(); 
		visor.setBounds(200, 100, 500, 400);
		visor.setBackground(Color.WHITE);
		
		scroll = new JScrollPane(visor);
		scroll.setBounds(200, 100, 500, 400);
		scroll.setBackground(Color.WHITE);
		
		miPanel.add(scroll);
	}
	
	//este metodo se encarga de agregar actionlisteners a cada boton
	private void agregarActions(SessionFactory sF, Session mySession){
		
		//buscar cliente
		ActionListener buscarCliente = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer idCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del cliente a buscar"));
				
				Cliente clienteEncontrado = Consultas.buscarCliente(sF, mySession, idCliente);
				
				if(clienteEncontrado != null) {
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
				else {
					visor.append("No se encontró un cliente con el ID " + idCliente);
				}
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
				if(productoEncontrado != null) {
					visor.append("\nID del producto: " + productoEncontrado.getId());
					visor.append("\nNombre del producto: " + productoEncontrado.getNombre());
					visor.append("\nPrecio del producto: " + productoEncontrado.getPrecio());
					visor.append("\nStock del producto: " + productoEncontrado.getStock());
					visor.append("\nDetalle del producto: " + productoEncontrado.getDetalle());
					visor.append("\nSeccion del producto: " + productoEncontrado.getSeccion());
					visor.append("\n");
					visor.append("\n");
				}
			}
		};
		
		//Eliminar clientes
		ActionListener eliminarCliente = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer idCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del cliente a eliminar"));
				JOptionPane.showMessageDialog(null, "Eliminar un cliente, borra sus pedidos para evitar problemas en el gestor.");
				JOptionPane.showMessageDialog(null, "Antes de eliminar un cliente, realice un respaldo de los pedidos con el boton: Realizar respaldo de pedidos.");
				try {
					Cliente clienteEliminado = DeleteRegistro.deleteCliente(sF, mySession, idCliente);
					
					visor.append("\nID del cliente eliminado: " + idCliente);
					visor.append("\nnombre del cliente eliminado: " + clienteEliminado.getNombre() + " " + clienteEliminado.getApellido());
					visor.append("\nCorreo del cliente eliminado: " + clienteEliminado.getDetallesCliente().getCorreo());
					visor.append("\nTeléfono del cliente eliminado: " + clienteEliminado.getTelefono());
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
		
		//eliminar producto
		ActionListener eliminarProducto = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer idProducto = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del producto a eliminar"));
				try {
					Producto prodEliminado = DeleteProducto.deleteProducto(sF, mySession, idProducto);
					visor.append("\nProducto eliminado:" + prodEliminado.getNombre());
				} catch (Exception exception) {
					System.out.println(exception);
				}
			}
		};
		
		//actualizar clientes
		ActionListener actualizarCliente = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer clienteIdUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del cliente a actualizar"));
				
				Cliente cActualizado = Update.UpdateCliente(clienteIdUpdate, sF, mySession);
				visor.append("\n");
				visor.append("\n");
				visor.append("\nNuevos datos del cliente: ");
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
		
		//actualizarProducto
		ActionListener actualizarProducto = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer idPedido = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del producto a actualizar"));
				
				Producto productoActualizado = UpdateProducto.updateProductos(sF, mySession, idPedido);
				
				visor.append("\nID del producto: " + productoActualizado.getId());
				visor.append("\nNombre del producto: " + productoActualizado.getNombre());
				visor.append("\nPrecio del producto:  " + productoActualizado.getPrecio());
				visor.append("\nStock del producto:  " + productoActualizado.getStock());
				visor.append("\nSeccion del producto:  " + productoActualizado.getSeccion());
				visor.append("\nDetalle del producto:  "  + productoActualizado.getDetalle());
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
					visor.append("\nID del cliente: " + i.getId());
					visor.append("\nNombre: " + i.getNombre() + " " + i.getApellido());
					visor.append("\nDirección: " + i.getDireccion());
					visor.append("\nCompras: " + i.getCompras());
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
		
		//ver todos los productos
		ActionListener verProductos = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				List <Producto> listaProductos = ConsultasGenerales.listaProductos(sF, mySession);
				
				for (Producto i: listaProductos){
					visor.append("\nProducto:");
					visor.append("\nID del producto: " + i.getId());
					visor.append("\nNombre del producto: " + i.getNombre());
					visor.append("\nSeccion del producto: " + i.getSeccion());
					visor.append("\nPrecio del producto: " + i.getPrecio());
					visor.append("\nStock del producto: " + i.getStock());
					visor.append("\nDetalee del producto: " + i.getDetalle());
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
				String seccion = (String) JOptionPane.showInputDialog(null,"Elija la sección: ", "Elegir",JOptionPane.QUESTION_MESSAGE,null, seccionesDisp, seccionesDisp[1]);
				
				try {
					Producto miProducto = InsertProducto.insertarPrducto(sF, mySession, nombre, precio, stock, detalle, seccion);
					if(miProducto != null) {
						visor.append("\n ");
						visor.append("\n ");
						visor.append(miProducto.getNombre() + " Insertado correctamente.");
						visor.append("\nStock ingresado: " + stock);
						visor.append("\nPrecio ingresado: "+ precio);
						visor.append("\nDetalle ingresado: "+ detalle);
						visor.append("\nSección ingresadas: "+ seccion);
						visor.append("\n ");
						visor.append("\n ");
					}
				} catch (Exception exception) {
					System.out.println(exception);
				}
				
			}
		};
		
		//buscar cliente por nombre
		ActionListener clienteNombre = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombreCliente = JOptionPane.showInputDialog(null, "Ingrese nombre del cliente a buscar: ");
				ArrayList<Cliente> listaClientes = Consultas.buscarClienteNombre(sF, mySession, nombreCliente);
				
				if(listaClientes != null) {
					for(Cliente i: listaClientes) {
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
				else {
					JOptionPane.showMessageDialog(null, "No se encontraron clientes con ese nombre");
				}
			}
		};
		
		//buscar producto por nombre
		ActionListener productoNombre = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombreProducto = JOptionPane.showInputDialog(null, "Ingrese nombre del producto a buscar: ");
				List<Producto> listaProductos = Consultas.buscarProductoNombre(sF, mySession, nombreProducto);
				
				if(listaProductos != null) {
					for(Producto i: listaProductos) {
						visor.append("\nPedido:");
						visor.append("\nID del producto: " + i.getId());
						visor.append("\nNombre del producto: " + i.getNombre());
						visor.append("\nSeccion del producto: " + i.getSeccion());
						visor.append("\nPrecio del producto: " + i.getPrecio());
						visor.append("\nStock del producto: " + i.getStock());
						visor.append("\nDetalee del producto: " + i.getDetalle());
						visor.append("\n ");
						visor.append("\n ");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "No se encontraron productos con ese nombre");
				}
				
			}
		};
		
		//buscar producto por seccion
		ActionListener seccionProductos = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String [] seccionesDisp = {"Informatica", "Libreria", "Textiles", "Deportes", "Muebles"};
				String seccion = (String) JOptionPane.showInputDialog(null,"Elija la sección: ", "Elegir",JOptionPane.QUESTION_MESSAGE,null, seccionesDisp, seccionesDisp[1]);
				List<Producto> listaProductos = Consultas.buscarProductoSeccion(sF, mySession, seccion);
				
				if(listaProductos != null) {
					for(Producto i: listaProductos) {
						visor.append("\nPedido:");
						visor.append("\nID del producto: " + i.getId());
						visor.append("\nNombre del producto: " + i.getNombre());
						visor.append("\nSeccion del producto: " + i.getSeccion());
						visor.append("\nPrecio del producto: " + i.getPrecio());
						visor.append("\nStock del producto: " + i.getStock());
						visor.append("\nDetalee del producto: " + i.getDetalle());
						visor.append("\n ");
						visor.append("\n ");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "No se encontraron productos en esa sección.");
				}
				
			}
		};		
		
		//limpia el visor
		ActionListener limpiar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				visor.setText(" ");
			}
			
		};
		
		//respaldo de pedidos
		ActionListener respaldarPedidos = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Esto realiza un respaldo de los pedidos, en una tabla historica.");
				JOptionPane.showMessageDialog(null, "Esta tabla no guarda de que cliente es cada pedido. Usela solo si necesita borrar clientes");
				Integer eleccion = JOptionPane.showConfirmDialog(null, "¿Desea respaldar los registros de pedidos?", "Respaldar pedidos", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(eleccion == 0) {
					List<Pedido> listaPedidosClientes = ConsultasGenerales.listaDePedidos(sF, mySession);
					InsertarPedidosHuerfanos.insertarPedidosSinCliente(listaPedidosClientes, mySession);
					JOptionPane.showMessageDialog(null, "Respaldo en la tabla historica creado.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Respaldo cancelado.");
				}
			}
		};
		
		//ver respaldo de pedidos
		ActionListener verTablaHistoricaPedidos = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				List<PedidoHuerfano> listaPedidosHistoricos = ConsultasGenerales.listaPedidosHuerfanos(mySession);
				
				VentanaRegistrosHistoricos nuevaVentana = new VentanaRegistrosHistoricos(listaPedidosHistoricos);
				
			}
		};
		
		//abrir ventana de eliminacion de tablas
		ActionListener ventanaEliminaTablas = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaEliminaTablas ventanaEliminaTablas = new VentanaEliminaTablas(mySession);
				
			}
		};
		
		//respaldar bases de datos
		ActionListener respaldo = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaRespaldo ventanaNueva = new VentanaRespaldo(mySession);
				
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
		boton14.addActionListener(eliminarProducto);
		boton15.addActionListener(actualizarProducto);
		boton16.addActionListener(verProductos);
		
		boton17.addActionListener(clienteNombre);
		boton18.addActionListener(productoNombre);
		boton19.addActionListener(seccionProductos);
		
		boton20.addActionListener(respaldarPedidos);
		boton21.addActionListener(verTablaHistoricaPedidos);
		
		boton22.addActionListener(ventanaEliminaTablas);
		
		boton23.addActionListener(respaldo);
		
	}

}
