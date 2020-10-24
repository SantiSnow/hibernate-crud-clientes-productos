package src;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import model.*;

public class RespaldarBaseDeDatos {
	
	public static String respaldarProd(Session mySession) throws Exception{
		
		String [] sistemaCarpetas = {"Documents", "Desktop", "Downloads"}; 
		String carpeta = (String) JOptionPane.showInputDialog(null,"Ingrese la carpeta de destino: ", "Elegir",JOptionPane.QUESTION_MESSAGE,null, sistemaCarpetas, sistemaCarpetas[0]);
		
		String filename = "C:\\users\\Santiago\\" + carpeta + "\\respaldo-productos.csv";
	    FileWriter fw = new FileWriter(filename);
	    
	    //creamos la transaccion sql
	    mySession.beginTransaction();
	    List<Producto> listaProductos;
	    listaProductos = mySession.createQuery("from Producto").getResultList();
		//commit
		mySession.getTransaction().commit();
		
		for(Producto i: listaProductos) {
			fw.append("\n" + i.getId());
			fw.append(',');
			fw.append(i.getNombre());
			fw.append(',');
			fw.append(i.getDetalle());
			fw.append(',');
			fw.append("" + i.getPrecio());
			fw.append(',');
			fw.append(i.getSeccion());
			fw.append(',');
			fw.append("" + i.getStock());
			fw.append(',');
		}
		fw.flush();
		fw.close();
		
		return filename;
	}
	
	public static void respaldarCli(Session mySession) throws Exception{
		String [] sistemaCarpetas = {"Documents", "Desktop", "Downloads"}; 
		String carpeta = (String) JOptionPane.showInputDialog(null,"Ingrese la carpeta de destino: ", "Elegir",JOptionPane.QUESTION_MESSAGE,null, sistemaCarpetas, sistemaCarpetas[0]);
		String filename = "C:\\users\\Santiago\\" + carpeta + "\\respaldo-clientes.csv";
		FileWriter fw = new FileWriter(filename);
            
    	//creamos la transaccion sql
    		mySession.beginTransaction();
    		List<Cliente> listaClientes;
    		listaClientes = mySession.createQuery("from Cliente").getResultList();
    		//commit
    		mySession.getTransaction().commit();
    		for(Cliente i: listaClientes) {
    			fw.append("\n" + i.getId());
    			fw.append(',');
    			fw.append(i.getNombre());
    			fw.append(',');
    			fw.append(i.getApellido());
    			fw.append(',');
    			fw.append(i.getDireccion());
    			fw.append(',');
    			fw.append("" + i.getCompras());
    			fw.append(',');
    			fw.append("" + i.getTelefono());
    			fw.append(',');
    			fw.append(i.getDetallesCliente().getCorreo());
    			fw.append(',');
    			fw.append(i.getDetallesCliente().getComentarios());
    			fw.append(',');
    		}
    		fw.flush();
            fw.close();		
	}
	
	public static void respaldarPed(Session mySession) throws Exception{
		String [] sistemaCarpetas = {"Documents", "Desktop", "Downloads"}; 
		String carpeta = (String) JOptionPane.showInputDialog(null,"Ingrese la carpeta de destino: ", "Elegir",JOptionPane.QUESTION_MESSAGE,null, sistemaCarpetas, sistemaCarpetas[0]);
		//creacion del archivo
		String filename = "C:\\users\\Santiago\\" + carpeta + "\\respaldo-pedidos.csv";
		FileWriter fw = new FileWriter(filename);
		
		//conexion a mysql
		//creamos la transaccion sql
		mySession.beginTransaction();
		List<Pedido> listaPedidos;
		listaPedidos = mySession.createQuery("from Pedido").getResultList();
		//commit
		mySession.getTransaction().commit();
		for(Pedido i: listaPedidos) {
			fw.append("\n" + i.getId());
			fw.append(',');
			fw.append(i.getFormaPago());
			fw.append(',');
			fw.append("" + i.getFecha());
			fw.append(',');
			fw.append("" + i.getCliente().getNombre() + " " + i.getCliente().getApellido());
			fw.append(',');
		}
		fw.flush();
        fw.close();
	}
}
