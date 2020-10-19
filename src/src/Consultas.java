package src;

import java.util.*;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.*;

public class Consultas {
	
	public static Cliente buscarCliente(SessionFactory myFactory, Session mySession, Integer idCliente) {
		
		mySession.beginTransaction();
		
		Cliente miCliente = mySession.get(Cliente.class, idCliente);
		DetallesCliente detalles = mySession.get(DetallesCliente.class, idCliente);
		
		if(miCliente != null && detalles != null) {
			//JOptionPane.showMessageDialog(null, "Id del cliente: " + miCliente.getId() + "\nNombre: " + miCliente.getNombre() + " " + miCliente.getApellido() + "\nTelefono: " + miCliente.getTelefono() + "\nDireccion: " + miCliente.getDireccion() + "\nCompras: " + miCliente.getCompras());
			//JOptionPane.showMessageDialog(null, "Detalles del cliente: " + detalles.getCorreo() + "\nComentarios: " + detalles.getComentarios());
			
			mySession.getTransaction().commit();
			
			return miCliente;
			
		}else {
			JOptionPane.showMessageDialog(null, "No se encontro un cliente con ese ID.");
			
			mySession.getTransaction().commit();
			
			return null;
			
		}
		
	}
	
	public static ArrayList<Cliente> buscarClienteNombre(SessionFactory myFactory, Session mySession, String nombreCliente) {
		
		mySession.beginTransaction();
		
		ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) mySession.createQuery("from Cliente cl where cl.Nombre='" + nombreCliente + "'").getResultList();
		
		//commit
		mySession.getTransaction().commit();
		
		if(listaClientes.size() > 0) {
			return listaClientes;
		}
		else {
			return null;
		}
		
	}
	
	public static ArrayList<Producto> buscarProductoNombre(SessionFactory myFactory, Session mySession, String nombreProducto) {
		
		mySession.beginTransaction();
		
		ArrayList<Producto> listaProductos = (ArrayList<Producto>) mySession.createQuery("from Producto where Nombre='" + nombreProducto + "'").getResultList();
		
		//commit
		mySession.getTransaction().commit();
		
		if(listaProductos.size() > 0) {
			return listaProductos;
		}
		else {
			return null;
		}
		
	}
	
	public static ArrayList<Producto> buscarProductoSeccion(SessionFactory myFactory, Session mySession, String seccionProducto) {
		
		mySession.beginTransaction();
		
		ArrayList<Producto> listaProductos = (ArrayList<Producto>) mySession.createQuery("from Producto where Seccion='" + seccionProducto + "'").getResultList();
		
		//commit
		mySession.getTransaction().commit();
		
		if(listaProductos.size() > 0) {
			return listaProductos;
		}
		else {
			return null;
		}
		
	}

}
