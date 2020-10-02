package src;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.service.spi.ServiceException;

public class ConsultasClientes {

	public static void main(String[] args) {
		
		List<Cliente> listaClientes;
		
		//creamos un session factory
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();
				
		//creamos un session
		Session mySession = myFactory.openSession();
		
		try{
			//comenzamos la transaccion
			mySession.beginTransaction();
			
			//consulta usando el nombre de la clase orm
			listaClientes = mySession.createQuery("from Cliente").getResultList();
			
			System.out.println("Lista completa de Clientes: ");
			
			Cliente.recorrerLista(listaClientes);
			
			//consulta con filtros, empleados con la edad 24
			listaClientes = mySession.createQuery("from Cliente cl where cl.Compras=2").getResultList();
			
			System.out.println("Lista de Clientes con 2 compras: ");
			
			//mostramos la lista filtrada
			Cliente.recorrerLista(listaClientes);
			
			//consulta con filtros, empleados con la edad 19 o seccion marketing
			listaClientes = mySession.createQuery("from Cliente cl where cl.Compras=5 or cl.Direccion='T Plaza 3400'").getResultList();
			
			System.out.println("Lista de Clientes con 5 compras o direccion en T Plaza: ");
			
			//mostramos la lista filtrada
			Cliente.recorrerLista(listaClientes);
			
			//commit
			mySession.getTransaction().commit();
			
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
