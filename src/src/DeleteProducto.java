package src;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.*;

public class DeleteProducto {
	
	public static Producto deleteProducto(SessionFactory myFactory, Session mySession, Integer idProducto) {
		
		mySession.beginTransaction();
		
		Producto miProducto = mySession.get(Producto.class, idProducto);
		
		if(miProducto != null) {
			JOptionPane.showMessageDialog(null, "Producto: " + miProducto.getNombre() + "\nSeccion: " + miProducto.getSeccion());
			Integer eleccion = JOptionPane.showConfirmDialog(null, "¿Desea realmente borrar estos registros?", "Borrar producto", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if(eleccion == 0) {
				mySession.delete(miProducto);
				JOptionPane.showMessageDialog(null, "Producto borrado.");
				mySession.getTransaction().commit();
			}
			else {
				JOptionPane.showMessageDialog(null, "El Producto no fue borrado.");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "No se encontro un producto con ese ID.");
		}
		
		return miProducto;
	}

}
