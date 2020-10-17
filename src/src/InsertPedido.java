package src;

import java.util.Calendar;

import javax.swing.JOptionPane;

import org.hibernate.*;

import model.Cliente;
import model.Pedido;


public class InsertPedido {
	
	public static void insertarPedido(SessionFactory myFactory, Session mySession, Integer idCliente, Calendar fecha, String pago) throws Exception {
		//creamos objetos orm
		Cliente miCliente = mySession.get(Cliente.class, idCliente);
		Pedido miPedido = new Pedido(fecha.getTime(), pago, miCliente);
		
		miCliente.agregarPedido(miPedido);

		//creamos la transaccion sql
		mySession.beginTransaction();
		
		//crea la instruccion sql por nosotros
		mySession.save(miPedido);
		
		mySession.getTransaction().commit();
		
		Pedido pedidoInsertado = mySession.get(Pedido.class, miPedido.getId());
		
		JOptionPane.showMessageDialog(null, "El registro insertado fue: \n" + pedidoInsertado.toString() + "\nCliente: " + miCliente.getNombre() + " " + miCliente.getApellido());
	}

}
