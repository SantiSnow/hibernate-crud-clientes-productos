package src;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DeleteRegistro {
	
	public static void deleteCliente(SessionFactory myFactory, Session mySession, Integer idCliente) throws Exception{
		
		mySession.beginTransaction();
		
		Cliente miCliente = mySession.get(Cliente.class, idCliente);
		DetallesCliente detalles = mySession.get(DetallesCliente.class, idCliente);
		
		if(miCliente != null && detalles != null) {
			JOptionPane.showMessageDialog(null, "Id del cliente: " + miCliente.getId() + "\nNombre: " + miCliente.getNombre() + " " + miCliente.getApellido() + "\nTelefono: " + miCliente.getTelefono() + "\nDireccion: " + miCliente.getDireccion() + "\nCompras: " + miCliente.getCompras());
			JOptionPane.showMessageDialog(null, "Detalles del cliente: " + detalles.getCorreo() + "\nComentarios: " + detalles.getComentarios());
			
			Integer opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Desea realmente borrar estos registros?\nIngrese 1 para eliminar y continuar, o 2 para no borrarlo."));
			
			if(opcion == 1) {
				mySession.delete(miCliente);
				JOptionPane.showMessageDialog(null, "Cliente y datos borrados.");
			}
			else {
				JOptionPane.showMessageDialog(null, "El cliente no fue borrado.");
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "No se encontro un cliente con ese ID.");
		}
		
		mySession.getTransaction().commit();
	}

}
