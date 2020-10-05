package src;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.service.spi.ServiceException;

public class Consultas {

	public static void main(String[] args) {
		
		//creamos un session factory
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Cliente.class)
				.addAnnotatedClass(DetallesCliente.class)
				.buildSessionFactory();
				
		//creamos un session
		Session mySession = myFactory.openSession();
		
		try{
			
			mySession.beginTransaction();
			
			Integer idCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del cliente: "));
			
			Cliente miCliente = mySession.get(Cliente.class, idCliente);
			DetallesCliente detalles = mySession.get(DetallesCliente.class, idCliente);
			
			if(miCliente != null && detalles != null) {
				JOptionPane.showMessageDialog(null, "Id del cliente: " + miCliente.getId() + "\nNombre: " + miCliente.getNombre() + " " + miCliente.getApellido() + "\nTelefono: " + miCliente.getTelefono() + "\nDireccion: " + miCliente.getDireccion() + "\nCompras: " + miCliente.getCompras());
				JOptionPane.showMessageDialog(null, "Detalles del cliente: " + detalles.getCorreo() + "\nComentarios: " + detalles.getComentarios());
			}else {
				JOptionPane.showMessageDialog(null, "No se encontro un cliente con ese ID.");
			}
			
			System.out.println(miCliente);
			System.out.println(detalles);
			
			mySession.getTransaction().commit();
			
		}
		catch(ServiceException e) {
			
			JOptionPane.showMessageDialog(null, "Error del tipo Service Exception, la base de datos no se puede conectar, verifique el puerto");
		}
		catch(IdentifierGenerationException e) {
			JOptionPane.showMessageDialog(null, "Error, la base de datos requiere un tipo de dato ID. Verifique si la tabla es correcta.");
		}
		catch(Exception e) {
			System.out.println("Error del tipo: ");
			e.printStackTrace();
		}
		finally {
			mySession.close();
			
			myFactory.close();
		}

	}

}
