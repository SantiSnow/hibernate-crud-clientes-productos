package src;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.service.spi.ServiceException;

public class Consultas {
	
	public static Cliente buscarCliente(SessionFactory myFactory, Session mySession, Integer idCliente) {
		
		mySession.beginTransaction();
		
		Cliente miCliente = mySession.get(Cliente.class, idCliente);
		DetallesCliente detalles = mySession.get(DetallesCliente.class, idCliente);
		
		if(miCliente != null && detalles != null) {
			//JOptionPane.showMessageDialog(null, "Id del cliente: " + miCliente.getId() + "\nNombre: " + miCliente.getNombre() + " " + miCliente.getApellido() + "\nTelefono: " + miCliente.getTelefono() + "\nDireccion: " + miCliente.getDireccion() + "\nCompras: " + miCliente.getCompras());
			//JOptionPane.showMessageDialog(null, "Detalles del cliente: " + detalles.getCorreo() + "\nComentarios: " + detalles.getComentarios());
			
			mySession.getTransaction().commit();
			
			return miCliente;
			
		}else {
			JOptionPane.showMessageDialog(null, "No se encontro un cliente con ese ID.");
			
			mySession.getTransaction().commit();
			
			return null;
			
		}
		
		
		
	}

}
