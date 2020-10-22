package src;

import java.util.*;

import org.hibernate.*;

import model.Cliente;
import model.Pedido;


public class ConsultarPedidos {
	
	public static List<Pedido> consultaPedido(SessionFactory myFactory, Session mySession, Integer idCliente) {
		
		//creamos la transaccion sql
		mySession.beginTransaction();
		
		Cliente miCliente = mySession.get(Cliente.class, idCliente);
		List<Pedido> pedidos = miCliente.getPedidos();
		mySession.getTransaction().commit();

		return pedidos;
	}
}
