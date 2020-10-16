package src;

import javax.swing.JOptionPane;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.service.spi.ServiceException;


public class UpdatePedido {
	
	public static Pedido updatePedido(SessionFactory myFactory, Session mySession, Integer idPedido) {
		Pedido miPedido = mySession.get(Pedido.class, idPedido);

		//creamos la transaccion sql
		mySession.beginTransaction();
		
		if(miPedido != null) {
			JOptionPane.showMessageDialog(null, miPedido.toString() + "\nCliente: " + miPedido.getCliente().getNombre() + " " + miPedido.getCliente().getApellido());
					
			int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese 1 para actualizar la forma de pago \nIngrese 2 para cambiar el ID del cliente:"));
			
			switch (opcion) {
			case 1:
				String formaPago = JOptionPane.showInputDialog(null, "Ingrese forma de pago: ");
				miPedido.setFormaPago(formaPago);
				JOptionPane.showMessageDialog(null, "Pedido actualizado correctamente.");
				break;

			case 2:
				Integer idCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese id del cliente: "));
				Cliente nuevoCliente = mySession.get(Cliente.class, idCliente);
				miPedido.setCliente(nuevoCliente);
				JOptionPane.showMessageDialog(null, "Pedido actualizado correctamente.");
				break;
			default:
				
				break;
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "No se encontro un pedido con ese ID.");
		}
		
		mySession.getTransaction().commit();
		
		return miPedido;
	}

}
