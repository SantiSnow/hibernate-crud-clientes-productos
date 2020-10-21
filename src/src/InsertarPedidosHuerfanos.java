package src;

import java.util.*;

import org.hibernate.Session;

import model.*;

public class InsertarPedidosHuerfanos {
	
	public static void insertarPedidosSinCliente(List<Pedido> listaObtenida, Session mySession) {
		//creamos la transaccion sql
		mySession.beginTransaction();
		//recorrer la lista
		for(Pedido i: listaObtenida) {
			PedidoHuerfano nuevoPedido = new PedidoHuerfano(i.getFecha(), i.getFormaPago());
			mySession.save(nuevoPedido);
		}
		mySession.getTransaction().commit();
	}
}
