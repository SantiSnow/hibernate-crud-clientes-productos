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
			Cliente miCliente = new Cliente("Pablo", "Aguirre", "Brandsend 3400", 11321344, 2);

			//creamos la transaccion sql
			mySession.beginTransaction();
			
			//crea la instruccion sql por nosotros
			mySession.save(miCliente);
			
			mySession.getTransaction().commit();
			
			Cliente clienteInsertado = mySession.get(Cliente.class, miCliente.getId());
			
			System.out.println("El registro insertado fue= " + clienteInsertado.toString());
			
			
			System.out.println("Registro insertado con exito");
			
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