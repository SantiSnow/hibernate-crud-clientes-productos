package src;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.*;
public class ConsultasGenerales {

	public static List<Cliente> listaDeClientes(SessionFactory myFactory, Session mySession){
	
		mySession.beginTransaction();
		
		List<Cliente> listaClientes;
		
		listaClientes = mySession.createQuery("from Cliente").getResultList();
		
		//commit
		mySession.getTransaction().commit();
		
		return listaClientes;
	}
	
	public static List<Pedido> listaDePedidos(SessionFactory myFactory, Session mySession){
		
		mySession.beginTransaction();
		
		List<Pedido> listaPedidos;
		
		listaPedidos = mySession.createQuery("from Pedido").getResultList();
		
		//commit
		mySession.getTransaction().commit();
		
		return listaPedidos;
	}
	
	public static List<Producto> listaProductos(SessionFactory myFactory, Session mySession){
		mySession.beginTransaction();
		
		List<Producto> listaProductos;
		
		listaProductos = mySession.createQuery("from Producto").getResultList();
		
		//commit
		mySession.getTransaction().commit();
		
		return listaProductos;
	}
	
	public static List<PedidoHuerfano> listaPedidosHuerfanos(Session mySession){
		
		mySession.beginTransaction();
		List<PedidoHuerfano> listaPedidosHuerfanos = mySession.createQuery("from PedidoHuerfano").getResultList();
		mySession.getTransaction().commit();
		return listaPedidosHuerfanos;
		
	}
	
}
