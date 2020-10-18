package src;


import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.*;

public class ConsultarProductos {
	
public static Producto consultaProducto(SessionFactory myFactory, Session mySession, Integer idProducto) {
		
		//creamos la transaccion sql
		mySession.beginTransaction();
		
		Producto miProducto = mySession.get(Producto.class, idProducto);
		if(miProducto != null) {
			mySession.getTransaction().commit();
			return miProducto;
		}
		JOptionPane.showMessageDialog(null, "No se encontro un producto con ese ID.");
		mySession.getTransaction().commit();
		return null;
	}
}
