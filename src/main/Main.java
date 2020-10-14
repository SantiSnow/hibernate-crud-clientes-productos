package main;

import java.util.Calendar;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.service.spi.ServiceException;

import src.Cliente;
import src.DeletePedido;
import src.DeleteRegistro;
import src.DetallesCliente;
import src.Insert;
import src.InsertPedido;
import src.Pedido;
import src.Update;
import src.UpdatePedido;

public class Main {

	public static void main(String[] args) {
		//creamos un session factory
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Cliente.class)
			.addAnnotatedClass(DetallesCliente.class)
			.addAnnotatedClass(Pedido.class)
			.buildSessionFactory();
				
		Session mySession = myFactory.openSession();
		
		try {
			
			//insertar cliente
			String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre del cliente: ");
			String apellido = JOptionPane.showInputDialog(null, "Ingrese apellido del cliente: ");
			String direccion = JOptionPane.showInputDialog(null, "Ingrese dirección del cliente: ");
			Integer telefono = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese telefono del cliente: "));
			Integer compras = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese cantidad de compras del cliente: "));
			String correo = JOptionPane.showInputDialog(null, "Ingrese correo del cliente: ");
			String comentarios = JOptionPane.showInputDialog(null, "Ingrese comentarios del cliente: ");
			
			Insert.insertarCliente(nombre, apellido, direccion, telefono, compras, correo, comentarios, myFactory, mySession);
			
			//insertar pedido
			Integer idCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese id del cliente que realiza el pedido: "));
			Calendar fecha = Calendar.getInstance();
			String pago = JOptionPane.showInputDialog(null, "Ingrese forma de pago del cliente: ");
			
			InsertPedido.insertarPedido(myFactory, mySession, idCliente, fecha, pago);
			
			//eliminar cliente
			Integer idClienteEliminar = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del cliente a eliminar: "));
			DeleteRegistro.deletePedido(myFactory, mySession, idClienteEliminar);
			
			//eliminar pedido
			Integer idPedidoEliminar = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese id del pedido a eliminar: "));
			DeletePedido.deletePedidos(myFactory, mySession, idPedidoEliminar);
			
			//update cliente
			Integer clienteIdUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del cliente a actualizar"));
			Update.UpdateCliente(clienteIdUpdate, myFactory, mySession);
			
			//update pedido
			Integer idPedidoUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese id del pedido a cambiar: "));
			UpdatePedido.updatePedido(myFactory, mySession, idPedidoUpdate);
			
		}
		catch(ServiceException e) {
			
			JOptionPane.showMessageDialog(null, "Error del tipo Service Exception, la base de datos no se puede conectar, verifique el puerto");
		}
		catch(IdentifierGenerationException e) {
			JOptionPane.showMessageDialog(null, "Error, la base de datos requiere un tipo de dato ID. Verifique si la tabla es correcta.");
		}
		catch(Exception e) {
			System.out.println("Error del tipo: ");
			e.printStackTrace();
		}
		finally {
			mySession.close();
			
			myFactory.close();
			
			System.out.println("Fin del programa");
		}
	}

}
