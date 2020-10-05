package src;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.service.spi.ServiceException;

public class Delete {

	public static void main(String[] args) {
		//creamos un session factory
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();
				
		//creamos un session
		Session mySession = myFactory.openSession();
				
		try {
			
			//creamos la transaccion sql
			mySession.beginTransaction();
			
			//criterio con like
			mySession.createQuery("delete Cliente where Direccion LIKE 'Sequeira%'").executeUpdate();
			
			mySession.getTransaction().commit();
					
			System.out.println("Registros eliminados con exito");
			
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
			
			System.out.println("Fin del programa");
		}

	}

}
