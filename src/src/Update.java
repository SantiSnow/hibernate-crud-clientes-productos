package src; 

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.service.spi.ServiceException;

import model.Cliente;
import model.DetallesCliente;

public class Update {

	
	public static Cliente UpdateCliente(Integer clienteId, SessionFactory myFactory, Session mySession) {
		//creamos la transaccion sql
		mySession.beginTransaction();
				
		//clase ORM
		Cliente miCliente = mySession.get(Cliente.class, clienteId);
		DetallesCliente detalles = mySession.get(DetallesCliente.class, clienteId);
		
		JOptionPane.showMessageDialog(null, "Cliente con ID: " + clienteId + "\n" + "Nombre: " + miCliente.getNombre() + " " + miCliente.getApellido() + "\nTelefono: " + miCliente.getTelefono() + "\nDirección: " + miCliente.getDireccion());
		JOptionPane.showMessageDialog(null, "Correo: " + detalles.getCorreo() + "\nComentarios: " + detalles.getComentarios());
		
		if(miCliente != null) {
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
		return miCliente;
	}
}
