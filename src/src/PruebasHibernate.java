package src;

import javax.swing.JOptionPane;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.service.spi.ServiceException;


public class PruebasHibernate {
	
	public static void main(String[] args) {
		//creamos un session factory
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();
		
		Session mySession = myFactory.openSession();
		
		try {
			
			//creamos un objeto orm
			Cliente miCliente = new Cliente("Santiago", "Aguirre", "Sequeira 3400", 11321344, 2);

			//creamos la transaccion sql
			mySession.beginTransaction();
			
			//crea la instruccion sql por nosotros
			mySession.save(miCliente);
			
			mySession.getTransaction().commit();
			
			System.out.println("Registro insertado con exito");
			
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
