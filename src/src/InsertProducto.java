package src;

import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Producto;

public class InsertProducto {
	
	public static Producto insertarPrducto(SessionFactory myFactory, Session mySession, String nombre, Integer precio, Integer stock, String detalle, String seccion) throws Exception {
		//creamos objetos orm
		Producto miProducto = new Producto(nombre, precio, stock, detalle, seccion);

		//creamos la transaccion sql
		mySession.beginTransaction();
		
		//crea la instruccion sql por nosotros
		mySession.save(miProducto);
		
		mySession.getTransaction().commit();
		
		Producto productoInsertado = mySession.get(Producto.class, miProducto.getId());
		
		JOptionPane.showMessageDialog(null, "El producto insertado fue: \n" + productoInsertado.toString() + "\nNombre: " + miProducto.getNombre() + ", Seccion" + miProducto.getSeccion() + ", Precio: $" 
				+ productoInsertado.getPrecio());
		
		return miProducto;
	}

}
