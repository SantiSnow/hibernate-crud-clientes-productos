package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.service.spi.ServiceException;
import org.junit.*;

import src.Cliente;
import src.DetallesCliente;
import src.Pedido;


public class TestSistema {
	@BeforeClass
	public static void avisoInicioDeLosTest() {
		JOptionPane.showMessageDialog(null, "El test del Sistema ha comenzado correctamente");
	}
	
	@AfterClass
	public static void avisoFinDeLosTest() {
		JOptionPane.showMessageDialog(null, "El test del Sistema ha finalizado, compruebe los resultados");
	}
	
	@Test
	public void testParaInsertarClientes() {
		//creamos un session factory
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Cliente.class)
			.addAnnotatedClass(DetallesCliente.class)
			.addAnnotatedClass(Pedido.class)
			.buildSessionFactory();
				
		Session mySession = myFactory.openSession();
		try {
			
			String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre del cliente: ");
			String apellido = JOptionPane.showInputDialog(null, "Ingrese apellido del cliente: ");
			String direccion = JOptionPane.showInputDialog(null, "Ingrese dirección del cliente: ");
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
			assertEquals(nombre, clienteInsertado.getNombre());
			assertEquals(apellido, clienteInsertado.getApellido());
			assertEquals(direccion, clienteInsertado.getDireccion());
			assertEquals(telefono, clienteInsertado.getTelefono());
			assertEquals(compras, clienteInsertado.getCompras());
			
			
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
