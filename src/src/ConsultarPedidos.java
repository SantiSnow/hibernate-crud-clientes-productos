package src;

import java.util.*;
import javax.swing.JOptionPane;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.service.spi.ServiceException;

import view.Demo2;


public class ConsultarPedidos {
	
	public static void consultaPedido(SessionFactory myFactory, Session mySession, Integer idCliente) {
		
		//creamos la transaccion sql
		mySession.beginTransaction();
		
		Cliente miCliente = mySession.get(Cliente.class, idCliente);
		List<Pedido> pedidos = miCliente.getPedidos();
		
		Demo2 demo1 = new Demo2(pedidos);

		mySession.getTransaction().commit();
		
	}
}
