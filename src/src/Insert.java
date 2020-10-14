package src;

import javax.swing.JOptionPane;

import org.hibernate.*;

public class Insert {
	
	public static void insertarCliente(String nombre, String apellido, String direccion, Integer telefono, Integer compras, String correo, String comentarios, SessionFactory myFactory, Session mySession) throws Exception {
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
}
