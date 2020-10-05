package src;

import javax.swing.JOptionPane;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.service.spi.ServiceException;


public class Insert {
	
	public static void main(String[] args) {
		//creamos un session factory
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Cliente.class)
				.addAnnotatedClass(DetallesCliente.class)
				.buildSessionFactory();
		
		Session mySession = myFactory.openSession();
		
		try {
			
			String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre del cliente: ");
			String apellido = JOptionPane.showInputDialog(null, "Ingrese apellido del cliente: ");
			String direccion = JOptionPane.showInputDialog(null, "Ingrese direcci�n del cliente: ");
			Integer telefono = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese telefono del cliente: "));
			Integer compras = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese cantidad de compras del cliente: "));
			String correo = JOptionPane.showInputDialog(null, "Ingrese correo del cliente: ");
			String comentarios = JOptionPane.showInputDialog(null, "Ingrese comentarios del cliente: ");
			
			//creamos un objeto orm
			Cliente miCliente = new Cliente(nombre, apellido, direccion, telefono, compras);
			DetallesCliente detallesC = new DetallesCliente(correo, comentarios);
			miCliente.setDetallesCliente(detallesC);

			//creamos la transaccion sql
			mySession.beginTransaction();
			
			//crea la instruccion sql por nosotros
			mySession.save(miCliente);
			
			mySession.getTransaction().commit();
			
			Cliente clienteInsertado = mySession.get(Cliente.class, miCliente.getId());
			
			JOptionPane.showMessageDialog(null, "El registro insertado fue= \n" + clienteInsertado.toString());
			
			
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
