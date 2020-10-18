package src;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.*;

public class UpdateProducto {

	public static Producto updateProductos(SessionFactory myFactory, Session mySession, Integer idPedido) {
		Producto miProducto = mySession.get(Producto.class, idPedido);
		
		//creamos la transaccion sql
		mySession.beginTransaction();
				
		if(miProducto != null) {
			JOptionPane.showMessageDialog(null, miProducto.toString());
			
			int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese 1 para actualizar nombre "
					+ "\nIngrese 2 para cambiar el detalle "
					+ "\nIngrese 3 para cambiar el precio "
					+ "\nIngrese 4 para cambiar el stock "
					+ "\nIngrese 5 para cambiar la seccion"));
			
			switch (opcion) {
			case 1:
				//actualizacion del dato deseado con un setter
				String nuevoNombre = JOptionPane.showInputDialog(null, "Ingrese nuevo nombre del producto: ");
				miProducto.setNombre(nuevoNombre);
				break;

			case 2:
				String detalle = JOptionPane.showInputDialog(null, "Ingrese nuevo detalle del producto: ");
				miProducto.setDetalle(detalle);
				break;
			case 3:
				Integer precio = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese nuevo precio del producto"));
				miProducto.setPrecio(precio);
				break;
			case 4:
				Integer stock = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese nuevo stock del producto"));
				miProducto.setStock(stock);
				break;
			case 5:
				String [] seccionesDisp = {"Informatica", "Libreria", "Textiles", "Deportes", "Muebles"};
				String seccion = (String) JOptionPane.showInputDialog(null,"Elija la sección: ", "Elegir",JOptionPane.QUESTION_MESSAGE,null, seccionesDisp, seccionesDisp[1]);
				miProducto.setSeccion(seccion);
				break;
			}
			
			mySession.getTransaction().commit();
			return miProducto;
		}
		else {
			JOptionPane.showMessageDialog(null, "No se encontro un pedido con ese ID.");
			mySession.getTransaction().commit();
			return miProducto;
		}
	}
}
