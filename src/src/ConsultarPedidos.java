package src;

import java.util.*;
import javax.swing.JOptionPane;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.service.spi.ServiceException;

import view.Demo2;


public class ConsultarPedidos {
	
	public static void main(String[] args) {
		//creamos un session factory
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Cliente.class)
				.addAnnotatedClass(DetallesCliente.class)
				.addAnnotatedClass(Pedido.class)
				.buildSessionFactory();
		
		Session mySession = myFactory.openSession();
		
		try {
			
			Integer idCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese id del cliente cuyos pedidos busca: "));
			//creamos la transaccion sql
			mySession.beginTransaction();
			
			Cliente miCliente = mySession.get(Cliente.class, idCliente);
			List<Pedido> pedidos = miCliente.getPedidos();
			
			Demo2 demo1 = new Demo2(pedidos);

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
			
			System.out.println("Fin del programa");
		}

	}

}
