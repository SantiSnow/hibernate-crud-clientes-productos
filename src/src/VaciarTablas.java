package src;

import java.util.List;

import org.hibernate.Session;

import model.*;

public class VaciarTablas {

	public static void vaciarTablaClientes(Session mySession) {
		mySession.beginTransaction();
		
		List<Cliente> listaCliente;
		
		listaCliente = mySession.createQuery("from Cliente").getResultList();
		
		for(Cliente i: listaCliente) {
			mySession.delete(i);
		}
		
		//commit
		mySession.getTransaction().commit();
	}
	
	public static void vaciarTablaProductos(Session mySession) {
		mySession.beginTransaction();
		
		List<Producto> listaProductos;
		
		listaProductos = mySession.createQuery("from Producto").getResultList();
		
		for(Producto i: listaProductos) {
			mySession.delete(i);
		}
		
		//commit
		mySession.getTransaction().commit();
		
	}
	
	public static void vaciarTablaPedidos(Session mySession) {
		mySession.beginTransaction();
		
		List<Pedido> listaPedido;
		
		listaPedido = mySession.createQuery("from Pedido").getResultList();
		
		for(Pedido i: listaPedido) {
			mySession.delete(i);
		}
		
		//commit
		mySession.getTransaction().commit();
	}
}
