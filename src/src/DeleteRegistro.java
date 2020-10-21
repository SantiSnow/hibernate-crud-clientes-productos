package src;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.*;


public class DeleteRegistro {
	
	public static Cliente deleteCliente(SessionFactory myFactory, Session mySession, Integer idCliente) throws Exception{
		
		mySession.beginTransaction();
		
		Cliente miCliente = mySession.get(Cliente.class, idCliente);
		DetallesCliente detalles = mySession.get(DetallesCliente.class, idCliente);
		
		if(miCliente != null && detalles != null) {
			JOptionPane.showMessageDialog(null, "Id del cliente: " + miCliente.getId() + "\nNombre: " + miCliente.getNombre() + " " + miCliente.getApellido() + "\nTelefono: " + miCliente.getTelefono() + "\nDireccion: " + miCliente.getDireccion() + "\nCompras: " + miCliente.getCompras());
			JOptionPane.showMessageDialog(null, "Detalles del cliente: " + detalles.getCorreo() + "\nComentarios: " + detalles.getComentarios());
			
			Integer eleccion = JOptionPane.showConfirmDialog(null, "¿Desea realmente borrar estos registros?", "Borrar cliente", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			
			if(eleccion == 0) {
				mySession.delete(miCliente);
				JOptionPane.showMessageDialog(null, "Cliente y datos borrados.");
				
				mySession.getTransaction().commit();
			}
			else {
				JOptionPane.showMessageDialog(null, "El cliente no fue borrado.");
				
				return null;
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "No se encontro un cliente con ese ID.");
			
			return null;
		}
		
		mySession.getTransaction().commit();
		
		return miCliente;
	}

}
