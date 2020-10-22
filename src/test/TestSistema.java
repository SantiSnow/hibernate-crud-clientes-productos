package test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.service.spi.ServiceException;
import org.junit.*;

import model.Cliente;
import model.DetallesCliente;
import model.Pedido;
import src.Insert;
import src.UpdatePedido;


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
	public void testPruebaConexionEstandar() {
		//datos conexion mysql
		String host = "jdbc:mysql://localhost:3306/hibernate?serverTimezone=UTC&useSSL=false";
		String usr = "root";
		String pass = "";
			
		try {
				
			System.out.println("Intentando conectar");
			
			Connection miConexion = DriverManager.getConnection(host, usr, pass);
			
			Assert.assertTrue(miConexion != null);
			
			System.out.println("Conexion exitosa");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//test de prueba de conexion con hibernate
	@Test
	public void testParaProbarLaConexion() {
		
		//creamos un session factory
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Cliente.class)
			.addAnnotatedClass(DetallesCliente.class)
			.addAnnotatedClass(Pedido.class)
			.buildSessionFactory();
		
		Session mySession = myFactory.openSession();
		
		Assert.assertTrue(mySession != null);
		
		mySession.close();
		
		myFactory.close();
		
		
		
	}
	
	//test que prueba si la insersion de clientes es correcta
	@Test
	public void testParaInsertarClientes() {
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Cliente.class)
				.addAnnotatedClass(DetallesCliente.class)
				.addAnnotatedClass(Pedido.class)
				.buildSessionFactory();
		
		Session mySession = myFactory.openSession();
		//insertar cliente
		String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre del cliente: ");
		String apellido = JOptionPane.showInputDialog(null, "Ingrese apellido del cliente: ");
		String direccion = JOptionPane.showInputDialog(null, "Ingrese dirección del cliente: ");
		Integer telefono = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese telefono del cliente: "));
		Integer compras = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese cantidad de compras del cliente: "));
		String correo = JOptionPane.showInputDialog(null, "Ingrese correo del cliente: ");
		String comentarios = JOptionPane.showInputDialog(null, "Ingrese comentarios del cliente: ");
		try {
			Cliente clienteInsertado = Insert.insertarCliente(nombre, apellido, direccion, telefono, compras, correo, comentarios, myFactory, mySession);
			
			Assert.assertFalse(clienteInsertado == null);
			
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}
	
	//test que prueba si la insersion de pedidos de clientes es correcta
	@Test
	public void testParaInsertarPedidos() {
		//creamos un session factory
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Cliente.class)
			.addAnnotatedClass(DetallesCliente.class)
			.addAnnotatedClass(Pedido.class)
			.buildSessionFactory();
				
		Session mySession = myFactory.openSession();
		
		try {
			
			Integer idCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese id del cliente que realiza el pedido: "));
			Calendar fecha = Calendar.getInstance();
			String pago = JOptionPane.showInputDialog(null, "Ingrese forma de pago del cliente: ");
			
			Cliente miCliente = mySession.get(Cliente.class, idCliente);
			Pedido miPedido = new Pedido(fecha.getTime(), pago, miCliente);
			
			miCliente.agregarPedido(miPedido);

			//creamos la transaccion sql
			mySession.beginTransaction();
			
			//crea la instruccion sql por nosotros
			mySession.save(miPedido);
			
			mySession.getTransaction().commit();
			
			Pedido pedidoInsertado = mySession.get(Pedido.class, miPedido.getId());
			
			assertEquals(idCliente, pedidoInsertado.getCliente().getId());
			assertEquals(pago, pedidoInsertado.getFormaPago());
			assertEquals(miCliente.getNombre(), pedidoInsertado.getCliente().getNombre());
			
			JOptionPane.showMessageDialog(null, "El registro insertado fue: \n" + pedidoInsertado.toString() + "\nCliente: " + miCliente.getNombre() + " " + miCliente.getApellido());
			
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
	
	//test que prueba si la eliminacion de clientes es correcta
	@Ignore
	public void testParaEliminarClientes() {
		//creamos un session factory
				SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml")
						.addAnnotatedClass(Cliente.class)
						.addAnnotatedClass(DetallesCliente.class)
						.addAnnotatedClass(Pedido.class)
						.buildSessionFactory();
						
				//creamos un session
				Session mySession = myFactory.openSession();
				
				try{
					
					mySession.beginTransaction();
					
					Integer idCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del cliente a eliminar: "));
					
					Cliente miCliente = mySession.get(Cliente.class, idCliente);
					DetallesCliente detalles = mySession.get(DetallesCliente.class, idCliente);
					
					if(miCliente != null && detalles != null) {
						JOptionPane.showMessageDialog(null, "Id del cliente: " + miCliente.getId() + "\nNombre: " + miCliente.getNombre() + " " + miCliente.getApellido() + "\nTelefono: " + miCliente.getTelefono() + "\nDireccion: " + miCliente.getDireccion() + "\nCompras: " + miCliente.getCompras());
						JOptionPane.showMessageDialog(null, "Detalles del cliente: " + detalles.getCorreo() + "\nComentarios: " + detalles.getComentarios());
						
						Integer opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Desea realmente borrar estos registros?\nIngrese 1 para eliminar y continuar, o 2 para no borrarlo."));
						
						if(opcion == 1) {
							mySession.delete(miCliente);
							JOptionPane.showMessageDialog(null, "Cliente y datos borrados.");
						}
						else {
							JOptionPane.showMessageDialog(null, "El cliente no fue borrado.");
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "No se encontro un cliente con ese ID.");
					}
					
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
	
	//test que prueba si la eliminacion de pedidos de clientes es correcta, ignorado para no eliminar ahora clientes ni pedidos
	@Ignore
	public void testParaEliminarPedidos() {
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Cliente.class)
				.addAnnotatedClass(DetallesCliente.class)
				.addAnnotatedClass(Pedido.class)
				.buildSessionFactory();
		
		Session mySession = myFactory.openSession();
		
		try {
			
			Integer idPedido = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese id del pedido a eliminar: "));
			
			Pedido miPedido = mySession.get(Pedido.class, idPedido);

			//creamos la transaccion sql
			mySession.beginTransaction();
			
			if(miPedido != null) {
				JOptionPane.showMessageDialog(null, miPedido.toString() + "\nCliente: " + miPedido.getCliente().getNombre() + " " + miPedido.getCliente().getApellido());
						
				Integer opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Desea realmente borrar estos registros?\nIngrese 1 para eliminar y continuar, o 2 para no borrarlo."));
				if(opcion == 1) {
					mySession.delete(miPedido);
					JOptionPane.showMessageDialog(null, "Pedido borrado.");
				}
				else {
					JOptionPane.showMessageDialog(null, "El pedido no fue borrado.");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "No se encontro un pedido con ese ID.");
			}
			
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
	
	//test que prueba si la actualizacion de pedidos de clientes es correcta
	@Test
	public void testParaActualizarPedidos() {
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Cliente.class)
				.addAnnotatedClass(DetallesCliente.class)
				.addAnnotatedClass(Pedido.class)
				.buildSessionFactory();
		
		Session mySession = myFactory.openSession();
		
		try {
			Integer idPedido = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del pedido a actualizar"));
			Pedido pedidoAct = UpdatePedido.updatePedido(myFactory, mySession, idPedido);
			
			Assert.assertTrue(pedidoAct != null);
			
		}
		catch (Exception e) {
			
		}
	}
	
	
	//test que prueba si la actualizacion de clientes es correcta
	@Test
	public void testParaActualizarClientes() {
		//creamos un session factory
				SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml")
						.addAnnotatedClass(Cliente.class)
						.addAnnotatedClass(DetallesCliente.class)
						.addAnnotatedClass(Pedido.class)
						.buildSessionFactory();
						
				//creamos un session
				Session mySession = myFactory.openSession();
						
				try {
					
					//criterio de busqueda
					Integer clienteId = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del cliente a actualizar"));
					
					//creamos la transaccion sql
					mySession.beginTransaction();
							
					//clase ORM
					Cliente miCliente = mySession.get(Cliente.class, clienteId);
					DetallesCliente detalles = mySession.get(DetallesCliente.class, clienteId);
					
					JOptionPane.showMessageDialog(null, "Cliente con ID: " + clienteId + "\n" + "Nombre: " + miCliente.getNombre() + " " + miCliente.getApellido() + "\nTelefono: " + miCliente.getTelefono() + "\nDirección: " + miCliente.getDireccion());
					JOptionPane.showMessageDialog(null, "Correo: " + detalles.getCorreo() + "\nComentarios: " + detalles.getComentarios());
					
					int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese 1 para actualizar nombre \n2 para cambiar el apellido \n3 para cambiar el telefono \n4 para actualizar la dirección \n5 para cambiar el correo \nO 6 para cambiar los comentarios:"));

					switch (opcion) {
					case 1:
						//actualizacion del dato deseado con un setter
						String nuevoNombre = JOptionPane.showInputDialog(null, "Ingrese nuevo nombre del cliente: ");
						miCliente.setNombre(nuevoNombre);
						break;

					case 2:
						String nuevoapellido = JOptionPane.showInputDialog(null, "Ingrese nuevo apellido del cliente: ");
						miCliente.setApellido(nuevoapellido);
						break;
					case 3:
						Integer nuevoTel = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese nuevo telefono del cliente"));
						miCliente.setTelefono(nuevoTel);
						break;
					case 4:
						String nuevaDireccion = JOptionPane.showInputDialog(null, "Ingrese nueva direccion del cliente: ");
						miCliente.setDireccion(nuevaDireccion);
						break;
					case 5:
						String correo = JOptionPane.showInputDialog(null, "Ingrese nueva direccion del cliente: ");
						detalles.setCorreo(correo);
						break;
					case 6:
						String comentarios = JOptionPane.showInputDialog(null, "Ingrese nueva direccion del cliente: ");
						detalles.setComentarios(comentarios);
						break;
					default:
						
						break;
					}
					
					mySession.getTransaction().commit();
					
					JOptionPane.showMessageDialog(null, "Registro actualizado con exito");
					System.out.println("Registro actualizado con exito");
					
					
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
