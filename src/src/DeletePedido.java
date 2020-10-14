package src;

import javax.swing.JOptionPane;

import org.hibernate.*;


public class DeletePedido {
	
	public static void deletePedidos(SessionFactory myFactory, Session mySession, Integer idPedido) throws Exception {
		Pedido miPedido = mySession.get(Pedido.class, idPedido);

		//creamos la transaccion sql
		mySession.beginTransaction();
		
		if(miPedido != null) {
			JOptionPane.showMessageDialog(null, miPedido.toString() + "\nCliente: " + miPedido.getCliente().getNombre() + " " + miPedido.getCliente().getApellido());
					
			Integer opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "�Desea realmente borrar estos registros?\nIngrese 1 para eliminar y continuar, o 2 para no borrarlo."));
			if(opcion == 1) {
				mySession.delete(miPedido);
				JOptionPane.showMessageDialog(null, "Pedido borrado.");
			}
			else {
				JOptionPane.showMessageDialog(null, "El pedido no fue borrado.");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "No se encontro un pedido con ese ID.");
		}
		
		mySession.getTransaction().commit();
		
	}

}
